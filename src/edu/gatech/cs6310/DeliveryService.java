package edu.gatech.cs6310;
import java.util.Scanner;
import java.util.TreeMap;

import java.util.*;

public class DeliveryService {

    public void commandLoop() {
        Scanner commandLineInput = new Scanner(System.in);
        String wholeInputLine;
        String[] tokens;
        final String DELIMITER = ",";
        TreeMap<String,Store> storemap = new TreeMap<>();
        TreeMap<String,Pilot> pilotmap = new TreeMap<>();
        TreeMap<String,Customer> customermap = new TreeMap<>();
        while (true) {
            try {
                // Determine the next command and echo it to the monitor for testing purposes
                wholeInputLine = commandLineInput.nextLine();
                tokens = wholeInputLine.split(DELIMITER);
                System.out.println("> " + wholeInputLine);

                if (tokens[0].equals("make_store")) {
                    Store newstore = new Store(tokens[1],Integer.parseInt(tokens[2]));
                    storemap.put(tokens[1],newstore);
                    //System.out.println(storemap);
                    //System.out.println("store: " + tokens[1] + ", revenue: " + tokens[2]);
                    System.out.println("OK:change_completed");
                } else if (tokens[0].equals("display_stores")) {
                    for (String key : storemap.keySet()) {
                        Store storetoprint = storemap.get(key);
                        storetoprint.print_revenue();
                    }
                    //System.out.println("no parameters needed");
                    System.out.println("OK:display_completed");
                } else if (tokens[0].equals("sell_item")) {
                    String storename = tokens[1];
                    String itemname = tokens[2];
                    int itemweight = Integer.parseInt(tokens[3]);
                    Item itemtoadd = new Item(itemname,itemweight);
                    Store store = storemap.get(storename);
                    store.update_item(itemname,itemtoadd);
                    //System.out.println("store: " + tokens[1] + ", item: " + tokens[2] + ", weight: " + tokens[3]);
                    System.out.println("OK:change_completed");
                } else if (tokens[0].equals("display_items")) {
                    String storename = tokens[1];
                    Store thestore = storemap.get(storename);
                    thestore.display_items();
                    //System.out.println("no parameters needed");
                    System.out.println("OK:display_completed");
                } else if (tokens[0].equals("make_pilot")) {
                    String account = tokens[1];
                    String firstname = tokens[2];
                    String lastname = tokens[3];
                    String phonenumber = tokens[4];
                    String SSN = tokens[5];
                    String licenseID = tokens[6];
                    int experienceride = Integer.parseInt(tokens[7]);
                    Pilot newpilot = new Pilot(account, firstname, lastname, phonenumber, SSN, licenseID, experienceride);
                    //String pilotname = firstname + "_" + lastname;
                    pilotmap.put(account,newpilot);
                    System.out.println("OK:change_completed");
                } else if (tokens[0].equals("display_pilots")) {
                    for (String pilotname : pilotmap.keySet()) {
                        Pilot pilot = pilotmap.get(pilotname);
                        pilot.display();
                    }
                    System.out.println("OK:display_completed");
                } else if (tokens[0].equals("make_drone")) {
                    String storename = tokens[1];
                    int id = Integer.parseInt(tokens[2]);
                    int capacity = Integer.parseInt(tokens[3]);
                    int trips_left = Integer.parseInt(tokens[4]);
                    Drone newdrone = new Drone(storename, id, capacity, trips_left);
                    Store store = storemap.get(storename);
                    store.update_drone(id, newdrone);
                    System.out.println("OK:change_completed");
                } else if (tokens[0].equals("display_drones")) {
                    String storename = tokens[1];
                    Store store = storemap.get(storename);
                    store.display_drones();
                    System.out.println("OK:display_completed");
                } else if (tokens[0].equals("fly_drone")){
                    String storename = tokens[1];
                    int droneid = Integer.parseInt(tokens[2]);
                    String pilot_account = tokens[3];
                    Pilot pilot = pilotmap.get(pilot_account);
                    String pilotname = pilot.getname();
                    Store store = storemap.get(storename);
                    store.assign_pilot(pilot_account,pilotname, droneid); 
                    System.out.println("OK:change_completed");
                } else if (tokens[0].equals("make_customer")) {
                    String account = tokens[1];
                    String firstname = tokens[2];
                    String lastname = tokens[3];
                    String phonenumber = tokens[4];
                    int rating = Integer.parseInt(tokens[5]);
                    int credit = Integer.parseInt(tokens[6]);
                    Customer customer = new Customer(account, firstname, lastname, phonenumber, rating, credit);
                    customermap.put(account,customer);
                    System.out.println("OK:change_completed");
                } else if (tokens[0].equals("display_customers")) {
                    for (String account : customermap.keySet()) {
                        Customer customer = customermap.get(account);
                        customer.display();
                    }
                    System.out.println("OK:display_completed");
                } else if (tokens[0].equals("start_order")) {
                    String storename = tokens[1];
                    String orderID = tokens[2];
                    int droneID = Integer.parseInt(tokens[3]);
                    String customerID = tokens[4];
                    Order neworder = new Order(orderID, customerID, storename, droneID);
                    Store store = storemap.get(storename);
                    store.update_order(orderID, neworder);
                    System.out.println("OK:change_completed");
                    //System.out.println("store: " + tokens[1] + ", order: " + tokens[2] + ", drone: " + tokens[3] + ", customer: " + tokens[4]);
                } else if (tokens[0].equals("display_orders")) {
                    String storename = tokens[1];
                    Store store = storemap.get(storename);
                    store.display_orders();
                    System.out.println("OK:display_completed");
                } else if (tokens[0].equals("request_item")) {
                    String storename = tokens[1];
                    String orderID = tokens[2];
                    String itemname = tokens[3];
                    int itemquantity = Integer.parseInt(tokens[4]);
                    int itemprice = Integer.parseInt(tokens[5]);
                    //System.out.println("store: " + tokens[1] + ", order: " + tokens[2] + ", item: " + tokens[3] + ", quantity: " + tokens[4] + ", unit_price: " + tokens[5]);
                    Store store = storemap.get(storename);
                    String customerID = store.pull_customerID(orderID);
                    int customer_credit = customermap.get(customerID).get_credit();
                    store.update_order(orderID, customer_credit, itemname, itemquantity, itemprice);
                    System.out.println("OK:change_completed");
                } else if (tokens[0].equals("purchase_order")) {
                    String storename = tokens[1];
                    String orderID = tokens[2];
                    Store store = storemap.get(storename);
                    String customerID = store.pull_customerID(orderID);
                    Customer customer = customermap.get(customerID);
                    int totalcost = store.get_orderamount(orderID);
                    customer.deduct_credit(totalcost);
                    store.add_revenue(totalcost);
                    String pilotid = store.get_pilot_id(orderID);
                    Pilot pilot = pilotmap.get(pilotid);
                    pilot.finishoneride();
                    store.processorder(orderID);
                    System.out.println("OK:change_completed");
                    //System.out.println("store: " + tokens[1] + ", order: " + tokens[2]);

                } else if (tokens[0].equals("cancel_order")) {
                    //System.out.println("store: " + tokens[1] + ", order: " + tokens[2]);
                    String storename = tokens[1];
                    String orderID = tokens[2];
                    Store store = storemap.get(storename);
                    store.removeorder(orderID);
                    System.out.println("OK:change_completed");
                } else if (tokens[0].equals("stop")) {
                    System.out.println("stop acknowledged");
                    break;

                } else {
                    System.out.println("command " + tokens[0] + " NOT acknowledged");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println();
            }
        }

        System.out.println("simulation terminated");
        commandLineInput.close();
    }
}
