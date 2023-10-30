package rust.tmx;

public class move extends basic {
 private String target;
 public boolean unload;
 public move(int x0, int y0, int w0, int h0, point g,point to) {
  super(x0, y0, w0, h0, g);
  target=to.id();
 }
 public move(int x0, int y0, int w0, int h0, triggers triggers,point to) {
  super(x0, y0, w0, h0, triggers);
  target=to.id();
 }
 protected void before() throws Exception {
  super.before();
  m.append("target",target);
  m.append("unload",unload);
 }
}
