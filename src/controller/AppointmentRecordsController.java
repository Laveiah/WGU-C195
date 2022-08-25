package controller;

import dao.AppointmentsQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointments;

import java.io.IOException;
import java.sql.SQLException;
import java.time.*;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.stream.IntStream;

/**
 * Controller class that displays the appointment screen of the application.
 *
 * @author Joseph Veal-Briscoe
 */
public class AppointmentRecordsController {
    Stage stage;
    Parent scene;


    private static Appointments appointmentsToModify;
    static Appointments getAppointmentsToModify() {
        return appointmentsToModify;}

    /**
     * The Appointment add button.
     */
    @FXML
    private Button appointmentsAddButton;

    /**
     * The Appointment back button.
     */
    @FXML
    private Button appointmentsBackButton;

    /**
     * The Appointment delete button.
     */
    @FXML
    private Button appointmentsDeleteButton;

    /**
     * The Appointment All radio button.
     */
    @FXML
    private RadioButton appointmentsAllRadioButton;

    /**
     * The Appointment Monthly radio button.
     */
    @FXML
    private RadioButton appointmentsMonthlyRadioButton;

    /**
     * The Appointment Weekly radio button.
     */
    @FXML
    private RadioButton appointmentsWeeklyRadioButton;

    /**
     * The Appointment Contact table column.
     */
    @FXML
    private TableColumn<?, ?> appointmentsContactColumn;

    /**
     * The Appointment Description table column.
     */
    @FXML
    private TableColumn<?, ?> appointmentsDescriptionColumn;

    /**
     * The Appointment End Date table column.
     */
    @FXML
    private TableColumn<?, ?> appointmentsEndDateColumn;

    /**
     * The Appointment ID table column.
     */
    @FXML
    private TableColumn<Appointments, String> appointmentsIDColumn;

    /**
     * The Appointment Location table column.
     */
    @FXML
    private TableColumn<?, ?> appointmentsLocationColumn;

    /**
     * The Appointment Start Date table column.
     */
    @FXML
    private TableColumn<?, ?> appointmentsStartDateColumn;

    /**
     * The Appointment Table table view.
     */
    @FXML
    private TableView<Appointments> appointmentsTable;

    /**
     * The Appointment Title table column.
     */
    @FXML
    private TableColumn<?, ?> appointmentsTitleColumn;

    /**
     * The Appointment User ID table column.
     */
    @FXML
    private TableColumn<?, ?> appointmentsUserIDColumn;

    /**
     * The Appointment Customer ID table column.
     */
    @FXML
    public TableColumn<?, ?> appointmentsCustIDColumn;

    /**
     * The Appointment Type table column.
     */
    @FXML
    private TableColumn<?, ?> appointmentsTypeColumn;

    /**
     * The Appointment toggle group.
     */
    @FXML
    private ToggleGroup appointmentsToggleGroup;

    /**
     * The Appointment Update button.
     */
    @FXML
    private Button appointmentsUpdateButton;


    /**
     * Initializes controller and generates the Appointments Table from the database.
     *
     * @throws SQLException From FXMLLoader.
     */
    public void initialize() throws SQLException {
        try {
            appointmentsTable.setItems(AppointmentsQuery.getAllAppointments());
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        appointmentsIDColumn.setCellValueFactory(new PropertyValueFactory<Appointments, String>("appointmentID"));
        appointmentsTitleColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        appointmentsDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        appointmentsLocationColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
        appointmentsTypeColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        appointmentsStartDateColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        appointmentsEndDateColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        appointmentsCustIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        appointmentsContactColumn.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        appointmentsUserIDColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
    }

    /**
     * Displays confirmation dialog and loads AddAppointmentRecordsController.
     *
     * @param actionEvent Add button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void onAppointmentsAddButton(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/AddAppointmentRecordsView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Displays confirmation dialog and loads SelectViewController.
     *
     * @param actionEvent Back button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void onAppointmentsBackButton(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/selectView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Displays confirmation dialog and deletes appointments from the appointment tables.
     *
     * @param event Delete button action.
     * @throws SQLException From FXMLLoader.
     */
    @FXML
    void onAppointmentsDeleteButton(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Delete");
        alert.setHeaderText("Appointment ID: " + appointmentsTable.getSelectionModel().getSelectedItem().getAppointmentID() +
                " and Appointment Type: " + appointmentsTable.getSelectionModel().getSelectedItem().getAppointmentType() + " are deleted.");
        alert.setContentText("Are you sure");
        alert.showAndWait();

        AppointmentsQuery.deleteOnlyAppointment(appointmentsTable.getSelectionModel().getSelectedItem());
        appointmentsTable.setItems(AppointmentsQuery.getAllAppointments());




    }

    /**
     * Displays confirmation dialog, gets data from Appointments and loads UpdateAppointmentRecordsController.
     *
     * @param actionEvent Back button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void onAppointmentsUpdateButton(ActionEvent actionEvent) throws IOException {
        if (appointmentsTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Appointment Not Selected");
            alert.setContentText("Please make sure the appointment is selected");
            alert.showAndWait();
        } else {
            appointmentsToModify = appointmentsTable.getSelectionModel().getSelectedItem();

            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("../view/updateAppointmentRecordsView.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /**
     * Filters for Appointment All and displays it on the AppointmentRecordController.
     *
     * @param event All Appointment button action.
     * @throws SQLException From FXMLLoader.
     */
    @FXML
    void onAppointmentsAllRadioButon(ActionEvent event) throws SQLException {
        appointmentsTable.setItems(AppointmentsQuery.getAllAppointments());
    }

    /**
     * Filters for Monthly Appointments and displays it on the AppointmentRecordController.
     *
     * @param event Appointment Monthly button action.
     * @throws SQLException From FXMLLoader.
     */
    @FXML
    void onAppointmentsMonthlyRadioButton(ActionEvent event) throws SQLException {

        Month month = LocalDateTime.now().getMonth();
//        LocalDateTime monthStart = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0));
//        LocalDateTime monthEnd = monthStart.plusDays(30);
        ObservableList<Appointments> appointmentMonthSelection = FXCollections.observableArrayList();
        AppointmentsQuery.getAllAppointments().forEach(appointment -> {

            if (month == appointment.getStart().getMonth()) {
                appointmentMonthSelection.add(appointment);
            }
        });
        appointmentsTable.setItems(appointmentMonthSelection);
    }


    /**
     * Filters for Weekly Appointments and displays it on the AppointmentRecordController.
     * LAMBDA EXPRESSION  - access appointments from the database, filters appointments by week. Reason - Simplify code.
     * @param event Appointment Weekly button action.
     * @throws SQLException From FXMLLoader.
     */
    @FXML
    void onAppointmentsWeeklyRadioButton(ActionEvent event) throws SQLException {

        LocalDateTime weekStart = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0));
        while (weekStart.getDayOfWeek() != DayOfWeek.SUNDAY){
            weekStart = weekStart.minusDays(1);
        }
        LocalDateTime weekEnd = weekStart.plusDays(7);
        ObservableList<Appointments> appointmentWeekSelection = FXCollections.observableArrayList();
        LocalDateTime finalWeekStart = weekStart;
        AppointmentsQuery.getAllAppointments().forEach(appointment -> {


            if ((appointment.getStart().equals(finalWeekStart) || (appointment.getStart().isAfter(finalWeekStart))) &&
                    ((appointment.getEnd().isBefore(weekEnd)) || appointment.getEnd().equals(weekEnd)))
                appointmentWeekSelection.add(appointment);
        });
        appointmentsTable.setItems(appointmentWeekSelection);
    }

}
