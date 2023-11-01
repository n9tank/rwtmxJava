package rust.tmx;

import java.io.BufferedWriter;
import java.util.concurrent.Callable;

public class point implements Cloneable {
 protected String id;
 public triggers m;
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
  triggers trg=m;
  if (id == null)id = ids = trg.id(trg.mid++);
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
 public point clone() throws CloneNotSupportedException {
  triggers trg=m;
  point back=(point)super.clone();
  trg.queue.add(back);
  back.id=null;
  return back;
 }
}
