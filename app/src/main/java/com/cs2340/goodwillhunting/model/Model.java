package com.cs2340.goodwillhunting.model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


import com.cs2340.goodwillhunting.R;
import com.cs2340.goodwillhunting.controllers.CSVFile;
import com.cs2340.goodwillhunting.controllers.LocationListActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Class to represent the model of the application
 */
public class Model {

    private static final Model _instance = new Model();
    public static Model getInstance() { return _instance; }

    private List<Location> _locations;
    private Location currLocation;
    private DatabaseReference reference;
    private InputStream inputStream;


    /**
     * No-args constructor to invoke the model (private - only one object can be created)
     */
    private Model() {
        _locations = new ArrayList<>();
        //loadData();
    }

    //public void initInputStream(InputStream inputStream) { this.inputStream = inputStream; }

    /**
     * method to load the Data into the model from the database
     */
    private void loadData() {
        reference = FirebaseDatabase.getInstance().getReference();

        CSVFile csvFile = new CSVFile(inputStream);
        List locs = csvFile.read();

        for (int i = 1; i < locs.size(); i++) {
            String[] row = (String[]) locs.get(i);
            Location loc = new Location(Integer.parseInt(row[0]), row[1], row[2], row[3], row[4], row[5], row[6],
                    row[7], row[8], row[9], row[10]);
            reference.child("locations").child(Integer.toString(loc.getKey())).setValue(loc);
            getInstance().addLocation(loc);
        }

    }

    /**
     * getter for locations
     * @return list of locations
     */
    public List<Location> getLocations() { return _locations; }

    /**
     * method to add a location to the list
     * @param loc location to be added
     * @return boolean true if location added, false if location is a duplicate
     */
    public boolean addLocation(Location loc) {
        for (Location l : _locations) {
            if(l.equals(loc)) { return false; }
        }
        _locations.add(loc);
        return true;
    }

    /**
     * method to clear the locations list
     */
    public void clear(){ _locations.clear(); }

    /**
     * getter for current location
     * @return current location
     */
    public Location getCurrLocation() { return currLocation; }

    /**
     * setter for current location
     * @param loc new location to set as current location
     */
    public void setCurrLocation(Location loc) { currLocation = loc; }

    /**
     * gets location based on a key
     * @param key key to match to location
     * @return location that matches the key, null if no matches
     */
    public Location getLocationByKey(int key) {
        for (Location l : _locations) {
            if (l.getKey() == key) {
                return l;
            }
        }
        return null;
    }

    /**
     * removes a particular location from location list
     * @param loc location to be removed
     */
    public void removeLocation(Location loc) {
        boolean found = false;
        int i = 0;
        while (!found && i < _locations.size()) {
            if (loc.equals(_locations.get(i))) {
                _locations.remove(i);
                found = true;
            }
            else {
                i++;
            }
        }
    }

    /**
     * edits the name of a location
     * @param loc location to be edited
     * @param newName new name of location
     */
    public void editLocationName(Location loc, String newName) {
        for (Location l : _locations) {
            if (l.equals(loc)) {
                loc.setName(newName);
            }
        }
    }



}