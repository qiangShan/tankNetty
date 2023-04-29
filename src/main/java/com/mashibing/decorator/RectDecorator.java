package com.mashibing.decorator;

import com.mashibing.facade.GameObject;

import java.awt.*;

public class RectDecorator extends GODecorator{

    public RectDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g){
        this.x=go.x;
        this.y=go.y;
        go.paint(g);

        Color color=g.getColor();
        g.setColor(Color.WHITE);
        g.drawRect(go.x,go.y,go.getWidth()+2,super.go.getHeight()+2);
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
