import com.mashibing.tank.Dir;
import com.mashibing.tank.Tank;
import com.mashibing.tank.TankFrame;

public class Main {

    public static void main(String[] args) {
        TankFrame tf=new TankFrame();

        int initTankCount=5;
        for(int i=0;i<initTankCount;i++){
            tf.tanks.add(new Tank(50+i*80,200, Dir.DOWN,tf));
        }

        while(true){
            try {
                Thread.sleep(50);
                tf.repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
