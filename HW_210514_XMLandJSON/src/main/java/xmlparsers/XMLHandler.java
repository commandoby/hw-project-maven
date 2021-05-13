package xmlparsers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import xmlparsers.workers.MedicalWorkers;
import xmlparsers.workers.Worker;

public class XMLHandler extends DefaultHandler {
    MedicalWorkers medicalWorkers;
    private String lastName, hospitalName, hospitalAddress, name, surname, position, department;
    private int experience;

    public XMLHandler(MedicalWorkers medicalWorkers) {
        this.medicalWorkers = medicalWorkers;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        lastName = qName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String text = new String(ch, start, length).trim();
        if (lastName.equals("hospitalName")) hospitalName = text;
        if (lastName.equals("hospitalAddress")) hospitalAddress = text;
        if (lastName.equals("name")) name = text;
        if (lastName.equals("surname")) surname = text;
        if (lastName.equals("position")) position = text;
        if (lastName.equals("department")) department = text;
        if (lastName.equals("experience")) experience = Integer.valueOf(text);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("hospitalName") && !hospitalName.isEmpty()) {
            medicalWorkers.setHospitalName(hospitalName);
            hospitalName = null;
        }
        if (qName.equals("hospitalAddress") && !hospitalAddress.isEmpty()) {
            medicalWorkers.setHospitalAddress(hospitalAddress);
            hospitalAddress = null;
        }
        if (qName.equals("worker")) {
            medicalWorkers.addWorker(new Worker(name, surname, position, department, experience));
            name = null;
            surname = null;
            position = null;
            department = null;
            experience = 0;
        }
    }
}
