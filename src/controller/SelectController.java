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
 * Controller class that displays the Select Controller screen of the application.
 *
 * @author Joseph Veal-Briscoe
 */
public class SelectController {
    Stage stage;
    Parent scene;

    /**
     * The Appointment button.
     */
    @FXML
    private Button selectAppointmentsButton;

    /**
     * The Customer button.
     */
    @FXML
    private Button selectCustomerButton;

    /**
     * The Exit button.
     */
    @FXML
    private Button selectExitButton;

    /**
     * The Report button.
     */
    @FXML
    private Button selectReportsButton;


    /**
     * Displays confirmation dialog and loads AppointmentRecordsViewController.
     *
     * @param actionEvent Select Appointments button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void onSelectAppointmentsButton(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/AppointmentRecordsView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Displays confirmation dialog and loads CustomerRecordsViewController.
     *
     * @param actionEvent Customer button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void onSelectCustomerButton(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/CustomerRecordsView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Displays confirmation dialog and exits the program.
     *
     * @param actionEvent Exit button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void onSelectExitButton(ActionEvent actionEvent) throws IOException {
        System.exit(0);
    }

    /**
     * Displays confirmation dialog and loads ReportsViewController.
     *
     * @param actionEvent Report button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void onSelectReportsButton(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/ReportsMainPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

}
