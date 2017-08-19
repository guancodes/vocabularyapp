package vocabularyapp;

/**
 *
 * @author guanwang
 */
public enum PROFICIENCY {
	
	BEGINNER(10, "Beginner"),
	INTERMEDIATE(14, "Intermediate"),
        PRO(30, "Pro"); //Picking 20 to be the natural length of any word
//private
	private final int maxLetters;
	private final String acronym;
	
	private PROFICIENCY(int maxLetters, String acronym) {
		this.maxLetters = maxLetters;
		this.acronym = acronym;
	}
//Queries
	public int maxLetters() {
		return this.maxLetters;
	}
        @Override	
	public String toString () {
		return this.acronym;
	}
}	
