import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GradeCalculatorGUI extends JFrame {

    public GradeCalculatorGUI() {
        // Set up the JFrame
        setTitle("Grade Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        // Create labels, text fields, and a button
        JLabel labelSubject = new JLabel("Enter Marks (out of 100) for each subject:");
        JTextField[] textFields = new JTextField[3];  // Assuming 3 subjects
        for (int i = 0; i < textFields.length; i++) {
            textFields[i] = new JTextField(5);
        }
        JButton calculateButton = new JButton("Calculate");

        // Create action listener for the button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateAndDisplayResults(textFields);
            }
        });

        // Set up the layout using GroupLayout
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(labelSubject)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(textFields[0])
                        .addComponent(textFields[1])
                        .addComponent(textFields[2]))
                .addComponent(calculateButton)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(labelSubject)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(textFields[0])
                        .addComponent(textFields[1])
                        .addComponent(textFields[2]))
                .addComponent(calculateButton)
        );

        pack();
    }

    private void calculateAndDisplayResults(JTextField[] textFields) {
        // Calculate total marks
        int totalMarks = 0;
        for (JTextField textField : textFields) {
            totalMarks += Integer.parseInt(textField.getText());
        }

        // Calculate average percentage
        double averagePercentage = totalMarks / (double) textFields.length;

        // Assign grades based on average percentage
        String grade;
        if (averagePercentage >= 90) {
            grade = "A";
        } else if (averagePercentage >= 80) {
            grade = "B";
        } else if (averagePercentage >= 70) {
            grade = "C";
        } else if (averagePercentage >= 60) {
            grade = "D";
        } else {
            grade = "F";
        }

        // Display results in a dialog
        JOptionPane.showMessageDialog(this,
                "Total Marks: " + totalMarks + "\n" +
                        "Average Percentage: " + String.format("%.2f", averagePercentage) + "%\n" +
                        "Grade: " + grade,
                "Results",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GradeCalculatorGUI().setVisible(true);
            }
        });
    }
}
