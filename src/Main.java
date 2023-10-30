
import java.io.OutputStreamWriter;
import rust.tmx.*;
public class Main {
 public static void main(String ...arg) throws Exception {
 triggers all=new triggers(triggers.map_, new OutputStreamWriter(System.out), 0);
 /* unitObjects obj=new unitObjects(all.mbuff);
  obj.add(0, 0, 0, unitType.land_tank);
  obj.end();
  unitDetect de=new unitDetect(0, 0, 20, 20, all);
  de.resetActivationAfter="1s";
  basic bac=new basic(0, 0, 20, 20, de);
  bac.link(de);
  bac.resetActivationAfter="1s";
  bac.msg("","hello_world");*/
  Mbool bool=new Mbool(all);
  bool.set(true);
  teamTagDetect b2=bool.True();
  b2.resetActivationAfter="1s";
  basic bac=new basic(0, 0, 0, 0, all);
  bac.link(b2);
  bac.msg("","test");
  bac.resetActivationAfter="1s";
  all.finsh();
  all.mbuff.flush();
  System.out.println();
 }
}
