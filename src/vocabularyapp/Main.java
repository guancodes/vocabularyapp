package vocabularyapp;
    
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
        System.out.println("Welcome to GuanLang German learning app!");
        VocabularyApp app = VocabularyApp.make();
        try {
            app.run();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
       
} 
