package net.kibotu;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileWriter;

public class Main {

    public static void main(final String... args) throws Exception {

        String inputFilePath;
        inputFilePath = args[0] != null ? args[0] : "assets/strings.xml";

        String outputFilePath = inputFilePath;
        if(args[1] != null)
            outputFilePath = args[1];

        final File source = new File(inputFilePath);
        if (!source.exists()) {
            System.out.println("'" + inputFilePath + " ' File doesn't exist.");
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
            final FileWriter writer = new FileWriter(outputFilePath);
            fmt.output(doc, writer);
            writer.flush();
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
