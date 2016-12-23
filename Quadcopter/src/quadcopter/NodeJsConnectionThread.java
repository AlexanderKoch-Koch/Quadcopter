
package quadcopter;

import com.eclipsesource.json.JsonObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Vector;
import netscape.javascript.JSObject;

public class NodeJsConnectionThread extends Thread{
    
    volatile boolean run = true;
    BufferedReader reader;
    protected static Vector _listeners;//listening objects
    InputStream inputStream;
     
    public NodeJsConnectionThread(InputStream input){
        this.inputStream = input;
        reader = new BufferedReader(new InputStreamReader(input));
    }
    
    public void run(){
        char[] receivedBytes = new char[1024];
        int currentIndex = 0;
        
        while(run){
            try{
                if(currentIndex >= receivedBytes.length){
                    Log.e("Message from NodeJs Server longer than " + receivedBytes.length + " bytes" + 
                            "\nno \\n found");
                    currentIndex = 0;
                }
                receivedBytes[currentIndex] =  (char) inputStream.read();          
                if(receivedBytes[currentIndex] == '\n'){
                    String msg = new String(receivedBytes, 0, currentIndex);
                    
                    try{
                        JsonObject jsonObject = JsonObject.readFrom(msg);
                        fireCommandEvent(jsonObject);
                    }catch(Exception ex){
                        System.out.println("non json command: " + msg);
                    }
                    
                    
                    //overwrite array from beginning
                    currentIndex = 0;
                }else{
                    currentIndex++;
                }
            }catch(Exception ex){
                    System.out.println("NodeJsConnectionThread: " + ex);
            }      
        }
    }
    
    
     
    // Method for listener classes to register themselves
    public  void addEventListener(WebCommandInterface listener)
    {
        //System.out.println("adding event listener");
        if (_listeners == null)
            _listeners = new Vector();
             
        _listeners.addElement(listener);
    }
     
    // "fires" the event
    protected  void fireCommandEvent(JsonObject command)
    {
        //System.out.println("PHPConnection: fire:" + command);
        //System.out.println("listening "+ _listeners.toString()+ _listeners.isEmpty());
        if (_listeners != null && !_listeners.isEmpty())
        {
            Enumeration e = _listeners.elements();
            while (e.hasMoreElements())
            {
                WebCommandInterface interfaceVar = (WebCommandInterface) e.nextElement();
                interfaceVar.commandReceived(command);
            }
        }else{
            //System.out.println("no listeners");
        }
    }
}
