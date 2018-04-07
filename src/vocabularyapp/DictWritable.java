package vocabularyapp;

import java.io.Closeable;

/**
 * This interface extends Closeable class
 * having following methods was introduced to mock unit test
 * @author guanwang
 */
public interface DictWritable extends Closeable {
    
    /**
     * prints given value
     * @param value string to be printed
     */
    public void print(String value);
}
