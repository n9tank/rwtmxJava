package rust.tmx;
import java.io.BufferedWriter;
import java.io.IOException;

public class unitObjects {
 private BufferedWriter out;
 public unitObjects(BufferedWriter wt) throws IOException {
  out = wt;
  wt.write("<objectgroup name=\"UnitObjects\">");
 }
 public void add(float x, float y, int team, String type) throws IOException {
  BufferedWriter wt=out;
  wt.write("<object x=\"");
  wt.write(triggers.floatNum(x));
  wt.write("\"y=\"");
  wt.write(triggers.floatNum(y));
  wt.write("\"><properties>");
  wt.write("<property name=\"unit\"value=\"");
  wt.write(type);
  wt.write("\"/><property name=\"team\"value=\"");
  wt.write(String.valueOf(team));
  wt.write("\"/></properties></object>");
 }
 public void end() throws IOException {
  out.write("</objectgroup>");
 }
}
