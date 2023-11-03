package rust.tmx.memory;
import rust.tmx.triggers;
import rust.tmx.unitDetect;
import rust.tmx.unitType;

public class McheckBox extends  MunitBox {
 private unitDetect isTrue;
 private unitDetect isFalse;
 public McheckBox(float x0, float y0, int t, triggers trg) {
  this(x0, y0, 14f, t, unitType.sea_lightSub, trg);
  safe = true;
 }
 public McheckBox(float x0, float y0, float w0, int t, String type, triggers trg) {
  super(x0, y0, w0, w0, t, type, trg);
  keep = true;
 }
 public unitDetect True() {
  unitDetect de=isTrue;
  if (de == null) {
   isTrue = de = detect();
   de.onlyIdle = true;
   de.maxUnits = 0;
  }
  return de;
 }
 public unitDetect False() {
  unitDetect de=isFalse;
  if (de == null) {
   isFalse = de = detect();
   de.onlyIdle = true;
  }
  return de;
 }
 public void apply() {
  triggers trg=m;
  trg.apply(isTrue);
  trg.apply(isFalse);
  super.apply();
 }
}
