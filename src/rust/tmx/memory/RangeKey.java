package rust.tmx.memory;

public class RangeKey implements Comparable {
 public int compareTo(Object obj) {
  RangeKey key=(RangeKey)obj;
  int u=max - key.max;
  if (u != 0)return u;
  return min - key.min;
 }
 public int hashCode() {
  return hash;
 }
 public boolean equals(Object obj) {
  return compareTo(obj) == 0;
 } 
 protected RangeKey(int i, int x) {
  min = i;
  max = x;
  int h=17;
  h = h * 31 + i;
  h = h * 31 + x;
  hash = h;
 }
 private int hash;
 private int max;
 private int min;
}
