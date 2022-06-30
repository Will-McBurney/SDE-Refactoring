package sde.hw3.main;

import java.util.List;

public class Coordinate {
    private double longitude, latitude;
    //constructor
    public Coordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    //getters
    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    /**
     * Euclidean distance from one coordinate to another
     * @param c
     * @return
     */
    public double euclideanDistanceTo(Coordinate c) {
        double dy = this.latitude - c.latitude;
        double dx = this.longitude - c.longitude;
        return Math.sqrt((dy * dy) + (dx * dx));
    }
}
