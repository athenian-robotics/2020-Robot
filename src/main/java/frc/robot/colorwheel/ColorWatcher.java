package frc.robot.colorwheel;

import frc.robot.subsystems.ColorWheelSubsystem;

import static frc.robot.colorwheel.Colors.RED;


public class ColorWatcher {
    private ColorWheelSubsystem colorWheel;

    public ColorWatcher(ColorWheelSubsystem colorWheel) {
        this.colorWheel = colorWheel;

    }


    public void trackMovement(WatcherEvent watcherEvent, int direction) {
        Colors currentColor = colorWheel.currentColor();

        while (true) {
            if (currentColor == RED) {

                watcherEvent.onColorChange();

            }


        }

    }


}