package rust.tmx.memory;
import java.io.IOException;
import rust.tmx.mapText;
import rust.tmx.triggers;
import rust.tmx.unitAdd;
import rust.tmx.unitDetect;
import rust.tmx.unitObjects;
import rust.tmx.unitType;

public class Mswitch extends MunitBox {
 public boolean center;
 public unitDetect[] list;
 public mapText[] text;
 public int index;
 public int textSize=15;
 public String textColor;
 public int maxc=Integer.MAX_VALUE;
 public Mswitch(float x0, float y0, int t, int max, triggers trg) {
  this(x0, y0, unitType.bio_bugMeleeLarge, t, max, 40f, trg);
  safe = true;
 }
 public Mswitch(float x0, float y0, String unit, int t, int max, float w1, triggers trg) {
  super(x0, y0, w1, w1, t, unit, trg);
  list = new unitDetect[max];
  text = new mapText[max];
  keep = true;
 }
 public unitDetect hasUnit() {
  return hasUnit(true);
 }
 public unitDetect nohasUnit() {
  return hasUnit(false);
 }
 private unitDetect hasUnit(boolean has) {
  int max=list.length,c= max / maxc;
  int u;
  if ((u = max % maxc) > 0)++c;
  float w0=w;
  w = c * w0;
  float h0 =h;
  h = (c > 1 ?maxc: u) * h0;
  unitDetect ru=has ?super.hasUnit(): super.nohasUnit();
  w = w0;h = h0;
  return ru;
 }
 public unitAdd set() {
  try {
   return setN(null);
  } catch (IOException e) {}
  return null;
 }
 public void set(unitObjects obj) throws IOException {
  setN(obj);
 }
 private unitAdd setN(unitObjects obj) throws IOException {
  float x0=x;
  float y0=y;
  if (center) {
   int l=list.length;
   int w0=(l >> 1);
   x0 += w0 / maxc * w;
   y0 += (l > maxc ?maxc: w0 % maxc) / 2 * h;
  }
  unitAdd add=null;
  if (obj == null) {
   add = set(x0, y0, unit(1));
  } else super.set(x0, y0, obj);
  return add;
 }
 public unitDetect add(String s) {
  int i=index++;
  float x0=x,y0=y;
  x0 += i / maxc * w;
  y0 += i % maxc * h;
  triggers trg=m;
  unitDetect de=detect(x0, y0, w, h);
  trg.apply(de);
  de.onlyIdle = true;
  list[i] = de;
  if (s != null) {
   int size=textSize;
   mapText txt=new mapText(x0 + w * 0.5f, y0 + h * 0.5f - (size * 0.5f), trg);
   txt.size = size;
   txt.textLang("", s);
   txt.color = textColor;
   text[i] = txt;
   trg.apply(txt);
  }
  return de;
 }
}
