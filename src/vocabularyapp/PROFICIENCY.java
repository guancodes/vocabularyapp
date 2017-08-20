package vocabularyapp;

/**
 * Proficiency levels that can be selected by users.
 * maxLetters indicates the maximum length of words to be used
 * @author guanwang
 */
public enum PROFICIENCY {
	/**
         * Beginner level
         */
	BEGINNER(10, "Beginner"),
        /**
         * Intermediate level
         */
	INTERMEDIATE(14, "Intermediate"),
        /**
         * Pro level
         */
        PRO(50, "Pro"); 
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
