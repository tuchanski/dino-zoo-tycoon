package views.panels;

import services.ZooSystem;
import views.utils.CustomButton;
import views.utils.CustomFont;
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
    private User currentUser;


    public MainMenu(User currentUser) {
        this.currentUser = currentUser;

        setUndecorated(true);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        userController = new UserController();

        ImageBackgroundPanel backgroundPanel = new ImageBackgroundPanel("src/resources/backgrounds/bg.png");
        backgroundPanel.setLayout(null);

        ImageIcon overviewViewButton = new ImageIcon("src/resources/utils/overview.png");
        JLabel overviewViewLabel = new JLabel(overviewViewButton);
        overviewViewLabel.setBounds(233, 80, 114, 45);
        backgroundPanel.add(overviewViewLabel);

        ImageIcon imageIcon = new ImageIcon("src/resources/images/map.png");
        JLabel mapLabel = new JLabel(imageIcon);
        mapLabel.setBounds(30, 80, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        backgroundPanel.add(mapLabel);

        TitleBarButton titleBarButtons = new TitleBarButton(this, currentUser, overviewViewLabel, mapLabel);
        titleBarButtons.setBounds(0, 0, 800, 100);
        backgroundPanel.add(titleBarButtons);

        String username = currentUser.getUsername().toUpperCase();
        Font usernameFont = CustomFont.useCustomFont(18f);
        JLabel usernameLabel = new JLabel(username);
        usernameLabel.setFont(usernameFont);

        Color fontColor = new Color(228, 201, 173);
        usernameLabel.setForeground(fontColor);

        FontMetrics metrics = usernameLabel.getFontMetrics(usernameFont);
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
        ZooSystem.setCurrentZoo(null);
        ZooSystem.setCurrentUser(null);
        this.dispose();

        InitialPanel initialPanel = new InitialPanel();
        initialPanel.setVisible(true);
    }


    public static void main(String[] args) {
        User user = new User("testando10", "1grse81g8541g851g8sr1grsg8s1gs51g5s");
        new MainMenu(user);
    }
}
