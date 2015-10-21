package fr.dralagen.hongchenglv.tjonction;

import java.util.concurrent.locks.Lock;

public class MinorRoad extends Road {

	public MinorRoad(TrafficLight light, Lock lock) {
		super(light, lock);
	}

	@Override
	public void run() {
		while (true) {
			lock.lock();
			try {
				toOpen();
				System.out.println("minor: green");
				Thread.sleep(500);
				System.out.println("minor: orange");
				toStop();
				System.out.println("minor: red");
			} catch (InterruptedException e) {
			} finally {
				lock.unlock();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
				}
			}
		}
	}

}
