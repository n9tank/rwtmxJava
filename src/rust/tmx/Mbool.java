package rust.tmx;


import rust.tmx.teamTagDetect;
import rust.tmx.teamTags;
import rust.tmx.triggers;
import java.util.ArrayList;

public class Mbool {
 private teamTags set;
 private teamTags unset;
 private teamTagDetect bool;
 private triggers m;
 private String id;
 private int mv;
 public Mbool(triggers trg) {
  m = trg;
  int arr[]=trg.id;
  int len=arr.length;
  int index=trg.mbool++;
  mv = (index % len) - 2;
  index /= len;
  id = trg.id(index);
 }
 public teamTags set(boolean bool) {
  teamTags tag=bool ?set: unset;
  if (tag == null) {
   tag = new teamTags(m, mv);
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
   tag = new teamTagDetect(m, mv, id);
   bool = tag;
  }
  return tag;
 }
}
