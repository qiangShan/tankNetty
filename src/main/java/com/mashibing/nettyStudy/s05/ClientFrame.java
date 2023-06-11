package com.mashibing.nettyStudy.s05;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientFrame extends Frame {

    TextArea ta=new TextArea();
    TextField tf=new TextField();

    Client c=null;

    public ClientFrame() throws InterruptedException {
        this.setSize(600,400);
        this.setLocation(100,20);
        this.add(ta,BorderLayout.CENTER);
        this.add(tf,BorderLayout.SOUTH);
        tf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //把字符串发送到服务器，服务器进行转发
                c.send(tf.getText());
                ta.setText(ta.getText()+tf.getText());
                tf.setText("");
            }
        });
        this.setVisible(true);

        connectToServer();
    }

    private void connectToServer() throws InterruptedException {
        c=new Client();
        c.connect();
    }

    public static void main(String[] args) throws InterruptedException {
        new ClientFrame();
    }
}
