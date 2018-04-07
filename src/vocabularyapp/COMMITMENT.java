package vocabularyapp;

/**
 * Commitment levels that can be selected by users. pairs indicates the number
 * of word pairs to use
 * @author guanwang
 */
public enum COMMITMENT {
    /**
     * Casual level
     */
    CASUAL(15, "Casual"),
    /**
     * Serious level
     */
    SERIOUS(30, "Serious"),
    /**
     * Hardcore level
     */
    HARDCORE(50, "Hardcore"),
    /**
     * Godlike level
     */
    GODLIKE(80, "Godlike");
//Private
    private final int pairs;
    private final String acronym;

    private COMMITMENT(int pairs, String acronym) {
        this.pairs = pairs;
        this.acronym = acronym;
    }
//Queries

    /**
     * @return the pair
     */
    public int pairs() {
        return this.pairs;
    }

    @Override
    public String toString() {
        return this.acronym;
    }

}
