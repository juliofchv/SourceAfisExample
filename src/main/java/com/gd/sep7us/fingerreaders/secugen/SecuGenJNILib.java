package com.gd.sep7us.fingerreaders.secugen;

import SecuGen.FDxSDKPro.jni.*;
import com.gd.sep7us.fingerreaders.entity.FingerData;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author gigaDatta
 */
public class SecuGenJNILib extends FingerData {
    
    public static enum TemplateFormat { ANSI378, ISO19794, SG400 }
    
    public SecuGenJNILib (String templateFormat) throws Exception {
       // SecuGenFingerCapture(templateFormat);
    }
    
    private synchronized void SecuGenFingerCapture (SecuGenJNILib.TemplateFormat templateFormat) throws Exception {
        long err; 
        short template;
        switch(templateFormat) {
            case ANSI378: template = SGFDxTemplateFormat.TEMPLATE_FORMAT_ANSI378;
            case ISO19794: template = SGFDxTemplateFormat.TEMPLATE_FORMAT_ISO19794;
            default: template = SGFDxTemplateFormat.TEMPLATE_FORMAT_SG400;
        }    
        JSGFPLib sgfplib = new JSGFPLib();
        SGDeviceInfoParam deviceInfo = new SGDeviceInfoParam();
        byte[] imageBuffer1;
        int[] quality = new int[1];
        int[] maxSize = new int[1];
        int[] size = new int[1];
        byte[] MinutiaeBuffer1;
        SGFingerInfo fingerInfo = new SGFingerInfo();
        if ((sgfplib != null) && (sgfplib.jniLoadStatus != SGFDxErrorCode.SGFDX_ERROR_JNI_DLLLOAD_FAILED)) {
            err = sgfplib.Init(SGFDxDeviceName.SG_DEV_AUTO);
        if (err != 0) {
            throw new Exception("Secugen error 0x"+err+": "+SecuGenErrorList.getErrMessage(err));
        } else {
            err = sgfplib.OpenDevice(SGPPPortAddr.USB_AUTO_DETECT);
            err = sgfplib.GetLastError();        
            err = sgfplib.GetDeviceInfo(deviceInfo);        
            fingerInfo.FingerNumber = SGFingerPosition.SG_FINGPOS_UK;
            fingerInfo.ImageQuality = quality[0];
            err = sgfplib.SetBrightness(0);       
            fingerInfo.ImpressionType = SGImpressionType.SG_IMPTYPE_LP;
            fingerInfo.ViewNumber = 1;                  
            err = sgfplib.SetLedOn(true);         
            imageBuffer1 = new byte[deviceInfo.imageHeight*deviceInfo.imageWidth];
            BufferedImage originalImage = new BufferedImage(deviceInfo.imageWidth, deviceInfo.imageHeight, BufferedImage.TYPE_BYTE_GRAY);
            imageBuffer1 = ((java.awt.image.DataBufferByte) originalImage.getRaster().getDataBuffer()).getData();
            err = sgfplib.GetImage(imageBuffer1);
            if (err == SGFDxErrorCode.SGFDX_ERROR_NONE) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write( originalImage, "jpg", baos );
                baos.flush();
                setFingerprint(baos.toByteArray());
                baos.close();
                int dvimw = deviceInfo.imageWidth;
                int dvimh = deviceInfo.imageHeight;
                err = sgfplib.GetImageQuality(dvimw, dvimh, imageBuffer1, quality);            
                err = sgfplib.SetTemplateFormat(template);
                err = sgfplib.GetMaxTemplateSize(maxSize);
                MinutiaeBuffer1 = new byte[maxSize[0]];
                setRAWData(imageBuffer1);
                err = sgfplib.CreateTemplate(fingerInfo, imageBuffer1, MinutiaeBuffer1);
                err = sgfplib.GetTemplateSize(MinutiaeBuffer1, size);
                byte[] ansi = new byte[size[0]];
                System.arraycopy(MinutiaeBuffer1,0,ansi,0,size[0]);
                if (err == SGFDxErrorCode.SGFDX_ERROR_NONE)
                    setTemplate(ansi);
            } else {
                err = sgfplib.SetLedOn(false);
                err = sgfplib.CloseDevice();
                sgfplib.Close();
                throw new Exception("¡NO HUBO CAPTURA DE HUELLA DACTILAR, ES NECESARIO COLOCAR EL DEDO CORRECTAMENTE!\nSecugen error 0x"+err+" : "+SecuGenErrorList.getErrMessage(err));
                }
            err = sgfplib.SetLedOn(false);
            err = sgfplib.CloseDevice();
            sgfplib.Close();
            }
        } else {
            throw new Exception("Secugen error: JNI DLL Load failed");
        }       
    }
    
}