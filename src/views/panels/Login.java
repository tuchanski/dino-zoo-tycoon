package views.panels;

import controllers.UserController;
import controllers.ZooController;
import models.User;
import services.ZooSystem;
import views.utils.CustomButton;
import views.utils.CustomDialog;
import views.utils.CustomFont;
import views.utils.ImageBackgroundPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Authentication to the project, filled with user registered infos.
// After correct authentication user is redirected to MainMenu (Dashboard).

public class Login extends JFrame {
    private int mouseX, mouseY;
    private JTextField usernameField;
    private JPasswordField passwordField;

    private UserController userController;
    private ZooController zooController;

    public Login(JFrame parentFrame){
        userController = new UserController();

        setUndecorated(true);
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        ImageBackgroundPanel backgroundPanel = new ImageBackgroundPanel("src/resources/backgrounds/small-bg-login.png");
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

        CustomButton backButton = new CustomButton(
                "src/resources/buttons/backButtonSmall.png",
                20,
                14,
                52,
                53,
                e -> {
                    if (parentFrame != null) {
                        parentFrame.setVisible(true);
                    }
                    dispose();
                },
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );

        backgroundPanel.add(closeButton);
        backgroundPanel.add(minimizeButton);
        backgroundPanel.add(backButton);

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

        // JLayeredPane - organize layers
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 400, 500);
        backgroundPanel.add(layeredPane);

        // Username
        configFieldWithLabel(layeredPane, "USERNAME:", 153, 140, 100, 30, 14);

        ImageIcon usernameFieldBg = new ImageIcon("src/resources/utils/customField.png");
        JLabel usernameFieldBackground = new JLabel(usernameFieldBg);
        usernameFieldBackground.setBounds(65, 170, 266, 47);
        layeredPane.add(usernameFieldBackground, JLayeredPane.DEFAULT_LAYER);

        usernameField = transparentField(68, 170, 262, 47, 12);
        layeredPane.add(usernameField, JLayeredPane.PALETTE_LAYER);

        // Password
        configFieldWithLabel(layeredPane, "PASSWORD:", 153, 220, 120, 30, 14);

        ImageIcon passwordFieldBg = new ImageIcon("src/resources/utils/customField.png");
        JLabel passwordFieldBackground = new JLabel(passwordFieldBg);
        passwordFieldBackground.setBounds(65, 250, 266, 47);
        layeredPane.add(passwordFieldBackground, JLayeredPane.DEFAULT_LAYER);

        passwordField = transparentPasswordField(68, 250, 262, 47, 12);
        layeredPane.add(passwordField, JLayeredPane.PALETTE_LAYER);

        CustomButton loginButton = new CustomButton(
                "src/resources/buttons/loginButton.png",
                116,
                320,
                165,
                62,
                e -> login(),
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );
        layeredPane.add(loginButton, JLayeredPane.PALETTE_LAYER);

        add(backgroundPanel);
        setVisible(true);
    }

    private JTextField transparentField(int x, int y, int width, int height, int fontSize) {
        JTextField field = new JTextField();
        field.setOpaque(false);
        Color fontColor = new Color(218, 195, 167);
        field.setForeground(fontColor);
        field.setBorder(null);
        field.setBounds(x, y, width, height);
        field.setFont(CustomFont.useCustomFont(fontSize));
        field.setHorizontalAlignment(SwingConstants.CENTER);
        return field;
    }

    private JPasswordField transparentPasswordField(int x, int y, int width, int height, int fontSize) {
        JPasswordField field = new JPasswordField();
        field.setOpaque(false);
        Color fontColor = new Color(218, 195, 167);
        field.setForeground(fontColor);
        field.setBorder(null);
        field.setBounds(x, y, width, height);
        field.setFont(CustomFont.useCustomFont(fontSize));
        field.setHorizontalAlignment(SwingConstants.CENTER);
        return field;
    }

    private void configFieldWithLabel(JLayeredPane panel, String labelText, int x, int y, int width, int height, int fontSize) {
        JLabel label = new JLabel(labelText);
        Color fontColor = new Color(218, 195, 167);
        label.setForeground(fontColor);
        label.setFont(CustomFont.useCustomFont(fontSize));
        label.setBounds(x, y, width, height);
        panel.add(label, JLayeredPane.PALETTE_LAYER);
    }

    private void login(){
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            CustomDialog.showMessage("Username or password cannot be empty", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            User user = userController.getUserByUsername(username);

            if (user != null && user.getPassword().equals(ZooSystem.hashPassword(password))) {
                ZooSystem.setCurrentUser(user);
                zooController = new ZooController(user);
                ZooSystem.setCurrentZoo(zooController.getZooByUser());
                CustomDialog.showMessage("Login successful!", JOptionPane.INFORMATION_MESSAGE);

                Timer timer = new Timer(900, evt -> {
                    MainMenu mainMenu = new MainMenu(user);
                    mainMenu.setVisible(true);
                    dispose();
                });
                timer.setRepeats(false);
                timer.start();

            } else {
                CustomDialog.showMessage("Invalid username or password", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Login(null);
    }


}
