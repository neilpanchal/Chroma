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

import java.util.Arrays;

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
        return String.format("%S", "#" + Integer.toHexString(chroma.getColor()).substring(2, 8));
    }

    public String toString() {
        return hexString();
    }
}
