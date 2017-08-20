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
    public static Dictionary make(String filename){
        return new Dictionary(filename);
    }
    
    public boolean exists() {
        return this.file.exists();
    }
    
    //Queries

    public Optional<ArrayList<StringPair>> load() throws FileNotFoundException {
        if (!file.exists()){
            return Optional.empty();
        }
        DictScanner scanner = new DictScanner(this.file);
        return Utility.loadData(scanner);
    }
    
    //Commands
    
    public void save(ArrayList<StringPair> list) throws IOException {
        try (DictWriter writer = new DictWriter(this.file)) {
            Utility.saveData(writer, list);
        }
    }
    
}
