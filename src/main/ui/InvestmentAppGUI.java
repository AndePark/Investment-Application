package ui;


import model.Portfolio;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;





public class InvestmentAppGUI {
    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 800;
    private static final String[] COLUMNNAMES = {"Ticker", "Amount Funded", "Listed Price",
            "# of Shares", "Profit", "Balance", "Realized Gains"};

    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField ticker;
    private JTextField amountFunded;
    private JTextField listedPrice;
    private JTextField percentage;
    private JTextField currentPrice;
    private Portfolio portfolio;

    public InvestmentAppGUI() {
        runGUI();
    }

    private void runGUI() {
        frame = new JFrame();
        portfolio = new Portfolio();

        initializeInvestInputs();
        initializeJLabels();
        initializeButtonBuy();
        initializeButtonSell();
        //initializeButtonSave();
        //initializeButtonLoad();
        initializeTable();

        frame.setLayout(null);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setTitle("Investment Portfolio");
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void initializeTable() {
        table = new JTable();
        tableModel = new DefaultTableModel() {
            @Override
            public Class getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableModel.setColumnIdentifiers(COLUMNNAMES);
        table.setModel(tableModel);
        table.setRowHeight(100);
        customizeTable();

        JScrollPane pane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        pane.setBounds(275, 25, 700, 525);
        frame.add(pane);
    }

    private void customizeTable() {
        table.setRowHeight(75);

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(new Font("", Font.PLAIN, 13));

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        tableHeader.setDefaultRenderer(renderer);
        table.setDefaultRenderer(String.class, renderer);
        table.setDefaultRenderer(Double.class, renderer);
    }

    private void initializeInvestInputs() {
        ticker = new JTextField();
        amountFunded = new JTextField();
        listedPrice = new JTextField();
        percentage = new JTextField();
        currentPrice = new JTextField();


        ticker.setBounds(50, 50, 150, 20);
        amountFunded.setBounds(50, 100, 150, 20);
        listedPrice.setBounds(50, 150, 150, 20);
        percentage.setBounds(50, 200, 150, 20);
        currentPrice.setBounds(50, 250, 150, 20);


        frame.add(ticker);
        frame.add(amountFunded);
        frame.add(listedPrice);
        frame.add(percentage);
        frame.add(currentPrice);
    }

    private void initializeJLabels() {
        JLabel tickerText = new JLabel("Ticker");
        JLabel amountFundedText = new JLabel("Amount Funded");
        JLabel listedPriceText = new JLabel("Listed Price");
        JLabel percentageText = new JLabel("% to Sell");
        JLabel currentPriceText = new JLabel("Current Listed Price");

        tickerText.setBounds(55, 30, 150, 20);
        amountFundedText.setBounds(55, 80, 150, 20);
        listedPriceText.setBounds(55, 130, 150, 20);
        percentageText.setBounds(55, 180, 150, 20);
        currentPriceText.setBounds(55, 230, 150, 20);

        frame.add(tickerText);
        frame.add(amountFundedText);
        frame.add(listedPriceText);
        frame.add(percentageText);
        frame.add(currentPriceText);
    }

    private void initializeButtonBuy() {
        JButton buttonBuy = new JButton("Buy");
        buttonBuy.setBounds(25, 400, 100, 30);
        frame.add(buttonBuy);

        buttonBuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUserInvestment();
                clearUserInvestment();
            }
        });
    }


    private void initializeButtonSell() {
        JButton buttonSell = new JButton("Sell");
        buttonSell.setBounds(25, 450, 100, 30);
        frame.add(buttonSell);

        buttonSell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() >= 0) {
                    int i = table.getSelectedRow();

                    Double numShares = (Double) tableModel.getValueAt(i, 3);


                    Double numSharesUpdate = (Double.parseDouble(percentage.getText()) * numShares) / 100;
                    Double numSharesRemain = (numSharesUpdate * 100) / (Double.parseDouble(percentage.getText()))
                            - numSharesUpdate; // INCORRECT
                    tableModel.setValueAt(numSharesUpdate, i, 3);
                    Double realizedGainsUpdate = (Double.parseDouble(currentPrice.getText()) * numSharesUpdate);
                    tableModel.setValueAt(realizedGainsUpdate, i, 6);

                    Double balanceUpdate = (numSharesRemain * (Double) tableModel.getValueAt(i, 2))
                            + realizedGainsUpdate;
                    tableModel.setValueAt(balanceUpdate, i, 5);
                    Double profitUpdate = balanceUpdate - (Double) tableModel.getValueAt(i, 1);
                    tableModel.setValueAt(profitUpdate, i, 4);
                }
            }
        });
    }
}