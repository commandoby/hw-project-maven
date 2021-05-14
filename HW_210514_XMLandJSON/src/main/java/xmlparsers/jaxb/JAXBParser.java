package xmlparsers.jaxb;

import xmlparsers.workers.MedicalWorkers;
import xmlparsers.workers.Worker;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JAXBParser {
    Unmarshaller unmarshaller;

    public JAXBParser() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(MedicalWorkers.class, Worker.class);
        unmarshaller = context.createUnmarshaller();
    }

    public MedicalWorkers read(File file) throws JAXBException {
        return (MedicalWorkers) unmarshaller.unmarshal(file);
    }

}
