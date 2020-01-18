package frc.robot.colorwheel;

import frc.robot.subsystems.ColorWheelSubsystem;

import static frc.robot.colorwheel.DifferentColors.RED;


public class ColorWatcher {
    private ColorWheelSubsystem colorWheel;

    public ColorWatcher(ColorWheelSubsystem colorWheel) {
        this.colorWheel = colorWheel;

    }


    public void trackMovement(WatcherEvent watcherEvent, int direction) {
        DifferentColors currentColor = colorWheel.currentColor();


        //TODO Complete this
        while (true) {
            if (currentColor == RED) {

                watcherEvent.onColorChange();

            }


        }

    }


}