/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package talkpadfrontend;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author gaurav
 */
public class FXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root;
        root = FXMLLoader.load(getClass().getResource("../login/login.fxml"));
     
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("../login/login.css").toExternalForm());
        primaryStage.setTitle("Login page");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
