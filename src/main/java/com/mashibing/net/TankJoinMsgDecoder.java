package com.mashibing.net;

import com.mashibing.tank.Dir;
import com.mashibing.tank.Group;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.UUID;

public class TankJoinMsgDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

        if(byteBuf.readableBytes()<33) return;

        TankJoinMsg tankJoinMsg=new TankJoinMsg();

       tankJoinMsg.x=byteBuf.readInt();
       tankJoinMsg.y=byteBuf.readInt();
       tankJoinMsg.dir=Dir.values()[byteBuf.readInt()];
       tankJoinMsg.moving=byteBuf.readBoolean();
       tankJoinMsg.group= Group.values()[byteBuf.readInt()];
       tankJoinMsg.id=new UUID(byteBuf.readLong(),byteBuf.readLong());

       list.add(tankJoinMsg);
    }
}
