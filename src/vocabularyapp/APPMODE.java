package vocabularyapp;

/**
 * Two modes of the app
 * @author guanwang
 */
public enum APPMODE {
    /**
     * Learning mode
     */
    LEARNING("Learning"),
    /**
     * Recap mode
     */
    RECAP("Recap");

    private String acronym;

    private APPMODE(String acronym) {
        this.acronym = acronym;
    }

//Queries
    @Override
    public String toString() {
        return this.acronym;
    }

}
