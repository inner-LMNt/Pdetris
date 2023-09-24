import processing.core.*;
import processing.data.*;
import processing.event.*;
import processing.opengl.*;

import java.util.Random;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class Pdetris extends PApplet {

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
  /* size commented out by preprocessor */;
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
public class Background {

  private int[][][] colors;
  int r, g, b;
  int w = 30;
  private int x, y;

  public Background() {
    colors = new int[10][20][3];
  }

  private int checkLinesCleared() {
    int result = 0;
    for (int i = 0; i < 20; i++) {
      if (checkRow(i)) {
        lines++;
        removeLine(i);
        result++;
      }
    }
    return result;
  }

  public void display() {
    switch(checkLinesCleared()) {
    case 1:
      score = score + 100;
      break;
    case 2:
      score = score + 400;
      break;
    case 3:
      score = score + 1000;
      break;
    case 4:
      score = score + 2000;
      break;
    }
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 20; j++) {
        r = colors[i][j][0];
        g = colors[i][j][1];
        b = colors[i][j][2];
        fill(r, g, b);
        rect(i*w + 190, j*w + 50, w, w);
      }
    }
    for (int i = 0; i < 20; i++) {
      if (checkRow(i)) {
        lines++;
        removeLine(i);
      }
    }
  }

  public void writeShape(Shape s) {
    for (int i = 0; i < 4; i++) {
      x = s.shape[i][0];
      y = s.shape[i][1];

      if (y < 0) {
        isGameOver = true;
      }
      if (!isGameOver) {
        colors[x][y][0] = s.r;
        colors[x][y][1] = s.g;
        colors[x][y][2] = s.b;
      }
    }
  }

  public boolean checkRow(int row) {
    for (int i = 0; i < 10; i++) {
      if (colors[i][row][0] == 0 && colors[i][row][1] == 0 && colors[i][row][2] == 0) {
        return false;
      }
    }
    return true;
  }

  public void removeLine(int row) {
    int[][][] newBackground = new int[10][20][3];
    for (int i = 0; i < 10; i++) {
      for (int j = 19; j > row; j--) {
        for (int k = 0; k < 3; k++) {
          newBackground[i][j][k] = colors[i][j][k];
        }
      }
      for (int r = row; r >= 1; r--) {
        for (int j = 0; j < 10; j++) {
          newBackground[j][r][0] = colors[j][r-1][0];
          newBackground[j][r][1] = colors[j][r-1][1];
          newBackground[j][r][2] = colors[j][r-1][2];
        }
      }
    }
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 20; j++) {
        for (int k = 0; k < 3; k++) {
          colors[i][j][k] = newBackground[i][j][k];
        }
      }
    }
  }
}
public class Grid{
  
  private float w;
  
  public Grid(){
    w = 30;
  }
  
  public void display(){
    stroke(200);
    for (int i = 0; i < 11; i++){
      line(190, i*w+50, 490, i*w+50);
      line(190, (i+10)*w+50, 490, (i+10)*w+50);
      line(i*w+190, 50, i*w+190, 650);
    }
  }
}
 //<>//

public class Shape {

  private int[][] sPiece = {{4, 0}, {4, 1}, {3, 1}, {5, 0}};
  private int[][] zPiece = {{3, 0}, {4, 1}, {4, 0}, {5, 1}};
  private int[][] lPiece = {{3, 1}, {4, 1}, {5, 1}, {5, 0}};
  private int[][] jPiece = {{3, 0}, {4, 1}, {3, 1}, {5, 1}};
  private int[][] oPiece = {{4, 0}, {5, 0}, {4, 1}, {5, 1}};
  private int[][] tPiece = {{3, 1}, {4, 1}, {4, 0}, {5, 1}};
  private int[][] iPiece = {{3, 0}, {4, 0}, {5, 0}, {6, 0}};

  private ArrayList<Integer> bag = new ArrayList<Integer>();
  private int[] pieces = {0, 1, 2, 3, 4, 5, 6};

  private int[][] shape, originalShape;
  private int r, g, b;
  private int x, y;
  private int counter = 1;
  private int rotationCount = 0;
  private int tempRotationCount;
  private boolean active;
  private int shapeNum;

  private Random random = new Random();

  private int drawPiece() {
    if (bag.size() == 0) {
      for (int i = 0; i < 7; i++) {
        bag.add(pieces[i]);
      }
    }
    int index = random.nextInt(bag.size());
    int result = bag.get(index);
    bag.remove(index);
    return result;
  }

  public Shape(int choice) {
    switch(choice) {
    case 0:
      shape = sPiece;
      r = 34;
      g = 177;
      b = 76;
      break;
    case 1:
      shape = zPiece;
      r = 237;
      g = 28;
      b = 36;
      break;
    case 2:
      shape = lPiece;
      r = 255;
      g = 127;
      b = 39;
      break;
    case 3:
      shape = jPiece;
      r = 63;
      g = 72;
      b = 204;
      break;
    case 4:
      shape = oPiece;
      r = 255;
      g = 242;
      b = 0;
      break;
    case 5:
      shape = tPiece;
      r = 163;
      g = 73;
      b = 164;
      break;
    case 6:
      shape = iPiece;
      r = 0;
      g = 162;
      b = 232;
      break;
    }
    originalShape = shape;
    shapeNum = choice;
  }

  public void display() {
    fill(r, g, b);
    for (int i = 0; i < 4; i++) {
      rect(shape[i][0] * 30 + 190, shape[i][1] * 30 + 50, 30, 30);
    }
  }

  public void showNext1() {
    fill(r, g, b);
    if (equal(oPiece)) {
      for (int i = 0; i < 4; i++) {
        rect(shape[i][0] * 30 + 440, shape[i][1] * 30 + 130, 30, 30);
      }
    } else if (equal(iPiece)) {
      for (int i = 0; i < 4; i++) {
        rect(shape[i][0] * 30 + 440, shape[i][1] * 30 + 145, 30, 30);
      }
    } else {
      for (int i = 0; i < 4; i++) {
        rect(shape[i][0] * 30 + 456, shape[i][1] * 30 + 130, 30, 30);
      }
    }
  }

  public void showNext2() {
    fill(r, g, b);
    if (equal(oPiece)) {
      for (int i = 0; i < 4; i++) {
        rect(shape[i][0] * 30 + 440, shape[i][1] * 30 + 220, 30, 30);
      }
    } else if (equal(iPiece)) {
      for (int i = 0; i < 4; i++) {
        rect(shape[i][0] * 30 + 440, shape[i][1] * 30 + 235, 30, 30);
      }
    } else {
      for (int i = 0; i < 4; i++) {
        rect(shape[i][0] * 30 + 456, shape[i][1] * 30 + 220, 30, 30);
      }
    }
  }

  public void showNext3() {
    fill(r, g, b);
    if (equal(oPiece)) {
      for (int i = 0; i < 4; i++) {
        rect(shape[i][0] * 30 + 440, shape[i][1] * 30 + 310, 30, 30);
      }
    } else if (equal(iPiece)) {
      for (int i = 0; i < 4; i++) {
        rect(shape[i][0] * 30 + 440, shape[i][1] * 30 + 325, 30, 30);
      }
    } else {
      for (int i = 0; i < 4; i++) {
        rect(shape[i][0] * 30 + 456, shape[i][1] * 30 + 310, 30, 30);
      }
    }
  }

  public void showNext4() {
    fill(r, g, b);
    if (equal(oPiece)) {
      for (int i = 0; i < 4; i++) {
        rect(shape[i][0] * 30 + 440, shape[i][1] * 30 + 400, 30, 30);
      }
    } else if (equal(iPiece)) {
      for (int i = 0; i < 4; i++) {
        rect(shape[i][0] * 30 + 440, shape[i][1] * 30 + 415, 30, 30);
      }
    } else {
      for (int i = 0; i < 4; i++) {
        rect(shape[i][0] * 30 + 456, shape[i][1] * 30 + 400, 30, 30);
      }
    }
  }

  public void showNext5() {
    fill(r, g, b);
    if (equal(oPiece)) {
      for (int i = 0; i < 4; i++) {
        rect(shape[i][0] * 30 + 440, shape[i][1] * 30 + 490, 30, 30);
      }
    } else if (equal(iPiece)) {
      for (int i = 0; i < 4; i++) {
        rect(shape[i][0] * 30 + 440, shape[i][1] * 30 + 505, 30, 30);
      }
    } else {
      for (int i = 0; i < 4; i++) {
        rect(shape[i][0] * 30 + 456, shape[i][1] * 30 + 490, 30, 30);
      }
    }
  }

  public void showHeld() {
    fill(r, g, b);
    if (hasHeld) {
      fill(100);
    }
    if (equal(oPiece)) {
      for (int i = 0; i < 4; i++) {
        rect(originalShape[i][0] * 30 - 60, originalShape[i][1] * 30 + 130, 30, 30);
      }
    } else if (equal(iPiece)) {
      for (int i = 0; i < 4; i++) {
        rect(originalShape[i][0] * 30 - 60, originalShape[i][1] * 30 + 145, 30, 30);
      }
    } else {
      for (int i = 0; i < 4; i++) {
        rect(originalShape[i][0] * 30 - 44, originalShape[i][1] * 30 + 130, 30, 30);
      }
    }
  }

  public void showGhost() {
    fill(r/2, g/2, b/2);
    for (int i = 0; i < 20; i++) {
      if (checkDown(background)) {
        moveShape("down");
      }
    }
    for (int i = 0; i < 4; i++) {
      rect(shape[i][0] * 30 + 190, shape[i][1] * 30 + 50, 30, 30);
    }
  }

  public void moveDown(int level) {
    if (counter % (76 - 3 * level) == 0) {
      moveShape("down");
    }
    counter ++;
  }

  public boolean checkSide(String direction) {
    switch(direction) {
    case "right":
      for (int i = 0; i < 4; i++) {
        if (shape[i][0] > 8) {
          return false;
        }
      }
      break;
    case "left":
      for (int i = 0; i < 4; i++) {
        if (shape[i][0] < 1) {
          return false;
        }
      }
      break;
    case "down":
      for (int i = 0; i < 4; i++) {
        if (shape[i][1] > 18) {
          active = false;
          return false;
        }
      }
      break;
    case "up":
      for (int i = 0; i < 4; i++) {
        if (shape[i][1] > 18) {
          active = false;
          return false;
        }
      }
      break;
    }
    return true;
  }

  public void moveShape(String direction) {
    if (checkSide(direction)) {
      if (direction == "right" && checkRight(background)) {
        for (int i = 0; i < 4; i++) {
          shape[i][0]++;
        }
      }
      if (direction == "left" && checkLeft(background)) {
        for (int i = 0; i < 4; i++) {
          shape[i][0]--;
        }
      }
      if (direction == "down") {
        for (int i = 0; i < 4; i++) {
          shape[i][1]++;
        }
      }
      if (direction == "up") {
        for (int i = 0; i < 20; i++) {
          if (checkDown(background)) {
            moveShape("down");
            score++;
          }
        }
      }
    }
  }

  public boolean checkDown(Background b) {
    for (int i = 0; i < 4; i++) {
      x = shape[i][0];
      y = shape[i][1];
      if (x >= 0 && x < 10 && y >= 0 && y < 19) {
        for (int j = 0; j < 3; j++) {
          if (b.colors[x][y + 1][j] != 0) {
            return false;
          }
        }
      }
    }
    return true;
  }

  public boolean checkLeft(Background b) {
    for (int i = 0; i < 4; i++) {
      x = shape[i][0];
      y = shape[i][1];
      if (x >= 1 && x < 10 && y >= 0 && y < 19) {
        for (int j = 0; j < 3; j++) {
          if (b.colors[x - 1][y][j] != 0) {
            return false;
          }
        }
      }
    }
    return true;
  }

  public boolean checkRight(Background b) {
    for (int i = 0; i < 4; i++) {
      x = shape[i][0];
      y = shape[i][1];
      if (x >= 0 && x < 9 && y >= 0 && y < 19) {
        for (int j = 0; j < 3; j++) {
          if (b.colors[x + 1][y][j] != 0) {
            return false;
          }
        }
      }
    }
    return true;
  }

  public boolean checkRotationCCW() {
    int[][] tempShape = new int[4][2];
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 2; j++) {
        tempShape[i][j] = shape[i][j];
      }
    }
    int[][] rotatedShape = new int[4][2];
    tempRotationCount = rotationCount;
    for (int j = 0; j < 2; j++) {
      if (shape != oPiece) {
        if (tempRotationCount % 4 == 0) {
          for (int i = 0; i < 4; i++) {
            rotatedShape[i][0] = originalShape[i][1] - tempShape[1][0];
            rotatedShape[i][1] = -originalShape[i][0] - tempShape[1][1];
          }
        } else if (tempRotationCount % 4 == 1 || tempRotationCount % 4 == -3) {
          for (int i = 0; i < 4; i++) {
            rotatedShape[i][0] = -originalShape[i][0] - tempShape[1][0];
            rotatedShape[i][1] = -originalShape[i][1] - tempShape[1][1];
          }
        } else if (tempRotationCount % 4 == 2 || tempRotationCount % 4 == -2) {
          for (int i = 0; i < 4; i++) {
            rotatedShape[i][0] = -originalShape[i][1] - tempShape[1][0];
            rotatedShape[i][1] = originalShape[i][0] - tempShape[1][1];
          }
        } else if (tempRotationCount % 4 == 3 || tempRotationCount % 4 == -1) {
          for (int i = 0; i < 4; i++) {
            rotatedShape[i][0] = originalShape[i][0] - tempShape[1][0];
            rotatedShape[i][1] = originalShape[i][1] - tempShape[1][1];
          }
        }
      }
      for (int i = 0; i < 4; i++) {
        for (int k = 0; k < 2; k++) {
          tempShape[i][k] = rotatedShape[i][k];
        }
      }
    }

    for (int i = 0; i < 4; i++) {
      x = tempShape[i][0];
      y = tempShape[i][1];
      if (x >= 0 && x < 10 && y >= 0 && y < 19) {
        for (int j = 0; j < 3; j++) {
          if (background.colors[x][y + 1][j] != 0) {
            return false;
          }
        }
      }
    }

    for (int i = 0; i < 4; i++) {
      if (tempShape[i][0] < 0 || tempShape[i][0] > 9 || tempShape[i][1] > 19) {
        return false;
      }
    }

    return true;
  }

  public void rotateCCW() {
    int[][] rotatedShape = new int[4][2];

    if (shape != oPiece) {
      if (rotationCount % 4 == 0) {
        for (int i = 0; i < 4; i++) {
          rotatedShape[i][0] = originalShape[i][1] - shape[1][0];
          rotatedShape[i][1] = -originalShape[i][0] - shape[1][1];
        }
      } else if (rotationCount % 4 == 1 || rotationCount % 4 == -3) {
        for (int i = 0; i < 4; i++) {
          rotatedShape[i][0] = -originalShape[i][0] - shape[1][0];
          rotatedShape[i][1] = -originalShape[i][1] - shape[1][1];
        }
      } else if (rotationCount % 4 == 2 || rotationCount % 4 == -2) {
        for (int i = 0; i < 4; i++) {
          rotatedShape[i][0] = -originalShape[i][1] - shape[1][0];
          rotatedShape[i][1] = originalShape[i][0] - shape[1][1];
        }
      } else if (rotationCount % 4 == 3 || rotationCount % 4 == -1) {
        for (int i = 0; i < 4; i++) {
          rotatedShape[i][0] = originalShape[i][0] - shape[1][0];
          rotatedShape[i][1] = originalShape[i][1] - shape[1][1];
        }
      }
      shape = rotatedShape;
    }
  }

  public boolean checkRotationCW() {
    int[][] tempShape = new int[4][2];
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 2; j++) {
        tempShape[i][j] = shape[i][j];
      }
    }
    int[][] rotatedShape = new int[4][2];
    tempRotationCount = rotationCount;
    for (int j = 0; j < 2; j++) {
      if (shape != oPiece) {
        if (tempRotationCount % 4 == 0) {
          for (int i = 0; i < 4; i++) {
            rotatedShape[i][0] = -originalShape[i][1] - tempShape[1][0];
            rotatedShape[i][1] = originalShape[i][0] - tempShape[1][1];
          }
        } else if (rotationCount % 4 == 1 || rotationCount % 4 == -3) {
          for (int i = 0; i < 4; i++) {
            rotatedShape[i][0] = originalShape[i][0] - tempShape[1][0];
            rotatedShape[i][1] = originalShape[i][1] - tempShape[1][1];
          }
        } else if (rotationCount % 4 == 2 || rotationCount % 4 == -2) {
          for (int i = 0; i < 4; i++) {
            rotatedShape[i][0] = originalShape[i][1] - tempShape[1][0];
            rotatedShape[i][1] = -originalShape[i][0] - tempShape[1][1];
          }
        } else if (rotationCount % 4 == 3 || rotationCount % 4 == -1) {
          for (int i = 0; i < 4; i++) {
            rotatedShape[i][0] = -originalShape[i][0] - tempShape[1][0];
            rotatedShape[i][1] = -originalShape[i][1] - tempShape[1][1];
          }
        }
      }

      for (int i = 0; i < 4; i++) {
        for (int k = 0; k < 2; k++) {
          tempShape[i][k] = rotatedShape[i][k];
        }
      }
    }

    for (int i = 0; i < 4; i++) {
      x = tempShape[i][0];
      y = tempShape[i][1];
      if (x >= 0 && x < 10 && y >= 0 && y < 19) {
        for (int j = 0; j < 3; j++) {
          if (background.colors[x][y + 1][j] != 0) {
            return false;
          }
        }
      }
    }

    for (int i = 0; i < 4; i++) {
      if (tempShape[i][0] < 0 || tempShape[i][0] > 9 || tempShape[i][1] > 19) {
        return false;
      }
    }

    return true;
  }

  public void rotateCW() {
    int[][] rotatedShape = new int[4][2];

    if (shape != oPiece) {
      if (rotationCount % 4 == 0) {
        for (int i = 0; i < 4; i++) {
          rotatedShape[i][0] = -originalShape[i][1] - shape[1][0];
          rotatedShape[i][1] = originalShape[i][0] - shape[1][1];
        }
      } else if (rotationCount % 4 == 1 || rotationCount % 4 == -3) {
        for (int i = 0; i < 4; i++) {
          rotatedShape[i][0] = originalShape[i][0] - shape[1][0];
          rotatedShape[i][1] = originalShape[i][1] - shape[1][1];
        }
      } else if (rotationCount % 4 == 2 || rotationCount % 4 == -2) {
        for (int i = 0; i < 4; i++) {
          rotatedShape[i][0] = originalShape[i][1] - shape[1][0];
          rotatedShape[i][1] = -originalShape[i][0] - shape[1][1];
        }
      } else if (rotationCount % 4 == 3 || rotationCount % 4 == -1) {
        for (int i = 0; i < 4; i++) {
          rotatedShape[i][0] = -originalShape[i][0] - shape[1][0];
          rotatedShape[i][1] = -originalShape[i][1] - shape[1][1];
        }
      }
      shape = rotatedShape;
    }
  }

  public boolean checkRotation180() {
    int[][] tempShape = new int[4][2];
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 2; j++) {
        tempShape[i][j] = shape[i][j];
      }
    }
    int[][] rotatedShape = new int[4][2];
    tempRotationCount = rotationCount;
    for (int j = 0; j < 2; j++) {
      if (shape != oPiece) {
        if (tempRotationCount % 4 == 0) {
          for (int i = 0; i < 4; i++) {
            rotatedShape[i][0] = -originalShape[i][1] - tempShape[1][0];
            rotatedShape[i][1] = originalShape[i][0] - tempShape[1][1];
          }
        } else if (rotationCount % 4 == 1 || rotationCount % 4 == -3) {
          for (int i = 0; i < 4; i++) {
            rotatedShape[i][0] = originalShape[i][0] - tempShape[1][0];
            rotatedShape[i][1] = originalShape[i][1] - tempShape[1][1];
          }
        } else if (rotationCount % 4 == 2 || rotationCount % 4 == -2) {
          for (int i = 0; i < 4; i++) {
            rotatedShape[i][0] = originalShape[i][1] - tempShape[1][0];
            rotatedShape[i][1] = -originalShape[i][0] - tempShape[1][1];
          }
        } else if (rotationCount % 4 == 3 || rotationCount % 4 == -1) {
          for (int i = 0; i < 4; i++) {
            rotatedShape[i][0] = -originalShape[i][0] - tempShape[1][0];
            rotatedShape[i][1] = -originalShape[i][1] - tempShape[1][1];
          }
        }
      }
      
      for (int i = 0; i < 4; i++) {
        for (int k = 0; k < 2; k++) {
          tempShape[i][k] = rotatedShape[i][k];
        }
      }
    }

    tempRotationCount--;

    for (int j = 0; j < 2; j++) {
      if (shape != oPiece) {
        if (tempRotationCount % 4 == 0) {
          for (int i = 0; i < 4; i++) {
            rotatedShape[i][0] = -originalShape[i][1] - tempShape[1][0];
            rotatedShape[i][1] = originalShape[i][0] - tempShape[1][1];
          }
        } else if (rotationCount % 4 == 1 || rotationCount % 4 == -3) {
          for (int i = 0; i < 4; i++) {
            rotatedShape[i][0] = originalShape[i][0] - tempShape[1][0];
            rotatedShape[i][1] = originalShape[i][1] - tempShape[1][1];
          }
        } else if (rotationCount % 4 == 2 || rotationCount % 4 == -2) {
          for (int i = 0; i < 4; i++) {
            rotatedShape[i][0] = originalShape[i][1] - tempShape[1][0];
            rotatedShape[i][1] = -originalShape[i][0] - tempShape[1][1];
          }
        } else if (rotationCount % 4 == 3 || rotationCount % 4 == -1) {
          for (int i = 0; i < 4; i++) {
            rotatedShape[i][0] = -originalShape[i][0] - tempShape[1][0];
            rotatedShape[i][1] = -originalShape[i][1] - tempShape[1][1];
          }
        }
      }
      for (int i = 0; i < 4; i++) {
        for (int k = 0; k < 2; k++) {
          tempShape[i][k] = rotatedShape[i][k];
        }
      }
    }

    for (int i = 0; i < 4; i++) {
      x = tempShape[i][0];
      y = tempShape[i][1];
      if (x >= 0 && x < 10 && y >= 0 && y < 19) {
        for (int j = 0; j < 3; j++) {
          if (background.colors[x][y + 1][j] != 0) {
            return false;
          }
        }
      }
    }

    for (int i = 0; i < 4; i++) {
      if (tempShape[i][0] < 0 || tempShape[i][0] > 9 || tempShape[i][1] > 19) {
        return false;
      }
    }

    return true;
  }

  public boolean equal(Shape s) {
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 2; j++) {
        if (originalShape[i][j] != s.originalShape[i][j]) {
          return false;
        }
      }
    }
    return true;
  }

  public boolean equal(int[][] a) {
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 2; j++) {
        if (originalShape[i][j] != a[i][j]) {
          return false;
        }
      }
    }
    return true;
  }

  public void transferAttributes(Shape s) {
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 2; j++) {
        shape[i][j] = s.shape[i][j];
      }
    }
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 2; j++) {
        originalShape[i][j] = s.originalShape[i][j];
      }
    }
    r = s.r;
    g = s.g;
    b = s.b;
    x = s.x;
    y = s.y;
    rotationCount = 0;
    tempRotationCount = 0;
    counter = 0;
  }

  public int getShapeNum() {
    return shapeNum;
  }

  public String toString() {
    String result = "(" + shape[0][0] + ", " + shape[0][1] + "), " + "(" +  shape[1][0] + ", " + shape[1][1] + "), " + "(" + shape[2][0] + ", " + shape[2][1] + "), " + "(" + shape[3][0] + ", " + shape[3][1] + ")";
    return result;
  }
}


  public void settings() { size(1000, 700); }

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Pdetris" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
