package ui;

import model.Invest;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;

public class InvestTable extends JPanel {
    private boolean debug = false;
    private double ticker = Invest.getName();


    public InvestTable() {
        super(new GridLayout(1, 0));
        String[] columnNames = {
                "Ticker", "Amount Funded", "Listed Price", "# of Shares", "Profit", "Balance"};

        JTable table = new JTable(new MyInvestTable());
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }




