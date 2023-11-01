package rust.tmx;

public class teamTags extends basic {
 public String addTeamTags;
 public String removeTeamTags;
 public teamTags(triggers triggers, int team) {
  super(triggers);
  this.team = team;
 }
 protected void before() throws Exception {
  super.before();
  triggers trg=m;
  trg.append("addTeamTags", addTeamTags);
  trg.append("removeTeamTags", removeTeamTags);
 }
}
