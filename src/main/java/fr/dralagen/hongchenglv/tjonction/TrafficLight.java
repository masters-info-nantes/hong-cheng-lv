package fr.dralagen.hongchenglv.tjonction;

public class TrafficLight {

	public static enum StateLight {
		GREEN, ORANGE, RED
	}

	private StateLight state;

	public TrafficLight(StateLight state) {
		setState(state);
	}

	public StateLight getState() {
		return state;
	}

	public void setState(StateLight state) {
		this.state = state;
	}

}
