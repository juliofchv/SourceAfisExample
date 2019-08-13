/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SourceAfisTest;

import java.io.File;

/**
 *
 * @author Julio
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        byte[] img = com.gd.utils.ByteFilesHandler.readFile(new File("D:\\huella.jpg"));
        byte[] img2 = com.gd.utils.ByteFilesHandler.readFile(new File("D:\\ANSI.jpg"));
        System.out.println(SourceAfis.FingerImageMatch(img2, img, 500, 500, 50));
        
    }
    
}