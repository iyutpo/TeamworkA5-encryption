package edu.gatech.cs6310;

public class User {
    private String firstname;
    private String lastname;
    private String phoneNumber;

    public User(String firstname, String lastname, String phonenumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = phonenumber;
    }

    String getfirstname() {
        return firstname;
    }

    String getlastname() {
        return lastname;
    }

    String getphonenumber() {
        return phoneNumber;
    }
}
