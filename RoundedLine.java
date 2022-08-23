/**
	Shape of a hotdog. It's basically a line with rounded ends.
	
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

public class RoundedLine implements DrawingObject {
    private BasicStroke roundedStroke;
    private Color color;

    private Line2D.Double roundedLine;

    /**
     * Also known as a hotdog, but for professionalism, I named it roundedline :c
     * Constructs the shapes of the hotdog.
     * 
     * @param startX starting x coordinate
     * @param startY starting y coordinate
     * @param endX end x coordinate
     * @param endY end y coordinate
     * @param thickness thickness of roundedline
     * @param color color of rounded line
     */
    public RoundedLine(double startX, double startY, double endX, double endY, double thickness, Color color) {
        //variables
        roundedStroke  = new BasicStroke((int)thickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        this.color = color;
        //shape
        roundedLine = new Line2D.Double(startX, startY, endX, endY);
    }

    
    /** 
     * Draws a hotdog shape from (startX, startY) to (endX, endY).
     * 
     * @param g2d
     */
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.setStroke(roundedStroke);
        g2d.draw(roundedLine);
        
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
