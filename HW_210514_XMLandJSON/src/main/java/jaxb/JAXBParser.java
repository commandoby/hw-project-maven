package jaxb;

import xmlparsers.workers.MedicalWorkers;
import xmlparsers.workers.Worker;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JAXBParser {
    Unmarshaller unmarshaller;
    Marshaller marshaller;

    public JAXBParser() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(MedicalWorkers.class, Worker.class);
        unmarshaller = context.createUnmarshaller();
        marshaller = context.createMarshaller();
    }

    public MedicalWorkers read(File file) throws JAXBException {
        return (MedicalWorkers) unmarshaller.unmarshal(file);
    }

    public void write (File file, MedicalWorkers medicalWorkers) throws JAXBException {
        marshaller.marshal(medicalWorkers, file);
    }

}
