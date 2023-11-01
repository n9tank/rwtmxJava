package rust.tmx.memory;
import java.io.IOException;
import rust.tmx.spawnUnit;
import rust.tmx.triggers;
import rust.tmx.unitAdd;
import rust.tmx.unitDetect;
import rust.tmx.unitObjects;
import rust.tmx.unitRemove;
import rust.tmx.unitType;

public class McheckBox {
 private float x;
 private float y;
 private float w;
 private int team;
 private spawnUnit type;
 private triggers m;
 private unitDetect isFalse;
 private unitDetect nohasUnit;
 private unitRemove remove;
 private unitAdd setTag;
 public boolean safe;
 public McheckBox(triggers trg) {
  this(trg.getPos(0, 0), trg.y, 1f, 0, new spawnUnit(unitType.other_dummyNonUnitWithTeam, 1), trg);
  safe = true;
 }
 public McheckBox(float x0, float y0, int t, triggers trg) {  
  this(x0, y0, 20f, t, new spawnUnit(unitType.sea_lightSub, 1), trg);
  type.offsetHeight = -8;
  safe = true;
 }
 public McheckBox(float x0, float y0, float w0, int t, spawnUnit unit, triggers trg) {
  m = trg;
  type = unit;
  team = t;
  x = x0;
  y = y0;
  w = w0;
 }
 public void set(unitObjects obj) throws IOException {
  obj.add(x, y, team, type.type);
 }
 public unitAdd set() {
  unitAdd add=setTag;
  if (add == null) {
   setTag = add = new unitAdd(x, y, m, team, type);
   add.resetActivationAfter = "1s";
   if (!safe)add.append(add.link, nohasUnit());
  }
  return add;
 }
 public unitDetect False() {
  unitDetect de=isFalse;
  if (de == null) {
   isFalse = de = add();
   if(y>=0){
    de.onlyIdle = true;
   }
  }
  return de;
 }
 public unitDetect nohasUnit() {
  unitDetect de=nohasUnit;
  if (de == null) {
   nohasUnit = de = add();
   de.maxUnits = 0;
  }
  return de;
 }
 private unitDetect add() {
  float w0=w;
  unitDetect de = new unitDetect(x, y, w0, w0, m);
  de.resetActivationAfter = "1s";
  if (!safe) {
   de.team = team;
   de.unitType = type.type;
  }
  return de;
 }
 public unitRemove remove() {
  if (y>=0&&safe)throw new RuntimeException("safe=true");
  unitRemove add=remove;
  if (add == null) {
   float w0=w;
   remove = add = new unitRemove(x, y, w0, w0, m);
   add.resetActivationAfter = "1s";
   if (!safe)add.team = team;
  }
  return add;
 }
}
