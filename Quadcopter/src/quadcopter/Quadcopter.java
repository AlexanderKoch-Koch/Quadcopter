package quadcopter;

import com.pi4j.io.i2c.I2CDevice;


public class Quadcopter {

    public static void main(String[] args) throws Exception{
        System.out.println("Quadcopter Java App Start");
        NodeJsConnection.setup();
        //GPIO.setup();
        //SystemInfoProvider systemInfoProvider = new SystemInfoProvider();
        //Lighting lighting = new Lighting();
    }
    
}
