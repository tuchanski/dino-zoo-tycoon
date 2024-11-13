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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenu extends JFrame {
    private int mouseX, mouseY;
    private UserController userController;
    private User currentUser;
    private ZooRepositoryImpl zooRepository;
    private ZooController zooController;
    private JLabel cashLabel;
    private JLabel usernameLabel;
    private JTextArea logTextArea;
    private JScrollPane logScrollPane;

    private String visitorName = "";


    public MainMenu(User currentUser) {

        this.currentUser = currentUser;
        this.zooController = new ZooController(currentUser);
        this.zooRepository = new ZooRepositoryImpl(currentUser);

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
        usernameLabel = new JLabel(username);

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
        textWidth = metrics.stringWidth("$ " + currentCash + "   ");

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

        ImageIcon employeesViewButton = new ImageIcon("src/resources/utils/employeeManageButton.png");
        JLabel employeeViewLabel = new JLabel(employeesViewButton);
        employeeViewLabel.setBounds(610, 214, 102, 41);
        backgroundPanel.add(employeeViewLabel);

        CustomButton hireEmployeeButton = new CustomButton(
                "src/resources/buttons/hireEmployeeButton.png",
                583,
                237,
                153,
                58,
                e -> logoutAction(),
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );

        ImageIcon visitorsViewsButton = new ImageIcon("src/resources/utils/visitorsManageButton.png");
        JLabel visitorsViewsLabel = new JLabel(visitorsViewsButton);
        visitorsViewsLabel.setBounds(610, 300, 102, 41);
        backgroundPanel.add(visitorsViewsLabel);

        CustomButton manageVisitorsButton = new CustomButton(
                "src/resources/buttons/manageVisitorsButton.png",
                583,
                323,
                153,
                58,
                e -> openListVisitor(),
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );

        backgroundPanel.add(logoutButton);
        backgroundPanel.add(userPhoto);
        backgroundPanel.add(usernameLabel);
        backgroundPanel.add(hireEmployeeButton);
        backgroundPanel.add(manageVisitorsButton);

        JPanel logPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                ImageIcon backgroundImage = new ImageIcon("src/resources/backgrounds/log-bg.png");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        logPanel.setLayout(new BorderLayout());
        logPanel.setBounds(47, 435, 262, 142);

        logPanel.setOpaque(false);

        logTextArea = new JTextArea();
        logTextArea.setOpaque(false);
        logTextArea.setEditable(false);
        logTextArea.setFont(CustomFont.useCustomFont(12f));
        Color logTextColor = new Color(216, 194, 166);
        logTextArea.setForeground(logTextColor);
        logTextArea.setLineWrap(true);
        logTextArea.setWrapStyleWord(true);
        logTextArea.setMargin(new Insets(5, 5, 5, 5));
        logTextArea.setBorder(null);

        JScrollPane scrollPane = new JScrollPane(logTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
        scrollPane.setBounds(0, 0, logPanel.getWidth(), logPanel.getHeight());

        logPanel.add(scrollPane, BorderLayout.CENTER);

        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        ImageIcon visitorsViewButton = new ImageIcon("src/resources/utils/visitorsButton.png");
        JLabel visitorsViewLabel = new JLabel(visitorsViewButton);
        visitorsViewLabel.setBounds(135, 565, 74, 32);
        backgroundPanel.add(visitorsViewLabel);

        backgroundPanel.add(logPanel);

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

        Timer timer = new Timer(10000, evt -> {
            if (this.isVisible()) {
                zooController.addVisitor(zooController.getZooByUser(), logTextArea);

                int newCash = zooRepository.getCurrentCash(currentUser.getId());
                System.out.println(newCash);

                String cashText = "$ " + newCash;
                cashLabel.setText(cashText);

                FontMetrics newMetrics = cashLabel.getFontMetrics(cashLabel.getFont());
                int newTextWidth = newMetrics.stringWidth(cashText);

                int newLogoutButtonX = 650;
                int newLogoutButtonWidth = 103;
                int newLabelX = newLogoutButtonX + (newLogoutButtonWidth / 2) - (newTextWidth / 2);

                cashLabel.setBounds(newLabelX, 143, newTextWidth, 20);
            }
        });
        timer.setRepeats(true);
        timer.start();

    }

    private void logoutAction(){
        ZooSystem.setCurrentZoo(null);
        ZooSystem.setCurrentUser(null);
        this.dispose();

        InitialPanel initialPanel = new InitialPanel();
        initialPanel.setVisible(true);
    }

    public void updateUsername(String newUsername){
        this.currentUser.setUsername(newUsername);
        String upperUsername = newUsername.toUpperCase();

        usernameLabel.setText(upperUsername);
        FontMetrics metrics = usernameLabel.getFontMetrics(usernameLabel.getFont());
        int textWidth = metrics.stringWidth(upperUsername);

        int logoutButtonX = 650;
        int logoutButtonWidth = 103;
        int labelX = logoutButtonX + (logoutButtonWidth / 2) - (textWidth / 2);

        usernameLabel.setBounds(labelX, 115, textWidth, 30);
        usernameLabel.repaint();
    }

    private void openListVisitor(){
        ListVisitorPanel listVisitorPanel = new ListVisitorPanel(this);
        listVisitorPanel.setVisible(true);
        this.setState(JFrame.ICONIFIED);
    }

    public static void main(String[] args) {
        User user = new User("testando10", "1grse81g8541g851g8sr1grsg8s1gs51g5s");
        new MainMenu(user);
    }
}
