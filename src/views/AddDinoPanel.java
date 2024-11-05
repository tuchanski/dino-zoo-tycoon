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

        ImageBackgroundPanel backgroundPanel = new ImageBackgroundPanel("src/resources/images/small-bg-add.png");
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
                e -> parentFrame.setState(JFrame.ICONIFIED),
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );

        backgroundPanel.add(closeButton);
        backgroundPanel.add(minimizeButton);

        // JLayeredPane - organize layers
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 400, 500);
        backgroundPanel.add(layeredPane);

        // Name
        configFieldWithLabel(layeredPane, "NAME:", 175, 110, 100, 30, 16);

        ImageIcon nameFieldBg = new ImageIcon("src/resources/images/teste.png");
        JLabel nameFieldBackground = new JLabel(nameFieldBg);
        nameFieldBackground.setBounds(65, 140, 266, 47);
        layeredPane.add(nameFieldBackground, JLayeredPane.DEFAULT_LAYER);

        nameField = transparentField(68, 140, 262, 47, 12);
        layeredPane.add(nameField, JLayeredPane.PALETTE_LAYER);

        // Type
        configFieldWithLabel(layeredPane, "TYPE:", 175, 190, 100, 30, 16);

        ImageIcon typeFieldBg = new ImageIcon("src/resources/images/teste.png");
        JLabel typeFieldBackground = new JLabel(typeFieldBg);
        typeFieldBackground.setBounds(65, 220, 266, 47);
        layeredPane.add(typeFieldBackground, JLayeredPane.DEFAULT_LAYER);

        typeField = transparentField(68, 220, 262, 47, 12);
        layeredPane.add(typeField, JLayeredPane.PALETTE_LAYER);

        // Age
        configFieldWithLabel(layeredPane, "AGE:", 178, 270, 100, 30, 16);

        ImageIcon ageFieldBg = new ImageIcon("src/resources/images/teste.png");
        JLabel ageFieldBackground = new JLabel(ageFieldBg);
        ageFieldBackground.setBounds(65, 300, 266, 47);
        layeredPane.add(ageFieldBackground, JLayeredPane.DEFAULT_LAYER);

        ageField = transparentField(68, 300, 262, 47, 12);
        layeredPane.add(ageField, JLayeredPane.PALETTE_LAYER);

        CustomButton submitButton = new CustomButton(
                "src/resources/buttons/submitButton.png",
                116,
                370,
                165,
                62,
                e -> parentFrame.setState(JFrame.ICONIFIED),
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );

        layeredPane.add(submitButton, JLayeredPane.PALETTE_LAYER);

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
        field.setFont(new Font("Arial", Font.BOLD, fontSize));
        return field;
    }

    private void configFieldWithLabel(JLayeredPane panel, String labelText, int x, int y, int width, int height, int fontSize) {
        JLabel label = new JLabel(labelText);
        Color fontColor = new Color(218, 195, 167);
        label.setForeground(fontColor);
        label.setFont(new Font("Arial", Font.BOLD, fontSize));
        label.setBounds(x, y, width, height);
        panel.add(label, JLayeredPane.PALETTE_LAYER);
    }
}
