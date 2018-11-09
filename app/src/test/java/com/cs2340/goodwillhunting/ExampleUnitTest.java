package com.cs2340.goodwillhunting;

import com.cs2340.goodwillhunting.model.Location;
import com.cs2340.goodwillhunting.model.Model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private Model model;
    private Location location1;
    private Location location2;
    private Location location3;

    @Before
    public void setUp() {
        model = Model.getInstance();
        model.clear();
    }


    @Test
    public void testAddLocation() {
        location1 = new Location(1, "Donation Center", "1", "1", "Georgia Tech",
                "Atl", "GA", "30332", "Dropoff", "1111111111", "1.com");

        location2 = new Location(2, "Donation Center", "1", "1", "Georgia Tech",
                "Atl", "GA", "30332", "Dropoff", "1111111111", "1.com");

        model.addLocation(location1);
        model.addLocation(location2);
        assertEquals(location1, model.getLocations().get(0));
    }


    @Test
    public void testGetLocationByKey() {
        location1 = new Location(1, "Donation Center", "1", "1", "Georgia Tech",
                "Atl", "GA", "30332", "Dropoff", "1111111111", "1.com");

        location2 = new Location(2, "Donation Center", "1", "1", "Georgia Tech",
                "Atl", "GA", "30332", "Dropoff", "1111111111", "1.com");

        model.addLocation(location1);
        assertEquals(location1, model.getLocationByKey(1));
        assertNull(model.getLocationByKey(2));

    }

    @Test
    public void testRemoveLocation() {
        location1 = new Location(1, "Donation Center", "1", "1", "Georgia Tech",
                "Atl", "GA", "30332", "Dropoff", "1111111111", "1.com");

        location2 = new Location(2, "Donation Center", "1", "1", "Georgia Tech",
                "Atl", "GA", "30332", "Dropoff", "1111111111", "1.com");

        model.addLocation(location1);
        model.addLocation(location2);
        assertEquals(2, model.getLocations().size());
        model.removeLocation(location2);
        assertEquals(1, model.getLocations().size());

    }

    @Test
    public void testEditLocationName() {
        location1 = new Location(1, "Donation Center", "1", "1", "Georgia Tech",
                    "Atl", "GA", "30332", "Dropoff", "1111111111", "1.com");

        String newName = "Donation Center 2";

        model.addLocation(location1);
        model.editLocationName(location1, newName);
        assertEquals(newName, model.getLocations().get(0).getName());

    }
}

