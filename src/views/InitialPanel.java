package views;

import views.utils.CustomButton;
import views.utils.ImageBackgroundPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InitialPanel extends JFrame {
    private int mouseX, mouseY;

    public InitialPanel() {
        setUndecorated(true);
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ImageBackgroundPanel backgroundPanel = new ImageBackgroundPanel("src/resources/images/small-bg-welcome.png");
        backgroundPanel.setLayout(null);

        CustomButton closeButton = new CustomButton(
                "src/resources/buttons/closeButtonSmall.png",
                335,
                14,
                52,
                53,
                e -> System.exit(0),
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );

        CustomButton minimizeButton = new CustomButton(
                "src/resources/buttons/minimizeButtonSmall.png",
                280,
                14,
                52,
                53,
                e -> this.setState(JFrame.ICONIFIED),
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );

        backgroundPanel.add(closeButton);
        backgroundPanel.add(minimizeButton);

        CustomButton loginButton = new CustomButton(
                "src/resources/buttons/loginButton.png",
                116,
                190,
                165,
                62,
                e -> {
                    new Login(this);
                    setVisible(false);
                },
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );

        CustomButton registerButton = new CustomButton(
                "src/resources/buttons/registerButton.png",
                116,
                260,
                165,
                62,
                e -> {
                    new Register(this);
                    setVisible(false);
                },
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );

        backgroundPanel.add(loginButton);
        backgroundPanel.add(registerButton);

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
        new InitialPanel();
    }
}
