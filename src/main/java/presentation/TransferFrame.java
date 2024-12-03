package presentation;

import javax.swing.SwingUtilities;

import domain.ATM;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransferFrame extends JFrame {
    private JTextField fromAccountField;
    private JTextField toAccountField;
    private JTextField amountField;
    private JButton transferButton;
    private JLabel statusLabel;

    private ATM atm;
    private ATMFrame atmFrame;

    public TransferFrame(ATM atm, ATMFrame atmFrame) {
        this.atm = atm; // 전달받은 ATM 객체를 필드에 저장
        this.atmFrame = atmFrame; // ATMFrame 객체 저장
        setTitle("Transfer Money");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        // From Account
        JLabel fromAccountLabel = new JLabel("From Account:");
        fromAccountLabel.setBounds(10, 20, 120, 25);
        panel.add(fromAccountLabel);

        fromAccountField = new JTextField();
        fromAccountField.setBounds(140, 20, 200, 25);
        panel.add(fromAccountField);

        // To Account
        JLabel toAccountLabel = new JLabel("To Account:");
        toAccountLabel.setBounds(10, 60, 120, 25);
        panel.add(toAccountLabel);

        toAccountField = new JTextField();
        toAccountField.setBounds(140, 60, 200, 25);
        panel.add(toAccountField);

        // Amount
        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(10, 100, 120, 25);
        panel.add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(140, 100, 200, 25);
        panel.add(amountField);

        // Transfer Button
        transferButton = new JButton("Transfer");
        transferButton.setBounds(140, 140, 100, 25);
        panel.add(transferButton);

        // Status Label
        statusLabel = new JLabel("");
        statusLabel.setBounds(10, 180, 330, 25);
        panel.add(statusLabel);

        // Add action listener to the transfer button
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performTransfer();
            }
        });
    }

        private void performTransfer() {
        try {
            String fromAccount = fromAccountField.getText();
            String toAccount = toAccountField.getText();
            double amount = Double.parseDouble(amountField.getText());

            // ATM 객체를 통해 이체 수행
            String result = atm.performTransfer(fromAccount, toAccount, amount);
            atmFrame.displayTransferResult(result);

        } catch (NumberFormatException e) {
            statusLabel.setText("Invalid amount format.");
        } catch (Exception e) {
            statusLabel.setText("Error: " + e.getMessage());
        }
    }
}