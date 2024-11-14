package views.panels;

import views.utils.ImageBackgroundPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Emergency mode to bring immersion to the project.

public class SOSMessage extends JFrame {
    private int mouseX, mouseY;
    private Timer blinking;
    private boolean isVisible = true;

    public SOSMessage(JFrame parentFrame){

        setUndecorated(true);
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parentFrame);

        int x = parentFrame.getX() + parentFrame.getWidth() + 20;
        int y = parentFrame.getY() + 50;
        setLocation(x, y);

        ImageBackgroundPanel backgroundPanel = new ImageBackgroundPanel("src/resources/backgrounds/sos-bg.png");
        backgroundPanel.setLayout(null);

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

        add(backgroundPanel);

        blinking = new Timer(900, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isVisible = !isVisible;
                SOSMessage.this.setVisible(isVisible);
            }
        });

        blinking.start();

        setVisible(true);
    }

    @Override
    public void dispose(){
        if (blinking != null){
            blinking.stop();
        }
        super.dispose();
    }
}
