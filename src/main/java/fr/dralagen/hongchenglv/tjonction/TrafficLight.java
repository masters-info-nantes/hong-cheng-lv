package fr.dralagen.hongchenglv.tjonction;

import java.net.URL;
import java.util.Observable;

public class TrafficLight extends Observable {

    public static enum StateLight {
        GREEN {
            @Override
            public URL getIcon() {
                return getClass().getResource("/img/feux-feuvert.gif");
            }
        },
        YELLOW {
            @Override
            public URL getIcon() {
                return getClass().getResource("/img/feux-feuorange.gif");
            }
        },
        RED {
            @Override
            public URL getIcon() {
                return getClass().getResource("/img/feux-feurouge.gif");
            }
        };
        public abstract URL getIcon();
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

        setChanged();
        notifyObservers(state);
    }

    public URL getIcon() {
        return state.getIcon();
    }

}
