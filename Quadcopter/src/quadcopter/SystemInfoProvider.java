/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quadcopter;

import com.eclipsesource.json.JsonObject;
import com.pi4j.platform.PlatformManager;
import com.pi4j.system.NetworkInfo;
import com.pi4j.system.SystemInfo;
import java.util.Timer;
import java.util.TimerTask;

public class SystemInfoProvider {
    
    public SystemInfoProvider(){
        new Timer().schedule(new TimerTask() {
            public void run()  {
                try{
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.add("systemInfo", SystemInfo.getCpuTemperature());
                    NodeJsConnection.sendJson(jsonObject);
                }
                catch(Exception ex){
                }
            }
        }, 500, 1000);
    
    }
}
