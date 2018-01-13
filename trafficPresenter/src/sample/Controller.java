package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.WritableImage;
import sample.Entities.TrafficNode;
import sample.UseCase.UseCase;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class Controller {

    public static XYChart.Series lastSeries = new XYChart.Series();

    @FXML
    public LineChart<Date,Number> chart;

    @FXML
    public Button save;

    @FXML
    public ComboBox<String> roadChoice;

    @FXML
    public ComboBox<String> timeResolutionChoice;

    @FXML
    public ComboBox<String> direction;

    @FXML
    public ComboBox<String> day;

    @FXML
    public void readChoice(){
        int valueFromRoadChoice=0;
        String valueFromTimeResolution;
        TrafficNode temporaryStorageNode;
        XYChart.Series series = new XYChart.Series();

        chart.autosize();

        chart.getData().removeAll(lastSeries);

        valueFromRoadChoice = Integer.parseInt(roadChoice.getSelectionModel().getSelectedItem().toString());

        valueFromTimeResolution = timeResolutionChoice.getValue();

//        // get Traffic Node
        temporaryStorageNode = UseCase.getTrafficNodeBy(valueFromRoadChoice);

        // reduce Traffic Node By Hours
        temporaryStorageNode.getTrafficNodeReduceTo(valueFromTimeResolution);

        // reduce Traffic Node By week
        if (getIntegerFromWeekDay() != UseCase.notAllDaysSelected)
            temporaryStorageNode.getTrafficNodeReduceToWeekDay(getIntegerFromWeekDay());

        // adding elements to series
        for(Map.Entry<Date,Integer> entry:temporaryStorageNode.getVehicleFlow().entrySet())
            series.getData().add(new XYChart.Data<>(entry.getKey().toString(),entry.getValue()));

        chart.setTitle(temporaryStorageNode.getSource()+"->"+temporaryStorageNode.getDestination());

        chart.getData().addAll(series);

        lastSeries = series;

    }


    public void initialize(){
        UseCase.setupTrafficNodeList();
        List<String> roads = UseCase.getRoadFromTrafficNodeList();
        ObservableList<String> observableListRoad = FXCollections.observableList(roads);
        ObservableList<String> observableListTimeResolution = FXCollections.observableArrayList("1h","2h","4h","8h","1d");

        // set default value
        roadChoice.setValue("roads");
        roadChoice.setItems(observableListRoad);

        // set default value
        timeResolutionChoice.setValue("1h");
        timeResolutionChoice.setItems(observableListTimeResolution);

        // set default value
        day.setValue("All Days");
        day.setItems(FXCollections.observableArrayList("Monday","Tuesday","Wednesday","Thursday",
                "Friday","Saturday","Sunday","All Days"));

        // eliminates the problem of not displaying correct date on X label for chart
        chart.setAnimated(false);
    }

    @FXML
    public void setSave(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        WritableImage writableImage = chart.snapshot(new SnapshotParameters(),null);
        List<String> sourceDestination = UseCase.getSourceAndDestinationFromTrafficNodeList(Integer.parseInt(roadChoice.getValue()));
        File file = new File(sourceDestination.get(0)+"-"+
                sourceDestination.get(1)+"-"+timeResolutionChoice.getValue()+"-"+day.getValue()+".png");
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(writableImage,null),"png",file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        alert.setContentText("The chart has been saved as:\n "+sourceDestination.get(0)+"-"+
                sourceDestination.get(1)+"-"+timeResolutionChoice.getValue()+"-"+day.getValue()+".png");
        alert.show();
    }

    @FXML
    public void validateChoiceRoad() {

        try {
            Integer.parseInt(roadChoice.getSelectionModel().getSelectedItem().toString());
            readChoice();
        }
        catch (Exception e){
            Alert alertChooseRoad = new Alert(Alert.AlertType.INFORMATION);
            alertChooseRoad.setContentText("Please choose a road");
            alertChooseRoad.show();
        }
    }

    @FXML
    public void validateSaveFile() {

        try {
            Integer.parseInt(roadChoice.getSelectionModel().getSelectedItem().toString());
            setSave();
        }
        catch (Exception e){
            Alert alertChooseRoad = new Alert(Alert.AlertType.INFORMATION);
            alertChooseRoad.setContentText("Please choose a road in order to save");
            alertChooseRoad.show();
        }
    }

    public Integer getIntegerFromWeekDay(){
        String weekDay = day.getValue();
        switch (weekDay){
            case "Monday": return 1;
            case "Tuesday": return 2;
            case "Wednesday": return 3;
            case "Thursday": return 4;
            case "Friday": return 5;
            case "Saturday": return 6;
            case "Sunday": return 0;
            default: return 7;
        }
    }

}
