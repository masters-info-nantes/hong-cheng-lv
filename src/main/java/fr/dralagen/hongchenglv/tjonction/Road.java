package fr.dralagen.hongchenglv.tjonction;

import java.util.concurrent.locks.Lock;

import javax.swing.ImageIcon;
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
        ImageIcon img = new ImageIcon("ressources/img/feux-feuvert.gif");
		feu1 = new JLabel();
		feu1.setIcon(img);
		feu1.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		
		feu2 = new JLabel();
		feu2.setIcon(img);
		feu2.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		
    }

    public void toStop() {
        trafficLight.setState(StateLight.YELLOW);
        ImageIcon img = new ImageIcon("ressources/img/feux-feuorange.gif");
		feu1.setIcon(img);
		feu2.setIcon(img);


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        trafficLight.setState(StateLight.RED);
        img = new ImageIcon("ressources/img/feux-feurouge.gif");
		feu1.setIcon(img);		
		feu2.setIcon(img);

    }

    public void toOpen() {
        trafficLight.setState(StateLight.GREEN);
        ImageIcon img = new ImageIcon("ressources/img/feux-feuvert.gif");
		feu1.setIcon(img);
		feu2.setIcon(img);

    }

    public StateLight getLightState() {
        return trafficLight.getState();
    }

    public void switchOff() {
        isPower = false;
    }

}
