package edu.gatech.cs6310;

public class Drone {
    private String storename;
    private int id;
    private int weight_capacity;
    private int trips_left;
    private String pilotID = null;
    private String pilotname = null;
    private int numoforders = 0;
    private int remaining_cap;

    public Drone(String storename, int id, int weight_capacity, int trips_left) {
        this.storename = storename;
        this.id = id;
        this.weight_capacity = weight_capacity;
        this.remaining_cap = weight_capacity;
        this.trips_left = trips_left;
    }


    int get_weight_capacity() {
        return weight_capacity;
    }

    int get_remaining_cap() {
        return this.remaining_cap;
    }
    void display() {
        String basic_output = "droneID:" + id + ",total_cap:" + weight_capacity +",num_orders:" + numoforders + ",remaining_cap:" + remaining_cap+ ",trips_left:" + trips_left;
        if (pilotname == null) {
            System.out.println(basic_output);
        }
        else {
            System.out.println(basic_output + ",flown_by:" + pilotname);
        }
    }

    void set_pilotID(String pilotID) {
        this.pilotID = pilotID;
    }

    void set_pilotname(String name) {
        pilotname = name;
    }

    String get_pilotID() {
        return pilotID;
    }

    String get_pilotname() {
        return pilotname;
    }

    void deductweight(int weightoreduce) {
        this.remaining_cap = this.remaining_cap - weightoreduce;
    }

    void finishoneride() {
        this.trips_left = this.trips_left - 1;
    }

    void itemcancel(int weighttoadd) {
        this.remaining_cap = this.remaining_cap + weighttoadd;
    }
}
