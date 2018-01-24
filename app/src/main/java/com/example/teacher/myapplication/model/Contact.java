package com.example.teacher.myapplication.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Teacher on 2017/12/23.
 */

public class Contact {

    private String name;
    private String family;
    private String phoneNumber;

    public Contact() {
    }

    public Contact(String name, String family, String phoneNumber) {
        this.name = name;
        this.family = family;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static List<Contact> createMockContacts() {

        List<Contact> contactList = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            Contact contact = new Contact("ALI " + i, "MOHAMMADI " + i, "0912" + (100 * i + 50 * i));
            contactList.add(contact);
        }

        return contactList;
    }
}
