package com.chroma;

public class ChromaLCH extends ChromaColor {

	private double lch_L;
	private double lch_C;
	private double lch_H;

	public ChromaLCH(double lch_L_, double lch_C_, double lch_H_, double alpha_) {

		this.lch_L = lch_L_;
		this.lch_C = lch_C_;
		this.lch_H = lch_H_;
		this.alpha = alpha_;
	}

	public double[] getLCHComp() {
		return new double[] { lch_L, lch_C, lch_H };
	}

	// GET & SET METHODS
	// ///////////////////////////////////////////////////////////////////////////////////
	@Override
	public double getLCH_L() {
		return this.lch_L;
	}

	@Override
	public double getLCH_C() {
		return this.lch_C;
	}

	@Override
	public double getLCH_H() {
		return this.lch_H;
	}

	@Override
	public void setLCH_L(double lch_L_) {
		this.lch_L = lch_L_;
	}

	@Override
	public void setLCH_C(double lch_C_) {
		this.lch_C = lch_C_;
	}

	@Override
	public void setLCH_H(double lch_H_) {
		this.lch_H = lch_H_;
	}

	// CONVERSION METHODS
	// ///////////////////////////////////////////////////////////////////////////////////

	@Override
	public ChromaRGB getChromaRGB() {
		return getChromaLAB().getChromaRGB();
	}

	@Override
	public ChromaHSL getChromaHSL() {
		return getChromaLAB().getChromaHSL();
	}

	@Override
	public ChromaHSV getChromaHSV() {
		return getChromaLAB().getChromaHSV();
	}

	@Override
	public ChromaLAB getChromaLAB() {

		double lch_H_ = this.lch_H * Math.PI / 180;
		return new ChromaLAB(this.lch_L, Math.cos(lch_H_) * this.lch_C,
				Math.sin(lch_H_) * this.lch_C, this.alpha);
	}

	@Override
	public ChromaLCH getChromaLCH() {
		return this;
	}

}
