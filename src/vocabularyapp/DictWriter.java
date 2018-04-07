package vocabularyapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Creates a bridge that wraps a real PrintWriter in the object
 * @author guanwang
 */
public class DictWriter implements DictWritable {

    private PrintWriter writer;

    /**
     * Creates a new DictWriter
     * @param file file to be written into
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
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
