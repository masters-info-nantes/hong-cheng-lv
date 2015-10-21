package fr.dralagen.hongchenglv.tjonction;

import java.util.concurrent.locks.Lock;

public class MajorRoad extends Road {

    public MajorRoad(TrafficLight light, Lock lock) {
        super(light, lock);
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
