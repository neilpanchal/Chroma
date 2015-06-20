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
	// ///////////////////////////////////////////////////////////////////////////////////

	public Chroma(ColorSpace space, double arg1, double arg2, double arg3,
			double alpha) {

		switch (space) {
		case RGB:
			this.chroma = new ChromaRGB(arg1, arg2, arg3, alpha);
			break;
		case HSL:
			this.chroma = new ChromaHSL(arg1, arg2, arg3, alpha);
			break;
		case HSV:
			this.chroma = new ChromaHSV(arg1, arg2, arg3, alpha);
			break;
		case LAB:
			this.chroma = new ChromaLAB(arg1, arg2, arg3, alpha);
			break;
		case LCH:
			this.chroma = new ChromaLCH(arg1, arg2, arg3, alpha);
			break;
		default:
			this.chroma = new ChromaRGB(arg1, arg2, arg3, alpha);
			break;
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

	public Chroma(String stringInput) {

		int r_;
		int g_;
		int b_;

		if (stringInput.startsWith("#")) {

			if (ChromaUtil.validateHex(stringInput)) {

				if (stringInput.length() == 4) {

					String[] hex3 = stringInput.split("");

					r_ = Integer.parseInt(hex3[1] + hex3[1], 16);
					g_ = Integer.parseInt(hex3[2] + hex3[2], 16);
					b_ = Integer.parseInt(hex3[3] + hex3[3], 16);

					this.chroma = new ChromaRGB(r_, g_, b_, 255);

				} else if (stringInput.length() == 7) {

					int rgbInt = Integer.parseInt(stringInput.substring(1, 7),
							16);

					r_ = rgbInt >> 16;
					g_ = rgbInt >> 8 & 0xFF;
					b_ = rgbInt & 0xFF;

					this.chroma = new ChromaRGB(r_, g_, b_, 255);
				}

			} else {

				throw new IllegalArgumentException(
						"Invalid Hex Color. Please use #F00 or #FF0000 format.");
			}

		} else {

			String match = ChromaUtil.colorName(stringInput);

			if (match != null) {

				int rgbInt = Integer.parseInt(match.substring(1, 7), 16);

				r_ = rgbInt >> 16;
				g_ = rgbInt >> 8 & 0xFF;
				b_ = rgbInt & 0xFF;

				this.chroma = new ChromaRGB(r_, g_, b_, 255);

			} else {

				throw new IllegalArgumentException(
						"Invalid Color String. Please refer to the API valid input formats.");

			}
		}

	}

	// GET/SET METHODS
	// ///////////////////////////////////////////////////////////////////////////////////

	public boolean clipped() {
		return chroma.clipped();
	}

	public double getAlpha() {
		return chroma.getAlpha();
	}

	public void setAlpha(double alpha) {
		chroma.setAlpha(alpha);
	}

	public double getLuminance() {
		return chroma.getLuminance();
	}
	public Chroma tone() {
		return tone(100.0);
	}

	public Chroma saturate() {
		return saturate(100.0);
	}

	public Chroma lighten() {
		return lighten(100.0);
	}

	// Tone changes the saturation or chromacity of the color (0-100% Absolute)
	public Chroma tone(double amount) {
		if (amount < 0 || amount > 100) {
			System.out.println("Invalid Amount entered");
			return new Chroma(0);
		}

		double lum = chroma.getLCH_L();
		double chr = chroma.getLCH_C();
		double hue = chroma.getLCH_H();

		double maxChroma = getMaxChroma(lum, chr, hue, 20, 0.1);

		return new Chroma(ColorSpace.LCH, lum, maxChroma * amount / 100.0, hue);

	}

	// Tint changes the lightness of the color (0-100% Absolute)
	public Chroma tint(double amount) {

		Args.checkForRange(amount, 0, 100,
				"Invalid tint() amount. Please enter a number between 0-100.");

		double lum = chroma.getLCH_L();
		double chr = chroma.getLCH_C();
		double hue = chroma.getLCH_H();
		double maxLuma = getMaxLuma(lum, chr, hue, 20, 0.1);

		return new Chroma(ColorSpace.LCH, maxLuma * (amount / 100.0), chr, hue);
	}

	public Chroma saturate(double amount) {

		Args.checkForRange(amount, 0, 100,
				"Invalid saturate() amount. Please enter a number between 0-100.");

		double lum = chroma.getLCH_L();
		double chr = chroma.getLCH_C();
		double hue = chroma.getLCH_H();

		double maxChroma = getMaxChroma(lum, chr, hue, 20, 0.1);

		return new Chroma(ColorSpace.LCH, lum, chr + (maxChroma - chr)
				* (amount / 100.0), hue);
	}

	public Chroma saturateTo(double amount) {

		Args.checkForRange(amount, 0, 128,
				"Invalid saturateTo() amount. Please enter a number between 0-128.");

		double lum = chroma.getLCH_L();
		double chr = chroma.getLCH_C();
		double hue = chroma.getLCH_H();

		double maxChroma = getMaxChroma(lum, chr, hue, 20, 0.1);
		double toChr;

		// Check if the amount clips the color space

		if ((maxChroma - (chr + amount)) > 0) {
			// OK to add more Chroma
			toChr = amount;
		} else {
			// Clip at max Chroma
			toChr = maxChroma;
		}

		return new Chroma(ColorSpace.LCH, lum, toChr, hue);
	}

	public Chroma saturateBy(double amount) {

		Args.checkForRange(amount, 0, 128,
				"Invalid saturateBy() amount. Please enter a number between 0-128.");

		double lum = chroma.getLCH_L();
		double chr = chroma.getLCH_C();
		double hue = chroma.getLCH_H();

		double maxChroma = getMaxChroma(lum, chr, hue, 20, 0.1);
		double addChr;

		// Check if the amount clips the color space

		if ((maxChroma - (chr + amount)) > 0) {
			// OK to add more Chroma
			addChr = amount;
		} else {
			// Clip at max Chroma
			addChr = maxChroma;
		}

		return new Chroma(ColorSpace.LCH, lum, chr + addChr, hue);
	}

	public Chroma lighten(double amount) {

		Args.checkForRange(amount, 0, 100,
				"Invalid lighten() amount. Please enter a number between 0-100.");

		double lum = chroma.getLCH_L();
		double chr = chroma.getLCH_C();
		double hue = chroma.getLCH_H();

		double maxLuma = getMaxLuma(lum, chr, hue, 20, 0.1);

		return new Chroma(ColorSpace.LCH, lum + (maxLuma - lum)
				* (amount / 100.0), chr, hue);
	}

	private double getMaxLuma(double lum, double chr, double hue, int maxIter,
			double threshold) {

		boolean truth = true;
		int iter = 0;

		double min = lum;
		double max = 100;
		double mid = min + (max - min) / 2.0;

		Chroma test = new Chroma(ColorSpace.LCH, lum, chr, hue);
		double bottomHalf;
		double topHalf;

		while (truth) {

			bottomHalf = min + (mid - min) / 2.0;
			topHalf = mid + (max - mid) / 2.0;

			test.set(ColorSpace.LCH, Channel.L, mid);

			if (test.clipped()) {
				max = mid;
				mid = bottomHalf;

			} else {
				if ((max - min) < threshold) {
					return test.getLCH(Channel.L);

				}
				min = mid;
				mid = topHalf;

			}

			iter++;

			if (iter == maxIter) {
				truth = false;
				return test.getLCH(Channel.L);
			}

		}
		return test.getLCH(Channel.L);

	}

	private double getMaxChroma(double lum, double chr, double hue,
			int maxIter, double threshold) {

		boolean truth = true;
		int iter = 0;

		double min = chr;
		double max = 128;
		double mid = min + (max - min) / 2.0;

		Chroma test = new Chroma(ColorSpace.LCH, lum, chr, hue);
		double bottomHalf;
		double topHalf;

		while (truth) {

			bottomHalf = min + (mid - min) / 2.0;
			topHalf = mid + (max - mid) / 2.0;

			test.set(ColorSpace.LCH, Channel.C, mid);

			if (test.clipped()) {
				max = mid;
				mid = bottomHalf;

			} else {
				if ((max - min) < threshold) {
					return test.getLCH(Channel.C);

				}
				min = mid;
				mid = topHalf;

			}

			iter++;

			if (iter == maxIter) {
				truth = false;
				return test.getLCH(Channel.C);
			}

		}
		return test.getLCH(Channel.C);

	}

	// GET-COMPONENT METHODS
	// ///////////////////////////////////////////////////////////////////////////////////
	public int get() {
		return chroma.getColor(); // By default, get RGB color
	}

	public double[] get(ColorSpace space) {
		switch (space) {
		case RGB:
			return getRGB();
		case HSL:
			return getHSL();
		case HSV:
			return getHSV();
		case LAB:
			return getLAB();
		case LCH:
			return getLCH();
		default:
			throw new IllegalArgumentException("Invalid color space.");
		}
	}

	public double get(ColorSpace space, Channel channel) {
		switch (space) {
		case RGB:
			return getRGB(channel);
		case HSL:
			return getHSL(channel);
		case HSV:
			return getHSV(channel);
		case LAB:
			return getLAB(channel);
		case LCH:
			return getLCH(channel);
		default:
			throw new IllegalArgumentException("Invalid color space.");
		}
	}

	public double[] getRGB() {
		return chroma.getChromaRGB().getRGBComp();
	}

	public double getRGB(Channel channel) {

		switch (channel) {
		case R:
			return chroma.getRGB_R();
		case G:
			return chroma.getRGB_G();
		case B:
			return chroma.getRGB_B();
		default:
			throw new IllegalArgumentException(
					"Invalid channel for RGB color space.");

		}
	}

	public double[] getHSL() {
		return chroma.getChromaHSL().getHSLComp();
	}

	public double getHSL(Channel channel) {

		switch (channel) {
		case H:
			return chroma.getHSL_H();
		case S:
			return chroma.getHSL_S();
		case L:
			return chroma.getHSL_L();
		default:
			throw new IllegalArgumentException(
					"Invalid channel for HSL color space.");

		}
	}

	public double[] getHSV() {
		return chroma.getChromaHSV().getHSVComp();
	}

	public double getHSV(Channel channel) {

		switch (channel) {
		case H:
			return chroma.getHSV_H();
		case S:
			return chroma.getHSV_S();
		case V:
			return chroma.getHSV_V();
		default:
			throw new IllegalArgumentException(
					"Invalid channel for HSV color space.");

		}
	}

	public double[] getLAB() {
		return chroma.getChromaLAB().getLABComp();
	}

	public double getLAB(Channel channel) {

		switch (channel) {
		case L:
			return chroma.getLAB_L();
		case A:
			return chroma.getLAB_A();
		case B:
			return chroma.getLAB_B();
		default:
			throw new IllegalArgumentException(
					"Invalid channel for LAB color space.");

		}
	}

	public double[] getLCH() {
		return chroma.getChromaLCH().getLCHComp();
	}

	public double getLCH(Channel channel) {

		switch (channel) {
		case L:
			return chroma.getLCH_L();
		case C:
			return chroma.getLCH_C();
		case H:
			return chroma.getLCH_H();
		default:
			throw new IllegalArgumentException(
					"Invalid channel for LCH color space.");

		}
	}

	// SET-COMPONENT METHODS
	// ///////////////////////////////////////////////////////////////////////////////////

	public void set(ColorSpace space, double input1, double input2,
			double input3) {
		switch (space) {
		case RGB:
			setRGB(input1, input2, input3);
			break;
		case HSL:
			setHSL(input1, input2, input3);
			break;
		case HSV:
			setHSV(input1, input2, input3);
			break;
		case LAB:
			setLAB(input1, input2, input3);
			break;
		case LCH:
			setLCH(input1, input2, input3);
			break;
		default:
			throw new IllegalArgumentException("Invalid color space.");
		}
	}

	public void set(ColorSpace space, Channel channel, double input) {
		switch (space) {
		case RGB:
			setRGB(channel, input);
			break;
		case HSL:
			setHSL(channel, input);
			break;
		case HSV:
			setHSV(channel, input);
			break;
		case LAB:
			setLAB(channel, input);
			break;
		case LCH:
			setLCH(channel, input);
			break;
		default:
			throw new IllegalArgumentException("Invalid color space.");
		}
	}

	public void setRGB(double input1, double input2, double input3) {
		this.chroma = chroma.getChromaRGB();
		chroma.setRGB_R(input1);
		chroma.setRGB_G(input2);
		chroma.setRGB_B(input3);
	}

	public void setRGB(Channel channel, double input) {
		this.chroma = chroma.getChromaRGB();
		switch (channel) {
		case R:
			chroma.setRGB_R(input);
			break;
		case G:
			chroma.setRGB_G(input);
			break;
		case B:
			chroma.setRGB_B(input);
			break;
		default:
			throw new IllegalArgumentException(
					"Invalid channel for RGB color space");
		}
	}

	public void setHSL(double input1, double input2, double input3) {
		this.chroma = chroma.getChromaHSL();
		chroma.setHSL_H(input1);
		chroma.setHSL_S(input2);
		chroma.setHSL_L(input3);

	}

	public void setHSL(Channel channel, double input) {
		this.chroma = chroma.getChromaHSL();
		switch (channel) {
		case H:
			chroma.setHSL_H(input);
			break;
		case S:
			chroma.setHSL_S(input);
			break;
		case L:
			chroma.setHSL_L(input);
			break;
		default:
			throw new IllegalArgumentException(
					"Invalid channel for HSL color space");
		}
	}

	public void setHSV(double input1, double input2, double input3) {
		this.chroma = chroma.getChromaHSV();
		chroma.setHSV_H(input1);
		chroma.setHSV_S(input2);
		chroma.setHSV_V(input3);
	}

	public void setHSV(Channel channel, double input) {
		this.chroma = chroma.getChromaHSV();
		switch (channel) {
		case H:
			chroma.setHSV_H(input);
			break;
		case S:
			chroma.setHSV_S(input);
			break;
		case V:
			chroma.setHSV_V(input);
			break;
		default:
			throw new IllegalArgumentException(
					"Invalid channel for HSV color space");
		}
	}

	public void setLAB(double input1, double input2, double input3) {
		this.chroma = chroma.getChromaLAB();
		chroma.setLAB_L(input1);
		chroma.setLAB_A(input2);
		chroma.setLAB_B(input3);
	}

	public void setLAB(Channel channel, double input) {
		this.chroma = chroma.getChromaLAB();
		switch (channel) {
		case L:
			chroma.setLAB_L(input);
			break;
		case A:
			chroma.setLAB_A(input);
			break;
		case B:
			chroma.setLAB_B(input);
			break;
		default:
			throw new IllegalArgumentException(
					"Invalid channel for LAB color space");
		}
	}

	public void setLCH(double input1, double input2, double input3) {
		this.chroma = chroma.getChromaLCH();
		chroma.setLCH_L(input1);
		chroma.setLCH_C(input2);
		chroma.setLCH_H(input3);
	}

	public void setLCH(Channel channel, double input) {
		this.chroma = chroma.getChromaLCH();
		switch (channel) {
		case L:
			chroma.setLCH_L(input);
			break;
		case C:
			chroma.setLCH_C(input);
			break;
		case H:
			chroma.setLCH_H(input);
			break;
		default:
			throw new IllegalArgumentException(
					"Invalid channel for LCH color space");
		}
	}

	// STRING REPRESENTATION METHODS
	// ///////////////////////////////////////////////////////////////////////////////////
	public String hexString() {
		return String.format("%S", "#"
				+ Integer.toHexString(chroma.getColor()).substring(2, 8));
	}

	@Override
	public String toString() {
		return hexString();
	}
}
