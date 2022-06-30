package sde.hw3.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;

public class CommandLineUI {
    private Controller controller;
    private boolean quit = false;

    private static final int NUM_CHOICES = 3;
    Scanner sc;

    /**
     * Constructor that passes command line arguments to the constructor.
     * @param tweetFilename
     * @param statesFilename
     */
    public CommandLineUI(String tweetFilename, String statesFilename) {
        try {
            controller = new Controller(tweetFilename, statesFilename);
        } catch (IllegalArgumentException e) {
            throw e;
        }
        sc = new Scanner(System.in);
    }

    /**
     * Start up the command line system
     */
    public void start() {
        System.out.println("Welcome to the GeoTweets program!");
        System.out.println("Now loading...");
        controller.load(); //preloads tweet and state information into memory (optional, deleting this call will just
        //make the first operation slower
        while(!quit) { //repeat until quitting time
            System.out.println();//blank line for visual flair
            menu();
        }
        sc.close();
    }

    /**
     * Main menu selection
     */
    private void menu() {
        printOptions(); //print the choices
        int selection = getUserSelection(); //get the choice
        switch(selection) {
            case 1:
                displayStateTweetCounts();
                break;
            case 2:
                displayStateHashTags();
                break;
            case 3:
                quit = true;
                break;
            default:
                System.out.println("Selection is not valid, try again!");
        }

    }

    /**
     * Take in a state name and get the most popular hashtags from that state.
     * Then display the top 10 hashtags in order from most to least common.
     */
    private void displayStateHashTags() {
        String stateName = getStateEntry(); //get a state from the user
        if (!controller.isAStateName(stateName)) { //if the state doesn't exist
            System.out.println("Invalid state name. Returning to the main menu.");
            return; //end function call
        }
        //get the hash tag counts from the state
        Map<String, Integer> hashTagMap = controller.getHashTagsForState(stateName);
        //create an intermediate list for sorting purposes
        ArrayList<HashTagCount> hashTagCounts= new ArrayList<>();

        for (String hashTag : hashTagMap.keySet()) {
            hashTagCounts.add(new HashTagCount(hashTag, hashTagMap.get(hashTag)));
        }
        Collections.sort(hashTagCounts);

        for (int i = 0; i < Math.min(10, hashTagCounts.size()); i++) {
            HashTagCount htc = hashTagCounts.get(i);
            System.out.println((i + 1) + ". " + htc.getHashTag() + " - " + htc.count);
        }
    }

    /**
     * Prompts the user for a state name
     * @return
     */
    private String getStateEntry() {
        System.out.println("Enter the name of a state: ");
        return sc.nextLine().trim();
    }

    private void displayStateTweetCounts() {
        Map<State, Integer> stateTweetMap = controller.getStateTweetCounts();

        ArrayList<StateTweetCount> stateTweetCounts = new ArrayList<>();
        for (State state : stateTweetMap.keySet()) {
            stateTweetCounts.add(new StateTweetCount(state, stateTweetMap.get(state)));
        }

        Collections.sort(stateTweetCounts);

        int rank = 0;
        for(StateTweetCount stc : stateTweetCounts) {
            rank++;
            System.out.println(rank + ". " + stc.getState().getName() + " - " + stc.getCount());
        }
    }

    /**
     * Simply prints the options available to the user.
     */
    private void printOptions() {
        System.out.println("Please make your selection:");
        System.out.println("1. Show the number of tweets per state.");
        System.out.println("2. Show the hashtags for a particular state.");
        System.out.println("3. Quit the system");
    }

    /**
     * Tries to get an int selection for the menu from the user. If the user input is invalid, reprompts
     * @return
     */
    private int getUserSelection() {
        boolean hasValidSelection = false;
        int selection = -1;
        do {
            //remove whitespace around it
            String input = sc.nextLine().trim();
            try {
                selection = Integer.parseInt(input);
                if (selection > 0 && selection <= NUM_CHOICES) {
                    hasValidSelection = true; //entered a valid number between 1 up to and including NUM_CHOICES
                } else {
                    System.out.println("User error: Must enter a number 1 to " + NUM_CHOICES + "! Try again!");
                }
            } catch (NumberFormatException e) {
                System.out.println("User error: must enter a number! Try again!");
            }

        } while(!hasValidSelection); //keep going until the selection is valid
        return selection;
    }

    /**
     * Private class used to sort state-HashTagCount pairs in descending order
     */
    private static class HashTagCount implements Comparable<HashTagCount> {
        private String hashTag;
        private int count;

        private HashTagCount(String hashTag, int count) {
            this.hashTag = hashTag;
            this.count = count;
        }

        @Override
        public int compareTo(HashTagCount o) {
            return o.count - this.count;
        }

        public String getHashTag() {
            return hashTag;
        }

        public int getCount() {
            return count;
        }
    }

    /**
     * Private class used to sort state-tweetCount pairs in descending order
     */
    private static class StateTweetCount implements Comparable<StateTweetCount> {
        private State state;
        private int count;
        private StateTweetCount(State state, int count) {
            this.state = state;
            this.count = count;
        }

        @Override
        public int compareTo(StateTweetCount o) {
            return o.getCount() - this.getCount();
        }

        public State getState() {
            return state;
        }

        public int getCount() {
            return count;
        }
    }
}
