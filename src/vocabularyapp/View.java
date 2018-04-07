package vocabularyapp;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 * The view of the app
 * @author guanwang
 */
public class View extends JFrame {

    private Controller control;
    private JButton startButton;
    private JButton nextButton;
    private JButton previousButton;
    private JButton saveButton;
    private JLabel germanLabel;
    private JLabel englishLabel;
    private JLabel gerWordLabel;
    private JLabel engWordLabel;
    private JLabel appModeLabel;
    private JLabel proficiencyLabel;
    private JLabel commitmentLabel;
    private JLabel entryLabel;
    private JComboBox<String> appModeCombo;
    private JComboBox<String> proficiencyCombo;
    private JComboBox<String> commitmentCombo;

    /**
     * Constructs the view
     * @param control The controller of the app
     */
    public View(Controller control) {
        this.control = control;
        setup();
    }

    /**
     * Sets up the view and all its components
     */
    private void setup() {
        setLayout(null);
        startButton = makeStartButton();
        nextButton = makeNextButton();
        nextButton.setVisible(false);
        previousButton = makePreviousButton();
        previousButton.setVisible(false);
        saveButton = makeSaveButton();
        saveButton.setVisible(false);
        germanLabel = makeLabel("GERMAN:", SwingConstants.RIGHT);
        germanLabel.setVisible(false);
        germanLabel.setLocation(10, 200);
        germanLabel.setSize(100, 10);
        englishLabel = makeLabel("ENGLISH:", SwingConstants.RIGHT);
        englishLabel.setVisible(false);
        englishLabel.setLocation(10, 230);
        englishLabel.setSize(100, 10);
        entryLabel = makeLabel("N/A", SwingConstants.LEFT);
        entryLabel.setVisible(false);
        entryLabel.setLocation(130, 170);
        entryLabel.setSize(100, 20);
        gerWordLabel = makeLabel("N/A", SwingConstants.LEFT);
        gerWordLabel.setVisible(false);
        gerWordLabel.setForeground(Color.red);
        gerWordLabel.setLocation(130, 195);
        gerWordLabel.setSize(400, 20);
        engWordLabel = makeLabel("N/A", SwingConstants.LEFT);
        engWordLabel.setVisible(false);
        engWordLabel.setForeground(Color.blue);
        engWordLabel.setLocation(130, 225);
        engWordLabel.setSize(400, 20);
        appModeLabel = makeLabel("Mode:", SwingConstants.RIGHT);
        appModeLabel.setLocation(10, 30);
        appModeLabel.setSize(100, 20);
        proficiencyLabel = makeLabel("Proficiency:", SwingConstants.RIGHT);
        proficiencyLabel.setLocation(10, 60);
        proficiencyLabel.setSize(100, 20);
        commitmentLabel = makeLabel("Commitment:", SwingConstants.RIGHT);
        commitmentLabel.setLocation(10, 90);
        commitmentLabel.setSize(100, 20);

        appModeCombo = makeCombo(control.getPossibleAppModes());
        appModeCombo.setLocation(150, 30);
        appModeCombo.setSize(150, 20);
        appModeCombo.addActionListener((ActionEvent event) -> {
            stop();
            String item = (String) appModeCombo.getSelectedItem();
            control.setAppMode(APPMODE.valueOf(item.toUpperCase()));
            updateCombos();
        });

        proficiencyCombo = makeCombo(control.getPossibleProficiencies());
        proficiencyCombo.setLocation(150, 60);
        proficiencyCombo.setSize(150, 20);
        proficiencyCombo.addActionListener((ActionEvent event) -> {
            stop();
            String item = (String) proficiencyCombo.getSelectedItem();
            control.setProficiency(PROFICIENCY.valueOf(item.toUpperCase()));
        });

        commitmentCombo = makeCombo(control.getPossibleCommitments());
        commitmentCombo.setLocation(150, 90);
        commitmentCombo.setSize(150, 20);
        commitmentCombo.addActionListener((ActionEvent event) -> {
            stop();
            String item = (String) commitmentCombo.getSelectedItem();
            control.setCommitment(COMMITMENT.valueOf(item.toUpperCase()));
        });

        add(startButton);
        add(nextButton);
        add(previousButton);
        add(saveButton);
        add(germanLabel);
        add(englishLabel);
        add(gerWordLabel);
        add(engWordLabel);
        add(appModeLabel);
        add(proficiencyLabel);
        add(commitmentLabel);
        add(appModeCombo);
        add(proficiencyCombo);
        add(commitmentCombo);
        add(entryLabel);

        setResizable(false);
        setTitle("GuanLang German Learning");
        setSize(575, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Creates the start button
     * @return the start button
     */
    private JButton makeStartButton() {
        JButton button = new JButton("Start");
        button.setLocation(10, 140);
        button.setSize(150, 25);
        button.addActionListener((ActionEvent event) -> {
            this.control.reset();
            if (this.control.getPair().isPresent()) {
                start();
            } else {
                JOptionPane.showMessageDialog(this, "No data available for " + control.getAppMode());
            }
        });
        return button;
    }

    /**
     * starts presenting english-german pairs
     */
    private void start() {
        this.control.reset();
        Optional<StringPair> pair = this.control.getPair();
        if (pair.isPresent()) {
            updateWords(pair.get());
        }
        entryLabel.setVisible(true);
        germanLabel.setVisible(true);
        englishLabel.setVisible(true);
        gerWordLabel.setVisible(true);
        engWordLabel.setVisible(true);
        nextButton.setVisible(true);
        previousButton.setVisible(true);
        startButton.setText("Restart");
        if (control.getAppMode() == APPMODE.LEARNING) {
            saveButton.setVisible(true);
        }
    }

    /**
     * resets the status of the app and stops presenting word pairs
     */
    private void stop() {
        this.control.reset();
        entryLabel.setVisible(false);
        germanLabel.setVisible(false);
        englishLabel.setVisible(false);
        gerWordLabel.setVisible(false);
        engWordLabel.setVisible(false);
        nextButton.setVisible(false);
        previousButton.setVisible(false);
        saveButton.setVisible(false);
        startButton.setText("Start");
    }
    
    /**
     * Creates next button
     * @return the next button
     */

    private JButton makeNextButton() {
        JButton button = new JButton("Next");
        button.setLocation(280, 140);
        button.setSize(100, 25);
        button.addActionListener((ActionEvent event) -> {
            Optional<StringPair> pair = this.control.nextPair();
            if (pair.isPresent()) {
                updateWords(pair.get());
            } else {
                this.control.previousPair();
                JOptionPane.showMessageDialog(this, "No further entries available");
            }
        });
        return button;
    }

    /**
     * Creates the previous button
     * @return the previous button
     */
    private JButton makePreviousButton() {
        JButton button = new JButton("Previous");
        button.setLocation(170, 140);
        button.setSize(100, 25);
        button.addActionListener((ActionEvent event) -> {
            Optional<StringPair> pair = this.control.previousPair();
            if (pair.isPresent()) {
                updateWords(pair.get());
            } else {
                this.control.nextPair();
                JOptionPane.showMessageDialog(this, "No further entries available");
            }
        });
        return button;
    }

    /**
     * Creates the save button
     * @return 
     */
    private JButton makeSaveButton() {
        JButton button = new JButton("Save Learned");
        button.setLocation(410, 140);
        button.setSize(150, 25);
        button.addActionListener((ActionEvent event) -> {
            int n = this.control.saveLearning();
            String entries = n > 1 ? "entries" : "entry";
            JOptionPane.showMessageDialog(this, "Saved " + n + " learned " + entries);
        });
        return button;
    }

    /**
     * Updates words
     * @param pair A string pair
     */
    private void updateWords(StringPair pair) {
        gerWordLabel.setText(pair.left());
        engWordLabel.setText(pair.right());
        entryLabel.setText((control.entryIndex() + 1) + " / " + control.nEntries());
    }

    /**
     * Creates the label
     * @param text Text to be set
     * @param alignment The alignment of the text
     * @return the label
     */
    private JLabel makeLabel(String text, int alignment) {
        JLabel lbl = new JLabel(text, alignment);
        return lbl;
    }

    /**
     * Creates the combo box
     * @param elements Elements stored in the combo box
     * @return the combo box
     */
    private JComboBox<String> makeCombo(Object[] elements) {
        JComboBox<String> combo = new JComboBox<String>();
        for (Object e : elements) {
            combo.addItem(e.toString());
        }
        return combo;
    }

    /**
     * Updates the combo boxes
     */
    private void updateCombos() {
        boolean visible = control.getAppMode() == APPMODE.LEARNING;
        proficiencyLabel.setVisible(visible);
        commitmentLabel.setVisible(visible);
        proficiencyCombo.setVisible(visible);
        commitmentCombo.setVisible(visible);
    }

}
