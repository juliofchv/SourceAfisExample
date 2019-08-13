/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SourceAfisTest;

import com.machinezoo.sourceafis.*;


/**
 *
 * @author Julio
 */
public class SourceAfis {

    public static enum TemplateFormat { ANSI378v2004, AnsiIncits378v2009, AnsiIncits378v2009AM1 } 
    
    /**
     * Búsqueda iterable
     * @param probe
     * @param candidates
     * @param threshold
     * @return 
     */
    UserDetails find(FingerprintTemplate probe, Iterable<UserDetails> candidates, double threshold) {
        FingerprintMatcher matcher = new FingerprintMatcher().index(probe);
        UserDetails match = null;
        double high = 0;
        for (UserDetails candidate : candidates) {
            double score = matcher.match(candidate.template);
            if (score > high) {
                high = score;
                match = candidate;
            }
        }
        return high >= threshold ? match : null;
    }
    
    /**
     * Verificación de 2 templates biométricos
     * @param templateProbe
     * @param templateCandidate
     * @param threshold
     * @return
     */
    public static boolean FingerTemplateMatch(byte[] templateProbe, byte[] templateCandidate, double threshold) {
        FingerprintTemplate probe = FingerprintCompatibility.convert(templateProbe);
        FingerprintTemplate candidate = FingerprintCompatibility.convert(templateCandidate);
        double score = new FingerprintMatcher().index(probe).match(candidate);
        return score >= threshold;
    }
    
    /**
     * Verificación de 2 imágenes de huella dactilar biométricas
     * @param probeImage
     * @param candidateImage
     * @param dpiProbeIMG
     * @param dpiCandidateIMG
     * @param threshold
     * @return 
     */
    public static boolean FingerImageMatch (byte[] probeImage, byte[] candidateImage, int dpiProbeIMG, int dpiCandidateIMG, double threshold) {
        FingerprintTemplate probe = new FingerprintTemplate().dpi(dpiProbeIMG).create(probeImage);
        FingerprintTemplate candidate = new FingerprintTemplate().dpi(dpiCandidateIMG).create(candidateImage);
        double score = new FingerprintMatcher().index(probe).match(candidate);
        return score >= threshold;
    }
    
    /**
     * Extracción de minucias y generación de plantilla biometrica a través de la imágen de una huella dactilar
     * @param probeImage
     * @param dpiProbeIMG
     * @param template
     * @return
     * @throws Exception 
     */
    public static byte[] TemplateExtract(byte[] probeImage, int dpiProbeIMG, TemplateFormat template) throws Exception {
        FingerprintTemplate probe = new FingerprintTemplate().dpi(dpiProbeIMG).create(probeImage);
        switch (template) {
            case ANSI378v2004: return FingerprintCompatibility.toAnsiIncits378v2004(probe);
            case AnsiIncits378v2009: return FingerprintCompatibility.toAnsiIncits378v2009(probe);
            case AnsiIncits378v2009AM1: return FingerprintCompatibility.toAnsiIncits378v2009AM1(probe);
            default: throw new Exception("no format was specified");
        }
    }
    
    /**
     * 
     * @param templateProbe
     * @param template
     * @return
     * @throws java.lang.Exception
     */
    public static byte[] TemplateConvert(byte[] templateProbe, TemplateFormat template) throws Exception {
        FingerprintTemplate probe = FingerprintCompatibility.convert(templateProbe);
        switch (template) {
            case ANSI378v2004: return FingerprintCompatibility.toAnsiIncits378v2004(probe);
            case AnsiIncits378v2009: return FingerprintCompatibility.toAnsiIncits378v2009(probe);
            case AnsiIncits378v2009AM1: return FingerprintCompatibility.toAnsiIncits378v2009AM1(probe);
            default: throw new Exception("no format was specified");
        }
    }
       
}