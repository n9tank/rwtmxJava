package rust.tmx;

public class unitDetect extends basic{
 public boolean onlyOnResourcePool;
 public boolean onlyMainBuildings;
 public boolean onlyBuildings;
 public boolean onlyBuilders;
 public boolean onlyIdle;
 public boolean onlyEmptyQueue;
 public boolean onlyIfEmpty;
 public int onlyTechLevel;
 public boolean onlyAttack;
 public boolean onlyAttackAir;
 public String onlyWithTag;
 public boolean includeIncomplete;
 public String unitType;
 public int minUnits;
 public int maxUnits=-1;
 public unitDetect(float x0, float y0, float w0, float h0, point g) {
  super(x0, y0, w0, h0, g);
  unBox=false;
 }
 public unitDetect(float x0, float y0, float w0, float h0, triggers triggers) {
  super(x0, y0, w0, h0, triggers);
  unBox=false;
 }
 protected void before() throws Exception{
  super.before();
  triggers trg=m;
  trg.append("onlyOnResourcePool",onlyOnResourcePool);
  trg.append("onlyMainBuildings",onlyMainBuildings);
  trg.append("onlyBuildings",onlyBuildings);
  trg.append("onlyBuilders",onlyBuilders);
  trg.append("onlyIdle",onlyIdle);
  trg.append("onlyEmptyQueue",onlyEmptyQueue);
  trg.append("onlyIfEmpty",onlyIfEmpty);
  trg.append("onlyTechLevel",onlyTechLevel);
  trg.append("onlyAttack",onlyAttack);
  trg.append("onlyAttackAir",onlyAttackAir);
  trg.append("onlyWithTag",onlyWithTag);
  trg.append("includeIncomplete",includeIncomplete);
  trg.append("unitType",unitType);
  trg.append("minUnits",minUnits);
  trg.append("maxUnits",maxUnits,-1);
 }
}
