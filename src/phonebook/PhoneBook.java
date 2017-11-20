/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 *private static final String JDBC_URL = "jdbc:derby:database;create=true";
 and open the template in the editor.
 */
package phonebook;

import java.sql.SQLException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Pepe
 */
public class PhoneBook extends Application {

    static int totalRecordcount = 0;
    PhoneBookDB database =null;
    @Override
    public void start(Stage primaryStage) {

       
        try {
            database = new PhoneBookDB();
           //database.createTable();
            
            database.printTable();
        } catch (SQLException exc) {
            
            
        }

        VBox mainVB = new VBox();
        Scene scene = new Scene(mainVB, 350, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Phone Book");
        primaryStage.show();

        HBox inputHB = new HBox();

        TextField nameTF = new TextField();
        nameTF.setPromptText("Name");
        TextField numberTF = new TextField();
        numberTF.setPromptText("Number");
        Button addButton = new Button("ADD");

        inputHB.getChildren().add(nameTF);
        inputHB.getChildren().add(numberTF);
        inputHB.getChildren().add(addButton);

        mainVB.getChildren().add(inputHB);

        VBox displayVB = new VBox();

        ScrollPane displaySP = new ScrollPane(displayVB);
        mainVB.getChildren().add(displaySP);

        HBox displayTotalHB = new HBox();
        Label totalRecordLabel = new Label("Total records: ");
        TextField TotalRecordTF = new TextField();

        displayTotalHB.getChildren().add(totalRecordLabel);
        displayTotalHB.getChildren().add(TotalRecordTF);

        mainVB.getChildren().add(displayTotalHB);

        inputHB.requestFocus();

        addButton.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent eventOne) {
                String name = nameTF.getText();
                String number = numberTF.getText();
                System.out.println(name + " " + number);
                
                Label diplayLabel = new Label(name + " #" + number);
                displayVB.getChildren().add(diplayLabel);
                //add to database
                database.addToTable(name, number);
                
                totalRecordcount++;
                TotalRecordTF.setText("" + totalRecordcount);
            }
        });

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        launch(args);
    }

}
