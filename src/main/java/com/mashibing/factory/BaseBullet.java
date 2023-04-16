package com.mashibing.factory;

import com.mashibing.tank.Tank;

import java.awt.*;

public abstract class BaseBullet {

    public abstract void paint(Graphics graphics);

    public abstract void collideWith(BaseTank tank);
}