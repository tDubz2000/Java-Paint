/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ms_paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class CircleMaker extends ShapeMaker {
    
    private double radius;

    public CircleMaker(double x1, double y1, double x2, double y2, Color colour) {
        super(x1, y1, x2, y2, colour);
        this.radius = getWidth() / 2;
    }
    
    public double getRadius(){
        return radius;
    }
    
    @Override
    public void draw(GraphicsContext gc){
        gc.setFill(getColour());
        if(getX1() < getX2() && getY1() < getY2()){
            gc.fillOval( getX1(), getY1(), getWidth(), getHeight());
        } else if (getX1() > getX2() && getY1() > getY2()){
            gc.fillOval(getX2(), getY2(), getWidth(), getHeight());
        } else if (getX1() < getX2() && getY1() > getY2()) {
            gc.fillOval(getX1(), getY2(), getWidth(), getHeight());
        } else if(getX1() > getX2() && getY1() < getY2()) {
            gc.fillOval(getX2(), getY1(), getWidth(), getHeight());
        }
        System.out.println("This Circle was drawn!");
    }
    
    @Override
    public String toString(){
        return super.toString()
                + "\nRadius: " + radius;
    }
}
