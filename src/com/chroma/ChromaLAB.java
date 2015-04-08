package com.chroma;

public class ChromaLAB extends ChromaColor {

    private double lab_L;
    private double lab_A;
    private double lab_B;

    public ChromaLAB(double lab_L_, double lab_A_, double lab_B_, double alpha_) {

        this.lab_L = lab_L_;
        this.lab_A = lab_A_;
        this.lab_B = lab_B_;
        this.alpha = alpha_;
    }

    public double[] getLABComp() {
        return new double[] {lab_L, lab_A, lab_B};
    }

    // GET & SET METHODS
    /////////////////////////////////////////////////////////////////////////////////////
    public double getLAB_L() { return this.lab_L;}
    public double getLAB_A() { return this.lab_A;}
    public double getLAB_B() { return this.lab_B;}

    public void setLAB_L(double lab_L_) { this.lab_L = lab_L_;}
    public void setLAB_A(double lab_A_) { this.lab_A = lab_A_;}
    public void setLAB_B(double lab_B_) { this.lab_B = lab_B_;}

    // CONVERSION METHODS
    /////////////////////////////////////////////////////////////////////////////////////

    @Override
	public ChromaRGB getChromaRGB() {

        double y = (this.lab_L + 16.0) / 116.0;
        double x = y + this.lab_A / 500.0;
        double z = y - this.lab_B / 200.0;

        x = ChromaUtil.lab_xyz(x) * CONSTANT_X;
        y = ChromaUtil.lab_xyz(y) * CONSTANT_Y;
        z = ChromaUtil.lab_xyz(z) * CONSTANT_Z;

        double rgb_R_ = ChromaUtil.xyz_rgb(3.2404542 * x - 1.5371385 * y - 0.4985314 * z);
        double rgb_G_ = ChromaUtil.xyz_rgb(-0.9692660 * x + 1.8760108 * y + 0.0415560 * z);
        double rgb_B_ = ChromaUtil.xyz_rgb(0.0556434 * x - 0.2040259 * y + 1.0572252 * z);

        return new ChromaRGB(rgb_R_, rgb_G_, rgb_B_, alpha);
    }


    @Override
	public ChromaHSL getChromaHSL() {
        return getChromaRGB().getChromaHSL();
    }

    @Override
	public ChromaHSV getChromaHSV() {
        return getChromaRGB().getChromaHSV();
    }

    @Override
	public ChromaLAB getChromaLAB() {
        return this;
    }
    @Override
	public ChromaLCH getChromaLCH() {

        double lch_C_ = Math.sqrt(Math.pow(this.lab_A, 2) + Math.pow(this.lab_B, 2));
        double lch_H_ = Math.atan2(this.lab_B, this.lab_A) / Math.PI * 180;

        return new ChromaLCH(this.lab_L, lch_C_, lch_H_, alpha);
    }

}


