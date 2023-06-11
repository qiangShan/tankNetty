package com.mashibing.nettyStudy1.s13;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.ReferenceCountUtil;

/**
 * 代码重构，将main方法代码放到connect();
 * new Client().connect();
 * */

public class Client {

    private Channel channel=null;

    public void connect(){
        //线程池
        EventLoopGroup group=new NioEventLoopGroup(2);

        Bootstrap b=new Bootstrap();

        try{
            ChannelFuture f=b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientChannelInitializer())
                    .connect("localhost",8888);

            f.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if(!future.isSuccess()){
                        System.out.println("not connected!");
                    }else{
                        System.out.println("connected!");
                        //initialize the channel
                        channel=future.channel();
                    }
                }
            });

            f.sync();
            f.channel().closeFuture().sync();
            System.out.println("客户端已经退出!");

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }

    public void closeConnect(){
        this.send("_bye_");
    }

    public void send(String msg){
        ByteBuf buf=Unpooled.copiedBuffer(msg.getBytes());
        channel.writeAndFlush(buf);
    }

    public static void main(String[] args) {
        Client c=new Client();
        c.connect();
    }
}

class ClientChannelInitializer extends ChannelInitializer<SocketChannel>{

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline()
                .addLast(new TankMsgEncoder())
                .addLast(new ClientHandler());
    }
}

class ClientHandler extends ChannelInboundHandlerAdapter{
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf=null;
        try{
            buf=(ByteBuf)msg;
            byte[] bytes=new byte[buf.readableBytes()];
            buf.getBytes(buf.readerIndex(),bytes);
            String msgAccepted=new String(bytes);
            ClientFrame.INSTANCE.updateText(msgAccepted);
        }finally {
            if(buf !=null){
                ReferenceCountUtil.release(buf);
            }
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(new TankMsg(5,8));
    }
}
