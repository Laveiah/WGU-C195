package main;

import helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;

public class Main extends Application {

    /**
     * Initializes the MainloginView.fxml
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/MainLoginView.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    /**
     * The main method loads all database connection.
     *
     * @param args
     */
    public static void main(String[] args) {
//       Locale.setDefault(new Locale("fr", "FR")); // french


        JDBC.makeConnection();
        launch(args);
        JDBC.closeConnection();
    }
}
