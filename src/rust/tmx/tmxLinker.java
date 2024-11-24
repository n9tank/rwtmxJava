package rust.tmx;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class tmxLinker {
 Node map;
 Node trgs;
 Node unitsObj;
 public BufferedWriter wt;
 public tmxLinker(InputStream in, BufferedWriter wt) throws Exception {
  this.wt = wt;
  DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
  Document document = docBuilder.parse(in);
  map = document.getFirstChild();
  NodeList list=map.getChildNodes();
  for (int i=list.getLength();--i >= 0;) {
   Node item=list.item(i);
   NamedNodeMap nameAttr;
   Node name;
   if ("objectgroup".equals(item.getNodeName()) && (nameAttr = item.getAttributes()) != null && (name = nameAttr.getNamedItem("name")) != null) {
    if ("triggers".equalsIgnoreCase(name.getNodeValue()))
     trgs = item;
    else unitsObj = item;
    map.removeChild(item);
   }
  }
 }
 public static int Ipare(NamedNodeMap attr, String str) {
  Node  node= attr.getNamedItem(str);
  if (node == null)return -1;
  return Integer.parseInt(node.getNodeValue());
 }
 public point getPoint(triggers trg, String name) {
  NodeList list=map.getChildNodes();
  for (int i=0,len=list.getLength();i < len;++i) {
   Node item=list.item(i);
   NamedNodeMap nameAttr;
   Node namefind;
   if (item.getNodeType() == Node.ELEMENT_NODE && (nameAttr = item.getAttributes()) != null && (namefind = nameAttr.getNamedItem("name")) != null) {
    if (name.equals(namefind.getNodeValue())) {
     map.removeChild(item);
     return new point(Ipare(nameAttr, "x"), Ipare(nameAttr, "y"), trg);
    }
   }
  }
  return null;
 }
 public void outxml(Node node) throws IOException {
  BufferedWriter out=wt;
  NodeList list=node.getChildNodes();
  for (int i=0,len=list.getLength();i < len;++i) {
   Node item=list.item(i);
   if (item.getNodeType() == Node.TEXT_NODE) {
    out.write(item.getNodeValue().replaceAll("\\s", ""));
    return;
   }
   out.write('<');
   String name=item.getNodeName();
   out.write(name);
   NamedNodeMap maps=item.getAttributes();
   if (maps != null) {
    int j=maps.getLength();
    boolean st=true; 
    for (;--j >= 0;) {
     Node kv=maps.item(j);
     name = kv.getNodeName();
     if (st) {
      out.write(' ');   
      st = false;
     }             
     out.write(name);
     out.write("=\"");
     out.write(kv.getNodeValue());
     out.write('\"');
    }
   }
   NodeList ch=item.getChildNodes();
   if (ch.getLength() == 0)out.write('/');
   out.write('>');
   if (ch.getLength() > 0) {
    outxml(item);
    out.write("</");
    out.write(item.getNodeName());
    out.write('>');
   }
  }
 }
 public void link() throws IOException {
  wt.write("<map>");
  outxml(map);
  if (unitsObj != null) {
   wt.write("<objectgroup name=\"unitObjects\">");
   outxml(unitsObj);
  }
 }
 public unitObjects getUnits() throws IOException {
  unitObjects obj=new unitObjects(wt, unitsObj != null);
  unitsObj = null;
  return obj;
 }
 public void link(triggers trg) throws Exception {
  if (unitsObj != null)
   wt.write("</objectgroup>");
  if (trgs != null) {
   wt.write("<objectgroup name=\"triggers\">");
   outxml(trgs);
  }
  if (trg != null)
   trg.finsh(trgs != null);
  else if (trgs != null)
   wt.write("</objectgroup>");
  wt.write("</map");
 }
}
