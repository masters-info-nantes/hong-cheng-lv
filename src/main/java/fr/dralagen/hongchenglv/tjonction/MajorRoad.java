package fr.dralagen.hongchenglv.tjonction;

import java.util.concurrent.locks.Lock;

import javax.swing.JLabel;

public class MajorRoad extends Road {

    public MajorRoad(TrafficLight light, Lock lock) {
        super(light, lock);
    }
    
    public JLabel getFeu1(){
    	return feu1;
    }
    
    public JLabel getFeu2(){
    	return feu2;
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
