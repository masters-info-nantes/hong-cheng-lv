package fr.dralagen.hongchenglv.tjonction;

import java.util.concurrent.locks.Lock;

public class MajorRoad extends Road {

	public MajorRoad(TrafficLight light, Lock lock) {
		super(light, lock);
	}

	@Override
	public void run() {
		while (true) {
			lock.lock();
			try {
				toOpen();
				System.out.println("major: green");
				Thread.sleep(500);
				System.out.println("major: orange");
				toStop();
				System.out.println("major: red");
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
