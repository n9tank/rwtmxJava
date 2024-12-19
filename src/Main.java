
import java.io.BufferedWriter;
import java.io.FileWriter;
import rust.tmx.llvm.llvmUtil;
import rust.tmx.memory.Mshop;
import rust.tmx.memory.Mswitch;
import rust.tmx.spawnUnit;
import rust.tmx.triggers;
import rust.tmx.unitAdd;
import rust.tmx.unitType;
public class Main {
 public static void testSpawn(triggers all) {
  unitAdd add=
   new unitAdd(0, 0, 1, all
               , new spawnUnit(unitType.land_modularSpider, 2.5f)
               , new spawnUnit(unitType.old_Artillery, 100f)
               , new spawnUnit(unitType.old_lasertank, 80f)
               , new spawnUnit(unitType.bio_bugMeleeLarge, 60f)
               , new spawnUnit(unitType.air_c_amphibiousJet, 50f)
               , new spawnUnit(unitType.bio_bugGeneratorN, 35f)
               , new spawnUnit(unitType.old_mammothtank, 35f)
               , new spawnUnit(unitType.bio_bugSpore, 10f)
               , new spawnUnit(unitType.old_experimentaltank, 8f)
               , new spawnUnit(unitType.bio_bugMeleeT31, 8f)
               , new spawnUnit(unitType.old_antiNukeLaucher, 8f)
               , new spawnUnit(unitType.land_mechFlyingLanded, 8f)
               , new spawnUnit(unitType.old_nukeLaucher, 5f)
               , new spawnUnit(unitType.land_experimentalGunship, 2.5f)
               , new spawnUnit(unitType.other_megaTank, 100f));
  add.fixPool();
 }
 public static void testShop(triggers all) {
  Mshop add=new Mshop(100, 100, 0, all);
  add.sing(100);
  unitAdd ad;
  add.linkAnd(ad = new unitAdd(200, 200, 0, all, new spawnUnit(unitType.land_builder, 1)));
  ad.resetActivationAfter = "0";
  all.apply(ad);
 }
 public static void testShopList(triggers all) {
  Mswitch sw=new Mswitch(1000, 0, 8, 4, all);
  sw.set();
  sw.add("两栖飞机 1600");
  sw.add("猛犸 3210");
  sw.add("实验坦克 11200");
  sw.add("模块蜘蛛 72000");
  llvmUtil llvm=new llvmUtil(all);
  Mshop buy=new Mshop(1080, 0, 8, all);
  buy.form(sw, 1600, 3210, 11200, 72000);
  unitAdd add=llvm.unitAdd(1220, 1120, 8, new spawnUnit(unitType.air_c_amphibiousJet, 1));
  add.resetActivationAfter = "0";
  buy.linkAnd(add, 0);
  add = llvm.unitAdd(1220, 1120, 8, new spawnUnit(unitType.old_mammothtank, 1));
  add.resetActivationAfter = "0";
  buy.linkAnd(add, 1);
  add = llvm.unitAdd(1220, 1120, 8, new spawnUnit(unitType.old_experimentaltank, 1));
  add.resetActivationAfter = "0";
  buy.linkAnd(add, 2);
  add = llvm.unitAdd(1220, 1120, 8, new spawnUnit(unitType.land_modularSpider, 1));
  add.resetActivationAfter = "0";
  buy.linkAnd(add, 3);
 }
 public static void main(String ...arg) throws Exception {
  BufferedWriter out=new BufferedWriter(new FileWriter("sdcard/a.txt"));
  triggers all=new triggers(triggers.map_, out);
  /*testSpawn(all);
   testShop(all);*/
  testShopList(all);
  //banUnit.air(2400,2400,all);
  all.finsh();
  out.close();
  System.out.println();
 }
}
