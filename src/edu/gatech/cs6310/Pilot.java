package edu.gatech.cs6310;

public class Pilot extends Employee {
    private String account;
    private String licenseID;
    private int experienceride;

    public Pilot(String account, String firstname, String lastname, String phonenumber,String SSN, String licenseID, int experienceride) {
        super(SSN, firstname, lastname, phonenumber);
        this.account = account;
        this.licenseID = licenseID;
        this.experienceride = experienceride;
    }

    void display() {
        System.out.println("name:" + this.getfirstname() + "_" + this.getlastname() + ",phone:" + this.getphonenumber() + ",taxID:" + this.getSSN() + ",licenseID:" + this.licenseID + ",experience:" + this.experienceride);
    }

    String getname() {
        return this.getfirstname() + "_" + this.getlastname();
    }

    void finishoneride() {
        experienceride = experienceride + 1;
    } 
}
