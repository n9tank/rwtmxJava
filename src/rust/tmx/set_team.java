package rust.tmx;

public class set_team extends fall {
 public int team;
 public set_team(float x0, float y0, float w0, float h0, int team, point g) {
  super(x0, y0, w0, h0, g);
  this.team = team;
 }
 public set_team(float x0, float y0, float w0, float h0, int team, triggers triggers) {
  super(x0, y0, w0, h0, triggers);
  this.team = team;
 }
 protected void before() throws Exception {
  super.before();
  m.start_p = true;
  m.append("team", team, -3);
 }
}
