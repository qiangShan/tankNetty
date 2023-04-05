package com.mashibing.tank;

import java.awt.*;
import java.awt.event.*;

public class TankFrame extends Frame {

    private static final int GAME_WIDTH=960;
    private static final int GAME_HEIGHT=720;

    Tank myTank=new Tank(300,300,Dir.DOWN);
    Bullet b=new Bullet(300,300,Dir.DOWN);


    public TankFrame (){
        this.setSize(GAME_WIDTH,GAME_HEIGHT);
        this.setResizable(false);
        this.setTitle("tankNetty");
        this.setVisible(true);

        this.addKeyListener(new MyKeyListener());

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g){

        myTank.paint(g);
        b.paint(g);

    }

    class MyKeyListener extends KeyAdapter{ //获取键盘

        boolean bL=false;
        boolean bU=false;
        boolean bR=false;
        boolean bD=false;


        @Override
        public void keyPressed(KeyEvent e) {  //按下

            int key=e.getKeyCode();

            switch (key){
                case KeyEvent.VK_LEFT:
                    bL=true;
                    break;
                case KeyEvent.VK_UP:
                    bU=true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR=true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD=true;
                    break;
                default:
                    break;
            }

            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {  //抬起

            int key=e.getKeyCode();

            switch (key){

                case KeyEvent.VK_LEFT:
                    bL=false;
                    break;
                case KeyEvent.VK_UP:
                    bU=false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR=false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD=false;
                    break;
                default:
                    break;
            }

            setMainTankDir();
        }

        public void setMainTankDir(){
            if(!bL && !bU && !bR && !bD){
               myTank.setMoving(false);
            }else{
                myTank.setMoving(true);
                if(bL) myTank.setDir(Dir.LEFT);
                if(bU) myTank.setDir(Dir.UP);
                if(bR) myTank.setDir(Dir.RIGHT);
                if(bD) myTank.setDir(Dir.DOWN);
            }
        }
    }
}
