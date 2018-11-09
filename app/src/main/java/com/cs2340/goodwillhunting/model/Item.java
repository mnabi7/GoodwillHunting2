package com.cs2340.goodwillhunting.model;


/**
 * Class to define an Item
 */
public class Item {

    private String name;
    private String description;
    private String category;
    private int quantity;
    private int locKey; //location associated with this item
    private int itemKey;
    private String locName;
    private double value;

    /**
     * No-args constructor to invoke an item
     */
    public Item() {

    }

    /**
     * Constructor to invoke an item
     * @param name item name
     * @param description item description
     * @param category item category
     * @param quantity item quantity
     * @param locKey item location key
     * @param value item value
     * @param locName item location
     * @param itemKey item key
     */
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

    /**
     * getter for name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * setter for name
     * @param itemName new item name to set as item name
     */
    public void setName(String itemName) {
        this.name = itemName;
    }

    /**
     * getter for description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * setter for description
     * @param description new description to set as description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * getter for quantity
     * @return quantity
     */
    public int getQuantity() { return quantity;}

    /**
     * setter for quantity
     * @param quantity new quantity to set as quantity
     */
    public void setQuantity(int quantity) { this.quantity = quantity; }

    /**
     * getter for location
     * @return location key
     */
    public int getLocation() { return locKey; }

    /**
     * setter for location
     * @param locKey new location key to set as location key
     */
    public void setLocation(int locKey) { this.locKey = locKey; }

    /**
     * getter for value
     * @return value
     */
    public double getValue() { return value; }

    /**
     * setter for value
     * @param value new value to set as value
     */
    public void setValue(double value) { this.value = value; }

    /**
     * getter for category
     * @return category
     */
    public String getCategory() { return category; } // only need a getter

    /**
     * getter for location name
     * @return location name
     */
    public String getLocName() { return locName; }


    /**
     * setter for location name
     * @param locName new location name to set as location name
     */
    public void setLocName(String locName) { this.locName = locName; }

    /**
     * getter for item key
     * @return item key
     */
    public int getItemKey() { return itemKey; }

    /**
     * setter for item key
     * @param key new key to set as key
     */
    public void setItemKey(int key) { this.itemKey = key; }


    /**
     * to string method to return item information
     * @return string representation of item information
     */
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
