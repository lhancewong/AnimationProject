/**
	A grid that helps me map out things. It makes a grid that is made of 
    16x16 squares that fill the screen.
	
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

public class Grid implements DrawingObject{
    private double x_crement, y_crement;
    private double width, height;
    private boolean visible;

    /**
     * I had a lot more features for this grid when i first made it but then I realized that I didn't need them.
     * And now that I removed them, I'm too lazy to move all the shapes into the constructor.
     * This also instantiates the variables of grid.
     * 
     * @param width width of the entire grid
     * @param height height of the entire grid
     * @param x_crement x spacing of the grid
     * @param y_crement y spacing of the grid
     */
    public Grid(double width, double height, double x_crement, double y_crement) {
        this.x_crement = x_crement;
        this.y_crement = y_crement;
        this.width = width;
        this.height = height;
        visible = false;



    }

    /**
     * Draws a grid based on variables in the constructor.
     * 
     * @param g2d
     */
    public void draw(Graphics2D g2d) {
        if (visible) {
            Rectangle2D.Double gridBox = new Rectangle.Double(0,0,width-1.0,height-1.0);
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(1));
            g2d.draw(gridBox);

            //loop drawing horizontal lines for square grid
            for(int i = 1 ; i <= (height/y_crement) ; i++) {
                double h_startEnd = (double)i * 16.0;
                Line2D.Double horizontal = new Line2D.Double(0, h_startEnd, width-1.0, h_startEnd);
                g2d.draw(horizontal);
            }

            //loop drawing vertical lines for square grid
            for (int i = 1 ; i <= (width/x_crement) ; i++) {
                double v_startEnd = (double)i * 16.0;
                Line2D.Double vertical = new Line2D.Double(v_startEnd, 0, v_startEnd, height-1.0);
                g2d.draw(vertical);
            }  
            //System.out.printf("Increments [ x: %.2f, y:%.2f ]", x_crement, y_crement);
        }
    }

    /**
     * Flips a boolean which determines if the grid should be drawn in or not.
     */
    public void setVisibility() {
        visible = !visible;
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
