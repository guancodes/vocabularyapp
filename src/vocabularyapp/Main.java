package vocabularyapp;

import java.sql.SQLException;

/**
 * Driver of the app
 * @author guanwang
 */
public class Main {

    /**
     * Creates a new vocabulary app and starts running it
     * @param args 
     */
    public static void main(String[] args) {
        System.out.println("Starting app ...");
        VocabularyApp app = new VocabularyApp();
        try {
            app.run();
        } catch (SQLException e) {
       		Utility.printSQLException(e);
       	} catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
       
} 
