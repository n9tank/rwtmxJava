package rust.tmx.llvm;
import java.util.ArrayList;
import rust.tmx.ai_allow_full_use;
import rust.tmx.attack_point;
import rust.tmx.basic;
import rust.tmx.camera_start;
import rust.tmx.changeCredits;
import rust.tmx.disable_unit_ai;
import rust.tmx.fall;
import rust.tmx.mapText;
import rust.tmx.move;
import rust.tmx.moveCamera;
import rust.tmx.objective;
import rust.tmx.point;
import rust.tmx.rotate;
import rust.tmx.set_team;
import rust.tmx.spawnUnit;
import rust.tmx.teamTagDetect;
import rust.tmx.teamTags;
import rust.tmx.team_info;
import rust.tmx.triggers;
import rust.tmx.unitAdd;
import rust.tmx.unitDetect;
import rust.tmx.unitRemove;

public class llvmUtil {
 public triggers m;
 public ifBlock block;
 public llvmUtil(triggers m) {
  this.m = m;
 }
 public Object pop() {
  ArrayList qe=m.queue;
  return qe.remove(qe.size() - 1);
 }
 public basic basic() {
  basic bs= new basic(m);
  bs.link(block);
  m.apply(bs);
  return bs;
 }
 public objective objective() {
  objective bs= new objective(m);
  bs.link(block);
  m.apply(bs);
  return bs;
 }
 public mapText mapText(float x, float y) {
  mapText bs= new mapText(x, y, m);
  bs.link(block);
  m.apply(bs);
  return bs;
 }
 public unitDetect unitDetect(float x, float y, float w, float h) {
  unitDetect bs= new unitDetect(x, y, w, h, m);
  bs.link(block);
  m.apply(bs);
  return bs;
 }
 public unitAdd unitAdd(float x, float y, int t, spawnUnit ...add) {
  unitAdd bs= new unitAdd(x, y, t, m, add);
  bs.link(block);
  m.apply(bs);
  return bs;
 }
 public unitRemove unitRemove(float x, float y, float w, float h) {
  unitRemove bs= new unitRemove(x, y, w, h, m);
  bs.link(block);
  m.apply(bs);
  return bs;
 }
 public teamTags teamTags(int t) {
  teamTags bs= new teamTags(t, m);
  bs.link(block);
  m.apply(bs);
  return bs;
 }
 public teamTagDetect teamTagDetect(int t, String add) {
  teamTagDetect bs= new teamTagDetect(t, add, m);
  bs.link(block);
  m.apply(bs);
  return bs;
 }
 public ai_allow_full_use ai_allow_full_use(float x, float y, float w, float h) {
  ai_allow_full_use bs= new ai_allow_full_use(x, y, w, h, m);
  m.apply(bs);
  return bs;
 }
 public attack_point attack_point(float x, float y) {
  attack_point bs= new attack_point(x, y, m);
  m.apply(bs);
  return bs;
 }
 public camera_start camera_start(float x, float y) {
  camera_start bs= new camera_start(x, y, m);
  m.apply(bs);
  return bs;
 }
 public changeCredits changeCredits(int t) {
  changeCredits bs= new changeCredits(t, m);
  m.apply(bs);
  return bs;
 }
 public disable_unit_ai disable_unit_ai(float x, float y, float w, float h) {
  disable_unit_ai bs= new disable_unit_ai(x, y, w, h, m);
  m.apply(bs);
  return bs;
 }
 public fall fall(float x, float y, float w, float h) {
  fall bs= new fall(x, y, w, h, m);
  m.apply(bs);
  return bs;
 }
 public move move(float x, float y, float w, float h, point p) {
  move bs= new move(x, y, w, h, m, p);
  m.apply(bs);
  return bs;
 }
 public moveCamera moveCamera(float x, float y) {
  moveCamera bs= new moveCamera(x, y, m);
  m.apply(bs);
  return bs;
 }
 public point point(float x, float y) {
  point bs= new point(x, y, m);
  m.apply(bs);
  return bs;
 }
 public rotate rotate(float x, float y, float w, float h, float dir) {
  rotate bs= new rotate(x, y, w, h, m, dir);
  m.apply(bs);
  return bs;
 }
 public set_team set_team(float x, float y, float w, float h, int t) {
  set_team bs= new set_team(x, y, w, h, t, m);
  m.apply(bs);
  return bs;
 }
 public team_info team_info(int t) {
  team_info bs= new team_info(t, m);
  m.apply(bs);
  return bs;
 }
}
