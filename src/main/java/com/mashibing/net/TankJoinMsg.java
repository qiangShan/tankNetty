package com.mashibing.net;

import com.mashibing.tank.Dir;
import com.mashibing.tank.Group;
import com.mashibing.tank.Tank;

import java.io.*;
import java.util.UUID;

public class TankJoinMsg {

    public int x;
    public int y;
    public Dir dir;
    public boolean moving;
    public Group group;
    public UUID id;

    public TankJoinMsg(Tank t){
        this.x=t.getX();
        this.y=t.getY();
        this.dir=t.getDir();
        this.group=t.getGroup();
        this.id=t.getId();
        this.moving=t.isMoving();
    }

    public TankJoinMsg() {
    }

    public TankJoinMsg(int x, int y, Dir dir, boolean moving, Group group, UUID id) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.moving = moving;
        this.group = group;
        this.id = id;
    }

    public void parse(byte[] bytes){
        DataInputStream dis=new DataInputStream(new ByteArrayInputStream(bytes));

        try {
            this.x=dis.readInt();
            this.y=dis.readInt();
            this.dir=Dir.values()[dis.readInt()];
            this.moving=dis.readBoolean();
            this.group=Group.values()[dis.readInt()];
            this.id=new UUID(dis.readLong(),dis.readLong());
        }catch (IOException e){
            e.printStackTrace();
        }finally {

            try{
                if(dis != null){
                    dis.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public byte[] boBytes() {
        ByteArrayOutputStream baos=null;
        DataOutputStream dos=null;
        byte[] bytes=null;

        try{
            baos=new ByteArrayOutputStream();
            dos=new DataOutputStream(baos);
            dos.writeInt(x);
            dos.writeInt(y);
            dos.writeInt(dir.ordinal());
            dos.writeBoolean(moving);
            dos.writeInt(group.ordinal());
            dos.writeLong(id.getMostSignificantBits());
            dos.writeLong(id.getLeastSignificantBits());

            dos.flush();
            bytes=baos.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(baos !=null){
                    baos.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }

            try {
                if(dos !=null){
                    dos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }
}
