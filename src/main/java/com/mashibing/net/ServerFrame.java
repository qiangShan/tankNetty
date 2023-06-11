package com.mashibing.net;

import com.mashibing.nettyStudy1.s13.Server;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ServerFrame extends Frame{

    public static final ServerFrame INSTANCE=new ServerFrame();

    Button btnStart=new Button("start");
    TextArea taLeft=new TextArea();
    TextArea taRight=new TextArea();
    com.mashibing.nettyStudy1.s13.Server server=new Server();

    public ServerFrame() {
        this.setSize(1600,600);
        this.setLocation(300,30);
        this.add(btnStart,BorderLayout.NORTH);
        Panel p=new Panel(new GridLayout(1,2));
        p.add(taLeft);
        p.add(taRight);
        this.add(p);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }


    public static void main(String[] args) {
        ServerFrame.INSTANCE.setVisible(true);
        ServerFrame.INSTANCE.server.serverStart();
    }

    public void updateClientMsg(String string){
        this.taRight.setText(taLeft.getText()+string+System.getProperty("line.separator"));
    }

    public void updateServerMsg(String string){
        this.taLeft.setText(taLeft.getText()+string+System.getProperty("line.separator"));
    }
}
