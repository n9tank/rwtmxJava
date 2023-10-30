package rust.tmx;

import java.util.concurrent.Callable;
import java.io.IOException;

public class team_info implements Callable {
 private int t;
 private triggers m;
 public int credits;
 public boolean aiUse;
 public int ai;
 public int teams;
 public boolean aiAttack;
 public boolean aiBuild;
 public boolean shareFog;
 public static final int ai_veryEasy=-2;
 public static final int ai_easy=-1;
 public static final int ai_medium=0;
 public static final int ai_hard=1;
 public static final int ai_veryHard=2;
 public static final int ai_impossible=3;
 public team_info(int id,triggers trg) {
  t = id;
  m=trg;
  trg.queue.add(this);
 }
 public Object call() throws IOException {
  triggers trg=m;
  trg.mbuff.write("<object type=\"team_info\">");
  trg.append("team",t,0);
  trg.append("disabledAI",aiUse);
  trg.append("allyGroup",teams,0);
  trg.append("credits",credits,0);
  trg.append("shareFogWithAllies",shareFog);
  trg.append("lockAiDifficulty",ai,0);
  if(aiAttack)trg.append("ai","survival");
  trg.endObj();
  return null;
 }
}
