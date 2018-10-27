package com.cs2340.goodwillhunting.model;

public class Item {

    private String name;
    private String description;
    private String category;
    private int quantity;
    private int locKey; //location associated with this item
    private int itemKey;
    private String locName;
    private double value;

    public Item() {

    }

    public Item(String name, String description, String category, int quantity, int locKey, double value, String locName, int itemKey) {
        this.name = name;
        this.description= description;
        this.quantity = quantity;
        this.category = category;
        this.locKey= locKey;
        this.itemKey = itemKey;
        this.value = value;
        this.locName = locName;
    }

    public String getName() {
        return name;
    }

    public void setName(String itemName) {
        this.name = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() { return quantity;}

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public int getLocation() { return locKey; }

    public void setLocation(int locKey) { this.locKey = locKey; }

    public double getValue() { return value; }

    public void setValue(double value) { this.value = value; }

    public String getCategory() { return category; } // only need a getter

    public String getLocName() { return locName; }

    public void setLocName(String locName) { this.locName = locName; }

    public int getItemKey() { return itemKey; }

    public void setItemKey(int key) { this.itemKey = key; }



    @Override
    public String toString() {
        return "Item {\n" +
                "   name ='" + name + '\'' +
                "\n   description ='" + description + '\'' +
                "\n   value = $'" + value + '\'' +
                "\n   quantity =" + quantity +
                "\n   category =" + category +
                "\n   location =" + locName +
                "\n}";
    }
}
