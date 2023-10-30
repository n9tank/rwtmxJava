package rust.tmx;

public class teamTags extends basic {
 public String addTeamTags;
 public String removeTeamTags;
 public teamTags(triggers triggers, int team) {
  super(0, 0, 0, 0, triggers);
  unxy=unBox = true;
  this.team = team;
 }
 public teamTags(point g, int team) {
  super(0, 0, 0, 0, g);
  unxy=unBox = true;
  this.team = team;
 }
 protected void before() throws Exception {
  super.before();
  triggers trg=m;
  trg.append("addTeamTags", addTeamTags);
  trg.append("removeTeamTags", removeTeamTags);
 }
}
