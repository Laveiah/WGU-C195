package controller;

import dao.AppointmentsQuery;
import dao.CustomersQuery;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Countries;
import model.Customers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;


/**
 * Controller class that displays the Customer Records screen of the application.
 *
 * @author Joseph Veal-Briscoe
 */
public class CustomerRecordsController implements Initializable {

    private static Customers customerToModify;
    public static Customers getCustomerToModify() {
        return customerToModify;}

    Stage stage;
    Parent scene;

    /**
     * The Customer add button.
     */
    @FXML
    private Button customerAddButton;

    /**
     * The Customer back button.
     */
    @FXML
    private Button customerBackButton;

    /**
     * The Customer Delete button.
     */
    @FXML
    private Button customerDeleteButton;

    /**
     * The Customer Edit button.
     */
    @FXML
    private Button customerEditButton;

    /**
     * The Customer Address table column.
     */
    @FXML
    private TableColumn<Customers, String> customerAddressColumn;

    /**
     * The Customer First Level Division table column.
     */
    @FXML
    private TableColumn<Customers, String> customerFirstLevelDivisionColumn;

    /**
     * The Customer Country table column.
     */
    @FXML
    private TableColumn<Customers, String> customerCountryColumn;

    /**
     * The Customer ID table column.
     */
    @FXML
    private TableColumn<Customers, Integer> customerIDColumn;

    /**
     * The Customer Name table column.
     */
    @FXML
    private TableColumn<Customers, String> customerNameColumn;

    /**
     * The Customer Phone Number table column.
     */
    @FXML
    private TableColumn<Customers, String> customerPhoneNumberColumn;

    /**
     * The Customer Postal Zip table column.
     */
    @FXML
    private TableColumn<Customers, String> customerPostalZipColumn;

    /**
     * The Customer Records table.
     */
    @FXML
    private TableView<Customers> customerRecordsTable;



    /**
     * Displays confirmation dialog and loads CustomerRecordsViewController.
     *
     * @param actionEvent Add button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void onCustomerAddButton(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/AddCustomerRecordsView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Displays confirmation dialog and loads SelectViewController.
     *
     * @param actionEvent Customer Back button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void onCustomerBackButton(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/selectView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Displays confirmation dialog and deletes a customer from the CustomerRecordsTable.
     *
     * @param event Delete button action.
     * @throws SQLException From FXMLLoader.
     */
    @FXML
    void onCustomerDeleteButton(ActionEvent event) throws SQLException {
        AppointmentsQuery.deleteAppointment(customerRecordsTable.getSelectionModel().getSelectedItem());
        CustomersQuery.deleteCustomer(customerRecordsTable.getSelectionModel().getSelectedItem());
        customerRecordsTable.setItems(CustomersQuery.getAllCustomers());
    }

    /**
     * Displays confirmation dialog, gets data from Customer and loads UpdateCustomerRecordsViewController.
     *
     * @param actionEvent Customer Edit button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void onCustomerEditButton(ActionEvent actionEvent) throws IOException {
        customerToModify = customerRecordsTable.getSelectionModel().getSelectedItem();

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/updateCustomerRecordsView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    /**
     * Initializes controller and generates the Customer Records Table from the database.
     *
     * @param url
     * @param resourceBundle The resources used to localize the root object.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        try {
            customerRecordsTable.setItems(CustomersQuery.getAllCustomers());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        customerAddressColumn.setCellValueFactory(new PropertyValueFactory("customerAddress"));
        customerFirstLevelDivisionColumn.setCellValueFactory(new PropertyValueFactory("divisionID"));
        customerPhoneNumberColumn.setCellValueFactory(new PropertyValueFactory("customerPhoneNumber"));
        customerIDColumn.setCellValueFactory(new PropertyValueFactory("customerID"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory("customerName"));
        customerPostalZipColumn.setCellValueFactory(new PropertyValueFactory("customerPostalCode"));
        customerCountryColumn.setCellValueFactory(new PropertyValueFactory("country"));
    }


}