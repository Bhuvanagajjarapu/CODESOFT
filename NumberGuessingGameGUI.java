import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGameGUI extends JFrame {

    private final Random random = new Random();
    private int targetNumber;
    private int maxAttempts;
    private int attempts;

    private JLabel titleLabel;
    private JLabel instructionLabel;
    private JTextField guessTextField;
    private JButton guessButton;
    private JTextArea resultTextArea;

    public NumberGuessingGameGUI() {
        setTitle("Number Guessing Game");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeComponents();
        newGame();

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });
    }

    private void initializeComponents() {
        setLayout(new GridLayout(5, 1));

        titleLabel = new JLabel("Welcome to the Number Guessing Game!");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel);

        instructionLabel = new JLabel("Guess the number:");
        instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(instructionLabel);

        guessTextField = new JTextField();
        guessTextField.setHorizontalAlignment(SwingConstants.CENTER);
        add(guessTextField);

        guessButton = new JButton("Submit Guess");
        guessButton.setHorizontalAlignment(SwingConstants.CENTER);
        add(guessButton);

        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        resultTextArea.setWrapStyleWord(true);
        resultTextArea.setLineWrap(true);
        add(resultTextArea);
    }

    private void newGame() {
        targetNumber = random.nextInt(100) + 1;
        maxAttempts = 7;
        attempts = 0;

        guessTextField.setText("");
        resultTextArea.setText("");
        guessButton.setEnabled(true);
    }

    private void checkGuess() {
        String userInput = guessTextField.getText();
        if (!userInput.isEmpty()) {
            int userGuess = Integer.parseInt(userInput);
            attempts++;

            if (userGuess == targetNumber) {
                resultTextArea.setText("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                guessButton.setEnabled(false);
            } else if (userGuess < targetNumber) {
                resultTextArea.setText("Too low. Try again.");
            } else {
                resultTextArea.setText("Too high. Try again.");
            }

            if (attempts == maxAttempts) {
                resultTextArea.append("\nSorry, you've reached the maximum number of attempts. The correct number was " + targetNumber);
                guessButton.setEnabled(false);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumberGuessingGameGUI().setVisible(true);
            }
        });
    }
}
