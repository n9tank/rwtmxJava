package rust.tmx.memory;
import rust.tmx.mapText;
import rust.tmx.triggers;
import rust.tmx.unitDetect;

public class Mchar {
 public float x;
 public float y;
 public int end;
 public int size=15;
 public boolean sub;
 public triggers m;
 public mapText[] text;
 public int ti;
 public Mchar(float x0, float y0, int ed, triggers trg) {
  x = x0;
  y = y0;
  end = ed;
  m = trg;
  while (ed > 0) {
   int i=ed % 10;
   for (int j=0;j < i;++j) {
    mapText txt=new mapText(x, y, m);
    txt.resetActivationAfter = "0";
    txt.textLang("", String.valueOf(j));
    txt.size = 15;
    text[++ti] = txt;
    m.apply(txt);
   }
   x += size;
   ed /= 10;
  }
 }
 public void link(Madder add) {
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
   unitDetect de= add.find(i);
   int u=i;
   if (sub)u = end - i;
   int c=0;
   while (u > 0) {
    int j=u % 10;
    text[c + j].linkOr(de);
    u /= 10;
    c += 10;
   }
   if (sub)--i;
   else ++i;
  }
 }
}
