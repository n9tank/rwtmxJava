
import java.io.FileWriter;
import rust.tmx.triggers;
public class Main {
 public static void main(String ...arg) throws Exception {
  FileWriter out=new FileWriter("sdcard/a.txt");
  triggers all=new triggers(triggers.map_, out);
  /*
   生成概率
   unitAdd add=new unitAdd(0, 0, all, 1, new spawnUnit(unitType.land_modularSpider,2.5f), new spawnUnit(unitType.old_Artillery,100f)
   ,new spawnUnit(unitType.old_lasertank,80f),new spawnUnit(unitType.bio_bugMeleeLarge,60f),new spawnUnit(unitType.air_c_amphibiousJet,50f)
   ,new spawnUnit(unitType.bio_bugGeneratorN,35f),new spawnUnit(unitType.old_mammothtank,35f),new spawnUnit(unitType.bio_bugSpore,10f)
   ,new spawnUnit(unitType.old_experimentaltank,8f),new spawnUnit(unitType.bio_bugMeleeT31,8f),new spawnUnit(unitType.old_antiNukeLaucher,8f)
   ,new spawnUnit(unitType.land_mechFlyingLanded,8f),new spawnUnit(unitType.old_nukeLaucher,5f),
   new spawnUnit(unitType.land_experimentalGunship,2.5f),new spawnUnit(unitType.other_megaTank,100f));
   add.fixPool();
   单例商店
   Mbuy add=new Mbuy(100, 100, 0, all);
   add.init();
   unitAdd ad;
   add.linkAnd(ad = new unitAdd(200, 200, 0, all, new spawnUnit(unitType.land_builder, 1)));
   ad.resetActivationAfter = "1s";
   all.apply(ad);
   add.sing(100);
   列表测试
   Mswitch sw=new Mswitch(40, 20, 0, 1, all);
   sw.set();
   sw.add("one");
   Mbuy buy=new Mbuy(200, 200, 0, all);
   buy.init();
   sw.apply();
   buy.apply();
   unitDetect[] link=buy.form(sw, 100, 200);
   unitAdd add=new unitAdd(300, 300, 0, all, new spawnUnit(unitType.land_tank, 1));
   add.resetActivationAfter="0";
   add.linkAnd(link[0]);
   buy.linkAnd(add);
   all.apply(add);*/
  all.finsh();
  all.mbuff.flush();
  System.out.println();
 }
}
