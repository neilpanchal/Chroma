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
            this.chroma = new ChromaRGB(arg1, arg2, arg3, alpha); break;
        case HSL:
            this.chroma = new ChromaHSL(arg1, arg2, arg3, alpha); break;
        case HSV:
        	this.chroma = new ChromaHSV(arg1, arg2, arg3, alpha); break;
        case LAB:
        	this.chroma = new ChromaLAB(arg1, arg2, arg3, alpha); break;
        case LCH:
        	this.chroma = new ChromaLCH(arg1, arg2, arg3, alpha); break;
		default:
			this.chroma = new ChromaRGB(arg1, arg2, arg3, alpha); break;
        }

    }

    public Chroma() {
    	this(ColorSpace.RGB, 255, 255, 255, 255);
    }
    // Chroma(255, 0, 0)
    public Chroma(int red, int green, int blue) {
        this(ColorSpace.RGB, red, green, blue, 255);
    }

    // Chroma(255, 0, 0, 255)
    public Chroma(int red, int green, int blue, int alpha) {
        this(ColorSpace.RGB, red, green, blue, alpha);
    }

    // Chroma (ColorSpace.RGB, 255, 0, 0, 255)
    public Chroma(ColorSpace space, double arg1, double arg2, double arg3) {
        this(space, arg1, arg2, arg3, 255);
    }

    // Chroma (120);
    public Chroma(int gray) {
    	this(ColorSpace.RGB, gray, gray, gray, 255);
    }

    public Chroma(String stringInput){

    	int r_;
    	int g_;
    	int b_;

    	if(stringInput.startsWith("#")) {

    		if (ChromaUtil.validateHex(stringInput)) {

    			if (stringInput.length() == 4) {

    				String[] hex3 = stringInput.split("");

    		        r_ = Integer.parseInt(hex3[1]+hex3[1], 16);
    		        g_ = Integer.parseInt(hex3[2]+hex3[2], 16);
    		        b_ = Integer.parseInt(hex3[3]+hex3[3], 16);


    				this.chroma = new ChromaRGB(r_, g_, b_, 255);

    			} else if (stringInput.length() == 7) {

    				int rgbInt = Integer.parseInt(stringInput.substring(1, 7),16);

    		        r_ = rgbInt >> 16;
    		        g_ = rgbInt >> 8 & 0xFF;
    		        b_ = rgbInt & 0xFF;

                    this.chroma = new ChromaRGB(r_, g_, b_, 255);
                }

    		} else {

    			throw new IllegalArgumentException("Invalid Hex Color. Please use #F00 or #FF0000 format.");
    		}

    	} else {

    		String match = ChromaUtil.colorName(stringInput);

    		if (match != null) {

				int rgbInt = Integer.parseInt(match.substring(1, 7),16);

		        r_ = rgbInt >> 16;
		        g_ = rgbInt >> 8 & 0xFF;
		        b_ = rgbInt & 0xFF;


                this.chroma = new ChromaRGB(r_, g_, b_, 255);

    		} else {

        		throw new IllegalArgumentException("Invalid Color String. Please refer to the API valid input formats.");

    		}
    	}

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
    public double getLuminance(){
    	return chroma.getLuminance();
    }

    // GET-COMPONENT METHODS
    /////////////////////////////////////////////////////////////////////////////////////
    public double getRGB_R() {
        return chroma.getRGB_R();
    }
    public double getRGB_G() {
        return chroma.getRGB_G();
    }
    public double getRGB_B() {
        return chroma.getRGB_B();
    }

    public double getHSL_H() {
        return chroma.getHSL_H();
    }
    public double getHSL_S() {
        return chroma.getHSL_S();
    }
    public double getHSL_L() {
        return chroma.getHSL_L();
    }

    public double getHSV_H() {
        return chroma.getHSV_H();
    }
    public double getHSV_S() {
        return chroma.getHSV_S();
    }
    public double getHSV_V() {
        return chroma.getHSV_V();
    }

    public double getLAB_L() {
        return chroma.getLAB_L();
    }
    public double getLAB_A() {
        return chroma.getLAB_A();
    }
    public double getLAB_B() {
        return chroma.getLAB_B();
    }

    public double getLCH_L() {
        return chroma.getLCH_L();
    }
    public double getLCH_C() {
        return chroma.getLCH_C();
    }
    public double getLCH_H() {
        return chroma.getLCH_H();
    }


    public void setRGB_R(double rgb_R_) {
        this.chroma = chroma.getChromaRGB();
        chroma.setRGB_R(rgb_R_);
    }
    public void setRGB_G(double rgb_G_) {
        this.chroma = chroma.getChromaRGB();
        chroma.setRGB_G(rgb_G_);
    }

    public void setRGB_B(double rgb_B_) {
        this.chroma = chroma.getChromaRGB();
        chroma.setRGB_B(rgb_B_);
    }

    public void setHSL_H(double hsl_H_) {
        this.chroma = chroma.getChromaHSL();
        chroma.setHSL_H(hsl_H_);
    }
    public void setHSL_S(double hsl_S_) {
        this.chroma = chroma.getChromaHSL();
        chroma.setHSL_S(hsl_S_);
    }
    public void setHSL_L(double hsl_L_) {
        this.chroma = chroma.getChromaHSL();
        chroma.setHSL_L(hsl_L_);
    }

    public void setHSV_H(double hsv_H_) {
        this.chroma = chroma.getChromaHSV();
        chroma.setHSV_H(hsv_H_);
    }
    public void setHSV_S(double hsv_S_) {
        this.chroma = chroma.getChromaHSV();
        chroma.setHSV_S(hsv_S_);
    }
    public void setHSV_V(double hsv_V_) {
        this.chroma = chroma.getChromaHSV();
        chroma.setHSV_V(hsv_V_);
    }

    public void setLAB_L(double lab_L_) {
        this.chroma = chroma.getChromaLAB();
        chroma.setLAB_L(lab_L_);
    }
    public void setLAB_A(double lab_A_) {
        this.chroma = chroma.getChromaLAB();
        chroma.setLAB_A(lab_A_);
    }
    public void setLAB_B(double lab_B_) {
        this.chroma = chroma.getChromaLAB();
        chroma.setLAB_B(lab_B_);
    }

    public void setLCH_L(double hsv_L_) {
        this.chroma = chroma.getChromaLCH();
        chroma.setLCH_L(hsv_L_);
    }
    public void setLCH_C(double hsv_C_) {
        this.chroma = chroma.getChromaLCH();
        chroma.setLCH_C(hsv_C_);
    }
    public void setLCH_H(double hsv_H_) {
        this.chroma = chroma.getChromaLCH();
        chroma.setLCH_H(hsv_H_);
    }

    // GET-COMPONENT ARRAY METHODS
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
        return String.format("%S", "#" + Integer.toHexString(chroma.getColor()).substring(2, 8));
    }

    @Override
	public String toString() {
        return hexString();
    }
}
