package views;

import views.utils.CustomButton;
import views.utils.ImageBackgroundPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddDinoPanel extends JFrame {
    private int mouseX, mouseY;

    private JTextField nameField;
    private JTextField typeField;
    private JTextField ageField;

    public AddDinoPanel(JFrame parentFrame) {
        setUndecorated(true);
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        ImageBackgroundPanel backgroundPanel = new ImageBackgroundPanel("src/resources/images/small-bg.png");
        backgroundPanel.setLayout(null);

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

        CustomButton closeButton = new CustomButton(
                "src/resources/buttons/closeButton.png",
                319,
                14,
                68,
                70,
                e -> System.exit(0),
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );

        CustomButton minimizeButton = new CustomButton(
                "src/resources/buttons/minimizeButton.png",
                250,
                14,
                68,
                70,
                e -> parentFrame.setState(JFrame.ICONIFIED),
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );

        backgroundPanel.add(closeButton);
        backgroundPanel.add(minimizeButton);


        // Name
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBounds(30, 70, 100, 30);
        backgroundPanel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBackground(new Color(255, 255, 255));
        nameField.setForeground(new Color(0, 0, 0));
        nameField.setBorder(null);
        nameField.setBounds(30, 100, 340, 30);
        backgroundPanel.add(nameField);


        // Type
        JLabel typeLabel = new JLabel("Type:");
        typeLabel.setForeground(Color.WHITE);
        typeLabel.setBounds(30, 140, 100, 30);
        backgroundPanel.add(typeLabel);

        typeField = new JTextField();
        typeField.setBackground(new Color(255, 255, 255));
        typeField.setForeground(new Color(0, 0, 0));
        typeField.setBorder(null);
        typeField.setBounds(30, 170, 340, 30);
        backgroundPanel.add(typeField);

        // Age

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setForeground(Color.WHITE);
        ageLabel.setBounds(30, 200, 100, 30);
        backgroundPanel.add(ageLabel);

        ageField = new JTextField();
        ageField.setBackground(new Color(255, 255, 255));
        ageField.setForeground(new Color(0, 0, 0));
        ageField.setBorder(null);
        ageField.setBounds(30, 235, 340, 30);
        backgroundPanel.add(ageField);


        CustomButton submitButton = new CustomButton(
                "src/resources/buttons/addButton.png",
                120,
                300,
                165,
                62,
                e -> parentFrame.setState(JFrame.ICONIFIED),
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );

        backgroundPanel.add(submitButton);

        add(backgroundPanel);
        setVisible(true);
    }
}
