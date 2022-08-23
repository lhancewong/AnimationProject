/**
	This is an interface. It has a draw method, update method and reset method.
	
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

public interface DrawingObject {
    
    /**
     * Draw method.
     * 
     * @param g2d
     */
    void draw(Graphics2D g2d);

    /**
     * Update method.
     * 
     * @param speed how much this update function updates
     */
    void update(double speed);

    /**
     * Reset method.
     */
    void reset();
}
