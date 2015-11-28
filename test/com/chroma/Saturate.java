
package com.chroma;

import processing.core.PApplet;

public class Saturate extends PApplet {

	public static void main(String[] args) {
		String[] a = { "Main" };
		PApplet.runSketch(a, new Saturate());
	}
	
	int l = 50; // Luminosity, Range: 0-100
	int c = 70; // Chroma, Range: 0-128
	int h = 340; // Hue, Range: 0-360

	Chroma hexColor;

	@Override
	public void settings() {
		size(600, 600, FX2D);
		pixelDensity(2);
	}
	
	
	@Override
	public void setup() {

		rectMode(CENTER);
		noStroke();

		hexColor = new Chroma(ColorSpace.LCH, 50, 20, 0);
		noLoop();

	}

	@Override
	public void draw() {

		background(255);
		fill(hexColor.get());
		rect(width / 2, height / 2, 600, 600);

		fill(hexColor.saturate().get());
		rect(width / 2, height / 2, 300, 300);
	}

	@Override
	public void keyReleased() {
		// Save a screenshot in PNG format
		if (key == 's' || key == 'S') {
			saveFrame("####.png");
		}
	}

}
