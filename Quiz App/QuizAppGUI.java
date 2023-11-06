import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizAppGUI {
    private JFrame frame;
    private JPanel panel;
    private JLabel questionLabel;
    private JRadioButton[] options;
    private ButtonGroup buttonGroup;
    private JButton nextButton;
    private String[] questions = {
        "What is the capital of France?",
        "Which planet is known as the Red Planet?",
        "Who wrote 'Romeo and Juliet'?",
        "What is the largest mammal in the world?",
        "What is the chemical symbol for water?"
    };
    private String[][] choices = {
        {"Paris", "Berlin", "London", "Madrid"},
        {"Mars", "Jupiter", "Venus", "Saturn"},
        {"Shakespeare", "Hemingway", "Tolstoy", "Dickens"},
        {"Blue whale", "African elephant", "Giraffe", "Kangaroo"},
        {"H2O", "CO2", "O2", "N2"}
    };
    private int currentQuestion = 0;
    private int score = 0;

    public QuizAppGUI() {
        frame = new JFrame("Quiz App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        questionLabel = new JLabel(questions[currentQuestion]);
        panel.add(questionLabel);

        options = new JRadioButton[4];
        buttonGroup = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton(choices[currentQuestion][i]);
            buttonGroup.add(options[i]);
            panel.add(options[i]);
        }

        nextButton = new JButton("Next");
        nextButton.addActionListener(new NextButtonListener());
        panel.add(nextButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private class NextButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < 4; i++) {
                if (options[i].isSelected()) {
                    if (choices[currentQuestion][i].equals("Paris")) {
                        score++;
                    }
                }
            }

            currentQuestion++;

            if (currentQuestion < questions.length) {
                questionLabel.setText(questions[currentQuestion]);
                for (int i = 0; i < 4; i++) {
                    options[i].setText(choices[currentQuestion][i]);
                    options[i].setSelected(false);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Quiz complete. Your score: " + score + " out of " + questions.length);
                frame.dispose();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QuizAppGUI());
    }
}
