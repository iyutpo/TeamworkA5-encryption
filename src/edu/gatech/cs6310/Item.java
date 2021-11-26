package edu.gatech.cs6310;

public class Item {
    private String name;
    private int weight ;

    public Item(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }
    
    int get_weight() {
        double itemweight = weight;
        return (int) itemweight;
    }
}