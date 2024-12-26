import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalcApp extends JFrame implements ActionListener {
    // Components of the calculator
    private final JTextField display;
    private String operator;
    private double num1, num2, result;

    public CalcApp() {
        // Create the main frame
        setTitle("Calculator App");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Display field
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4,5, 5));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Times New Roman", Font.BOLD, 20));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("[0-9]")) { // If it's a number
            display.setText(display.getText() + command);
        } else if (command.matches("[+\\-*/]")) { // If it's an operator
            operator = command;
            num1 = Double.parseDouble(display.getText());
            display.setText("");
        } else if (command.equals("=")) { // If it's the equals sign
            num2 = Double.parseDouble(display.getText());
            switch (operator) {
                case "+" -> result = num1 + num2;
                case "-" -> result = num1 - num2;
                case "*" -> result = num1 * num2;
                case "/" -> result = num2 != 0 ? num1 / num2 : Double.NaN;
            }
            display.setText(String.valueOf(result));
        } else if (command.equals("C")) { // Clear the display
            display.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalcApp calculator = new CalcApp();
            calculator.setVisible(true);
        });
    }
}
