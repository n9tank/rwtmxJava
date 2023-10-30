package rust.tmx;

public class teamTagDetect extends basic{
private String tag;
 public teamTagDetect(int x0, int y0, triggers triggers,int team,String add) {
  super(x0, y0, 0, 0, triggers);
  unBox = true;
  this.team=team;
  tag=add;
  useId=true;
 }
 public teamTagDetect(int x0, int y0, point g,int team,String add) {
  super(x0, y0, 0, 0, g);
  unBox = true;
  this.team=team;
  tag=add;
  useId=true;
 }
 protected void before() throws Exception{
  super.before();
  m.append("teamTag",tag);
 }
}
