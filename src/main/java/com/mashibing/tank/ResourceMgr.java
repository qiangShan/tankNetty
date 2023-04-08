package com.mashibing.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceMgr {

    public static  BufferedImage goodTankL,goodTankU,goodTankR,goodTankD;
    public static  BufferedImage badTankL,badTankU,badTankR,badTankD;
    public static  BufferedImage bulletL,bulletU,bulletR,bulletD;
    public static BufferedImage[] explodes=new BufferedImage[16];

    static{
        try {
            goodTankL= ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            goodTankU= ImageUtil.rotateImage(goodTankL,-90);
            goodTankR= ImageUtil.rotateImage(goodTankL,90);
            goodTankD= ImageUtil.rotateImage(goodTankL,180);

            badTankL=ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            badTankU=ImageUtil.rotateImage(badTankL,-90);
            badTankR=ImageUtil.rotateImage(badTankL,90);
            badTankD=ImageUtil.rotateImage(badTankL,180);

            bulletL=ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletL.gif"));
            bulletU=ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
            bulletR=ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletR.gif"));
            bulletD=ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));

            for(int i=0 ;i< 16; i++){
                explodes[i]=ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e"+(i+1)+".gif"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
