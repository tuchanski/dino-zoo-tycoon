package views.panels;

import controllers.ParkEventController;
import controllers.ZooController;
import exceptions.EntityNotFoundException;
import exceptions.NotEnoughMoneyException;
import models.enums.ParkEvent;
import repositories.ZooRepositoryImpl;
import services.ZooSystem;
import views.utils.*;
import java.util.List;

import controllers.UserController;
import models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// MainMenu (Dashboard).
// Current user can manage all zoo from here.

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

        CustomButton parkEventButton = new CustomButton(
                "",
                425,
                100 ,
                76,
                72,
                e -> {
                    showParkEventMessage();
                },
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );

        backgroundPanel.add(parkEventButton);

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

        ZooController zooController = new ZooController(currentUser);
        String zooName = zooController.getZooName();

        JLabel welcomeLabel = new JLabel("WELCOME TO " + zooName);

        Color welcomeFontColor = new Color(37, 27, 20);
        welcomeLabel.setFont(CustomFont.useCustomFont(14f));
        welcomeLabel.setForeground(welcomeFontColor);
        welcomeLabel.setBounds(553, 200, 250, 41);

        backgroundPanel.add(welcomeLabel);


        ImageIcon employeesViewButton = new ImageIcon("src/resources/utils/employeeManageButton.png");
        JLabel employeeViewLabel = new JLabel(employeesViewButton);
        employeeViewLabel.setBounds(610, 235, 102, 41);
        backgroundPanel.add(employeeViewLabel);

        CustomButton hireEmployeeButton = new CustomButton(
                "src/resources/buttons/hireEmployeeButton.png",
                583,
                259 ,
                153,
                58,
                e -> {
                    hireEmployeeAction();
                },
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );

        CustomButton manageEmployeesButton = new CustomButton(
                "src/resources/buttons/manageVisitorsButton.png",
                583,
                315,
                153,
                58,
                e -> openListEmployee(),
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );

        ImageIcon visitorsViewsButton = new ImageIcon("src/resources/utils/visitorsManageButton.png");
        JLabel visitorsViewsLabel = new JLabel(visitorsViewsButton);
        visitorsViewsLabel.setBounds(610, 376, 102, 41);
        backgroundPanel.add(visitorsViewsLabel);

        CustomButton manageVisitorsButton = new CustomButton(
                "src/resources/buttons/manageVisitorsButton.png",
                583,
                399,
                153,
                58,
                e -> openListVisitor(),
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );

        ImageIcon foodViewButton = new ImageIcon("src/resources/utils/foodButton.png");
        JLabel foodViewLabel = new JLabel(foodViewButton);
        foodViewLabel.setBounds(610, 462, 102, 41);
        backgroundPanel.add(foodViewLabel);

        CustomButton buyFoodButton = new CustomButton(
                "src/resources/buttons/buyButton.png",
                583,
                485,
                153,
                58,
                e -> openBuyFood(),
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );

        backgroundPanel.add(logoutButton);
        backgroundPanel.add(userPhoto);
        backgroundPanel.add(usernameLabel);
        backgroundPanel.add(hireEmployeeButton);
        backgroundPanel.add(manageVisitorsButton);
        backgroundPanel.add(manageEmployeesButton);
        backgroundPanel.add(buyFoodButton);

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

        ImageIcon generalViewLblButton = new ImageIcon("src/resources/utils/generalCountingLbl.png");
        JLabel generalViewLblLabel = new JLabel(generalViewLblButton);
        generalViewLblLabel.setBounds(365, 424, 114, 45);
        backgroundPanel.add(generalViewLblLabel);

        int totalVisitors = 100;
        int totalDinosaurs = 100;
        int totalEmployees = 100;
        int totalEarnings = 100;

        JLabel visitorsLabel = new JLabel("Visitors: " + totalVisitors);
        JLabel dinosaursLabel = new JLabel("Dinosaurs: " + totalDinosaurs);
        JLabel employeesLabel = new JLabel("Employees: " + totalEmployees);
        JLabel earningsLabel = new JLabel("Earnings: $" + totalEarnings);

        Font infoFont = CustomFont.useCustomFont(14f);
        Color infoColor = new Color(37, 25, 20);

        visitorsLabel.setFont(infoFont);
        visitorsLabel.setForeground(infoColor);
        dinosaursLabel.setFont(infoFont);
        dinosaursLabel.setForeground(infoColor);
        employeesLabel.setFont(infoFont);
        employeesLabel.setForeground(infoColor);
        earningsLabel.setFont(infoFont);
        earningsLabel.setForeground(infoColor);

        int centerX = generalViewLblLabel.getX() + (generalViewLblLabel.getWidth() / 2);

        int labelY = 463;
        int spacingY = 20;

        for (JLabel label : new JLabel[]{visitorsLabel, dinosaursLabel, employeesLabel, earningsLabel}) {
            FontMetrics metricss = label.getFontMetrics(label.getFont());
            int labelWidth = metricss.stringWidth(label.getText());
            int labelXx = centerX - (labelWidth / 2);

            label.setBounds(labelXx, labelY, labelWidth, 30);
            backgroundPanel.add(label);

            labelY += spacingY;
        }

        ImageIcon generalViewButton = new ImageIcon("src/resources/utils/generalCountingButton.png");
        JLabel generalViewLabel = new JLabel(generalViewButton);
        generalViewLabel.setBounds(320, 435, 207, 148);
        backgroundPanel.add(generalViewLabel);


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

    private void openListEmployee(){
        ListEmployeePanel listEmployeePanel = new ListEmployeePanel(this);
        listEmployeePanel.setVisible(true);
        this.setState(JFrame.ICONIFIED);
    }
    private void openBuyFood(){
        BuyFoodPanel buyFood = new BuyFoodPanel(this);
        buyFood.setVisible(true);
        this.setState(JFrame.ICONIFIED);
    }

    private void hireEmployeeAction() {
        try {
            int currentCash = zooRepository.getCurrentCash(ZooSystem.getCurrentZoo().getZooId());

            zooRepository.contractNewEmployee(ZooSystem.getCurrentZoo().getZooId());

            CustomDialog.showMessage("Employee successfully hired!", JOptionPane.INFORMATION_MESSAGE);

        } catch (NotEnoughMoneyException e) {
            CustomDialog.showMessage("Not enough balance.", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            CustomDialog.showMessage("Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showParkEventMessage() {
        ParkEventController parkEventController = new ParkEventController();
        List<ParkEvent> events = parkEventController.getAllParkEvents();

        if (!events.isEmpty()) {
            ParkEvent event = events.get(0);
            String eventDescription = event.getDescription();

            CustomDialog.showMessage(eventDescription + " started", JOptionPane.INFORMATION_MESSAGE);
        } else {
            CustomDialog.showMessage("No events found.", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        User user = new User("testando10", "1grse81g8541g851g8sr1grsg8s1gs51g5s");
        new MainMenu(user);
    }
}
