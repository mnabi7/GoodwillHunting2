package com.cs2340.goodwillhunting.model;

import java.util.HashMap;

/**
 * Class that defines a Location
 */
public class Location {

    private int key;
    private String name;
    private String longitude;
    private String latitude;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String type;
    private String phone;
    private String website;
    private HashMap<String, Item> items = new HashMap<>();

    /**
     * No-args constructor to invoke a Location object
     */
    public Location(){

    }

    /**
     * Constructor to invoke a Location object
     * @param key location key
     * @param name location name
     * @param latitude location latitude
     * @param longitude location longitude
     * @param address location address
     * @param city location city
     * @param state location state
     * @param zip location zip code
     * @param type location type
     * @param phone location phone number
     * @param website location website
     */
    public Location(int key, String name, String latitude, String longitude, String address,
                        String city, String state, String zip, String type, String phone, String website) {
        this.key = key;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.type = type;
        this.phone = phone;
        this.website = website;
    }

    /*
     * GETTERS AND SETTERS
     */

    /**
     * getter for key
     * @return key
     */
    public int getKey() { return key; }

    /**
     * setter for key
     * @param key new key to set as key
     */
    public void setKey(int key) { this.key = key; }

    /**
     * getter for name
     * @return name
     */
    public String getName() { return name; }

    /**
     * setter for name
     * @param name new name to set as name
     */
    public void setName(String name) { this.name = name; }

    /**
     * getter for longitude
     * @return longitutde
     */
    public String getLongitude() { return longitude; }

    /**
     * setter for longitude
     * @param longitude new longitude to set as longitude
     */
    public void setLongitude(String longitude) { this.longitude = longitude; }

    /**
     * getter for latitude
     * @return latitude
     */
    public String getLatitude() { return latitude; }

    /**
     * setter for latitude
     * @param latitude new latitude to set as latitude
     */
    public void setLatitude(String latitude) { this.latitude = latitude; }

    /**
     * getter for address
     * @return address
     */
    public String getAddress() { return address; }

    /**
     * setter for address
     * @param address new address to set as address
     */
    public void setAddress(String address) { this.address = address; }

    /**
     * getter for city
     * @return city
     */
    public String getCity() { return city; }

    /**
     * setter for city
     * @param city new city to set as city
     */
    public void setCity(String city) { this.city = city; }

    /**
     * getter for state
     * @return state
     */
    public String getState() { return state; }

    /**
     * setter for state
     * @param state new state to set as state
     */
    public void setState(String state) { this.state = state; }

    /**
     * getter for zip code
     * @return zip
     */
    public String getZip() { return zip; }

    /**
     * setter for zip code
     * @param zip
     */
    public void setZip(String zip) { this.zip = zip; }

    /**
     * getter for location type
     * @return type
     */
    public String getType() { return type; }

    /**
     * setter for location type
     * @param type new type to set as type
     */
    public void setType(String type) { this.type = type; }

    /**
     * getter for phone number
     * @return phone
     */
    public String getPhone() { return phone; }

    /**
     * setter for phone number
     * @param phone new phone to set as phone
     */
    public void setPhone(String phone) { this.phone = phone; }

    /**
     * getter for website
     * @return website
     */
    public String getWebsite() { return website; }

    /**
     * setter for website
     * @param website new website to set as website
     */
    public void setWebsite(String website) { this.website = website; }

    /**
     * getter for items
     * @return items
     */
    public HashMap<String, Item> getItems() { return items; }

    /**
     * setter for items
     * @param items new items to set as items
     */
    public void setItems(HashMap<String, Item> items) { this.items = items; }


    /**
     * to string method to return location information
     * @return string output with location information
     */
    public String toString() {
        return name + "\n" + address + "\n" + city + ", " + state + " " + zip + "\n" + phone + "\n" + website;
    }

    /**
     * equals method to test if two locations are equal
     * @param o object to be tested
     * @return boolean of if locations are found to be equal
     */
    @Override
    public boolean equals(Object o) {
        if(o == null) {
            return false;
        }
        if(o == this){
            return true;
        }

        if(!(o instanceof Location)) {
            return false;
        }
        Location loc = (Location) o;
        // didnt want to individually check all attributes
        // if the string reprs are the same, all is the same
        return loc.toString().equals(this.toString())
                && this.key == loc.getKey();

    }
}
