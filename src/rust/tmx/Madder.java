package rust.tmx;
import java.util.HashMap;

public class Madder {
 private triggers m;
 private float x;
 private float y;
 private float w;
 private boolean safe;
 private String unit;
 private unitRemove rest;
 private HashMap<Integer,unitAdd> map;
 private HashMap<Madder$key,unitDetect> eqz;
 private int team;
 public int max;
 public int addMax;
 public unitAdd[] adds;
 public unitDetect[] finds;
 public Madder(triggers trg) {
  this(trg, trg.getPos(1f, 1f), trg.y, 1f, unitType.other_dummyNonUnitWithTeam, -1);
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
   add.resetActivationAfter = "1s";
   table.put(obj, add);
  }
  return add;
 }
 public unitRemove rest() {
  unitRemove re=rest;
  if (re == null) {
   float w0=w;
   rest = re = new unitRemove(x, y, w0, w0, m);
   re.resetActivationAfter = "1s";
   if (!safe)re.team = team;
  }
  return re;
 }
 private static int binary(int to) {
  int c;
  if ((to & (to - 1)) == 0)c = 1;
  else c = 0;
  do {
   to >>= 1;
   ++c;
  }while(to > 1);
  return c;
 }
 public unitAdd[] addLink(int i) {
  unitAdd[] add=adds;
  int tmp=addMax;
  if (add == null) {
   int c=binary(tmp);
   int fu=1 << c;
   add = new unitAdd[c];
   adds = add;
   float x0=x;
   float y0=y;
   int te=team;
   String type=unit;
   triggers trg=m;
   while (--c >= 0) {
    unitAdd addn = new unitAdd(x0, y0, trg, te, new spawnUnit(type, fu));
    addn.resetActivationAfter = "1s";
    add[c] =addn;
    fu >>= 1;
   }
  }
  unitAdd[] ru=new unitAdd[binary(i)];
  int c=0;
  int in=0;
  do{
   if (i >= tmp) {
    i -= tmp;
    ru[c++] = add[in];
   }
   tmp >>= 1;
   ++in;
  }while(i > 0);
  return ru;
 }
 public Madder$findLink findLink(int in) {
  unitDetect[] add=finds;
  int tmp=max << 1;
  int tmp3;
  if (add == null) {
   tmp3 = tmp % 3;
   int tmp2 = tmp / 3 + tmp3 == 0 ?1: tmp3;
   add = new unitDetect[tmp2];
   finds=add;
   do{
    int tmp4 = tmp - 1;
    add[--tmp2] = add(tmp4, tmp);
    tmp = tmp4;
    if (tmp4 > 0)--tmp4;
    add[--tmp2] = add(tmp4, tmp);
    tmp -= 2;
   }while(tmp > 0);
   if (tmp2 == 0) {
    add[--tmp2] = add(0, 0);
   }
  } else tmp3 = tmp % 3;
  if (tmp3 == 2)tmp3 = 0;
  else if (tmp3 == 0)tmp3 = -1;
  int i = in + tmp3;
  tag: {
   if (i < 0 || (w == 0 && tmp3 >= 0)) {
    i = 0;
    break tag;
   }
   i = (i << 1) / 3;
   if (tmp3 < 0)++i;
  }
  Madder$findLink link= new Madder$findLink();
  unitDetect[] links=new unitDetect[2];
  unitDetect de=add[i];
  int max=de.maxUnits;
  int min=de.minUnits;
  if (max >= 0 && min < max) {
   if (in <= max) {
    if (in < max)link.dlink = de;
    else links[1] = de;
    FixRest(de);
    de = add[++i];
   }
  }
  FixRest(de);
  links[0] = de;
  return link;
 }
 private unitDetect add(int min, int ax) {
  float w0=w;
  unitDetect de=new unitDetect(x, y, w0, w0, m);
  de.minUnits = min;
  if (ax < max)de.maxUnits = ax;
  if (!safe) {
   de.unitType = unit;
   de.team = team;
  }
  return de;
 }
 private void FixRest(unitDetect de){
  if(!safe||rest!=null)de.resetActivationAfter="1s";
 }
 public unitDetect of(int min, int max) {
  Madder$key key=new Madder$key(min, max);
  HashMap<Madder$key, unitDetect> table=eqz;
  unitDetect de=table.get(key);
  if (de == null) {
   de = add(min, max);
   table.put(key, de);
  }
  FixRest(de);
  return de;
 }
}
