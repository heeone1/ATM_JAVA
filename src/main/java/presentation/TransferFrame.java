package presentation;

import javax.swing.SwingUtilities;
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

    public TransferFrame() {
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
        String fromAccount = fromAccountField.getText();
        String toAccount = toAccountField.getText();
        String amountText = amountField.getText();

        // Perform validation and transfer logic here
        // For now, we just display a success message
        statusLabel.setText("Transfer successful from " + fromAccount + " to " + toAccount + " of amount " + amountText);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TransferFrame().setVisible(true);
            }
        });
    }
}