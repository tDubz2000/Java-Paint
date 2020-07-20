/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ms_paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author PC-1
 */
public class SquareMaker extends ShapeMaker {
    
    public SquareMaker(double x1, double x2, double y1, double y2, Color colour){
        super(x1, x2, y1, y2, colour);
    }
    
    @Override
    public void draw(GraphicsContext gc){
        gc.setFill(getColour());
        if(getX1() < getX2() && getY1() < getY2()){
            gc.fillRect( getX1(), getY1(), getWidth(), getHeight());
        } else if (getX1() > getX2() && getY1() > getY2()){
            gc.fillRect( getX2(), getY2(), getWidth(), getHeight());
        } else if (getX1() < getX2() && getY1() > getY2()) {
            gc.fillRect(getX1(), getY2(), getWidth(), getHeight());
        } else if(getX1() > getX2() && getY1() < getY2()) {
            gc.fillRect(getX2(), getY1(), getWidth(), getHeight());
        }
        gc.setFill(getColour());
        System.out.println("This Square was drawn!");
    }
    
    @Override
    public String toString(){
        return super.toString() + "\nDimentions: " + getWidth() + ", " + getHeight();
    }
}
