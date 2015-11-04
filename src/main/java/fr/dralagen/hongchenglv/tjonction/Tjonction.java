package fr.dralagen.hongchenglv.tjonction;

import fr.dralagen.hongchenglv.tjonction.TrafficLight.StateLight;

public class Tjonction extends Thread {

    private Road minorRoad;
    private Road majorRoad;

    private CarPresentThread tjonction;

    public Tjonction() {

        tjonction = new CarPresentThread();

        majorRoad = new Road(new TrafficLight(StateLight.RED));
        minorRoad = new Road(new TrafficLight(StateLight.RED));

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

    public synchronized void carPresent() {

        if (!tjonction.isAlive()) {
            tjonction = new CarPresentThread();
            tjonction.start();

        } else {
            tjonction.toggleCarPresent();
        }
    }

    private class CarPresentThread extends Thread {

        private boolean isCar = true;

        @Override
        public void run() {
            while (isCar) {

                synchronized (minorRoad) {
                    majorRoad.toStop();
                    minorRoad.notifyAll();
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (majorRoad) {
                    minorRoad.toStop();
                    majorRoad.notifyAll();
                }

                // wait majorRoad to state Green
                try {
                    Thread.sleep(3000);
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
