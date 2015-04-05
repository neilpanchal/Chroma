package com.chroma;

public class ChromaUtil {

    public static double clamp(double value, double lower, double upper) {
        return Math.max(lower, Math.min(upper, value));
    }


    public static double max3(double a, double b, double c) {
        return Math.max(a, Math.max(b, c));
    }

    public static double min3(double a, double b, double c) {
        return Math.min(a, Math.min(b, c));
    }

    public static double lab_xyz(double x) {

        if (x > 0.206893034) {
            return x * x * x;
        } else {
            return (x - 4.0F / 29.0) / 7.787037;
        }
    }

    public static double xyz_rgb(double r) {

        return Math.round(255.0 * (r <= 0.00304 ? 12.92 * r : 1.055 * Math.pow(r, 1 / 2.4) - 0.055));

    }

    public static double rgb_xyz(double r) {

        if ((r /= 255.0) <= 0.04045) {
            return r / 12.92;
        } else {
            return Math.pow((r + 0.055) / 1.055, 2.4);
        }
    }

    public static double xyz_lab(double x) {

        if (x > 0.008856) {
            return Math.pow(x, 1.0 / 3.0);
        } else {
            return 7.787037 * x + 4.0 / 29.0;
        }
    }

}
