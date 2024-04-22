package rust.tmx.memory;
import rust.tmx.mapText;
import rust.tmx.triggers;

public class Mchar {
 public String cr;
 public triggers m;
 public int size=15;
 public float x;
 public float y;
 public String color;
 public mapText chars[];
 public Mchar(float x0, float y0, String str, triggers trg) {
  cr = str;
  m = trg;
  x = x0;
  y = y0;
 }
 public void apply() {
  String str=cr;
  int i=0,l=str.length(),c=0;
  mapText[] ch=new mapText[l];
  chars = ch;
  do{
   int v = str.offsetByCodePoints(i, 1);
   mapText text=new mapText(x, y, m);
   ch[c++] = text;
   text.color = color;
   text.size = size;
   text.textLang("", str.substring(i, v));
   text.resetActivationAfter = "0";
   m.apply(text);
   i = v;
  }while(i < l);
 }
}
