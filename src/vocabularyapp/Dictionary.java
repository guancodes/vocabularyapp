package vocabularyapp;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;


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
        Scanner fileScan = new Scanner(this.file);
        ArrayList<StringPair> list = new ArrayList();
        while (fileScan.hasNext()){
            StringTokenizer st = new StringTokenizer(fileScan.nextLine(), ",");
            String left = st.nextToken().replace("\"", "");
            String right = st.nextToken().replace("\"", "");
            StringPair strPair = StringPair.make(left, right);
            list.add(strPair);
        }
        return Optional.of(list);
    }
    
    //Commands
    
    public void save(ArrayList<StringPair> list) throws IOException {
        try (PrintWriter writer = new PrintWriter(this.file, "UTF-8")) {
            for (StringPair pair : list){
                writer.print("\"");
                writer.print(pair.left());
                writer.print("\"");
                writer.print(",");
                writer.print("\"");
                writer.print(pair.right());
                writer.println("\"");
            }
        }
    }
    
}
