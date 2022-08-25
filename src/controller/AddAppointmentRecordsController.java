package controller;

import dao.AppointmentsQuery;
import dao.ContactsQuery;
import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Appointments;
import model.Contacts;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

/**
 * Controller class that displays the add appointment screen of the application.
 *
 * @author Joseph Veal-Briscoe
 */

public class AddAppointmentRecordsController implements Initializable {

    Stage stage;
    Parent scene;


    /**
     * The save appointment button.
     */
    @FXML
    public Button addAppointmentsSaveButton;

    /**
     * The back button to the previous screen.
     */
    @FXML
    private Button addAppointmentsBackButton;

    /**
     * The add Appointment Contact combo box.
     */
    @FXML
    public ComboBox<Contacts> addAppointmentsContactComboBox;

    /**
     * The add Appointment Description text field.
     */
    @FXML
    private TextField addAppointmentsDescriptionField;

    /**
     * The add Appointment ID text field.
     */
    @FXML
    private TextField addAppointmentsIDField;

    /**
     * The add Appointment Location text field.
     */
    @FXML
    private TextField addAppointmentsLocationField;

    /**
     * The add Appointment Contact text field.
     */
    @FXML
    private DatePicker addAppointmentsEndDatePickerButtton;

    /**
     * The add Appointment Start Date picker button.
     */
    @FXML
    private DatePicker addAppointmentsStartDatePickerButton;

    /**
     * The add Appointment Start Time combo box.
     */
    @FXML
    private ComboBox<LocalTime> addAppointmentsStartTimeComboBox;

    /**
     * The add Appointment End Time combo box.
     */
    @FXML
    private ComboBox<LocalTime> addAppointmentsEndTimeComboBox;

    /**
     * The add Appointment Title text field.
     */
    @FXML
    private TextField addAppointmentsTitleField;

    /**
     * The add Appointment Type text field.
     */
    @FXML
    private TextField addAppointmentsTypeField;

    /**
     * The add Appointment Customer ID text field.
     */
    public TextField addAppointmentsCustomerIDField;

    /**
     * The add Appointment User text field.
     */
    public TextField addAppointmentsUserIDField;


    /**
     * Displays confirmation dialog and loads AppointmentController.
     *
     * @param actionEvent back button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void onAddAppointmentsBackButton(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/AppointmentRecordsView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }



    /**
     * Displays confirmation dialog and loads AppointmentRecordsViewController.
     * Text Fields are saved to new appointments and update the database.
     *
     * @param actionEvent save button action.
     * @throws IOException From FXMLLoader.
     */

    @FXML
    void onAddAppointmentsSaveButton(ActionEvent actionEvent) throws IOException, SQLException {
        int appointmentID = -1; // filler
        String appointmentTitle = addAppointmentsTitleField.getText();
        String appointmentDescription = addAppointmentsDescriptionField.getText();
        String appointmentLocation = addAppointmentsLocationField.getText();
        String appointmentType = addAppointmentsTypeField.getText();
        LocalDate startdate = addAppointmentsStartDatePickerButton.getValue(); //local date time. webinar its about time.
        LocalTime starttime = addAppointmentsStartTimeComboBox.getValue();
        LocalDate enddate = addAppointmentsEndDatePickerButtton.getValue();
        LocalTime endtime = addAppointmentsEndTimeComboBox.getValue();
        int contact = addAppointmentsContactComboBox.getValue().getId();
        int customer;
        try {
            customer= Integer.parseInt(addAppointmentsCustomerIDField.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Information");
            alert.setContentText("Invalid Customer ID, Please enter one that is valid");
            alert.showAndWait();
            return;
        }

        int user;
        try {
            user= Integer.parseInt(addAppointmentsUserIDField.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Information");
            alert.setContentText("Invalid User ID, Please enter one that is valid");
            alert.showAndWait();
            return;
        }



        LocalDateTime start = LocalDateTime.of(startdate, starttime);
        LocalDateTime end = LocalDateTime.of(enddate, endtime);

        if (appointmentTitle.isBlank() || appointmentDescription.isBlank() || appointmentLocation.isBlank() ||
                appointmentType.isBlank()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Missing Information");
            alert.setContentText("Please Make Sure All Fields Are Filled Out Completely");
            alert.showAndWait();

            return;
        }

        if (JDBC.appointmentOverlap(customer, start, end, -1)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Overlapping Appointment");
            alert.setContentText("Please make sure the appointments aren't overlapping.");
            alert.showAndWait();

        } else {
            Appointments newAppointments = new Appointments(appointmentID, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, start, end, customer, user, contact);
            AppointmentsQuery.addAppointment(newAppointments);

            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("../view/AppointmentRecordsView.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    @FXML
    void onAddAppointmentsEndDatePicker(ActionEvent event) {

    }

    @FXML
    void onAddAppointmentsEndTimeComboBox(ActionEvent event) {

    }

    @FXML
    void onAddAppointmentsStartDatePicker(ActionEvent event) {

    }

    @FXML
    void onAddAppointmentsStartTimeComboBox(ActionEvent event) {

    }

    @FXML
    void onAddAppointmentsContactComboBox(ActionEvent event) {

    }


    /**
     * Initializes controller and generates a loop for the start and end combo boxes.
     * LAMBDA EXPRESSION - Creates numbers for the combo boxes to log the appointment time. Reason - Simplify code.
     * @param url
     * @param resourceBundle The resources used to localize the root object.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<LocalTime> timelist = FXCollections.observableArrayList();
        ObservableList<LocalTime> timelist2 = FXCollections.observableArrayList();

        ZonedDateTime est8am =  ZonedDateTime.of(LocalDate.now(), LocalTime.of(8, 0), ZoneId.of("America/New_York"));
        ZonedDateTime localStart = est8am.withZoneSameInstant(ZoneId.systemDefault());

        /**
         * Lambda #1 IDE Converter.
         */
        IntStream.range(localStart.getHour(), localStart.getHour()+14).forEachOrdered(n -> {
            timelist.add(LocalTime.of(n, 0));
            timelist.add(LocalTime.of(n, 30));

            timelist2.add(LocalTime.of(n, 30));
            timelist2.add(LocalTime.of(n + 1, 0));
        });
        addAppointmentsStartTimeComboBox.setItems(timelist);
        addAppointmentsEndTimeComboBox.setItems(timelist2);


        try {
            addAppointmentsContactComboBox.setItems(ContactsQuery.getAllContacts());
            addAppointmentsContactComboBox.setCellFactory(new Callback<ListView<Contacts>, ListCell<Contacts>>() {
                @Override
                public ListCell<Contacts> call(ListView<Contacts> l) {
                    return new ListCell<Contacts>() {

                        @Override
                        protected void updateItem(Contacts item, boolean empty) {
                            super.updateItem(item, empty);
                            if (item == null || empty) {
                                setGraphic(null);
                            } else {
                                setText(item.getContactName());
                            }
                        }
                    } ;
                }
            });

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
//
    }
}
