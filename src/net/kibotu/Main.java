package net.kibotu;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileWriter;

public class Main {

    public static void main(final String... args) throws Exception {

        final String pathname = "assets/strings.xml";
        final File source = new File(pathname);
        if (!source.exists()) {
            System.out.println("'" + pathname + " ' File doesn't exist.");
            return;
        }

        final SessionIdentifierGenerator generator = new SessionIdentifierGenerator();

        try {

            // load and parse
            final SAXBuilder builder = new SAXBuilder();
            final Document doc = builder.build(source);
            final XMLOutputter fmt = new XMLOutputter();
            final Element resource = doc.getRootElement();

            // update fields with garbage
            for (final Element element : resource.getChildren("string"))
                element.setText(generator.nextSessionId().substring(10).toUpperCase());

            // store
            final FileWriter writer = new FileWriter(pathname);
            fmt.output(doc, writer);
            writer.flush();
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
