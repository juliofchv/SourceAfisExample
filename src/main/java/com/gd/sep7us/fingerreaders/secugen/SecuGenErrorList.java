/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gd.sep7us.fingerreaders.secugen;

/**
 *
 * @author gigaDatta
 */
public abstract class SecuGenErrorList {
    
    private final static String ERR0 = "No error";
    private final static String ERR1 = "JSGFPLib object creation failed";
    private final static String ERR2 = "Function call failed";
    private final static String ERR3 = "Invalid parameter used";
    private final static String ERR4 = "Not used function";
    private final static String ERR5 = "DLL loading failed";
    private final static String ERR6 = "Device driver loading failed";
    private final static String ERR7 = "Algorithm DLL loading failed";
    
    private final static String ERR51 = "Cannot find driver sys file";
    private final static String ERR52 = "Chip initialization failed";
    private final static String ERR53 = "Image data lost";
    private final static String ERR54 = "GetImageEx() timeout";
    private final static String ERR55 = "Device not found";
    private final static String ERR56 = "Driver file load failed";
    private final static String ERR57 = "Wrong image";
    private final static String ERR58 = "Lack of USB bandwidth";
    private final static String ERR59 = "Device is already opened";
    private final static String ERR60 = "Serial number does not exist";
    private final static String ERR61 = "Unsupported device";
    
    private final static String ERR101 = "Inadequate number of minutiae";
    private final static String ERR102 = "Wrong template type";
    private final static String ERR103 = "Error in decoding template 1";
    private final static String ERR104 = "Error in decoding template 2";
    private final static String ERR105 = "Extraction failed";
    private final static String ERR106 = "Matching failed";
    
    public static String getErrMessage(long err) {
        if (err == 0)
            return ERR0;
        else if (err == 1)
            return ERR1;
        else if (err == 2)
            return ERR2;
        else if (err == 3)
            return ERR3;
        else if (err == 4)
            return ERR4;
        else if (err == 5)
            return ERR5;
        else if (err == 6)
            return ERR6;
        else if (err == 7)
            return ERR7;
        
        else if (err == 51)
            return ERR51;
        else if (err == 52)
            return ERR52;
        else if (err == 53)
            return ERR53;
        else if (err == 54)
            return ERR54;
        else if (err == 55)
            return ERR55;
        else if (err == 56)
            return ERR56;
        else if (err == 57)
            return ERR57;
        else if (err == 58)
            return ERR58;
        else if (err == 59)
            return ERR59;
        else if (err == 60)
            return ERR60;
        else if (err == 61)
            return ERR61;
        
        else if (err == 101)
            return ERR101;
        else if (err == 102)
            return ERR102;
        else if (err == 103)
            return ERR103;
        else if (err == 104)
            return ERR104;
        else if (err == 105)
            return ERR105;
        else if (err == 106)
            return ERR106;
        
        else
            return "Unknow error";
    }
    
    
}
