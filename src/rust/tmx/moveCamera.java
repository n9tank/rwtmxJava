package rust.tmx;

public class moveCamera extends basic{
 public moveCamera(float x0, float y0, point g) {
  super(x0, y0, 0f, 0f, g);
  unBox = true;
 }
 public moveCamera(float x0, float y0, triggers triggers) {
  super(x0, y0, 0f, 0f, triggers);
  unBox = true;
 }
}
