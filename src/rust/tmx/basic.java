package rust.tmx;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import rust.tmx.memory.findLink;

public class basic extends set_team implements Callable {
 public ArrayList msg;
 public String debug;
 public String msgDelay;
 public String msgColor;
 public String warmup;
 public String delay;
 public String resetActivationAfter;
 public String repeatDelay;
 public boolean showOnMap;
 public final static String msgDelay_slow="slow";
 public StringBuilder link;
 public boolean linkAll;
 public StringBuilder alsoLink;
 public StringBuilder dlink;
 protected String endBuff(StringBuilder buff) {
  int i=buff.length() - 1;
  if (i >= 0) {
   buff.setLength(i);
   return buff.toString();
  }
  return null;
 }
 public void link(findLink find) {
  linkAnd(find.link);
  linkAnd(find.link2);
  dlink(find.dlink);
 }
 public void linkAnd(basic bs) {
  StringBuilder buff=link;
  boolean is=linkAll;
  if (buff.length() == 0)linkAll = is = true;
  if (!is)throw new RuntimeException("linkAnd():linkAll=false");
  append(buff, bs);
 }
 public void linkAnd(basic ...arg) {
  int size=arg.length;
  while (--size >= 0) {
   linkAnd(arg[size]);
  }
 }
 public void linkOr(basic bs) {
  if (linkAll)throw new RuntimeException("linkOr():linkAll=true");
  append(link, bs);
 }
 public void linkOr(basic ...arg) {
  if (linkAll)throw new RuntimeException("linkOr():linkAll=true");
  append(link, arg);
 }
 public void dlink(basic bs) {
  if (bs != null) {
   resetActivationAfter = null;
   append(dlink, bs);
  }
 }
 public void dlink(basic ...bs) {
  resetActivationAfter = null;
  append(dlink, bs);
 }
 public static void append(StringBuilder buff, basic bs) {
  if (bs != null) {
   buff.append(bs.id());
   buff.append(',');
  }
 }
 public static void append(StringBuilder buff, basic ...arg) {
  int size=arg.length;
  while (--size >= 0) {
   basic bs=arg[size];
   append(buff, bs);
  }
 }
 public void msg(String lang, String mes) {
  ArrayList list = msg;
  list.add(mes);
  list.add(lang);
 }
 private void init() {
  alsoLink = new StringBuilder();
  link = new StringBuilder();
  dlink = new StringBuilder();
  msg = new ArrayList();
  unBox = true;
 }
 public basic(triggers trg) {
  this(0f, 0f, 0f, 0f, trg);
 }
 public basic(float x0, float y0, float w0, float h0, point g) {
  super(x0, y0, w0, h0, g, -3);
  init();
 }
 public basic(float x0, float y0, float w0, float h0, triggers triggers) {
  super(x0, y0, w0, h0, triggers, -3);
  init();
 }
 protected void before() throws Exception {
  super.before();
  triggers triggers=m;
  triggers.append("showOnMap", showOnMap);
  triggers.append("resetActivationAfter", resetActivationAfter);
  triggers.append("activatedBy", endBuff(link));
  triggers.append("allToActivate", linkAll);
  triggers.append("deactivatedBy", endBuff(dlink));
  triggers.append("activateIds", endBuff(alsoLink));
  triggers.append("debugMessage", debug);
  triggers.append("warmup", warmup);
  triggers.append("delay", delay);
  triggers.append("globalMessage_textColor", msgColor);
  triggers.append("globalMessage_delayPerChar", msgDelay);
  ArrayList<String> arr=msg;
  int i=arr.size();
  while (--i >= 0)triggers.append("globalMessage".concat(arr.get(i)), arr.get(--i));
 }
 public basic cloneAll() throws CloneNotSupportedException{
  basic bs=(basic)clone();
  bs.alsoLink=new StringBuilder(alsoLink);
  bs.link=new StringBuilder(link);
  bs.dlink=new StringBuilder(dlink);
  bs.msg=(ArrayList)msg.clone();
  return bs;
 }
}
