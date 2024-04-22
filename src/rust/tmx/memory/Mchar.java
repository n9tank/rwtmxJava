package rust.tmx.memory;
import java.util.Arrays;
import rust.tmx.mapText;
import rust.tmx.triggers;

public class Mchar {
 public static mapText[] form(float x, float y, String str, triggers m) {
  return form(x, y, str, 15, null, m);
 }
 public static mapText[] form(float x, float y, String str, int size, String color, triggers m) {
  int i=0,l=str.length(),c=0;
  mapText[] ch=new mapText[l];
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
  ch = Arrays.copyOf(ch, c);
  return ch;
 }
}
