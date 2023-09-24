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
