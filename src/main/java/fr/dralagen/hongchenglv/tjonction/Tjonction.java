package fr.dralagen.hongchenglv.tjonction;

import fr.dralagen.hongchenglv.tjonction.TrafficLight.StateLight;

public class Tjonction extends Thread {

    public static final int MINOR_ROAD_GREEN_DELAY = 30000; // 30 Seconds
    public static final int MAJOR_ROAD_GREEN_DELAY = 30000; // 30 Seconds
    public static final int INTERIM_LIGHTS_DELAY = 5000; // 5 Seconds

    private Road minorRoad;
    private Road majorRoad;

    private CarPresentThread tjonction;

    private boolean isCar;

    public Tjonction() {

        tjonction = new CarPresentThread();

        majorRoad = new Road(new TrafficLight(StateLight.RED));
        minorRoad = new Road(new TrafficLight(StateLight.RED));

        isCar = false;

        tjonction.start();

    }

    public Road getMinor() {
        return minorRoad;
    }

    public Road getMajor() {
        return majorRoad;
    }

    @Override
    public void run() {
        super.run();

        majorRoad.start();
        minorRoad.start();

        synchronized (majorRoad) {
            majorRoad.toOpen();
            majorRoad.notify();
        }

        try {
            majorRoad.join();
            minorRoad.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void quit() {
        minorRoad.switchOff();
        majorRoad.switchOff();
    }

    public synchronized void toggleCarPresent() {

        tjonction.toggleCarPresent();

        if (!tjonction.isAlive()) {
            tjonction = new CarPresentThread();
            tjonction.start();
        }
    }

    public boolean isCarPresent() {
        return isCar;
    }

    private class CarPresentThread extends Thread {

        @Override
        public void run() {
            while (isCar) {

                synchronized (minorRoad) {
                    majorRoad.toStop();
                    minorRoad.notifyAll();
                }
                // Delay of minor-road stays on green
                try {
                    Thread.sleep(MINOR_ROAD_GREEN_DELAY); // 30 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (majorRoad) {
                    minorRoad.toStop();
                    majorRoad.notifyAll();
                }

                // delay of major-road stays on green
                try {
                    Thread.sleep(MAJOR_ROAD_GREEN_DELAY); // 30 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

        public void toggleCarPresent() {
            isCar = !isCar;
        }
    };

}
