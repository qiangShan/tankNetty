package com.mashibing.tank;

import java.awt.*;

public class Bullet {

    private static final int SPEED=10;
    private static final int WIDTH=30;
    private static final int HEIGHT=30;

    private boolean live=true;

    private int x ,y;
    private Dir dir=Dir.DOWN;
    private TankFrame tf=null;

    public Bullet(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf=tf;
    }

    public void paint(Graphics g){

        if(!live){
            tf.bullets.remove(this);
        }

        Color color = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x,y,WIDTH,HEIGHT);
        g.setColor(color);

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
                live=false;
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

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }
}
