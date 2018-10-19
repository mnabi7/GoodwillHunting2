package com.cs2340.goodwillhunting.model;

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

    public Location(){

    }
    public Location(int key, String name, String longitude, String latitude, String address,
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
    public int getKey() { return key; }
    public void setKey(int key) { this.key = key; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLongitude() { return longitude; }
    public void setLongitude(String longitude) { this.longitude = longitude; }

    public String getLatitude() { return latitude; }
    public void setLatitude(String latitude) { this.latitude = latitude; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getZip() { return zip; }
    public void setZip(String zip) { this.zip = zip; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }

    public String toString() {
        return name + "\n" + address + "\n" + city + ", " + state + " " + zip + "\n" + phone + "\n" + website;
    }
}
