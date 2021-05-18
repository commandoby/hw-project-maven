package xmlparsers;

import Exceptions.WrongTagException;
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
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        lastName = qName;
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String text = new String(ch, start, length).trim();
        try {
            switch (lastName) {
                case "hospitalName" -> hospitalName = text;
                case "hospitalAddress" -> hospitalAddress = text;
                case "name" -> name = text;
                case "surname" -> surname = text;
                case "position" -> position = text;
                case "department" -> department = text;
                case "experience" -> experience = Integer.valueOf(text);
                default -> throw new WrongTagException("Неверный тег: " + lastName);
            }
        } catch (WrongTagException e) {
            System.out.println(e.getMessage());
        }
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
