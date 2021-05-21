package com.SQLClassies;

public class Worker {
    private String name, surname, position, department;
    private int id, years;

    public Worker() {
        id = 0;
        name = null;
        surname = null;
        position = null;
        department = null;
        years = 0;
    }

    public Worker(int id, String name, String surname, String position, String department, int years) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.department = department;
        this.years = years;
    }

    public int getId() {
        return id;
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

    public int getYears() {
        return years;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setYears(int years) {
        this.years = years;
    }

    @Override
    public String toString() {
        return "Работник{" +
                "ID - '" + id + '\'' +
                ", Имя - '" + name + '\'' +
                ", Фамилия - '" + surname + '\'' +
                ", Должность - '" + position + '\'' +
                ", Отделение - '" + department + '\'' +
                ", Возраст " + years +
                " лет}";
    }
}
