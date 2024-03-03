import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BankuuATM extends JFrame {
    private Bankuu bankuu;

    public BankuuATM(Bankuu bankuu) {
        this.bankuu = bankuu;

        setTitle("ATM Machine");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        // Create components
        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");
        JButton checkBalanceButton = new JButton("Check Balance");

        // Create action listeners
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountString = JOptionPane.showInputDialog("Enter withdrawal amount:");
                try {
                    double amount = Double.parseDouble(amountString);
                    if (amount > 0) {
                        if (bankuu.withdraw(amount)) {
                            showMessage("Withdrawal successful. Remaining balance: $" + bankuu.getBalance());
                        } else {
                            showMessage("Insufficient funds. Unable to withdraw.");
                        }
                    } else {
                        showMessage("Invalid withdrawal amount. Please enter a positive value.");
                    }
                } catch (NumberFormatException ex) {
                    showMessage("Invalid input. Please enter a valid number.");
                }
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountString = JOptionPane.showInputDialog("Enter deposit amount:");
                try {
                    double amount = Double.parseDouble(amountString);
                    if (amount > 0) {
                        bankuu.deposit(amount);
                        showMessage("Deposit successful. New balance: $" + bankuu.getBalance());
                    } else {
                        showMessage("Invalid deposit amount. Please enter a positive value.");
                    }
                } catch (NumberFormatException ex) {
                    showMessage("Invalid input. Please enter a valid number.");
                }
            }
        });

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMessage("Current balance: $" + bankuu.getBalance());
            }
        });

        // Set up the layout
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(withdrawButton)
                .addComponent(depositButton)
                .addComponent(checkBalanceButton)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(withdrawButton)
                .addComponent(depositButton)
                .addComponent(checkBalanceButton)
        );

        pack();
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "ATM Message", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        Bankuu userAccount = new Bankuu(1000.0); // Initial balance for the user
        BankuuATM bankuuATM = new BankuuATM(userAccount);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                bankuuATM.setVisible(true);
            }
        });
    }
}
