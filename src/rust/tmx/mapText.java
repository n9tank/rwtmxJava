package rust.tmx;
import java.util.ArrayList;

public class mapText extends basic {
 protected ArrayList text;
 public boolean style;
 public String color;
 public float offX;
 public float offY;
 public int size;
 public mapText(int x0, int y0, point g) {
  super(x0, y0, 0, 0, g);
  text = new ArrayList();
  unBox = true;
 }
 public mapText(int x0, int y0, triggers triggers) {
  super(x0, y0, 0, 0, triggers);
  text = new ArrayList();
  unBox = true;
 }
 public void textLang(String lang,String add){
  ArrayList<String> arr=text;
  arr.add(add);
  arr.add(lang);
 }
 public void before() throws Exception {
  super.before();
  triggers triggers=m;
  if(style)triggers.append("style","arrow");
  triggers.append("textColor",color);
  triggers.append("textOffsetX",floatNum(offX));
  triggers.append("textOffsety",floatNum(offY));
  triggers.append("textSize",size);
  ArrayList<String> arr=text;
  int i=arr.size();
  while (--i >= 0)triggers.append("text".concat(arr.get(i)), arr.get(--i));
 }
}
