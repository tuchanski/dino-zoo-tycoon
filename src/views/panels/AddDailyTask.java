package views.panels;

import controllers.EmployeeController;
import models.Employee;
import services.ZooSystem;
import views.utils.CustomButton;
import views.utils.CustomDialog;
import views.utils.CustomFont;
import views.utils.ImageBackgroundPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class AddDailyTask extends JFrame {
    private int mouseX, mouseY;

    private JComboBox<Employee> employeeComboBox;
    private EmployeeController employeeController;

    public AddDailyTask(JFrame parentFrame) {
        employeeController = new EmployeeController(ZooSystem.getCurrentZoo());
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

        // Combbox
        configFieldWithLabel(layeredPane, "EMPLOYEE:", 150, 160, 100, 30, 16);

        ImageIcon employeeFieldBg = new ImageIcon("src/resources/utils/customField.png");
        JLabel employeeFieldBackground = new JLabel(employeeFieldBg);
        employeeFieldBackground.setBounds(74, 190, 266, 47);
        layeredPane.add(employeeFieldBackground, JLayeredPane.DEFAULT_LAYER);

        List<Employee> employees = employeeController.getEmployees();
        employeeComboBox = new JComboBox<>(employees.toArray(new Employee[0]));
        employeeComboBox.setBounds(76, 190, 262, 47);
        employeeComboBox.setFont(CustomFont.useCustomFont(12f));

        DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
        listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        employeeComboBox.setRenderer(listRenderer);

        layeredPane.add(employeeComboBox, JLayeredPane.PALETTE_LAYER);

        CustomButton submitButton = new CustomButton(
                "src/resources/buttons/submitButton.png",
                120,
                270,
                165,
                62,
                e -> addDailyTask(),
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

    private void addDailyTask(){
        Employee employee = (Employee) employeeComboBox.getSelectedItem();

        if (employee != null){
            String dailyTask = employeeController.getDailyTask(employee.getId().intValue());
            if (dailyTask != null) {
                CustomDialog.showMessage(dailyTask, JOptionPane.INFORMATION_MESSAGE);
            } else {
                CustomDialog.showMessage("Daily task not found for this employee.", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            CustomDialog.showMessage("Please select an employee", JOptionPane.ERROR_MESSAGE);
        }
    }
}
