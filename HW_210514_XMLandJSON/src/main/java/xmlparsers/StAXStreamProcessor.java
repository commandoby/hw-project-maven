package xmlparsers;

import Exceptions.WrongTagException;
import xmlparsers.workers.MedicalWorkers;
import xmlparsers.workers.Worker;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;

public class StAXStreamProcessor implements AutoCloseable {
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
                switch (getLocalName()) {
                    case "hospitalName" -> medicalWorkers.setHospitalName(getText());
                    case "hospitalAddress" -> medicalWorkers.setHospitalAddress(getText());
                    case "name" -> name = getText();
                    case "surname" -> surname = getText();
                    case "position" -> position = getText();
                    case "department" -> department = getText();
                    case "experience" ->
                        medicalWorkers.addWorker(new Worker(name, surname, position, department, Integer.valueOf(getText())));
                    default -> {
                        if (!getLocalName().equals("medicalWorkers") && !getLocalName().equals("worker"))
                            throw new WrongTagException("Неверный тег: " + getLocalName());
                    }
                }
            }
        } catch (XMLStreamException | WrongTagException e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean startElement() throws XMLStreamException {
        while (reader.hasNext()) {
            int event = reader.next();
            if (event == XMLEvent.START_ELEMENT) return true;
        }
        return false;
    }

    private String getLocalName() {
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
