public Grid grid;
public Shape s, shape, next1, next2, next3, next4, next5, next6, held, ghost;
public Background background;
public boolean hasHeld = false;
public boolean firstHeld = true;
public int highScore = 0;
public int score = 0;
public int lines = 0;
public boolean isGameOver = false;
public int level = 1;

public void setup() {
  size(1000, 700);
  grid = new Grid();
  s = new Shape(0);
  ghost = new Shape(0);
  shape = new Shape(s.drawPiece());
  ghost.transferAttributes(shape);
  background = new Background();
  shape.active = true;
  next1 = new Shape(s.drawPiece());
  next2 = new Shape(s.drawPiece());
  next3 = new Shape(s.drawPiece());
  next4 = new Shape(s.drawPiece());
  next5 = new Shape(s.drawPiece());
}

public void draw() {
  stroke(0);
  strokeWeight(2);
  fill(220);
  rect(130, 50, 300, 600);
  background(20, 20, 60);
  background.display();
  fill(50, 50, 120);
  rect(520, 90, 140, 500);
  rect(20, 90, 140, 140);
  grid.display();
  drawShape();
  fill(255);
  textSize(40);
  strokeWeight(1);
  if(score > highScore){
    highScore = score;
  }
  text("Score: " + score, 690, 200);
  text("High Score: " + highScore, 690, 350);
  text("Next", 549, 70);
  text("Hold", 52, 70);
  text("Lines: " + lines, 690, 500);
  //text(shape.toString(), 500, 650);
}

public void drawShape() {
  for (int i = 0; i < 4; i++) {
    for (int j = 0; j < 2; j++) {
      ghost.shape[i][j] = shape.shape[i][j];
    }
  }
  ghost.r = shape.r;
  ghost.g = shape.g;
  ghost.b = shape.b;
  ghost.showGhost();
  shape.display();
  next1.showNext1();
  next2.showNext2();
  next3.showNext3();
  next4.showNext4();
  next5.showNext5();
  level = (lines - (lines % 4))/4;
  if (firstHeld == false) {
    held.showHeld();
  }
  if (shape.checkDown(background)) {
    shape.moveDown(level);
  } else {
    shape.active = false;
  }
  if (!shape.active) {
    if (!isGameOver) {
      background.writeShape(shape);
      shape = next1;
      shape.active = true;
      next1 = next2;
      next2 = next3;
      next3 = next4;
      next4 = next5;
      next5 = new Shape(s.drawPiece());
      hasHeld = false;
    }
    if (!shape.checkDown(background)) {
      textSize(120);
      fill(95);
      text("Game Over!", 211, 300);
      text("Game Over!", 209, 300);
      text("Game Over!", 210, 301);
      text("Game Over!", 210, 299);
      fill(225);
      text("Game Over!", 210, 300);
      textSize(100);
      fill(95);
      text("Press 'r' to Retry", 171, 420);
      text("Press 'r' to Retry", 169, 420);
      text("Press 'r' to Retry", 170, 421);
      text("Press 'r' to Retry", 170, 419);
      fill(225);
      text("Press 'r' to Retry", 170, 420);
      isGameOver = true;
    }
  }
}

public void keyPressed() {
  if (!isGameOver) {
    if (keyCode == RIGHT) {
      shape.moveShape("right");
    }
    if (keyCode == LEFT) {
      shape.moveShape("left");
    }
    if (keyCode == DOWN) {
      shape.moveShape("down");
      if (shape.checkDown(background)) {
        score++;
      }
    }
    if (keyCode == UP) {
      shape.moveShape("up");
      score = score + 20;
    }
    if (key == 'z') {
      if (firstHeld == true) {
        held = new Shape(shape.getShapeNum());
        shape = new Shape(next1.getShapeNum());
        next1 = new Shape(next2.getShapeNum());
        next2 = new Shape(next3.getShapeNum());
        next3 = new Shape(next4.getShapeNum());
        next4 = new Shape(next5.getShapeNum());
        next5 = new Shape(s.drawPiece());
        firstHeld = false;
        hasHeld = true;
        shape.active = true;
      } else if (hasHeld == false) {
        int x = held.getShapeNum();
        int y = shape.getShapeNum();
        shape = new Shape(x);
        held = new Shape(y);
        hasHeld = true;
        shape.active = true;
      }
    }
  }
}

public void keyReleased() {
  if (!isGameOver) {
    if (key == 'x') {
      if (shape.checkRotationCCW()) {
        shape.rotateCCW();
        shape.rotateCCW();
        shape.rotationCount++;
      }
    }

    if (key == 'c') {
      if (shape.checkRotationCW()) {
        shape.rotateCW();
        shape.rotateCW();
        shape.rotationCount--;
      }
    }

    if (key == 'v') {
      if (shape.checkRotation180()) {
        shape.rotateCW();
        shape.rotateCW();
        shape.rotationCount--;
        shape.rotateCW();
        shape.rotateCW();
        shape.rotationCount--;
      }
    }
  }
  if (key == 'r') {
    setup();
    lines = 0;
    score = 0;
    isGameOver = false;
    hasHeld = false;
    firstHeld = true;
  }
}
