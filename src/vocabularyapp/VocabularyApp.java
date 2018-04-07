package vocabularyapp;

import java.awt.EventQueue;
import java.io.IOException;
import java.sql.SQLException;

/**
 * The vocabulary app using an MVC pattern
 * @author guanwang
 */
public class VocabularyApp {

    /**
     * starts a vocabulary app
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void run() throws ClassNotFoundException, IOException, SQLException {
        Model model = new Model();
        Controller control = new Controller(model);
        View view = new View(control);

        EventQueue.invokeLater(() -> {
            view.setVisible(true);
        });
    }

}
