package rust.tmx;

public class rotate extends fall {
 private float dir;
 public rotate(float x0, float y0, float w0, float h0, point g, float dir) {
  super(x0, y0, w0, h0, g);
  this.dir = dir;
 }
 public rotate(float x0, float y0, float w0, float h0, triggers triggers, float dir) {
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
