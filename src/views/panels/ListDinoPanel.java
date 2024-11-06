package views.panels;

import controllers.DinosaurController;
import exceptions.EntityNotFoundException;
import models.Dinosaur;
import views.utils.CustomButton;
import views.utils.ImageBackgroundPanel;
import views.utils.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListDinoPanel extends JFrame {
    private int mouseX, mouseY;
    private DinosaurController dinosaurController;

    public ListDinoPanel(JFrame parentFrame) {
        dinosaurController = new DinosaurController();
        setUndecorated(true);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parentFrame);

        ImageBackgroundPanel backgroundPanel = new ImageBackgroundPanel("src/resources/backgrounds/manage-bg.png");
        backgroundPanel.setLayout(null);

        ImageIcon imageIcon = new ImageIcon("src/resources/utils/watermark.png");
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(706, 550, imageIcon.getIconWidth(), imageIcon.getIconHeight());
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

        // Close and minimize buttons
        CustomButton closeButton = new CustomButton(
                "src/resources/buttons/closeButtonSmall.png",
                735,
                14,
                52,
                53,
                e -> dispose(),
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );

        CustomButton minimizeButton = new CustomButton(
                "src/resources/buttons/minimizeButtonSmall.png",
                680,
                14,
                52,
                53,
                e -> setState(JFrame.ICONIFIED),
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );

        backgroundPanel.add(closeButton);
        backgroundPanel.add(minimizeButton);

        // Only 3 per column
        JPanel dinosaursPanel = new JPanel();
        dinosaursPanel.setLayout(new GridLayout(0, 3, 20, 20));
        dinosaursPanel.setOpaque(false);

        addDinosaurCards(dinosaursPanel);

        JScrollPane scrollPane = new JScrollPane(dinosaursPanel);
        scrollPane.setBounds(50, 100, 700, 400);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);
        backgroundPanel.add(scrollPane);

        CustomButton refreshButton = new CustomButton(
                "src/resources/buttons/refreshButton.png",
                330,
                520,
                165,
                62,
                e -> refreshDinosaurCards(dinosaursPanel),
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        backgroundPanel.add(refreshButton);

        add(backgroundPanel);
        setVisible(true);
    }

    private void addDinosaurCards(JPanel dinosaursPanel) {
        dinosaursPanel.removeAll();

        List<Dinosaur> dinosaurs = dinosaurController.getDinosaurs();
        if (dinosaurs.isEmpty()) {
            JLabel emptyLabel = new JLabel("No dinosaurs found.");
            dinosaursPanel.add(emptyLabel);
        }

        for (Dinosaur dinosaur : dinosaurs) {
            JPanel cardPanel = new JPanel();
            cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
            cardPanel.setPreferredSize(new Dimension(180, 220));
            cardPanel.setBackground(new Color(94, 73, 61));
            cardPanel.setBorder(new RoundedBorder(new Color(37, 25, 20), 2, 20, 20));

            String imagePath;
            if ("Carnivore".equalsIgnoreCase(dinosaur.getSpecies().getDiet())) {
                imagePath = "src/resources/dinosaurs/carnivore.png";
            } else if ("Herbivore".equalsIgnoreCase(dinosaur.getSpecies().getDiet())) {
                imagePath = "src/resources/dinosaurs/herbivore.png";
            } else {
                imagePath = "src/resources/dinosaurs/omnivore.png";
            }

            // Img
            JLabel imageLabel = new JLabel(new ImageIcon(imagePath));
            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            cardPanel.add(imageLabel);

            // Species
            JLabel speciesLabel = new JLabel(dinosaur.getSpecies().name());
            speciesLabel.setFont(new Font("Arial", Font.BOLD, 16));
            Color fontColorSpecies = new Color(228, 201, 171);
            speciesLabel.setForeground(fontColorSpecies);
            speciesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            cardPanel.add(speciesLabel);

            // Diet
            JLabel dietLabel = new JLabel(dinosaur.getSpecies().getDiet().toUpperCase());
            dietLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            Color fontColorDiet = new Color(228, 201, 171);
            dietLabel.setForeground(fontColorDiet);
            dietLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            cardPanel.add(dietLabel);



            JButton deleteButton = new JButton("Delete");
            deleteButton.setFont(new Font("Arial", Font.PLAIN, 12));
            deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            deleteButton.addActionListener(e -> {
                try {
                    dinosaurController.deleteDinosaurById(dinosaur.getId().intValue());
                    refreshDinosaurCards(dinosaursPanel);
                } catch (EntityNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Dinosaur not foudn.");
                }
            });
            cardPanel.add(deleteButton);

            JButton updateButton = new JButton("Update");
            updateButton.setFont(new Font("Arial", Font.PLAIN, 12));
            updateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            updateButton.addActionListener(e -> {
                new UpdateDinoPanel(this, dinosaur);
            });
            cardPanel.add(updateButton);

            dinosaursPanel.add(cardPanel);
        }

        dinosaursPanel.revalidate();
        dinosaursPanel.repaint();
    }

    private void refreshDinosaurCards(JPanel dinosaursPanel) {
        addDinosaurCards(dinosaursPanel);
    }
}
