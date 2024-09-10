package rust.tmx.memory;

public class Madder$key implements Comparable {
 public int compareTo(Object obj) {
  Madder$key key=(Madder$key)obj;
  int u=max-key.max;
  if(u!=0)return u;
  return min-key.min;
 }
 public int hashCode() {
  return super.hashCode();
 }
 public boolean equals(Object obj) {
  return compareTo(obj)==0;
 } 
 protected Madder$key(int i, int x) {
  min=i;
  max=x;
  int h=17;
  h=h*31+i;
  h=h*31+x;
  hash=h;
 }
 private int hash;
 private int max;
 private int min;
}
