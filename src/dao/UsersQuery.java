package dao;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersQuery extends Users{


    public UsersQuery(int userID, String userName, String userPassword) { super(userID, userName, userPassword);
    }

    /**
     * Method that gets username and password from the database.
     * @param userName
     * @param password
     * @return recordFound
     */
    public static boolean userIDCheck(String userName, String password) throws SQLException {
        boolean recordFound = false;
        String sqlLocate = "SELECT * FROM USERS WHERE USER_NAME = '" + userName + "' AND PASSWORD = '" + password + "'";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sqlLocate);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) { // if it finds a match
            recordFound = true;
        }
        return recordFound;
    }

    /**
     * Observable list for all users in database.
     * @throws SQLException
     * @return usersObservableList
     */
    public static ObservableList<Users> getAllUsers() throws SQLException {
        String sql = "SELECT * from users";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ObservableList<Users> usersObservableList = FXCollections.observableArrayList();


        while (rs.next()) {
            int user_ID = rs.getInt("User_ID");
            String user_Name = rs.getString("User_Name");
            String user_Password = rs.getString("Password");
            Users user = new Users(user_ID, user_Name, user_Password);
            usersObservableList.add(user);
        }
        return usersObservableList;
    }
}
