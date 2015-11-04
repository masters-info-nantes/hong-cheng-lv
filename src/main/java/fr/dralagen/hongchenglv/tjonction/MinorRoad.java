package fr.dralagen.hongchenglv.tjonction;

import java.util.concurrent.locks.Lock;

import javax.swing.JLabel;

public class MinorRoad extends Road {

    public MinorRoad(TrafficLight light, Lock minorLock) {
        super(light, minorLock);
    }

    public JLabel getFeu() {
        return feu1;
    }

}
