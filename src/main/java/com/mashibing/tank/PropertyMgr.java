package com.mashibing.tank;

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {

    //private static final PropertyMgr INSTANCE=new PropertyMgr();

    static Properties properties=new Properties();

    /**
    private PropertyMgr(){

    }

    public PropertyMgr getInstance(){
        return INSTANCE;
    }
     */

    static {
        try {
            properties.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object get(String key){
        if(properties == null){
            return null;
        }else{
            return properties.get(key);
        }
    }

    public static int getInt(String key){
        if(properties == null){
            return 0;
        }else{
            return Integer.parseInt((String)properties.get(key));
        }
    }

}
