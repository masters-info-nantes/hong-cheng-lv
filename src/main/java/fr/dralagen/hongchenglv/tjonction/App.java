package fr.dralagen.hongchenglv.tjonction;

import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
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

        Icon img = new ImageIcon(crossing.getMinor().getLightState().getIcon());
        minorFeu = new JLightIcon() {
            @Override
            protected void paintComponent(Graphics g) {
                if (iconURL == null) {
                    super.paintComponent(g);
                }
                super.paintComponent(rotateImage((Graphics2D) g, 90,
                        -getWidth() / 3, -getHeight() / 3));
            }
        };
        minorFeu.setIcon(crossing.getMinor().getLightState().getIcon());
        minorFeu.setBounds(0, 0, minorFeu.getIcon().getIconHeight(),
                minorFeu.getIcon().getIconHeight());
        add(minorFeu);
        minorFeu.setLocation(
                labMinorRoute.getX() + labMinorRoute.getWidth()
                        - minorFeu.getWidth(),
                labMinorRoute.getY() + labMinorRoute.getHeight());
        crossing.getMinor().getTrafficLight().addObserver(minorFeu);

        majorFeuTop = new JLightIcon() {
            @Override
            protected void paintComponent(Graphics g) {
                if (iconURL == null) {
                    super.paintComponent(g);
                }
                super.paintComponent(rotateImage((Graphics2D) g, 180, 0, 0));
            }
        };
        majorFeuTop.setIcon(crossing.getMajor().getLightState().getIcon());
        majorFeuTop.setBounds(0, 0, majorFeuTop.getIcon().getIconWidth(),
                majorFeuTop.getIcon().getIconHeight());
        add(majorFeuTop);
        majorFeuTop.setLocation(
                labMajorRouteTop.getX() - majorFeuTop.getWidth(),
                labMajorRouteTop.getY() + labMajorRouteTop.getHeight()
                        - majorFeuTop.getHeight() - 5);
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
        majorFeuDown.setIcon(crossing.getMajor().getLightState().getIcon());
        majorFeuDown.setBounds(0, 0, majorFeuDown.getIcon().getIconWidth(),
                majorFeuDown.getIcon().getIconHeight());
        add(majorFeuDown);
        majorFeuDown.setLocation(
                labMajorRouteDown.getX() + labMajorRouteDown.getWidth(),
                labMajorRouteDown.getY() + 5);
        crossing.getMajor().getTrafficLight().addObserver(majorFeuDown);

        crossing.start();

    }

    public static void main(String[] args) {
        JFrame frame = new App();
        frame.setBackground(Color.BLACK);

    }

    private class JLightIcon extends JLabel implements Observer {

        private static final long serialVersionUID = 1592674537139472208L;

        protected URL iconURL;

        @Override
        public void update(Observable o, Object arg) {
            setIcon(((StateLight) arg).getIcon());
        }

        public Graphics2D rotateImage(Graphics2D g, int rotate, int tx,
                int ty) {
            BufferedImage bi;
            try {
                bi = ImageIO.read(iconURL);
            } catch (IOException e) {
                return null;
            }
            g.rotate(Math.toRadians(rotate), bi.getWidth() / 2,
                    bi.getHeight() / 2);
            g.translate(tx, ty);
            g.drawImage(bi, 0, 0, null);

            g.dispose();
            return g;
        }

        public void setIcon(URL url) {
            iconURL = url;
            setIcon(new ImageIcon(url));
        }
    };

}
