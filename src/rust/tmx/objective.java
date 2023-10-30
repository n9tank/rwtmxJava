package rust.tmx;

public class objective extends basic {
 public objective(int x0, int y0, triggers triggers) {
  super(x0, y0, 0, 0, triggers);
  unBox=true;
 }
 public objective(int x0, int y0, point g) {
  super(x0, y0, 0, 0, g);
  unBox=true;
 }
}
