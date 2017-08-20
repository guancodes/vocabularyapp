package vocabularyapp;

import java.io.Closeable;


public interface DictWritable extends Closeable {
    
    public void print(String value);
}
