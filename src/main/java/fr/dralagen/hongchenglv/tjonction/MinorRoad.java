package fr.dralagen.hongchenglv.tjonction;

import javax.swing.JLabel;

public class MinorRoad extends Road {

    public MinorRoad(TrafficLight light) {
        super(light);
    }

    public JLabel getFeu() {
        return feu1;
    }

}
