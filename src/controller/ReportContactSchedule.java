package controller;

import dao.AppointmentsQuery;
import dao.ContactsQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Appointments;
import model.Contacts;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Controller class that displays the Report Contact Schedule Controller of the application.
 *
 * @author Joseph Veal-Briscoe
 */
public class ReportContactSchedule implements Initializable {
    Stage stage;
    Parent scene;

    /**
     * The Report Contact Schedule Appointment ID Column.
     */
        @FXML
        private TableColumn<?, ?> reportContactScheduleAppointmentIDColumn;

    /**
     * The Report Contact Schedule Back Button.
     */
        @FXML
        private Button reportContactScheduleBackButton;

    /**
     * The Report Contact Schedule Combo Box.
     */
        @FXML
        private ComboBox<Contacts> reportContactScheduleComboBox;

    /**
     * The Report Contact Schedule Customer ID Column.
     */
        @FXML
        private TableColumn<?, ?> reportContactScheduleCustomerIDColumn;

    /**
     * The Report Contact Schedule Description Column.
     */
        @FXML
        private TableColumn<?, ?> reportContactScheduleDescriptionColumn;

    /**
     * The Report Contact Schedule End Column.
     */
        @FXML
        private TableColumn<?, ?> reportContactScheduleEndColumn;

    /**
     * The Report Contact Schedule Start Column.
     */
        @FXML
        private TableColumn<?, ?> reportContactScheduleStartColumn;

    /**
     * The Report Contact Schedule Title Column.
     */
        @FXML
        private TableColumn<?, ?> reportContactScheduleTitleColumn;

    /**
     * The Report Contact Schedule Type Column.
     */
        @FXML
        private TableColumn<?, ?> reportContactScheduleTypeColumn;

    /**
     * The Report Customer Schedule Table View.
     */
        @FXML
        private TableView<Appointments> reportCustomerScheduleTableView;


    /**
     * Displays confirmation dialog and sets the Report Customer Schedule Table View with the Customer Info from the Data Base.
     *
     * @param event Report Contact Schedule action.
     * @throws SQLException From FXMLLoader.
     */
        @FXML
        void onReportContactScheduleComboBox(ActionEvent event) throws SQLException {
            reportCustomerScheduleTableView.setItems(AppointmentsQuery.getAppointmentsByCustomerID(reportContactScheduleComboBox.getValue().getId()));
        }

    /**
     * Displays confirmation dialog and loads Reports Main Page.
     *
     * @param actionEvent Report Contact Schedule Back button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void onContactScheduleBackButton(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/ReportsMainPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Initializes controller and generates the Appointment Info from the Database.
     *
     * @param url
     * @param resourceBundle The resources used to localize the root object.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reportContactScheduleAppointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        reportContactScheduleTitleColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        reportContactScheduleTypeColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        reportContactScheduleDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        reportContactScheduleStartColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        reportContactScheduleEndColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        reportContactScheduleCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));

        try {
            reportContactScheduleComboBox.setItems(ContactsQuery.getAllContacts());
            reportContactScheduleComboBox.setCellFactory(new Callback<ListView<Contacts>, ListCell<Contacts>>() {
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
    }
}
