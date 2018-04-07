package vocabularyapp;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
/**
 * The controller of the app
 * @author guanwang
 */
public class Controller {

    private Model model;
    private APPMODE appmode = APPMODE.LEARNING;
    private PROFICIENCY proficiency = PROFICIENCY.BEGINNER;
    private COMMITMENT commitment = COMMITMENT.CASUAL;
    private ArrayList<StringPair> dictionary;
    private ArrayList<StringPair> recentdict;
    private ArrayList<StringPair> dictionary_user;
    private int dictionary_index = 0;
    private ArrayList<StringPair> recentdict_user;
    private int recentdict_index = 0;
    private int learned_count = 1;
    private Random rand = new Random();

    public Controller(Model model) {
        this.model = model;
        this.dictionary = this.model.getDictionary();
        reset();
    }

    /**
     * Resets the state of the app
     */
    public void reset() {
        System.out.println("reset()");
        this.recentdict = this.model.getRecentDict();
        this.dictionary_index = 0;
        this.recentdict_index = 0;
        learned_count = 1;
        updateDicts();
    }

    /**
     * @return the current index into the dictionary depending on the app mode
     */
    public int entryIndex() {
        System.out.println("entryIndex()");
        int n = 0;
        if (this.appmode == APPMODE.LEARNING) {
            n = this.dictionary_index;
        } else if (this.appmode == APPMODE.RECAP) {
            n = this.recentdict_index;
        } else {
            assert (false);
        }
        return n;
    }

    /**
     * @return the number of the entries
     */
    public int nEntries() {
        System.out.println("nEntries()");
        int n = 0;
        if (this.appmode == APPMODE.LEARNING) {
            n = this.dictionary_user.size();
        } else if (this.appmode == APPMODE.RECAP) {
            n = this.recentdict_user.size();
        } else {
            assert (false);
        }
        return n;
    }

    /**
     * Saves the current learned dictionary
     * @return the number of words saved for recap
     */
    public int saveLearning() {
        System.out.println("saveLearning()");
        int N = Math.min(learned_count, this.dictionary_user.size());
        ArrayList<StringPair> data = new ArrayList<StringPair>();
        for (int i = 0; i < N; i++) {
            data.add(this.dictionary_user.get(i));
        }
        this.model.replaceRecentDict(data);
        return N;
    }

    /**
     * @return the next pair to be displayed
     */
    public Optional<StringPair> nextPair() {
        System.out.println("nextPair()");
        if (this.appmode == APPMODE.LEARNING) {
            dictionary_index++;
            learned_count++;
        } else if (this.appmode == APPMODE.RECAP) {
            recentdict_index++;
        } else {
            assert (false);
        }
        return getPair();
    }

    /**
     * @return the previous pair to be displayed
     */
    public Optional<StringPair> previousPair() {
        System.out.println("previousPair()");
        if (this.appmode == APPMODE.LEARNING) {
            dictionary_index--;
        } else if (this.appmode == APPMODE.RECAP) {
            recentdict_index--;
        } else {
            assert (false);
        }
        return getPair();
    }

    /**
     * @return a pair to be displayed depending on different learning mode
     */
    public Optional<StringPair> getPair() {
        StringPair pair = null;
        if (this.appmode == APPMODE.LEARNING) {
            if (this.dictionary_index >= 0 && this.dictionary_index < this.dictionary_user.size()) {
                pair = this.dictionary_user.get(this.dictionary_index);
            }
        } else if (this.appmode == APPMODE.RECAP) {
            if (this.recentdict_user != null && this.recentdict_index >= 0 && this.recentdict_index < this.recentdict_user.size()) {
                pair = this.recentdict_user.get(this.recentdict_index);
            }
        } else {
            assert (false);
        }
        return Optional.ofNullable(pair);
    }

    /**
     * @return all possible app modes
     */
    public APPMODE[] getPossibleAppModes() {
        System.out.println("getPossibleAppModes()");
        return APPMODE.values();
    }

    /**
     * Sets app mode
     * @param appmode The app mode to be set
     */
    public void setAppMode(APPMODE appmode) {
        System.out.println("setAppMode()");
        this.appmode = appmode;
    }

    /**
     * @return the current app mode
     */
    public APPMODE getAppMode() {
        System.out.println("getAppMode()");
        return this.appmode;
    }

    /**
     * @return all possible proficiencies
     */
    public PROFICIENCY[] getPossibleProficiencies() {
        System.out.println("getPossibleProficiencies()");
        return PROFICIENCY.values();
    }

    /**
     * Sets the proficiency
     * @param proficiency They proficiency to be set
     */
    public void setProficiency(PROFICIENCY proficiency) {
        System.out.println("setProficiency()");
        this.proficiency = proficiency;
    }

    /**
     * @return the current proficiency
     */
    public PROFICIENCY getProficiency() {
        System.out.println("getProficiency()");
        return this.proficiency;
    }

    /**
     * @return all possible commitment levels
     */
    public COMMITMENT[] getPossibleCommitments() {
        System.out.println("getPossibleCommitments()");
        return COMMITMENT.values();
    }

    /**
     * Sets commitment level
     * @param commitment The commitment level to be set
     */
    public void setCommitment(COMMITMENT commitment) {
        System.out.println("setCommitment()");
        this.commitment = commitment;
    }

    /**
     * @return the current commitment
     */
    public COMMITMENT getCommitment() {
        System.out.println("getCommitment()");
        return this.commitment;
    }

    @SuppressWarnings("unchecked")
    /**
     * Updates the dictionary
     */
    private void updateDicts() {
        if (this.appmode == APPMODE.LEARNING) {
            dictionary_user = (ArrayList<StringPair>) this.dictionary.clone();
            dictionary_user = Utility.reduceByWordLength(dictionary_user, this.proficiency.maxLetters());
            dictionary_user = Utility.generateWordList(dictionary_user, this.commitment.pairs(), this.rand);
        } else if (this.appmode == APPMODE.RECAP) {
            if (this.recentdict.size() > 0) {
                recentdict_user = (ArrayList<StringPair>) this.recentdict.clone();
            }
        } else {
            assert (false);
        }
    }

}
