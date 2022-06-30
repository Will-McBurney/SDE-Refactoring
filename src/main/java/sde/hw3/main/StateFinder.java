/**
 * Abstract definition of closest state finder. This will allow us to update how this is done later.
 */

package sde.hw3.main;


public interface StateFinder {
    public abstract State findState(Tweet t);
}
