package xmlparsers.workers;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "worker")
public class Worker {
    private String name, surname, position, department;
    private int experience;

    public Worker() {
        name = null;
        surname = null;
        position = null;
        department = null;
        experience = 0;
    }

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

    @XmlElement(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "surname")
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @XmlElement(name = "position")
    public void setPosition(String position) {
        this.position = position;
    }

    @XmlElement(name = "department")
    public void setDepartment(String department) {
        this.department = department;
    }

    @XmlElement(name = "experience")
    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "Работник{" +
                "Имя - '" + name + '\'' +
                ", Фамилия - '" + surname + '\'' +
                ", Должность - '" + position + '\'' +
                ", Отделение - '" + department + '\'' +
                ", Стаж работы " + experience +
                " лет}";
    }
}
