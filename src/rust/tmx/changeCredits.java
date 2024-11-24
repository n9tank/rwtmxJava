package rust.tmx;

public class changeCredits extends basic {
 public int add;
 public long set= Long.MAX_VALUE;
 public changeCredits(int team, triggers triggers) {
  super(triggers);
  unBox = true;
  this.team = team;
 }
 protected void before() throws Exception {
  super.before();
  triggers trg=m;
  trg.append("add", add, 0);
  if (set !=  Long.MAX_VALUE)
   trg.append("set", String.valueOf(set));
 }
}
