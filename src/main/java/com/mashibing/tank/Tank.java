package com.mashibing.tank;

import java.awt.*;

public class Tank {

    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    private static final int SPEED = 3;

    private boolean moving=false;

    private int x, y;

    private Dir dir = Dir.DOWN;
    TankFrame tf;

    public Tank(int x, int y, Dir dir,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf=tf;
    }



    public void paint(Graphics g) {
        Color color=g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, WIDTH, HEIGHT);
        g.setColor(color);

        move();
    }

    public void fire(){
        tf.b=new Bullet(this.x,this.y,this.dir);
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

}
