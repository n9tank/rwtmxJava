package rust.tmx;

public class rotate extends fall {
 public int dir;
 public rotate(int x0, int y0, int w0, int h0, point g) {
  super(x0, y0, w0, h0,g);
 }
 public rotate(int x0, int y0, int w0, int h0, triggers triggers) {
  super(x0, y0, w0, h0, triggers);
 }
 public void before() throws Exception {
  super.before();
  m.append("dir",dir);
 }
}
