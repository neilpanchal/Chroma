
package com.chroma;

import processing.core.PApplet;

public class PlotColors extends PApplet {

	public static void main(String[] args) {
		String[] a = { "Main" };
		PApplet.runSketch(a, new PlotColors());
	}
	
	int PALETTE_SIZE = 600;
	int CANVAS_MIN = 0;
	int CANVAS_MAX = 600;

	Chroma[][] testColors;

	@Override
	public void settings() {
		size(PALETTE_SIZE, PALETTE_SIZE, FX2D);
		pixelDensity(2);
	}
	
	@Override
	public void setup() {
		noStroke();
		noLoop();
		testColors = new Chroma[PALETTE_SIZE][PALETTE_SIZE];
	}

	@Override
	public void draw() {
		background(255);
		plotHC(testColors);
	}

	public void plotHC(Chroma[][] chromaPalette) {
		smooth();

		float L = 50;
		float C;
		float H;

		int R;
		int G;
		int B;

		for (int i = 0; i < PALETTE_SIZE; i++) {
			for (int j = 0; j < PALETTE_SIZE; j++) {

				C = map(i, 0, PALETTE_SIZE, 0, 120);
				H = map(j, 0, PALETTE_SIZE, 0, 360);

				chromaPalette[i][j] = new Chroma(ColorSpace.LCH, L, C, H);
			}
		}

		// Draw
		println("Plotting...");

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int p = (int) map(i, 0, width, 0, PALETTE_SIZE);
				int q = (int) map(j, 0, height, 0, PALETTE_SIZE);

				R = (int) chromaPalette[p][q].getRGB(Channel.R);
				G = (int) chromaPalette[p][q].getRGB(Channel.G);
				B = (int) chromaPalette[p][q].getRGB(Channel.B);

				if (chromaPalette[p][q].clipped()) {
					fill(255, 255, 255, 255);
				} else {
					fill(R, G, B, 255);
				}
				rect(j, i, (CANVAS_MAX - CANVAS_MIN) / PALETTE_SIZE,
						(CANVAS_MAX - CANVAS_MIN) / PALETTE_SIZE);
			}
		}
	}

	public void plotHL(Chroma[][] chromaPalette) {
		smooth();

		float L;
		float C = 100;
		float H;

		int R;
		int G;
		int B;

		for (int i = 0; i < PALETTE_SIZE; i++) {
			for (int j = 0; j < PALETTE_SIZE; j++) {

				L = map(i, 0, PALETTE_SIZE, 0, 100);
				H = map(j, 0, PALETTE_SIZE, 0, 360);

				chromaPalette[i][j] = new Chroma(ColorSpace.LCH, L, C, H);
			}
		}

		// Draw
		println("Plotting...");

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int p = (int) map(i, 0, width, 0, PALETTE_SIZE);
				int q = (int) map(j, 0, height, 0, PALETTE_SIZE);

				R = (int) chromaPalette[p][q].getRGB(Channel.R);
				G = (int) chromaPalette[p][q].getRGB(Channel.G);
				B = (int) chromaPalette[p][q].getRGB(Channel.B);

				if (chromaPalette[p][q].clipped()) {
					fill(255, 255, 255, 255);
				} else {
					fill(R, G, B, 255);
				}
				rect(i, j, (CANVAS_MAX - CANVAS_MIN) / PALETTE_SIZE,
						(CANVAS_MAX - CANVAS_MIN) / PALETTE_SIZE);
			}
		}
	}

	public void printColor(String chromaName, Chroma printColor) {
		println(chromaName + "RGB:\t\t" + printColor.getRGB(Channel.R) + "\t\t"
				+ printColor.getRGB(Channel.G) + "\t\t"
				+ printColor.getRGB(Channel.B) + "\t\t" + printColor.clipped());

		double[] chromaLab = printColor.getLAB();
		double[] chromaLch = printColor.getLCH();

		println(chromaName + "Lab:\t\t" + chromaLab[0] + "\t\t" + chromaLab[1]
				+ "\t\t" + chromaLab[2] + "\t\t");

		println(chromaName + "Lch:\t\t" + chromaLch[0] + "\t\t" + chromaLch[1]
				+ "\t\t" + chromaLch[2] + "\t\t");
	}

}
