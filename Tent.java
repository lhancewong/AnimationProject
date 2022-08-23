/**
	This is made up of many triangles and a path2D.Double shape. I put them all in the constructor
    since their values won't change anyway.
	
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

public class Tent implements DrawingObject {
    private Color tentFlapColor;

    private Triangle tent;
    private Triangle tentDoor;
    private Triangle leftPeg, rightPeg;
    private Triangle leftBaseCutout, rightBaseCutout;
    private Path2D.Double leftDoorFlap, rightDoorFlap;

    /**
     * Contructs all the shapes in the constructor and intializes tentFlapColor.
     * 
     * @param x middle base of x coodinate
     * @param y bottom base of y coordinate
     * @param size size of tent
     * @param tentColor color of tent
     * @param tentDoorColor color of tent door
     * @param tentPegColor color of tent peg
     * @param tentCutoutColor color of tent cutout color
     * @param tentFlapColor color of flap color
     */
    public Tent(double x, double y, double size, Color tentColor, Color tentDoorColor, Color tentPegColor, Color tentCutoutColor, Color tentFlapColor) {
        this.tentFlapColor = tentFlapColor;

        //tent
        tent = new Triangle(x,y-7.0*size,x+8.0*size,y,x-8*size,y, tentColor);

        //door
        tentDoor = new Triangle(x,y-4.0*size,x+2.0*size,y,x-2.0*size,y, tentDoorColor);

        //peg things
        leftPeg = new Triangle(x-8.0*size,y,x-5.0*size,y,x-6.5*size,y-1.32*size,tentPegColor);
        rightPeg = new Triangle(x+8.0*size,y,x+5.0*size,y,x+6.5*size,y-1.32*size,tentPegColor);

        //side triangle thingies
        leftBaseCutout = new Triangle(x-7.0*size,y,x-5.0*size,y,x-6.0*size,y-0.875*size, tentCutoutColor);
        rightBaseCutout = new Triangle(x+7.0*size,y,x+5.0*size,y,x+6.0*size,y-0.875*size, tentCutoutColor);

        //door flaps
        leftDoorFlap = new Path2D.Double();
        leftDoorFlap.moveTo(x,y-4.0*size);
        leftDoorFlap.curveTo(x-0.61*size, y-2.71*size, 
                             x-2.08*size, y-1.19*size, 
                             x-3.02*size, y-1.28*size);
        leftDoorFlap.lineTo(x-2.0*size,y);
        rightDoorFlap = new Path2D.Double();
        rightDoorFlap.moveTo(x,y-4.0*size);
        rightDoorFlap.curveTo(x+0.61*size, y-2.71*size, 
                              x+2.08*size, y-1.19*size, 
                              x+3.02*size, y-1.28*size);
        rightDoorFlap.lineTo(x+2.0*size,y);  
    }

    
    /** 
     * Draws all the shapes instantiated in the constructor.
     * 
     * @param g2d
     */
    @Override
    public void draw(Graphics2D g2d) {
        tent.draw(g2d);
        tentDoor.draw(g2d);
        leftPeg.draw(g2d);
        rightPeg.draw(g2d);
        leftBaseCutout.draw(g2d);
        rightBaseCutout.draw(g2d);
        g2d.setColor(tentFlapColor);
        g2d.fill(leftDoorFlap);
        g2d.fill(rightDoorFlap);
    }

    
    /** 
     * Does nothing.
     * 
     * @param speed does nothing this quickly = s p e e d
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
