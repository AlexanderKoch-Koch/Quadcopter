/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quadcopter;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;

/**
 *
 * @author Alexander
 */
public class Quadcopter {

    private static I2CDevice bno055;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        NodeJsConnection.setup();
        Lighting lighting = new Lighting();
        
        
        System.out.println("hello world");
        int num = 6;
        
    }
    
}
