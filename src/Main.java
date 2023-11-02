
import java.io.OutputStreamWriter;
import rust.tmx.*;
import rust.tmx.memory.banUnit;
import rust.tmx.memory.Mswitch;
import java.io.FileWriter;
public class Main {
 public static void main(String ...arg) throws Exception {
  FileWriter out=new FileWriter("sdcard/a.txt");
  triggers all=new triggers(triggers.map_,out);
  Mswitch sw=new Mswitch(all,0, 20, 0, 2);
  sw.center=true;
  sw.set(null);
  sw.addNot("a");
  sw.addNot("b");
  all.finsh();
  all.mbuff.flush();
  System.out.println();
 }
}
