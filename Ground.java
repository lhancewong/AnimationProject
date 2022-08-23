/**
	This is made of 7 roundedline shapes. It is intended that one side is 
    higher than the other. It can also only have one color.
	
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
import java.util.*;

public class Ground implements DrawingObject {
    
    private ArrayList<RoundedLine> ground = new ArrayList<RoundedLine>();

    /**
     * Draws the shape of the ground using an arraylist of roundedlines (hotdogs)
     * 
     * @param x x coordinate of the center-ish of the ground
     * @param y y coordinate of the bottom of th ground
     * @param size size of the ground 
     * @param color color of the ground
     */
    public Ground(double x, double y, double size, Color color) {

        //it is made up of many overlapping roundedlines of varying thickness
        ground.add(new RoundedLine(x-11.0*size, y-2.5*size, 
                                   x+6.00*size, y-2.5*size, 3.0*size, color)); //1
        ground.add(new RoundedLine(x-16.0*size, y-5.0*size, 
                                   x+6.00*size, y-5.0*size, 2.2*size, color)); //2
        ground.add(new RoundedLine(x-21.0*size, y-6.8*size, 
                                   x+3.00*size, y-6.8*size, 2.5*size, color)); //3
        ground.add(new RoundedLine(x-1.00*size, y-4.0*size, 
                                   x+16.0*size, y-4.0*size, 2.7*size, color)); //4
        ground.add(new RoundedLine(x-1.00*size, y-6.5*size, 
                                   x+18.0*size, y-6.5*size, 2.5*size, color)); //5
        ground.add(new RoundedLine(x-19.0*size, y-8.5*size, 
                                   x+3.00*size, y-8.5*size, 1.5*size, color)); //6
        ground.add(new RoundedLine(x-2.00*size, y-8.5*size, 
                                   x+20.0*size, y-8.5*size, 2.7*size, color)); //7
    }

    /**
     * Draws the shapes constructed in the contructor by iterating through the arraylist
     * 
     * @param g2d
     */
    @Override
    public void draw(Graphics2D g2d) {
        for (RoundedLine i : ground) {
            i.draw(g2d);
        }
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
