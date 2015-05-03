import com.chroma.*;

int l = 50; // Luminosity, Range: 0-100
int c = 70; // Chroma, Range: 0-128
int h = 340; // Hue, Range: 0-360'
int squares = 4;
Chroma testColor;

void setup() {

    size(1200, 1200, "processing.core.PGraphicsRetina2D");
    rectMode(CENTER);
    noStroke();

    testColor = new Chroma(ColorSpace.LCH, 50, 50, 200);
    noLoop();

}

void draw() {

    background(255);

    for (int i = 0; i < squares; i++) {
      fill(testColor.tint(map(i, 0, squares, 0, 100)).get());
      rect(width / 2, height / 2, 600* (squares-i)/squares, 600* (squares-i)/squares);
    }
}

void keyReleased() {
    // Save a screenshot in PNG format
    if (key == 's' || key == 'S') {
        saveFrame("####.png");
    }
}
