package rust.tmx;


import rust.tmx.teamTagDetect;
import rust.tmx.teamTags;
import rust.tmx.triggers;
import java.util.ArrayList;

public class Mbool {
 private teamTags set;
 private teamTags unset;
 private teamTagDetect isTrue;
 private teamTagDetect isFase;
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
  id = trg.id(index,false);
 }
 public teamTags set(boolean bool) {
  teamTags tag=bool ?set: unset;
  if (tag == null) {
   tag = new teamTags(m,mv -= 2);
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
 public teamTagDetect eqz(boolean bool) {
  teamTagDetect tag=bool ?isTrue: isFase;
  if (tag == null) {
   tag = new teamTagDetect(m, mv, id);
   if (bool)isTrue = tag;
   else isFase = tag;
  }
  return tag;
 }
}
