/**
	This class is the background of the night or day scene. It can only
    be drawn at 0,0. However, it can accept a width or height that isn't
    the size of the canvas.
	
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

public class Background implements DrawingObject{
    private Rectangle2D.Double background;
    private Color color;

    /**
     * Constructs a rectangle shape which I chose to call background. Also
     * initializes color. Always draws at 0,0, since it's a background shape.
     * 
     * @param width width of background
     * @param height height of background
     * @param color color of background
     */
    public Background(double width, double height, Color color) {
        background = new Rectangle2D.Double(0,0,width,height);
        this.color = color;
    }

    /**
     * Draws the background in given a color
     * 
     * @param g2d
     */
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fill(background);

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
