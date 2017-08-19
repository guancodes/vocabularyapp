
package vocabularyapp;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;
import java.util.Random;


public class VocabularyApp {
    private static Scanner input = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        System.out.println("Welcome to GuanLang German learning app!");
        ArrayList<StringPair> list = getRecentList ();
        System.out.println();
        if (list == null) {
            PROFICIENCY prof = select(PROFICIENCY.values(), "proficiency level");
            System.out.println(prof);
            System.out.println();
            COMMITMENT commit = select(COMMITMENT.values(), "commitment level");
            System.out.println(commit);
            System.out.println();
            File aFile =new File("/Users/guanwang/Downloads/en-de.txt");
            Scanner fileScan = new Scanner(aFile);
            list= Utility.wordRetrieval(fileScan);
            //System.out.println(list);
            list = Utility.reduceByWordLength(list, prof.maxLetters());
            Random rand = new Random();
            list = Utility.generateWordList(list, commit.pairs(), rand);
        }
        wordDisplay(list);
        saveWordList(list);
        
    }
    
    
    //select from the menu, returns the Enum type option that got selected
    //prints the option selected to the user
    private static <T> T select(T[] options, String type) {
        System.out.println("Please select your " + type + ":");
        StringBuilder sb = new StringBuilder();
        int counter = 1;
        for (T t: options) {
            sb.append(counter);
            sb.append(" ");
            sb.append(t.toString());
            sb.append("\n");
            counter += 1;
        }
        System.out.println(sb);
        int selected = input.nextInt();
        return options[selected - 1];
    }
   
   private static void wordDisplay(ArrayList<StringPair> list){
           
        for(StringPair sp : list){
            System.out.print("German word: ");
            System.out.println(sp.left());
            System.out.print("English translation: ");
            System.out.println(sp.right());
            System.out.println();
            System.out.println("Ready for the next word? Press N");
            String userReady = input.next();
            System.out.println();
            if(!userReady.equals("N") && !userReady.equals("n")){
                break;
            }

        }
    }
   
    private static void saveWordList (ArrayList<StringPair> list) {
        try{
            PrintWriter writer = new PrintWriter("/Users/guanwang/Downloads/output.txt", "UTF-8");
            for (StringPair pair : list){
                writer.print("\"");
                writer.print(pair.left());
                writer.print("\"");
                writer.print(",");
                writer.print("\"");
                writer.print(pair.right());
                writer.println("\"");
            }
            writer.close();
        } catch (IOException e) {
        }
    }
   
    private static ArrayList<StringPair> getRecentList () throws IOException {
        System.out.println("Would you like to review your most recent study? Y/N");
        String reviewOrNot = input.next();
        if(reviewOrNot.equals("y")||reviewOrNot.equals("Y")){
            File aFile = new File("/Users/guanwang/Downloads/output.txt");
            if (!aFile.exists()) {
                System.out.println("You don't have a recent study record!");
                return null;                
            }
            Scanner fileScan = new Scanner(aFile);
            ArrayList<StringPair> list= Utility.wordRetrieval(fileScan);
            fileScan.close();
            return list;
        }
        else{
            return null;
        }
    }
   
} 
