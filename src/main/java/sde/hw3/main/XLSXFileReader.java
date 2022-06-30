/**
 * Read in states from an excel file
 */

package sde.hw3.main;

public class XLSXFileReader extends StateReader {
    String filename; //xlsx filename

    public XLSXFileReader(String filename) {
        this.filename = filename;
    }

    /**
     * This method populates the parent "states" list by reading from an xlsx file.
     */
    @Override
    protected void readStates() {
        //TODO: unimplemented behavior
    }
}
