package rust.tmx.memory;
import java.util.HashMap;
import java.util.Iterator;
import rust.tmx.changeCredits;
import rust.tmx.triggers;
import rust.tmx.unitAdd;
import rust.tmx.unitDetect;
import rust.tmx.unitRemove;
import rust.tmx.unitType;

public class Mbuy extends MunitLogic {
 private HashMap<Integer,changeCredits[]>map;
 public Mbuy(float x0, float y0, int t, triggers trg) {
  this(x0, y0, 40f, 40f, t, unitType.buid_turret, trg);
  safe = true;
 }
 public Mbuy(float x0, float y0, float w, float h, int t, String type, triggers trg) {
  super(x0, y0, w, h, t, type, trg);
  map = new HashMap();
 }
 public void init() {
  linkBy(set());
  unitRemove re=remove();
  linkAnd(re);
 }
 public changeCredits[] sub(int i) {
  Integer in=Integer.valueOf(i);
  HashMap<Integer, changeCredits[]> tm=map;
  changeCredits[] chs=tm.get(in);
  if (chs == null) {
   triggers trg=m;
   int t=team;
   changeCredits ch = new changeCredits(trg, t);
   ch.resetActivationAfter = "1s";
   linkAnd(ch);
   ch.add = -i;
   chs = new changeCredits[2];
   chs[0] = ch;
   changeCredits add=new changeCredits(trg, t);
   add.resetActivationAfter = "1s";
   add.add = i;
   chs[1] = add;
   tm.put(i, chs);
  }
  return chs;
 }
 public void sing(int i) {
  McheckBox checkbox=new McheckBox(x + (w * 0.5f)-7, y + h, team, m);
  checkbox.hasUnit().warmup = "1s";
  unitAdd add=checkbox.set();
  linkAnd(add);
  changeCredits ad=sub(i)[1];
  checkbox.linkBy(ad);
  unitRemove re=checkbox.remove();
  checkbox.linkBy(re);
  checkbox.applyIf();
  applyIf();
  applyDo();
  checkbox.applyDo();
 }
 protected void dosome(unitDetect de) {
  de.onlyEmptyQueue = true;
 }
 public void applyDo() {
  triggers trg=m;
  Iterator<changeCredits[]> ite=map.values().iterator();
  while (ite.hasNext()) {
   changeCredits[] en=ite.next();
   trg.apply(en[0]);
   trg.apply(en[1]);
  }
  super.applyDo();
 }
}
