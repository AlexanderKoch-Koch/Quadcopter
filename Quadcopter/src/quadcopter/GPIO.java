/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quadcopter;

import com.eclipsesource.json.JsonObject;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

/**
 *
 * @author Alexander
 */
public final class GPIO implements WebCommandInterface{
    
    public final static GpioController GPIO_CONTROLLER = GpioFactory.getInstance();
    public final static GpioPinDigitalOutput LED1 = GPIO_CONTROLLER.provisionDigitalOutputPin(RaspiPin.GPIO_01, "LED1", PinState.LOW);
    
    public static void setup(){
        GPIO gpio = new GPIO();
    }
    
    public GPIO(){
        NodeJsConnection.thread.addEventListener(this);
    }

    @Override
    public void commandReceived(JsonObject command) {
        if(command.get("GPIO") != null){
            controlGPIO(command.get("GPIO").asObject());
        }
    }
    
    private void controlGPIO(JsonObject jsonObject){
        if(jsonObject.get("1") != null){
            boolean GPIO_1_value = jsonObject.get("1").asBoolean();
            System.out.println("setting LED1 to " + GPIO_1_value);
            if(GPIO_1_value == true) GPIO.LED1.setState(PinState.HIGH);
            else GPIO.LED1.setState(PinState.LOW);
        }
    }
}
