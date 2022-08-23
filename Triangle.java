/**
	This is a triangle shape. It works by connecting three points and filling
    it in with a color. This gives this triangle flexibility.
	
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

public class Triangle implements DrawingObject {
    private Color color;

    private Path2D.Double triangle;

    /**
     * Constructs the triangle based on the parameters.
     * 
     * @param x1 first x coordinate
     * @param y1 first y coordinate
     * @param x2 second x coordinate
     * @param y2 second y coordinate
     * @param x3 third x coordinate
     * @param y3 third y coordinate
     * @param color color of triangle
     */
    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3, Color color) {
        //variables
        this.color = color;
        //shape
        triangle = new Path2D.Double();
        triangle.moveTo(x1,y1); //moves to the point (x1,y1)
        triangle.lineTo(x2,y2); //draws a line to the point (x2,y2) from (x1,x2)
        triangle.lineTo(x3,y3); //draws a line to the point (x3,y3) from (x2,y2)
    }
    
    
    /** 
     * Draws the shapes instantiated in the constructor
     * 
     * @param g2d
     */
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fill(triangle);
    }

    
    /** 
     * Does nothing.
     * 
     * @param speed does nothing.
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
