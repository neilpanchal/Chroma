/////////////////////////////////////////////////////////////////////////////////////////
/*
Author: Neil Panchal
Website: http://neil.engineer/

Copyright 2015 Neil Panchal. All Rights Reserved.

Description: Chroma is a color conversion class for Processing. It implements color conversions in percetually linear color spaces such as CIE-Lab and CIE-LCH.

Usage & API: Please see the README.md file

References:  Color conversion methods are borrowed from various places as detailed below.

*   Gregory Aisch: https://github.com/gka/chroma.js
*   http://developer.classpath.org/doc/java/awt/Color-source.html
*   http://stackoverflow.com/a/7898685
*   http://www.cs.rit.edu/~ncs/color/t_convert.html

*/
/////////////////////////////////////////////////////////////////////////////////////////

package com.chroma;

public class Chroma {

    private ChromaColor chroma;

    // CONSTRUCTORS
    /////////////////////////////////////////////////////////////////////////////////////

    public Chroma(ColorSpace space, double arg1, double arg2, double arg3, double alpha) {

        switch (space) {
        case RGB:
            chroma = new ChromaRGB(arg1, arg2, arg3, alpha); break;
        case HSL:
            chroma = new ChromaHSL(arg1, arg2, arg3, alpha); break;
        case HSV:
            chroma = new ChromaHSV(arg1, arg2, arg3, alpha); break;
        case LAB:
            chroma = new ChromaLAB(arg1, arg2, arg3, alpha); break;
        case LCH:
            chroma = new ChromaLCH(arg1, arg2, arg3, alpha); break;
        }

    }

    public Chroma(int red, int green, int blue) {
        this(ColorSpace.RGB, red, green, blue);
    }

    public Chroma(int red, int green, int blue, int alpha) {
        this(ColorSpace.RGB, red, green, blue, alpha);
    }

    public Chroma(ColorSpace space, double arg1, double arg2, double arg3) {
        this(space, arg1, arg2, arg3, 255);
    }

    // GET/SET METHODS
    /////////////////////////////////////////////////////////////////////////////////////
    public int get() {
        return chroma.getColor() ;
    }
    public boolean clipped() {
        return chroma.clipped();
    }
    public double getAlpha() {
        return chroma.getAlpha();
    }
    public void setAlpha(double alpha) {
        chroma.setAlpha(alpha);
    }

    // GET-COMPONENT METHODS
    /////////////////////////////////////////////////////////////////////////////////////

    public double[] getRGB() {
        return chroma.getChromaRGB().getRGBComp();
    }
    public double[] getHSL() {
        return chroma.getChromaHSL().getHSLComp();
    }
    public double[] getHSV() {
        return chroma.getChromaHSV().getHSVComp();
    }
    public double[] getLAB() {
        return chroma.getChromaLAB().getLABComp();
    }
    public double[] getLCH() {
        return chroma.getChromaLCH().getLCHComp();
    }

    // STRING REPRESENTATION METHODS
    /////////////////////////////////////////////////////////////////////////////////////
    public String hexString() {
        return String.format("%S", "#" + Integer.toHexString(chroma.getColor() & 0x00FFFFFF));
    }

    public String toString() {
        return hexString();
    }
}
