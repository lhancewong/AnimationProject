/**
	A line shape with a certain thickness defined by double thickness. 
    I also used extra variables in BasicStroke to make sure it doesnt go beyond
    the start and end x and y's.
	
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

public class Line implements DrawingObject {
    private BasicStroke thicknessStroke;
    private Color color;
    
    private Line2D.Double line;

    /**
     * Draws a line that can be thick or thin.
     * 
     * @param startX x coordinate at one end of th line
     * @param startY y coordinates at one end of the line
     * @param endX x coordinate at the other end of the line
     * @param endY y coordinate at the other end of the line
     * @param thickness how thick the line is in pixels
     * @param color color of line
     */
    public Line(double startX, double startY, double endX, double endY, double thickness, Color color) {
        //variables
        thicknessStroke = new BasicStroke((int)thickness, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
        this.color = color;
        //shape
        line = new Line2D.Double(startX, startY, endX, endY);
    }

    /**
     * Draws the shapes constructed in the constructor.
     * 
     * @param g2d
     */
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.setStroke(thicknessStroke);
        g2d.draw(line);
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
