/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quadcopter;

/**
 *
 * @author Alexander
 */
public class BNO055Bytes {
    
    //message start bytes
    public static byte START_BYTE = (byte) 0xAA;
    public static byte WRITE_BYTE = (byte) 0x00;
    public static byte READ_BYTE = (byte) 0x01;
    
    //response bytes
    public static byte respnseByte = (byte) 0xEE;
    public static byte WRITE_SUCCESS = (byte) 0x01;
    public static byte WRITE_FAIL = (byte) 0x03;
    public static byte REGMAP_INVALID_ADDRESS = (byte) 0x04;
    public static byte WRITREGMAP_WRITE_DISABLEDE_SUCCESS = (byte) 0x05;
    public static byte WRONG_START_BYTE = (byte) 0x06;
    public static byte BUS_OVER_RUN_ERROR = (byte) 0x07;
    public static byte MAX_LENGTH_ERROR = (byte) 0x08;
    public static byte MIN_LENGTH_ERROR = (byte) 0x09;
    public static byte RECEIVE_CHARACTER_TIMEOUT = (byte) 0x0A;
    public static byte READ_FAIL = (byte) 0x02;
    public static byte REGMAP_WRITE_DISABLED = (byte) 0x05;
    
    //register addresses
    public static byte CHIP_ID = (byte) 0x0;
    public static byte GYR_ID = (byte) 0x3;
    public static byte MAG_ID = (byte) 0x2;
    public static byte PAGE_ID = (byte) 0x7;
    public static byte SYS_STATUS = (byte) 0x39;
    public static byte TEMPERATURE = (byte) 0x34;
    public static byte CALIBRATION_STATUS = (byte) 0x35;
    public static byte OPERATION_MODE = (byte) 0x3D;
    public static byte POWER_MODE = (byte) 0x3E;
    
    public static byte GYR_DATA_Z_LSB  = (byte) 0x18;
    public static byte GYR_DATA_Z_MSB  = (byte) 0x19;
    public static byte EUL_Heading_LSB = (byte) 0x1A;
    public static byte EUL_Heading_MSB = (byte) 0x1B;   
    public static byte EUL_Roll_LSB = (byte) 0x1C;   
    public static byte EUL_Roll_MSB = (byte) 0x1D;   
    public static byte EUL_Pitch_LSB = (byte) 0x1E;   
    public static byte EUL_Pitch_MSB  = (byte) 0x1F;
    public static byte SYS_TRIGGER = (byte) 0x3F;
    //Offsets
    public static byte ACC_OFFSET_X_LSB = (byte) 0x55;
    public static byte ACC_OFFSET_X_MSB = (byte) 0x56;
    public static byte ACC_OFFSET_Y_LSB = (byte) 0x57;
    public static byte ACC_OFFSET_Y_MSB = (byte) 0x58;
    public static byte ACC_OFFSET_Z_LSB = (byte) 0x59;
    public static byte ACC_OFFSET_Z_MSB = (byte) 0x5A;
    public static byte MAG_OFFSET_X_LSB = (byte) 0x5B;
    public static byte MAG_OFFSET_X_MSB = (byte) 0x5C;
    public static byte MAG_OFFSET_Y_LSB = (byte) 0x5D;
    public static byte MAG_OFFSET_Y_MSB = (byte) 0x5E;
    public static byte MAG_OFFSET_Z_LSB = (byte) 0x5F;
    public static byte MAG_OFFSET_Z_MSB = (byte) 0x60;
    public static byte GYR_OFFSET_X_LSB = (byte) 0x61;
    public static byte GYR_OFFSET_X_MSB = (byte) 0x62;
    public static byte GYR_OFFSET_Y_LSB = (byte) 0x63;
    public static byte GYR_OFFSET_Y_MSB = (byte) 0x64;
    public static byte GYR_OFFSET_Z_LSB = (byte) 0x65;
    public static byte GYR_OFFSET_Z_MSB  = (byte) 0x66;
    public static byte ACC_RADIUS_LSB = (byte) 0x67;
    public static byte ACC_RADIUS_MSB = (byte) 0x68;
    public static byte MAG_RADIUS_LSB = (byte) 0x69;
    public static byte MAG_RADIUS_MSB = (byte) 0x6A;
}
