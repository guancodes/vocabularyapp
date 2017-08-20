package vocabularyapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class DictScanner implements DictScannable {
    
    private Scanner scanner;
    
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
