package rust.tmx.memory;
import rust.tmx.triggers;
import rust.tmx.unitRemove;
import rust.tmx.unitAdd;
import rust.tmx.unitDetect;
import rust.tmx.spawnUnit;
import rust.tmx.unitType;
import rust.tmx.objective;
import rust.tmx.basic;

public class Mint {
 int max;
 triggers m;
 float x;
 float y;
 unitDetect topIsEmpty;
 unitDetect topGT1;
 basic toplink;
 unitDetect nohasUnit;
 unitRemove removeUnits[];
 unitAdd rest[];
 unitDetect restlink[];
 public Mint(int mx, triggers trg) {
  max = mx;
  m = trg;
  x = trg.getPos(1f, mx);
  y = trg.getY();
  topIsEmpty = new unitDetect(x, y, 1, 1, trg);
  topIsEmpty.maxUnits = 0;
  topIsEmpty.resetActivationAfter="0";
  topGT1 = new unitDetect(x, y, 1, 1, trg);
  topGT1.minUnits = 2;
  topGT1.resetActivationAfter="0";
  nohasUnit=new unitDetect(x, y, mx, 1, trg);
  nohasUnit.maxUnits=0;
  nohasUnit.resetActivationAfter="0";
  basic bc=new basic(m);
  bc.resetActivationAfter="0";
  bc.linkOr(topGT1);
  bc.linkOr(topIsEmpty);
  bc.append(bc.dlink,nohasUnit);
  removeUnits = new unitRemove[mx];
  unitRemove sub=removeUnits[mx] = sub(mx);
  sub.linkOr(bc);
  for (;--mx >= 0;) {
   unitAdd add=makeRest(mx);
   add.linkAnd(bc);
   add.linkAnd(restlink[mx] = makeRestLink(mx));
   rest[mx] = add;
  }
 }
 public unitAdd makeRest(int i) {
  spawnUnit[] list=new spawnUnit[i];
  for (;--i > 0;) {
   spawnUnit sp=new spawnUnit(unitType.other_dummyNonUnitWithTeam, 1);
   sp.offsetX = i;
   list[i] = sp;
  }
  unitAdd add=new unitAdd(x, y, 0, m, list);
  add.resetActivationAfter = "0";
  return add;
 }
 public unitDetect makeRestLink(int i) {
  unitDetect de=new unitDetect(x, y, max, 1, m);
  de.resetActivationAfter = "0";
  de.minUnits = i;
  de.maxUnits = i;
  return de;
 }
 public unitAdd newAdd(int i){
  unitAdd add=new unitAdd(x, y, 0, m, new spawnUnit(unitType.other_dummyNonUnitWithTeam, i));
  add.resetActivationAfter="0";
  return add;
 }
 public unitRemove sub(int i) {
  unitRemove de=removeUnits[i];
  if (de == null) {
   de = new unitRemove(x, y, i, 1, m);
   de.resetActivationAfter = "0";
   removeUnits[i] = de;
  }
  return de;
 }
 public void apply() {
  triggers trg=m;
  trg.apply(topIsEmpty);
  trg.apply(topGT1);
  trg.apply(nohasUnit);
  trg.apply(toplink);
  for(unitDetect de:restlink)trg.apply(de);
  for(unitRemove de:removeUnits)trg.apply(de);
  for(unitAdd de:rest)trg.apply(de);
 }
}
