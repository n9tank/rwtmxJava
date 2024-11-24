package rust.tmx.llvm;
import rust.tmx.basic;
import rust.tmx.triggers;

public class ifBlock {
 public basic[] links;
 public boolean or;
 public basic[] dlinks;
 public ifBlock(basic links[], basic[] dlinks, boolean or) {
  this.links = links;
  this.dlinks = dlinks;
  this.or = or;
 }
 public basic toBasic(triggers trg) {
  basic bs= new basic(trg);
  bs.resetActivationAfter = "0";
  bs.link(this);
  return bs;
 }
 public basic toBasicNow(triggers trg) {
  basic bs=toBasic(trg);
  trg.apply(bs);
  return bs;
 }
 public static void apply(ifBlock block) {
  basic[] links=block.links;
  if (links != null) {
   for (basic link:links) {
    if (link != null) {
     link.m.apply(link);
    }
   }
  }
  basic[] dlinks=block.dlinks;
  if (dlinks != null) {
   for (basic link:dlinks) {
    if (link != null)
     link.m.apply(link);
   }
  }
 }
}
