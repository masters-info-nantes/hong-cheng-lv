package fr.dralagen.hongchenglv.tjonction;

import java.util.concurrent.locks.Lock;

import fr.dralagen.hongchenglv.tjonction.TrafficLight.StateLight;

public class Road extends Thread {

	protected TrafficLight trafficLight;
	protected final Lock lock;

	public Road(TrafficLight light, Lock lock) {
		trafficLight = light;
		this.lock = lock;
	}

	public void toStop() {
		trafficLight.setState(StateLight.ORANGE);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}

		trafficLight.setState(StateLight.RED);
	}

	public void toOpen() {
		trafficLight.setState(StateLight.GREEN);
	}

	public StateLight getLightState() {
		return trafficLight.getState();
	}

}
