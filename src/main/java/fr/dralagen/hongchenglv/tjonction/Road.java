package fr.dralagen.hongchenglv.tjonction;

import fr.dralagen.hongchenglv.tjonction.TrafficLight.StateLight;

public class Road extends Thread {

    protected TrafficLight trafficLight;

    protected boolean isPower;

    public Road(TrafficLight light) {
        trafficLight = light;
    }

    public TrafficLight getTrafficLight() {
        return trafficLight;
    }

    public void toStop() {
        if (trafficLight.getState() == StateLight.GREEN) {
            trafficLight.setState(StateLight.YELLOW);

            // delay of interim lights
            try {
                Thread.sleep(Tjonction.INTERIM_LIGHTS_DELAY); // 5 seconds
            } catch (InterruptedException e) {
            }

            trafficLight.setState(StateLight.RED);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void toOpen() {

        if (trafficLight.getState() == StateLight.RED) {
            trafficLight.setState(StateLight.GREEN);
        }

    }

    public StateLight getLightState() {
        return trafficLight.getState();
    }

    public void switchOff() {
        isPower = false;
    }

    @Override
    public void run() {
        isPower = true;
        while (isPower) {

            synchronized (this) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                toOpen();
            }
        }
    }

}
