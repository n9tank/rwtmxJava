package rust.tmx;

public class changeCredits extends basic {
 public int add;
 private Integer set;
 public void set(int i){
  set=Integer.valueOf(i);
 }
 public changeCredits(int team,triggers triggers) {
  super(triggers);
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
