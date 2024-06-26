package rust.tmx.memory;


import rust.tmx.teamTagDetect;
import rust.tmx.teamTags;
import rust.tmx.triggers;
import java.util.ArrayList;

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
  id = str;
  teamTagDetect tag = new teamTagDetect(0, str, m);
  tag.resetActivationAfter = "0";
  bool = tag;
 }
 public teamTags set() {
  teamTags tag=set;
  if (tag == null) {
   tag = new teamTags(0, m);
   tag.resetActivationAfter = "0";
   tag.addTeamTags = id;
   set = tag;
  }
  return tag;
 }
 public teamTags unset() {
  teamTags tag=unset;
  if (tag == null) {
   tag = new teamTags(0, m);
   tag.resetActivationAfter = "0";
   tag.removeTeamTags = id;
   unset = tag;
  }
  return tag;
 }
 public void apply() {
  triggers trg=m;
  trg.apply(bool);
  trg.apply(set);
  trg.apply(unset);
 }
}
