package com.example.studentslist.contact;

/**
 * Created by Николай on 27.11.2016.
 */

public class ContactItem {

    private String name;
    private String number;

    public ContactItem(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
