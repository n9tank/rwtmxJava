package rust.tmx.memory;
import java.util.ArrayList;
import rust.tmx.changeCredits;
import rust.tmx.spawnUnit;
import rust.tmx.teamTagDetect;
import rust.tmx.teamTags;
import rust.tmx.triggers;
import rust.tmx.unitAdd;
import rust.tmx.unitDetect;
import rust.tmx.unitRemove;
import rust.tmx.unitType;
import java.util.List;

public class banUnit {
 public changeCredits add;
 public changeCredits sub;
 private Mbool bool;
 public banUnit(int team, triggers trg) {
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
 public void add(String unit) {
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
 public static void air(int w, int h, triggers m) {
  unitRemove re=new unitRemove(0, 0, w + 300, h + 300, m);
  re.team = -2;
  re.resetActivationAfter = "10s";
  int c=(int)(Math.ceil(w / 600f) * Math.ceil(h / 600f));
  spawnUnit[] sp=new spawnUnit[c];
  unitAdd ad=new unitAdd(300, 300, -2, m, sp);
  ad.resetActivationAfter = "10s";
  int u=0;
  int j=0;
  while (j < h) {
   int i=0;
   while (i < w) {
    spawnUnit s=new spawnUnit(unitType.buid_c_antiAirTurretT3, 1);
    s.offsetX = i;
    s.offsetY = j;
    s.offsetHeight = -130;
    sp[u++] = s;
    i += 600;
   }
   j += 600;
  }
  m.apply(re);
  m.apply(ad);
 }
 public static banUnit put(int team, triggers m, List<String> list) {
  banUnit ban=new banUnit(team, m);
  for (String str:list)
   ban.add(str);
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
