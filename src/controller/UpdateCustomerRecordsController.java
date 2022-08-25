package controller;

import dao.CountriesQuery;
import dao.CustomersQuery;
import dao.FirstLevelDivisionQuery;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Countries;
import model.Customers;
import model.FirstLevelDivision;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


/**
 * Controller class that displays the Update Customer Records screen of the application.
 *
 * @author Joseph Veal-Briscoe
 */
public class UpdateCustomerRecordsController implements Initializable {
    Stage stage;
    Parent scene;

    private Customers selectedCustomers;

    /**
     * The Customer Address text field.
     */
    @FXML
    private TextField updatedCustomerAddress;

    /**
     * The Customer Back button.
     */
    @FXML
    private Button updatedCustomerBackButton;

    /**
     * The Customer Cancel button.
     */
    @FXML
    private Button updatedCustomerCancelButton;

    /**
     * The Customer Name text field.
     */
    @FXML
    private TextField updatedCustomerName;

    /**
     * The Customer Phone text field.
     */
    @FXML
    private TextField updatedCustomerPhoneNumber;

    /**
     * The Customer Postal Code text field.
     */
    @FXML
    private TextField updatedCustomerPostalCode;

    /**
     * The Customer Records ID text field.
     */
    @FXML
    private TextField updatedCustomerRecordsID;

    /**
     * The Customer Save Button.
     */
    @FXML
    private Button updatedCustomerSaveButton;

    /**
     * The Customer State combo box.
     */
    @FXML
    private ComboBox<FirstLevelDivision> updatedCustomerStateComboBox;

    /**
     * The Customer Country combo box.
     */
    @FXML
    private ComboBox<Countries> updatedCustomerCountryComboBox;

    /**
     * Displays confirmation dialog and saves the new text fields into the CustomerRecordsController.
     *
     * @param actionEvent Save button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void onUpdatedCustomerSaveButton(ActionEvent actionEvent) throws IOException {
        int customer_id = Integer.parseInt(updatedCustomerRecordsID.getText());
        String customerName = updatedCustomerName.getText();
        String customerAddress = updatedCustomerAddress.getText();
        String customerPostalCode = updatedCustomerPostalCode.getText();
        String customerPhoneNumber = updatedCustomerPhoneNumber.getText();
        int division_ID = updatedCustomerStateComboBox.getValue().getDivisionID();
        String country_ID = updatedCustomerCountryComboBox.getValue().getCountriesName();

        Customers newCustomers = new Customers(customer_id, customerName, customerAddress, customerPostalCode, customerPhoneNumber, division_ID);
        CustomersQuery.updateCustomer(newCustomers);

        if (country_ID.isBlank() ||  customerName.isBlank() || customerAddress.isBlank() || customerPostalCode.isBlank() ||
                customerPhoneNumber.isBlank()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Missing Information");
            alert.setContentText("Please Make Sure All Fields Are Filled Out Completely");
            alert.showAndWait();
        } else {
            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("../view/CustomerRecordsView.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

        }
    }

    /**
     * Displays confirmation dialog and loads CustomerRecordsViewController.
     *
     * @param actionEvent Back button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void onUpdatedCustomerBackButton(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/CustomerRecordsView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Displays confirmation dialog and loads the Country into the combo box.
     *
     * @param event Country combo box action.
     * @throws SQLException From FXMLLoader.
     */
    @FXML
    void onUpdatedCustomerCountryComboBox(ActionEvent event) throws SQLException {
        int country_ID = updatedCustomerCountryComboBox.getValue().getCountriesID();
        updatedCustomerStateComboBox.setItems(FirstLevelDivisionQuery.divisionByCountry(country_ID));
        updatedCustomerStateComboBox.getSelectionModel().clearSelection(); // clear
        updatedCustomerStateComboBox.setValue(null);

    }

    @FXML
    void onUpdatedCustomerStateComboBox(ActionEvent event) {


    }

    /**
     * Initializes controller, updates textfields in CustomerRecordsController, updates database.
     *
     * @param url
     * @param resourceBundle The resources used to localize the root object.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectedCustomers = CustomerRecordsController.getCustomerToModify();

        updatedCustomerRecordsID.setText(String.valueOf(selectedCustomers.getCustomerID()));
        updatedCustomerName.setText(String.valueOf(selectedCustomers.getCustomerName()));
        updatedCustomerAddress.setText(String.valueOf(selectedCustomers.getCustomerAddress()));
        updatedCustomerPostalCode.setText(String.valueOf(selectedCustomers.getCustomerPostalCode()));
        updatedCustomerPhoneNumber.setText(String.valueOf(selectedCustomers.getCustomerPhoneNumber()));
        try {
            updatedCustomerCountryComboBox.setItems(CountriesQuery.getCountries()); //go to list then set value
            updatedCustomerCountryComboBox.setValue(selectedCustomers.getCountry());

           updatedCustomerStateComboBox.setItems(FirstLevelDivisionQuery.divisionByCountry(selectedCustomers.getCountry().getCountriesID()));
            updatedCustomerStateComboBox.setValue(FirstLevelDivisionQuery.getAllFirstLevelDivisionsByID(selectedCustomers.getDivisionID()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }
}


