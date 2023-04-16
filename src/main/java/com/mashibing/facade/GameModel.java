package com.mashibing.facade;

import com.mashibing.chain.ColliderChain;
import com.mashibing.cor.BulletTankCollider;
import com.mashibing.cor.Collider;
import com.mashibing.cor.TankTankCollider;
import com.mashibing.tank.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {

    Tank myTank=new Tank(300,600, Dir.DOWN, Group.GOOD,this);

    private List<GameObject> objects=new ArrayList<>();

    ColliderChain chain=new ColliderChain();

    public GameModel(){

        //初始化敌方坦克
        int initTankCount=Integer.parseInt((String) PropertyMgr.get("initTankCount")) ;
        for(int i=0;i<initTankCount;i++){
            add(new Tank(50+i*80,200, Dir.DOWN, Group.BAD,this));
        }

    }

    public void add(GameObject go){
        this.objects.add(go);
    }

    public void remove(GameObject go){
        this.objects.remove(go);
    }

    public void paint(Graphics g) {

        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.setColor(color);

        //画出主战坦克
        myTank.paint(g);

        //画出所有物品
        for(int i=0; i<objects.size(); i++){
            objects.get(i).paint(g);
        }

        //碰撞检测：互相碰撞
        for(int i=0; i<objects.size();i++){
            for(int j=i+1; j<objects.size(); j++){
                GameObject o1=objects.get(i);
                GameObject o2=objects.get(j);

                chain.collide(o1,o2);
            }
        }


    }

    public Tank getMainTank() {
        return myTank;
    }
}
