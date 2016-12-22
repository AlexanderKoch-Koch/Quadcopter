package quadcopter;

import com.pi4j.io.i2c.I2CDevice;


public class BNO055 {
    private I2CDevice bno055;
    
    public BNO055(){
        BNO055Thread bno055Thread = new BNO055Thread();
        bno055Thread.start();
    }
}
