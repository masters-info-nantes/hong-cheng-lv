package fr.dralagen.hongchenglv.tjonction;

import javax.swing.JLabel;

public class MajorRoad extends Road {

    public MajorRoad(TrafficLight light) {
        super(light);
    }

    public JLabel getFeu1() {
        return feu1;
    }

    public JLabel getFeu2() {
        return feu2;
    }

}
