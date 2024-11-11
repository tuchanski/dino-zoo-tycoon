package views.utils;

import models.User;
import models.Zoo;
import views.panels.*;

import javax.swing.*;
import java.awt.*;

public class TitleBarButton extends JPanel {
    private final MainMenu parentFrame;
    private User currentUser;


    public TitleBarButton(MainMenu parentFrame, User currentUser){
        this.currentUser = currentUser;
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
                e -> openSosMessage(),
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
        Zoo zoo = new Zoo(1L, "Teste Zoo", 1L);
        AddDinoPanel addDinoFrame = new AddDinoPanel(parentFrame);
        addDinoFrame.setVisible(true);
    }

    private void openListDino() {
        ListDinoPanel listDinoPanel = new ListDinoPanel(parentFrame);
        listDinoPanel.setVisible(true);
    }

    private void openUserSettings() {
        UserSettings userSettings = new UserSettings(parentFrame, currentUser);
        userSettings.setVisible(true);
    }

    private void openSosMessage(){
        SOSMessage sosMessage = new SOSMessage(parentFrame);
        sosMessage.setVisible(true);
    }
}