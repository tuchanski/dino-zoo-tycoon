package views.panels;

import controllers.UserController;
import exceptions.EntityAlreadyRegisteredException;
import exceptions.EntityNotFoundException;
import models.User;
import views.utils.CustomButton;
import views.utils.CustomDialog;
import views.utils.CustomFont;
import views.utils.ImageBackgroundPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserSettings extends JFrame {
    private int mouseX, mouseY;
    private JTextField usernameField;
    private JTextField passwordField;
    private User currentUser;
    private MainMenu mainMenu;
    private UserController userController;

    public UserSettings(JFrame parentFrame, User currentUser, MainMenu mainMenu) {
        this.userController = new UserController();
        this.currentUser = currentUser;
        this.mainMenu = mainMenu;

        setUndecorated(true);
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parentFrame);

        ImageBackgroundPanel backgroundPanel = new ImageBackgroundPanel("src/resources/backgrounds/small-bg-settings.png");
        backgroundPanel.setLayout(null);

        // Layout da janela
        CustomButton closeButton = new CustomButton("src/resources/buttons/closeButtonSmall.png", 335, 14, 52, 53, e -> dispose(), Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        CustomButton minimizeButton = new CustomButton("src/resources/buttons/minimizeButtonSmall.png", 280, 14, 52, 53, e -> setState(JFrame.ICONIFIED), Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        backgroundPanel.add(closeButton);
        backgroundPanel.add(minimizeButton);

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

        CustomButton userPhoto = new CustomButton("src/resources/buttons/fakeUser.png", 158, 125, 85, 86, e -> this.setState(JFrame.ICONIFIED), Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backgroundPanel.add(userPhoto);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 400, 500);
        backgroundPanel.add(layeredPane);

        // Username
        configFieldWithLabel(layeredPane, "USERNAME:", 153, 210, 100, 30, 14);

        ImageIcon usernameFieldBg = new ImageIcon("src/resources/utils/customField.png");
        JLabel usernameFieldBackground = new JLabel(usernameFieldBg);
        usernameFieldBackground.setBounds(65, 240, 266, 47);
        layeredPane.add(usernameFieldBackground, JLayeredPane.DEFAULT_LAYER);

        usernameField = transparentField(68, 240, 262, 47, 12);
        usernameField.setText(currentUser.getUsername());
        layeredPane.add(usernameField, JLayeredPane.PALETTE_LAYER);

        // Password
        configFieldWithLabel(layeredPane, "PASSWORD:", 153, 290, 120, 30, 14);

        ImageIcon passwordFieldBg = new ImageIcon("src/resources/utils/customField.png");
        JLabel passwordFieldBackground = new JLabel(passwordFieldBg);
        passwordFieldBackground.setBounds(65, 320, 266, 47);
        layeredPane.add(passwordFieldBackground, JLayeredPane.DEFAULT_LAYER);

        passwordField = transparentField(68, 320, 262, 47, 12);
        passwordField.setText(currentUser.getPassword());
        layeredPane.add(passwordField, JLayeredPane.PALETTE_LAYER);

        CustomButton updateButton = new CustomButton("src/resources/buttons/updateButton.png", 120, 385, 165, 62, e -> updateUser(), Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        layeredPane.add(updateButton, JLayeredPane.PALETTE_LAYER);

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

    private void updateUser() {
        String newUsername = usernameField.getText();
        String newPassword = passwordField.getText();

        if (newUsername.isEmpty() || newPassword.isEmpty()) {
            CustomDialog.showMessage("Username or password cannot be empty", JOptionPane.ERROR_MESSAGE);
            return;
        }

        userController.updateUserById(currentUser.getId().intValue(), newUsername, newPassword);
        currentUser.setUsername(newUsername);
        mainMenu.updateUsername(newUsername);

        CustomDialog.showMessage("Update successfully!", JOptionPane.INFORMATION_MESSAGE);


    }
}
