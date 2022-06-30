package sde.hw3.main;

import java.util.List;

/**
 * This abstract class describes a TweetReader. We create an abstract class so we can add new concrete implementors
 * in case the implementation needs to change. Each implementor defines it's own constructor and readTweets method.
 */






public abstract class TweetReader {
    protected List<Tweet> tweets; //tweet memo

    public TweetReader() {
        tweets = null;
    }

    public List<Tweet> getTweets() {
        if (tweets == null) {
            readTweets();
        }
        return tweets;
    }

    protected abstract void readTweets();
}
