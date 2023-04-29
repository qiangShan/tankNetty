package com.mashibing.tank;

import com.mashibing.facade.GameModel;
import com.mashibing.facade.GameObject;

import java.awt.*;

public class Bullet extends GameObject {

    private static final int SPEED=10;
    public static final int WIDTH=ResourceMgr.bulletL.getWidth();
    public static final int HEIGHT=ResourceMgr.bulletL.getHeight();

    private boolean living =true;

    public Group group=Group.BAD;

    private int x ,y;
    private Dir dir=Dir.DOWN;

    public Rectangle rect=new Rectangle();

    public Bullet(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group=group;

        rect.x=this.x;
        rect.y=this.y;
        rect.width=WIDTH;
        rect.height=HEIGHT;

        GameModel.getInstance().add(this);
    }

    @Override
    public void paint(Graphics g){

        if(!living){
            GameModel.getInstance().remove(this);
        }

        switch (dir){
            case LEFT:
                g.drawImage(ResourceMgr.bulletL,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD,x,y,null);
                break;
            default:
                break;
        }

        move();
    }

    private void move() {

            switch (dir) {
                case LEFT:
                    x -= SPEED;
                    break;
                case UP:
                    y -= SPEED;
                    break;
                case RIGHT:
                    x += SPEED;
                    break;
                case DOWN:
                    y += SPEED;
                    break;
                default:
                    break;
            }

            //update rect
            rect.x=this.x;
            rect.y=this.y;

            if(x<0 || y<0 ||x>TankFrame.GAME_WIDTH ||y>TankFrame.GAME_HEIGHT)
                living =false;
        }

    public void die() {
        this.living=false;
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

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isLiving() {
        return living;
    }

    public void setLiving(boolean living) {
        this.living = living;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }
}
