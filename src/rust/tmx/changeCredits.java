package rust.tmx;

public class changeCredits extends basic {
 public int add;
 private Integer set;
 public void set(int i){
  set=Integer.valueOf(i);
 }
 public changeCredits(int x0, int y0, triggers triggers, int team) {
  super(x0, y0, 0, 0, triggers);
  unBox = true;
  this.team = team;
 }
 public changeCredits(int x0, int y0, point g, int team) {
  super(x0, y0, 0, 0, g);
  unBox = true;
  this.team = team;
 }
 protected void before() throws Exception {
  super.before();
  triggers trg=m;
  trg.append("add", add, 0);
  Integer obj=set;
  if(obj!=null)trg.append("set",obj.toString());
 }
}
