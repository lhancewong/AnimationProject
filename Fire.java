/**
	Made of 2 roundedlines that are in the constructor, and two Path2D.Double
    flames in the draw method. Update makes it "pulse" in size to give it a 
    firey(?) effect.
	
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

public class Fire implements DrawingObject {
    private double x, y, size;
    private double originalX, originalY, originalSize;
    private boolean isIncreasing, isDead;
    private Color innerFlameColor, outerFlameColor;

    private RoundedLine topFireLog, bottomFireLog;

    /**
     * Draws a campfire looking thing.
     * 
     * @param x center x coordinate of fire
     * @param y bottom y coordinate of fire
     * @param size size of fire
     * @param fireWoodColor color of fire wood
     * @param innerFlameColor color of the inner flame
     * @param outerFlameColor color of the outer flame
     * @param isDead tells if the fire is supposed to be dead
     */
    public Fire(double x, double y, double size, Color fireWoodColor, Color innerFlameColor, Color outerFlameColor, boolean isDead) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.innerFlameColor = innerFlameColor;
        this.outerFlameColor = outerFlameColor;
        this.isDead = isDead;
       
        isIncreasing = true;
        originalX = x;
        originalY = y;
        originalSize = size;

        topFireLog = new RoundedLine(x-1.1*size, y-0.75*size, x+1.1*size, y-0.73*size, 0.5*size, fireWoodColor);
        bottomFireLog = new RoundedLine(x-1.55*size, y-0.25*size, x+1.55*size, y-0.27*size, 0.6*size, fireWoodColor);
    }

    /**
     * Draws the logs contructed in the constructor. It then makes the flame part of the fire
     * and also draws that in.
     * 
     * @param g2d
     */
    @Override
    public void draw(Graphics2D g2d) {
        topFireLog.draw(g2d); 
        bottomFireLog.draw(g2d);
        if(!isDead) {
             //outer flame
             Path2D.Double outerFlame = new Path2D.Double();
             outerFlame.moveTo(x          ,y-0.50*size); //anchor point
            outerFlame.curveTo(x+2.28*size,y-0.69*size,  
                               x+0.76*size,y-2.51*size,  
                               x          ,y-3.50*size); //anchor point
             outerFlame.moveTo(x          ,y-0.50*size); //anchor point
            outerFlame.curveTo(x-2.28*size,y-0.69*size,  
                               x-0.76*size,y-2.51*size,  
                               x          ,y-3.50*size); //anchor point
    
            //inner flame
            Path2D.Double innerFlame = new Path2D.Double();
             innerFlame.moveTo(x          ,y-0.49*size); //anchor point
            innerFlame.curveTo(x+1.14*size,y-0.69*size,  
                               x+0.78*size,y-1.26*size,  
                               x          ,y-2.00*size); //anchor point
             innerFlame.moveTo(x          ,y-0.49*size); //anchor point
            innerFlame.curveTo(x-1.14*size,y-0.69*size,  
                               x-0.78*size,y-1.26*size,  
                               x          ,y-2.00*size); //anchor point
            
            g2d.setColor(outerFlameColor);
            g2d.fill(outerFlame);
            g2d.setColor(innerFlameColor);
            g2d.fill(innerFlame);
        }
    }

    /**
     * Updates the size variable to make the fire increase and decrease in size at a constant pace
     * 
     * @param speed how fast the fire changes in size
     */
    @Override
    public void update(double speed) {
        double sizeChange = 0.1*speed;
        if (isIncreasing) {
            size += sizeChange;
            if (size >= 17) {
                isIncreasing = false;
            }
        } 
        if (!isIncreasing) {
            size -= sizeChange;
            if (size <= 15) {
                isIncreasing = true;
            }
        }
        
    }

    /**
     * Resets the values of fire.
     */
    @Override
    public void reset() {
        x = originalX;
        y = originalY;
        size = originalSize;
    }
}
