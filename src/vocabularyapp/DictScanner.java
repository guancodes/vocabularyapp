package vocabularyapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * Creates a bridge that wraps a real Scanner in the object
 * @author guanwang
 */

public class DictScanner implements DictScannable {
    
    private Scanner scanner;
    
    /**
     * Creates a new DictScanner from a given source file
     * @param file file to be fed into scanner
     * @throws FileNotFoundException 
     */
    public DictScanner(File file) throws FileNotFoundException {
        this.scanner = new Scanner(file);
    }

    @Override
    public boolean hasNext() {
        return scanner.hasNext();
    }

    @Override
    public String nextLine() {
        return this.scanner.nextLine();
    }
    
}
