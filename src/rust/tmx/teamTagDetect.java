package rust.tmx;

public class teamTagDetect extends basic {
 private String tag;
 public teamTagDetect(triggers triggers, int team, String add) {
  super(0f, 0f, 0f, 0f, triggers);
  this.team = team;
  tag = add;
 }
 public teamTagDetect(point g, int team, String add) {
  super(0f, 0f, 0f, 0f, g);
  this.team = team;
  tag = add;
 }
 protected void before() throws Exception {
  super.before();
  m.append("teamTag", tag);
 }
}
