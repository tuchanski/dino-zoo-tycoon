package views.panels;

import controllers.DinosaurController;
import services.ZooSystem;
import views.utils.CustomButton;
import views.utils.CustomDialog;
import views.utils.CustomFont;
import views.utils.ImageBackgroundPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import models.enums.DinosaurSpecies;

public class AddDinoPanel extends JFrame {
    private int mouseX, mouseY;

    private JComboBox<DinosaurSpecies> speciesComboBox;
    private DinosaurController dinosaurController;

    public AddDinoPanel(JFrame parentFrame) {
        dinosaurController = new DinosaurController(ZooSystem.getCurrentZoo());
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

        // JLayeredPane - organize layers
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 400, 500);
        backgroundPanel.add(layeredPane);

        // Combbox
        configFieldWithLabel(layeredPane, "SPECIES:", 170, 160, 100, 30, 16);

        ImageIcon speciesFieldBg = new ImageIcon("src/resources/utils/customField.png");
        JLabel speciesFieldBackground = new JLabel(speciesFieldBg);
        speciesFieldBackground.setBounds(74, 190, 266, 47);
        layeredPane.add(speciesFieldBackground, JLayeredPane.DEFAULT_LAYER);

        speciesComboBox = new JComboBox<>(DinosaurSpecies.values());
        speciesComboBox.setBounds(76, 190, 262, 47);
        speciesComboBox.setFont(CustomFont.useCustomFont(12f));

        DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
        listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        speciesComboBox.setRenderer(listRenderer);

        layeredPane.add(speciesComboBox, JLayeredPane.PALETTE_LAYER);

        CustomButton submitButton = new CustomButton(
                "src/resources/buttons/submitButton.png",
                120,
                270,
                165,
                62,
                e -> addDino(),
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
        label.setFont(CustomFont.useCustomFont(fontSize));
        label.setBounds(x, y, width, height);
        panel.add(label, JLayeredPane.PALETTE_LAYER);
    }

    private void addDino(){
        DinosaurSpecies species = (DinosaurSpecies) speciesComboBox.getSelectedItem();

        if (species != null){
            try{
                dinosaurController.createDinosaur(species.name());
                CustomDialog.showMessage("Dinosaur added!", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e){
                CustomDialog.showMessage("Species not found", JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
            CustomDialog.showMessage("Please select a species", JOptionPane.ERROR_MESSAGE);
        }
    }
}