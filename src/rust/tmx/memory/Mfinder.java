package rust.tmx.memory;
import java.util.HashMap;
import rust.tmx.llvm.ifBlock;
import rust.tmx.triggers;
import rust.tmx.unitDetect;

public class Mfinder {
 public HashMap<Integer,unitDetect> key;
 public HashMap<RangeKey,unitDetect> rgkey;
 public MunitBox box;
 public unitDetect list[];
 public unitDetect finds[];
 public int max=64;
 public Mfinder(MunitBox box) {
  this.box = box;
  rgkey = new HashMap();
  key = new HashMap();
 }
 private int initLink() {
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
 private static int getOf(int tmp3, int in) {
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
  } else ru = of(min, max);
  return ru;
 }
 public ifBlock findLink(int in) {
  int tmp=initLink();
  int i=getOf(tmp, in);
  unitDetect[] add=finds;
  unitDetect links[]=new unitDetect[2];
  unitDetect dlinks[]=new unitDetect[1];
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
   if (eqz)links[1] = de;
   else dlinks[0] = de;
   de = add[i];
  }
  links[0] = de;
  return new ifBlock(links, dlinks, false);
 }
 private unitDetect add(int min, int ax) {
  unitDetect de=box.detect();
  de.minUnits = min;
  if (ax < max)de.maxUnits = ax;
  return de;
 }
 public unitDetect of(int min, int max) {
  if (min == max) {
   if (list != null)return list[min];
   HashMap<Integer,unitDetect> table=key;
   unitDetect de=table.get(key);
   if (de == null) {
    de = add(min, max);
    table.put(min, de);
   }
   return de;
  }
  RangeKey key=new RangeKey(min, max);
  HashMap<RangeKey, unitDetect> table=rgkey;
  unitDetect de=table.get(key);
  if (de == null) {
   de = add(min, max);
   table.put(key, de);
  }
  return de;
 }
 public void apply() {
  triggers trg=box.m;
  for (unitDetect de:key.values())
   trg.apply(de);
  for (unitDetect de:rgkey.values())
   trg.apply(de);
  if (finds != null) {
   for (unitDetect de:finds)
    trg.apply(de);
  }
 }
}
