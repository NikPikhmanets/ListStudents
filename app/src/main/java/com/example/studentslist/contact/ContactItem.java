package com.example.studentslist.contact;

import android.net.Uri;

public class ContactItem {

    private String name;
    private String number;
    private Uri uriAvatar;

    public ContactItem(String name, String number, Uri uriAvatar) {
        this.name = name;
        this.number = number;
        this.uriAvatar = uriAvatar;
    }

    public Uri getUriAvatar() {
        return uriAvatar;
    }

    public void setUriAvatar(Uri uriAvatar) {

        this.uriAvatar = uriAvatar;
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
