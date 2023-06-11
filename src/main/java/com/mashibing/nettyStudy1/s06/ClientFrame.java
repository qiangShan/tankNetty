package com.mashibing.nettyStudy1.s06;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientFrame extends Frame {

    public static final ClientFrame INSTANCE=new ClientFrame();

    TextArea ta=new TextArea();
    TextField tf=new TextField();
    Client c=null;

    public ClientFrame(){
        this.setSize(600,400);
        this.setLocation(100,20);
        this.add(ta,BorderLayout.CENTER);
        this.add(tf,BorderLayout.SOUTH);

        tf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //发送消息到服务器
                c.send(tf.getText());
                ta.setText(ta.getText()+tf.getText());
                tf.setText("");
            }
        });

    }

    public void connectToServer(){
        c=new Client();
        c.connect();
    }

    public static void main(String[] args) {
        ClientFrame frame=ClientFrame.INSTANCE;
        frame.setVisible(true);
        frame.connectToServer();
    }

    public void updateText(String msgAccepted){
        this.ta.setText(ta.getText()+System.getProperty("line.separator")+msgAccepted);
    }
}
