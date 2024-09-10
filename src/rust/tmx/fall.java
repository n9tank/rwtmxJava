package rust.tmx;

import java.io.BufferedWriter;

public class fall extends point {
 protected boolean unBox;
 protected float w;
 protected float h;
 public fall(float x0, float y0, float w0, float h0, point g) {
  super(x0, y0, g);
  w = w0;
  h = h0;
 }
 public fall(float x0, float y0, float w0, float h0, triggers triggers) {
  super(x0, y0, triggers);
  w = w0;
  h = h0;
 }
 protected void before() throws Exception {
  super.before();
  if(!unBox){
  BufferedWriter buff=m.mbuff;
  buff.write("width=\"");
  buff.write(triggers.floatNum(w));
  buff.write("\"height=\"");
  buff.write(triggers.floatNum(h));
  buff.write("\"");
  }
 }
}
