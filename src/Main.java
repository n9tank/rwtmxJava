
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
  unitAdd add=new unitAdd(0, 0, all, 1, new spawnUnit(unitType.land_modularSpider,2.5f), new spawnUnit(unitType.old_Artillery,100f)
  ,new spawnUnit(unitType.old_lasertank,80f),new spawnUnit(unitType.bio_bugMeleeLarge,60f),new spawnUnit(unitType.air_c_amphibiousJet,50f)
  ,new spawnUnit(unitType.bio_bugGeneratorN,35f),new spawnUnit(unitType.old_mammothtank,35f),new spawnUnit(unitType.bio_bugSpore,10f)
  ,new spawnUnit(unitType.old_experimentaltank,8f),new spawnUnit(unitType.bio_bugMeleeT31,8f),new spawnUnit(unitType.old_antiNukeLaucher,8f)
  ,new spawnUnit(unitType.land_mechFlyingLanded,8f),new spawnUnit(unitType.old_nukeLaucher,5f),
  new spawnUnit(unitType.land_experimentalGunship,2.5f),new spawnUnit(unitType.other_megaTank,100f));
  add.fixPool();
  all.apply(add);
  all.finsh();
  all.mbuff.flush();
  System.out.println();
 }
}
