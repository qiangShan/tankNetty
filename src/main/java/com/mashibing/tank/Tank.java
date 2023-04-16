package com.mashibing.tank;

import com.mashibing.strategy.DefaultFireStrategy;
import com.mashibing.strategy.FireStrategy;
import com.mashibing.strategy.FourFireStrategy;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class Tank {

    public static final int WIDTH = ResourceMgr.goodTankD.getWidth();
    public static final int HEIGHT = ResourceMgr.goodTankD.getHeight();
    private static final int SPEED = 5;

    private boolean moving=true;
    private boolean living=true;

    Rectangle rect=new Rectangle();


    FireStrategy fireStrategy;

    public int x, y;
    public Group group=Group.BAD;

    private Random random=new Random();

    public Dir dir = Dir.DOWN;
    public TankFrame tf;

    public Tank(int x, int y, Dir dir,Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group=group;
        this.tf=tf;

        rect.x=this.x;
        rect.y=this.y;
        rect.width=WIDTH;
        rect.height=HEIGHT;

        if(this.group == Group.GOOD) {
            String goodFS =(String) PropertyMgr.get("goodFS");
            try {
                fireStrategy = (FireStrategy) Class.forName(goodFS).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            String badFS=(String) PropertyMgr.get("badFS");
            try {
                fireStrategy=(FireStrategy) Class.forName(badFS).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    public void paint(Graphics g) {

        if(!living)
            tf.tanks.remove(this);

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

    public void fire(){
        fireStrategy.fire(this);
    }

    private void move() {

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

    //边界检测
    private void boundsCheck() {
        if(this.x< 2 ) x=2;
        if(this.y< 28)  y=28;
        if(this.x>TankFrame.GAME_WIDTH-Tank.WIDTH-2) x=TankFrame.GAME_WIDTH-Tank.WIDTH-2;
        if(this.y>TankFrame.GAME_HEIGHT-Tank.HEIGHT-2) y=TankFrame.GAME_HEIGHT-Tank.HEIGHT-2;
    }

    private void randomDir() {
        this.dir=Dir.values()[random.nextInt(4)];
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
