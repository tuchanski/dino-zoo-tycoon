package views.utils;

import models.User;
import models.Zoo;
import views.panels.*;

import javax.swing.*;
import java.awt.*;

public class TitleBarButton extends JPanel {
    private final MainMenu parentFrame;
    private User currentUser;

    private AddDinoPanel addDinoPanel;
    private ListDinoPanel listDinoPanel;
    private UserSettings userSettings;
    private SOSMessage sosMessage;


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
        if (addDinoPanel == null || !addDinoPanel.isDisplayable()){
            addDinoPanel = new AddDinoPanel(parentFrame);
            addDinoPanel.setVisible(true);
        } else{
            addDinoPanel.toFront();
        }
    }

    private void openListDino() {
        if (listDinoPanel == null || !listDinoPanel.isDisplayable()){
            listDinoPanel = new ListDinoPanel(parentFrame);
            listDinoPanel.setVisible(true);
        } else{
            listDinoPanel.toFront();
        }
    }

    private void openUserSettings() {
        if (userSettings == null || !userSettings.isDisplayable()){
            userSettings = new UserSettings(parentFrame, currentUser);
            userSettings.setVisible(true);
        } else{
            userSettings.toFront();
        }
    }

    private void openSosMessage(){
        if (sosMessage == null || !sosMessage.isDisplayable()){
            sosMessage = new SOSMessage(parentFrame);
            sosMessage.setVisible(true);
        } else {
            sosMessage.toFront();
        }
    }
}