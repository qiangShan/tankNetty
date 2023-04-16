package com.mashibing.strategy;

import com.mashibing.tank.Bullet;
import com.mashibing.tank.Dir;
import com.mashibing.tank.Tank;


public class FourFireStrategy implements FireStrategy{

    @Override
    public void fire(Tank t) {
        int bX=t.x+Tank.WIDTH/2- Bullet.WIDTH/2;
        int bY=t.y+Tank.HEIGHT/2-Bullet.HEIGHT/2;

        Dir[] dirs=Dir.values();

        for(Dir dir : dirs){
            new Bullet(bX,bY,dir,t.group,t.tf);
        }
    }
}