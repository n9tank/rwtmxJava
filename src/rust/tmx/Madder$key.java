package rust.tmx;

public class Madder$key{
 public int hashCode() {
  return super.hashCode();
 }
 public boolean equals(Object obj) {
  Madder$key key=(Madder$key)obj;
  return key.min==min&&key.max==max;
 } 
 protected Madder$key(int i, int x) {
  min=i;
  max=x;
  int h=1;
  h=(h<<5)-h+i;
  h=(h<<5)-h+x;
  hash=h;
 }
 private int hash;
 private int max;
 private int min;
}
