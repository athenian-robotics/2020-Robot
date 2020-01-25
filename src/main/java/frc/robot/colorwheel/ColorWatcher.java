package frc.robot.colorwheel;

public class ColorWatcher {
    //Init basic variables and classes
    private int tilesPassed = 0;
    private final ColorWheelUtils colorWheelUtils = new ColorWheelUtils();


    public ColorWatcher() {

    }


    /**
     * A basic getTilesPassed method that grabs the current color,
     * waits .5 seconds, and grabs the current color again
     * If the old color != the new detected color, add 1 to the tile passed count
     * <p>
     * Divide total tile count by 8 to get # of rotations (if needed)
     */

}