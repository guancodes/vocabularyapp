


import org.junit.Test;
import static org.junit.Assert.*;
import vocabularyapp.*;
import java.util.*;
/**
 *
 * @author guanwang
 */
public class UtilityTest {
    //@Test
    // public void hello() {}
    
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
    public void generateWordListHappyPath () {
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
    public void generateWordListNoneNull () {
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
    public void generateWordListHugeSize () {
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
    
    //private
    
    
    
}
