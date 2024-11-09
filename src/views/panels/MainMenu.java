package views.panels;

import views.utils.CustomButton;
import views.utils.ImageBackgroundPanel;
import views.utils.TitleBarButton;

import controllers.UserController;
import models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenu extends JFrame {
    private int mouseX, mouseY;
    private UserController userController;

    public MainMenu(User user) {
        setUndecorated(true);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        userController = new UserController();

        ImageBackgroundPanel backgroundPanel = new ImageBackgroundPanel("src/resources/backgrounds/bg.png");
        backgroundPanel.setLayout(null);

        TitleBarButton titleBarButtons = new TitleBarButton(this);
        titleBarButtons.setBounds(0, 0, 800, 100);
        backgroundPanel.add(titleBarButtons);

        ImageIcon imageIcon = new ImageIcon("src/resources/images/map.png");
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(30, 80, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        backgroundPanel.add(imageLabel);

        String username = user.getUsername().toUpperCase();
        Font usernameFont = new Font("Arial", Font.BOLD, 18);
        JLabel usernameLabel = new JLabel(username);
        usernameLabel.setFont(usernameFont);

        Color fontColor = new Color(228, 201, 173);
        usernameLabel.setForeground(fontColor);

        FontMetrics metrics = getFontMetrics(usernameFont);
        int textWidth = metrics.stringWidth(username);
        // logout info x,y
        int logoutButtonX = 650;
        int logoutButtonWidth = 103;
        // Center usernameLabel
        int labelX = logoutButtonX + (logoutButtonWidth / 2) - (textWidth / 2);
        usernameLabel.setBounds(labelX, 126, textWidth, 30);

        backgroundPanel.add(usernameLabel);

        CustomButton logoutButton = new CustomButton(
                "src/resources/buttons/logoutButton.png",
                650,
                165,
                103,
                41,
                e -> logoutAction(),
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );

        CustomButton userPhoto = new CustomButton(
                "src/resources/buttons/fakeUser.png",
                550,
                120,
                85,
                86,
                e -> this.setState(JFrame.ICONIFIED),
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );

        backgroundPanel.add(logoutButton);
        backgroundPanel.add(userPhoto);
        backgroundPanel.add(usernameLabel);

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

    private void logoutAction(){
        this.dispose();

        InitialPanel initialPanel = new InitialPanel();
        initialPanel.setVisible(true);
    }

    public static void main(String[] args) {
        User user = new User("testando10", "1grse81g8541g851g8sr1grsg8s1gs51g5s");
        new MainMenu(user);
    }
}
