package sample;

import es.usc.citius.hipster.algorithm.Hipster;
import es.usc.citius.hipster.model.Transition;
import es.usc.citius.hipster.model.function.CostFunction;
import es.usc.citius.hipster.model.function.HeuristicFunction;
import es.usc.citius.hipster.model.function.impl.StateTransitionFunction;
import es.usc.citius.hipster.model.problem.ProblemBuilder;
import es.usc.citius.hipster.model.problem.SearchProblem;
import es.usc.citius.hipster.util.examples.maze.Maze2D;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.List;


public class Controller implements Serializable {

    @FXML
    private Canvas canvas;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private TextField brushSize;

    @FXML
    private CheckBox eraser;

    @FXML
    private TextField startPointX;

    @FXML
    private TextField startPointY;

    @FXML
    private TextField endPointX;

    @FXML
    private TextField endPointY;

    @FXML
    private Button generate;

    @FXML
    private Button setPosition;


    public void initialize(){

        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        colorPicker.setValue(Color.BLACK);

        canvas.setOnMouseDragged(e->{
            double size = Double.parseDouble(brushSize.getText());
            double x,y;
            x = e.getSceneX()-size/2;
            y = e.getSceneY()-size/2;

            if (eraser.isSelected()){
                graphicsContext.clearRect(x,y,size,size);
            }
            else
            {
                graphicsContext.setFill(colorPicker.getValue());
                graphicsContext.fillRect(x,y,size,size);

            }

        });
    }

    @FXML
    public void onSave(){

        double [][] matrixPixels = new double[560][600];

        try {
            Image snapshot = canvas.snapshot(null, null);

            System.out.println(canvas.getHeight()+" "+canvas.getWidth());
            System.out.println(snapshot.getHeight()+" "+snapshot.getWidth());

            FileWriter file = new FileWriter("matriceDeTest.txt");
            PrintWriter writer = new PrintWriter(file);
            for (int i=0;i<(int)snapshot.getWidth();i++) {
                for (int j = 0; j < (int) snapshot.getHeight(); j++) {
                    matrixPixels[i][j] = snapshot.getPixelReader().getColor(i, j).getRed();
                    writer.print(matrixPixels[i][j]+" ");
                }
                writer.println();
            }
            writer.close();


            ImageIO.write(SwingFXUtils.fromFXImage(snapshot,null),"png",new File("imageJava.png"));
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Image saved as imageJava.png in the current director!");
            alert.setTitle("Image Saved!");
            alert.showAndWait();
        }
        catch (Exception e){
            System.out.println("error saving image: "+e.toString());
        }
    }

    @FXML
    public void onExit(){
        System.exit(0);
    }

    @FXML
    public void newPage(){
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.clearRect(0,0,800,600);
    }

    @FXML
    public void generatePath() {

        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        String [] maze = createMaze();
        calculateShortestPath(maze);
    }


    public double [][] getMatrixFromCanvas(){
        double [][] matrix = new double[560][600];
        Image snapshot = canvas.snapshot(null,null);
        for (int i=0;i<(int)snapshot.getWidth();i++) {
            for (int j = 0; j < (int) snapshot.getHeight(); j++) {
                matrix[i][j] = snapshot.getPixelReader().getColor(i, j).getRed();
            }
        }
        return matrix;
    }


    public String[] createMaze() {
        String[] maze = new String[560];
        String line="";
        double [][] matrix = getMatrixFromCanvas();
        StartStopPoint startStopPoints = new StartStopPoint();
        setStartStopPoints(startStopPoints);

        for(int i=0;i<560;i++) {
            for (int j = 0; j < 600; j++) {
                if ((int)startStopPoints.getStartX() == i && (int)startStopPoints.getStartY() == j)
                    line+="S";
                else
                    if ((int)startStopPoints.getEndX() ==i && (int)startStopPoints.getEndY() ==j )
                        line+="G";
                else
                    if (matrix[i][j] < 1)
                            line+="X";
                        else
                            line+=" ";
            }
            maze[i]=line;
            line = "";
        }
    return maze;
    }


    public void calculateShortestPath(String [] example){

        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        Maze2D maze = new Maze2D(example);
        Point origin = maze.getInitialLoc();
        final Point goal = maze.getGoalLoc();
        SearchProblem p = ProblemBuilder.create().initialState(origin).defineProblemWithoutActions().useTransitionFunction(new StateTransitionFunction<Point>() {
            public Iterable<Point> successorsOf(Point state) {
                return maze.validLocationsFrom(state);
            }
        }).useCostFunction(new CostFunction<Void, Point, Double>() {
            public Double evaluate(Transition<Void, Point> transition) {
                Point source = (Point)transition.getFromState();
                Point destination = (Point)transition.getState();
                return source.distance(destination);
            }
        }).useHeuristicFunction(new HeuristicFunction<Point, Double>() {
            public Double estimate(Point state) {
                return state.distance(goal);
            }
        }).build();


        List<List<Object>> lists =Hipster.createAStar(p).search(goal).getOptimalPaths();
        graphicsContext.setLineWidth(Double.parseDouble(brushSize.getText()));

        graphicsContext.setFill(Color.PINK);

        for(List list:lists)
            for(Object object:list){
                graphicsContext.fillRect(((Point)object).getY(),((Point)object).getX(),2.5,2.5);
            }

        graphicsContext.setFill(colorPicker.getValue());
    }

    @FXML
    public void displayPositions(){
        StartStopPoint startStopPoint = new StartStopPoint();
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
//        the dimension of the rectangles
        int width = 20;
        int hight = 20;
        setStartStopPoints(startStopPoint);
        graphicsContext.setFill(Color.RED);
        graphicsContext.fillRect(startStopPoint.getEndX(),startStopPoint.getEndY(),width,hight);
        graphicsContext.setFill(Color.GREEN);
        graphicsContext.fillRect(startStopPoint.getStartX(),startStopPoint.getStartY(),width,hight);

//        restore the previous color
        graphicsContext.setFill(colorPicker.getValue());
    }

    public void setStartStopPoints(StartStopPoint startStopPoints){

        Double startXDef = 1.0;
        Double startYDef = 1.0;
        Double endXDef = 500.0;
        Double endYDef = 500.0;

        try{
            startStopPoints.setStartX(Double.parseDouble(startPointX.getText()));
            startStopPoints.setStartY(Double.parseDouble(startPointY.getText()));
            startStopPoints.setEndX(Double.parseDouble(endPointX.getText()));
            startStopPoints.setEndY(Double.parseDouble(endPointY.getText()));
        }
        catch (Exception exception){
            startStopPoints.setStartX(startXDef);
            startStopPoints.setStartY(startYDef);
            startStopPoints.setEndX(endXDef);
            startStopPoints.setEndY(endYDef);
//            System.out.println("we used default start/end points");
        }
    }

}
