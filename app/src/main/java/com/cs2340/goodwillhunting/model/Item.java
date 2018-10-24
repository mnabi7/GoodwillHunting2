package com.cs2340.goodwillhunting.model;

public class Item {

    private String name;
    private String description;
    private String idNumber;
    private int quantity;
    private int key;

    public Item() {

    }

    public Item(String name, String description, String idNumber, int quantity, int key) {
        this.name = name;
        this.description= description;
        this.idNumber = idNumber;
        this.quantity = quantity;
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getname() {
        return name;
    }

    public void setname(String itemName) {
        this.name = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public int getQuantity() { return quantity;}

    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return "Item {\n" +
                "   name='" + name + '\'' +
                "\n   description='" + description + '\'' +
                "\n   idNumber='" + idNumber + '\'' +
                "\n   quantity=" + quantity +
                "\n   key=" + key +
                "\n}";
    }
}
