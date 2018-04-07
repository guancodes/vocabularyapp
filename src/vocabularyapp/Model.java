package vocabularyapp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * The model of the app
 * @author guanwang
 */
public class Model {

    private String protocol = "jdbc:derby://localhost:1527/";
    private Connection conn = null;

    /**
     * Creates a model
     * @throws IOException If reading from files fails
     * @throws ClassNotFoundException If the driver cannot be loaded
     * @throws SQLException If a database error occurs
     */
    public Model() throws IOException, ClassNotFoundException, SQLException {
        MakeConnection();
        CreateTables();
    }

    /**
     * @return the entire main dictionary
     */
    public ArrayList<StringPair> getDictionary() {
        return readTableContent("dictionary");
    }

    /**
     * @return the most recent dictionary
     */
    public ArrayList<StringPair> getRecentDict() {
        return readTableContent("recentdict");
    }

    /**
     * Replaces the most recent dictionary
     * @param data a list of string pairs
     */
    public void replaceRecentDict(ArrayList<StringPair> data) {
        replaceTableContent("recentdict", data);
    }
    
    /**
     * Connects to the database
     * @throws SQLException If connecting fails
     */
    private void MakeConnection() throws SQLException {
        String dbName = "VocabularyApp";
        conn = DriverManager.getConnection(protocol + dbName + ";create=true");
        conn.setAutoCommit(false);
    }

    /**
     * Creates 2 tables, one to store the entire main dictionary, the other
     * to store the most recent dictionary
     * @throws FileNotFoundException If the file doesn't exist
     */
    private void CreateTables() throws FileNotFoundException {
        if (!Utility.checkTableExists(conn, "dictionary")) {
            createTable("create table dictionary(german varchar(100), english varchar(100))");
            FillDictionaryTable();
        }
        if (!Utility.checkTableExists(conn, "recentdict")) {
            createTable("create table recentdict(german varchar(100), english varchar(100))");
        }
    }

    /**
     * Fills the main dictionary table
     * @throws FileNotFoundException If the file doesn't exist
     */
    private void FillDictionaryTable() throws FileNotFoundException {
        Dictionary dictMain = Dictionary.make("Resources/en-de.txt");
        if (!dictMain.exists()) {
            throw new FileNotFoundException("Main dictionary missing");
        }
        ArrayList<StringPair> data = dictMain.load().get();
        replaceTableContent("dictionary", data);
    }
    
    /**
     * Replaces the table content
     * @param tableName The name of the table
     * @param data a list of string pairs
     */
    private void replaceTableContent(String tableName, ArrayList<StringPair> data) {
        deleteTableContent(tableName);
        PreparedStatement psInsert = null;
        try {
            psInsert = conn.prepareStatement("insert into " + tableName + " values (?, ?)");
            for (StringPair pair : data) {
                psInsert.setString(1, Utility.limitString(pair.left(), 100));
                psInsert.setString(2, Utility.limitString(pair.right(), 100));
                psInsert.executeUpdate();
            }
            conn.commit();
        } catch (SQLException e) {
            Utility.printSQLException(e);
        } finally {
            Utility.closeSQLStatement(psInsert);
        }
    }
    
    /**
     * Reads the table content
     * @param tableName The name of the table
     * @return a list of string pairs
     */
    private ArrayList<StringPair> readTableContent(String tableName) {
        ArrayList<StringPair> data = new ArrayList<StringPair>();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery("select * from " + tableName);
            while (rs.next()) {
                StringPair pair = StringPair.make(rs.getString(1), rs.getString(2));
                data.add(pair);
            }
            conn.commit();
        } catch (SQLException e) {
            Utility.printSQLException(e);
        } finally {
            Utility.closeSQLResultSet(rs);
            Utility.closeSQLStatement(st);
        }
        return data;
    }

    /**
     * Deletes the table content
     * @param tableName The name of the table
     */
    private void deleteTableContent(String tableName) {
        Statement st = null;
        try {
            st = conn.createStatement();
            st.executeUpdate("delete from " + tableName);
            conn.commit();
        } catch (SQLException e) {
            Utility.printSQLException(e);
        } finally {
            Utility.closeSQLStatement(st);
        }
    }

    /**
     * Creates a table
     * @param query The sql query to be executed
     */
    private void createTable(String query) {
        Statement st = null;
        try {
            st = conn.createStatement();
            st.execute(query);
            conn.commit();
        } catch (SQLException e) {
            Utility.printSQLException(e);
        } finally {
            Utility.closeSQLStatement(st);
        }
    }

}
