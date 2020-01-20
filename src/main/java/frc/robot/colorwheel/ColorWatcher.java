package frc.robot.colorwheel;

import edu.wpi.first.wpilibj.Timer;


public class ColorWatcher {
    //Init basic variables and classes
    private int tilesPassed = 0;
    private final ColorWheelUtils colorWheelUtils = new ColorWheelUtils();
    private DifferentColors OGColor;
    private DifferentColors NEWColor;


    public ColorWatcher() {

    }


    /**
     * A basic getTilesPassed method that grabs the current color,
     * waits .5 seconds, and grabs the current color again
     * If the old color != the new detected color, add 1 to the tile passed count
     * <p>
     * Divide total tile count by 8 to get # of rotations (if needed)
     */
    public int getTilesPassed() {
        //Grab the current color
        OGColor = colorWheelUtils.currentColor();
        //Wait .3 seconds before grabbing the detected color again
        Timer.delay(0.3);
        NEWColor = colorWheelUtils.currentColor();

        if (NEWColor != OGColor) {
            tilesPassed++;
            System.out.println(tilesPassed);
        }
        return tilesPassed;
    }
}