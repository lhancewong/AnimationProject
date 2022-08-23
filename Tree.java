/**
	This is a tree shape made of 3 mountain shapes. I used a mountain shape
    since it already has a shadow color and lit color. It makes making a tree
    easier. This is also a composite shape.
	
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

public class Tree implements DrawingObject {
    private double x, y, angle;
    private double originalX, originalY, originalAngle;
    private boolean isIncreasing;

    private Mountain topLeaves, middleLeaves, bottomLeaves;
    private Line trunk;

    /**
     * Constructs three mountain shapes as the leaves and a thick line to serve as the trunk.
     * Also initializes some variables.
     * 
     * @param x center coordinate of the trunk
     * @param y bottom coordinate of the trunk
     * @param size size of the tree
     * @param shadowColor color of the shadow
     * @param treeColor color of the tree
     * @param trunkColor color of the trunk
     */
    public Tree(double x, double y, double size, Color shadowColor, Color treeColor, Color trunkColor) {
        this.x = x;
        this.y = y;
        this.angle = 0;
        this.isIncreasing = true;

        originalX = x;
        originalY = y;
        originalAngle = angle;

           topLeaves = new Mountain(x-0.5*size, y-7.0*size, 3.4*size, 3.0*size, shadowColor, treeColor);
        middleLeaves = new Mountain(x-0.5*size, y-4.0*size, 4.0*size, 4.0*size, shadowColor, treeColor);
        bottomLeaves = new Mountain(x-0.5*size, y-1.2*size, 5.0*size, 4.9*size, shadowColor, treeColor);

                trunk = new Line(x-0.5*size, y, x-0.5*size, y-1.3*size, size, trunkColor);
    }

    
    /** 
     * Draws the tree and rotates with the pivot point at it's base.
     * 
     * @param g2d
     */
    @Override
    public void draw(Graphics2D g2d) {
        AffineTransform reset = g2d.getTransform();

        g2d.rotate(Math.toRadians(angle), x, y);
        trunk.draw(g2d);
        bottomLeaves.draw(g2d);
        middleLeaves.draw(g2d);
        topLeaves.draw(g2d);

        g2d.setTransform(reset);
    }

    
    /** 
     * Makes the tree sway by increasing and decreasing an angle from -1 to 1.
     * 
     * @param speed
     */
    @Override
    public void update(double speed) {
        double angleChange = (0.01*speed);
        if (isIncreasing) {
            angle += angleChange;
            if (angle >= 1) {
                isIncreasing = false;
            }
        } 
        if (!isIncreasing) {
            angle -= angleChange;
            if (angle <= -1) {
                isIncreasing = true;
            }
        }
        
    }
    /**
     * Resets the values of tree.
     */
    @Override
    public void reset() {
        x = originalX;
        y = originalY;
        angle = originalAngle;
        
    }
}
