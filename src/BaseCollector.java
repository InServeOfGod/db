import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.Random;

public abstract class BaseCollector {
    public static boolean setDir(String dir){
        File d = new File(dir);
        return d.mkdir();
    }

    public static long produceId(String ext){
        File d = new File(new ColumnData().getDirectory());
        File[] files =  d.listFiles();
        Random random = new Random();
        long id = random.nextLong();

        if (files != null){
            for (File file : files) {
                String filename = file.getName().replace(ext, "");
                long fileId = Long.parseLong(filename);

                if (fileId == id) {
                    id = random.nextLong();
                }
            }
        }

        return Math.abs(id);
    }

    public static String[] read(File f, ColumnData columnData) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document dom = builder.parse(f);

            Element element = dom.getDocumentElement();
            String[] data = new String[columnData.getColumns().length];

            for (int i = 0; i < columnData.getColumns().length; i++){
                try {
                    String tag = element.getElementsByTagName(columnData.getColumns()[i].replace(" ", "")).item(0).getTextContent();
                    data[i] = tag;
                }

                catch (NullPointerException nullPointerException){
                    nullPointerException.printStackTrace();
                }
            }

            return data;
        }

        catch (Exception exception){
            exception.printStackTrace();
        }

        return null;
    }

    public static boolean write(File f, ColumnData columnData){
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document dom = builder.newDocument();

            Element root = dom.createElement("root");
            dom.appendChild(root);

            for (int i = 0; i < columnData.getColumns().length; i++){
                Element element = dom.createElement(columnData.getColumns()[i].replace(" ", ""));
                element.setTextContent(columnData.getAll()[i]);
                root.appendChild(element);
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(dom), new StreamResult(f));

        } catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
