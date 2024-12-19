package rust.tmx;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import rust.tmx.llvm.ifBlock;

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
 public void link(ifBlock find) {
  if (find == null)return;
  basic[] links=find.links;
  if (links != null) {
   if (find.or)linkOr(links);
   else linkAnd(links);
  }
  links = find.dlinks;
  if (links != null)
   append(dlink, links);
 }
 public void linkAnd(basic bs) {
  StringBuilder buff=link;
  boolean is=linkAll;
  if (buff.length() == 0)linkAll = is = true;
  if (!is)throw new RuntimeException("linkAnd():linkAll=false");
  append(buff, bs);
 }
 public void linkAnd(basic ...arg) {
  for (basic link:arg)
   linkAnd(link);
 }
 public void linkOr(basic bs) {
  if (linkAll)throw new RuntimeException("linkOr():linkAll=true");
  append(link, bs);
 }
 public void linkOr(basic ...arg) {
  if (linkAll)throw new RuntimeException("linkOr():linkAll=true");
  append(link, arg);
 }
 public static void append(StringBuilder buff, basic bs) {
  if (bs != null) {
   buff.append(bs.id());
   buff.append(',');
  }
 }
 public static void append(StringBuilder buff, basic ...arg) {
  for (basic link:arg)
   append(buff, link);
 }
 public void msg(String lang, String mes) {
  ArrayList list = msg;
  list.add(lang);
  list.add(mes);
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
  super(x0, y0, w0, h0, -3, g);
  init();
 }
 public basic(float x0, float y0, float w0, float h0, triggers triggers) {
  super(x0, y0, w0, h0, -3, triggers);
  init();
 }
 protected void before() throws Exception {
  super.before();
  triggers triggers=m;
  triggers.append("showOnMap", showOnMap);
  triggers.append("resetActivationAfter", resetActivationAfter);
  String str=endBuff(link);
  if (str != null) {
   triggers.append("activatedBy", str);
   if (str.indexOf(',') >= 0)triggers.append("allToActivate", linkAll);
  }
  triggers.append("deactivatedBy", endBuff(dlink));
  triggers.append("activateIds", endBuff(alsoLink));
  triggers.append("debugMessage", debug);
  triggers.append("warmup", warmup);
  triggers.append("delay", delay);
  triggers.append("globalMessage_textColor", msgColor);
  triggers.append("globalMessage_delayPerChar", msgDelay);
  ArrayList<String> arr=msg;
  int len=arr.size();
  for (int i=0;i < len;)
   triggers.append("globalMessage".concat(arr.get(i++)), arr.get(i++));
 }
 public basic cloneAll() throws CloneNotSupportedException {
  basic bs=(basic)clone();
  bs.alsoLink = new StringBuilder(alsoLink);
  bs.link = new StringBuilder(link);
  bs.dlink = new StringBuilder(dlink);
  bs.msg = (ArrayList)msg.clone();
  return bs;
 }
}
