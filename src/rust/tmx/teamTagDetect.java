package rust.tmx;

public class teamTagDetect extends basic {
 private String tag;
 public teamTagDetect(int team, String add,triggers triggers) {
  super(triggers);
  this.team = team;
  tag = add;
 }
 protected void before() throws Exception {
  super.before();
  m.append("teamTag",tag);
 }
}
