package rust.tmx;


import rust.tmx.teamTagDetect;
import rust.tmx.teamTags;
import rust.tmx.triggers;

public class Mbool {
 private teamTags set;
 private teamTags unset;
 public teamTagDetect bool;
 private triggers m;
 private String id;
 public Mbool(triggers trg) {
  m = trg;
  int index=trg.mbool++;
  id = trg.id(index);
  teamTagDetect tag = new teamTagDetect(m, 0, id);
  tag.resetActivationAfter="1s";
  bool=tag;
 }
 public teamTags set(boolean is) {
  teamTags tag=is ?set: unset;
  if (tag == null) {
   tag = new teamTags(m, 0);
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
}
