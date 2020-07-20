/*
  * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ms_paint;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Separator;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author PC-1
 */
public class MS_Paint extends Application {
    
    Canvas drawSpace = new Canvas(1000,750); //create the drawing canvas
    GraphicsContext gc = drawSpace.getGraphicsContext2D();
    
    private ToolBar menu;
    private HBox shapeBox;
    private VBox shapeMenu;
    private ToggleGroup shapes;
    private ToggleButton squareButton;
    private ToggleButton circleButton;
    private ToggleButton drawButton;
    
    private HBox colourBox;
    private VBox styleMenu;
    private ToggleGroup colourSelector;
    private RadioButton red;
    private RadioButton orange;
    private RadioButton yellow;
    private RadioButton green;
    private RadioButton blue;
    private RadioButton purple;
    
    private Label styleThickness;
    private ComboBox thickness;
    private HBox thicknessBox;
    
    Button undo;
    
    
    private ArrayList<ShapeMaker> drawShapes = new ArrayList<>();
    private static double clickY;
    private static double clickX;
    
    public Color getColourSelected(){
        if(red.isSelected() == true){
            return Color.RED;
        }else if(orange.isSelected() == true){
            return Color.ORANGE;
        }else if(yellow.isSelected() == true){
            return Color.YELLOW;
        }else if(green.isSelected() == true){
            return Color.GREEN;
        }else if(blue.isSelected() == true){
            return Color.BLUE;
        }else if(purple.isSelected() == true){
            return Color.PURPLE;
        }else{
            System.out.println("Youve Fucked up the console!!! You Buffoon!!");
            return null;
        }
    }
    
    public void mouseDraggedHandler(MouseEvent d) {
        if(shapes.getSelectedToggle().equals(squareButton)){
            //purposely left balnk
        } else if(shapes.getSelectedToggle().equals(circleButton)){
            //purposely left blank
        } else if(shapes.getSelectedToggle().equals(drawButton)){
            gc.fillRect(d.getX(), d.getY(), 1, 1);
            gc.setFill(getColourSelected());
        }
    //without if statment, free draw method will be invoked when ever mouse drags no matter what
//NEEDS EXCEPTION STATMENT
    }
    
    /**
     * getting the origin of shapes to be drawn based on the coordinates of the click and setting a static variables values to be used in other methods
     * @param c 
     */
    public void mouseClickedHandler(MouseEvent c){
        if(shapes.getSelectedToggle().equals(squareButton)){
            System.out.println("clicked @: " + c.getX() + ", " + c.getY());
            clickX = c.getX();
            clickY = c.getY();
        } else if(shapes.getSelectedToggle().equals(circleButton)){
            System.out.println(c.getX() + ", " + c.getY());
            clickX = c.getX();
            clickY = c.getY();
        }
//NEEDS EXCEPTION LINE
    }
    
    /**
     * adding shape object to arraylist and drawing the object on the canvas.
     * **NOTE THAT DRAWING IT BACKWORDS WILL NOT DRAW THE SHAPE AND CAUSE LOGICAL ERRORS!**
     * @param r 
     */
    public void mouseReleasedHandler(MouseEvent r){
        if(shapes.getSelectedToggle().equals(squareButton)){
            System.out.println("released @: " + r.getX() + ", " + r.getY());
            drawShapes.add(new SquareMaker(clickX, clickY, r.getX(), r.getY(), getColourSelected()));
            drawShapes.get(drawShapes.size() - 1).draw(gc);
            System.out.println(drawShapes.get(drawShapes.size()-1).toString());
            
        } else if(shapes.getSelectedToggle().equals(circleButton)){
            System.out.println("released @: " + r.getX() + ", " + r.getY());
            drawShapes.add(new CircleMaker(clickX, clickY, r.getX(), r.getY(), getColourSelected()));
            drawShapes.get(drawShapes.size() - 1).draw(gc);
            System.out.println(drawShapes.get(drawShapes.size()-1).toString());
        }
    }
    
    /**
     * This action, when called on, will clear the canvas entirely, then delete the last object referenced in the arrayList drawShapes, then redraw all the objects in the arraylist. 
     * @param u 
     */
    public void undoHandler(ActionEvent u){
        gc.clearRect(drawSpace.getLayoutX(), drawSpace.getLayoutY(), drawSpace.getHeight(), drawSpace.getWidth());
        try{
            drawShapes.remove(drawShapes.size() - 1);
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Nothing to undo");
        }
        for(ShapeMaker s: drawShapes){
            s.draw(gc);
            s.toString();
            System.out.println(s.toString());
            
        }
    }
    
    
    
    
    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane(); //create the main window to hold all other nodes
        Scene scene = new Scene(root, 1000, 750); //create the scene 
        primaryStage.setTitle("JavaPaint!"); //set the title of the /gui application
        primaryStage.setScene(scene);
        
//Every line of code from here until the 'End of ToolBar' comment is code for creating and adding nodes to the toolBar
        menu = new ToolBar(); //create TOOLBAR constructor named menu
        shapeBox = new HBox(); //create Layout Node to hold shape related buttons
        shapeMenu = new VBox(); //create Layout Node to hold shapeBox and Labels for shapeBox
        HBox colourMenu = new HBox(); 
        
    //Start of code to creating ToggleGroup shapes
        Label shapeLabel = new Label("Shape"); //create text label called shapeLabel
        Image square = new Image("square.png"); //create image names square using local URL
        ImageView sqr = new ImageView(square); //create imageview from image squre
        squareButton = new ToggleButton(null, sqr); //create square button
        Image circle = new Image("circle.png"); //create image named circle using local URL
        ImageView cir = new ImageView(circle); //create imageview from image circle
        circleButton = new ToggleButton(null, cir); //create circle button
        Image brush = new Image("brush.png"); //create image named draw using local URL
        ImageView draw = new ImageView(brush); //create imageview from image brush
        drawButton = new ToggleButton(null, draw); //creates a draw button
        shapes = new ToggleGroup(); //creates a toggle group shapes
        squareButton.setToggleGroup(shapes); //adds button to toggleGroup shapes
        circleButton.setToggleGroup(shapes); //adds nutton to togglegroup shapes
        drawButton.setToggleGroup(shapes); //adds button to toggleGroup shapes
        shapeBox.getChildren().addAll(squareButton, circleButton, drawButton); //adds all the buttons in the ToggleGroup to HBox shapeBox
        shapeMenu.getChildren().addAll(shapeLabel,shapeBox); //adding nodes to the vBox
    //End of code for ToggleGroup shapes
    //Start of styleMenu
        Label styleColour = new Label("Colours ");
        red = new RadioButton();
        red.setStyle("-fx-color: Red");
        orange = new RadioButton();
        orange.setStyle("-fx-color: Orange");
        yellow = new RadioButton();
        yellow.setStyle("-fx-color: Yellow");
        green = new RadioButton();
        green.setStyle("-fx-color: Green");
        blue = new RadioButton();
        blue.setStyle("-fx-color: Blue");
        purple = new RadioButton();
        purple.setStyle("-fx-color: Purple");
        
        ArrayList<RadioButton> buttonList = new ArrayList<>();
        buttonList.add(red);
        buttonList.add(orange);
        buttonList.add(yellow);
        buttonList.add(green);
        buttonList.add(blue);
        buttonList.add(purple);
        for(RadioButton a: buttonList){
            a.getStyleClass().remove("radio-button");
            a.getStyleClass().add("toggle-button");
            a.setPrefSize(20, 20);
            a.setMaxSize(a.getPrefWidth(), a.getPrefHeight());
            a.setMinSize(a.getPrefWidth(), a.getPrefHeight());
        }
        
        colourSelector = new ToggleGroup();
        red.setToggleGroup(colourSelector);
        orange.setToggleGroup(colourSelector);
        yellow.setToggleGroup(colourSelector);
        green.setToggleGroup(colourSelector);
        blue.setToggleGroup(colourSelector);
        purple.setToggleGroup(colourSelector);
        
        styleThickness = new Label("Thickness ");
        thickness = new ComboBox();
        
        
        
        colourBox = new HBox();
        colourBox.getChildren().addAll(styleColour, red, orange, yellow, green, blue, purple);
        thicknessBox = new HBox();
        thicknessBox.getChildren().addAll(styleThickness, thickness);
        styleMenu = new VBox();
        styleMenu.getChildren().addAll(colourBox, thicknessBox);
    //End of styleMenu
    //Start of Undo Button
        undo = new Button("UNDO");
        undo.setOnAction(this::undoHandler);
    //End of Undo Button

        menu.getItems().addAll(shapeMenu, new Separator(),styleMenu, new Separator(), undo); //add layout nodes that hold menue buttons to the toolbar 
//End of ToolBar
        
        root.getChildren().addAll(drawSpace, menu); //add toolbar and canvas to the primary stage
                
        
        sqr.setFitWidth(20); //set square image width to 20px
        sqr.setFitHeight(20); //set square image height to 20px
        cir.setFitHeight(20); // set circle image height to 20px
        cir.setFitWidth(20); // set circle image width to 20px
        draw.setFitHeight(20); //set draw button height to 20px
        draw.setFitWidth(20); //set draw button width to 20 px
        menu.prefWidthProperty().bind(primaryStage.widthProperty()); //set the wdth of toolbar:menu to be equal to the primarystage width
        menu.prefHeight(75); //set preferred height of ToolBar:menu respectively    
        
        drawSpace.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::mouseDraggedHandler); //add listener to canvas detecting when mouse is dragged on canvas, and envoke mouse dragged handler
        drawSpace.addEventHandler(MouseEvent.MOUSE_PRESSED, this::mouseClickedHandler); // add listener to canvas detecting mouse clicks and envoking mouseCLickedHandler method
        drawSpace.addEventHandler(MouseEvent.MOUSE_RELEASED, this::mouseReleasedHandler); //add listened to canvas detecting mouse depression to envoke mouseREleasedHandler method
        drawSpace.prefWidth(primaryStage.getWidth()); //set canvas Drawspace width to stage width
        drawSpace.prefHeight(primaryStage.getHeight()); // set canvas drawspace Height to stage height
        
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
