import java.util.Random; //<>//

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
