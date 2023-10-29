package rust.tmx;

import java.io.BufferedWriter;
import java.io.IOException;

public class point {
 protected String id;
 protected triggers m;
 protected float x;
 protected float y;
 public point(int x0, int y0, point g) {
  x = g.x + x0;
  y = g.y + y0;
  m = g.m;
 }
 public point(int x0, int y0, triggers triggers) {
  x = x0;
  y = y0;
  m = triggers;
 }
 protected String id() {
  String ids=id;
  if (id == null)id = ids = m.id();
  return ids;
 }
 public static String floatNum(float f) {
  int to=(int)f;
  String s;
  if (f == to)s = String.valueOf(to);
  else s = String.valueOf(f);
  return s;
 }
 public void apply() {
  m.queue.add(this);
 }
 public Object call() throws Exception {
  before();
  m.endObj();
  return null;
 }
 public void before() throws Exception {
  triggers triggers=m;
  BufferedWriter buff=triggers.mbuff;
  buff.append("<object type=\"");
  String type=getClass().getSimpleName();
  buff.append(type);
  String ids=id;
  if (ids != null) {
   buff.append("\"name=\"");
   buff.append(ids);
   buff.append("\"");
  }
  buff.append("\"x=\"");
  buff.append(floatNum(x));
  buff.append("\"y=\"");
  buff.append(floatNum(y));
  buff.append("\"");
 }
}
