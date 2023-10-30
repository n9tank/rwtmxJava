package rust.tmx;

import java.io.BufferedWriter;

public class point {
 protected String id;
 protected triggers m;
 protected float x;
 protected float y;
 protected boolean unxy;
 public point(float x0, float y0, point g) {
  x = g.x + x0;
  y = g.y + y0;
  triggers trg = g.m;
  m=trg;
  trg.queue.add(this);
 }
 public point(float x0, float y0, triggers triggers) {
  x = x0;
  y = y0;
  m = triggers;
  triggers.queue.add(this);
 }
 protected String id() {
  String ids=id;
  if (id == null)id = ids = m.id(++m.mid, true);
  return ids;
 }
 public Object call() throws Exception {
  before();
  m.endObj();
  return null;
 }
 protected void before() throws Exception {
  triggers triggers=m;
  BufferedWriter buff=triggers.mbuff;
  buff.append("<object type=\"");
  String type=getClass().getSimpleName();
  buff.append(type);
  String ids=id;
  if (ids != null) {
   buff.append("\"name=\"");
   buff.append(ids);
  }
  if (!unxy) {
   buff.append("\"x=\"");
   buff.append(triggers.floatNum(x));
   buff.append("\"y=\"");
   buff.append(triggers.floatNum(y));
  }
  buff.append("\"");
 }
}
