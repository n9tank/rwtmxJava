package rust.tmx.memory;
import rust.tmx.changeCredits;
import rust.tmx.spawnUnit;
import rust.tmx.triggers;
import rust.tmx.unitAdd;
import rust.tmx.unitDetect;
import rust.tmx.unitRemove;
import rust.tmx.unitType;

public class Mbuy extends MunitLogic {
 public Mbuy(float x0, float y0, int t, triggers trg) {
  this(x0, y0, 40f, 40f, t, unitType.buid_turret, trg);
  safe = true;
 }
 public Mbuy(float x0, float y0, float w, float h, int t, String type, triggers trg) {
  super(x0, y0, w, h, t, type, trg);
 }
 public void init() {
  linkBy(set());
  unitRemove re=remove();
  linkAnd(re);
 }
 public changeCredits sub(int i) {
  changeCredits ch = new changeCredits(team, m);
  ch.resetActivationAfter = "0";
  ch.add = i;
  return ch;
 }
 public unitDetect[] form(Mswitch sw, int ...args) {
  if (sw.horizontal)throw new RuntimeException("horizontal=true");
  unitDetect[] list=sw.list;
  int l=list.length;
  unitDetect[] ru=new unitDetect[l];
  spawnUnit sp=new spawnUnit(unitType.sea_lightSub, 1);
  sp.offsetHeight = -8;
  unitRemove re=new unitRemove(sw.x + 40, sw.y, 20, (l + 1) * 40, m);
  unitAdd adds[]=new unitAdd[l];
  unitDetect des[]=new unitDetect[l];
  for (int i=0;i < l;i++) {
   int pr=-args[i];
   changeCredits sub=sub(pr);
   unitDetect de=list[i];
   unitAdd add=new unitAdd(40, 0, -2, de, sp);
   adds[i] = add;
   re.append(re.dlink, de);
   add.linkAnd(de);
   sub.linkAnd(de);
   add.resetActivationAfter = "0";
   unitDetect de2=new unitDetect(40, 0, 20, 20, de);
   ru[i] = de2;
   changeCredits ok=sub(pr);
   linkAnd(ok);
   ok.linkAnd(de2);
   sub.append(sub.dlink, de2);
   changeCredits adc=sub(-pr);
   adc.linkAnd(de2);
   adc.append(adc.dlink, de);
   m.apply(de2);
   m.apply(add);
   m.apply(sub);
   m.apply(adc);
   m.apply(ok);
   des[i] = de2;
   de2.resetActivationAfter = "0";
  }
  for (int i=0;i < l;i++) {
   unitAdd add=adds[i];
   for (int j=0;j < l;j++) {
    add.append(add.dlink, des[j]);
   }
  }
  m.apply(re);
  return ru;
 }
 public void sing(int i) {
  McheckBox checkbox=new McheckBox(x + (w * 0.5f) - 7, y + h, team, m);
  checkbox.hasUnit().warmup = "0";
  unitAdd add=checkbox.set();
  linkAnd(add);
  linkAnd(sub(-i));
  changeCredits ad=sub(i);
  checkbox.linkBy(ad);
  unitRemove re=checkbox.remove();
  checkbox.linkBy(re);
  checkbox.applyIf();
  apply();
  checkbox.applyDo();
 }
 protected void dosome(unitDetect de) {
  de.onlyEmptyQueue = true;
 }
}
