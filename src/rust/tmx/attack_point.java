package rust.tmx;

public class attack_point extends point{
 public attack_point(float x0, float y0,point g) {
  super(x0, y0, g);
  isName=true;
 }
 public attack_point(float x0, float y0,triggers triggers) {
  super(x0, y0, triggers);
  isName=true;
 }
}
