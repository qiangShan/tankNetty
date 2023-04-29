package com.mashibing.decorator;

import com.mashibing.facade.GameObject;

import java.awt.*;

public class TailDecorator extends GODecorator{

    public TailDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g){
        this.x=go.x;
        this.y=go.y;
        go.paint(g);

        Color color=g.getColor();
        g.setColor(Color.YELLOW);
        g.drawLine(go.x,go.y,go.x+getWidth(),go.y+getHeight());
        g.setColor(color);
    }

    @Override
    public int getWidth() {
        return go.getWidth();
    }

    @Override
    public int getHeight() {
        return go.getHeight();
    }
}
