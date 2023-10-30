package rust.tmx;

public class teamTags extends basic {
 public String addTeamTags;
 public String removeTeamTags;
 public teamTags(int x0, int y0, triggers triggers, int team) {
  super(x0, y0, 0, 0, triggers);
  unBox = true;
  this.team = team;
 }
 public teamTags(int x0, int y0, point g, int team) {
  super(x0, y0, 0, 0, g);
  unBox = true;
  this.team = team;
 }
 protected void before() throws Exception {
  super.before();
  triggers trg=m;
  trg.append("addTeamTags", addTeamTags);
  trg.append("removeTeamTags", removeTeamTags);
 }
}
