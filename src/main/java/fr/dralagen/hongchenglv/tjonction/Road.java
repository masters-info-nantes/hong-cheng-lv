package fr.dralagen.hongchenglv.tjonction;

import java.util.concurrent.locks.Lock;

import javax.swing.Icon;
import javax.swing.JLabel;

import fr.dralagen.hongchenglv.tjonction.TrafficLight.StateLight;

public class Road extends Thread {

    protected TrafficLight trafficLight;
    protected final Lock greenLocker;

    protected boolean isPower;

    JLabel feu1;
    JLabel feu2;

    public Road(TrafficLight light, Lock lock) {
        trafficLight = light;
        greenLocker = lock;
        Icon img = trafficLight.getIcon();
        feu1 = new JLabel();
        feu1.setIcon(img);
        feu1.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());

        feu2 = new JLabel();
        feu2.setIcon(img);
        feu2.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());

    }

    public void toStop() {
        trafficLight.setState(StateLight.YELLOW);
        feu1.setIcon(trafficLight.getIcon());
        feu2.setIcon(trafficLight.getIcon());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        trafficLight.setState(StateLight.RED);
        feu1.setIcon(trafficLight.getIcon());
        feu2.setIcon(trafficLight.getIcon());

    }

    public void toOpen() {
        trafficLight.setState(StateLight.GREEN);
        feu1.setIcon(trafficLight.getIcon());
        feu2.setIcon(trafficLight.getIcon());

    }

    public StateLight getLightState() {
        return trafficLight.getState();
    }

    public void switchOff() {
        isPower = false;
    }

}
