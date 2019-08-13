/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gd.sep7us.fingerreaders.entity;

/**
 *
 * @author Julio
 */
public abstract class FingerData {
    
    private byte[] RAWData;
    private byte[] Template;
    private byte[] Fingerprint;

    public byte[] getRAWData() {
        return RAWData;
    }

    public void setRAWData(byte[] RAWData) {
        this.RAWData = RAWData;
    }

    public byte[] getTemplate() {
        return Template;
    }

    public void setTemplate(byte[] Template) {
        this.Template = Template;
    }

    public byte[] getFingerprint() {
        return Fingerprint;
    }

    public void setFingerprint(byte[] Fingerprint) {
        this.Fingerprint = Fingerprint;
    }
    
}