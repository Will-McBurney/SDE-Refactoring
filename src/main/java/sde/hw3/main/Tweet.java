package sde.hw3.main;

import java.util.ArrayList;
import java.util.regex.*;

public class Tweet {
	private static Pattern hashTagPattern = Pattern.compile("#[A-Za-z0-9]+");
	
    private Coordinate coordinates; //location where the tweet originates
    private String timeStamp; //The timestamp of when the tweet was sent
    private String text; //the content of the tweet
    private ArrayList<String> hashTagsMemo; //a memo of hashtags, populated after the first call to getHashTags

    //constructor
    public Tweet(Coordinate coordinates, String timeStamp, String text) {
        this.coordinates = coordinates;
        this.timeStamp = timeStamp;
        this.text = text;
        hashTagsMemo = null;
    }
    //simple getters
    public Coordinate getCoordinates() {
        return coordinates;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String text() {
        return text;
    }

    /**
     * Get the hashtags from a tweet. This uses a memo ArrayList to store so this function needs to parse a string
     * only once. After the first time parsing hashTags, it stores the hashTags in memory to be recalled later.
     * @return
     */
    public ArrayList<String> getHashTags() {
        //if we have populated hashTagsMemo before
        if (hashTagsMemo != null) {
            return hashTagsMemo;
        }
        
        hashTagsMemo = new ArrayList<>();
        Matcher matcher = hashTagPattern.matcher(text);
        
        while (matcher.find()) {
        	hashTagsMemo.add(matcher.group().toLowerCase());
        }
  
        return hashTagsMemo;
    }
}
