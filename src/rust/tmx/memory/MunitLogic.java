package rust.tmx.memory;

import rust.tmx.basic;
import rust.tmx.triggers;
import rust.tmx.unitDetect;

public abstract class MunitLogic extends MunitBox {
 public unitDetect isTrue;
 public unitDetect isFalse;
 public MunitLogic(float x0, float y0, float w0, float h0, int t, String type, triggers trg) {
  super(x0, y0, w0, h0, t, type, trg);
 }
 protected abstract void dosome(unitDetect de);
 public unitDetect True() {
  unitDetect de=isTrue;
  if (de == null) {
   isTrue = de = detect();
   de.maxUnits = 0;
   dosome(de);
  }
  return de;
 }
 public void linkAnd(basic re) {
  linkBy(re);
  link(re);
 }
 public void linkBy(basic re) {
  unitDetect de=isFalse;
  if (de != null) {
   re.append(re.dlink, isFalse);
  } else {
   re.linkAnd(True());
  }
 }
 public unitDetect False() {
  unitDetect de=isFalse;
  if (de == null) {
   isFalse = de = detect();
   dosome(de);
  }
  return de;
 }
 public void applyIf() {
  super.applyIf();
  triggers trg=m;
  trg.apply(isTrue);
  trg.apply(isFalse);
 } 
}
