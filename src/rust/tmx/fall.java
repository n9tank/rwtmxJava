package rust.tmx;

import java.io.BufferedWriter;

public class fall extends point {
 protected boolean unBox;
 protected float w;
 protected float h;
 public fall(int x0, int y0, int w0, int h0, point g) {
  super(x0, y0, g);
  w = w0;
  h = h0;
  useId=false;
 }
 public fall(int x0, int y0, int w0, int h0, triggers triggers) {
  super(x0, y0, triggers);
  w = w0;
  h = h0;
  useId=false;
 }
 protected void before() throws Exception {
  super.before();
  if(!unBox){
  BufferedWriter buff=m.mbuff;
  buff.append("\"width=\"");
  buff.append(triggers.floatNum(w));
  buff.append("\"height=\"");
  buff.append(triggers.floatNum(h));
  buff.append("\"");
  }
 }
}
