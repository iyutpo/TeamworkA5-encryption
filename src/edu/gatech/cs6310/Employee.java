package edu.gatech.cs6310;

public class Employee extends User {
    private String SSN;
    private int number_of_month_work = 0;
    private int current_salary = 0;

    public Employee(String SSN, String firstname, String lastname, String phonenumber) {
        super(firstname, lastname, phonenumber);
        this.SSN = SSN;
    }
    String getSSN() {
        return SSN;
    }
}
