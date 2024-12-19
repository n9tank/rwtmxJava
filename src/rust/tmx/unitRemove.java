package rust.tmx;

public class unitRemove extends basic {
 public boolean onlyIfEmpty;
 public unitRemove(float x0, float y0, float w, float h, point g) {
  super(x0, y0, w, h, g);
  unBox = false;
 }
 public unitRemove(float x0, float y0, float w, float h, triggers triggers) {
  super(x0, y0, w, h, triggers);
  unBox = false;
 }
 protected void before() throws Exception {
  super.before();
  m.append("onlyIfEmpty", onlyIfEmpty);
 }
}
