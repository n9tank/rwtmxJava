package rust.tmx;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class basic extends set_team implements Callable {
 private ArrayList msg;
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
 public void append(Madder$findLink find){
  append(link,find.link);
  unitDetect de=find.dlink;
  if(de!=null)append(dlink,de);
 }
 public static void append(StringBuilder buff, basic ...arg) {
  int size=arg.length;
  while (--size >= 0) {
   basic bs=arg[size];
   if (bs != null) {
    buff.append(bs.id());
    buff.append(',');
   }
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
}
