package ui;


import model.Invest;
import model.Portfolio;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Double.parseDouble;


public class InvestmentAppGUI {
    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 800;
    private static final String[] COLUMNNAMES = {"Ticker", "Listed Price", "Amount Funded",
            "# of Shares", "Balance", "Profit", "Realized Gains"};
    private static final String JSON_STORE = "./data/portfolio.json";

    private HashMap<Integer, Invest> indexMap;

    // hasmap idtoinv<int, Inv>
    private JFrame frame;
    private JFrame frame2;
    private JPanel introPanel;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField ticker;
    private JTextField amountFunded;
    private JTextField listedPrice;
    private JTextField percentage;
    private JTextField currentPrice;
    private JTextField search;
    private Portfolio portfolio;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JLabel tickerText = new JLabel("Ticker");
    private JLabel amountFundedText = new JLabel("Amount Funded");
    private JLabel listedPriceText = new JLabel("Listed Price");
    private JLabel percentageText = new JLabel("% to Sell");
    private JLabel currentPriceText = new JLabel("Current Listed Price");
    private JLabel searchText = new JLabel("Search by Name");
    private Timer introTimer;





    public InvestmentAppGUI() {
        this.indexMap = new HashMap<>();
        runIntroGUI();
        introTimer = new Timer(1500, e -> {
            frame2.setVisible(false);
            runGUI();
        });
        introTimer.setRepeats(false);
        introTimer.start();
    }

    private void runIntroGUI() {
        introPanel = new JPanel();
        introPanel.setBackground(Color.black);
        ImageIcon icon;
        BufferedImage image;
        initFrame2();

        try {
            image = ImageIO.read(new File("./data/bull_bear.jpg"));
            icon = new ImageIcon(image);

            JLabel introLabel = new JLabel();
            introLabel.setPreferredSize(new Dimension(945, 533));
            introLabel.setIcon(icon);

            introPanel.add(introLabel);
            frame2.getContentPane().add(introPanel);
            frame2.invalidate();
            frame2.validate();

        } catch (IOException e) {
            System.out.println("Run Again");
        }
    }

    private void initFrame2() {
        frame2 = new JFrame();
        frame2.setSize(945, 533);
        frame2.setTitle("Investment Portfolio");
        frame2.setResizable(false);
        frame2.setLocationRelativeTo(null);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setVisible(true);
//        frame2.setUndecorated(true);

    }

    private void runGUI() {
        frame = new JFrame();
        portfolio = new Portfolio();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        initializeInvestInputs();
        initializeJLabels();
        initializeButtonBuy();
        initializeButtonSell();
        initializeButtonSave();
        initializeButtonLoad();
        initializeButtonSearch();
        initializeTable();

        frame.setLayout(null);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.getContentPane().setBackground(new Color(77, 13, 58));
        frame.setTitle("Investment Portfolio");
        frame.setResizable(false);
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


        table.setAutoCreateRowSorter(true);

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
        search = new JTextField();


        ticker.setBounds(200, 600, 150, 20);
        amountFunded.setBounds(200, 650, 150, 20);
        listedPrice.setBounds(200, 700, 150, 20);
        percentage.setBounds(700, 600, 150, 20);
        currentPrice.setBounds(700, 700, 150, 20);
        search.setBounds(100, 460, 80, 30);


        frame.add(ticker);
        frame.add(amountFunded);
        frame.add(listedPrice);
        frame.add(percentage);
        frame.add(currentPrice);
        frame.add(search);
    }

    private void initializeJLabels() {

        tickerText.setBounds(205, 580, 150, 20);
        tickerText.setForeground(new Color(255,255,255));

        amountFundedText.setBounds(205, 630, 150, 20);
        amountFundedText.setForeground(new Color(255,255,255));

        listedPriceText.setBounds(205, 680, 150, 20);
        listedPriceText.setForeground(new Color(255,255,255));

        percentageText.setBounds(705, 580, 150, 20);
        percentageText.setForeground(new Color(255,255,255));

        currentPriceText.setBounds(705, 680, 150, 20);
        currentPriceText.setForeground(new Color(255,255,255));

        searchText.setBounds(50, 410, 150, 20);
        searchText.setForeground(new Color(255,255,255));

        frame.add(tickerText);
        frame.add(amountFundedText);
        frame.add(listedPriceText);
        frame.add(percentageText);
        frame.add(currentPriceText);
        frame.add(searchText);

    }

    private void initializeButtonBuy() {
        JButton buttonBuy = new JButton("BUY");
        buttonBuy.setFont(new Font("Bold", Font.BOLD, 40));
        buttonBuy.setBounds(25, 600, 150, 120);
        buttonBuy.setForeground(new Color(255,255,255));
        buttonBuy.setBackground(new Color(13, 126, 13));
        buttonBuy.setBorder(BorderFactory.createLineBorder(new Color(13, 126, 13)));
        buttonBuy.setOpaque(true);
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
        JButton buttonSell = new JButton("SELL");
        buttonSell.setFont(new Font("Bold", Font.BOLD, 40));
        buttonSell.setBounds(525, 600, 150, 120);
        buttonSell.setForeground(new Color(255,255,255));
        buttonSell.setBackground(new Color(171, 24, 24));
        buttonSell.setBorder(BorderFactory.createLineBorder(new Color(171, 24, 24)));
        buttonSell.setOpaque(true);
        frame.add(buttonSell);

        buttonSell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() >= 0) {
                    int i = table.getSelectedRow();
                    editUserInvestment(i);
                    clearUserInvestment();
                }
            }
        });
    }

    private void initializeButtonSave() {
        JButton buttonSave = new JButton("Save");
        buttonSave.setBounds(65, 75, 150, 80);
        buttonSave.setForeground(new Color(255,255,255));
        buttonSave.setFont(new Font("Bold", Font.PLAIN, 40));
        buttonSave.setBackground(new Color(93, 16, 73));
        buttonSave.setBorder(BorderFactory.createLineBorder(new Color(93, 16, 73)));
        buttonSave.setOpaque(true);
        frame.add(buttonSave);

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
//                    System.out.println("tesla");
//                    System.out.println(portfolio.isEmpty());
//                    for(Invest i: portfolio.getInvestments("TESLA")){
//                        System.out.println(i.getBalance());
//                    }
//                    System.out.println("apple");
//                    for(Invest i: portfolio.getInvestments("APPLE")){
//                        System.out.println(i.getBalance());
//                    }
                    jsonWriter.open();
                    jsonWriter.write(portfolio);
                    jsonWriter.close();
                } catch (FileNotFoundException fe) {
                    System.out.println("Unable to Write to File: " + JSON_STORE);
                }
            }
        });
    }

    private void initializeButtonLoad() {
        JButton buttonLoad = new JButton("Load");
        buttonLoad.setBounds(65, 165, 150, 80);
        buttonLoad.setBackground(new Color(93, 16, 73));
        buttonLoad.setForeground(new Color(255,255,255));
        buttonLoad.setFont(new Font("Bold", Font.PLAIN, 40));
        buttonLoad.setBorder(BorderFactory.createLineBorder(new Color(93, 16, 73)));

        frame.add(buttonLoad);

        buttonLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadPortfolio();
            }
        });
    }

    private void initializeButtonSearch() {


    }

    private void loadPortfolio() {
        try {
            portfolio = jsonReader.read();
            tableModel.setRowCount(0);
            indexMap = new HashMap<>();
            for (String name : portfolio.keySet()) {
                ArrayList<Invest> invests = portfolio.getInvestments(name);
                for (int j = 0; j < invests.size(); j++) {
                    Invest invest = invests.get(j);
                    Object[] row = new Object[7];
                    row[0] = invest.getName();
                    row[1] = Double.toString(invest.getListPrice());
                    row[2] = Double.toString(invest.getAmountFunded());
                    row[3] = invest.getNumberShares();
                    row[4] = Double.toString(invest.getBalance());
                    row[5] = invest.getProfit();
                    row[6] = invest.getRealizedGains();
                    // when i load it aren't they all objects as well

                    tableModel.addRow(row);
                    indexMap.put(j, invest);
                }
            }
        } catch (IOException e) {
            System.out.println("Unable to Read from File " + JSON_STORE);
        }
    }

    private void editUserInvestment(int i) {
        final Double numShares = (Double) tableModel.getValueAt(i, 3);
        Double numSharesUpdate = (Double.parseDouble(percentage.getText()) * numShares) / 100;
        Double numSharesRemain = numShares - numSharesUpdate;
        final Double realizedGains = (Double) tableModel.getValueAt(i, 6);

        tableModel.setValueAt(numSharesRemain, i, 3);
        Double realizedGainsUpdate = (Double.parseDouble(currentPrice.getText()) * numSharesUpdate);
        tableModel.setValueAt(realizedGainsUpdate, i, 6);

        Double balanceUpdate = realizedGainsUpdate
                + (numSharesRemain * (Double.parseDouble((String) tableModel.getValueAt(i, 1))) + realizedGains);
        tableModel.setValueAt(balanceUpdate, i, 4);

        Double profitUpdate = (balanceUpdate - Double.parseDouble((String) tableModel.getValueAt(i, 2)));
        tableModel.setValueAt(profitUpdate, i, 5);

        Invest currentInvestment = this.indexMap.get(i);
        currentInvestment.sell(Double.parseDouble(currentPrice.getText()),
                Double.parseDouble(percentage.getText()));
    }

    private void addUserInvestment() {

        Object [] row = new Object[7];
        row[0] = ticker.getText().toUpperCase(); // name
        row[1] = listedPrice.getText();// listedprice
        row[2] = amountFunded.getText(); // amountfunded
        row[3] = (Double.parseDouble(amountFunded.getText()) / Double.parseDouble(listedPrice.getText())); // num shares
        row[4] = amountFunded.getText(); //balance
        row[5] = 0.0; // profit
        row[6] = 0.0; // realized gains

        Invest invest = new Invest(row[0].toString(),
                parseDouble(row[1].toString()),
                parseDouble(row[2].toString()),
                parseDouble(row[3].toString()),
                parseDouble(row[4].toString()),
                parseDouble(row[5].toString()),
                parseDouble(row[6].toString())
        );


//        portfolio.addToPortfolio(row[0].toString(),
//                parseDouble(row[1].toString()),
//                parseDouble(row[2].toString()),
//                parseDouble(row[3].toString()),
//                parseDouble(row[4].toString()),
//                parseDouble(row[5].toString()),
//                parseDouble(row[6].toString()));

        portfolio.addToPortfolio(invest);
        tableModel.addRow(row);
        indexMap.put(tableModel.getRowCount() - 1, invest);

    }

    // MODIFIES: this
    // EFFECTS: clears text field boxes
    private void clearUserInvestment() {
        ticker.setText("");
        amountFunded.setText("");
        listedPrice.setText("");
        percentage.setText("");
        currentPrice.setText("");
    }

}