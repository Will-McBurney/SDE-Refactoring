/**
 * State Finder class that finds the closest state using euclidean distance to the nearest state center.
 */

package sde.hw3.main;


import java.util.List;

public class ClosestCenterStateFinder implements StateFinder {
    private List<State> states;

    //Constructor takes in a list of states to compare against
    public ClosestCenterStateFinder(List<State> states) {
        if (states == null || states.size() == 0) {
            throw new IllegalArgumentException("Error: StateFinder input ArrayList may not be empty or null.");
        }
        this.states = states;
    }

    @Override
    //Finds the nearest state
    public State findState(Tweet t) {
    	Coordinate c = t.getCoordinates();
        State closest = null;
        double minimumDistance = Double.MAX_VALUE;
        for (State s : states) {
            //get the distance from c to this state
            double distance = c.euclideanDistanceTo(s.getCoordinates());

            if (distance < minimumDistance) { //if new minimum found
                closest = s; //s is the new closest state
                minimumDistance = distance;
            }
        }
        return closest;
    }
}
