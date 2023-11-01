package rust.tmx.memory;


import rust.tmx.teamTagDetect;
import rust.tmx.teamTags;
import rust.tmx.triggers;

public class Mbool {
 private teamTags set;
 private teamTags unset;
 public final teamTagDetect bool;
 private triggers m;
 private String id;
 public Mbool(triggers trg) {
  m = trg;
  int index=trg.mbool++;
  String str= trg.id(index);
  id=str;
  teamTagDetect tag = new teamTagDetect(m, 0, str);
  tag.resetActivationAfter = "1s";
  bool=tag;
 }
 public teamTags set(boolean is) {
  teamTags tag=is ?set: unset;
  if (tag == null) {
   tag = new teamTags(m,0);
   tag.resetActivationAfter = "1s";
   if (is) {
    tag.addTeamTags = id;
    set = tag;
   } else {
    tag.removeTeamTags = id;
    unset = tag;
   }
  }
  return tag;
 }
 protected Mbool clone() throws CloneNotSupportedException{
  Mbool bo=new Mbool(m);
  String nid=bo.id;
  teamTags se=set;
  if (se != null) {
   se=(teamTags) se.clone();
   se.addTeamTags=nid;
   bo.set=se;
  }
  se= unset;
  if(se!=null){
   se=(teamTags) se.clone();
   se.removeTeamTags=nid;
   bo.unset=se;
  }
  return bo;
 } 
 public Mbool cloneAll() throws CloneNotSupportedException{
  Mbool bo=new Mbool(m);
  String nid=bo.id;
  teamTags se=set;
  if (se != null) {
  se=(teamTags) se.cloneAll();
  se.addTeamTags=nid;
  bo.set=se;
  }
  se= unset;
  if(se!=null){
  se=(teamTags) se.cloneAll();
  se.removeTeamTags=nid;
  bo.unset=se;
  }
  return bo;
 }
}
