package views;

import views.utils.ImageBackgroundPanel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenu extends JFrame {
    private int mouseX, mouseY;

    public MainMenu() {
        setUndecorated(true);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ImageBackgroundPanel backgroundPanel = new ImageBackgroundPanel("src/resources/images/bg.png");
        backgroundPanel.setLayout(null);

        TitleBarButton titleBarButtons = new TitleBarButton(this);
        titleBarButtons.setBounds(0, 0, 800, 100);
        backgroundPanel.add(titleBarButtons);

        backgroundPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });

        backgroundPanel.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                setLocation(x - mouseX, y - mouseY);
            }
        });

        add(backgroundPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}
