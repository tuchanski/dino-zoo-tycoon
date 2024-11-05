package views;

import javax.swing.*;
import java.awt.*;

public class ImageTextFieldExample extends JFrame {

    public ImageTextFieldExample() {
        setTitle("JTextField com Imagem de Fundo");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel com a imagem de fundo
        JPanel backgroundPanel = new JPanel() {
            private Image backgroundImage = new ImageIcon("src/resources/images/teste.png").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        backgroundPanel.setLayout(null); // Para posicionamento absoluto

        // Configuração do JTextField com fundo transparente
        JTextField typeField = new JTextField();
        typeField.setOpaque(false); // Torna o JTextField transparente
        typeField.setForeground(new Color(0, 0, 0));
        typeField.setBorder(null);
        typeField.setBounds(30, 170, 340, 30);

        // Adiciona o JTextField ao painel de fundo
        backgroundPanel.add(typeField);

        // Adiciona o painel ao JFrame
        add(backgroundPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new ImageTextFieldExample();
    }
}
