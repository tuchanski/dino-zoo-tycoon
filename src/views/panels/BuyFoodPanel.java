package views.panels;

import controllers.FoodController;
import controllers.FoodStockController;
import controllers.ZooController;
import models.Food;
import services.ZooSystem;
import views.utils.CustomButton;
import views.utils.CustomDialog;
import views.utils.CustomFont;
import views.utils.ImageBackgroundPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import models.enums.FoodType;

public class BuyFoodPanel extends JFrame {
    private int mouseX, mouseY;

    private JComboBox<FoodType> foodComboBox;
    private ZooController zooController;
    private FoodController foodController;
    private FoodStockController foodStockController;

    public BuyFoodPanel(JFrame parentFrame) {
        foodController = new FoodController();
        foodStockController = new FoodStockController(ZooSystem.getCurrentZoo());
        zooController = new ZooController(ZooSystem.getCurrentUser());

        setUndecorated(true);
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parentFrame);

        ImageBackgroundPanel backgroundPanel = new ImageBackgroundPanel("src/resources/backgrounds/small-bg.png");
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
        configFieldWithLabel(layeredPane, "FOOD TYPE:", 150, 160, 200, 30, 16);

        ImageIcon foodFieldBg = new ImageIcon("src/resources/utils/customField.png");
        JLabel foodFieldBackground = new JLabel(foodFieldBg);
        foodFieldBackground.setBounds(74, 190, 266, 47);
        layeredPane.add(foodFieldBackground, JLayeredPane.DEFAULT_LAYER);

        foodComboBox = new JComboBox<>(FoodType.values());
        foodComboBox.setBounds(76, 190, 262, 47);
        foodComboBox.setFont(CustomFont.useCustomFont(12f));

        DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
        listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        foodComboBox.setRenderer(listRenderer);

        layeredPane.add(foodComboBox, JLayeredPane.PALETTE_LAYER);

        CustomButton submitButton = new CustomButton(
                "src/resources/buttons/submitButton.png",
                120,
                270,
                165,
                62,
                e -> buyFood(),
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

    private void buyFood() {

        List<Food> foods = foodController.getFoods();
        FoodType foodType = (FoodType) foodComboBox.getSelectedItem();

        if (foodType != null){
            try {
                Food selectedFood = foods.stream().filter(food -> food.getType() == foodType)
                        .findFirst()
                        .orElse(null);
                if (selectedFood != null) {
                    long id = selectedFood.getId();
                    foodStockController.addFood((int) id, 1);
                    CustomDialog.showMessage("Food purchased!", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception e) {
                CustomDialog.showMessage("Error purchasing food", JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
            CustomDialog.showMessage("Please select a food type", JOptionPane.ERROR_MESSAGE);
        }
    }
}
