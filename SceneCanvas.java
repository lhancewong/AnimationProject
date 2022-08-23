/**
	I dumped most methods into this class. It handles drawing and constructing the scene.
    It contains a lot of methods that will be used in sceneframe to do calculations.
	
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
import javax.swing.*;
import java.util.*;

public class SceneCanvas extends JComponent {
    private Graphics2D g2d;
    private RenderingHints AA;
    
    private ArrayList<DrawingObject> animation;

    private SunMoon sunMoon;
    private Grid grid;

    private double animationSpeed;
    private double dayCompositeValue, nightCompositeValue;
    private boolean antiAliasingOn;

    /**
     * Constructs all the shapes needed in the animation.
     * Initializes variables needed in the program.
     */
    public SceneCanvas() {
        setPreferredSize(new Dimension(1024,768));
        AA =  new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

        animation = new ArrayList<DrawingObject>();
        for(int i = 0; i <= 1; i++) {
            boolean isDay = false;
            if (i == 1) {
                isDay = true;
            }
            initLayer1(isDay);
            initLayer2(isDay);
            initLayer3(isDay);
            initLayer4(isDay);
            initLayer5(isDay);
        }
        //initial values
        nightCompositeValue = 1;
        dayCompositeValue = 0;
        animationSpeed = 1;
        antiAliasingOn = false;
        
        grid = new Grid(1024,768,16,16);
    }
    
    /** 
     * Uses a for loop to iterate through all DrawingObjects.
     * It can also change the opacity of the night scene or day scene using nightCompositeValue and dayCompositeValue.
     * 
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        g2d = (Graphics2D) g;

        if (antiAliasingOn){
            g2d.setRenderingHints(AA);
        }
        
        //animation
        int count = 1;
        for(DrawingObject i : animation) {
            switch(count) {
                case 1: //start of night scene
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float)nightCompositeValue));
                    break;
                case 32: //start of day scene
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float)dayCompositeValue));
                    break;
                    
            }
            i.draw(g2d);
            count++;
        }
    
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float)1));
        grid.draw(g2d);
    }

    
    /** 
     * Calls DrawingObject.update(animationSpeed) on every shape in the canvas.
     * 
     * @param isRunning tells the method if the animation should be updated
     */
    public void updateAnimation(boolean isRunning) {
        if (isRunning) {
            //calls update on all items in the list animation
            for (DrawingObject i : animation) {
                i.update(animationSpeed);
            }
            //depending on the position of the sun or the moon, it might start 
            //transitioning the scene from night to day or vice versa.
            if (sunMoon.isMoonSetting()) {
                //turns it to day
                incrementDayComposite(0.006*animationSpeed);
            }

            if (sunMoon.isSunSetting()) {
                //turns it to night
                incrementNightComposite(0.006*animationSpeed);
            }
        }
    }

    /**
     * initLayers 1-5 will have a naming scheme for the shapes involving numbers 1,2,3,4... 
     * This means (left to right; top to down).
     * For example, star1 would be the leftmost and topmost star  
     * ======================================================================
     * 
     * Contains and initializes background, stars, back cloud, sun, and moon
     * 
     * @param isDay tells the method if it's day
     */
    public void initLayer1(boolean isDay) { 
        //color loop
        Color backgroundColor = new Color(46,127,160);
        Color starColor = new Color(166,237,229);
        Color backCloudColor = new Color(189,223,214);
        Color moonShadowColor = new Color(46,127,160);
        Color moonColor = new Color(166,237,229);
        Color sunColor = new Color(255,212,0);
        if(isDay) {
            backgroundColor = new Color(163,212,230);
            starColor = new Color(163,212,230);
            backCloudColor = new Color(255,255,255);
        }
        //background
        Background background = new Background(1026,768,backgroundColor); //1
        //stars
        Star star1 = new Star(150,170,16,starColor); //2
        Star star2 = new Star(240,273,16,starColor); //3
        Star star3 = new Star(330,119,16,starColor); //4
        Star star4 = new Star(477, 84,16,starColor); //5
        Star star5 = new Star(572,138,16,starColor); //6
        Star star6 = new Star(626,264,16,starColor); //7
        Star star7 = new Star(700, 80,16,starColor); //8
        Star star8 = new Star(783,240,16,starColor); //9
        Star star9 = new Star(830,100,16,starColor); //10
        //sun moon
        sunMoon = new SunMoon(528,700,16,moonShadowColor,moonColor,sunColor);//11
        //cloud
        Cloud backCloud = new Cloud(436, 197, 12, backCloudColor); //12
        //adding to arraylist
        animation.add(background);
        animation.add(star1);
        animation.add(star2);
        animation.add(star3);
        animation.add(star4);
        animation.add(star5);
        animation.add(star6);
        animation.add(star7);
        animation.add(star8);
        animation.add(star9);
        animation.add(sunMoon);
        animation.add(backCloud);
    }

    
    /** 
     * Contains and initializes 5 mountains.
     * 
     * @param isDay tells the method if it's day
     */
    public void initLayer2(boolean isDay) {
        //color loop
        Color backMountainShadow = new Color(24,92,120);
        Color backMountainColor = new Color(40,111,140);
        Color frontMountainShadow = new Color(29,80,101);
        Color frontMountainColor = new Color(35,96,120);
        if(isDay) {
            backMountainShadow = new Color(143,143,143);
            backMountainColor = new Color(178,178,178);
            frontMountainShadow = new Color(111,111,111);
            frontMountainColor = new Color(133,133,133);
        }
        //3 back mountains
        Mountain backMountain1 = new Mountain(345,488,200,270,backMountainShadow,backMountainColor); //13
        Mountain backMountain2 = new Mountain(497,482,280,336,backMountainShadow,backMountainColor); //14
        Mountain backMountain3 = new Mountain(688,482,260,209,backMountainShadow,backMountainColor); //15
        //2 front mountains
        Mountain frontMountain1 = new Mountain(419,472,260,302,frontMountainShadow,frontMountainColor); //16
        Mountain frontMountain2 = new Mountain(606,475,260,200,frontMountainShadow,frontMountainColor); //17
        //adding to arraylist
        animation.add(backMountain1);
        animation.add(backMountain2);
        animation.add(backMountain3);
        animation.add(frontMountain1);
        animation.add(frontMountain2);
    }

    
    /** 
     * Contains and initializes 3 trees behind the bushes.
     * 
     * @param isDay tells the method if it's day
     */
    public void initLayer3(boolean isDay) {
        //color loop
        Color backBushTreeShadow = new Color(14,68,65);
        Color backBushTreeColor = new Color(26,87,74);
        if(isDay) {
            backBushTreeShadow = new Color(62,100,48);
            backBushTreeColor = new Color(71,126,50);
        } 
        //4 back bush trees
        Mountain backBushTree1 = new Mountain(640,450,38,50,backBushTreeShadow,backBushTreeColor); //18
        Mountain backBushTree2 = new Mountain(671,437,40,46,backBushTreeShadow,backBushTreeColor); //19
        Mountain backBushTree3 = new Mountain(696,438,36,62,backBushTreeShadow,backBushTreeColor); //20
        Mountain backBushTree4 = new Mountain(744,450,44,80,backBushTreeShadow,backBushTreeColor); //21
        animation.add(backBushTree1);
        animation.add(backBushTree2);
        animation.add(backBushTree3);
        animation.add(backBushTree4);
    }

    
    /** 
     * Contains and initializes a bush, 3 trees in front of that bush, and the ground.
     * 
     * @param isDay tells the method if it's day
     */
    public void initLayer4(boolean isDay) { 
        //color loop
        Color frontTreeShadow = new Color(14,68,65);
        Color frontTreeColor = new Color(26,87,74);
        Color frontTreeTrunkColor = new Color(34,29,21);
        Color groundColor = new Color(29,80,101);
        Color bushColor = new Color(13,69,92);
        if(isDay) {
            frontTreeShadow = new Color(62,100,48);
            frontTreeColor = new Color(71,126,50);
            frontTreeTrunkColor = new Color(89,60,34);
            groundColor = new Color(41,151,69);
            bushColor = new Color(28,111,63);
        }
        //bush
        Bush bush = new Bush(528,475,16, bushColor); //22
        //3 front bush trees
        Tree frontBushTree1 = new Tree(346,464,14.2,frontTreeShadow,frontTreeColor,frontTreeTrunkColor); //23
        Tree frontBushTree2 = new Tree(413,464,16,frontTreeShadow,frontTreeColor,frontTreeTrunkColor); //24
        Tree frontBushtree3 = new Tree(490,453,12.4,frontTreeShadow,frontTreeColor,frontTreeTrunkColor); //25
        //ground
        Ground ground = new Ground(512, 608, 16, groundColor); //26
        //adding duh
        animation.add(bush);
        animation.add(frontBushTree1);
        animation.add(frontBushTree2);
        animation.add(frontBushtree3);
        animation.add(ground);
    }

    
    /** 
     * Contains and initializes front clouds, tent, and fire
     * 
     * @param isDay tells the method if it's day
     */
    public void initLayer5(boolean isDay) {
        //color loop for time cycle
        Color frontCloudColor = new Color(189,223,214);
        Color tentColor = new Color(198,196,67);
        Color tentDoorColor = new Color(16,44,53);
        Color tentPegColor = Color.GRAY;
        Color tentCutoutColor = new Color(29,80,101);
        Color tentFlapColor = new Color(144,158,71);
        Color fireWoodColor = new Color(56,36,10);
        Color innerFlameColor = new Color(230,140,37);
        Color outerFlameColor = new Color(255,235,68);
        //tells the fire to either burn or not burn (die)
        boolean isDead = false;
        if(isDay) {
            frontCloudColor = new Color(255,255,255);
            tentColor = new Color(240,191,65);
            tentDoorColor = new Color(68,43,34);
            tentPegColor = Color.GRAY;
            tentCutoutColor = new Color(41,151,69);
            tentFlapColor = new Color(215,133,55);
            fireWoodColor = new Color(113,83,44);
            isDead = true;
        }
        //3 front clouds
        Cloud frontCloud1 = new Cloud(230,230,16,frontCloudColor); //27
        Cloud frontCloud2 = new Cloud(555,260,8.4,frontCloudColor); //28
        Cloud frontCloud3 = new Cloud(760,280,10,frontCloudColor); //29
        //tent
        Tent tent = new Tent(413,509,16,tentColor,tentDoorColor,tentPegColor,tentCutoutColor,tentFlapColor); //30
        //fire
        Fire fire = new Fire(640,544,16,fireWoodColor,innerFlameColor,outerFlameColor,isDead); //31
        //adding stuff
        animation.add(frontCloud1);
        animation.add(frontCloud2);
        animation.add(frontCloud3);
        animation.add(tent);
        animation.add(fire);
    }
    


    //========== Calculations shiz for Scene Frame ==========//

    /** 
     * Increments nightCompositeValue and sunCompositeValue from 0 to 1 and 1 to 0 respectively. 
     * It also has extra measures to make sure it only outputs a number in between [0,1].
     * 
     * @param incrementSpeed will be added/subtracted to the composite values
     */
    public void incrementNightComposite(double incrementSpeed) {
        if (dayCompositeValue - incrementSpeed < 0) {
            nightCompositeValue = 1;
            dayCompositeValue = 0;
        }
        if (nightCompositeValue != 1) {
            nightCompositeValue += incrementSpeed;
            dayCompositeValue -= incrementSpeed;
        }
    }
    
    /** 
     * Increments dayCompositeValue and nightCompositeValue from 0 to 1 and 1 to 0 respectively. 
     * It also has extra measures to make sure it only outputs a number in between [0,1].
     * 
     * @param incrementSpeed will be added/subtracted to the composite values
     */
    public void incrementDayComposite(double incrementSpeed) {
        if(nightCompositeValue - incrementSpeed < 0){
            dayCompositeValue = 1;
            nightCompositeValue = 0;
        }
        if (dayCompositeValue != 1) {
            dayCompositeValue += incrementSpeed;
            nightCompositeValue -= incrementSpeed;
        }
    }

    /** 
     * Uses the method SunMoon.isSelected to check if the x and y coords are within
     * the Sun or Moon's centers. It returns true or false based on that.
     * 
     * @param mouseX the x coordinate
     * @param mouseY the y coordinate
     * @param i tells the method which center to check for-- 0 for moon and 1 for sun
     * @return boolean 
     */
    public boolean isSunMoonSelected(double mouseX, double mouseY, int i) {
        return sunMoon.isSelected(mouseX, mouseY, i);
    }
    
    /** 
     * The parameter receieves x and y values from SceneFrame's mouse listener.
     * since these values are not the actual center of SunMoon but the center of the sun or moon,
     * it does extra calculations for it to set the center correctly
     *
     * @param mouseX is the x coordinate.
     * @param mouseY is the y coordinate.
     * @param i tells the method which center to check for-- 0 for moon and 1 for sun. 
     */
    public void setSunMoonCenter(double mouseX, double mouseY, int i) {
        sunMoon.setX(mouseX - sunMoon.getCenter()[i+4]);
        sunMoon.setY(mouseY + sunMoon.getCenter()[i+5]);
    }


    
    //============= Button shenanigans =============//

    /** 
     * Sets the animation speed of the scene.
     * 
     * @param speed is the speed of the animation.
     */
    public void setAnimationSpeed(double speed) {
        animationSpeed = speed;
    }

    
    /** 
     * Sets the scene to either night or day based on the boolean day.
     * True for day, false for night.
     * 
     * @param isDay tells the method if it's day
     */
    public void setTimeDay(boolean isDay) {
        if (isDay) {
            nightCompositeValue = 0;
            dayCompositeValue = 1;
            sunMoon.setAngle(480.0);
        } else {
            nightCompositeValue = 1;
            dayCompositeValue = 0;
            sunMoon.setAngle(300.0);
        }
    }
    
    /**
     * Flips the boolean antiAliasing that is used in paint component to 
     * determine if Anti-Aliasing should be turned on.
     */
    public void toggleAntiAliasing() {
        antiAliasingOn = !antiAliasingOn;
    }

    /**
     * Resets the values of all DrawingObjects by calling DrawingObject.reset().
     * Some DrawingObjects do not have anything implemented in their .reset() method.
     */
    public void resetScene() {
        nightCompositeValue = 1;
        dayCompositeValue = 0;
        for(DrawingObject i : animation) {
            i.reset();
        }
    }

    /**
     * Calls grid.setVisibility which effectively flips a boolean 
     * that decides whether to show or not show the grid.
     */
    public void showGrid() {
        //flips a boolean in grid
        grid.setVisibility();
    }
}
