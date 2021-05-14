package xmlparsers;

import Exceptions.WrongTagException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import xmlparsers.workers.MedicalWorkers;
import xmlparsers.workers.Worker;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class DOMParser {
    MedicalWorkers medicalWorkers;
    Document document;
    private String name, surname, position, department;
    private int experience;

    public DOMParser(File file, MedicalWorkers medicalWorkers)
            throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.parse(file);
        this.medicalWorkers = medicalWorkers;
    }

    public void read() {
        NodeList hospitalName = document.getElementsByTagName("hospitalName");
        medicalWorkers.setHospitalName(hospitalName.item(0).getTextContent());
        NodeList hospitalAddress = document.getElementsByTagName("hospitalAddress");
        medicalWorkers.setHospitalAddress(hospitalAddress.item(0).getTextContent());
        NodeList workers = document.getElementsByTagName("worker");
        for (int i = 0; i < workers.getLength(); i++) {
            NodeList worker = workers.item(i).getChildNodes();
            for (int j = 0; j < worker.getLength(); j++) {
                try {
                    switch (worker.item(j).getNodeName()) {
                        case "name" -> name = worker.item(j).getTextContent();
                        case "surname" -> surname = worker.item(j).getTextContent();
                        case "position" -> position = worker.item(j).getTextContent();
                        case "department" -> department = worker.item(j).getTextContent();
                        case "experience" -> experience = Integer.valueOf(worker.item(j).getTextContent());
                        default -> {
                            if (!worker.item(j).getNodeName().equals("#text"))
                                throw new WrongTagException("Неверный тег: " + worker.item(j).getNodeName());
                        }
                    }
                } catch (WrongTagException e) {
                    System.out.println(e.getMessage());
                }
            }
            medicalWorkers.addWorker(new Worker(name, surname, position, department, experience));
        }
    }
}
