package com.cs2340.goodwillhunting.model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


import com.cs2340.goodwillhunting.R;
import com.cs2340.goodwillhunting.controllers.CSVFile;
import com.cs2340.goodwillhunting.controllers.LocationListActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Model {

    private static final Model _instance = new Model();
    public static Model getInstance() { return _instance; }

    private List<Location> _locations;
    private Location currLocation;
    private DatabaseReference reference;
    private InputStream inputStream;


    private Model() {
        _locations = new ArrayList<>();
        //loadData();
    }

    //public void initInputStream(InputStream inputStream) { this.inputStream = inputStream; }

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

    public List<Location> getLocations() { return _locations; }

    public boolean addLocation(Location loc) {
        for (Location l : _locations) {
            if(l.equals(loc)) { return false; }
        }
        _locations.add(loc);
        return true;
    }

    public void clear(){ _locations.clear(); }

    public Location getCurrLocation() { return currLocation; }
    public void setCurrLocation(Location loc) { currLocation = loc; }

    public Location getLocationByKey(int key) {
        for (Location l : _locations) {
            if (l.getKey() == key) {
                return l;
            }
        }
        return null;
    }

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

    public void editLocationName(Location loc, String newName) {
        for (Location l : _locations) {
            if (l.equals(loc)) {
                loc.setName(newName);
            }
        }
    }

}