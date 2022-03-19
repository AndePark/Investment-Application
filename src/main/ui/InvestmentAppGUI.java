package ui;

import javax.swing.*;
import java.awt.*;


public class InvestmentAppGUI extends JFrame {
    private ButtonPanel buttons;
    private InvestTable table;

    public InvestmentAppGUI() {
        this.setSize(400, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        buttons = new ButtonPanel();
        table = new InvestTable();
        this.add(buttons, BorderLayout.SOUTH);
    }


}
