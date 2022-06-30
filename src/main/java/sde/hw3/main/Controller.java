/**
 * This class provides a menu of options to the UI layer to interact with the data layer. Effectively, each "menu choice"
 * in the UI maps to one controller function.
 */

package sde.hw3.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller {
    private TweetReader tweetReader; //The class responsible for getting tweets
    private StateReader stateReader; //The class responsible for getting states
    private StateFinder stateFinder; //The class responsible for finding the state for a coordinate

    //Constructor
    public Controller(String tweetFilename, String stateFilename) {
        //create tweet reader
        tweetReader = new TSVTweetReader(tweetFilename);

        //create state reader
        StateReaderFactory srf = new StateReaderFactory();
        stateReader = srf.getStateReader(stateFilename);

        //create state finder
        stateFinder = new ClosestCenterStateFinder(stateReader.getStates());
    }

    /**
     * Returns a map of States to the number of tweets from that state.
     */
    public Map<State, Integer> getStateTweetCounts() {
        //initialize the output map
        Map<State, Integer> stateTweetCounts = new HashMap<>();

        //for each tweet
        for (Tweet t : tweetReader.getTweets()) {
            //find the nearest state
            State s = stateFinder.findState(t);

            //add one to that state's tweet count
            if (stateTweetCounts.containsKey(s)) {
                stateTweetCounts.put(s, stateTweetCounts.get(s) + 1); //increment the value
            } else { //if not already in the map
                stateTweetCounts.put(s, 1);
            }
        }
        return stateTweetCounts;
    }

    /**
     *
     */
    public Map<String, Integer> getHashTagsForState(String stateName) {
        Map<String, Integer> hashTagCounts = new HashMap<>();
        for (Tweet t : tweetReader.getTweets()) {
        	State state = stateFinder.findState(t);
            if (state.getName().equalsIgnoreCase(stateName)) {
                List<String> hashTags = t.getHashTags();
                for (String hashTag : hashTags) {
                    if (hashTagCounts.containsKey(hashTag)) {
                        hashTagCounts.put(hashTag, hashTagCounts.get(hashTag) + 1);
                    } else {
                        hashTagCounts.put(hashTag, 1);
                    }
                }
            }
        }

        return hashTagCounts;
    }

    /**
     * Force the TweetReader and StateReader to load in their data into memory.
     */
    public void load() {
        tweetReader.getTweets();
        stateReader.getStates();
    }

    public boolean isAStateName(String s) {
        List<State> states = stateReader.getStates();
        for (State state : states) {
            if (state.getName().equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }
}
