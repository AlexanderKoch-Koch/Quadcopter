package quadcopter;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;

public class BNO055Thread extends Thread{
    private I2CDevice bno055;
    
    public BNO055Thread(){
        try{
            I2CBus bus = I2CFactory.getInstance(I2CBus.BUS_1);
            
            bno055 = bus.getDevice(0x28);
            bno055.write((int) BNO055Bytes.PAGE_ID, (byte) 0x00);       //choose page0
            System.out.println(bno055.read(BNO055Bytes.PAGE_ID));
            bno055.write((int) BNO055Bytes.OPERATION_MODE, (byte) 0x0C);    //choose ndof operation mode
            Thread.sleep(30);
            
            
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    
    public void run() {
        byte[] data = new byte[6];
        int heading, roll, pitch = 0;
        try{
            while(true){
                bno055.read(BNO055Bytes.EUL_Heading_LSB, data, 0, 6);
                        
                heading = Conversion.bytesToInt(data[0], data[1]);
                roll = Conversion.bytesToInt(data[2], data[3]);
                pitch = Conversion.bytesToInt(data[4], data[5]);
                
                if(heading >= 0 && heading <= 5780){
                    if(roll >= -1440 && roll <= 1440){
                        if(pitch >= -1440 && pitch <= 1440){
                            System.out.println(heading + " " + pitch + " " + roll);
                        }
                    }
                }
                
                Thread.sleep(1);
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
}
