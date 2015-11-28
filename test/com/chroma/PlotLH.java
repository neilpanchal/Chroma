
package com.chroma;

import processing.core.PApplet;

public class PlotLH extends PApplet {
	
	public static void main(String[] args) {
		String[] a = { "Main" };
		PApplet.runSketch(a, new PlotLH());
	}
	
	int CANVAS_X = 720;
	int CANVAS_Y = 720;

	int DOT_COUNT_X = CANVAS_X / 20;
	int DOT_COUNT_Y = CANVAS_Y / 20;

	int DOT_SIZE_X = 10;
	int DOT_SIZE_Y = 10;

	Chroma[][] dotColors;

	float l = 60;
	float c = 40;
	float h = 0;

	@Override
	public void settings() {
		size(CANVAS_X, CANVAS_Y, FX2D);
		pixelDensity(2);
	}
	
	
	@Override
	public void setup() {

		ellipseMode(CENTER);
		noStroke();
		noLoop();
		dotColors = new Chroma[DOT_COUNT_X + 1][DOT_COUNT_Y + 1];

		for (int i = 0; i < DOT_COUNT_X + 1; i++) {
			for (int j = 0; j < DOT_COUNT_Y + 1; j++) {
				h = map(i, 0, DOT_COUNT_X, 0, 360);
				l = map(j, 0, DOT_COUNT_Y, 0, 100);
				dotColors[i][j] = new Chroma(ColorSpace.LCH, l, c, h, 255);
			}
		}
	}

	@Override
	public void draw() {

		background(255);

		for (int i = 0; i < DOT_COUNT_X + 1; i++) {
			for (int j = 0; j < DOT_COUNT_Y + 1; j++) {
				Chroma tempColor = dotColors[i][j];
				if (tempColor.clipped()) {
					fill(color(255));
				} else {
					fill(dotColors[i][j].get());
				}
				ellipse(map(i, 0, DOT_COUNT_X, DOT_SIZE_X / 2, CANVAS_X
						- DOT_SIZE_X / 2),
						map(j, 0, DOT_COUNT_Y, DOT_SIZE_Y / 2, CANVAS_Y
								- DOT_SIZE_Y / 2), DOT_SIZE_X, DOT_SIZE_Y);
			}
		}
	}

	@Override
	public void keyReleased() {
		if (key == 's' || key == 'S')
			saveFrame("####.png");
	}

}
