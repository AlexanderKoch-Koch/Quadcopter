/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quadcopter;

import java.util.BitSet;

/**
 *
 * @author Alexander
 */
public class Conversion {
    
    public static String byteToBits(byte byteVar){
        return String.format("%8s", Integer.toBinaryString(byteVar & 0xFF)).replace(' ', '0');
    }
    
    public static String byteToHex(byte byteVar) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%02X ", byteVar));
        String string = sb.toString();
        return string.substring(0, string.length() - 1);
    }
    
    public static String hexToBin(String hex){
        String bin = "";
        String binFragment = "";
        int iHex;
        hex = hex.trim();
        hex = hex.replaceFirst("0x", "");
        
        for(int i = 0; i < hex.length(); i++){
            iHex = Integer.parseInt(""+hex.charAt(i),16);
            binFragment = Integer.toBinaryString(iHex);
            
            while(binFragment.length() < 4){
                binFragment = "0" + binFragment;
            }
            bin += binFragment;
        }
        return bin;
    }
    
    public static int bytesToInt(byte LSB, byte MSB){
        int intLSB = LSB & 0xFF;//convert LSB byte to int without sign
        int intMSB = MSB << 8;
        int result = intLSB + intMSB;
        return result;
    }
}
