package com.chroma;

public class ChromaHSV extends ChromaColor {

    private double hsv_H;
    private double hsv_S;
    private double hsv_V;

    public ChromaHSV(double hsv_H_, double hsv_S_, double hsv_V_, double alpha_) {

        this.hsv_H = hsv_H_;
        this.hsv_S = hsv_S_;
        this.hsv_V = hsv_V_;
        this.alpha = alpha_;
    }


    public double[] getHSVComp() {
        return new double[] {this.hsv_H, this.hsv_S, this.hsv_V};
    }

    // GET & SET METHODS
    /////////////////////////////////////////////////////////////////////////////////////
    public double getHSV_H() { return this.hsv_H;}
    public double getHSV_S() { return this.hsv_S;}
    public double getHSV_V() { return this.hsv_V;}

    public void setHSV_H(double hsv_H_) { this.hsv_H = hsv_H_;}
    public void setHSV_S(double hsv_S_) { this.hsv_S = hsv_S_;}
    public void setHSV_V(double hsv_V_) { this.hsv_V = hsv_V_;}

    // CONVERSION METHODS
    /////////////////////////////////////////////////////////////////////////////////////

    @Override
	public ChromaRGB getChromaRGB() {

        double rgb_R_ = 0;
        double rgb_G_ = 0;
        double rgb_B_ = 0;

        // Convert value from 0-1 to 0-255
        double hsv_V_ = this.hsv_V * 255;
        double hsv_S_ = this.hsv_S;
        double hsv_H_ = this.hsv_H;

        if (hsv_S_ == 0) {

            rgb_R_ = rgb_G_ = rgb_B_ = hsv_V_;

        } else {
            // Wrap hue if is out of 0-360 range
            if (hsv_H_ == 360) hsv_H_ = 0;
            if (hsv_H_ > 360) hsv_H_ -= 360;
            if (hsv_H_ < 0)  hsv_H_ += 360;

            // Transforming chromaticity plane to bottom 3 faces of the RGB cube
            hsv_H_ /= 60;
            int i = (int)Math.floor(hsv_H_);
            double f = hsv_H_ - i;
            double p = hsv_V_ * (1 - hsv_S_);
            double q = hsv_V_ * (1 - hsv_S_ * f);
            double t = hsv_V_ * (1 - hsv_S_ * (1 - f));

            switch (i) {
            case 0: rgb_R_ = hsv_V_;  rgb_G_ = t;  rgb_B_ = p;  break;
            case 1: rgb_R_ = q;  rgb_G_ = hsv_V_;  rgb_B_ = p;  break;
            case 2: rgb_R_ = p;  rgb_G_ = hsv_V_;  rgb_B_ = t;  break;
            case 3: rgb_R_ = p;  rgb_G_ = q;  rgb_B_ = hsv_V_;  break;
            case 4: rgb_R_ = t;  rgb_G_ = p;  rgb_B_ = hsv_V_;  break;
            case 5: rgb_R_ = hsv_V_;  rgb_G_ = p;  rgb_B_ = q;  break;
            default: break;
            }
        }

        return new ChromaRGB(rgb_R_, rgb_G_, rgb_B_, this.alpha);
    }

    @Override
	public ChromaHSL getChromaHSL() {
        return getChromaRGB().getChromaHSL();
    }
    @Override
	public ChromaHSV getChromaHSV() {
        return this;
    }
    @Override
	public ChromaLAB getChromaLAB() {
        return getChromaRGB().getChromaLAB();
    }
    @Override
	public ChromaLCH getChromaLCH() {
        return getChromaRGB().getChromaLCH();
    }
}


