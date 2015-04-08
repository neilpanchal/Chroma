package com.chroma;

public class ChromaRGB extends ChromaColor {

    private double rgb_R;
    private double rgb_G;
    private double rgb_B;

    public ChromaRGB(double rgb_R_, double rgb_G_, double rgb_B_, double alpha_) {

        this.rgb_R = rgb_R_;
        this.rgb_G = rgb_G_;
        this.rgb_B = rgb_B_;
        this.alpha = alpha_;
    }
    public double[] getRGBComp() {
        return new double[] {this.rgb_R, this.rgb_G, this.rgb_B};
    }

    // GET & SET METHODS
    /////////////////////////////////////////////////////////////////////////////////////
    public double getRGB_R() { return this.rgb_R;}
    public double getRGB_G() { return this.rgb_G;}
    public double getRGB_B() { return this.rgb_B;}

    public void setRGB_R(double rgb_R_) { this.rgb_R = rgb_R_;}
    public void setRGB_G(double rgb_G_) { this.rgb_G = rgb_G_;}
    public void setRGB_B(double rgb_B_) { this.rgb_B = rgb_B_;}


    // CONVERSION METHODS
    /////////////////////////////////////////////////////////////////////////////////////

    @Override
	public ChromaRGB getChromaRGB() { return this;}

    @Override
	public ChromaHSL getChromaHSL() {

        ChromaHSV hsv = getChromaHSV();
        double[] hsv_comp = hsv.getHSVComp();

        double hsv_H_ = hsv_comp[0];
        double hsv_S_ = hsv_comp[1];
        double hsv_V_ = hsv_comp[2];

        double hsl_H_;
        double hsl_S_;
        double hsl_L_;

        hsl_H_ = hsv_H_;
        hsl_S_ = hsv_S_ * hsv_V_;
        hsl_L_ = (2 - hsv_S_) * hsv_V_;
        hsl_S_ /= (hsl_L_ <= 1) ? (hsl_L_) : (2 - hsl_L_);
        hsl_L_ /= 2;

        return new ChromaHSL(hsl_H_, hsl_S_, hsl_L_, getAlpha());
    }

    @Override
	public ChromaHSV getChromaHSV() {

        double rgb_R_ = rgb_R / 255.0;
        double rgb_G_ = rgb_G / 255.0;
        double rgb_B_ = rgb_B / 255.0;

        double max = ChromaUtil.max3(rgb_R_, rgb_G_, rgb_B_);
        double min = ChromaUtil.min3(rgb_R_, rgb_G_, rgb_B_);

        double delta = max - min;

        double hsv_H_;
        double hsv_S_;
        double hsv_V_;

        hsv_V_ = max;

        if (hsv_V_ == 0) {
            hsv_H_ = 0;
            hsv_S_ = 0;
        } else {
            hsv_S_ = delta / max;

            if (hsv_S_ == 0) {
                hsv_H_ = 0;
            } else {
                if (max == rgb_R_) {
                    hsv_H_ = (rgb_G_ - rgb_B_) / delta;
                } else if (max == rgb_G_) {
                    hsv_H_ = 2 + (rgb_B_ - rgb_R_) / delta;
                } else {
                    hsv_H_ = 4 + (rgb_R_ - rgb_G_) / delta;
                }

                hsv_H_ *= 60.0;

                if (hsv_H_ < 0) {
                    hsv_H_ += 360.0;
                }
            }
        }

        return new ChromaHSV(hsv_H_, hsv_S_, hsv_V_, alpha);
    }

    @Override
	public ChromaLAB getChromaLAB() {

        double rgb_R_ = ChromaUtil.rgb_xyz(this.rgb_R);
        double rgb_G_ = ChromaUtil.rgb_xyz(this.rgb_G);
        double rgb_B_ = ChromaUtil.rgb_xyz(this.rgb_B);

        double x = ChromaUtil.xyz_lab((0.4124564 * rgb_R_ + 0.3575761 * rgb_G_ + 0.1804375 * rgb_B_) / CONSTANT_X);
        double y = ChromaUtil.xyz_lab((0.2126729 * rgb_R_ + 0.7151522 * rgb_G_ + 0.0721750 * rgb_B_) / CONSTANT_Y);
        double z = ChromaUtil.xyz_lab((0.0193339 * rgb_R_ + 0.1191920 * rgb_G_ + 0.9503041 * rgb_B_) / CONSTANT_Z);

        return new ChromaLAB(116.0 * y - 16.0, 500.0 * (x - y), 200.0 * (y - z), alpha);
    }

    @Override
	public ChromaLCH getChromaLCH() { return getChromaLAB().getChromaLCH();}

}
