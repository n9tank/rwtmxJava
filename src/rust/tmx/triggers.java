package rust.tmx;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class triggers implements Closeable {
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
 protected float x;
 protected float y=-250;
 protected float max;
 protected ArrayList queue;
 public float getPos(float w, float h) {
  float x0=x;
  float ma=max;
  float y0=y;
  if (h > ma)max = ma = h;
  x0 -= w;
  if (x0 >= 16777216) {
   x0 = 0;
   y = y0 -= ma;
   max = 0;
  }
  x=x0;
  return x0;
 }
 public triggers(String map_type, Writer out, int minTeam) throws IOException {
  BufferedWriter buff = new BufferedWriter(out);
  buff.append("<objectgroup name=\"Triggers\"><object type=\"map_info\">");
  mbuff = buff;
  append("type", map_type);
  warp = new StringBuilder();
  warp2 = new StringBuilder();
  queue = new ArrayList();
  id = new int[minTeam + 2];
 }
 protected BufferedWriter mbuff;
 protected int mid;
 protected int[] id;
 protected int mbool;
 protected boolean start;
 protected void append(String key, String value) throws IOException {
  if (value != null) {
   BufferedWriter buff=mbuff;
   if (!start) {
    start = true;
    buff.append("<properties>");
   }
   buff.append("<property name=\"");
   buff.append(key);
   buff.append("\"value=\"");
   value = value.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;").replace("'", "&apos;");
   buff.append(value);
   buff.append("\"/>");
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
   buff.append("</properties></object>");
  } else buff.append("/>");
 }
 public String fog;
 public String info;
 public String win;
 public boolean shareFog;
 public boolean oldWaves;
 public String music;
 public String waves;
 public void commit() throws IOException {
  append("fog", fog);
  append("introText", info);
  append("winCondition", win);
  append("shareFogWithAllies", shareFog);
  append("survivalWavesClassic", oldWaves);
  append("startWithMusic", music);
  append("survivalWaves", waves);
  endObj();
 }
 public void flush() throws Exception {
  ArrayList<Callable> arr=queue;
  int size=arr.size();
  while (--size >= 0)arr.get(size).call();
  arr.clear();
 }
 public void finsh() throws Exception {
  flush();
  mbuff.append("</objectgroup name=\"Triggers\">");
  mbuff.flush();
  close();
 }
 public void close() throws IOException {
  mbuff.close();
 }
 protected String id(int i, boolean igron) {
  StringBuilder buff=warp;
  buff.setLength(0);
  do{
   char mod=0;
   i /= 90;
   mod += i % 90 + '!';
   if (mod >= '\"')++mod;
   if (mod >= '&')mod += 2;
   if (mod >= ',')++mod;
   if (mod >= '<')++mod;
   if (mod >= '>')++mod;
   if (!igron && (mod >= 'a' || mod <= 'z')) {
    mod -= 33;
   }
   buff.append(mod);
  }while(i > 0);
  return buff.toString();
 }
}
