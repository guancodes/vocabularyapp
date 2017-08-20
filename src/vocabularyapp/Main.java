package vocabularyapp;
    

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to GuanLang German learning app!");
        VocabularyApp app = VocabularyApp.make();
        try {
            app.run();
        } catch (Exception e) {
            System.out.println(e.getClass() + ": " + e.getMessage());
            System.exit(1);
        }
    }
       
} 
