/**
    This is a cloud that is made of 1 rounded line and 3 ellipses.
    Calling update makes the cloud move right once at a random distance.
    Looping this method makes it move at a constant speed until it reaches
    x = 1000. It will then get a new speed.
	
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

public class Cloud implements DrawingObject {
    private double x, y, size, cloudSpeed;
    private double originalX, originalY;

    private RoundedLine cloudTail;
    private Ellipse2D.Double leftEllipse, middleEllipse, rightEllipse;
    private Color color;


    /**
     * Contructs variables to be used in draw().
     * 
     * @param x center-ish x coordinate of cloud
     * @param y bottom y coordinate of cloud
     * @param size size of cloud
     * @param color color of cloud
     */
    public Cloud(double x, double y, double size, Color color) { //made of a hotdog and 3 ellipses
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
        cloudSpeed = (Math.random()*3) + 0.5;

        originalX = x;
        originalY = y;
    }

    /**
     * Draws a cloud shapes using 3 ellipses and a rounded line
     * 
     * @param g2d
     */
    @Override
    public void draw(Graphics2D g2d) {
        //cloud's tail
        cloudTail = new RoundedLine(x-4.0*size,y-0.26*size,
                                    x+2.9*size,y-0.26*size,
                                    0.6*size,color);
          //left ellipse of cloud
          leftEllipse = new Ellipse2D.Double(x-2.45*size, y-1.4*size, 
                                               2.00*size, 1.4*size);
        //middle ellipse of cloud
        middleEllipse = new Ellipse2D.Double(x-size, y-2.0*size, 
                                          2.45*size, 2.0*size);
         //right ellipse of cloud
         rightEllipse = new Ellipse2D.Double(x+0.85*size, y-1.35*size, 
                                                2.0*size, 1.35*size);

        g2d.setColor(color);
        cloudTail.draw(g2d);
        g2d.fill(leftEllipse);
        g2d.fill(middleEllipse);
        g2d.fill(rightEllipse);
    }

    /**
     * Updates the x coordinate of cloud at a random speed every run.
     * It makes a random speed which stays the same until it reaches the end of the screen,
     * it then goes back to its original x value and gets a new speed.
     */
    @Override
    public void update(double speed) {
        if(x >= 1100) {
            x = -100;
            cloudSpeed = ((Math.random()*3) + 0.5);
        } else {
            x += cloudSpeed*speed;
        }
        
    }

    /**
     * Resets the values of cloud.
     */
    @Override
    public void reset() {
        x = originalX;
        y = originalY;
        
    }
}
