package com.mashibing.tank;

import java.awt.*;

public class Bullet {

    private static final int SPEED=10;
    public static final int WIDTH=ResourceMgr.bulletL.getWidth();
    public static final int HEIGHT=ResourceMgr.bulletL.getHeight();

    private boolean living =true;

    private Group group=Group.BAD;

    private int x ,y;
    private Dir dir=Dir.DOWN;
    private TankFrame tf=null;

    public Bullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group=group;
        this.tf=tf;
    }

    public void paint(Graphics g){

        if(!living){
            tf.bullets.remove(this);
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

            if(x<0 || y<0 ||x>TankFrame.GAME_WIDTH ||y>TankFrame.GAME_HEIGHT)
                living =false;
        }

    public void collideWith(Tank tank) {

        if(this.group == tank.getGroup())
            return;

        Rectangle rect1=new Rectangle(this.x,this.y,WIDTH,HEIGHT);
        Rectangle rect2=new Rectangle(tank.getX(),tank.getY(),Tank.WIDTH,Tank.HEIGHT);

        if(rect1.intersects(rect2)){
            tank.die();
            this.die();

            int eX=this.getX()+Tank.WIDTH/2-Explode.WIDTH/2;
            int eY=this.getY()+Tank.HEIGHT/2-Explode.HEIGHT/2;

            tf.explodes.add(new Explode(eX,eY,tf));
        }

    }

    private void die() {
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
}
