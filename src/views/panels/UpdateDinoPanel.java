package views.panels;

import controllers.DinosaurController;
import models.Dinosaur;
import models.enums.DinosaurSpecies;
import views.utils.CustomButton;
import views.utils.ImageBackgroundPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class UpdateDinoPanel extends JFrame {
    private int mouseX, mouseY;
    private JComboBox<DinosaurSpecies> speciesComboBox;
    private DinosaurController dinosaurController;
    private Dinosaur dinosaurToUpdate;

    public UpdateDinoPanel(JFrame parentFrame, Dinosaur dinosaurToUpdate) {
        this.dinosaurToUpdate = dinosaurToUpdate;
        //dinosaurController = new DinosaurController();
        setUndecorated(true);
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parentFrame);

        ImageBackgroundPanel backgroundPanel = new ImageBackgroundPanel("src/resources/backgrounds/small-bg-add.png");
        backgroundPanel.setLayout(null);

        ImageIcon imageIcon = new ImageIcon("src/resources/utils/watermark.png");
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(306, 450, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        backgroundPanel.add(imageLabel);

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
                e -> dispose(),
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );

        CustomButton minimizeButton = new CustomButton(
                "src/resources/buttons/minimizeButtonSmall.png",
                280,
                14,
                52,
                53,
                e -> setState(JFrame.ICONIFIED),
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );

        backgroundPanel.add(closeButton);
        backgroundPanel.add(minimizeButton);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 400, 500);
        backgroundPanel.add(layeredPane);

        configFieldWithLabel(layeredPane, "NEW SPECIES:", 120, 220, 160, 30, 16);

        speciesComboBox = new JComboBox<>(DinosaurSpecies.values());
        speciesComboBox.setBounds(76, 250, 262, 47);
        speciesComboBox.setFont(new Font("Arial", Font.BOLD, 12));
        speciesComboBox.setBackground(new Color(255, 255, 255, 100));
        layeredPane.add(speciesComboBox, JLayeredPane.PALETTE_LAYER);

        CustomButton submitButton = new CustomButton(
                "src/resources/buttons/submitButton.png",
                120,
                330,
                165,
                62,
                e -> updateDino(),
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );

        layeredPane.add(submitButton, JLayeredPane.PALETTE_LAYER);

        add(backgroundPanel);
        setVisible(true);
    }

    private void configFieldWithLabel(JLayeredPane panel, String labelText, int x, int y, int width, int height, int fontSize) {
        JLabel label = new JLabel(labelText);
        Color fontColor = new Color(218, 195, 167);
        label.setForeground(fontColor);
        label.setFont(new Font("Arial", Font.BOLD, fontSize));
        label.setBounds(x, y, width, height);
        panel.add(label, JLayeredPane.PALETTE_LAYER);
    }

    private void updateDino() {
        DinosaurSpecies species = (DinosaurSpecies) speciesComboBox.getSelectedItem();

        if (species != null) {
            try {
                dinosaurController.updateDinosaurById(dinosaurToUpdate.getId().intValue(), species.name());
                JOptionPane.showMessageDialog(this, "Dinosaur updated!");
                dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error updating dinosaur.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a species.");
        }
    }
}
