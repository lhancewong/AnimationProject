/**
	One way to think of this shape is its an orbit path. The sun and moon
    lie directly opposite to each other. I made it like this so that it
    would be simplier to code. But then I realizied towards the end of my 
    program that it just complicated the scene switch from day to night or vice versa.
    Most of the calculations for sunmoon are in this class to achieve eNCApsuLatiOn.

	
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

public class SunMoon implements DrawingObject {
    private static double x, y, size, radius, angle;
    private double originalX, originalY;
    private Color moonDarkSideColor, moonCresentColor, sunColor;

    private Circle moonCresent, moonDarkSide;
    private Circle sun;

    /**
     * Initializes starting variables
     * 
     * @param x pivot point x coordinate
     * @param y pivot point y coordinate
     * @param size size of SunMoon
     * @param moonDarkSideColor color of dark side moon
     * @param moonCresentColor color of moon cresent
     * @param sunColor color of sun
     */
    public SunMoon(double x, double y, double size, Color moonDarkSideColor, Color moonCresentColor, Color sunColor) {
        SunMoon.x = x;
        SunMoon.y = y;
        SunMoon.size = size;
        this.moonDarkSideColor = moonDarkSideColor;
        this.moonCresentColor = moonCresentColor;
        this.sunColor = sunColor;

        originalX = x;
        originalY = y;
        
        //initial values
        radius = 37.0*size;
        angle = 20.0;
        originalX = x;
        originalY = y;
    }

    
    /** 
     * Draws the sun and moon from a center point (not directly at the center point).
     * 
     * @param g2d
     */
    @Override
    public void draw(Graphics2D g2d) {
        AffineTransform reset = g2d.getTransform();
        moonDarkSide = new Circle(x+size,y-(radius+size),4.0*size,moonDarkSideColor);
        moonCresent = new Circle(x,y-radius,4.0*size,moonCresentColor);
        sun = new Circle(x,y+radius,5.0*size,sunColor);

        g2d.rotate(Math.toRadians(angle), x, y);

        moonCresent.draw(g2d);
        moonDarkSide.draw(g2d);
        sun.draw(g2d);

        g2d.setTransform(reset);
    }

    
    /** 
     * Adds a value to angle which is shared between both sun and moon.
     * it is divided by two because it is called in the animation loop.
     * 
     * @param speed mulitplied to angle to increase the speed 
     */
    @Override
    public void update(double speed) {
        angle += (0.2*speed)/2.0;
    }

    
    /** 
     * Returns x.
     * 
     * @return double
     */
    public double getX() {
        return x;
    }

    
    /** 
     * Returns y.
     * 
     * @return double
     */
    public double getY() {
        return y;
    }

    
    /** 
     * Returns radius.
     * 
     * @return double
     */
    public double getRadius() {
        return radius;
    }

    
    /** 
     * Returns angle.
     * 
     * @return double
     */
    public double getAngle() {
        return angle;
    }

    
    /** 
     * Sets x.
     * 
     * @param x
     */
    public void setX(double x) {
        SunMoon.x = x;
    }

    
    /** 
     * Sets y.
     * 
     * @param y
     */
    public void setY(double y) {
        SunMoon.y = y;
    }

    
    /** 
     * Sets angle.
     * 
     * @param angle
     */
    public void setAngle(double angle) {
        SunMoon.angle = angle;
    }

    /**
     * Resets the values of sun and moon.
     */
    @Override
    public void reset() {
        x = originalX;
        y = originalY;
        angle = 20;
    }
    
    
    /**
     *  Uses some big brain SOH,CAH,TOA trigonometry to determine the midpoint of sun and moon given 
     *  a radius and angle of the revolution. Returns the values in a double array.
     * 
     * @return double[]
     */
    public double[] getCenter() {
        double[] centers = new double[8];

        double moonAngle = Math.toRadians(angle);
        //x of moon
        centers[0] = x+(radius*(Math.sin(moonAngle)));
        //y of moon
        centers[1] = y-(radius*(Math.cos(moonAngle)));

        double sunAngle = Math.toRadians(angle+180.0);
        //x of sun
        centers[2] = x+(radius*(Math.sin(sunAngle)));
        //y of sun
        centers[3] = y-(radius*(Math.cos(sunAngle)));

        centers[4]= radius*(Math.sin(moonAngle));
        centers[5]= radius*(Math.cos(moonAngle));
        centers[6]= radius*(Math.sin(sunAngle));
        centers[7]= radius*(Math.cos(sunAngle));

        return centers;
    }

    
    /** 
     * Checks if mouseX and mouseY is within a 50x50 pixel box of the center of the sun (int 2) or moon (int 1).
     * 
     * @param mouseX the x coordinate
     * @param mouseY the y coordinate
     * @param i the value that tells the method which center it's checking 0 for moon, 1 for sun.
     * @return boolean
     */
    public boolean isSelected(double mouseX, double mouseY, int i) {
        if( (mouseX >= getCenter()[i  ]-20.0) && (mouseX <= getCenter()[i  ]+20.0) && 
            (mouseY >= getCenter()[i+1]-20.0) && (mouseY <= getCenter()[i+1]+20.0) ) 
            return true;
        else
            return false;
    }

    
    /** 
     * Checks if the moon is setting.
     * 
     * 
     * @return boolean
     */
    public boolean isMoonSetting() {
        if (getCenter()[0] >= 1000 && getCenter()[1] <= 700) {
            return true;
        } else {
            return false;
        }
    }

    
    /** 
     * Checks if the sun is setting.
     * 
     * @return boolean
     */
    public boolean isSunSetting() {
        if (getCenter()[2] >= 1000 && getCenter()[3] <= 700) {
            return true;
        } else {
            return false;
        }
    }

}
