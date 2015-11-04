package fr.dralagen.hongchenglv.tjonction;

import java.util.Observable;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class TrafficLight extends Observable {

    public static enum StateLight {
        GREEN {
            @Override
            public Icon getIcon() {
                return new ImageIcon(
                        getClass().getResource("/img/feux-feuvert.gif"));
            }
        },
        YELLOW {
            @Override
            public Icon getIcon() {
                return new ImageIcon(
                        getClass().getResource("/img/feux-feuorange.gif"));
            }
        },
        RED {
            @Override
            public Icon getIcon() {
                return new ImageIcon(
                        getClass().getResource("/img/feux-feurouge.gif"));
            }
        };
        public abstract Icon getIcon();
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

    public Icon getIcon() {
        return state.getIcon();
    }

}
