package fr.dralagen.hongchenglv.tjonction;

public class MajorRoad extends Thread {

	private TrafficLight trafficLight;

	public MajorRoad(TrafficLight.StateLight state) {
		trafficLight = new TrafficLight(state);
	}

}
