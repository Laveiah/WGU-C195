package dao;
import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customers;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomersQuery {

    /**
     * Observable list for all customers in database.
     * @throws SQLException
     * @return customersRecordsObservableList
     */
    public static ObservableList<Customers> getAllCustomers() throws SQLException {
        String sqlLocate = "SELECT customers.Customer_ID, customers.Customer_Name, customers.Address, customers.Postal_Code, customers.Phone, customers.Division_ID FROM CUSTOMERS";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sqlLocate);
        ResultSet rs = ps.executeQuery();
        ObservableList<Customers> customersRecordsObservableList = FXCollections.observableArrayList();

        while (rs.next()) {
            int customer_id = rs.getInt("Customer_ID");
            String customer_name = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String postal_Code = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            int division_ID = rs.getInt("Division_ID");
            Customers customer = new Customers(customer_id, customer_name, address, postal_Code, phone, division_ID);
            customersRecordsObservableList.add(customer);
        }
        return customersRecordsObservableList;
    }


    /**
     * Method that updates the customer in the database.
     * @param customer
     */
    // ? placeholder, parameter
    public static void  updateCustomer(Customers customer) {
        String sqlLocate = "UPDATE CUSTOMERS SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";
        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sqlLocate);
            ps.setString(1, customer.getCustomerName());
            ps.setString(2, customer.getCustomerAddress());
            ps.setString(3, customer.getCustomerPostalCode());
            ps.setString(4, customer.getCustomerPhoneNumber());
            ps.setInt(5, customer.getDivisionID());
            ps.setInt(6, customer.getCustomerID());

            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Method that adds the customer in the database.
     * @param customer
     */
    public static void addCustomer(Customers customer){
        String sqlLocate = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sqlLocate);
//            ps.setInt(1, customer.getCustomerID());
            ps.setString(1, customer.getCustomerName());
            ps.setString(2, customer.getCustomerAddress());
            ps.setString(3, customer.getCustomerPostalCode());
            ps.setString(4, customer.getCustomerPhoneNumber());
            ps.setInt(5, customer.getDivisionID());

            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Method that deletes customer based on Customer ID.
     * @param customers
     */
    public static void deleteCustomer(Customers customers) {
        String sqlLocate = "DELETE FROM customers WHERE Customer_ID = ?";
        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sqlLocate);

            ps.setInt(1, customers.getCustomerID());
            ps.executeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}


