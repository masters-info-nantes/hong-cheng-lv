package fr.dralagen.hongchenglv.tjonction;

import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import fr.dralagen.hongchenglv.tjonction.TrafficLight.StateLight;

public class App extends JFrame {

    private static final long serialVersionUID = -436158930693626346L;

    private static final String LABEL_CAR_PRESENT = "Have car present";
    private static final String LABEL_NO_CAR_PRESENT = "No car present";

    private Tjonction crossing;

    private JLightIcon majorFeuTop;

    private JLightIcon minorFeu;

    private JLightIcon majorFeuDown;

    public App() {
        super("Hong cheng lv");

        crossing = new Tjonction();

        WindowListener l = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                crossing.quit();
                System.exit(0);
            }
        };
        setLayout(null);
        addWindowListener(l);
        setSize(430, 750);
        setVisible(true);
        // setResizable(false);

        Button carPresentBtn = new Button(LABEL_NO_CAR_PRESENT);
        carPresentBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                crossing.carPresent();

                if (carPresentBtn.getLabel() == LABEL_NO_CAR_PRESENT) {
                    carPresentBtn.setLabel(LABEL_CAR_PRESENT);
                } else {
                    carPresentBtn.setLabel(LABEL_NO_CAR_PRESENT);
                }
            }
        });

        add(carPresentBtn);
        carPresentBtn.setBounds(0, 0, 250, 50);

        ImageIcon majorRouteTop = new ImageIcon(
                getClass().getResource("/img/routeV.gif"));
        ImageIcon majorRouteDown = new ImageIcon(
                getClass().getResource("/img/routeV.gif"));
        ImageIcon minorRoute = new ImageIcon(
                getClass().getResource("/img/route.gif"));
        ImageIcon intersection = new ImageIcon(
                getClass().getResource("/img/intersection.gif"));

        JLabel labMajorRouteTop = new JLabel();
        labMajorRouteTop.setIcon(majorRouteTop);

        JLabel labMinorRoute = new JLabel();
        labMinorRoute.setIcon(minorRoute);

        add(labMajorRouteTop);
        labMajorRouteTop.setBounds(minorRoute.getIconWidth(), 0,
                majorRouteTop.getIconWidth(), majorRouteTop.getIconHeight());
        labMajorRouteTop.setLocation(minorRoute.getIconWidth(), 0);

        add(labMinorRoute);

        labMinorRoute.setBounds(0, labMajorRouteTop.getHeight(),
                minorRoute.getIconWidth(), minorRoute.getIconHeight());

        Icon img = crossing.getMinor().getLightState().getIcon();

        minorFeu = new JLightIcon();
        minorFeu.setIcon(img);
        minorFeu.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
        add(minorFeu);
        minorFeu.setLocation(
                labMinorRoute.getX() + labMinorRoute.getWidth()
                        - minorFeu.getWidth(),
                labMinorRoute.getY() + labMinorRoute.getHeight());
        crossing.getMinor().getTrafficLight().addObserver(minorFeu);

        img = crossing.getMajor().getLightState().getIcon();

        majorFeuTop = new JLightIcon();
        majorFeuTop.setIcon(img);
        majorFeuTop.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
        add(majorFeuTop);
        majorFeuTop.setLocation(
                labMajorRouteTop.getX() - majorFeuTop.getWidth(),
                labMajorRouteTop.getY() + labMajorRouteTop.getHeight()
                        - minorFeu.getHeight());
        crossing.getMajor().getTrafficLight().addObserver(majorFeuTop);

        JLabel labIntersection = new JLabel();
        labIntersection.setIcon(intersection);
        add(labIntersection);
        labIntersection.setBounds(
                labMinorRoute.getX() + minorRoute.getIconWidth(),
                labMinorRoute.getY(), intersection.getIconHeight(),
                intersection.getIconHeight());

        JLabel labMajorRouteDown = new JLabel();
        labMajorRouteDown.setIcon(majorRouteDown);
        add(labMajorRouteDown);
        labMajorRouteDown.setBounds(labIntersection.getX(),
                labIntersection.getY() + labIntersection.getHeight(),
                majorRouteDown.getIconWidth(), majorRouteDown.getIconHeight());

        majorFeuDown = new JLightIcon();
        majorFeuDown.setIcon(img);
        majorFeuDown.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
        add(majorFeuDown);
        majorFeuDown.setLocation(
                labMajorRouteDown.getX() + labMajorRouteDown.getWidth(),
                labMajorRouteDown.getY());
        crossing.getMajor().getTrafficLight().addObserver(majorFeuDown);

        crossing.start();

    }

    public static void main(String[] args) {
        JFrame frame = new App();
        frame.setBackground(Color.BLACK);

    }

    private class JLightIcon extends JLabel implements Observer {

        private static final long serialVersionUID = 1592674537139472208L;

        @Override
        public void update(Observable o, Object arg) {
            Icon img = ((StateLight) arg).getIcon();
            setIcon(img);
        }
    };

}
