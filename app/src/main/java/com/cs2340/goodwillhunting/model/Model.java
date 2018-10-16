package com.cs2340.goodwillhunting.model;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private static final Model _instance = new Model();
    public static Model getInstance() { return _instance; }

    private List<Location> _locations;
    private Location currLocation;


    private Model() {
        _locations = new ArrayList<>();
    }

    public List<Location> getLocations() { return _locations; }

    public boolean addLocation(Location loc) {
        for (Location l : _locations) {
            if(l.equals(loc)) { return false; }
        }
        _locations.add(loc);
        return true;
    }

    public Location getCurrLocation() { return currLocation; }
    public void setCurrLocation(Location loc) { currLocation = loc; }

    public Location getLocationByKey(String key) {
        for (Location l : _locations) {
            if (l.getKey().equals(key)) {
                return l;
            }
        }
        return null;
    }

}