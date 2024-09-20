package rust.tmx.memory;
import rust.tmx.basic;
import rust.tmx.changeCredits;
import rust.tmx.triggers;
import rust.tmx.unitAdd;
import rust.tmx.unitDetect;
import rust.tmx.unitRemove;
import rust.tmx.unitType;

public class Mshop extends MunitLogic {
 public McheckBox sign;
 public unitDetect[] form;
 public Mshop(float x0, float y0, int t, triggers trg) {
  this(x0, y0, 40f, 40f, t, unitType.buid_turret, trg);
  safe = true;
 }
 public Mshop(float x0, float y0, float w, float h, int t, String type, triggers trg) {
  super(x0, y0, w, h, t, type, trg);
 }
 public void apply() {
  linkBy(set());
  unitRemove re=remove();
  linkAnd(re);
  super.apply();
 }
 public changeCredits add(int i) {
  changeCredits ch = new changeCredits(team, m);
  ch.resetActivationAfter = "0";
  ch.add = i;
  return ch;
 }
 public void form(Mswitch sw, int ...args) {
  sw.apply();
  apply();
  unitDetect[] list=sw.list;
  int l=list.length;
  unitDetect[] ru=new unitDetect[l];
  form = ru;
  float x0=m.getPos(l, 1);
  float y0=m.getY();
  unitRemove re=new unitRemove(x0, y0, l, 1, m);
  unitDetect has=new unitDetect(x0,y0,l,1,m);
  has.resetActivationAfter="0";
  m.apply(has);
  for (int i=0;i < l;i++) {
   int pr=-args[i];
   changeCredits sub=add(pr);
   unitDetect de=list[i];
   MunitBox box=new MunitBox(x0++, y0, 1, 1, team, unitType.other_dummyNonUnitWithTeam, m);
   box.safe = true;
   box.nofix = true;
   unitAdd add=box.set();
   add.append(add.dlink,has);
   re.append(re.dlink, de);
   add.linkAnd(de);
   sub.linkAnd(de);
   unitDetect de2=box.hasUnit();
   ru[i] = de2;
   changeCredits ok=add(pr);
   linkAnd(ok);
   ok.linkAnd(de2);
   sub.append(sub.dlink, de2);
   changeCredits adc=add(-pr);
   adc.linkAnd(de2);
   adc.append(adc.dlink, de);
   box.apply();
   m.apply(sub);
   m.apply(adc);
   m.apply(ok);
  }
  m.apply(re);
 }
 public void sing(int i) {
  apply();
  McheckBox checkbox=new McheckBox(x + (w * 0.5f) - 7, y + h, team, m);
  linkAnd(checkbox.set());
  changeCredits sub;
  linkAnd(sub = add(-i));
  changeCredits ad=add(i);
  checkbox.linkAnd(ad);
  unitRemove re=checkbox.remove();
  checkbox.linkBy(re);
  checkbox.apply();
  m.apply(sub);
  m.apply(ad);
  sign = checkbox;
 }
 public void linkAnd(basic re) {
  super.linkAnd(re);
  if (sign != null)sign.link(re);
 }
 public void linkAnd(basic re, int i) {
  linkAnd(re);
  re.linkAnd(form[i]);
 }
 protected void dosome(unitDetect de) {
  de.onlyEmptyQueue = true;
 }
}    
