package rust.tmx.memory;
import java.io.IOException;
import rust.tmx.mapText;
import rust.tmx.triggers;
import rust.tmx.unitAdd;
import rust.tmx.unitDetect;
import rust.tmx.unitObjects;
import rust.tmx.unitType;

public class Mswitch extends MunitBox {
 private float w2;
 public boolean horizontal;
 public boolean center;
 public unitDetect[] list;
 public mapText[] text;
 public int index;
 public int textSize=15;
 public String textColor;
 public Mswitch(triggers trg, float x0, float y0, int t, int max) {
  this(trg, x0, y0, unitType.bio_bugMeleeLarge, t, max, 14f, 40f);
  safe = true;
 }
 public Mswitch(triggers trg, float x0, float y0, String unit, int t, int max, float w0, float w1) {
  super(x0, y0, w1, w1, t, unit, trg);
  w2 = w0;
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
  float w0=w;
  float w1=w0 * (list.length + 1);
  if (horizontal)w = w1;
  else h = w1;
  unitDetect ru=has ?super.hasUnit(): super.nohasUnit();
  w = w0;h = w0;
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
   float w0=(list.length >> 1) * w;
   if (horizontal)x0 += w0;
   else y0 += w0;
  }
  unitAdd add=null;
  if (obj == null) {
   add = set(x0, y0, unit(1));
  } else super.set(x0, y0, obj);
  float w0=w;
  if (!center) {
   if (horizontal)x = x0 += w0;
   else y = y0 += w0;
  }
  return add;
 }
 public unitDetect add(String s) {
  int i=index++;
  float x0=x,y0=y;
  float we=w;
  if (center) {
   if (i == list.length >> 1) {
    if (horizontal)x0 += we;
    else y0 += we;
   }
  }
  triggers trg=m;
  if (s != null) {
   float wu=we * 0.5f;
   int size=textSize;
   mapText txt=new mapText(x0 + wu, y0 + wu - (size * 0.5f), trg);
   txt.size = size;
   txt.textLang("", s);
   txt.color = textColor;
   text[i] = txt;
   trg.apply(txt);
  }
  float w1=we;
  float w3=w1;
  float w0=w2;
  float x2=x0;
  float y2=y0;
  if (horizontal) {
   w1 -= w0;
   x2 += w0;
  } else {
   w3 -= w0;
   y2 += w0;
  }
  unitDetect de=detect(x2, y2, w1, w3);
  de.onlyIdle = true;
  list[i] = de;
  if (horizontal) x = x0 += we;
  else y = y0 += we;
  return de;
 }
 public void apply() {
  int len=list.length;
  triggers trg=m;
  while (--len >= 0) {
   trg.apply(list[len]);
  }
  super.apply();
 }
}
