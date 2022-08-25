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
import java.sql.Timestamp;
import java.time.*;
import java.util.ResourceBundle;
import java.util.stream.IntStream;


/**
 * Controller class that displays the Update Appointment Records screen of the application.
 *
 * @author Joseph Veal-Briscoe
 */
public class UpdateAppointmentRecordsController implements Initializable {

    Stage stage;
    Parent scene;


    private Appointments selectedAppointments;



    /**
     * The Appointment EndTime combo box.
     */
    @FXML
    private ComboBox<LocalTime> appointmentsEndTimeComboBox;

    /**
     * The Back button.
     */
    @FXML
    private Button updateAppointmentsBackButton;

    /**
     * The Appointment Contact Combo Box.
     */
    @FXML
    public ComboBox<Contacts> updateAppointmentsContactComboBox;


    /**
     * The Appointment Description text field.
     */
    @FXML
    private TextField updateAppointmentsDescriptionField;

    /**
     * The Appointment End Date picker button.
     */
    @FXML
    private DatePicker updateAppointmentsEndDatePickerButtton;

    /**
     * The Appointment ID text field.
     */
    @FXML
    private TextField updateAppointmentsIDField;

    /**
     * The Appointment Location text field.
     */
    @FXML
    private TextField updateAppointmentsLocationField;

    /**
     * The Appointment Start Date picker button.
     */
    @FXML
    private DatePicker updateAppointmentsStartDatePickerButton;

    /**
     * The Appointment Start Time combo box.
     */
    @FXML
    private ComboBox<LocalTime> updateAppointmentsStartTimeComboBox;

    /**
     * The Appointment Title text field.
     */
    @FXML
    private TextField updateAppointmentsTitleField;

    /**
     * The Appointment Type text field.
     */
    @FXML
    private TextField updateAppointmentsTypeField;

    /**
     * The Appointment Save button.
     */
    @FXML
    public Button updateAppointmentsSaveButton;


    /**
     * The Appointment Customer ID text field.
     */
    public TextField updateAppointmentsCustomerIDField;


    /**
     * The Appointment User ID text field.
     */
    public TextField updateAppointmentsUserIDField;



    /**
     * Displays confirmation dialog and loads AppointmentRecordsViewController.
     *
     * @param actionEvent Back button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void onUpdateAppointmentsBackButton(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/AppointmentRecordsView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onUpdateAppointmentsDeleteButton(ActionEvent event) {

    }

    @FXML
    void onUpdateAppointmentsEndDatePicker(ActionEvent event) {

    }

    @FXML
    void onUpdateAppointmentsEndTimeComboBox(ActionEvent event) {

    }

    @FXML
    void onUpdateAppointmentsStartDatePicker(ActionEvent event) {

    }

    @FXML
    void onUpdateAppointmentsStartTimeComboBox(ActionEvent event) {

    }

    @FXML
    void onUpdateAppointmentsContactComboBox(ActionEvent event) {
        
    }




    /**
     * Displays confirmation dialog and saves the new text fields into the AppointmentRecordsViewController.
     *
     * @param actionEvent Save button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void onUpdateAppointmentsSaveButton(ActionEvent actionEvent) throws IOException, SQLException {
        int appointmentID = Integer.parseInt(updateAppointmentsIDField.getText());
        String appointmentTitle = updateAppointmentsTitleField.getText();
        String appointmentDescription = updateAppointmentsDescriptionField.getText();
        String appointmentLocation = updateAppointmentsLocationField.getText();
        String appointmentType = updateAppointmentsTypeField.getText();
        LocalDate startdate = updateAppointmentsStartDatePickerButton.getValue(); //local date time. webinar its about time.
        LocalTime starttime = updateAppointmentsStartTimeComboBox.getValue();
        LocalDate enddate = updateAppointmentsEndDatePickerButtton.getValue();
        LocalTime endtime = appointmentsEndTimeComboBox.getValue();
        int customer = Integer.parseInt(updateAppointmentsCustomerIDField.getText());
        int user = Integer.parseInt(updateAppointmentsUserIDField.getText());
        int contact = updateAppointmentsContactComboBox.getValue().getId();

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
        if (JDBC.appointmentOverlap(customer, start, end, appointmentID)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Overlapping Appointment");
            alert.setContentText("Please make sure the appointments aren't overlapping.");
            alert.showAndWait();

            return;
        }

        Appointments newAppointments = new Appointments(appointmentID, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, start, end, customer, user, contact);
        AppointmentsQuery.updateAppointment(newAppointments);

        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/AppointmentRecordsView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Initializes controller, gets time for the combo boxes, displays text field for UpdateAppointmentRecords Controller.
     *
     * @param url
     * @param resourceBundle The resources used to localize the root object.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectedAppointments = AppointmentRecordsController.getAppointmentsToModify();

        updateAppointmentsIDField.setText(String.valueOf(selectedAppointments.getAppointmentID()));
        updateAppointmentsTitleField.setText(String.valueOf(selectedAppointments.getAppointmentTitle()));
        updateAppointmentsDescriptionField.setText(String.valueOf(selectedAppointments.getAppointmentDescription()));
        updateAppointmentsLocationField.setText(String.valueOf(selectedAppointments.getAppointmentLocation()));
        updateAppointmentsTypeField.setText(String.valueOf(selectedAppointments.getAppointmentType()));
        LocalDate lDate = (selectedAppointments.getStart().toLocalDate());
        LocalTime lTime = (selectedAppointments.getStart().toLocalTime());
        updateAppointmentsStartTimeComboBox.setValue(lTime);
        updateAppointmentsStartDatePickerButton.setValue(lDate);

        LocalDate eDate = (selectedAppointments.getEnd().toLocalDate());
        LocalTime eTime = (selectedAppointments.getEnd().toLocalTime());
        appointmentsEndTimeComboBox.setValue(eTime);
        updateAppointmentsEndDatePickerButtton.setValue(eDate);
//        updateAppointmentsContactComboBox.setValue(selectedAppointments.getContactID());

        updateAppointmentsCustomerIDField.setText(String.valueOf(selectedAppointments.getCustomerID()));
        updateAppointmentsUserIDField.setText(String.valueOf(selectedAppointments.getUserID()));

        ObservableList<LocalTime> timelist3 = FXCollections.observableArrayList();
        ObservableList<LocalTime> timelist4 = FXCollections.observableArrayList();

        ZonedDateTime est8am =  ZonedDateTime.of(LocalDate.now(), LocalTime.of(8, 0), ZoneId.of("America/New_York"));
        ZonedDateTime localStart = est8am.withZoneSameInstant(ZoneId.systemDefault());

        IntStream.range(localStart.getHour(), localStart.getHour()+14).forEachOrdered(n -> {
            timelist3.add(LocalTime.of(n, 0));
            timelist3.add(LocalTime.of(n, 30));

            timelist4.add(LocalTime.of(n, 30));
            timelist4.add(LocalTime.of(n + 1, 0));
        });
        updateAppointmentsStartTimeComboBox.setItems(timelist3);
        appointmentsEndTimeComboBox.setItems(timelist4);

//Contact Combo Box
        try {
            updateAppointmentsContactComboBox.setItems(ContactsQuery.getAllContacts());
            for(Contacts c: updateAppointmentsContactComboBox.getItems()){
                if (c.getId() == selectedAppointments.getContactID()){
                    updateAppointmentsContactComboBox.setValue(c);
                    break;
                }
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
