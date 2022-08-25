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
import model.AppointmentTypeReport;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


/**
 * Controller class that displays the Report Appointment Totals Controller of the application.
 *
 * @author Joseph Veal-Briscoe
 */
public class ReportAppointmentTotalsController implements Initializable {
    Stage stage;
    Parent scene;

    /**
     * The Appointment Month Column.
     */
    @FXML
    private TableColumn<?, ?> reportAppointmentMonthColumn;

    /**
     * The Appointment Type Column.
     */
    @FXML
    private TableColumn<AppointmentTypeReport, String> reportAppointmentTypeColumn;

    /**
     * The Report Appointment Type Table View.
     */
    @FXML
    private TableView<AppointmentTypeReport> reportAppointmentTypeTableView;

    /**
     * The Report Appointment Type TableView.
     */
    @FXML
    private TableColumn<AppointmentTypeReport, Integer> reportAppointmentTypeTotalColumn;

    /**
     * Displays confirmation dialog and loads SelectViewController.
     *
     * @param actionEvent Report Appointment Totals Back button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void onReportAppointmentTotalBackButton(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/ReportsMainPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**
     * Initializes controller and generates the Type, Total, and Month from the Database.
     *
     * @param url
     * @param resourceBundle The resources used to localize the root object.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            reportAppointmentTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
            reportAppointmentTypeTotalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
            reportAppointmentMonthColumn.setCellValueFactory(new PropertyValueFactory<>("month"));
//            reportAppointmentMonthColumn.setCellValueFactory(new PropertyValueFactory<>("month"));
//            reportAppointmentMonthTotalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
//            ObservableList<AppointmentMonthReport> monthReports = ReportQuery.getAppointmentMonthReport();
            ObservableList<AppointmentTypeReport> typeReports = ReportQuery.getAppointmentTypeReport();
            reportAppointmentTypeTableView.setItems(typeReports);
//            reportAppointmentMonthTableView.setItems(monthReports);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
