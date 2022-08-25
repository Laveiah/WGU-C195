package dao;
import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contacts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactsQuery {

    /**
     * Observable list for all contacts in database.
     * @throws SQLException
     * @return contactsObservableList
     */
    public static ObservableList<Contacts> getAllContacts() throws SQLException {
        ObservableList<Contacts> contactsObservableList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM contacts";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();


        while (rs.next()) {
            int contact_ID = rs.getInt("Contact_ID");
            String contact_Name = rs.getString("Contact_Name");
            String contact_Email = rs.getString("Email");
            Contacts contact = new Contacts(contact_ID, contact_Name, contact_Email);
            contactsObservableList.add(contact);
        }
        return contactsObservableList;
    }
}
