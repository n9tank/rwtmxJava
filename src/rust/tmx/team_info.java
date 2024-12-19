package rust.tmx;

import java.util.concurrent.Callable;

public class team_info extends point implements Callable {
 public int team=-3;
 private triggers m;
 public long credits=Long.MAX_VALUE;
 public boolean disabledAI;
 public int ai=-3;
 public int allyGroup=-3;
 public boolean aiAttack;
 public boolean basicAI;
 public boolean shareFog;
 public static final int ai_veryEasy=-2;
 public static final int ai_easy=-1;
 public static final int ai_medium=0;
 public static final int ai_hard=1;
 public static final int ai_veryHard=2;
 public static final int ai_impossible=3;
 public team_info(int id, triggers trg) {
  super(0, 0, trg);
  team = id;
  trg.apply(this);
 }
 protected void before() throws Exception {
  super.before();
  triggers trg=m;
  trg.append("team", team, -3);
  trg.append("basicAI", basicAI);
  trg.append("disabledAI", disabledAI);
  trg.append("allyGroup", allyGroup, -3);
  if (credits != Long.MAX_VALUE)
   trg.append("credits", credits);
  trg.append("shareFogWithAllies", shareFog);
  trg.append("lockAiDifficulty", ai, -3);
  if (aiAttack)trg.append("ai", "survival");
 }
}
