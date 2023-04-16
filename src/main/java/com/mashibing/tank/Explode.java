package com.mashibing.tank;

import com.mashibing.facade.GameModel;
import com.mashibing.facade.GameObject;

import java.awt.*;

public class Explode extends GameObject {

    public static final int WIDTH=ResourceMgr.explodes[0].getWidth();
    public static final int HEIGHT=ResourceMgr.explodes[0].getHeight();

    private int x,y;


    GameModel gm=null;

    private int step=0;

    public Explode(int x, int y, GameModel gm) {
        this.x = x;
        this.y = y;
        this.gm = gm;
    }

    @Override
    public void paint(Graphics g){
        g.drawImage(ResourceMgr.explodes[step++],x,y,null);
        if(step >= ResourceMgr.explodes.length)
            gm.remove(this);
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
