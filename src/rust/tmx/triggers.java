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
 protected float x=-16777216f;
 //坐标太大，可能会导致游戏崩溃，这里取整数精度范围。
 protected float y=-100f;
 protected float my;
 public ArrayList queue;
 public float getPos(float w, float h) {
  float x0=x,x1=x0;
  if (h > my)my = h;
  if ((x0 += w) >= 16777216f) {
   x0 = -16777216f;
   y -= my;
   my = 0;
  }
  x = x0;
  return x1;
 }
 public float getY() {
  return y - my;
 }
 public void apply(Callable back) {
  if (back != null)queue.add(back);
 }
 public triggers(String map_type, BufferedWriter out) throws IOException {
  mbuff = out;
  type = map_type;
  warp = new StringBuilder();
  warp2 = new StringBuilder();
  queue = new ArrayList();
 }
 public BufferedWriter mbuff;
 protected int mid;
 public int mbool;
 protected boolean start;
 protected boolean start_p;
 protected void append(String key, String value) throws IOException {
  if (value != null) {
   BufferedWriter buff=mbuff;
   if (!start) {
    if (start_p)buff.write('>');
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
  start_p = false;
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
  finsh(false);
 }
 public void finsh(boolean noHead) throws Exception {
  if (!noHead)
   mbuff.write("<objectgroup name=\"Triggers\">");
  if (type != null) {
   mbuff.write("<object name=\"map_info\"x=\"0\"y=\"0\">");
   append("type", type);
   append("fog", fog);
   append("introText", info);
   append("winCondition", win);
   append("shareFogWithAllies", shareFog);
   append("survivalWavesClassic", oldWaves);
   append("startWithMusic", music);
   append("survivalWaves", waves);
   endObj();
  }
  ArrayList<Callable> arr=queue;
  int size=arr.size(),i=0;
  while (i < size)arr.get(i++).call();
  arr.clear();
  mbuff.write("</objectgroup>");
  mbuff.flush();
 }
 public String id(int i) {
  StringBuilder buff=warp;
  buff.setLength(0);
  do{
   char mod=(char)(i % 63 + '!');
   i /= 63;
   if (mod >= '\"')++mod;
   if (mod >= '&')mod += 2;
   if (mod >= ',')++mod;
   if (mod >= '<')++mod;
   if (mod >= '>')++mod;
   if (mod >= 'a')mod += 26;
   buff.append(mod);
  }while(i > 0);
  return buff.toString();
 }
}
