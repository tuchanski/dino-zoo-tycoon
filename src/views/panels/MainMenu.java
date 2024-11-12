package views.panels;

import controllers.ZooController;
import repositories.ZooRepositoryImpl;
import services.ZooSystem;
import views.utils.CustomButton;
import views.utils.CustomFont;
import views.utils.ImageBackgroundPanel;
import views.utils.TitleBarButton;

import controllers.UserController;
import models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class MainMenu extends JFrame {
    private int mouseX, mouseY;
    private UserController userController;
    private User currentUser;
    private ZooRepositoryImpl zooRepository;
    private ZooController zooController;
    private JLabel cashLabel;

    public MainMenu(User currentUser) {

        this.currentUser = currentUser;
        this.zooController = new ZooController(currentUser);
        this.zooRepository = new ZooRepositoryImpl(currentUser);

        Timer timer = new Timer(10000, evt -> {
            zooController.addVisitor(zooController.getZooByUser());
        });
        timer.setRepeats(true);
        timer.start();

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
        usernameLabel.setBounds(labelX, 115, textWidth, 30);

        backgroundPanel.add(usernameLabel);

        //cash

        int currentCash = zooRepository.getCurrentCash(currentUser.getId());
        cashLabel = new JLabel("$ " + currentCash);
        cashLabel.setFont(CustomFont.useCustomFont(12f));
        cashLabel.setForeground(fontColor);

        metrics = cashLabel.getFontMetrics(cashLabel.getFont());
        textWidth = metrics.stringWidth("$ " + currentCash);

        logoutButtonX = 650;
        logoutButtonWidth = 103;

        labelX = logoutButtonX + (logoutButtonWidth / 2) - (textWidth / 2);

        cashLabel.setBounds(labelX, 143, textWidth, 20);
        backgroundPanel.add(cashLabel);

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
