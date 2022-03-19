package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ButtonPanel extends JPanel {
    private JButton sellButton;
    private JButton buyButton;

    Border emptyBorder = BorderFactory.createEmptyBorder();

    public ButtonPanel() {
        this.setPreferredSize(new Dimension(400, 60));
        this.setBackground(Color.red);

        sellButton = new JButton("Sell");
        sellButton.setBorder(emptyBorder);
        sellButton.setFont(new Font("San serif", Font.PLAIN, 20));
        this.add(sellButton);

        buyButton = new JButton("Buy");
        buyButton.setBorder(emptyBorder);
        buyButton.setFont(new Font("San serif", Font.PLAIN, 20));
        this.add(buyButton);
    }

    public JButton getSellButton() {
        return sellButton;
    }

    public JButton getBuyButton() {
        return buyButton;
    }
}

