
package com.chroma;

import processing.core.PApplet;

public class TestClient extends PApplet {

	public static void main(String[] args) {
		String[] a = { "Main" };
		PApplet.runSketch(a, new TestClient());
	}
	
	int l = 50; // Luminosity, Range: 0-100
	int c = 70; // Chroma, Range: 0-128
	int h = 380; // Hue

	Chroma testColor; // Declare a chroma object
	Chroma hexColor;
	
	@Override
	public void settings() {
		size(1280, 720, FX2D);
		pixelDensity(2);
	}
	
	@Override
	public void setup() {

		rectMode(CENTER);
		noStroke();

		testColor = new Chroma(ColorSpace.LCH, l, c, h, 255); // Create a chroma
																// object
		hexColor = new Chroma("#FF0000");
		println(hexColor.getRGB(Channel.R));
		hexColor.setRGB(Channel.R, 200);
		println(hexColor.getRGB(Channel.R));
		println(hexColor.getHSL());
		hexColor.setHSL(Channel.S, 20);
		println(hexColor.getHSL());
		println("------------------");
		println(hexColor.getRGB(Channel.R));

	}

	@Override
	public void draw() {

		background(255);

		fill(testColor.get()); // To fetch RGB color, use the getRGB method.
		rect(width / 2, height / 2, 100, 100); // Draw a cyan square
	}

	public void printColor(Chroma color_) {
		System.out
				.println("----------------------------------------------------------------");
		System.out.format("%16s%16s\n", "VALID:",
				Boolean.toString(!color_.clipped()).toUpperCase());
		System.out.format("%16s%16s\n", "HEX:", color_.toString());
		System.out
				.println("----------------------------------------------------------------");
		System.out.format("%16s%16f%16f%16f\n", "RGB:", color_.getRGB()[0],
				color_.getRGB()[1], color_.getRGB()[2]);
		System.out.format("%16s%16f%16f%16f\n", "HSL:", color_.getHSL()[0],
				color_.getHSL()[1], color_.getHSL()[2]);
		System.out.format("%16s%16f%16f%16f\n", "HSV:", color_.getHSV()[0],
				color_.getHSV()[1], color_.getHSV()[2]);
		System.out.format("%16s%16f%16f%16f\n", "LAB:", color_.getLAB()[0],
				color_.getLAB()[1], color_.getLAB()[2]);
		System.out.format("%16s%16f%16f%16f\n", "LCH:", color_.getLCH()[0],
				color_.getLCH()[1], color_.getLCH()[2]);
		System.out
				.println("----------------------------------------------------------------");
		System.out.format("%16s%16f\n", "LUMINANCE:", color_.getLuminance());

	}
}
