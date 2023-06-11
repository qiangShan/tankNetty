import com.mashibing.net.TankJoinMsg;
import com.mashibing.net.TankJoinMsgDecoder;
import com.mashibing.net.TankJoinMsgEncoder;
import com.mashibing.tank.Dir;
import com.mashibing.tank.Group;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class TankJoinMsgCodecTest {

    @Test
    public void testEncoder(){
        EmbeddedChannel channel=new EmbeddedChannel();

        UUID id=UUID.randomUUID();
        TankJoinMsg tankJoinMsg=new TankJoinMsg(5,10, Dir.DOWN,true, Group.BAD, id);
        channel.pipeline()
                .addLast(new TankJoinMsgEncoder());

        channel.writeOutbound(tankJoinMsg);

        ByteBuf buf=(ByteBuf) channel.readOutbound();

        int x=buf.readInt();
        int y=buf.readInt();
        int dirOrdianl=buf.readInt();
        Dir dir=Dir.values()[dirOrdianl];
        boolean moving=buf.readBoolean();
        int groupOrdianl=buf.readInt();
        Group group=Group.values()[groupOrdianl];
        UUID uuid=new UUID(buf.readLong(),buf.readLong());

        assertEquals(5,x);
        assertEquals(10,y);
        assertEquals(Dir.DOWN,dir);
        assertEquals(true,moving);
        assertEquals(Group.BAD,group);
        assertEquals(id,uuid);
    }

    @Test
    public void testDecoder(){
        EmbeddedChannel channel=new EmbeddedChannel();

        UUID id=UUID.randomUUID();
        TankJoinMsg tankJoinMsg=new TankJoinMsg(5,10, Dir.DOWN,true, Group.BAD, id);
        channel.pipeline()
                .addLast(new TankJoinMsgDecoder());

        ByteBuf buf= Unpooled.buffer();
        buf.writeBytes(tankJoinMsg.boBytes());
        channel.writeInbound(buf.duplicate());

        TankJoinMsg tankJoinMsgR=(TankJoinMsg) channel.readInbound();

        assertEquals(5,tankJoinMsgR.x);
        assertEquals(10,tankJoinMsgR.y);
        assertEquals(Dir.DOWN,tankJoinMsgR.dir);
        assertEquals(true,tankJoinMsgR.moving);
        assertEquals(Group.BAD,tankJoinMsgR.group);
        assertEquals(id,tankJoinMsgR.id);
    }
}
