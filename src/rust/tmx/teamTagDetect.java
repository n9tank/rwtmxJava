package rust.tmx;

public class teamTagDetect extends basic {
 private String tag;
 public teamTagDetect(triggers triggers, int team, String add) {
  super(0, 0, 0, 0, triggers);
  unBox = true;
  this.team = team;
  tag = add;
  useId = true;
  unxy=true;
 }
 public teamTagDetect(point g, int team, String add) {
  super(0, 0, 0, 0, g);
  unBox = true;
  this.team = team;
  tag = add;
  useId = true;
  unxy=true;
 }
 protected void before() throws Exception {
  super.before();
  m.append("teamTag", tag);
 }
}
