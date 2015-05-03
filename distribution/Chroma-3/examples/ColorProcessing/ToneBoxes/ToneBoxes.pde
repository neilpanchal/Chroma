import com.chroma.*;

int l = 50; // Luminosity, Range: 0-100
int c = 70; // Chroma, Range: 0-128
int h = 340; // Hue, Range: 0-360'



int sizeX = 100;
int sizeY = 100;

int CANVAS_X = 1200;
int CANVAS_Y = 1200;

int ASPECT_R = 1200/720;

int spacingX = CANVAS_X/24;
int spacingY = CANVAS_Y/24;

// Find number of squares
float squaresX = ((CANVAS_X-spacingX*2)+spacingX)/(sizeX+spacingX);
float squaresY = ((CANVAS_Y-spacingY*2)+spacingY)/(sizeY+spacingY);


int squares = 4;
Chroma testColor;

void setup() {

    size(CANVAS_X, CANVAS_Y, "processing.core.PGraphicsRetina2D");
    rectMode(CENTER);
    noStroke();

    testColor = new Chroma(ColorSpace.LCH, 50, 50, 30);

    println(squaresX);
    println(squaresY);

    noLoop();

}

void draw() {

    background(255);

    for (int i = 1; i <= squaresX; i++) {
        for (int j = 1; j <= squaresY; j++) {
            fill(testColor.tone(map(i*j, 0, squaresX*squaresY, 0, 100)).get());
            rect(i*(sizeX+spacingX), j*(sizeY+spacingY), sizeX, sizeY);
        }
    }
}

void keyReleased() {
    // Save a screenshot in PNG format
    if (key == 's' || key == 'S') {
        saveFrame("####.png");
    }
}
