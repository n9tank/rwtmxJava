
import java.io.OutputStreamWriter;
import rust.tmx.*;
import rust.tmx.memory.banUnit;
public class Main {
 public static void main(String ...arg) throws Exception {
  triggers all=new triggers(triggers.map_, new OutputStreamWriter(System.out));
  /*banUnit ban=new banUnit(all, 0);
  ban.add(unitType.air_airShip);
  ban.clone(1);*/
  basic bs=new basic(all);
  bs.debug="hello";
  bs.linkOr(bs);
  basic b2=(basic)bs.cloneAll();
  b2.linkOr(b2);
  b2.call();
  all.mbuff.flush();
 }
}
