import org.junit.Test;
import static org.junit.Assert.*;
import vocabularyapp.*;
import java.util.*;


class MockDictScanner implements DictScannable {

    private ArrayList<String> list = new ArrayList();
    
    public MockDictScanner(ArrayList<String> list) {
        this.list = list;
    }
    
    @Override
    public boolean hasNext() {
        return this.list.size() > 0;
    }
    
    @Override
    public String nextLine() {
        String value = this.list.get(0);
        this.list.remove(0);
        return value;
    }

}


class MockDictWriter implements DictWritable {
    
    public String memory = "";
    
    @Override
    public void print(String value) {
        memory += value;
    }

    @Override
    public void close() {}
}


public class UtilityTest {
    @Test
    public void testSaveData(){
        MockDictWriter writer = new MockDictWriter();
        StringPair pair1 = StringPair.make("nihao","hello");
        StringPair pair2 = StringPair.make("ta","he");
        ArrayList<StringPair> list = new ArrayList();
        list.add(pair1);
        list.add(pair2);
        Utility.saveData(writer, list);
        assertEquals("\"nihao\",\"hello\"\n" + "\"ta\",\"he\"\n",writer.memory);   
    }
    
    @Test
    public void testSaveDataEmptylist () {
        
        MockDictWriter writer = new MockDictWriter();
        ArrayList<StringPair> list = new ArrayList();
        Utility.saveData(writer, list);
        assertEquals("", writer.memory);
    }
    
    @Test
    public void testLoadData () {
        StringPair pair1 = StringPair.make("nihao","hello");
        ArrayList<StringPair> listExp = new ArrayList();
        listExp.add(pair1);
        ArrayList<String> aFile = new ArrayList();
        aFile.add("\"nihao\",\"hello\"");
        MockDictScanner scanner = new MockDictScanner(aFile);
        ArrayList<StringPair> result = new ArrayList<StringPair>();
        result = Utility.loadData(scanner).get();
        assertEquals(listExp,result);
    }
    
    @Test
    public void testLoadDataEmptyList () {
        ArrayList<String> aFile = new ArrayList();
        MockDictScanner scanner = new MockDictScanner(aFile);
        ArrayList<StringPair> result = new ArrayList<StringPair>();
        result = Utility.loadData(scanner).get();
        assertEquals(aFile,result);
    }
    
    @Test
    public void testReduceWordLengthHapppyPath () {
        StringPair pair1 = StringPair.make("nihao","hello");
        StringPair pair2 = StringPair.make("jintiantianqihao","it is a nice day today");
        StringPair pair3 = StringPair.make("zuijinzenmeyang","how are you doing lately");
        StringPair pair4 = StringPair.make("baozi","bun");
        StringPair pair5 = StringPair.make("shuijiao","dumplings");
        ArrayList<StringPair> list1 = new ArrayList();
        list1.add(pair1);
        list1.add(pair2);
        list1.add(pair3);
        list1.add(pair4);
        list1.add(pair5);
        ArrayList<StringPair> list2 = new ArrayList();
        list2.add(pair1);
        list2.add(pair4);
        ArrayList<StringPair> result = Utility.reduceByWordLength(list1, 7);
        assertEquals(list2,result);
    }
    
    @Test
    public void testReduceWordLengthNonNull () {
        try{
            Utility.reduceByWordLength(null, 7);
            fail("not gonna get in here");
        }catch(NullPointerException e){}
    }
    
    @Test
    public void testReduceWordLengthNonEmptyList () {
        ArrayList<StringPair> list1 = new ArrayList();
        ArrayList<StringPair> result = Utility.reduceByWordLength(list1, 7);
        assertEquals(list1,result);
    }
    
    @Test
    public void testReduceWordLengthEmptyResult() {
        StringPair pair1 = StringPair.make("nihao","hello");
        StringPair pair2 = StringPair.make("jintiantianqihao","it is a nice day today");
        StringPair pair3 = StringPair.make("zuijinzenmeyang","how are you doing lately");
        StringPair pair4 = StringPair.make("baozi","bun");
        StringPair pair5 = StringPair.make("shuijiao","dumplings");
        ArrayList<StringPair> list1 = new ArrayList();
        list1.add(pair1);
        list1.add(pair2);
        list1.add(pair3);
        list1.add(pair4);
        list1.add(pair5);
        ArrayList<StringPair> list2 = new ArrayList();
        ArrayList<StringPair> result = Utility.reduceByWordLength(list1, 1);
        assertEquals(list2,result);       
    }
    
    @Test
    public void testReduceWordLengthSameResult() {
        StringPair pair1 = StringPair.make("nihao","hello");
        StringPair pair2 = StringPair.make("jintiantianqihao","it is a nice day today");
        StringPair pair3 = StringPair.make("zuijinzenmeyang","how are you doing lately");
        StringPair pair4 = StringPair.make("baozi","bun");
        StringPair pair5 = StringPair.make("shuijiao","dumplings");
        ArrayList<StringPair> list1 = new ArrayList();
        list1.add(pair1);
        list1.add(pair2);
        list1.add(pair3);
        list1.add(pair4);
        list1.add(pair5);
        ArrayList<StringPair> result = Utility.reduceByWordLength(list1, 100);
        assertEquals(list1,result);
    }
    
    @Test
    public void testgenerateWordListHappyPath () {
        StringPair pair1 = StringPair.make("nihao","hello");
        StringPair pair2 = StringPair.make("jintiantianqihao","it is a nice day today");
        StringPair pair3 = StringPair.make("zuijinzenmeyang","how are you doing lately");
        StringPair pair4 = StringPair.make("baozi","bun");
        StringPair pair5 = StringPair.make("shuijiao","dumplings");
        ArrayList<StringPair> list1 = new ArrayList();
        list1.add(pair1);
        list1.add(pair2);
        list1.add(pair3);
        list1.add(pair4);
        list1.add(pair5);
        
        ArrayList<StringPair> list2 = new ArrayList();
        list2.add(pair4);
        list2.add(pair2);
        list2.add(pair1);
        Random rand = new Random(10);
        ArrayList<StringPair> result = Utility.generateWordList(list1, 3, rand);
        assertEquals(list2,result); 
        }
    @Test
    public void testgenerateWordListNoneNull () {
        StringPair pair1 = StringPair.make("nihao","hello");
        StringPair pair2 = StringPair.make("jintiantianqihao","it is a nice day today");
        StringPair pair3 = StringPair.make("zuijinzenmeyang","how are you doing lately");
        StringPair pair4 = StringPair.make("baozi","bun");
        StringPair pair5 = StringPair.make("shuijiao","dumplings");
        ArrayList<StringPair> list1 = new ArrayList();
        list1.add(pair1);
        list1.add(pair2);
        list1.add(pair3);
        list1.add(pair4);
        list1.add(pair5);
        Random rand = new Random(10);
        try{
            Utility.generateWordList(null, 3, rand);
            fail("not gonna get in here");
        }catch(NullPointerException e){}
        try{
            Utility.generateWordList(list1, 3, null);
            fail("not gonna get in here");
        }catch(NullPointerException e){}
    }
    
    @Test
    public void testgenerateWordListHugeSize () {
        StringPair pair1 = StringPair.make("nihao","hello");
        StringPair pair2 = StringPair.make("jintiantianqihao","it is a nice day today");
        StringPair pair3 = StringPair.make("zuijinzenmeyang","how are you doing lately");
        StringPair pair4 = StringPair.make("baozi","bun");
        StringPair pair5 = StringPair.make("shuijiao","dumplings");
        ArrayList<StringPair> list1 = new ArrayList();
        list1.add(pair1);
        list1.add(pair2);
        list1.add(pair3);
        list1.add(pair4);
        list1.add(pair5);
        Random rand = new Random(10);
        
        try{
            Utility.generateWordList(list1, 7, rand);
            fail("not gonna get in here");
        }catch(AssertionError e){}
    }
    
    @Test
    public void testPreparePairForDisplayHappyPath () {
        StringPair pair = StringPair.make("nihao","hello"); 
        String preparedPair = Utility.preparePairForDisplay(pair);
        assertEquals("German Word: nihao\nEnglish translation: hello", preparedPair);
    }
    
    @Test
    public void testPreparePairForDisplayNonNull () {
        try{
            Utility.preparePairForDisplay(null);
            fail("assertion failure");
        } catch(NullPointerException e) {}
    }
    
    @Test
    public void testPreparePairForDisplayEmptyPair () {
        StringPair pair = StringPair.make("",""); 
        String preparedPair = Utility.preparePairForDisplay(pair);
        assertEquals("German Word: \nEnglish translation: ", preparedPair);
    }
    
    @Test
    public void testPrepareMenuForDisplayHappyPath () {
        PROFICIENCY[] p= PROFICIENCY.values();
        String result = Utility.prepareMenuForDisplay(p);
        assertEquals("1 Beginner\n2 Intermediate\n3 Pro\n", result);
   
    }
    
    @Test
    public void testPrepareMenuForDisplayNonNull () {
        try{
            Utility.prepareMenuForDisplay(null);
            fail("insertion failure");
        } catch(NullPointerException e){}
    }

}
