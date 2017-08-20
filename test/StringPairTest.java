import org.junit.Test;
import static org.junit.Assert.*;
import vocabularyapp.StringPair;
import java.util.Random;


public class StringPairTest {
    private Random rng = new Random(42);

	@Test
	public void testMakeBasic () {
		StringPair p = StringPair.make("foo", "bar");
		assertNotNull(p);
		assertEquals("foo", p.left());
		assertEquals("bar", p.right());
	}
	
	@Test
	public void testNewLeftRejectsNullBoth () {
		try {
			StringPair.newLeft(null, null);
			fail("newLeft should reject nulls");
		} catch (NullPointerException e) {}
	}
	
	@Test
	public void testNewLeftRejectsNullLeft () {
		try {
			StringPair.newLeft(null, "baz");
			fail("newLeft should reject nulls");
		} catch (NullPointerException e) {}
	}
	
	@Test
	public void testNewLeftRejectsNullRight () {
		try {
			StringPair p = StringPair.make("foo", "bar");
			StringPair.newLeft(p, null);
			fail("newLeft should reject nulls");
		} catch (NullPointerException e) {}
	}
	
	@Test
	public void testNewRightRejectsNullBoth () {
		try {
			StringPair.newRight(null, null);
			fail("newRight should reject nulls");
		} catch (NullPointerException e) {}
	}
	
	@Test
	public void testNewRightRejectsNullLeft () {
		try {
			StringPair.newRight(null, "baz");
			fail("newRight should reject nulls");
		} catch (NullPointerException e) {}
	}
	
	@Test
	public void testNewRightRejectsNullRight () {
		try {
			StringPair p = StringPair.make("foo", "bar");
			StringPair.newRight(p, null);
			fail("newRight should reject nulls");
		} catch (NullPointerException e) {}
	}
	
	@Test
	public void testNewLeftBehaviour () {
		StringPair old = StringPair.make("foo", "bar");
		StringPair p = StringPair.newLeft(old, "baz");
		assertNotNull(p);
		assertEquals("baz", p.left());
		assertEquals("bar", p.right());
		assertEquals("foo", old.left());
		assertEquals("bar", old.right());
	}
	
	@Test
	public void testNewRightBehaviour () {
		StringPair old = StringPair.make("foo", "bar");
		StringPair p = StringPair.newRight(old, "baz");
		assertNotNull(p);
		assertEquals("foo", p.left());
		assertEquals("baz", p.right());
		assertEquals("foo", old.left());
		assertEquals("bar", old.right());
	}
	
	@Test
	public void testEqualityBasic () {
		for (int i = 0; i < 1000000; i++) {
			String[] bases = new String[4];
			for (int j = 0; j < bases.length; j++) {
				bases[j] = rand();
			}
			StringPair p1 = StringPair.make(bases[0], bases[1]);
			StringPair p2 = StringPair.make(bases[2], bases[3]);
			boolean matchLeft = bases[0].equals(bases[2]);
			boolean matchRight = bases[1].equals(bases[3]);
			assertEquals(matchLeft && matchRight, p1.equals(p2));
		} 
	}
	
	@Test
	public void testEqualitySelf () {
		for (int i = 0; i < 1000000; i++) {
			StringPair p = StringPair.make(rand(), rand());
			assertTrue(p.equals(p));
		}
	}
	
	@Test
	public void testEqualityNull () {
		for (int i = 0; i < 1000000; i++) {
			StringPair p = StringPair.make(rand(), rand());
			assertFalse(p.equals(null));
		}
	}
	
	@Test
	public void testEqualityCommutes () {
		for (int i = 0; i < 1000000; i++) {
			StringPair p1 = StringPair.make(rand(), rand());
			StringPair p2 = StringPair.make(rand(), rand());
			assertEquals(p1.equals(p2), p2.equals(p1));
		}
	}
	
	@Test
	public void testEqualityAssociates () {
		for (int i = 0; i < 1000000; i++) {
			String left = rand();
			String right = rand();
			StringPair[] ps = new StringPair[3];
			for (int j = 0; j < ps.length; j++) {
				ps[j] = StringPair.make(left, right);
			}
			boolean eq = ps[0].equals(ps[1]) && ps[1].equals(ps[2]);
			assertEquals(eq, ps[0].equals(ps[2]));
		}
	}
	
	@Test
	public void testHashCode () {
		for (int i = 0; i < 1000000; i++) {
			String x = rand();
			String y = rand();
			StringPair p1 = StringPair.make(x, y);
			StringPair p2 = StringPair.make(x, y);
			assertEquals(p1.equals(p2), p1.hashCode() == p2.hashCode());
		}
	}
	
	@Test
	public void testToString () {
		StringPair p1 = StringPair.make("string1", "string2");
		String expected = "(string1, string2)";
		String actual = p1.toString();
		assertEquals(expected, actual);
	}
	
	// Private
	
	private String rand () { return Integer.toString(rng.nextInt(20) - 10); }
    

  
}
