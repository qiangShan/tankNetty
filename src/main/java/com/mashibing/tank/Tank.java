package com.mashibing.tank;

import com.mashibing.facade.GameModel;
import com.mashibing.facade.GameObject;

import java.awt.*;
import java.util.Random;

public class Tank extends GameObject {

    public static final int WIDTH = ResourceMgr.goodTankD.getWidth();
    public static final int HEIGHT = ResourceMgr.goodTankD.getHeight();
    private static final int SPEED = 5;

    private boolean moving=true;
    private boolean living=true;

    public Rectangle rect=new Rectangle();

    private int x, y;
    public Group group=Group.BAD;

    private Random random=new Random();

    private Dir dir = Dir.DOWN;

    public GameModel gm=null;

    int oldX,oldY;

    public Tank(int x, int y, Dir dir,Group group, GameModel gm) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group=group;
        this.gm=gm;


        rect.x=this.x;
        rect.y=this.y;
        rect.width=WIDTH;
        rect.height=HEIGHT;
    }

    @Override
    public void paint(Graphics g) {

        if(!living)
            gm.remove(this);

        switch (dir){
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL: ResourceMgr.badTankL, x, y,null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU: ResourceMgr.badTankU, x, y,null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR: ResourceMgr.badTankR, x, y,null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD: ResourceMgr.badTankD, x, y,null);
                break;
            default:
                break;
        }
        move();
    }

    public void back(){
        x=oldX;
        y=oldY;
    }

    private void move() {
        //记录移动之前的位置
        oldX=x;
        oldY=y;

        if(!moving) return;

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

        if(this.group == Group.BAD && random.nextInt(100)>95)
            this.fire();

        if(this.group == Group.BAD && random.nextInt(100)>95)
            randomDir();

        boundsCheck();

        //update rect
        rect.x=this.x;
        rect.y=this.y;
    }

    public void fire(){
        int bX=this.x+Tank.WIDTH/2-Bullet.WIDTH/2;
        int bY=this.y+Tank.HEIGHT/2-Bullet.HEIGHT/2;

        gm.add(new Bullet(bX,bY,this.dir,this.group,this.gm));
    }

    //边界检测
    private void boundsCheck() {
        if(this.x< 2 ) x=2;
        if(this.y< 28)  y=28;
        if(this.x>TankFrame.GAME_WIDTH-Tank.WIDTH-2) x=TankFrame.GAME_WIDTH-Tank.WIDTH-2;
        if(this.y>TankFrame.GAME_HEIGHT-Tank.HEIGHT-2) y=TankFrame.GAME_HEIGHT-Tank.HEIGHT-2;
    }


    public void stop(){
        moving=false;
    }

    private void randomDir() {
        this.dir=Dir.values()[random.nextInt(4)];
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
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

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
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
