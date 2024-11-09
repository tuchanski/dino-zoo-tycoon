package views.panels;

import controllers.UserController;
import controllers.ZooController;
import exceptions.EntityAlreadyRegisteredException;
import models.Zoo;
import services.ZooSystem;
import views.utils.CustomButton;
import views.utils.CustomDialog;
import views.utils.CustomFont;
import views.utils.ImageBackgroundPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Register extends JFrame {
    private int mouseX, mouseY;
    private JTextField usernameField;
    private JTextField passwordField;
    private JTextField zooNameField;

    private UserController userController;
    private ZooController zooController;

    public Register(JFrame parentFrame){

        userController = new UserController();

        setUndecorated(true);
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        ImageBackgroundPanel backgroundPanel = new ImageBackgroundPanel("src/resources/backgrounds/small-bg-register.png");
        backgroundPanel.setLayout(null);

        ImageIcon imageIcon = new ImageIcon("src/resources/utils/watermark.png");
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(306, 450, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        backgroundPanel.add(imageLabel);

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
        configFieldWithLabel(layeredPane, "USERNAME:", 153, 135, 100, 30, 14);

        ImageIcon usernameFieldBg = new ImageIcon("src/resources/utils/customField.png");
        JLabel usernameFieldBackground = new JLabel(usernameFieldBg);
        usernameFieldBackground.setBounds(65, 165, 266, 47);
        layeredPane.add(usernameFieldBackground, JLayeredPane.DEFAULT_LAYER);

        usernameField = transparentField(68, 165, 262, 47, 12);
        layeredPane.add(usernameField, JLayeredPane.PALETTE_LAYER);

        // Password
        configFieldWithLabel(layeredPane, "PASSWORD:", 153, 215, 120, 30, 14);

        ImageIcon passwordFieldBg = new ImageIcon("src/resources/utils/customField.png");
        JLabel passwordFieldBackground = new JLabel(passwordFieldBg);
        passwordFieldBackground.setBounds(65, 245, 266, 47);
        layeredPane.add(passwordFieldBackground, JLayeredPane.DEFAULT_LAYER);

        passwordField = transparentField(68, 245, 262, 47, 12);
        layeredPane.add(passwordField, JLayeredPane.PALETTE_LAYER);

        configFieldWithLabel(layeredPane, "ZOO NAME:", 153, 290, 120, 30, 14);

        ImageIcon zooNameFieldBg = new ImageIcon("src/resources/utils/customField.png");
        JLabel zooNameFieldBackground = new JLabel(zooNameFieldBg);
        zooNameFieldBackground.setBounds(65, 320, 266, 47);
        layeredPane.add(zooNameFieldBackground, JLayeredPane.DEFAULT_LAYER);

        zooNameField = transparentField(68, 320, 262, 47, 12);
        layeredPane.add(zooNameField, JLayeredPane.PALETTE_LAYER);

        CustomButton registerButton = new CustomButton(
                "src/resources/buttons/registerButton.png",
                116,
                380,
                165,
                62,
                e -> register(),
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );
        layeredPane.add(registerButton, JLayeredPane.PALETTE_LAYER);

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

    private void configFieldWithLabel(JLayeredPane panel, String labelText, int x, int y, int width, int height, int fontSize) {
        JLabel label = new JLabel(labelText);
        Color fontColor = new Color(218, 195, 167);
        label.setForeground(fontColor);
        label.setFont(CustomFont.useCustomFont(fontSize));
        label.setBounds(x, y, width, height);
        panel.add(label, JLayeredPane.PALETTE_LAYER);
    }

    private void register(){
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        String zooName = zooNameField.getText().trim();

        if (username.isEmpty() || password.isEmpty() || zooName.isEmpty()){
            CustomDialog.showMessage("Fill all in fields.", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (username.length() > 10) {
            CustomDialog.showMessage("Maximum 10 characters", JOptionPane.ERROR_MESSAGE);
            return;
        }


        try{
            userController.createUser(username, password);
            ZooSystem.setCurrentUser(userController.getUserByUsername(username));
            zooController = new ZooController(ZooSystem.getCurrentUser());
            zooController.createZoo(zooName);
            ZooSystem.setCurrentUser(null);
            CustomDialog.showMessage("User registered successfully!", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Register(null);
    }

}
