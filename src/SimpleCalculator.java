import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalculator extends JFrame implements ActionListener {

    private final JTextField display;
    private double first_number;
    private double result;
    private String operator;

    public SimpleCalculator() {
        setTitle("Simple Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        display = new JTextField();
        display.setBounds(20, 20, 240, 40);
        display.setEditable(false);
        add(display);

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        int x = 20, y = 80;
        for (int i = 0; i < buttons.length; i++) {
            JButton button = new JButton(buttons[i]);
            button.setBounds(x, y, 50, 50);
            button.addActionListener(this);
            add(button);
            x += 60;
            if ((i + 1) % 4 == 0) {
                x = 20;
                y += 60;
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "." -> display.setText(display.getText() + command);
            case "+" -> {
                first_number = Double.parseDouble(display.getText());
                operator = "+";
                display.setText("");
            }
            case "-" -> {
                first_number = Double.parseDouble(display.getText());
                operator = "-";
                display.setText("");
            }
            case "*" -> {
                first_number = Double.parseDouble(display.getText());
                operator = "*";
                display.setText("");
            }
            case "/" -> {
                first_number = Double.parseDouble(display.getText());
                operator = "/";
                display.setText("");
            }
            case "=" -> {
                double second_number = Double.parseDouble(display.getText());
                switch (operator) {
                    case "+" -> result = first_number + second_number;
                    case "-" -> result = first_number - second_number;
                    case "*" -> result = first_number * second_number;
                    case "/" -> result = first_number / second_number;
                }
                display.setText(String.valueOf(result));
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimpleCalculator calculator = new SimpleCalculator();
            calculator.setVisible(true);
        });
    }
}
