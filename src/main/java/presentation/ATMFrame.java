package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import domain.ATM;

public class ATMFrame extends JFrame {
    private ATM atm;

    private JTextField nameField;
    private JPasswordField passwordField;
    private JTextField accountField;
    private JTextField amountField;
    private JTextArea outputArea;

    public ATMFrame(ATM atm) {
        this.atm = atm;

        setTitle("ATM Machine");
        setSize(400, 430);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // null: 화면 중앙에 표시하도록 함 

        JPanel panel = new JPanel();
        panel.setLayout(null); // 내부 컴포넌트 위치와 크기를 직접 지정할 수 있도록 함
        add(panel);

        // customer name
        JLabel nameLabel = new JLabel("Customer Name:");
        nameLabel.setBounds(10, 20, 120, 25); // 크기 및 위치 지정
        panel.add(nameLabel);

        nameField = new JTextField(20);
        nameField.setBounds(140, 20, 200, 25);
        panel.add(nameField);

        // password
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 120, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(140, 50, 200, 25);
        panel.add(passwordField);

        // login button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 100, 25);
        panel.add(loginButton);

        // account number
        JLabel accountLabel = new JLabel("Account Number:");
        accountLabel.setBounds(10, 110, 120, 25);
        panel.add(accountLabel);

        accountField = new JTextField(20);
        accountField.setBounds(140, 110, 200, 25);
        panel.add(accountField);

        // amount
        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(10, 140, 120, 25);
        panel.add(amountLabel);

        amountField = new JTextField(20);
        amountField.setBounds(140, 140, 200, 25);
        panel.add(amountField);

        // transaction buttons
        JButton depositButton = new JButton("Deposit");
        depositButton.setBounds(10, 170, 100, 25);
        panel.add(depositButton);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(130, 170, 100, 25);
        panel.add(withdrawButton);

        JButton balanceButton = new JButton("Check Balance");
        balanceButton.setBounds(10, 200, 150, 25);
        panel.add(balanceButton);


        // Transfer Button
        JButton transferButton = new JButton("Transfer");
        transferButton.setBounds(250, 170, 100, 25);
        panel.add(transferButton);


        // output area
        outputArea = new JTextArea();
        outputArea.setBounds(10, 235, 370, 150);
        outputArea.setEditable(false);
        panel.add(outputArea);

        // 각 버튼에 이벤트 핸들러 등록
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performTransaction("deposit");
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performTransaction("withdraw");
            }
        });

        balanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBalance();
            }
        });

        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openTransferFrame();
            }
        });
    }

    private void login() {
        String name = nameField.getText();
        String password = new String(passwordField.getPassword());

        if (atm.login(name, password)) {
            outputArea.setText("Login successful. Welcome, " + name + "!");
        } else {
            outputArea.setText("Invalid login credentials.");
        }
    }

    private void performTransaction(String type) {
        String accountNumber = accountField.getText();
        double amount = Double.parseDouble(amountField.getText());

        String result = atm.performTransaction(type, accountNumber, amount);
        outputArea.setText(result);
    }

    private void checkBalance() {
        String accountNumber = accountField.getText();

        String result = atm.checkBalance(accountNumber);
        outputArea.setText(result);
    }

    private void openTransferFrame() {
        TransferFrame transferFrame = new TransferFrame(atm, this);
        transferFrame.setVisible(true);
    }
    
    public void displayTransferResult(String result) {
        outputArea.setText(result);
    }
}