package rust.tmx;

public class set_team extends fall{
public int team;
 public set_team(float x0, float y0, float w0, float h0, point g,int team) {
  super(x0, y0, w0, h0, g);
 }
 public set_team(float x0, float y0, float w0, float h0, triggers triggers,int team) {
  super(x0, y0, w0, h0, triggers);
 }
 protected void before() throws Exception {
  super.before();
  triggers trg=m;
  trg.mbuff.append('>');
  trg.append("setTeam", team, -3);
 }
}
