package rust.tmx;

public class rotate extends fall {
 private float dir;
 public rotate(int x0, int y0, int w0, int h0, point g, float dir) {
  super(x0, y0, w0, h0, g);
  this.dir = dir;
 }
 public rotate(int x0, int y0, int w0, int h0, triggers triggers, float dir) {
  super(x0, y0, w0, h0, triggers);
  this.dir = dir;
 }
 protected void before() throws Exception {
  super.before();
  triggers trg=m;
  trg.mbuff.append('>');
  trg.append("dir", dir);
 }
}
