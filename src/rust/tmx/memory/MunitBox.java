package rust.tmx.memory;
import java.io.IOException;
import rust.tmx.basic;
import rust.tmx.spawnUnit;
import rust.tmx.triggers;
import rust.tmx.unitAdd;
import rust.tmx.unitDetect;
import rust.tmx.unitObjects;
import rust.tmx.unitRemove;
import rust.tmx.unitType;

public class MunitBox {
 public float x;
 public float y;
 public float w;
 public float h;
 public int team;
 public triggers m;
 public boolean safe;
 public boolean keep;
 public String unit;
 protected unitAdd setTag;
 protected unitRemove remove;
 protected unitDetect hasUnit;
 protected unitDetect nohasUnit;
 protected boolean nofix;
 public MunitBox(triggers trg) {
  this(trg.getPos(1f, 1f), trg.getY(), 1f, 1f, 0, unitType.other_dummyNonUnitWithTeam, trg);
  safe = true;
 }
 public MunitBox(float x0, float y0, float w0, float h0, int t, String type, triggers trg) {
  m = trg;
  x = x0;
  y = y0;
  w = w0;
  h = h0;
  unit = type;
  team = t;
 }
 public spawnUnit unit(int i) {
  return new spawnUnit(unit, i);
 }
 public unitAdd add(spawnUnit unit) {
  return add(x, y, unit);
 }
 public unitAdd add(float x0, float y0, spawnUnit arg) {
  if (!nofix) {
   x0 += w * 0.5f;
   y0 += w * 0.5f;
  }
  unitAdd add = new unitAdd(x0, y0, team, m, arg);
  add.resetActivationAfter = "0";
  return add;
 }
 public void link(basic re) {
  unitDetect de = hasUnit;
  if (de != null) {
   re.linkAnd(de);
  } else {
   re.append(re.dlink, nohasUnit());
  }
 }
 public void linkNot(basic re) {
  unitDetect de = hasUnit;
  if (de != null) {
   re.append(re.dlink, de);
  } else {
   re.linkAnd(nohasUnit());
  }
 }
 public void set(unitObjects obj) throws IOException {
  set(x, y, obj);
 }
 protected void set(float x0, float y0, unitObjects obj) throws IOException {
  if (!nofix) {
   x0 += w * 0.5f;
   y0 += w * 0.5f;
  }
  obj.add(x0, y0, team, unit);
 }
 public unitAdd set() {
  return set(x, y, unit(1));
 }
 protected unitAdd set(float x0, float y0, spawnUnit unit) {
  unitAdd add=setTag;
  if (add == null) {
   setTag = add = add(x0, y0, unit);
   if (keep)linkNot(setTag);
  }
  return add;
 }
 public unitDetect detect(float x, float y, float w0, float h0) {
  unitDetect de = new unitDetect(x, y, w0, h0, m);
  de.resetActivationAfter = "0";
  if (!safe) {
   de.team = team;
   de.unitType = unit;
  }
  return de;
 }
 public unitDetect detect() {
  return detect(x, y, w, h);
 }
 public unitDetect hasUnit() {
  unitDetect de=hasUnit;
  if (de == null) {
   hasUnit = de = detect(x, y, w, h);
  }
  return de;
 }
 public unitDetect nohasUnit() {
  unitDetect de=nohasUnit;
  if (de == null) {
   nohasUnit = de = detect(x, y, w, h);
   de.maxUnits = 0;
  }
  return de;
 }
 public unitRemove remove() {
  unitRemove re=remove;
  if (re == null) {
   remove = re = new unitRemove(x, y, w, h, m);
   re.resetActivationAfter = "0";
   if (!safe)re.team = team;
  }
  return re;
 }
 public void applyIf() {
  triggers trg=m;
  trg.apply(hasUnit);
  trg.apply(nohasUnit);
 }
 public void applyDo() {
  triggers trg=m;
  trg.apply(remove);
  trg.apply(setTag);
 }
 public void apply() {
  applyIf();
  applyDo();
 }
}
