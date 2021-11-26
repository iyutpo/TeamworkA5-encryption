package edu.gatech.cs6310;

import java.util.*;

public class Order {
    private String orderID;
    private String customerID;
    private String storename;
    private int droneID;
    private Map<String, List<Integer>> itemlines; 
    

    public Order(String orderID, String customerID, String storename, int droneID) {
        this.customerID = customerID;
        this.storename = storename;
        this.orderID = orderID;
        this.droneID = droneID;
        this.itemlines = new TreeMap<>();
    }

    void display() {
        String basicoutput = "orderID:" + orderID;
        System.out.println(basicoutput);
        if (!itemlines.isEmpty()) {
            for (String itemname : itemlines.keySet()) {
                List<Integer> details = itemlines.get(itemname);
                String additionaloutput = "item_name:" + itemname + ",total_quantity:" + details.get(0) + ",total_cost:" + details.get(1) + ",total_weight:" + details.get(2);
                System.out.println(additionaloutput);
            }
        }
    }

    String get_customerID(){
        return customerID;
    }

    int get_droneID() {
        return droneID;
    }

    void updateitemlines(String itemname, int quantity, int totalprice, int pendingweight) {
        List<Integer> itemline_details = new ArrayList<>();
        itemline_details.add(quantity);
        itemline_details.add(totalprice);
        itemline_details.add(pendingweight);
        this.itemlines.put(itemname, itemline_details);
    }

    int compute_order_total() {
        int totalcost = 0;
        for (List<Integer> detail: itemlines.values()) {
            totalcost =+ detail.get(1);
        }
        return totalcost;
    }
}
