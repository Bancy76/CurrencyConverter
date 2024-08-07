package CurrencyConverter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter extends JFrame {

    private JComboBox<String> fromCurrency;
    private JComboBox<String> toCurrency;
    private JTextField amountField;
    private JLabel resultLabel;

    private static final Map<String, Double> exchangeRates = new HashMap<>();

    static {
        exchangeRates.put("USD", 1.0);
        exchangeRates.put("EUR", 0.85);
        exchangeRates.put("GBP", 0.75);
    }

    public CurrencyConverter() {
        setTitle("Currency Converter");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create UI components
        fromCurrency = new JComboBox<>(exchangeRates.keySet().toArray(new String[0]));
        toCurrency = new JComboBox<>(exchangeRates.keySet().toArray(new String[0]));
        amountField = new JTextField(10);
        JButton convertButton = new JButton("Convert");
        resultLabel = new JLabel("Converted Amount: ");

        // Set up layout
        setLayout(new GridLayout(5, 2));
        add(new JLabel("From: "));
        add(fromCurrency);
        add(new JLabel("To: "));
        add(toCurrency);
        add(new JLabel("Amount: "));
        add(amountField);
        add(new JLabel(""));
        add(convertButton);
        add(new JLabel(""));
        add(resultLabel);

        // Add button click event
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });
    }

    private void convertCurrency() {
        try {
            String from = (String) fromCurrency.getSelectedItem();
            String to = (String) toCurrency.getSelectedItem();
            double amount = Double.parseDouble(amountField.getText());

            double fromRate = exchangeRates.get(from);
            double toRate = exchangeRates.get(to);
            double convertedAmount = (amount / fromRate) * toRate;

            resultLabel.setText(String.format("Converted Amount: %.2f %s", convertedAmount, to));
        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid amount entered.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CurrencyConverter().setVisible(true);
            }
        });
    }
}
