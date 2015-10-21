package fr.dralagen.hongchenglv.tjonction;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import fr.dralagen.hongchenglv.tjonction.TrafficLight.StateLight;

public class Tjonction {

	private MinorRoad minorRoad;
	private MajorRoad majorRoad;

	private final Lock lock = new ReentrantLock();

	public Tjonction() {
		minorRoad = new MinorRoad(new TrafficLight(StateLight.RED), lock);
		majorRoad = new MajorRoad(new TrafficLight(StateLight.RED), lock);

		majorRoad.start();
		minorRoad.start();

	}

	public void printState() {
		System.out.println("-------------------");
		System.out.println("major : " + majorRoad.getLightState());
		System.out.println("minor : " + minorRoad.getLightState());
		System.out.println("-------------------");

	}

	public static void main(String[] args) {

		Tjonction crossing = new Tjonction();

	}
}
