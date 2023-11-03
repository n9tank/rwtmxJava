
import java.io.OutputStreamWriter;
import rust.tmx.*;
import rust.tmx.memory.banUnit;
import rust.tmx.memory.Mswitch;
import java.io.FileWriter;
import rust.tmx.memory.Mbuy;
public class Main {
 public static void main(String ...arg) throws Exception {
  FileWriter out=new FileWriter("sdcard/a.txt");
  triggers all=new triggers(triggers.map_, out);
  /* banUnit ban= new banUnit(all, 0);
   ban.add(unitType.buid_airFactory);
   ban.apply();*/
  Mbuy by=new Mbuy(20, 20, 0, all); 
  by.init();
  by.sing(100);
  all.finsh();
  all.mbuff.flush();
  System.out.println();
 }
}
