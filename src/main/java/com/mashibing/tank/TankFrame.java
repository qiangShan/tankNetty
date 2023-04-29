package com.mashibing.tank;

import com.mashibing.facade.GameModel;

import java.awt.*;
import java.awt.event.*;


public class TankFrame extends Frame {

    public static final int GAME_WIDTH=PropertyMgr.getInt("GAME_WIDTH");
    public static final int GAME_HEIGHT=PropertyMgr.getInt("GAME_HEIGHT");

    GameModel gm=GameModel.getInstance();

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

    //解决游戏中的双闪问题
    Image offScreenImage=null;
    @Override
    public void update(Graphics graphics){
        if(offScreenImage == null){
            offScreenImage=this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics gOffSecreen = offScreenImage.getGraphics();
        Color color=gOffSecreen.getColor();
        gOffSecreen.setColor(Color.BLACK);
        gOffSecreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        gOffSecreen.setColor(color);
        paint(gOffSecreen);
        graphics.drawImage(offScreenImage,0,0,null);
    }

    @Override
    public void paint(Graphics g){

        gm.paint(g);

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
                case KeyEvent.VK_CONTROL:
                    gm.getMainTank().fire();
                    break;
                default:
                    break;
            }

            setMainTankDir();
        }

        public void setMainTankDir(){
            if(!bL && !bU && !bR && !bD){
               gm.getMainTank().setMoving(false);
            }else{
                gm.getMainTank().setMoving(true);
                if(bL) gm.getMainTank().setDir(Dir.LEFT);
                if(bU) gm.getMainTank().setDir(Dir.UP);
                if(bR) gm.getMainTank().setDir(Dir.RIGHT);
                if(bD) gm.getMainTank().setDir(Dir.DOWN);
            }
        }
    }
}
