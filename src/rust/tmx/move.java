package rust.tmx;

public class move extends basic {
 private String target;
 public boolean unload;
 public move(float x0, float y0, float w0, float h0, point g, point to) {
  super(x0, y0, w0, h0, g);
  target = to.id();
 }
 public move(float x0, float y0, float w0, float h0, triggers triggers, point to) {
  super(x0, y0, w0, h0, triggers);
  target = to.id();
 }
 protected void before() throws Exception {
  super.before();
  m.append("target", target);
  if (unload)m.append("unload", "");
 }
}
