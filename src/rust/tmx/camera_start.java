package rust.tmx;

public class camera_start extends point{
public int zoomTo;
 public camera_start(int x0, int y0,point g) {
  super(x0, y0, g);
 }
 public camera_start(int x0, int y0,triggers triggers) {
  super(x0, y0, triggers);
 }
 public void before() throws Exception {
  super.before();
  m.append("zoomTo",zoomTo);
 }
}
