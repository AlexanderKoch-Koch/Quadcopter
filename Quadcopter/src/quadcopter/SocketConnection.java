package quadcopter;

import java.net.Socket;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

public final class SocketConnection extends Thread{

    final static String HOST = "localhost";
    final static int PORT = 8550;
    static OutputStream outputStream;
    static InputStream inputStream;
    static Socket socket;
    
    public static void setup(){
        try {
            SocketAddress address = new InetSocketAddress(HOST, PORT);
            socket = new Socket();
            socket.setReuseAddress(true);
            socket.connect(address);
            outputStream = socket.getOutputStream();
            inputStream = socket.getInputStream();
            outputStream.write(121);
            
            
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    static String readAll(BufferedReader bufferedReader){
        String line;
        String received = "";
        try{
            while ((line = bufferedReader.readLine()) != null) {
                received += line;
            }
        }catch(Exception ex){
            System.out.println("error reading socket input " + ex);
        }
        return received;
    }
    
    @Override
    public void run(){
         
        while(true){
            try{
            if(inputStream.available() > 0){
                System.out.println("received: " + inputStream.read());
            }
            }catch(Exception ex){
                System.out.println(ex);
            }
        }
    }
}
