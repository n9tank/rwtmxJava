package rust.tmx;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class triggers {
 public static final String map_survival="survival";
 public static final String map_="skirmish";
 public static final String map_mission="mission";
 public static final String map_challenge="challenge";
 public static final String fog_none="none";
 public static final String fog_los="los";
 public static final String fog_="map";
 public static final String win_mainBuildings="mainBuildings";
 public static final String win_allUnitsAndBuildings="allUnitsAndBuildings";
 public static final String win_allBuildings="allBuildings";
 public static final String win_commandCenter="commandCenter";
 public static final String win_requiredObjectives="requiredObjectives";
 public static final String win_none="none";
 protected StringBuilder warp;
 protected StringBuilder warp2;
 protected float x=-16777216;
 //坐标太大，可能会导致游戏崩溃，这里取整数精度范围。
 public float y=-250;
 protected float max;
 public ArrayList queue;
 public float getPos(float w, float h) {
  float x0=x;
  float ma=max;
  float y0=y;
  if (h > ma)max = ma = h;
  x0 += w;
  if (x0 >= 16777216) {
   x0 = -16777216;
   y = y0 -= ma;
   max = 0;
  }
  x=x0;
  return x0;
 }
 protected void add(Callable back){
  queue.add(back);
 }
 public void apply(basic back){
  if(back!=null)add(back);
 }
 public triggers(String map_type, Writer out) throws IOException {
  BufferedWriter buff = new BufferedWriter(out);
  mbuff = buff;
  type=map_type;
  warp = new StringBuilder();
  warp2 = new StringBuilder();
  queue = new ArrayList();
 }
 public BufferedWriter mbuff;
 protected int mid;
 public int mbool;
 protected boolean start;
 protected void append(String key, String value) throws IOException {
  if (value != null) {
   BufferedWriter buff=mbuff;
   if (!start) {
    start = true;
    buff.write("<properties>");
   }
   buff.write("<property name=\"");
   buff.write(key);
   buff.write("\"value=\"");
   value = value.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;").replace("'", "&apos;");
   buff.write(value);
   buff.write("\"/>");
  }
 }
 protected void append(String key, int i, int def) throws IOException {
  if (i != def)append(key, String.valueOf(i));
 }
 protected void append(String key, boolean i) throws IOException {
  if (i)append(key, "true");
 }
 protected void append(String key, float num) throws IOException {
  if (num != 0f)append(key, floatNum(num));
 }
 protected static String floatNum(float f) {
  int to=(int)f;
  String s;
  if (f == to)s = String.valueOf(to);
  else s = String.valueOf(f);
  return s;
 }
 protected void endObj() throws IOException {
  BufferedWriter buff=mbuff;
  if (start) {
   start = false;
   buff.write("</properties></object>");
  } else buff.write("/>");
 }
 public String fog;
 public String info;
 public String win;
 public boolean shareFog;
 public boolean oldWaves;
 public String music;
 public String waves;
 private String type;
 public void finsh() throws Exception {
  mbuff.write("<objectgroup name=\"Triggers\"><object name=\"map_info\"x=\"0\"y=\"0\">");
  append("type",type);
  append("fog", fog);
  append("introText", info);
  append("winCondition", win);
  append("shareFogWithAllies", shareFog);
  append("survivalWavesClassic", oldWaves);
  append("startWithMusic", music);
  append("survivalWaves", waves);
  endObj();
  ArrayList<Callable> arr=queue;
  int size=arr.size(),i=0;
  while (i<size)arr.get(i++).call();
  arr.clear();
  mbuff.write("</objectgroup>");
 }
 public String id(int i) {
  StringBuilder buff=warp;
  buff.setLength(0);
  do{
   char mod=(char)(i % 90+'!');
   i /= 90;
   if (mod >= '\"')++mod;
   if (mod >= '&')mod += 2;
   if (mod >= ',')++mod;
   if (mod >= '<')++mod;
   if (mod >= '>')++mod;
   if (mod >= 'a' && mod <= 'z') {
    mod -= 33;
   }
   buff.append(mod);
  }while(i > 0);
  return buff.toString();
 }
}
