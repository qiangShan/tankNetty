package com.mashibing.facade;

import com.mashibing.chain.ColliderChain;
import com.mashibing.cor.BulletTankCollider;
import com.mashibing.cor.Collider;
import com.mashibing.cor.TankTankCollider;
import com.mashibing.tank.*;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {

    private static final GameModel INSTANCE=new GameModel();

    //Tank myTank=new Tank(300,600, Dir.DOWN, Group.GOOD);
    Tank myTank;

    private List<GameObject> objects=new ArrayList<>();

    ColliderChain chain=new ColliderChain();

    private GameModel(){

    }

    static {
        INSTANCE.init();
    }

    private void init(){
        //初始化主战坦克
        myTank=new Tank(300,600, Dir.DOWN, Group.GOOD);

        //初始化敌方坦克
        int initTankCount=Integer.parseInt((String) PropertyMgr.get("initTankCount")) ;
        for(int i=0;i<initTankCount;i++){
            new Tank(50+i*80,200, Dir.DOWN, Group.BAD);
        }
        //初始化墙
        add(new Wall(150,150,200,50));
        add(new Wall(550,150,200,50));
        add(new Wall(300,300,50,200));
        add(new Wall(550,300,50,200));
    }

    public static GameModel getInstance(){
        return INSTANCE;
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

    public void save(){
        File f=null;
        FileOutputStream fos= null;
        ObjectOutputStream oos=null;
        try {
            f=new File("tank.data");
            fos = new FileOutputStream(f);
            oos=new ObjectOutputStream(fos);
            oos.writeObject(myTank);
            oos.writeObject(objects);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(oos != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void load(){
        File f=null;
        FileInputStream fis= null;
        ObjectInputStream ois=null;
        try {
            f=new File("tank.data");
            fis = new FileInputStream(f);
            ois=new ObjectInputStream(fis);
            myTank=(Tank)ois.readObject();
            objects=(List) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(ois != null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
