
package vocabularyapp;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.Random;


public class VocabularyApp {
    private static Scanner input = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        System.out.println("Welcome to GuanLang German learning app!");
        PROFICIENCY prof = select(PROFICIENCY.values(), "proficiency level");
        System.out.println(prof);
        System.out.println();
        COMMITMENT commit = select(COMMITMENT.values(), "commitment level");
        System.out.println(commit);
        System.out.println();
        ArrayList<StringPair> list= wordRetrieval();
        System.out.println(list);
        
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
    
    //returns an ArrayList of string pairs read from the file(later need to change)
    //the file directory
    
    private static ArrayList<StringPair> wordRetrieval() throws IOException {
        File aFile =new File("/Users/guanwang/Downloads/en-de.txt");
        Scanner fileScan = new Scanner(aFile);
        ArrayList<StringPair> list= new ArrayList<StringPair>();
        while (fileScan.hasNext()){
            //System.out.println(fileScan.nextLine());
            
            StringTokenizer st = new StringTokenizer(fileScan.nextLine(), ",");
            String left;
            String right;
            left = st.nextToken().replace("\"", "");
            right = st.nextToken().replace("\"", "");
            StringPair strPair = StringPair.make(left, right);
            list.add(strPair);
        }
        
        return list;
    }
    
    private static ArrayList<StringPair> reduceByWordLength(ArrayList<StringPair> list, int length){
        ArrayList<StringPair> result = new ArrayList();
        for (StringPair pair : list) {
            if (pair.left().length() <= length) {
                result.add(pair);
            }
        }
        return result;
    }
    
    private static ArrayList<StringPair> generateWordList(ArrayList<StringPair> list, int size, Random rand) {
        int listSize = list.size();
        ArrayList<Integer> indices = new ArrayList();
        for (int i=0; i<listSize; i++) {
            indices.add(i);
        }
        ArrayList<StringPair> result = new ArrayList();
        while (result.size() < size) {
            int index = rand.nextInt(indices.size());
            int listIndex = indices.get(index);
            indices.remove(index);
            result.add(list.get(listIndex));
        }
        return result;
    }
}
