package com.chroma;

public abstract class ChromaColor {

    public double alpha;

    // CONSTANTS
    /////////////////////////////////////////////////////////////////////////////////////

    public static final double CONSTANT_K = 18.0;
    public static final double CONSTANT_X = 0.950470;
    public static final double CONSTANT_Y = 1.0;
    public static final double CONSTANT_Z = 1.088830;

    // GLOBAL METHODS
    /////////////////////////////////////////////////////////////////////////////////////

    public double getAlpha() {
        return alpha;
    }
    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public int getColor() {

        double temp[] = getChromaRGB().getRGBComp();

        return  (int)ChromaUtil.clamp(this.alpha, 0, 255) << 24 |
                (int)ChromaUtil.clamp(temp[0], 0, 255) << 16 |
                (int)ChromaUtil.clamp(temp[1], 0, 255) << 8 |
                (int)ChromaUtil.clamp(temp[2], 0, 255);
    }

    public boolean clipped() {

        double temp[] = getChromaRGB().getRGBComp();

        return temp[0] < 0 || temp[1] < 0 || temp[2] < 0 ||
               temp[0] > 255 || temp[1] > 255 || temp[2] > 255;
    }

    // CHROMA CONVERSIONS (Must be implemented in each Chroma class)
    /////////////////////////////////////////////////////////////////////////////////////

    public abstract ChromaRGB getChromaRGB();
    public abstract ChromaHSV getChromaHSV();
    public abstract ChromaHSL getChromaHSL();
    public abstract ChromaLAB getChromaLAB();
    public abstract ChromaLCH getChromaLCH();

}
