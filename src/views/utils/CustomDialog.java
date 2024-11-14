package views.utils;

import javax.swing.*;
import java.awt.*;

public class CustomDialog extends JFrame {

    public CustomDialog(String message, int messageType) {
        setUndecorated(true);
        setSize(320, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ImageBackgroundPanel backgroundPanel = new ImageBackgroundPanel("src/resources/backgrounds/dialog-bg.png");
        backgroundPanel.setLayout(null);

        JLabel statusLabel = new JLabel();
        if (messageType == JOptionPane.INFORMATION_MESSAGE) {
            statusLabel.setText("Success!");
            Color fontColor = new Color(218, 195, 167);
            statusLabel.setForeground(fontColor);
            statusLabel.setFont(new Font("Arial", Font.BOLD, 14));
            statusLabel.setBounds(116, 30, 200, 30);
        } else if (messageType == JOptionPane.ERROR_MESSAGE) {
            statusLabel.setText("Error!");
            Color fontColor = new Color(218, 195, 167);
            statusLabel.setForeground(fontColor);
            statusLabel.setFont(new Font("Arial", Font.BOLD, 14));
            statusLabel.setBounds(128, 30, 200, 30);
        } else {
            statusLabel.setText("Info");
            Color fontColor = new Color(218, 195, 167);
            statusLabel.setForeground(fontColor);
        }


        JLabel messageLabel = new JLabel(message, SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 12));
        Color fontColor = new Color(218, 195, 167);
        messageLabel.setForeground(fontColor);
        messageLabel.setBounds(50, 53, 200, 30);

        backgroundPanel.add(messageLabel, BorderLayout.CENTER);

        CustomButton okButton = new CustomButton(
                "src/resources/buttons/okDialogButton.png",
                100,
                85,
                100,
                30,
                e -> dispose(),
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );

        backgroundPanel.add(statusLabel);
        backgroundPanel.add(messageLabel);
        backgroundPanel.add(okButton);

        add(backgroundPanel);
        setVisible(true);
    }

    public static void showMessage(String message, int messageType) {
        new CustomDialog(message, messageType);
    }

    public static void main(String[] args) {
        CustomDialog.showMessage("User registered successfully", JOptionPane.INFORMATION_MESSAGE);
    }
}
