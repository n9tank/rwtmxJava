
import java.io.OutputStreamWriter;
import rust.tmx.triggers;
import rust.tmx.unitObjects;
import rust.tmx.unitDetect;
import rust.tmx.unitType;
public class Main {
 public static void main(String ...arg) throws Exception {
  triggers all=new triggers(triggers.map_, new OutputStreamWriter(System.out), 0);
  unitObjects obj=new unitObjects(all.mbuff);
  unitDetect unit=new unitDetect(0, 0, 0, 0, all);
  obj.add(unit,0);
  all.finsh();
  all.mbuff.close();
  System.out.println();
 }
}
