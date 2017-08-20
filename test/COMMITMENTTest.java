import org.junit.Test;
import static org.junit.Assert.*;
import vocabularyapp.COMMITMENT;


public class COMMITMENTTest {
    
    @Test public void testCASUAL(){
        COMMITMENT c = COMMITMENT.CASUAL;
        assertEquals(15, c.pairs());
        assertEquals("Casual", c.toString());
    }
    
    @Test public void testSERIOUS(){
        COMMITMENT c = COMMITMENT.SERIOUS;
        assertEquals(30, c.pairs());
        assertEquals("Serious", c.toString());
    }
    
    @Test public void testHARDCORE(){
        COMMITMENT c = COMMITMENT.HARDCORE;
        assertEquals(50, c.pairs());
        assertEquals("Hardcore", c.toString());
    }
    
    @Test public void testGODLIKE(){
        COMMITMENT c = COMMITMENT.GODLIKE;
        assertEquals(80, c.pairs());
        assertEquals("Godlike", c.toString());
    }
}
