package edu.gatech.cs6310;


public class Customer extends User{
    private String account;
    private int rating;
    private int credit;

    public Customer(String account, String firstname, String lastname, String phonenumber, int rating, int credit) {
        super(firstname, lastname, phonenumber);
        this.account = account;
        this.rating = rating;
        this.credit = credit;
    }


    void display() {
        String output = "name:" + this.getfirstname() + "_" + this.getlastname() + ",phone:" + this.getphonenumber() + ",rating:" + rating + ",credit:" + credit;
        System.out.println(output);
    }

    int get_credit() {
        return this.credit;
    }

    void deduct_credit(int amount) {
        this.credit = this.credit - amount;
    }
}
