package rust.tmx;

public class spawnUnit {
 public boolean aggressiveTeam;
 public boolean neutralTeam;
 public float spawnChance=1.0f;
 public int maxSpawnLimit;
 public int techLevel;
 public boolean gridAlign;
 public boolean skipIfOverlapping;
 public boolean falling;
 public float offsetX;
 public float offsetY;
 public float offsetRandomXY;
 public float offsetRandomX;
 public float offsetRandomY;
 public float offsetHeight;
 public float offsetDir;
 public String type;
 protected int num;
 public spawnUnit(String unit, int n) {
  type = unit;
  num = n;
 }
 public spawnUnit(String str, float f) {
  this(str, 1);
  spawnChance = f;
  maxSpawnLimit = 1;
 }
}
