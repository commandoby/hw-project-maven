package xmlparsers;

import xmlparsers.workers.MedicalWorkers;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;

public class StAXStreamProcessor implements AutoCloseable{
    private static final XMLInputFactory FACTORY = XMLInputFactory.newInstance();
    private final XMLStreamReader reader;
    MedicalWorkers medicalWorkers;
    private String hospitalName, hospitalAddress, name, surname, position, department;
    private int experience;

    public StAXStreamProcessor(InputStream inputStream, MedicalWorkers medicalWorkers) throws XMLStreamException {
        reader = FACTORY.createXMLStreamReader(inputStream);
        this.medicalWorkers = medicalWorkers;
    }

    public void read() {
        try {
            while (startElement()) {
                if (getLocalName().equals("hospitalName")) medicalWorkers.setHospitalName(getText());
                if (getLocalName().equals("hospitalAddress")) medicalWorkers.setHospitalAddress(getText());
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    private boolean startElement() throws XMLStreamException {
        while (reader.hasNext()) {
            int event = reader.next();
            if (event == XMLEvent.START_ELEMENT) return true;
        }
        return false;
    }

    private String getLocalName () {
        return reader.getLocalName();
    }

    private String getText() throws XMLStreamException {
        return reader.getElementText();
    }

    @Override
    public void close() {
        if (reader != null) {
            try {
                reader.close();
            } catch (XMLStreamException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
