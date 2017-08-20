package vocabularyapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;


public class DictWriter implements DictWritable {
    
    private PrintWriter writer;
   
    public DictWriter(File file) throws FileNotFoundException, UnsupportedEncodingException {
        this.writer = new PrintWriter(file, "UTF-8");
    }

    @Override
    public void print(String value) {
        this.writer.print(value);
    }
    
    @Override
    public void close() {
        this.writer.close();
    }
    
}
