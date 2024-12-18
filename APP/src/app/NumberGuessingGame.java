package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGame {

    private int randomNumber;
    private int remainingChances = 3;
    private String username;

    public NumberGuessingGame() {
        createLandingPage();
    }

    private void createLandingPage() {
        JFrame landingFrame = new JFrame("Number Guessing Game");
        landingFrame.setSize(500, 300);
        landingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        landingFrame.setLayout(null);

        JLabel titleLabel = new JLabel("Welcome to the Number Guessing Game!", SwingConstants.CENTER);
        titleLabel.setBounds(50, 30, 400, 30);
        landingFrame.add(titleLabel);

        JLabel usernameLabel = new JLabel("Enter your username:", SwingConstants.CENTER);
        usernameLabel.setBounds(150, 90, 200, 20);
        landingFrame.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(150, 120, 200, 40);
        landingFrame.add(usernameField);

        JButton startButton = new JButton("Start Game");
        startButton.setBounds(150, 180, 200, 40);
        landingFrame.add(startButton);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username = usernameField.getText();
                if (!username.isEmpty()) {
                    landingFrame.dispose();
                    startGame();
                } else {
                    JOptionPane.showMessageDialog(landingFrame, "Please enter a username!");
                }
            }
        });

        landingFrame.setVisible(true);
    }

    private void startGame() {
        Random random = new Random();
        randomNumber = random.nextInt(10) + 1;

        JFrame gameFrame = new JFrame("Number Guessing Game");
        gameFrame.setSize(500, 300);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setLayout(null);

        JLabel instructionLabel = new JLabel("Guess the number between 1 and 10:", SwingConstants.CENTER);
        instructionLabel.setBounds(50, 30, 400, 30);
        gameFrame.add(instructionLabel);

        JLabel chancesLabel = new JLabel("Remaining Chances: " + remainingChances, SwingConstants.CENTER);
        chancesLabel.setBounds(50, 70, 400, 20);
        gameFrame.add(chancesLabel);

        JTextField guessField = new JTextField();
        guessField.setBounds(150, 100, 200, 40);
        gameFrame.add(guessField);

        JButton guessButton = new JButton("Guess");
        guessButton.setBounds(150, 160, 200, 40);
        gameFrame.add(guessButton);

        JLabel resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setBounds(50, 210, 400, 30);
        gameFrame.add(resultLabel);

        	guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int userGuess = Integer.parseInt(guessField.getText());

                    if (userGuess < 1 || userGuess > 10) {
                        resultLabel.setText("Please enter a number between 1 and 10.");
                        guessField.setText("");
                        return;
                    }

                    if (userGuess == randomNumber) {
                        resultLabel.setText("Correct! You guessed the number!");
                        showEndScreen(true, gameFrame);
                    } else {
                        remainingChances--;
                        chancesLabel.setText("Remaining Chances: " + remainingChances);

                        if (remainingChances == 0) {
                            resultLabel.setText("Game Over! The number was " + randomNumber + ".");
                            showEndScreen(false, gameFrame);
                        } else {
                            resultLabel.setText("Wrong! Try again.");
                        }
                    }
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Please enter a valid number.");
                }
            }
        });

        gameFrame.setVisible(true);
    }

    private void showEndScreen(boolean isWinner, JFrame gameFrame) {
        gameFrame.dispose();

        JFrame endFrame = new JFrame("Game Over");
        endFrame.setSize(500, 300);
        endFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        endFrame.setLayout(null);

        JLabel endMessage = new JLabel();
        endMessage.setBounds(50, 50, 400, 30);
        endMessage.setHorizontalAlignment(SwingConstants.CENTER);

        if (isWinner) {
            endMessage.setText("Congratulations, " + username + "! You won!");
        } else {
            endMessage.setText("Sorry, " + username + ". Better luck next time.");
        }
        endFrame.add(endMessage);

        JButton newGameButton = new JButton("New Game");
        newGameButton.setBounds(150, 120, 200, 50);
        endFrame.add(newGameButton);

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                endFrame.dispose();
                new NumberGuessingGame();
            }
        });

        endFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new NumberGuessingGame();
    }
}
