package ui;

import model.Invest;
import model.Portfolio;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Double.parseDouble;

// referenced: TrafficLight (C3-LectureLabSolution) IntersectionGUI for information on JButtons
// model: https://github.students.cs.ubc.ca/CPSC210/C3-LectureLabSolution

// referenced: stackoverflow (provided on EdX in project description phase 3) for how to set up/customize
// JFrame and JLabels
// model: https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application

// referenced: TableDemo && SimpleTableSelectionDemo tutorial on Oracle was used for the implementation the JTable
// itself along with information on how to implement the ActionListeners
// model: https://docs.oracle.com/javase/tutorial/uiswing/examples/components/TableDemoProject/src/components/
//        TableDemo.java
//      :https://docs.oracle.com/javase/tutorial/uiswing/examples/components/SimpleTableSelectionDemoProject
//       /src/components/SimpleTableSelectionDemo.java

// referenced: TableDialogEditDemo for the rendering of my JTable data
// model: https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing
//        /examples/components/TableDialogEditDemoProject/src/components/TableDialogEditDemo.java

// referenced: Google for images
// model: https://www.investors.com/wp-content/uploads/2020/06/Stock-bearbullchart-02-adobe.jpg
//      : https://jooinn.com/images/winner-loser-buttons-shows-winning-or-losing.jpg

// Investment application GUI
public class InvestmentAppGUI {
    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 740;
    private static final String[] COLUMNNAMES = {"Ticker", "Listed Price", "Amount Funded",
            "# of Shares", "Balance", "Profit", "Realized Gains"};
    private static final String JSON_STORE = "./data/portfolio.json";

    private HashMap<Integer, Invest> indexMap;
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
    private Portfolio portfolio;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JLabel tickerText = new JLabel("Ticker");
    private JLabel amountFundedText = new JLabel("Amount Funded");
    private JLabel listedPriceText = new JLabel("Listed Price");
    private JLabel percentageText = new JLabel("% to Sell");
    private JLabel currentPriceText = new JLabel("Current Listed Price");
    private Timer introTimer;
    private ImageIcon icon1;
    private ImageIcon icon2;
    private ImageIcon icon3;


    // EFFECTS: constructs and runs intro pop-up GUI then subsequently runs main investment app GUI
    public InvestmentAppGUI() {
        this.indexMap = new HashMap<>();
        runIntroGUI();
        introTimer = new Timer(2500, e -> {
            frame2.setVisible(false);
            runGUI();
        });
        introTimer.setRepeats(false);
        introTimer.start();
    }

    // MODIFIES: this
    // EFFECTS: runs the intro pop-up GUI
    private void runIntroGUI() {
        introPanel = new JPanel();
        introPanel.setBackground(Color.black);
        initFrame2();

        icon1 = new ImageIcon("./data/bull_bear.jpg");

        JLabel introLabel = new JLabel();
        introLabel.setPreferredSize(new Dimension(945, 533));
        introLabel.setIcon(icon1);

        introPanel.add(introLabel);
        frame2.getContentPane().add(introPanel);
        frame2.invalidate();
        frame2.validate();

        frame2.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes the intro pop-up JFrame
    private void initFrame2() {
        frame2 = new JFrame();
        frame2.setUndecorated(true);
        frame2.setSize(945, 533);
        frame2.setTitle("Investment Portfolio");
        frame2.setResizable(false);
        frame2.setLocationRelativeTo(null);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // MODIFIES: this
    // EFFECTS: initializes main application objects then runs main GUI
    private void runGUI() {
        frame = new JFrame();
        portfolio = new Portfolio();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);


        initMainFrame();

        initializeInvestTextField();

        initJLabel();

        initButtonBuy();
        initButtonSell();
        initButtonSave();
        initButtonLoad();
        initButtonProfit();
        initButtonAbout();

        initTable();
    }

    // MODIFIES: this
    // EFFECTS: initializes the main JFrame
    private void initMainFrame() {
        frame.setLayout(null);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.getContentPane().setBackground(new Color(122, 158, 159));
        frame.setTitle("Investment Portfolio");
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes table elements (JTable, DefaultTableModel, JScrollPane) then adds them to main GUI frame
    private void initTable() {
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
        customizeTable();

        JScrollPane pane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        pane.setBounds(100, 25, 800, 300);
        frame.add(pane);
    }

    // MODIFIES: this
    // EFFECTS: customizes table
    private void customizeTable() {
        table.setRowHeight(75);

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(new Font("", Font.PLAIN, 13));

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(String.class, renderer);
        table.setDefaultRenderer(Double.class, renderer);
    }

    // MODIFIES: this
    // EFFECTS: initializes all JTextFields then adds them to main JFrame
    private void initializeInvestTextField() {
        ticker = new JTextField();
        amountFunded = new JTextField();
        listedPrice = new JTextField();
        percentage = new JTextField();
        currentPrice = new JTextField();

        ticker.setBounds(200, 400, 150, 20);
        amountFunded.setBounds(200, 450, 150, 20);
        listedPrice.setBounds(200, 500, 150, 20);
        percentage.setBounds(700, 400, 150, 20);
        currentPrice.setBounds(700, 500, 150, 20);

        frame.add(ticker);
        frame.add(amountFunded);
        frame.add(listedPrice);
        frame.add(percentage);
        frame.add(currentPrice);
    }

    // MODIFIES: this
    // EFFECTS: initializes all JLabels then adds them to main JFrame
    private void initJLabel() {
        tickerText.setBounds(205, 380, 150, 20);
        setTextColour(tickerText);

        amountFundedText.setBounds(205, 430, 150, 20);
        setTextColour(amountFundedText);

        listedPriceText.setBounds(205, 480, 150, 20);
        setTextColour(listedPriceText);

        percentageText.setBounds(705, 380, 150, 20);
        setTextColour(percentageText);

        currentPriceText.setBounds(705, 480, 150, 20);
        setTextColour(currentPriceText);

        frame.add(tickerText);
        frame.add(amountFundedText);
        frame.add(listedPriceText);
        frame.add(percentageText);
        frame.add(currentPriceText);
    }

    // MODIFIES: this
    // EFFECTS: sets the text colour of all JLabels to white
    private void setTextColour(JLabel j) {
        j.setForeground(new Color(255, 255, 255));
    }

    // EFFECTS: an image is displayed on the pane if the user can find the button (easter egg)
    private void initButtonAbout() {
        ImageIcon icon4 = new ImageIcon("data/Instagram_egg.jpg");
        Image scaleImage = icon4.getImage().getScaledInstance(10,10, Image.SCALE_FAST);
        icon4 = new ImageIcon(scaleImage);
        JButton buttonAbout = new JButton(icon4);

        buttonAbout.setBounds(960, 690, 20, 20);
        customizeJButton(buttonAbout);
        buttonAbout.setFont(new Font("Bold", Font.PLAIN, 10));

        frame.add(buttonAbout);

        buttonAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                icon3 = new ImageIcon("data/Rick-Morty.jpg");
                Image scaleImage = icon3.getImage().getScaledInstance(500,500, Image.SCALE_DEFAULT);
                icon3 = new ImageIcon(scaleImage);

                JOptionPane.showMessageDialog(frame, null, "EasterEgg?",
                        JOptionPane.PLAIN_MESSAGE, icon3);
            }
        });
    }

    // MODIFIES: this, Invest
    // EFFECTS: initializes buy JButton, adds it to main frame, then performs buy of investment. Otherwise, displays
    //          pop-up panel notifying user that input is invalid
    private void initButtonBuy() {
        JButton buttonBuy = new JButton("BUY");
        buttonBuy.setBounds(25, 400, 150, 120);
        customizeJButton(buttonBuy);

        frame.add(buttonBuy);

        buttonBuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addUserInvestment();
                    clearUserInvestment();
                } catch (NumberFormatException ne) {
                    invalidEntry("Please Enter Valid Numbers");
                }
            }
        });
    }

    // MODIFIES: this, Invest
    // EFFECTS: initializes sell JButton, adds it to main frame, then performs sell of investment. Otherwise,
    //          displays pop-up notifying user that input/selection is invalid
    private void initButtonSell() {
        JButton buttonSell = new JButton("SELL");
        buttonSell.setBounds(525, 400, 150, 120);
        customizeJButton(buttonSell);

        frame.add(buttonSell);

        buttonSell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() >= 0) {
                    int i = table.getSelectedRow();
                    editUserInvestment(i);
                    clearUserInvestment();
                } else {
                    invalidEntry("Please Select an Investment / Input Values");
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: initializes save JButton, adds them to main frame, writes investments in table to JSON_STORE,
    // catches FileNotFoundException and displays new pop-up panel that notifies user that investments have been saved
    private void initButtonSave() {
        JButton buttonSave = new JButton("SAVE");
        buttonSave.setBounds(50, 600, 180, 80);
        customizeJButton(buttonSave);

        frame.add(buttonSave);

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(portfolio);
                    jsonWriter.close();
                } catch (FileNotFoundException fe) {
                    System.out.println("Unable to Write to File: " + JSON_STORE);
                }

                JOptionPane.showMessageDialog(frame, "Portfolio has been saved!", "Portfolio",
                        JOptionPane.PLAIN_MESSAGE);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: initializes load JButton, adds them to main frame, writes investments from JSON_STORE to JTable
    private void initButtonLoad() {
        JButton buttonLoad = new JButton("LOAD");
        buttonLoad.setBounds(400, 600, 180, 80);
        customizeJButton(buttonLoad);

        frame.add(buttonLoad);

        buttonLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadUserPortfolio();
            }
        });
    }


    // EFFECTS: initializes profit JButton, adds them to main frame, relays total profit from all investments on
    //          another pop-up panel
    private void initButtonProfit() {
        JButton buttonSearch = new JButton("PROFITS");
        buttonSearch.setBounds(755, 600, 180, 80);
        customizeJButton(buttonSearch);

        frame.add(buttonSearch);

        buttonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Double totalProfit = 0.0;

                for (int i = 0; i < table.getRowCount(); i++) {
                    Double profit = (Double) tableModel.getValueAt(i, 5);
                    totalProfit += profit;
                }
                icon2 = new ImageIcon("data/winner-loser.jpg");

                Image scaleImage = icon2.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);

                icon2 = new ImageIcon(scaleImage);

                JLabel label = new JLabel(String.valueOf(totalProfit));
                label.setHorizontalAlignment(JLabel.CENTER);
                JOptionPane.showMessageDialog(frame, label, "Are you winning?",
                        JOptionPane.PLAIN_MESSAGE, icon2);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: customizes all JButtons on main frame
    private void customizeJButton(JButton jb) {
        jb.setBackground(new Color(79, 99, 103));
        jb.setForeground(new Color(255, 255, 255));
        jb.setFont(new Font("Bold", Font.PLAIN, 40));
        jb.setBorder(BorderFactory.createLineBorder(new Color(184, 216, 216)));
    }

    // MODIFIES: this, Invest
    // EFFECTS: adds new investment to JTable using given ticker, listedPrice and amountFunded , updates portfolio
    //          and creates new indexMap to keep track of all investments on the table
    private void addUserInvestment() {
        Object [] row = new Object[7];
        row[0] = ticker.getText().toUpperCase();
        row[1] = listedPrice.getText();
        row[2] = amountFunded.getText();
        row[3] = (Double.parseDouble(amountFunded.getText()) / Double.parseDouble(listedPrice.getText()));
        row[4] = amountFunded.getText();
        row[5] = 0.0;
        row[6] = 0.0;

        Invest invest = new Invest(row[0].toString(),
                parseDouble(row[1].toString()),
                parseDouble(row[2].toString()),
                parseDouble(row[3].toString()),
                parseDouble(row[4].toString()),
                parseDouble(row[5].toString()),
                parseDouble(row[6].toString())
        );
        portfolio.addToPortfolio(invest);
        tableModel.addRow(row);
        indexMap.put(tableModel.getRowCount() - 1, invest);
    }

    // MODIFIES: this, Invest
    // EFFECTS: sells selected investment from JTable using given currentPrice and percentage
    private void editUserInvestment(int i) {
        final Double numShares = (Double) tableModel.getValueAt(i, 3);
        Double numSharesUpdate = (Double.parseDouble(percentage.getText()) * numShares) / 100;
        Double numSharesRemain = numShares - numSharesUpdate;
        final Double realizedGains = (Double) tableModel.getValueAt(i, 6);

        tableModel.setValueAt(numSharesRemain, i, 3);
        Double realizedGainsUpdate = (Double.parseDouble(currentPrice.getText()) * numSharesUpdate) + realizedGains;
        tableModel.setValueAt(realizedGainsUpdate, i, 6);

        Double balanceUpdate = realizedGainsUpdate
                + (numSharesRemain * (Double.parseDouble((String) tableModel.getValueAt(i, 1))));
        tableModel.setValueAt(balanceUpdate, i, 4);

        Double profitUpdate = (balanceUpdate - Double.parseDouble((String) tableModel.getValueAt(i, 2)));
        tableModel.setValueAt(profitUpdate, i, 5);

        Invest currentInvestment = this.indexMap.get(i);

        currentInvestment.sell(Double.parseDouble(currentPrice.getText()),
                Double.parseDouble(percentage.getText()));
    }

    // MODIFIES: this
    // EFFECTS: loads portfolio from JSON_STORE, creates a new indexMap for all investments on loaded JTable
    private void loadUserPortfolio() {
        try {
            tableModel.setRowCount(0);
            indexMap = new HashMap<>();
            portfolio = jsonReader.read();
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
                    tableModel.addRow(row);
                    indexMap.put(j + indexMap.size(), invest);
                }
            }
        } catch (IOException e) {
            System.out.println("Unable to Read from File " + JSON_STORE);
        }
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



    // EFFECTS: displays pop-up with given string
    private void invalidEntry(String s) {
        JOptionPane.showMessageDialog(frame, s, "ERROR", JOptionPane.PLAIN_MESSAGE);
    }

}