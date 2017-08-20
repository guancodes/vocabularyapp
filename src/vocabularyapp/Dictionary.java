package vocabularyapp;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Dictionary {
    //private
    private File file; 
    
    private Dictionary(String filename){
        this.file = new File(filename);
    }
    
    //factories
    
    /**
     * Creates a new Dictionary
     * @param filename string that has the name and path of the file
     * @return a new dictionary with the given file 
     */
    public static Dictionary make(String filename){
        return new Dictionary(filename);
    }
    
    //Queries
    /**
     * Checks if the file member exists
     * @return true if the file exists, false otherwise
     */
    public boolean exists() {
        return this.file.exists();
    }
    
    /**
     * Loads data if the file exists
     * @return an optional of ArrayList of StringPair by calling an Utility method
     * @throws FileNotFoundException 
     */
    public Optional<ArrayList<StringPair>> load() throws FileNotFoundException {
        if (!file.exists()){
            return Optional.empty();
        }
        DictScanner scanner = new DictScanner(this.file);
        return Utility.loadData(scanner);
    }
    
    //Commands
    /**
     * Saves a given list into a file
     * @param list list of data to be saved into a file
     * @throws IOException 
     */
    public void save(ArrayList<StringPair> list) throws IOException {
        try (DictWriter writer = new DictWriter(this.file)) {
            Utility.saveData(writer, list);
        }
    }
    
}
