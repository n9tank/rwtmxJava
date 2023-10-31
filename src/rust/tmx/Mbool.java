package rust.tmx;


import rust.tmx.teamTagDetect;
import rust.tmx.teamTags;
import rust.tmx.triggers;

public class Mbool {
 private teamTags set;
 private teamTags unset;
 private teamTagDetect bool;
 private triggers m;
 private String id;
 public Mbool(triggers trg) {
  m = trg;
  int index=trg.mbool++;
  id = trg.id(index);
 }
 public teamTags set(boolean bool) {
  teamTags tag=bool ?set: unset;
  if (tag == null) {
   tag = new teamTags(m, 0);
   tag.resetActivationAfter = "1s";
   if (bool) {
    tag.addTeamTags = id;
    set = tag;
   } else {
    tag.removeTeamTags = id;
    unset = tag;
   }
  }
  return tag;
 }
 public teamTagDetect True() {
  teamTagDetect tag=bool;
  if (tag == null) {
   tag = new teamTagDetect(m, 0, id);
   bool = tag;
   tag.resetActivationAfter = "1s";
  }
  return tag;
 }
}
