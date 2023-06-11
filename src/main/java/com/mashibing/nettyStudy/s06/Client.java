package com.mashibing.nettyStudy.s06;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.ReferenceCountUtil;

public class Client {

    private Channel channel=null;

    public void connect() {
        EventLoopGroup group=new NioEventLoopGroup(1);
        Bootstrap b=new Bootstrap();

        try{
            ChannelFuture f=b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientChannelInitalizer())
                    .connect("localhost",8888);

            f.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if(! future.isSuccess()){
                        System.out.println("server not connected!");
                    }else{
                        System.out.println("server connected!");
                        channel=future.channel();
                    }
                }
            });

            f.sync();

            f.channel().closeFuture().sync();


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }

    public void send(String msg){
        ByteBuf buf=Unpooled.copiedBuffer(msg.getBytes());
        channel.writeAndFlush(buf);
    }

    public static void main(String[] args){
        Client c=new Client();
        c.connect();
    }
}

class ClientChannelInitalizer extends ChannelInitializer<SocketChannel>{

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast(new ClientChildChannelHandler());
    }
}

class ClientChildChannelHandler extends ChannelInboundHandlerAdapter{
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf=null;
        try{
            buf=(ByteBuf) msg;
            byte[] bytes=new byte[buf.readableBytes()];
            buf.getBytes(buf.readerIndex(),bytes);
            String msgAccepted=new String(bytes);
            ClientFrame.INSTANCE.updateText(msgAccepted);
        }finally {
            if(buf != null){
                ReferenceCountUtil.release(buf);
            }
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf buf= Unpooled.copiedBuffer("hello".getBytes());
        ctx.writeAndFlush(buf);
    }
}
