package controller;

import dao.CountriesQuery;
import dao.CustomersQuery;
import dao.FirstLevelDivisionQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
 * Controller class that displays the add customer screen of the application.
 *
 * @author Joseph Veal-Briscoe
 */
public class AddCustomerRecordsController implements Initializable {
    Stage stage;
    Parent scene;

    /**
     * The add Customer Address text field.
     */
    @FXML
    private TextField addCustomerAddressField;

    /**
     * The add Customer Back button.
     */
    @FXML
    private Button addCustomerBackButton;

    /**
     * The add Customer Country combo box.
     */
    @FXML
    private ComboBox<Countries> addCustomerCountryComboBox;

    /**
     * The add Customer ID text field.
     */
    @FXML
    private TextField addCustomerIDField;

    /**
     * The add Customer Name text field.
     */
    @FXML
    private TextField addCustomerNameField;

    /**
     * The add Customer Phone Number text field.
     */
    @FXML
    private TextField addCustomerPhoneNumberField;

    /**
     * The add Customer Postal Code text field.
     */
    @FXML
    private TextField addCustomerPostalCodeField;

    /**
     * The add Customer Save button.
     */
    @FXML
    private Button addCustomerSaveButton;

    /**
     * The add Customer State Combo Box.
     */
    @FXML
    private ComboBox<FirstLevelDivision> addCustomerStateComboBox;


    /**
     * Displays confirmation dialog and loads CustomerRecordsController.
     *
     * @param actionEvent back button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void onAddCustomerBackButton(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/CustomerRecordsView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Displays confirmation dialog and sets items in the Customer State combo box from the country_id.
     *
     * @param event add customer country combo box action.
     * @throws SQLException From FXMLLoader.
     */
    @FXML
    void onAddCustomerCountryComboBox(ActionEvent event) throws SQLException {
        int country_ID = addCustomerCountryComboBox.getValue().getCountriesID();
        addCustomerStateComboBox.setItems(FirstLevelDivisionQuery.divisionByCountry(country_ID));

    }

    @FXML
    void onAddCustomerStateComboBox(ActionEvent event) {

    }

    /**
     * Displays confirmation dialog and loads CustomerRecordsController.
     * Text Fields are saved to new customer and update the database.
     *
     * @param actionEvent save button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void onAddCustomerSaveButton(ActionEvent actionEvent) throws IOException {
        int customer_id = -1; //filler
        String customerName = addCustomerNameField.getText();
        String customerAddress = addCustomerAddressField.getText();
        String customerPostalCode = addCustomerPostalCodeField.getText();
        String customerPhoneNumber = addCustomerPhoneNumberField.getText();
        int division_ID = addCustomerStateComboBox.getValue().getDivisionID();

        Customers newCustomers = new Customers(customer_id, customerName, customerAddress, customerPostalCode, customerPhoneNumber, division_ID);
        CustomersQuery.addCustomer(newCustomers);

        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/CustomerRecordsView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Initializes controller and generates a list for the Customer Country combo box.
     *
     * @param url
     * @param resourceBundle The resources used to localize the root object.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            addCustomerCountryComboBox.setItems(CountriesQuery.getCountries()); //go to list then set value
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}