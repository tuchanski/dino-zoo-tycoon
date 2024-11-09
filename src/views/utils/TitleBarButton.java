package views.utils;

import models.Zoo;
import views.panels.AddDinoPanel;
import views.panels.ListDinoPanel;
import views.panels.MainMenu;
import views.panels.UserSettings;

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
                e -> openListDino(),
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        CustomButton sosButton = new CustomButton(
                "src/resources/buttons/sosButton.png",
                384,
                16,
                165,
                62,
                e -> System.out.println("SOS | Emergency"),
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
                e -> openUserSettings(),
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        add(addButton);
        add(listButton);
        add(sosButton);

        add(closeButton);
        add(minimizeButton);
        add(settingsButton);
    }

    private void openAddDino() {
        Zoo zoo = new Zoo(1L, "Teste Zoo", 100L);
        AddDinoPanel addDinoFrame = new AddDinoPanel(parentFrame, zoo);
        addDinoFrame.setVisible(true);
    }

    private void openListDino() {
        ListDinoPanel listDinoPanel = new ListDinoPanel(parentFrame);
        listDinoPanel.setVisible(true);
    }

    private void openUserSettings(){
        UserSettings userSettings = new UserSettings(parentFrame);
        userSettings.setVisible(true);
    }
}