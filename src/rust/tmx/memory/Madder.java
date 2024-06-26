package rust.tmx.memory;
import java.util.HashMap;
import rust.tmx.triggers;
import rust.tmx.unitAdd;
import rust.tmx.unitDetect;
import rust.tmx.unitType;
import java.util.Iterator;

public class Madder extends MunitBox {
 private HashMap<Integer,unitAdd> map;
 private HashMap<Madder$key,unitDetect> eqz;
 public int max;
 public int addMax;
 public unitAdd[] adds;
 public unitDetect[] finds;
 protected byte findlink;
 public Madder(triggers trg) {
  this(trg.getPos(1f, 1f), trg.getY(), 1f, 0, unitType.other_dummyNonUnitWithTeam, trg);
  safe = true;
 }
 public Madder(float x0, float y0, float w0, int t, String type, triggers trg) {
  super(x0, y0, w0, w0, t, type, trg);
  map = new HashMap();
  eqz = new HashMap();
 }
 public unitAdd add(int i) {
  if (i <= max && adds != null)throw new RuntimeException("add():addLink()");
  Integer obj= Integer.valueOf(i);
  HashMap<Integer, unitAdd> table=map;
  unitAdd add=table.get(obj);
  if (add == null) {
   add = add(unit(i));
   table.put(obj, add);
  }
  return add;
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
  if (map.size() > 0)throw new RuntimeException("addLink():add()");
  unitAdd[] add=adds;
  int tmp=addMax;
  if (add == null) {
   int c=binary(tmp);
   int fu=1 << c;
   add = new unitAdd[c];
   adds = add;
   while (--c >= 0) {
    unitAdd addn =add(unit(i));
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
  if (findlink > 0)throw new RuntimeException("find():initLink()");
  findlink = -1;
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
  if (findlink < 0)throw new RuntimeException("initLink():find()");
  findlink = 1;
  unitDetect[] add=finds;
  int tmp=max << 1;
  int tmp3=tmp % 3;
  if (add == null) {
   if (eqz.size() > 0)throw new RuntimeException("initLink():of()");
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
   if (i < 0 || (i == 0 && tmp3 >= 0)) {
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
  int tmp=initLink();
  if (max - min == 1) {
   int i=getOf(tmp, min);
   unitDetect add[]=finds;
   ru = add[i];
   int mx=ru.maxUnits;
   if (mx > 0 || (mx == 0 && tmp > 0)) {
    i += max - mx;
    ru = add[i];
   }
  } else ru = ofN(min, max);
  return ru;
 }
 public findLink findLink(int in) {
  int tmp=initLink();
  int i=getOf(tmp, in);
  unitDetect[] add=finds;
  findLink link= new findLink();
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
  unitDetect de=detect();
  de.minUnits = min;
  if (ax < max)de.maxUnits = ax;
  return de;
 }
 public unitDetect of(int min, int max) {
  if (finds != null)throw new RuntimeException("of():initLink()");
  return ofN(min, max);
 }
 private unitDetect ofN(int min, int max) {
  Madder$key key=new Madder$key(min, max);
  HashMap<Madder$key, unitDetect> table=eqz;
  unitDetect de=table.get(key);
  if (de == null) {
   de = add(min, max);
   table.put(key, de);
  }
  return de;
 }
 public void apply() {
  triggers trg=m;
  unitAdd[] add=adds;
  if(add!=null){
   int i=add.length;
   while(--i>=0)trg.apply(add[i]);
  }
  Iterator ite=map.values().iterator();
  while(ite.hasNext()){
   unitAdd ad=(unitAdd)ite.next();
   trg.apply(ad);
  }
  trg.apply(remove);
  unitDetect[] find=finds;
  if (find != null) {
   int i=find.length;
   while(--i>=0)trg.apply(find[i]);
  }
  ite=eqz.values().iterator();
  while(ite.hasNext()){
   unitDetect ad=(unitDetect)ite.next();
   trg.apply(ad);
  }
 }
}
