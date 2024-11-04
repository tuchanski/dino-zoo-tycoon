package views;

import views.utils.CustomButton;

import javax.swing.*;
import java.awt.*;

public class TitleBarButton extends JPanel {
    private final MainMenu parentFrame;


    public TitleBarButton(MainMenu parentFrame){
        this.parentFrame = parentFrame;
        setLayout(null);
        setOpaque(false);

        CustomButton addButton = new CustomButton(
                "src/resources/buttons/addButton.png",
                26,
                16,
                165,
                62,
                e -> openAddDino(),
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        CustomButton listButton = new CustomButton(
                "src/resources/buttons/listButton.png",
                206,
                16,
                165,
                62,
                e -> System.exit(0),
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        CustomButton deleteButton = new CustomButton(
                "src/resources/buttons/button-add.png",
                384,
                16,
                165,
                62,
                e -> System.exit(0),
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        CustomButton closeButton = new CustomButton(
                "src/resources/buttons/closeButton.png",
                720,
                14,
                68,
                70,
                e -> System.exit(0),
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        CustomButton minimizeButton = new CustomButton(
                "src/resources/buttons/minimizeButton.png",
                644,
                14,
                68,
                70,
                e -> parentFrame.setState(JFrame.ICONIFIED),
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        CustomButton settingsButton = new CustomButton(
                "src/resources/buttons/settingsButton.png",
                568,
                14,
                68,
                70,
                e -> System.exit(0),
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        add(addButton);
        add(listButton);
        add(deleteButton);

        add(closeButton);
        add(minimizeButton);
        add(settingsButton);
    }

    private void openAddDino() {
        AddDinoPanel addDinoFrame = new AddDinoPanel(parentFrame);
        addDinoFrame.setVisible(true);
    }
}