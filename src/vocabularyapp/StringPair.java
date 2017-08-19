
package vocabularyapp;

import java.util.Objects;

/**
 * A representation of a pair of strings.
 * @author Guan Wang
 */
public class StringPair {
//Private
	
	private final String left, right;

	private StringPair (String left, String right) {	
		Objects.requireNonNull(left);
		Objects.requireNonNull(right);
		this.left = left;
		this.right = right;
	}
	
//Factories
	
	/**
	 * Creates a new pair.
	 * @param left the left element
	 * @param right the right element
	 * @return a new pair with the given elements
	 */
	public static StringPair make (String left, String right) {
		//no need to check for null because the constructor will do the work
		return new StringPair(left, right);
	}
	
	/**
	 * Creates a new pair based on old, with a different left element.
	 * @param old a pair (can't be null)
	 * @param newLeft a new left element (can't be null)
	 * @return a new pair with newLeft as the left element, and the same right element as old
	 */
	public static StringPair newLeft (StringPair old, String newLeft) {	
		//no need to check newLeft for null, constructor will do the job
		Objects.requireNonNull(old);
		return new StringPair(newLeft, old.right);
	}
	
	/**
	 * Creates a new pair based on old, with a different right element.
	 * @param old a pair (can't be null)
	 * @param newRight a new right element (can't be null)
	 * @return a new pair with newRight as the right element, and the same left element as old
	 */
	public static StringPair newRight (StringPair old, String newRight) {	
		Objects.requireNonNull(old);
		return new StringPair(old.left, newRight);
		
	}

//Queries
	
	/**
	 * @return the left element
	 */
	public String left () {
		return this.left;
	}
	
	/**
	 * @return the left element
	 */
	public String right () {
		return this.right;
	}
	
	@Override
	public boolean equals (Object other) {
		if (other instanceof StringPair) {	
			StringPair sp = (StringPair)other;
			return sp.left.equals(left) && sp.right.equals(right);
		}
		return false;
	}
	
	@Override
	public int hashCode () { 
		return Objects.hash(left, right);
	}
	
	@Override
	public String toString () {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append(left);
		sb.append(", ");
		sb.append(right);
		sb.append(")");
		return sb.toString();
	}
	
	//Commands

}

