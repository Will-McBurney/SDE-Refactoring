/**
 * This class implements StateReader by reading in a CSV file
 */

package sde.hw3.main;



import java.io.*;
import java.util.ArrayList;

public class CSVStateReader extends StateReader {
    private String stateFilename; //csv filename

    public CSVStateReader(String stateFilename) {
        super();
        this.stateFilename = stateFilename;
    }

    /**
     * Initializes and populates the parent "states" ArrayList
     */
    @Override
    protected void readStates() {
        states = new ArrayList<State>();
        try {
            //create a buffered reader
            BufferedReader br = new BufferedReader(new FileReader(new File(stateFilename)));
            //read in the first line
            String line = br.readLine();
            while (line != null) { //stop when we reach the end of the file
                if (line.length() > 2) { //make sure we are ignoring blank or nearly blank lines
                    //split and get state name
                    String[] ls = line.split(",");
                    String stateName = ls[0];

                    //extract Coordinate object from state
                    double latitude = Double.parseDouble(ls[1].trim());
                    double longitude = Double.parseDouble(ls[2].trim());
                    Coordinate c = new Coordinate(latitude, longitude);

                    //create state object and add it to states list
                    State newState = new State(stateName, c);
                    states.add(newState);
                }
                //get the next line
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            return;
        } catch (IOException e) {
            return;
        }
    }
}
