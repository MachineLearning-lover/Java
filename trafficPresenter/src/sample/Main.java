package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Traffic Control Statistics");
        primaryStage.setScene(new Scene(root, 634, 621));

        // set max dimensions for GUI
        primaryStage.setMaxHeight(634);
        primaryStage.setMaxWidth(621);

        // remove minimize/maximize button
        primaryStage.resizableProperty().setValue(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
