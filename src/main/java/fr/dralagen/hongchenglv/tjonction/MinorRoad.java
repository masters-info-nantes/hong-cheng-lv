package fr.dralagen.hongchenglv.tjonction;


import java.util.concurrent.locks.Lock;
import javax.swing.JLabel;

public class MinorRoad extends Road {

	
    public MinorRoad(TrafficLight light, Lock lock) {
        super(light, lock);
    }
    
    public JLabel getFeu(){
    	return feu1;
    }

    @Override
    public void run() {
        isPower = true;
        while (isPower) {
            greenLocker.lock();
            try {
                toOpen();
                Thread.sleep(1000);
                toStop();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            } finally {
                greenLocker.unlock();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }
        }
    }

}
