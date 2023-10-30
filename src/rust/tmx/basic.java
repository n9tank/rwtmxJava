package rust.tmx;
import java.io.BufferedWriter;
import java.io.IOException;
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
 private String link;
 public boolean linkAll;
 private String alsoLink;
 private String dlink;
 private String getlink(basic ...arg) {
  StringBuilder warp=m.warp2;
  warp.setLength(0);
  int size=arg.length;
  while (--size >= 0) {
   basic bs=arg[size];
   warp.append(bs.id());
   warp.append(',');
  }
  warp.setLength(warp.length() - 1);
  return warp.toString();
 }
 public void link(basic ...arg) {
  link = getlink(arg);
 }
 public void deLink(basic ...arg) {
  dlink = getlink(arg);
 }
 public void alsoLink(basic ...arg) {
  alsoLink = getlink(arg);
 }
 public void msg(String lang, String mes) {
  ArrayList list = msg;
  list.add(mes);
  list.add(lang);
 }
 public basic(float x0, float y0, float w0, float h0, point g) {
  super(x0, y0, w0, h0, g,-3);
  msg = new ArrayList();
 }
 public basic(float x0, float y0, float w0, float h0, triggers triggers) {
  super(x0, y0, w0, h0, triggers,-3);
  msg = new ArrayList();
 }
 protected void before() throws Exception{
  super.before();
  triggers triggers=m;
  triggers.append("showOnMap", showOnMap);
  triggers.append("resetActivationAfter", resetActivationAfter);
  triggers.append("activatedBy", link);
  triggers.append("allToActivate", linkAll);
  triggers.append("deactivatedBy", dlink);
  triggers.append("activateIds", alsoLink);
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
