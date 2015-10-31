package fr.dralagen.hongchenglv.tjonction;

import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class App extends JFrame {

    public App() {
        super("Hong cheng lv");

        final Tjonction crossing = new Tjonction();

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

        Button startStop = new Button("Stop");
        startStop.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if (startStop.getLabel().equals("Stop")) {
                    startStop.setLabel("Start");
                    crossing.quit();

                } else {
                    startStop.setLabel("Stop");
                }
            }
        });

        add(startStop);
        startStop.setBounds(0, 0, 100, 50);

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

        JLabel MinorFeu = crossing.getMinor().getFeu();
        add(MinorFeu);
        MinorFeu.setLocation(
                labMinorRoute.getX() + labMinorRoute.getWidth()
                        - MinorFeu.getWidth(),
                labMinorRoute.getY() + labMinorRoute.getHeight());

        JLabel MajorFeuTop = crossing.getMajor().getFeu1();
        add(MajorFeuTop);
        MajorFeuTop.setLocation(
                labMajorRouteTop.getX() - MajorFeuTop.getWidth(),
                labMajorRouteTop.getY() + labMajorRouteTop.getHeight()
                        - MinorFeu.getHeight());

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

        JLabel MajorFeuDown = crossing.getMajor().getFeu2();
        add(MajorFeuDown);
        MajorFeuDown.setLocation(
                labMajorRouteDown.getX() + labMajorRouteDown.getWidth(),
                labMajorRouteDown.getY());

        crossing.start();

    }

    public static void main(String[] args) {
        JFrame frame = new App();
        frame.setBackground(Color.BLACK);

    }

}
