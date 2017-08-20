package vocabularyapp;

import java.io.FileNotFoundException;
import java.util.*;
import java.io.IOException;
import java.util.Random;

public class VocabularyApp {

    private Scanner input = new Scanner(System.in);
    private Dictionary dictRecent = Dictionary.make("Resources/recent-dict.txt");
    private Dictionary dictMain = Dictionary.make("Resources/en-de.txt");

    public static VocabularyApp make() {
        return new VocabularyApp();
    }

    public void run() throws IOException {
        if (!this.dictMain.exists()) {
            throw new FileNotFoundException("Main dictionary missing");
        }

        Optional<ArrayList<StringPair>> list = getRecentList();
        System.out.println();

        if (!list.isPresent()) {
            PROFICIENCY prof = selectFrom(PROFICIENCY.values(), "proficiency level");
            System.out.println(prof);
            System.out.println();

            COMMITMENT commit = selectFrom(COMMITMENT.values(), "commitment level");
            System.out.println(commit);
            System.out.println();

            list = this.dictMain.load();

            list = Optional.of(Utility.reduceByWordLength(list.get(), prof.maxLetters()));

            Random rand = new Random();
            list = Optional.of(Utility.generateWordList(list.get(), commit.pairs(), rand));
        }

        wordDisplay(list.get());
        this.dictRecent.save(list.get());
    }

    //select from the menu, returns the Enum type option that got selected
    //prints the option selected to the user
    private <T> T selectFrom(T[] options, String type) {
        System.out.println("Please select your " + type + ":");
        String prepared = Utility.prepareMenuForDisplay(options);
        System.out.println(prepared);
        int selected = input.nextInt();
        return options[selected - 1];
    }

    private void wordDisplay(ArrayList<StringPair> list) {
        for (StringPair sp : list) {
            String prepared = Utility.preparePairForDisplay(sp);
            System.out.println(prepared);
            System.out.println();
            System.out.println("Ready for the next word? (y/n)");
            String userReady = input.next();
            System.out.println();
            if (userReady.equals("n")) {
                break;
            }
        }
    }

    private Optional<ArrayList<StringPair>> getRecentList() throws IOException {
        System.out.println("Would you like to review your most recent study? (y/n)");
        String reviewOrNot = input.next();
        if (reviewOrNot.equals("y")) {
            Optional<ArrayList<StringPair>> list = this.dictRecent.load();
            if (!list.isPresent()) {
                System.out.println();
                System.out.println("You don't have a recent study record!");
                System.out.println("But no worries, we'll go right ahead and "
                        + "create one for you!");
            }
            return list;
        } else {
            return Optional.empty();
        }
    }

}
