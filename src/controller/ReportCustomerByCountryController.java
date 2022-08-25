package controller;

import dao.ReportQuery;
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
import model.CountryReport;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


/**
 * Controller class that displays the Report Customer By Country Controller of the application.
 *
 * @author Joseph Veal-Briscoe
 */
public class ReportCustomerByCountryController implements Initializable {

    Stage stage;
    Parent scene;

    /**
     * The Report Customer By Country Name Column.
     */
    @FXML
    private TableColumn<Countries, String> customerByCountryNameColumn;

    /**
     * The Report Customer By Country Table View.
     */
    @FXML
    private TableView<CountryReport> customerByCountryTableView;

    /**
     * The Report Customer By Country Total Column.
     */
    @FXML
    private TableColumn<Countries, Integer> customerByCountryTotalColumn;

    /**
     * The Report Customer By Country Back Button.
     */
    @FXML
    private Button reportCustomerByCountryBackButton;

    /**
     * Displays confirmation dialog and loads Reports Main Page.
     *
     * @param actionEvent Report Customer By Country Back button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void onReportCustomerByCountryBackButton(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/ReportsMainPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Initializes controller and generates the Country Info from the Database.
     *
     * @param url
     * @param resourceBundle The resources used to localize the root object.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            customerByCountryTotalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
            customerByCountryNameColumn.setCellValueFactory(new PropertyValueFactory<>("countryName"));
            ObservableList<CountryReport> countryReports = ReportQuery.getCountryCount();
            customerByCountryTableView.setItems(countryReports);



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
