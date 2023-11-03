package rust.tmx.memory;
import rust.tmx.triggers;
import rust.tmx.unitDetect;
import rust.tmx.unitType;
import rust.tmx.unitAdd;
import rust.tmx.spawnUnit;
import rust.tmx.basic;

public class McheckBox extends  MunitLogic {
 public McheckBox(float x0, float y0, int t, triggers trg) {
  this(x0, y0, 14f, t, unitType.sea_lightSub, trg);
  safe = true;
 }
 public McheckBox(float x0, float y0, float w0, int t, String type, triggers trg) {
  super(x0, y0, w0, w0, t, type, trg);
  keep = true;
 }
 public unitAdd set() {
  spawnUnit add=unit(1);
  if (safe)add.offsetHeight = -8;
  return set(x,y,add);
 }
 protected void doTrue(unitDetect de) {
   de.onlyIdle = true;
 }
 protected void doFalse(unitDetect de) {
   de.onlyIdle = true;
 }
}
