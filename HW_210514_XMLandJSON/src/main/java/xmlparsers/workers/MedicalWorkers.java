package xmlparsers.workers;

import java.util.ArrayList;

public class MedicalWorkers {
    private String hospitalName;
    private String hospitalAddress;
    ArrayList<Worker> workers;

    public MedicalWorkers() {
        workers = new ArrayList<>();
    }

    public MedicalWorkers(String hospitalName, String hospitalAddress) {
        this.hospitalName = hospitalName;
        this.hospitalAddress = hospitalAddress;
        workers = new ArrayList<>();
    }

    public MedicalWorkers(String hospitalName, String hospitalAddress, ArrayList<Worker> workers) {
        this.hospitalName = hospitalName;
        this.hospitalAddress = hospitalAddress;
        this.workers = workers;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public String getHospitalAddress() {
        return hospitalAddress;
    }

    public ArrayList<Worker> getWorkers() {
        return workers;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public void setHospitalAddress(String hospitalAddress) {
        this.hospitalAddress = hospitalAddress;
    }

    public void addWorker(Worker worker) {
        workers.add(worker);
    }

    @Override
    public String toString() {
        String text = "MedicalWorkers{" +
                "hospitalName='" + hospitalName + '\'' +
                ", hospitalAddress='" + hospitalAddress + '\'' +
                '}';
        StringBuilder builder = new StringBuilder();
        builder.append(text);
        for (Worker w : workers) {
            builder.append("\n").append(w.toString());
        }
        return builder.toString();
    }
}
