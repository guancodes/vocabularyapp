package vocabularyapp;


public enum COMMITMENT {
    CASUAL(15, "Casual"),
    SERIOUS(30, "Serious"),
    HARDCORE(50, "Hardcore"),
    GODLIKE(80, "Godlike");
//Private
    private final int pairs;
    private final String acronym;
    private COMMITMENT(int pairs, String acronym){
        this.pairs = pairs;
        this.acronym = acronym;
    }
//Queries
    public int pairs() {
        return this.pairs;
    }
    
    @Override
    public String toString() {
        return this.acronym;
    }
    
}
