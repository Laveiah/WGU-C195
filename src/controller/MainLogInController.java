package controller;

        import dao.AppointmentsQuery;
        import dao.UsersQuery;
        import javafx.collections.ObservableList;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.fxml.Initializable;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Alert;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.control.TextField;
        import javafx.stage.Stage;
        import model.AppointmentTime;

        import java.io.*;
        import java.net.URL;
        import java.sql.SQLException;
        import java.time.LocalDateTime;
        import java.time.ZoneId;
        import java.util.Locale;
        import java.util.ResourceBundle;

/**
 * Controller class that displays the Main screen of the application.
 *
 * @author Joseph Veal-Briscoe
 */
public class MainLogInController implements Initializable {
    public Label loginTitle;
    public TextField loginScreenUserNameField;
    public TextField loginScreenPasswordField;
    public Label loginScreenLocationLabel;
    public Button loginExitButton;
    private ResourceBundle rb = ResourceBundle.getBundle("language", Locale.getDefault());

    Stage stage;
    Parent scene;


    /**
     * The Location Login text field.
     */
    @FXML
    private TextField locationLogIn;

    /**
     * The Login button.
     */
    @FXML
    private Button loginButton;

    /**
     * The Password Login button.
     */
    @FXML
    private TextField passwordLogIn;

    /**
     * The UserNameLogin text field.
     */
    @FXML
    private TextField userNameLogIn;

    /**
     * Displays confirmation dialog, exits/closes the program.
     *
     * @param event Back button action.
     */
    @FXML
    void onLoginExitButton(ActionEvent event) {
        System.exit(0);

    }

    /**
     * Checks if userID and password match the database. Loads SelectViewController.
     *
     * @param actionEvent Login button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void onLoginButton(ActionEvent actionEvent) throws IOException, SQLException {

        String userName = userNameLogIn.getText();
        String passWord = passwordLogIn.getText();

        if (UsersQuery.userIDCheck(userName, passWord)) { //write valid login
            try {
                PrintWriter pw = new PrintWriter(new FileOutputStream(
                        new File("login_activity.txt"),
                        true /* append = true */));
                pw.append("Valid Login by " + userName + " at " + LocalDateTime.now().toString() + "\n");
                pw.flush();//holds the data in memory
                pw.close();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            ObservableList<AppointmentTime> list = AppointmentsQuery.getAppointmentsByUserName(userName);
            if (list.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Alert");
                alert.setContentText("No Appointment Within 15 Minutes");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Alert");
                alert.setContentText("There is an upcoming appointment in the next 15 minutes. ID " + list.get(0).getAppointmentID() + " at " + list.get(0).getStart());
                alert.showAndWait();

            }

            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("../view/selectView.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();



        } else {
            //invalid login
                try {
                    PrintWriter pw = new PrintWriter(new FileOutputStream(
                            new File("login_activity.txt"),
                            true /* append = true */));
                    pw.append("Invalid Login by " + userName + " at " + LocalDateTime.now().toString() + "\n");
                    pw.flush();//holds the data in memory
                    pw.close();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(rb.getString("titleError"));
            alert.setHeaderText(rb.getString("headerText"));
            alert.setContentText(rb.getString("contentText"));
            alert.showAndWait();
        }

    }


    /**
     * Initializes controller and gets username and password.
     *
     * @param url
     * @param resourceBundle The resources used to localize the root object.
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginScreenLocationLabel.setText(ZoneId.systemDefault().toString()); // get timezone
        userNameLogIn.setPromptText(rb.getString("usernamePrompt"));
        passwordLogIn.setPromptText(rb.getString("passwordPrompt"));
        loginTitle.setText(rb.getString("loginTitlePrompt"));
        loginButton.setText(rb.getString("loginButtonPrompt"));
        loginExitButton.setText(rb.getString("loginExitButtonPrompt"));
    }
}



