package views.panels;

import controllers.EmployeeController;
import models.Employee;
import services.ZooSystem;
import views.utils.CustomButton;
import views.utils.CustomFont;
import views.utils.ImageBackgroundPanel;
import views.utils.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// List all employees hired at MainMenu.
// Current user can remove/unemploy any employee.

public class ListEmployeePanel extends JFrame {
    private int mouseX, mouseY;
    private EmployeeController employeeController;

    public ListEmployeePanel(JFrame parentFrame) {

        employeeController = new EmployeeController(ZooSystem.getCurrentZoo());
        System.out.println("Current zoo: " + ZooSystem.getCurrentZoo());

        setUndecorated(true);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parentFrame);

        ImageBackgroundPanel backgroundPanel = new ImageBackgroundPanel("src/resources/backgrounds/employees-manage-bg.png");
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

        JPanel employeesPanel = new JPanel();
        employeesPanel.setLayout(new GridLayout(0, 3, 20, 20));
        employeesPanel.setOpaque(false);

        addEmployeeCards(employeesPanel);

        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        wrapperPanel.setOpaque(false);
        wrapperPanel.add(employeesPanel);

        JScrollPane scrollPane = new JScrollPane(wrapperPanel);
        scrollPane.setBounds(50, 100, 700, 400);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        backgroundPanel.add(scrollPane);

        CustomButton refreshButton = new CustomButton(
                "src/resources/buttons/refreshButton.png",
                330,
                520,
                165,
                62,
                e -> refreshEmployeeCards(employeesPanel),
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );

        backgroundPanel.add(refreshButton);

        add(backgroundPanel);
        setVisible(true);
    }

    private void addEmployeeCards(JPanel employeesPanel) {
        employeesPanel.removeAll();

        List<Employee> employees = employeeController.getEmployees();
        if (employees.isEmpty()) {
            JLabel emptyLabel = new JLabel("No employees found.");
            employeesPanel.add(emptyLabel);
        }

        for (Employee employee : employees) {
            JPanel cardPanel = new JPanel();
            cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
            cardPanel.setPreferredSize(new Dimension(180, 220));
            cardPanel.setBackground(new Color(94, 73, 61));
            cardPanel.setBorder(new RoundedBorder(new Color(37, 25, 20), 2, 20, 20));

            // Img
            JLabel imageLabel = new JLabel(new ImageIcon("src/resources/employees/employee.png"));
            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            cardPanel.add(imageLabel);

            // Name
            JLabel nameLabel = new JLabel(employee.getName());
            nameLabel.setFont(CustomFont.useCustomFont(12f));
            nameLabel.setForeground(new Color(228, 201, 171));
            nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            cardPanel.add(nameLabel);

            JLabel roleLabel = new JLabel(employee.getRole().toUpperCase());
            roleLabel.setFont(CustomFont.useCustomFont(10f));
            roleLabel.setForeground(new Color(228, 201, 171));
            roleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            cardPanel.add(roleLabel);

            // Botões
            CustomButton deleteButton = new CustomButton(
                    "src/resources/buttons/deleteButtonSmall.png",
                    0,
                    0,
                    52,
                    53,
                    e -> {
                        employeeController.deleteEmployeeById(employee.getId().intValue());
                        refreshEmployeeCards(employeesPanel);
                    },
                    Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
            );


            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
            buttonPanel.setOpaque(false);

            buttonPanel.add(deleteButton);

            cardPanel.add(buttonPanel);

            employeesPanel.add(cardPanel);
        }

        employeesPanel.revalidate();
        employeesPanel.repaint();
    }

    private void refreshEmployeeCards(JPanel employeesPanel) {
        addEmployeeCards(employeesPanel);
    }
}
