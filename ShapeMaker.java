/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ms_paint;

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author PC-1
 */
public class ShapeMaker {
    private double x1;
    private double x2;
    private double width;
    private double y1;
    private double y2;
    private double height;
    private Color colour;
    
    public ShapeMaker(double x1, double y1, double x2, double y2, Color colour){
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.colour = colour;
        if(x1 < x2){
            this.width = x2 - x1;
        } else {
            this.width = x1 - x2;
        }
        if(y1 < y2){ 
            this.height = y2 - y1;
        } else {
            this.height = y1 - y2;
        }
    }
    
    /**
     * @return the x1
     */
    public double getX1() {
        return x1;
    }

    /**
     * @return the x2
     */
    public double getX2() {
        return x2;
    }

    /**
     * @return the y1
     */
    public double getY1() {
        return y1;
    }

    /**
     * @return the y2
     */
    public double getY2() {
        return y2;
    }
    
    public double getWidth(){
        return width;
    }
    
    public double getHeight(){
        return height;
    }
    
    public void setColour(Color newColour){
        colour = newColour;
    }
    
    public Color getColour(){
        return colour;
    }
    
    public void draw(GraphicsContext gc){
        System.out.println("This is not the methode we want to invoke!");
    }
    
    @Override
    public String toString(){
        return ("Origin at: (" + x1 + ", " + y1 + ")." 
                + "\nColour: " + colour 
                + "\nDimentions: " + width +", " + height);
    }
}


