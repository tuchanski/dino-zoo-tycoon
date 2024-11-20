package views.panels;

import controllers.EmployeeController;
import controllers.VisitorController;
import models.Employee;
import models.Visitor;
import services.ZooSystem;
import views.utils.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// List all visitors, you can delete or add a new daily task.

public class ListVisitorPanel extends JFrame {
    private int mouseX, mouseY;
    private VisitorController visitorController;
    private EmployeeController employeeController;

    public ListVisitorPanel(JFrame parentFrame) {

        employeeController = new EmployeeController(ZooSystem.getCurrentZoo());
        visitorController = new VisitorController(ZooSystem.getCurrentZoo());
        System.out.println("Current zoo: " + ZooSystem.getCurrentZoo());

        setUndecorated(true);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parentFrame);

        ImageBackgroundPanel backgroundPanel = new ImageBackgroundPanel("src/resources/backgrounds/visitors-manage-bg.png");
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

        // Only 3 per column
        JPanel visitorsPanel = new JPanel();
        visitorsPanel.setLayout(new GridLayout(0, 3, 20, 20));
        visitorsPanel.setOpaque(false);

        addVisitorCards(visitorsPanel);

        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        wrapperPanel.setOpaque(false);
        wrapperPanel.add(visitorsPanel);

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
                e -> refreshVisitorCards(visitorsPanel),
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        backgroundPanel.add(refreshButton);

        add(backgroundPanel);
        setVisible(true);
    }

    private void addVisitorCards(JPanel visitorsPanel) {
        visitorsPanel.removeAll();

        List<Visitor> visitors = visitorController.getVisitors();
        if (visitors.isEmpty()) {
            JLabel emptyLabel = new JLabel("No visitors found.");
            visitorsPanel.add(emptyLabel);
        }

        for (Visitor visitor : visitors) {
            JPanel cardPanel = new JPanel();
            cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
            cardPanel.setPreferredSize(new Dimension(180, 220));
            cardPanel.setBackground(new Color(94, 73, 61));
            cardPanel.setBorder(new RoundedBorder(new Color(37, 25, 20), 2, 20, 20));

            String imagePath;

            if ("Afonso".equalsIgnoreCase(visitor.getName())) {
                imagePath = "src/resources/visitors/afonso.png";
            } else if ("Guilherme".equalsIgnoreCase(visitor.getName())) {
                imagePath = "src/resources/visitors/guilherme.png";
            } else if ("Luiz".equalsIgnoreCase(visitor.getName())) {
                imagePath = "src/resources/visitors/luiz.png";
            } else if ("Fernanda".equalsIgnoreCase(visitor.getName())) {
                imagePath = "src/resources/visitors/fernanda.png";
            } else if ("Gabriela".equalsIgnoreCase(visitor.getName())) {
                imagePath = "src/resources/visitors/gabriela.png";
            } else if ("Marina".equalsIgnoreCase(visitor.getName())) {
                imagePath = "src/resources/visitors/marina.png";
            } else if ("Rafael".equalsIgnoreCase(visitor.getName())) {
                imagePath = "src/resources/visitors/rafael.png";
            } else if ("Felipe".equalsIgnoreCase(visitor.getName())) {
                imagePath = "src/resources/visitors/felipe.png";
            } else if ("Bruno".equalsIgnoreCase(visitor.getName())) {
                imagePath = "src/resources/visitors/bruno.png";
            } else if ("Alice".equalsIgnoreCase(visitor.getName())) {
                imagePath = "src/resources/visitors/alice.png";
            } else if ("Ana".equalsIgnoreCase(visitor.getName())) {
                imagePath = "src/resources/visitors/ana.png";
            } else if ("Carla".equalsIgnoreCase(visitor.getName())) {
                imagePath = "src/resources/visitors/carla.png";
            } else if ("Arion".equalsIgnoreCase(visitor.getName())) {
                imagePath = "src/resources/visitors/arion.png";
            } else if ("Gabriel".equalsIgnoreCase(visitor.getName())) {
                imagePath = "src/resources/visitors/gabriel.png";
            } else if ("Joaquim".equalsIgnoreCase(visitor.getName())) {
                imagePath = "src/resources/visitors/joaquim.png";
            } else if ("Raquel".equalsIgnoreCase(visitor.getName())) {
                imagePath = "src/resources/visitors/raquel.png";
            } else if ("Jurema".equalsIgnoreCase(visitor.getName())) {
                imagePath = "src/resources/visitors/jurema.png";
            } else if ("Sofia".equalsIgnoreCase(visitor.getName())) {
                imagePath = "src/resources/visitors/sofia.png";
            } else {
                imagePath = "src/resources/visitors/defaultVisitor.png";
            }

            // Img
            JLabel imageLabel = new JLabel(new ImageIcon(imagePath));
            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            cardPanel.add(imageLabel);

            JLabel nameLabel = new JLabel(visitor.getName());
            nameLabel.setFont(CustomFont.useCustomFont(12f));
            nameLabel.setForeground(new Color(228, 201, 171));
            nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            cardPanel.add(nameLabel);

            // Buttons
            CustomButton dailyTask = new CustomButton(
                    "src/resources/buttons/updateButtonSmall.png",
                    0,
                    0,
                    52,
                    53,
                    e -> {
                        List<Employee> employees = employeeController.getEmployees();
                        if (employees.isEmpty()) {
                            CustomDialog.showMessage("No employees found.", JOptionPane.ERROR_MESSAGE);
                        } else {
                            new AddDailyTask(this);
                        }
                    },
                    Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
            );

            CustomButton deleteButton = new CustomButton(
                    "src/resources/buttons/deleteButtonSmall.png",
                    0,
                    0,
                    52,
                    53,
                    e -> {
                        visitorController.deleteVisitorById(visitor.getId().intValue());
                        refreshVisitorCards(visitorsPanel);
                    },
                    Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
            );

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
            buttonPanel.setOpaque(false);
            buttonPanel.setMaximumSize(new Dimension(180, 60));

            dailyTask.setPreferredSize(new Dimension(52, 53));
            deleteButton.setPreferredSize(new Dimension(52, 53));

            buttonPanel.add(dailyTask);
            buttonPanel.add(deleteButton);

            cardPanel.add(Box.createVerticalStrut(10));
            cardPanel.add(buttonPanel);

            visitorsPanel.add(cardPanel);
        }

        visitorsPanel.revalidate();
        visitorsPanel.repaint();
    }

    private void refreshVisitorCards(JPanel visitorsPanel) {
        addVisitorCards(visitorsPanel);
    }
}
