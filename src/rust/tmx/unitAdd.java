package rust.tmx;



public class unitAdd extends basic {
 public spawnUnit[] spawnUnit;
 public void fixPool() {
  spawnUnit[] args=spawnUnit;
  int len=args.length;
  float all=0;
  int i=0;
  do{
   all += args[i].spawnChance;
  }while(++i < len);
  i = 0;
  do{
   spawnUnit sp=args[i];
   float w=sp.spawnChance;
   sp.spawnChance = w / all;
   all -= w;
  }while(++i < len);
 }
 public unitAdd(float x0, float y0, int team, triggers triggers, spawnUnit ...add) {
  super(x0, y0, 0f, 0f, triggers);
  this.team = team;
  spawnUnit = add;
 }
 public unitAdd(float x0, float y0, int team, point g, spawnUnit ...add) {
  super(x0, y0, 0f, 0f, g);
  this.team = team;
  spawnUnit = add;
 }
 private boolean start;
 private void add(String key, String value) {
  StringBuilder buff=m.warp;
  if (!start) {
   buff.append('(');
   start = true;
  }
  if (value != null) {
   buff.append(key);
   buff.append('=');
   buff.append(value);
   buff.append(',');
  }
 }
 private void add(String key, boolean b) {
  if (b)add(key, "true");
 }
 private void add(String key, int i) {
  if (i != 0)add(key, String.valueOf(i));
 }
 private void add(String key, float f) {
  if (f != 0)add(key, triggers.floatNum(f));
 }
 private void end() {
  StringBuilder buff=m.warp;
  if (start) {
   buff.setLength(buff.length() - 1);
   buff.append(')');
  }
  buff.append(',');
  start = false;
 }
 protected void before() throws Exception {
  super.before();
  spawnUnit[] spawn=spawnUnit;
  if (spawn != null) {
   triggers trg=m;
   StringBuilder buff=trg.warp;
   buff.setLength(0);
   int i=0;
   int len=spawn.length;
   do{
    spawnUnit unit=spawn[i];
    buff.append(unit.type);
    int cou=unit.num;
    if (cou > 1) {
     buff.append('*');
     buff.append(cou);
    }
    add("falling", unit.falling);
    add("gridAlign", unit.gridAlign);
    add("techLevel", unit.techLevel);
    float sp=unit.spawnChance;
    if (sp != 1f)add("spawnChance", triggers.floatNum(sp));
    if (i > 0 || cou > 1)add("maxSpawnLimit", unit.maxSpawnLimit);
    add("neutralTeam", unit.neutralTeam);
    add("aggressiveTeam", unit.aggressiveTeam);
    add("offsetDir", unit.offsetDir);
    add("offsetHeight", unit.offsetHeight);
    add("offsetRandomX", unit.offsetRandomX);
    add("offsetRandomXY", unit.offsetRandomXY);
    add("offsetRandomY", unit.offsetRandomY);
    add("offsetX", unit.offsetX);
    add("offsetY", unit.offsetY);
    add("skipIfOverlapping", unit.skipIfOverlapping);
    end();
   }while(++i < len);
   buff.setLength(buff.length() - 1);
   trg.append("spawnUnits", buff.toString());
  }
 }
}
