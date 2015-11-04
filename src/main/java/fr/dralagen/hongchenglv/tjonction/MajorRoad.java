package fr.dralagen.hongchenglv.tjonction;

import java.util.concurrent.locks.Lock;

import javax.swing.JLabel;

public class MajorRoad extends Road {

    public MajorRoad(TrafficLight light, Lock majorLock) {
        super(light, majorLock);
    }

    public JLabel getFeu1() {
        return feu1;
    }

    public JLabel getFeu2() {
        return feu2;
    }

}
