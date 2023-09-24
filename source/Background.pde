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
