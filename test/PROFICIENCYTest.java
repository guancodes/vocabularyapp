import org.junit.Test;
import static org.junit.Assert.*;
import vocabularyapp.PROFICIENCY;


public class PROFICIENCYTest {
    
    @Test
    public void testBEGINNER(){
        PROFICIENCY p = PROFICIENCY.BEGINNER;
        assertEquals(10, p.maxLetters());
        assertEquals("Beginner", p.toString());
    }
    
    @Test
    public void testINTERMEDIATE(){
        PROFICIENCY p = PROFICIENCY.INTERMEDIATE;
        assertEquals(14, p.maxLetters());
        assertEquals("Intermediate", p.toString());
    }
    
    @Test
    public void testPRO(){
        PROFICIENCY p = PROFICIENCY.PRO;
        assertEquals(50, p.maxLetters());
        assertEquals("Pro", p.toString());
    }
}
