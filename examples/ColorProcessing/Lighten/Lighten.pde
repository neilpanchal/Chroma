import com.chroma.*;

int l = 50; // Luminosity, Range: 0-100
int c = 70; // Chroma, Range: 0-128
int h = 340; // Hue, Range: 0-360

Chroma testColor;

void setup() {

    size(1200, 1200, "processing.core.PGraphicsRetina2D");
    rectMode(CENTER);
    noStroke();

    testColor = new Chroma(ColorSpace.LCH, 50, 20, 0);
    noLoop();

}

void draw() {

    background(255);
    fill(testColor.get());
    rect(width / 2, height / 2, 600, 600);

    fill(testColor.lighten().get());
    rect(width / 2, height / 2, 300, 300);
}

void keyReleased() {
    // Save a screenshot in PNG format
    if (key == 's' || key == 'S') {
        saveFrame("####.png");
    }
}


