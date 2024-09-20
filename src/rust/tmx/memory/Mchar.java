package rust.tmx.memory;
import rust.tmx.mapText;
import rust.tmx.triggers;
import rust.tmx.unitDetect;

public class Mchar {
 public float x;
 public float y;
 public int end;
 public String color;
 public int textSize=15;
 public int size=15;
 public triggers m;
 public mapText[] text;
 public int ti;
 public Mchar(float x0, float y0, int ed, triggers trg) {
  x = x0;
  y = y0;
  end = ed;
  m = trg;
 }
 public void init() {
  text = emSize(size);
  int li=-1;
  int i;
  int ed=end;
  while (ed > 0) {
   i = ed % 10;
   if (li >= 0)make(0, 9, -1);
   li = i;
   ed /= 10;
  }
  if (li >= 0)make(ti == 0 ?0: 1, li, -1);
 }
 public static mapText[] emSize(int size) {
  int emsize=0;
  while (size > 0) {
   size /= 10;
   emsize += 10;
  }
  return new mapText[emsize];
 }
 public static mapText newtext(float x, float y, triggers m, int i, int textSize, String color) {
  mapText txt=new mapText(x, y, m);
  txt.resetActivationAfter = "0";
  txt.textLang("", String.valueOf(i));
  txt.size = textSize;
  txt.color = color;
  return txt;
 }
 public void make(int st, int ed, int ls) {
  int i=0;
  for (;i <= ls;++i) {
   mapText txt=newtext(x, y, m, i, textSize, color);
   text[++ti] = txt;
   m.apply(txt); 
  }
  if (i < st) {
   i = st;
   ti += st - i;
  }
  for (;i <= ed;++i) {
   mapText txt=newtext(x, y, m, i, textSize, color);
   text[++ti] = txt;
   m.apply(txt); 
  }
  ti += 10 - i;
  x += size;
 }
 public void link(Mfinder add, boolean sub) {
  int ed;
  int i;
  if (sub) {
   i = end;
   ed = -1;
  } else {
   i = 0;
   ed = end + 1;
  }
  while (i != ed) {
   unitDetect de= add.of(i, i);
   int u=i;
   if (sub)u = end - i;
   int c=0;
   while (u > 0) {
    int j=u % 10;
    mapText txt=text[c + j];
    if (txt != null)txt.linkOr(de);
    u /= 10;
    c += 10;
   }
   if (sub)--i;
   else ++i;
  }
 }
 public static mapText[] textFor(Mfinder find, int st, int ed, float x, float y, int size, String color) {
  triggers m=find.box.m;
  mapText[] list= new mapText[ed - st + 1];
  int index=0;
  for (;st <= ed;++st) {
   mapText txt=newtext(x, y, m, st, size, color);
   txt.linkAnd(find.of(index, index));
   list[index++] = txt;
   m.apply(txt); 
  }
  return list;
 }
}
