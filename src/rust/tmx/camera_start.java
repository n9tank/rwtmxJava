package rust.tmx;

public class camera_start extends point {
 public int zoomTo;
 public camera_start(float x0, float y0, point g) {
  super(x0, y0, g);
  isName = true;
 }
 public camera_start(float x0, float y0, triggers triggers) {
  super(x0, y0, triggers);
  isName = true;
 }
 protected void before() throws Exception {
  super.before();
  triggers trg=m;
  trg.start_p=true;
  trg.append("zoomTo", zoomTo, 0);
 }
}
