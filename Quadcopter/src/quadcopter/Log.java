/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quadcopter;

import com.eclipsesource.json.JsonObject;

/**
 *
 * @author Alexander
 */
public class Log {
    
    public static void e(String errorMessage){
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("error", errorMessage);
        NodeJsConnection.sendMessage(jsonObject);
    }
}
