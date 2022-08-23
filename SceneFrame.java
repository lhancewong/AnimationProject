/**
	The scene frame is in charge of arranging the gui components and listeners.
    It has 3 listeners which do what their supposed to. This class is also the one
    in charge of looping the animation updates and displaaying the animation.
    I used different timers for each part because I ran into some problems with 
    java.util.Timers when displaying FPS. So I ended up using java.swing.Timers which
    run on the event dispatch thread (same thread swing components run on).
	
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

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;

public class SceneFrame {
    private Container mainPanel;
    private JFrame window;

    private JPanel buttonLayout;
    private JButton startAnimation, stopAnimation, moreActionsButton;
    private JLabel titleFPS;

    private JPopupMenu actionsMenu;
    private JMenuItem speedMenuButton, FPSMenuButton, experimentalFeaturesMenuButton;
    private JMenuItem toggleAntiAliasing;
    private JMenuItem updateScene;
    private JMenuItem resetScene;
    private JMenuItem toggleGridVisibility;

    private JPopupMenu speedMenu;
    private JMenuItem speedQuarter,speedHalf,speed1x, speed2x, speed3x, speed4x, speed5x;

    private JPopupMenu FPSMenu;
    private JMenuItem FPS30,FPS60,FPS120,FPS144,FPS240;

    private JPopupMenu experimentalFeaturesMenu;
    private JMenuItem timeSetDay, timeSetNight;
    private JMenuItem toggleMovableSunMoon;

    private SceneCanvas sceneCanvas;
    private boolean isRunning, isMovable;

    private double FPS_CAP = 60.0;
    private javax.swing.Timer EDT;

    /**
     * Initializes many things like JPopupMenus and JMenuItems etc.
     */
    public SceneFrame() {
        window = new JFrame();
        sceneCanvas = new SceneCanvas();
        
        buttonLayout = new JPanel();
        moreActionsButton = new JButton("More Actions");
        startAnimation = new JButton("Start Animation");
        stopAnimation = new JButton("Stop Animation");
        titleFPS = new JLabel("Camping   ",JLabel.RIGHT);

        //more actions menu
        actionsMenu = new JPopupMenu();
        speedMenuButton = new JMenuItem("Set Animation Speed:");
        FPSMenuButton = new JMenuItem("Set FPS Cap:");
        experimentalFeaturesMenuButton = new JMenuItem("Experimental Features:");
        toggleAntiAliasing = new JMenuItem("Toggle Anti-Aliasing");
        toggleMovableSunMoon = new JMenuItem("Toggle Movable Sun and Moon");
        updateScene = new JMenuItem("Update Scene Once");
        resetScene = new JMenuItem("Reset Scene");
        toggleGridVisibility = new JMenuItem("Show Grid");

        //animation speed menu
        speedMenu = new JPopupMenu();
        speedQuarter = new JMenuItem("0.25x");
        speedHalf = new JMenuItem("0.5x");
        speed1x = new JMenuItem("1x");
        speed2x = new JMenuItem("2x");
        speed3x = new JMenuItem("3x");
        speed4x = new JMenuItem("4x");
        speed5x = new JMenuItem("5x");

        //fps cap menu
        FPSMenu = new JPopupMenu();
        FPS30 = new JMenuItem("Set to 30FPS~");
        FPS60 = new JMenuItem("Set to 60FPS~");
        FPS120 = new JMenuItem("Set to 120FPS~");
        FPS144 = new JMenuItem("Set to 144FPS~");
        FPS240 = new JMenuItem("Set to 240FPS~");

        //experimental features menu
        experimentalFeaturesMenu = new JPopupMenu();
        timeSetDay = new JMenuItem("Set Time to Day");
        timeSetNight = new JMenuItem("Set Time to Night");

        isRunning = false;
        isMovable = false;
    }

    /**
     * Sets up the GUI, their layouts, and the menus.
     */
    public void setUpGUI() {
        mainPanel = window.getContentPane();
        window.setTitle("Midterm Project - Wong, Lhance Christian - 205467");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        //main buttons
        buttonLayout.setLayout(new GridLayout(1,3,0,0));
        buttonLayout.setBackground(Color.WHITE);
        buttonLayout.add(moreActionsButton);
        buttonLayout.add(startAnimation);
        buttonLayout.add(stopAnimation);
        

        //more actions menu
        actionsMenu.setLayout(new BoxLayout(actionsMenu, BoxLayout.Y_AXIS));
        actionsMenu.add(speedMenuButton);
        actionsMenu.add(FPSMenuButton);
        actionsMenu.add(experimentalFeaturesMenuButton);
        actionsMenu.add(toggleAntiAliasing);
        actionsMenu.add(updateScene);
        actionsMenu.add(resetScene);
        actionsMenu.add(toggleGridVisibility);

        //animation speed menu
        speedMenu.setLayout(new BoxLayout(speedMenu, BoxLayout.Y_AXIS));
        speedMenu.add(speedQuarter);
        speedMenu.add(speedHalf);
        speedMenu.add(speed1x);
        speedMenu.add(speed2x);
        speedMenu.add(speed3x);
        speedMenu.add(speed4x);
        speedMenu.add(speed5x);

        //FPS Menu
        FPSMenu.setLayout(new BoxLayout(FPSMenu, BoxLayout.Y_AXIS));
        FPSMenu.add(FPS30);
        FPSMenu.add(FPS60);
        FPSMenu.add(FPS120);
        FPSMenu.add(FPS144);
        FPSMenu.add(FPS240);

        //experimental features menu
        experimentalFeaturesMenu.setLayout(new BoxLayout(experimentalFeaturesMenu, BoxLayout.Y_AXIS));
        experimentalFeaturesMenu.add(timeSetDay);
        experimentalFeaturesMenu.add(timeSetNight);
        experimentalFeaturesMenu.add(toggleMovableSunMoon);
        
        //Arranging JFrames
        mainPanel.add(buttonLayout, BorderLayout.NORTH);
        mainPanel.add(sceneCanvas, BorderLayout.CENTER);
        mainPanel.add(titleFPS, BorderLayout.SOUTH);

        window.pack();
    }

    /**
     * Sets up the action listener which listens for events from all buttons and menu items in the GUI
     */
    public void setUpActionListener() {

        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Object buttonPressed = ae.getSource();
                //Buttons
                if(buttonPressed == moreActionsButton) {
                    actionsMenu.setVisible(true);
                    actionsMenu.show(moreActionsButton,8,26);
                }
                if(buttonPressed == startAnimation) {
                    isRunning = true;
                }
                if(buttonPressed == stopAnimation) {
                    isRunning = false;
                }

                //more actions menu
                if(buttonPressed == speedMenuButton) { //Set Animation Speed:
                    speedMenu.show(moreActionsButton,8,26);
                }
                if(buttonPressed == FPSMenuButton) {
                    FPSMenu.show(moreActionsButton,8,26);
                }
                if(buttonPressed == experimentalFeaturesMenuButton){
                    experimentalFeaturesMenu.show(moreActionsButton,8,26);
                }
                if(buttonPressed == toggleAntiAliasing) {
                    sceneCanvas.toggleAntiAliasing();
                }
                if(buttonPressed == updateScene) {
                    sceneCanvas.updateAnimation(true);
                }
                if(buttonPressed == resetScene) {
                    sceneCanvas.resetScene();
                }
                if(buttonPressed == toggleGridVisibility) { //Grid
                    sceneCanvas.showGrid();
                }

                //speed Menu stuff
                if(buttonPressed == speedQuarter) {
                    sceneCanvas.setAnimationSpeed(0.25);
                }
                if(buttonPressed == speedHalf) {
                    sceneCanvas.setAnimationSpeed(0.5);
                }
                if(buttonPressed == speed1x) { //1x
                    sceneCanvas.setAnimationSpeed(1);
                }
                if(buttonPressed == speed2x) { //2x
                    sceneCanvas.setAnimationSpeed(2);
                }
                if(buttonPressed == speed3x) { //3x
                    sceneCanvas.setAnimationSpeed(3); 
                }
                if(buttonPressed == speed4x) { //4x
                    sceneCanvas.setAnimationSpeed(4);
                }
                if(buttonPressed == speed5x) { //5x
                    sceneCanvas.setAnimationSpeed(5);  
                }

                //FPS Menu
                if(buttonPressed == FPS30) {
                    EDT.setDelay(33);
                }
                if(buttonPressed == FPS60) {
                    EDT.setDelay(16);
                }
                if(buttonPressed == FPS120) {
                    EDT.setDelay(8);
                }
                if(buttonPressed == FPS144) {
                    EDT.setDelay(7);
                }
                if(buttonPressed == FPS240) {
                    EDT.setDelay(4);
                }

                //experimental features
                if(buttonPressed == timeSetDay) {
                    sceneCanvas.setTimeDay(true);
                }
                if(buttonPressed == timeSetNight) {
                    sceneCanvas.setTimeDay(false);
                }
                if(buttonPressed == toggleMovableSunMoon) {
                    isMovable = !isMovable;
                }
            }
        };
        //main buttons
        moreActionsButton.addActionListener(al);
        startAnimation.addActionListener(al);
        stopAnimation.addActionListener(al);
        //more actions
        speedMenuButton.addActionListener(al);
        FPSMenuButton.addActionListener(al);
        experimentalFeaturesMenuButton.addActionListener(al);
        toggleAntiAliasing.addActionListener(al);
        updateScene.addActionListener(al);
        resetScene.addActionListener(al);
        toggleGridVisibility.addActionListener(al);
        //speed Menu
        speedQuarter.addActionListener(al);
        speedHalf.addActionListener(al);
        speed1x.addActionListener(al);
        speed2x.addActionListener(al);
        speed3x.addActionListener(al);
        speed4x.addActionListener(al);
        speed5x.addActionListener(al);
        //FPS Menu
        FPS30.addActionListener(al);
        FPS60.addActionListener(al);
        FPS120.addActionListener(al);
        FPS144.addActionListener(al);
        FPS240.addActionListener(al);
        //experimental features menu
        timeSetDay.addActionListener(al);
        timeSetNight.addActionListener(al);
        toggleMovableSunMoon.addActionListener(al);

    }

    /**
     * Sets up the mouse listener which listenes for mouse events which will be passed to isSunSelected
     * and setSunMoonCenter to effectively move the sun and moon. Only works when a JMenuItem is toggled.
     */
    public void setUpMouseListener() {
        MouseAdapter ma = new MouseAdapter() {
            @Override 
            public void mouseDragged(MouseEvent e) {
                double mouseX = e.getX();
                double mouseY = e.getY();
                if (sceneCanvas.isSunMoonSelected(mouseX, mouseY, 0) && isMovable) {
                    sceneCanvas.setSunMoonCenter(mouseX, mouseY, 0);
                    isRunning = false;
                }
                if (sceneCanvas.isSunMoonSelected(mouseX, mouseY, 2) && isMovable) {
                    sceneCanvas.setSunMoonCenter(mouseX, mouseY, 2);
                    isRunning = false;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(isMovable) {
                    isRunning = !isRunning;
                }
            }

        };
        mainPanel.addMouseListener(ma);
        mainPanel.addMouseMotionListener(ma);
    }

    /**
     * Sets up a key listener which is used to incremement the animation by one frame.
     */
    public void setUpKeyListener() {
        KeyAdapter ka = new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_A) {
                    isRunning = true;
                } else if(e.getKeyCode() == KeyEvent.VK_S) {
                    isRunning = false;
                } else {
                    sceneCanvas.updateAnimation(true);
                }

            }

        };
        mainPanel.addKeyListener(ka);
        moreActionsButton.addKeyListener(ka);
        startAnimation.addKeyListener(ka);
        stopAnimation.addKeyListener(ka);
    }


    /**
     * Sets up a java.util.Timer and javax.swing.Timer to update the 60 times every second and display
     * the animation depending on the current FPS cap.
     */
    public void setUpTimers() {

        Timer animationLoop = new Timer();
        //updates the animation 60 times a second
        TimerTask updateAnimation = new TimerTask() {
            @Override
            public void run() {
                sceneCanvas.updateAnimation(isRunning);
            }
        };
        animationLoop.scheduleAtFixedRate(updateAnimation,0L,16L);
        

        ActionListener displayAnimation = new ActionListener() {
            long previousTime = System.currentTimeMillis()-1;
            int frames = 0;

            public void actionPerformed(ActionEvent e) {
                frames++; 
                long currentTime = System.currentTimeMillis();
                /**
                 * Counts how many times the animation as displayed per second (basically fps)
                 * Checks if ~1 sec has passed. If a second has passed and displayFPS_Counter is true,
                 * it resets the value of frames and gets a new previous time.
                 */ 
                if (currentTime - previousTime >= 1000) {
                    if (isRunning) {
                        titleFPS.setText("Camping | FPS: " + frames + "   ");
                    } else {
                        titleFPS.setText("Camping   ");
                    }
                    frames = 0;
                    previousTime = currentTime;
                }

                sceneCanvas.repaint();
            }

        };
        EDT = new javax.swing.Timer((int)Math.round(1000.0/FPS_CAP), displayAnimation);
        EDT.start();
    }

    /**
     * All setup methods in one method
     */
    public void setUpSceneFrame() {
        setUpGUI();
        setUpTimers();
        setUpActionListener();
        setUpKeyListener();
        setUpMouseListener();
    }
}
