package rust.tmx.memory;
import java.util.HashMap;
import rust.tmx.triggers;
import rust.tmx.unitAdd;
import rust.tmx.unitDetect;
import rust.tmx.unitType;
import java.util.Iterator;

public class Madder extends MunitBox {
 public HashMap<Integer,unitAdd> map;
 public Mfinder finder;
 public int addMax;
 public unitAdd[] adds;
 public Madder(triggers trg) {
  this(trg.getPos(1f, 1f), trg.getY(), 1f, -1, unitType.other_dummyNonUnitWithTeam, trg);
  safe = true;
 }
 public Madder(float x0, float y0, float w0, int t, String type, triggers trg) {
  super(x0, y0, w0, w0, t, type, trg);
  finder = new Mfinder(this);
  map = new HashMap();
 }
 public unitAdd add(int i) {
  Integer obj= Integer.valueOf(i);
  HashMap<Integer, unitAdd> table=map;
  unitAdd add=table.get(obj);
  if (add == null) {
   add = add(unit(i));
   table.put(obj, add);
  }
  return add;
 }
 public Mfinder get() {
  return finder;
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
 public void apply() {
  triggers trg=m;
  unitAdd[] add=adds;
  if (add != null)
   for (unitAdd ad:add)trg.apply(ad);
  Iterator ite=map.values().iterator();
  while (ite.hasNext()) {
   unitAdd ad=(unitAdd)ite.next();
   trg.apply(ad);
  }
  trg.apply(remove);
  finder.apply();
 }
}
