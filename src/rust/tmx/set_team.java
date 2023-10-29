package rust.tmx;

public class set_team extends fall{
public int team=-3;
 public set_team(int x0, int y0, int w0, int h0, point g) {
  super(x0, y0, w0, h0, g);
 }
 public set_team(int x0, int y0, int w0, int h0, triggers triggers) {
  super(x0, y0, w0, h0, triggers);
 }
 public void before() throws Exception {
  super.before();
  if(team!=-3)m.append("setTeam",String.valueOf(team));
 }
}
