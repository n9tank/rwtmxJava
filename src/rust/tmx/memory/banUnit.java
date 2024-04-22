package rust.tmx.memory;
import java.util.ArrayList;
import rust.tmx.changeCredits;
import rust.tmx.teamTagDetect;
import rust.tmx.teamTags;
import rust.tmx.triggers;
import rust.tmx.unitDetect;

public class banUnit {
 public changeCredits add;
 public changeCredits sub;
 private Mbool bool;
 private ArrayList<String> copy;
 private banUnit(int team, triggers trg) {
  Mbool mb= new Mbool(trg);
  bool = mb;
  changeCredits ad = new changeCredits(team, trg);
  add = ad;
  ad.resetActivationAfter = "0";
  teamTagDetect eq=mb.bool;
  ad.linkOr(eq);
  ad.add = 2147483647;
  changeCredits su = new changeCredits(team, trg);
  sub = su;
  su.append(su.dlink, eq);
  su.add = -2147483647;
 }
 public banUnit(triggers trg, int team) {
  this(team, trg);
  copy = new ArrayList();
 }
 public void add(String unit) {
  copy.add(unit);
  addN(unit);
 }
 private void addN(String unit) {
  changeCredits ad=add;
  float inf=16777216;
  triggers trg=add.m;
  unitDetect de=new unitDetect(0, 0, inf, inf, trg);
  trg.apply(de);
  de.resetActivationAfter = "0";
  de.includeIncomplete = true;
  de.team = ad.team;
  de.unitType = unit;
  ad.append(ad.dlink, de);
  sub.linkOr(de);
  Mbool mb=bool;
  teamTags tr=mb.set();
  tr.linkOr(de);
  tr = mb.unset();
  tr.append(tr.dlink, de);
 }
 public banUnit put(int team) {
  banUnit ban=new banUnit(team, add.m);
  ban.copy = (ArrayList)copy.clone();
  int size=copy.size();
  while (--size >= 0) {
   ban.addN(copy.get(size));
  }
  return ban;
 }
 public void apply() {
  bool.apply();
  changeCredits ad=add;
  triggers trg = ad.m;
  trg.apply(ad);
  trg.apply(sub);
 }
}
