/**
    This is a bush shape that is made of many ellipses. To make it easier to draw 
    all these shapes, I used an arraylist of ellipse2D.Double shapes to quickly call
    draw on all of them. I used a single circle so that this shape will count as a 
    composite shape. 
	
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
import java.util.*;

public class Bush implements DrawingObject {
    private double x, y, size;
    private double originalX, originalY, originalSize;
    private Color color;
    private boolean isIncreasing;

    private RoundedLine bushBox;

    /**
     * Contructs the bottom hotdog shape of the bush and instializes some variables.
     * 
     * @param x center-ish x coordinate
     * @param y bottom y coordinate
     * @param size size of bush
     * @param color color of bush
     */
    public Bush(double x, double y, double size, Color color) {
        bushBox = new RoundedLine(x-19.0*size, y-size, x+18.0*size, y-size, 2.3*size, color);
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
        isIncreasing = true;

        originalX = x;
        originalY = y;
        originalSize = size;
    }

    /**
     * Draws A LOT of ellipse shapes of varying sizes and adds them to an arraylist.
     * Also draws a circle shape to make this a composite shape. It then loops through the 
     * arraylist to draw all the ellipses.
     * 
     * @param g2d
     */
    @Override
    public void draw(Graphics2D g2d) {
        ArrayList<Ellipse2D.Double> individualBushes = new ArrayList<Ellipse2D.Double>();
        individualBushes.add(new Ellipse2D.Double(x-19.1*size, y-3.0*size, 1.9*size, 1.6*size));//1
        individualBushes.add(new Ellipse2D.Double(x-17.8*size, y-3.2*size, 1.6*size, 1.9*size));//2
        individualBushes.add(new Ellipse2D.Double(x-16.3*size, y-2.7*size, 1.7*size, 1.5*size));//3
        individualBushes.add(new Ellipse2D.Double(x-15.0*size, y-3.1*size, 1.5*size, 1.4*size));//4
        individualBushes.add(new Ellipse2D.Double(x-13.7*size, y-2.6*size, 1.9*size, 1.4*size));//5
        individualBushes.add(new Ellipse2D.Double(x-12.5*size, y-3.1*size, 1.9*size, 1.4*size));//6
                 Circle lonelyCircle = new Circle(x-9.80*size, y-2.5*size, 2.2*size, color);    //7
        individualBushes.add(new Ellipse2D.Double(x-8.85*size, y-3.1*size, 2.2*size, 1.9*size));//8
        individualBushes.add(new Ellipse2D.Double(x-7.20*size, y-3.5*size, 2.1*size, 1.8*size));//9
        individualBushes.add(new Ellipse2D.Double(x-5.50*size, y-2.8*size, 1.5*size, 1.3*size));//10
        individualBushes.add(new Ellipse2D.Double(x-4.50*size, y-3.4*size, 1.8*size, 2.0*size));//11
        individualBushes.add(new Ellipse2D.Double(x-3.00*size, y-3.2*size, 1.8*size, 1.7*size));//12
        individualBushes.add(new Ellipse2D.Double(x-2.00*size, y-3.0*size, 2.5*size, 1.6*size));//13
        individualBushes.add(new Ellipse2D.Double(x+0.05*size, y-3.6*size, 2.2*size, 2.3*size));//14
        individualBushes.add(new Ellipse2D.Double(x+1.70*size, y-3.4*size, 1.8*size, 2.0*size));//15
        individualBushes.add(new Ellipse2D.Double(x+2.80*size, y-3.5*size, 1.9*size, 2.0*size));//16
        individualBushes.add(new Ellipse2D.Double(x+4.00*size, y-3.0*size, 1.5*size, 1.5*size));//17
        individualBushes.add(new Ellipse2D.Double(x+5.20*size, y-2.8*size, 2.0*size, 1.5*size));//18
        individualBushes.add(new Ellipse2D.Double(x+7.00*size, y-3.4*size, 2.5*size, 1.8*size));//19
        individualBushes.add(new Ellipse2D.Double(x+8.70*size, y-3.7*size, 2.2*size, 2.0*size));//20
        individualBushes.add(new Ellipse2D.Double(x+10.5*size, y-3.0*size, 2.1*size, 2.3*size));//21
        individualBushes.add(new Ellipse2D.Double(x+12.5*size, y-2.5*size, 1.7*size, 1.5*size));//22
        individualBushes.add(new Ellipse2D.Double(x+13.8*size, y-3.2*size, 1.9*size, 2.2*size));//23
        individualBushes.add(new Ellipse2D.Double(x+15.3*size, y-3.3*size, 2.1*size, 1.5*size));//24
        individualBushes.add(new Ellipse2D.Double(x+16.8*size, y-2.6*size, 1.4*size, 1.5*size));//25
        bushBox.draw(g2d);
        for(Ellipse2D.Double i : individualBushes) {
            g2d.fill(i);
        }
        lonelyCircle.draw(g2d);

    }

    /**
     * Updates the value of size which makes the bush increase in decrease in size
     * 
     * @param speed how much faster sizechange changes in an update
     */
    @Override
    public void update(double speed) {
        double sizeChange = 0.005*speed;
        if (isIncreasing) {
            size += sizeChange;
            if (size >= 16.15) {
                isIncreasing = false;
            }
        } 
        if (!isIncreasing) {
            size -= sizeChange;
            if (size <= 15.85) {
                isIncreasing = true;
            }
        }
            
        
    }

    @Override
    public void reset() {
        x = originalX;
        y = originalY;
        size = originalSize;
        
    }
}
