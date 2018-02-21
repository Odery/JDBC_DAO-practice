package dao;

import java.util.Date;

public class Student {
    private final int id;
    private int group_id;
    private String name;
    private String surname;
    private Date enrolment_date;
    private Group group;

    public Student(int id, int group_id, String name, String surname, Date enrolment_date, Group group) {
        this.id = id;
        this.group_id = group_id;
        this.name = name;
        this.surname = surname;
        this.enrolment_date = enrolment_date;
        this.group = group;
    }

    public Student(int id, int group_id, String name, String surname, Group group) {
        this.id = id;
        this.group_id = group_id;
        this.name = name;
        this.surname = surname;
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getEnrolment_date() {
        return enrolment_date;
    }

    public void setEnrolment_date(Date enrolment_date) {
        this.enrolment_date = enrolment_date;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
