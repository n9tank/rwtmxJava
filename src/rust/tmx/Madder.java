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
 protected boolean findlink;
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
    add[c] = addn;
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
 public unitDetect find(int i) {
  unitDetect[] add=finds;
  if (add == null) {
   int tmp=max;
   add = new unitDetect[++tmp];
   finds = add;
   while (--tmp >= 0) {
    add[tmp] = add(tmp, tmp);
   }
  }
  return add[i];
 }
 private int initLink() {
  findlink = true;
  unitDetect[] add=finds;
  int tmp=max << 1;
  int tmp3=tmp % 3;
  if (add == null) {
   int tmp2 = tmp / 3 + tmp3 == 0 ?1: tmp3;
   add = new unitDetect[tmp2];
   finds = add;
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
  }
  if (tmp3 == 2)tmp3 = 0;
  else if (tmp3 == 0)tmp3 = -1;
  return tmp3;
 }
 private int getOf(int tmp3, int in) {
  int i = in + tmp3;
  tag: {
   if (i < 0 || (w == 0 && tmp3 >= 0)) {
    i = 0;
    break tag;
   }
   i = (i << 1) / 3;
   if (tmp3 < 0)++i;
  }
  return i;
 }
 public unitDetect ofLink(int min, int max) {
  unitDetect ru;
  if (max - min == 1) {
   int tmp=initLink();
   int i=getOf(tmp, min);
   unitDetect add[]=finds;
   ru = add[i];
   int mx=ru.maxUnits;
   if (mx > 0 || (mx == 0 && tmp > 0)) {
    i+=max-mx;
    ru = add[i];
   }
  } else ru = of(min, max);
  return ru;
 }
 public Madder$findLink findLink(int in) {
  int tmp=initLink();
  int i=getOf(tmp, in);
  unitDetect[] add=finds;
  Madder$findLink link= new Madder$findLink();
  unitDetect de=add[i];
  int max=de.maxUnits;
  if (max > 0 || (max == 0 && tmp > 0)) {
   int n=i;
   if (tmp < 0)n += tmp;
   boolean eqz;
   if ((n & 1) == 0) {
    ++i;
    eqz = in == max;
   } else {
    --i;
    eqz = in < max;
   }
   if (eqz)link.link2 = de;
   else link.dlink = de;
   de = add[i];
  }
  link.link = de;
  return link;
 }
 private unitDetect add(int min, int ax) {
  float w0=w;
  unitDetect de=new unitDetect(x, y, w0, w0, m);
  de.minUnits = min;
  de.resetActivationAfter = "1s";
  if (ax < max)de.maxUnits = ax;
  if (!safe) {
   de.unitType = unit;
   de.team = team;
  }
  return de;
 }
 public unitDetect of(int min, int max) {
  Madder$key key=new Madder$key(min, max);
  HashMap<Madder$key, unitDetect> table=eqz;
  unitDetect de=table.get(key);
  if (de == null) {
   de = add(min, max);
   table.put(key, de);
  }
  return de;
 }
}
