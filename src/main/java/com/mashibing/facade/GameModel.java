package com.mashibing.facade;

import com.mashibing.tank.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {

    Tank myTank=new Tank(300,600, Dir.DOWN, Group.GOOD,this);
    public List<Bullet> bullets=new ArrayList<Bullet>();
    public List<Tank> tanks=new ArrayList<Tank>();
    public List<Explode> explodes=new ArrayList<>();

    public GameModel(){

        //初始化敌方坦克
        int initTankCount=Integer.parseInt((String) PropertyMgr.get("initTankCount")) ;
        for(int i=0;i<initTankCount;i++){
            tanks.add(new Tank(50+i*80,200, Dir.DOWN, Group.BAD,this));
        }

    }

    public void paint(Graphics g) {

        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量："+bullets.size(),10,60);
        g.drawString("敌方坦克的数量："+tanks.size(),10,80);
        g.drawString("敌方坦克的数量："+tanks.size(),10,100);
        g.setColor(color);

        //画出主战坦克
        myTank.paint(g);

        //画出子弹
        for(int i=0; i<bullets.size(); i++){
            bullets.get(i).paint(g);
        }

        //画出敌方坦克
        for(int i=0;i<tanks.size();i++){
            tanks.get(i).paint(g);
        }

        for(int i=0; i<bullets.size();i++){
            for(int j=0; j<tanks.size(); j++){
                bullets.get(i).collideWith(tanks.get(j));
            }
        }

        for(int i=0; i<explodes.size(); i++){
            explodes.get(i).paint(g);
        }

        /**
         for(Iterator<Bullet> iterator = bullets.iterator();iterator.hasNext();){
         Bullet b = iterator.next();
         if(!b.live)
         iterator.remove();
         }
         */
    }

    public Tank getMainTank() {
        return myTank;
    }
}
