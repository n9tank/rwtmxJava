package rust.tmx;

import java.io.BufferedWriter;
import java.io.IOException;

public class point {
 protected String id;
 protected triggers m;
 protected float x;
 protected float y;
 protected boolean useId;
 public point(int x0, int y0, point g) {
  x = g.x + x0;
  y = g.y + y0;
  m = g.m;
  useId=true;
 }
 public point(int x0, int y0, triggers triggers) {
  x = x0;
  y = y0;
  m = triggers;
  useId=true;
 }
 protected String id() {
  String ids=id;
  if (id == null)id = ids = m.id();
  return ids;
 }
 public void apply() throws Exception {
  String str;
  if(useId)str=id();
  else str=id;
  if(str==null)m.queue.add(this);
  else call();
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
   buff.append("\"");
  }
  buff.append("\"x=\"");
  buff.append(triggers.floatNum(x));
  buff.append("\"y=\"");
  buff.append(triggers.floatNum(y));
  buff.append("\"");
 }
}
