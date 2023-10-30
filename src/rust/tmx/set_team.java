package rust.tmx;

public class set_team extends fall{
public int team;
 public set_team(int x0, int y0, int w0, int h0, point g,int team) {
  super(x0, y0, w0, h0, g);
 }
 public set_team(int x0, int y0, int w0, int h0, triggers triggers,int team) {
  super(x0, y0, w0, h0, triggers);
 }
 protected void before() throws Exception {
  super.before();
  m.append("setTeam",team,-3);
 }
}
