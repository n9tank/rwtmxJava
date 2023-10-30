package rust.tmx;

public class rotate extends fall {
 private int dir;
 public rotate(int x0, int y0, int w0, int h0, point g, int dir) {
  super(x0, y0, w0, h0, g);
  this.dir = dir;
 }
 public rotate(int x0, int y0, int w0, int h0, triggers triggers, int dir) {
  super(x0, y0, w0, h0, triggers);
  this.dir = dir;
 }
 protected void before() throws Exception {
  super.before();
  m.append("dir", dir, 0);
 }
}
