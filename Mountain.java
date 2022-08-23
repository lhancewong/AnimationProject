/**
	A mountain shape which is made of two triangles. The left side is a shadow
    while the right side is the lit part.
	
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

public class Mountain implements DrawingObject {
    
    private Triangle shadow, mountain;
    
    /**
     * Draws two triangles which make an isoceles triangle. They can be in 
     * different colors.
     * 
     * @param x midpointX of the base of the mountain
     * @param y starting height of the mountain
     * @param base length of the base
     * @param height height of mountain
     * @param shadowColor color of the left side of the mountain
     * @param mountainColor color of the right side of the mountain
     */
    public Mountain(double x, double y, double base, double height, Color shadowColor, Color mountainColor) {
        shadow = new Triangle(x-base/2.0,y,x,y,x,y-height,shadowColor);
        mountain = new Triangle(x+base/2.0,y,x,y,x,y-height,mountainColor);
    }

    
    /** 
     * Draws the shapes constructed in the constructor
     * 
     * @param g2d
     */
    @Override
    public void draw(Graphics2D g2d) {
        shadow.draw(g2d);
        mountain.draw(g2d);
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
