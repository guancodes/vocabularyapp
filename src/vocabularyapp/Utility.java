package vocabularyapp;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;


public class Utility {
    
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

    public static String preparePairForDisplay(StringPair pair) {
        Objects.requireNonNull(pair);
        StringBuilder sb = new StringBuilder();
        sb.append("German Word: ");
        sb.append(pair.left());
        sb.append("\nEnglish translation: ");
        sb.append(pair.right());
        return sb.toString();
    }

    public static <T> String prepareMenuForDisplay(T[] options) {
        Objects.requireNonNull(options);
        StringBuilder sb = new StringBuilder();
        int counter = 1;
        for (T t: options) {
            sb.append(counter);
            sb.append(" ");
            sb.append(t.toString());
            sb.append("\n");
            counter += 1;
        }
        return sb.toString();
    }
    
}
