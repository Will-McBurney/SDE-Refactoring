package sde.hw3.main;

import java.io.File;

public class GeoTweets {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Invalid Arguments - you must have at least two for the Tweet and State filenames!");
            System.exit(0);
        }
        CommandLineUI clui = new CommandLineUI(args[0], args[1]);
        //make sure files exist
        File t = new File(args[0]), s = new File(args[1]);
        if (!t.exists()) {
            System.out.println("Tweet File does not exist. Correct your arguments and try again!");
        } else if (!s.exists()) {
            System.out.println("State File does not exist. Correct your arguments and try again!");
        } else {
            clui.start();
        }
    }
}
