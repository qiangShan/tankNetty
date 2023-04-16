package com.mashibing.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceMgr {

    public static  BufferedImage goodTankL,goodTankU,goodTankR,goodTankD;
    public static  BufferedImage badTankL,badTankU,badTankR,badTankD;
    public static  BufferedImage bulletL,bulletU,bulletR,bulletD;
    public static BufferedImage[] explodes=new BufferedImage[16];

    private static final ResourceMgr INSTANCE=new ResourceMgr();

    private ResourceMgr(){

    }

    public static ResourceMgr getInstance(){
        return INSTANCE;
    }

    static{
        try {
            goodTankU= ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            goodTankL= ImageUtil.rotateImage(goodTankU,-90);
            goodTankR= ImageUtil.rotateImage(goodTankU,90);
            goodTankD= ImageUtil.rotateImage(goodTankU,180);

            badTankU=ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            badTankL=ImageUtil.rotateImage(badTankU,-90);
            badTankR=ImageUtil.rotateImage(badTankU,90);
            badTankD=ImageUtil.rotateImage(badTankU,180);

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
