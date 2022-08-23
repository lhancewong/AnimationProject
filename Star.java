/**
	This is basically a circle shape with extra features. Calling update
    will make it "pulse" in size and occasionally twinkle.
	
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

public class Star implements DrawingObject {
    private double x, y, size;
    private double originalSize;
    private Color color;
    private boolean twinkle, isIncreasing;

    private Circle star;
    

    /**
     * Initializes some variables.
     * 
     * @param x center x coordinate of star
     * @param y center y coordinate of star
     * @param size size of star
     * @param starColor color of star
     */
    public Star(double x, double y, double size, Color starColor) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = starColor;

        originalSize = size;


        twinkle = false;
        isIncreasing = true;
    }

    
    /**
     * Draws the (star) if twinkle is false. 
     * 
     * @param g2d
     */
    @Override
    public void draw(Graphics2D g2d) {
        star = new Circle(x, y ,0.176*size, color);
        if (!twinkle) {
            star.draw(g2d);
            twinkle = false;
        }
    }

    
    /** 
     * Makes the size increase then decrease by 1 every update.
     * 
     * @param speed determines how fast this will change.
     */
    @Override
    public void update(double speed) {
        double sizeChange = 1*speed;
        if (isIncreasing) {
            size += sizeChange;
            if (size >= 30) {
                isIncreasing = false;
            }
        } 
        if (!isIncreasing) {
            size -= sizeChange;
            if (size <= 16) {
                isIncreasing = true;
            }
        }

        double number = Math.random();
        if (number >= 0.05) {
            twinkle = false;
        } else {
            twinkle = true;
        }
    }

    /**
     * Resets the values of star.
     */
    @Override
    public void reset() {
        size = originalSize;
    }
}
