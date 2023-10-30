
import java.io.OutputStreamWriter;
import rust.tmx.triggers;
import rust.tmx.unitObjects;
import rust.tmx.unitDetect;
import rust.tmx.unitType;
import java.io.BufferedWriter;
public class Main {
 public static void main(String ...arg) throws Exception {
 // triggers all=new triggers(triggers.map_, new OutputStreamWriter(System.out), 0);
  BufferedWriter buff=new BufferedWriter(new OutputStreamWriter(System.out));
  unitObjects obj=new unitObjects(buff);
  obj.add(0, 0, 0, unitType.sea_gunBoat);
  obj.end();
  buff.flush();
  System.out.println();
 }
}
