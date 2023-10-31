package rust.tmx;

import java.io.BufferedWriter;

public class point implements Cloneable {
 protected String id;
 protected triggers m;
 protected float x;
 protected float y;
 public point(float x0, float y0, point g) {
  x = g.x + x0;
  y = g.y + y0;
  triggers trg = g.m;
  m = trg;
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
  if (id == null)id = ids = m.id(++m.mid);
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
  buff.write("<object type=\"");
  String type=getClass().getSimpleName();
  buff.write(type);
  String ids=id;
  if (ids != null) {
   buff.write("\"name=\"");
   buff.write(ids);
  }
  buff.write("\"x=\"");
  buff.write(triggers.floatNum(x));
  buff.write("\"y=\"");
  buff.write(triggers.floatNum(y));
  buff.write("\"");
 }
 public point asPonit() {
  return new point(x, y, m);
 }
 public point clone() throws CloneNotSupportedException {
  point po=(point)super.clone();
  m.queue.add(po);
  return po;
 }
}
