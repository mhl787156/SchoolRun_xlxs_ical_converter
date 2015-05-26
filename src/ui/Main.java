package ui;

import convertor.spreadsheet;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        primaryStage.setTitle("Form to fill in");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));


        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("Path to Excel File:");
        grid.add(userName, 0, 1);

        final TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);


        Button btn = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);


        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);


        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setText("Sign in button pressed");

                String filePath = userTextField.getCharacters().toString();

                if (new File(filePath).exists())
                {
                    try {

                        if(filePath.contains(".xls") ||
                                filePath.contains(".xlsx")) {
                            spreadsheet.readSpreadsheet(filePath);
                        }else{
                            actiontarget.setText("Please input an excel file");
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }else{
                    actiontarget.setText("Please input an existing file");
                    System.out.println("Please input an existing file");
                }


            }
        });






        //StackPane r = new StackPane();
        //r = addButton( r , "Hello World");


        primaryStage.setScene(new Scene(grid, 800, 500));
        primaryStage.show();
    }

    public StackPane addButton(StackPane sp , final String str){
        Button btn = new Button();
        btn.setText("Hello World");

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(str);
            }
        });

        sp.getChildren().add(btn);
        return sp;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
