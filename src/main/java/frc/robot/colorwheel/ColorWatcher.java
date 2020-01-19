package frc.robot.colorwheel;

import frc.robot.subsystems.ColorWheelSubsystem;


public class ColorWatcher {
    private ColorWheelSubsystem colorWheel;
    private DifferentColors colorWanted;

    public ColorWatcher(ColorWheelSubsystem colorWheel, DifferentColors colorWanted) {
        this.colorWheel = colorWheel;
        this.colorWanted = colorWanted;

    }


    public void trackMovement(WatcherEvent watcherEvent, int direction) {

    }


}