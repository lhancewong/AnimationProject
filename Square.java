/**
	This is a square shape whose x and y coordinates point to its upper left corner.
    Obviously, this cant be a rectangle
	
	@author Lhance Christian S. Wong (205467)
	@version April 19, 2021
**/

/*
	I have not discussed the Java language code in my program 
	with anyone other than my instructor or the teaching assistants 
	assigned to this course.

	I have not used Java language code obtained from another student, 
	or any other unauthorized source, either modified or unmodified.

	If any Java language code or documentation used in my program 
	was obtained from another source, such as a textbook or website, 
	that has been clearly noted with a proper citation in the comments 
	of my program.
*/

import java.awt.*;
import java.awt.geom.*;

public class Square implements DrawingObject {
    private Color color;

    private Rectangle2D.Double square;

    /**
     * Sraws a square whose x and y are the upper corners.
     * 
     * @param x upper left corner x coordinate
     * @param y upper left corner y coordinate
     * @param sideLength side length of square
     * @param color color of square
     */
    public Square(double x, double y, double sideLength, Color color) {
        square = new Rectangle2D.Double(x, y, sideLength, sideLength);
    }

    
    /** 
     * Draws a square with x and y being the upper left corner.
     * 
     * @param g2d
     */
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fill(square);
    }

    
    /** 
     * Does nothing.
     * 
     * @param speed does nothing
     */
    @Override
    public void update(double speed) {
        // TODO Auto-generated method stub
        
    }

    /**
     * Does nothing.
     */
    @Override
    public void reset() {
        // TODO Auto-generated method stub
        
    }
}
