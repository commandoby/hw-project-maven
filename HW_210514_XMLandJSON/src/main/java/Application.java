import org.xml.sax.SAXException;
import xmlparsers.StAXStreamProcessor;
import xmlparsers.XMLHandler;
import xmlparsers.workers.MedicalWorkers;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Application {
    static final String ADDRESS = "src\\main\\XML\\MedicalWorkers.xml";

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        /*SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        SAXParser parser = parserFactory.newSAXParser();

        MedicalWorkers medicalWorkers = new MedicalWorkers();
        XMLHandler handler = new XMLHandler(medicalWorkers);
        parser.parse(new File(ADDRESS), handler);*/


        MedicalWorkers medicalWorkers = new MedicalWorkers();
        try (StAXStreamProcessor streamProcessor = new StAXStreamProcessor(Files.newInputStream(Paths.get(ADDRESS)), medicalWorkers)) {
            streamProcessor.read();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        System.out.println(medicalWorkers.toString());
    }
}
