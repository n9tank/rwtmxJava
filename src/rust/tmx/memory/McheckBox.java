package rust.tmx.memory;
import java.io.IOException;
import rust.tmx.spawnUnit;
import rust.tmx.triggers;
import rust.tmx.unitAdd;
import rust.tmx.unitDetect;
import rust.tmx.unitObjects;
import rust.tmx.unitRemove;
import rust.tmx.unitType;

public class McheckBox extends  MunitBox {
 private unitDetect isFalse;
 public McheckBox(triggers trg) {
  super(trg.getPos(0, 0), trg.y, 1f, 1f, 0, unitType.other_dummyNonUnitWithTeam, trg);
  safe = true;
 }
 public McheckBox(float x0, float y0, int t, triggers trg) {
  super(x0, y0, 14f, 14f, t, unitType.sea_lightSub, trg);
  safe = true;
 }
 public McheckBox(float x0, float y0, float w0, int t, String type, triggers trg) {
  super(x0, y0, w0, w0, t, type, trg);
 }
 public unitDetect False() {
  unitDetect de=isFalse;
  if (de == null) {
   isFalse = de = detect();
   if (y >= 0) {
    de.onlyIdle = true;
   }
  }
  return de;
 }
}
