package controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller class that displays the Report Main Controller of the application.
 *
 * @author Joseph Veal-Briscoe
 */
public class ReportMainController {
    Stage stage;
    Parent scene;

    /**
     * The Report Appointment Total Button.
     */
    @FXML
    private Button reportByAppointmentTotal;

    /**
     * The Report Contact Schedule Button.
     */
    @FXML
    private Button reportContactScheduleButton;

    /**
     * The Report Customer By Country Button.
     */
    @FXML
    private Button reportCustomerByCountryButton;

    /**
     * The Report Report Main Screen Back Button.
     */
    @FXML
    private Button reportMainScreenBackButton;


    /**
     * Displays confirmation dialog and loads Select View.
     *
     * @param actionEvent Report Report Main Screen Back button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void OnReportMainScreenBackButton(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/SelectView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }


    /**
     * Displays confirmation dialog and loads Reports Appointment Total View Page.
     *
     * @param actionEvent Report Appointment Totals Back button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void onReportByAppointmentTotalButton(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/ReportAppointmentTotalsView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    /**
     * Displays confirmation dialog and loads Reports Contact Schedule View Page.
     *
     * @param actionEvent Report Contact Schedule button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void onReportContactScheduleButton(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/ReportContactScheduleView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    /**
     * Displays confirmation dialog and loads Report Customer By Country View Page.
     *
     * @param actionEvent Report Customer By Country button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void onReportCustomerByCountryButton(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/ReportCustomerByCountryView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

}

