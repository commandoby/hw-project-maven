package xmlparsers.workers;

public class Worker {
    private final String name, surname, position, department;
    private final int experience;

    public Worker(String name, String surname, String position, String department, int experience) {
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.department = department;
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPosition() {
        return position;
    }

    public String getDepartment() {
        return department;
    }

    public int getExperience() {
        return experience;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", position='" + position + '\'' +
                ", department='" + department + '\'' +
                ", experience=" + experience +
                '}';
    }
}
