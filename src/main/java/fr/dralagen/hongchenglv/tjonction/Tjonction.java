package fr.dralagen.hongchenglv.tjonction;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import fr.dralagen.hongchenglv.tjonction.TrafficLight.StateLight;

public class Tjonction extends Thread {

    private MinorRoad minorRoad;
    private MajorRoad majorRoad;

    private final Lock lock = new ReentrantLock();

    public Tjonction() {
        minorRoad = new MinorRoad(new TrafficLight(StateLight.RED), lock);
        majorRoad = new MajorRoad(new TrafficLight(StateLight.RED), lock);

    }

    public MinorRoad getMinor() {
        return minorRoad;
    }

    public MajorRoad getMajor() {
        return majorRoad;
    }

    @Override
    public void run() {
        super.run();

        minorRoad.start();
        majorRoad.start();
    }

    public void quit() {
        minorRoad.switchOff();
        majorRoad.switchOff();
    }

}
