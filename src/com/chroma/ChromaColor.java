package com.chroma;

public abstract class ChromaColor {

	public double alpha;

	// CONSTANTS
	// ///////////////////////////////////////////////////////////////////////////////////

	public static final double CONSTANT_K = 18.0;
	public static final double CONSTANT_X = 0.950470;
	public static final double CONSTANT_Y = 1.0;
	public static final double CONSTANT_Z = 1.088830;

	// GLOBAL METHODS
	// ///////////////////////////////////////////////////////////////////////////////////

	public double getAlpha() {
		return alpha;
	}

	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}

	public int getColor() {

		double temp[] = getChromaRGB().getRGBComp();

		return (int) ChromaUtil.clamp(this.alpha, 0, 255) << 24
				| (int) ChromaUtil.clamp(temp[0], 0, 255) << 16
				| (int) ChromaUtil.clamp(temp[1], 0, 255) << 8
				| (int) ChromaUtil.clamp(temp[2], 0, 255);
	}

	public boolean clipped() {

		double temp[] = getChromaRGB().getRGBComp();

		return temp[0] < 0 || temp[1] < 0 || temp[2] < 0 || temp[0] > 255
				|| temp[1] > 255 || temp[2] > 255;
	}

	public double getLuminance() {
		double temp[] = getChromaRGB().getRGBComp();

		return 0.2126 * ChromaUtil.luminance_x(temp[0]) + 0.7152
				* ChromaUtil.luminance_x(temp[1]) + 0.0722
				* ChromaUtil.luminance_x(temp[2]);
	}

	// GET & SET METHOD FOR COLOR COMPONENTS
	// ///////////////////////////////////////////////////////////////////////////////////
	public double getRGB_R() {
		return this.getChromaRGB().getRGB_R();
	}

	public double getRGB_G() {
		return this.getChromaRGB().getRGB_G();
	}

	public double getRGB_B() {
		return this.getChromaRGB().getRGB_B();
	}

	public double getHSL_H() {
		return this.getChromaHSL().getHSL_H();
	}

	public double getHSL_S() {
		return this.getChromaHSL().getHSL_S();
	}

	public double getHSL_L() {
		return this.getChromaHSL().getHSL_L();
	}

	public double getHSV_H() {
		return this.getChromaHSV().getHSV_H();
	}

	public double getHSV_S() {
		return this.getChromaHSV().getHSV_S();
	}

	public double getHSV_V() {
		return this.getChromaHSV().getHSV_V();
	}

	public double getLAB_L() {
		return this.getChromaLAB().getLAB_L();
	}

	public double getLAB_A() {
		return this.getChromaLAB().getLAB_A();
	}

	public double getLAB_B() {
		return this.getChromaLAB().getLAB_B();
	}

	public double getLCH_L() {
		return this.getChromaLCH().getLCH_L();
	}

	public double getLCH_C() {
		return this.getChromaLCH().getLCH_C();
	}

	public double getLCH_H() {
		return this.getChromaLCH().getLCH_H();
	}

	public void setRGB_R(double rgb_R_) {
		this.getChromaRGB().setRGB_R(rgb_R_);
	}

	public void setRGB_G(double rgb_G_) {
		this.getChromaRGB().setRGB_G(rgb_G_);
	}

	public void setRGB_B(double rgb_B_) {
		this.getChromaRGB().setRGB_B(rgb_B_);
	}

	public void setHSL_H(double hsl_H_) {
		this.getChromaHSL().setHSL_H(hsl_H_);
	}

	public void setHSL_S(double hsl_S_) {
		this.getChromaHSL().setHSL_S(hsl_S_);
	}

	public void setHSL_L(double hsl_L_) {
		this.getChromaHSL().setHSL_L(hsl_L_);
	}

	public void setHSV_H(double hsv_H_) {
		this.getChromaHSV().setHSV_H(hsv_H_);
	}

	public void setHSV_S(double hsv_S_) {
		this.getChromaHSV().setHSV_S(hsv_S_);
	}

	public void setHSV_V(double hsv_V_) {
		this.getChromaHSV().setHSV_V(hsv_V_);
	}

	public void setLAB_L(double lab_L_) {
		this.getChromaLAB().setLAB_L(lab_L_);
	}

	public void setLAB_A(double lab_A_) {
		this.getChromaLAB().setLAB_A(lab_A_);
	}

	public void setLAB_B(double lab_B_) {
		this.getChromaLAB().setLAB_B(lab_B_);
	}

	public void setLCH_L(double lch_L_) {
		this.getChromaLCH().setLCH_L(lch_L_);
	}

	public void setLCH_C(double lch_C_) {
		this.getChromaLCH().setLCH_C(lch_C_);
	}

	public void setLCH_H(double lch_H_) {
		this.getChromaLCH().setLCH_H(lch_H_);
	}

	// CHROMA CONVERSIONS (Must be implemented in each Chroma class)
	// ///////////////////////////////////////////////////////////////////////////////////

	public abstract ChromaRGB getChromaRGB();

	public abstract ChromaHSV getChromaHSV();

	public abstract ChromaHSL getChromaHSL();

	public abstract ChromaLAB getChromaLAB();

	public abstract ChromaLCH getChromaLCH();

}
