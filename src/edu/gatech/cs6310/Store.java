package edu.gatech.cs6310;
import java.util.TreeMap;
import java.util.*;

public class Store {
    String name;
    private int revenue;
    private Map<String,Item> items;
    private Map<Integer,Drone> drones;
    private Map<String,Order> orders;

    public Store(String name, int revenue) {
        this.name = name;
        this.revenue = revenue;
        items = new TreeMap<>();
        drones = new TreeMap<>();
        orders = new TreeMap<>();
    }

    void print_revenue() {
        double _revenue = revenue;
        if (_revenue % 1 == 0) {
            System.out.println("name:" + name + "," + "revenue:" + (int) _revenue);
        }
        else {
            System.out.println("name:" + name + "," + "revenue:" + _revenue);
        }
    }

    void update_item(String name, Item item) {
        items.put(name,item);
    }

    void update_drone(int id, Drone drone) {
        drones.put(id, drone);
    }

    void update_order(String orderID, Order order) {
        orders.put(orderID, order);
    }

    void assign_pilot(String pilotID, String pilot_name, int droneid) {
        Drone drone = drones.get(droneid);
        drone.set_pilotname(pilot_name);
        drone.set_pilotID(pilotID);
    }

    void display_items() {
        for (String key : items.keySet()) {
            int weight = items.get(key).get_weight();
            System.out.println(key + "," + weight);
        }
    }

    void display_drones() {
        for (int i : drones.keySet()) {
            Drone drone_i = drones.get(i);
            drone_i.display();
        }
    }

    void display_orders() {
        for (String orderID: orders.keySet()) {
            orders.get(orderID).display();
        }
    }

    String pull_customerID(String orderID) {
        Order order = orders.get(orderID);
        return order.get_customerID();
    }

    void update_order(String orderID, int customer_credit, String itemname, int quantity, int price) {
        Item item = items.get(itemname);
        int itemweight = item.get_weight();
        int pendingweight = itemweight * quantity;
        Order order = orders.get(orderID);
        int droneid = order.get_droneID();
        Drone drone = drones.get(droneid);
        int remaining_drone_weight = drone.get_remaining_cap();
        if (pendingweight > remaining_drone_weight) {
            return;
        }
        int totalprice = price * quantity;
        if (totalprice > customer_credit) {
            return;
        }
        drone.deductweight(pendingweight);
        order.updateitemlines(itemname,quantity,totalprice,pendingweight);
    }

    // Note, this should be the last step to remove order (after attribute is settled)
    void removeorder(String orderID) {
        orders.remove(orderID);
    }

    void processorder(String orderID) {
        Order order = orders.get(orderID); 
        int droneid = order.get_droneID();
        Drone drone = drones.get(droneid);
        drone.finishoneride();
        removeorder(orderID);
    }

    int get_orderamount(String orderID) {
        Order order = orders.get(orderID);
        return order.compute_order_total();
    }

    void add_revenue(int amount) {
        this.revenue = this.revenue + amount;
    }

    String get_pilot_id(String orderID) {
        Order order = orders.get(orderID);
        int droneid = order.get_droneID();
        Drone drone = drones.get(droneid);
        return drone.get_pilotID();
    }
}