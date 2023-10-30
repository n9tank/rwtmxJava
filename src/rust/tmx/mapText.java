package rust.tmx;
import java.util.ArrayList;

public class mapText extends basic {
 protected ArrayList text;
 public boolean style;
 public String color;
 public float offX;
 public float offY;
 public int size;
 public mapText(float x0, float y0, point g) {
  super(x0, y0, 0f, 0f, g);
  text = new ArrayList();
 }
 public mapText(float x0, float y0, triggers triggers) {
  super(x0, y0, 0f, 0f, triggers);
  text = new ArrayList();
 }
 public void textLang(String lang,String add){
  ArrayList<String> arr=text;
  arr.add(add);
  arr.add(lang);
 }
 protected void before() throws Exception {
  super.before();
  triggers triggers=m;
  if(style)triggers.append("style","arrow");
  triggers.append("textColor",color);
  triggers.append("textOffsetX",offX);
  triggers.append("textOffsety",offY);
  triggers.append("textSize",size,0);
  ArrayList<String> arr=text;
  int i=arr.size();
  while (--i >= 0)triggers.append("text".concat(arr.get(i)), arr.get(--i));
 }
}
