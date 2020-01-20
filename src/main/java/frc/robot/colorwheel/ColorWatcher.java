package frc.robot.colorwheel;

import edu.wpi.first.wpilibj.Timer;


public class ColorWatcher {
    private int tilesPassed = 0;
    private final ColorWheelUtils colorWheelUtils = new ColorWheelUtils();


    private DifferentColors OGColor;
    private DifferentColors NEWColor;

    public ColorWatcher() {

    }

    public void getTilesPassed() {
        OGColor = colorWheelUtils.currentColor();
        Timer.delay(0.5);
        NEWColor = colorWheelUtils.currentColor();

        if (NEWColor != OGColor) {
            tilesPassed++;
            System.out.println(tilesPassed);
        }
        System.out.println(tilesPassed);
    }
}