import Exceptions.WrongCommandException;
import jackson.JsonReader;
import org.xml.sax.SAXException;
import xmlparsers.*;
import jaxb.JAXBParser;
import xmlparsers.workers.MedicalWorkers;
import xmlparsers.workers.Worker;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Application {
    static final String ADDRESS = "src\\main\\files\\MedicalWorkers.xml";
    static final String NEWADDRESS = "src\\main\\files\\NewMedicalWorkers.xml";
    static final String JSONADDRESS = "src\\main\\files\\MedicalWorkers.json";

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        MedicalWorkers medicalWorkers = null;
        Scanner scanner = new Scanner(System.in);
        String command;
        boolean active = true;

        while (active) {
            System.out.print("Введите команду (sax, stax, dom, jaxb, json, readworker, exit): ");
            command = scanner.nextLine();
            try {
                switch (command) {
                    case "exit":
                        active = false;
                        break;
                    case "sax":
                        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
                        SAXParser parser = parserFactory.newSAXParser();
                        medicalWorkers = new MedicalWorkers();
                        XMLHandler handler = new XMLHandler(medicalWorkers);
                        parser.parse(new File(ADDRESS), handler);
                        System.out.println(medicalWorkers);
                        break;
                    case "stax":
                        medicalWorkers = new MedicalWorkers();
                        try (StAXStreamProcessor streamProcessor =
                                     new StAXStreamProcessor(Files.newInputStream(Paths.get(ADDRESS)), medicalWorkers)) {
                            streamProcessor.read();
                        } catch (XMLStreamException e) {
                            e.printStackTrace();
                        }
                        System.out.println(medicalWorkers);
                        break;
                    case "dom":
                        medicalWorkers = new MedicalWorkers();
                        DOMParser domParser = new DOMParser(new File(ADDRESS), medicalWorkers);
                        domParser.read();
                        System.out.println(medicalWorkers);
                        break;
                    case "jaxb":
                        medicalWorkers = jaxb(medicalWorkers);
                        break;
                    case "json":
                        json(medicalWorkers);
                        break;
                    case "readworker":
                        if (medicalWorkers != null) {
                            System.out.print("Введите имя: ");
                            String name = scanner.nextLine();
                            System.out.print("Введите фамилию: ");
                            String surname = scanner.nextLine();
                            readWorker(medicalWorkers.getWorkers(), name, surname);
                        } else {
                            System.out.println("Прочтите список работников больницы!");
                        }
                        break;
                    default:
                        throw new WrongCommandException("Неверная команда: " + command);
                }
            } catch (WrongCommandException | JAXBException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    static MedicalWorkers jaxb(MedicalWorkers medicalWorkers) throws JAXBException {
        boolean active = true;
        Scanner scanner = new Scanner(System.in);
        String command;
        JAXBParser jaxbParser = new JAXBParser();
        while (active) {
            System.out.print("Введинте комманду (read, write, newread, end): ");
            command = scanner.nextLine();
            try {
                switch (command) {
                    case "end" -> active = false;
                    case "write" -> jaxbParser.write(new File(NEWADDRESS), medicalWorkers);
                    case "read" -> {
                        medicalWorkers = jaxbParser.read(new File(ADDRESS));
                        System.out.println(medicalWorkers);
                    }
                    case "newread" -> {
                        medicalWorkers = jaxbParser.read(new File(NEWADDRESS));
                        System.out.println(medicalWorkers);
                    }
                    default -> throw new WrongCommandException("Неверная команда: " + command);
                }
            } catch (WrongCommandException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return medicalWorkers;
    }

    static MedicalWorkers json(MedicalWorkers medicalWorkers) {
        boolean active = true;
        Scanner scanner = new Scanner(System.in);
        String command;
        JsonReader<MedicalWorkers> jsonReader = new JsonReader<>();
        while (active) {
            System.out.print("Введинте комманду (read, write, end): ");
            command = scanner.nextLine();
            try {
                switch (command) {
                    case "end" -> active = false;
                    case "write" -> {
                        if (medicalWorkers != null) {
                            jsonReader.write(medicalWorkers, new File(JSONADDRESS));
                        } else {
                            System.out.println("Сперва прочтите список работников больницы!");
                        }
                    }
                    case "read" -> {
                        MedicalWorkers newMedicalWorkers = jsonReader.read(new File(JSONADDRESS), MedicalWorkers.class);
                        System.out.println(newMedicalWorkers);
                    }
                    default -> throw new WrongCommandException("Неверная команда: " + command);
                }
            } catch (WrongCommandException | IOException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return medicalWorkers;
    }

    static void readWorker(ArrayList<Worker> workers, String name, String surname) {
        List<Worker> workerList = workers.stream().filter(worker -> worker.getName().equals(name))
                .filter(worker -> worker.getSurname().equals(surname)).collect(Collectors.toList());
        if (!workerList.isEmpty()) {
            for (Worker w : workerList) System.out.println(w);
        } else {
            System.out.println("Работник отсутствует.");
        }
    }
}
