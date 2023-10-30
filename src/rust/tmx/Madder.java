package rust.tmx;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

public class Madder {
 private triggers m;
 private float x;
 private float y;
 private float w;
 private boolean safe;
 private String unit;
 private unitRemove rest;
 private HashMap<Integer,unitAdd> map;
 private HashMap<String,unitDetect> eqz;
 private int team;
 public Madder(triggers trg, float w0) {
  this(trg, trg.getPos(w0, w0), trg.y, w0, unitType.other_dummyNonUnitWithTeam, -1);
  safe = true;
 }
 public Madder(triggers trg, float x0, float y0, float w0, String type, int t) {
  m = trg;
  x = x0;
  y = y0;
  w = w0;
  unit = type;
  map = new HashMap();
  eqz = new HashMap();
  team = t;
 }
 public unitAdd add(int i) {
  Integer obj= Integer.valueOf(i);
  HashMap<Integer, unitAdd> table=map;
  unitAdd add=table.get(obj);
  if (add == null) {
   spawnUnit sp=new spawnUnit(unit, i);
   float w0=w / 2;
   add = new unitAdd(x + w0, y + w0, m, team, sp);
   table.put(obj, add);
  }
  return add;
 }
 public unitRemove rest() {
  unitRemove re=rest;
  if (re == null) {
   float w0=w;
   rest = re = new unitRemove(x, y, w0, w0, m);
   if (!safe)re.team = team;
  }
  return re;
 }
 public unitDetect of(int min, int max) {
  StringBuilder buff=m.warp;
  buff.setLength(0);
  if (min > 0)buff.append(min);
  if (max >= 0) {
   buff.append('&');
   buff.append(max);
  }
  String key=buff.toString();
  HashMap<String, unitDetect> table=eqz;
  unitDetect de=table.get(key);
  if (de == null) {
   float w0=w;
   de = new unitDetect(x, y, w0, w0, m);
   de.minUnits=min;
   de.maxUnits=max;
   if (!safe) {
    de.unitType = unit;
    de.team = team;
   }
   table.put(key, de);
  }
  return de;
 }
}
