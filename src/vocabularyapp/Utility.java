package vocabularyapp;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.StringTokenizer;


public class Utility {
    /**
     * Reduces the list by only allowing a specific word length
     * @param list the list that's worked on
     * @param length the word length allowed
     * @return a new list with words of given word length
     */
    public static ArrayList<StringPair> 
        reduceByWordLength(ArrayList<StringPair> list, int length){
        Objects.requireNonNull(list);
        ArrayList<StringPair> result = new ArrayList();
        for (StringPair pair : list) {
            if (pair.left().length() <= length) {
                result.add(pair);
            }
        }
        return result;
    }
    
    /**
     * Generates a given sized list of random words from the given list
     * @param list the source list
     * @param size the size of the new list
     * @param rand a random number generator
     * @return a new list of from the given list
     */    
    public static ArrayList<StringPair> 
        generateWordList(ArrayList<StringPair> list, int size, Random rand) {
        Objects.requireNonNull(list);
        Objects.requireNonNull(rand);
        assert(list.size() > size);
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
    /**
     * Converts a StringPair to a string that's ready for display
     * @param pair the StringPair to be displayed
     * @return a string how the StringPair is going to print out
     */
    public static String preparePairForDisplay(StringPair pair) {
        Objects.requireNonNull(pair);
        StringBuilder sb = new StringBuilder();
        sb.append("German Word: ");
        sb.append(pair.left());
        sb.append("\nEnglish translation: ");
        sb.append(pair.right());
        return sb.toString();
    }
    
    /**
     * Converts an Array of options to a string that's ready for display
     * @param <T> one of the ENUMs
     * @param options the possible values inside a single enumeration object
     * @return a string how the options are going to print out
     */
    public static <T> String prepareMenuForDisplay(T[] options) {
        Objects.requireNonNull(options);
        StringBuilder sb = new StringBuilder();
        int counter = 1;
        for (T t : options) {
            sb.append(counter);
            sb.append(" ");
            sb.append(t.toString());
            sb.append("\n");
            counter += 1;
        }
        return sb.toString();
    }
    
    /**
     * Loads data from a given scanner
     * @param scanner a DictSannable used to parse the data
     * @return an Optional of ArrayList of StringPair that's ready for use
     */
    public static Optional<ArrayList<StringPair>> loadData(DictScannable scanner) {
        ArrayList<StringPair> list = new ArrayList();
        while (scanner.hasNext()) {
            StringTokenizer st = new StringTokenizer(scanner.nextLine(), ",");
            String left = st.nextToken().replace("\"", "");
            String right = st.nextToken().replace("\"", "");
            StringPair strPair = StringPair.make(left, right);
            list.add(strPair);
        }
        return Optional.of(list);
    }
    
    /**
     * Writes data of a given list into a file
     * @param writer a DictWritable used to record data into files
     * @param list an ArrayList of StringPair that contains data to be written into
     * files
     */
    public static void saveData(DictWritable writer, ArrayList<StringPair> list) {
        for (StringPair pair : list) {
            writer.print("\"");
            writer.print(pair.left());
            writer.print("\"");
            writer.print(",");
            writer.print("\"");
            writer.print(pair.right());
            writer.print("\"\n");
        }
    }
    
}
