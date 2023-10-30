package rust.tmx;
import java.util.regex.Pattern;


public class unitAdd extends basic {
 public final int safe_off=300;
 private String spawnUnit;
 public static String fomPool(String str,float ...args){
  int len=args.length,i=len;
  float all=0;
  while (--i >= 0)all += args[i];
  i = 0;
  float tmp=all;
  do{
   float w=args[i];
   float in= w / all;
   args[i]=tmp*in;
   tmp -= w;
  }while(++i < len);
  return String.format(str,args);
 }
 public unitAdd(int x0, int y0, triggers triggers,int team,String add) {
  super(x0, y0, 0, 0, triggers);
  unBox = true;
  this.team=team;
  spawnUnit=add;
 }
 public unitAdd(int x0, int y0, point g,int team,String add) {
  super(x0, y0, 0, 0, g);
  unBox = true;
  this.team=team;
  spawnUnit=add;
 }
 protected void before() throws Exception {
  super.before();
  m.append("spawnUnit",spawnUnit);
 }
}
