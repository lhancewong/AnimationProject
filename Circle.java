/**
	A circle class that uses ellipse2D.Double. It can only use one color.
    The x and y coordinate is not the upper right but actually the center
    of the circle.
	
	@author Lhance Christian S. Wong (205467)
	@version April 20, 2021
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

public class Circle implements DrawingObject {
    
    private Ellipse2D.Double circle;
    private Color color;

    /**
     * Instantiates a circle given the parameters.
     * 
     * @param x center x coordinate
     * @param y center y coordinate
     * @param diameter diameter of circle
     * @param color color of circle
     */
    public Circle(double x, double y, double diameter, Color color) {
        this.color = color;

        double radius = diameter/2.0;
        circle = new Ellipse2D.Double(x-radius, y-radius, diameter, diameter);
    }

    /**
     * Draws the circle contructed in the constructor.
     * 
     * @param g2d
     */
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fill(circle);
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
     * Does nothing
     */
    @Override
    public void reset() {
        // TODO Auto-generated method stub
        
    }
}
