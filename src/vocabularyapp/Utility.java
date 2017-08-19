/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vocabularyapp;

import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author guanwang
 */
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
        
    //returns an ArrayList of string pairs read from the file(later need to change)
    //the file directory
    public static ArrayList<StringPair> wordRetrieval(Scanner scanner) {
//        File aFile =new File("/Users/guanwang/Downloads/en-de.txt");
//        Scanner fileScan = new Scanner(aFile);
        ArrayList<StringPair> list= new ArrayList<StringPair>();
        while (scanner.hasNext()){
            //System.out.println(fileScan.nextLine());

            StringTokenizer st = new StringTokenizer(scanner.nextLine(), ",");
            String left;
            String right;
            left = st.nextToken().replace("\"", "");
            right = st.nextToken().replace("\"", "");
            StringPair strPair = StringPair.make(left, right);
            list.add(strPair);
        }

        return list;
    }
}
