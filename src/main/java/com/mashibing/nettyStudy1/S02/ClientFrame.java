package com.mashibing.nettyStudy1.S02;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientFrame extends Frame {

    TextArea ta=new TextArea();
    TextField tf=new TextField();

    public ClientFrame(){
        this.setSize(600,400);
        this.setLocation(100,20);
        this.add(ta,BorderLayout.CENTER);
        this.add(tf,BorderLayout.SOUTH);

        tf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //发送消息到服务器
                ta.setText(ta.getText()+tf.getText());
                tf.setText("");
            }
        });

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new ClientFrame();
    }
}
