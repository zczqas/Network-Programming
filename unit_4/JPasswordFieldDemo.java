import javax.swing.*;

public class JPasswordFieldDemo {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("JPasswordDemo");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        JPanel panel = new JPanel();

        JLabel label = new JLabel("Enter Password");
        label.setBounds(10, 10, 100, 20);

        JPasswordField password_field = new JPasswordField(20);
        password_field.setText("password");

        JButton button = new JButton("Submit");

        panel.add(button);
        panel.add(label);
        panel.add(password_field);

        frame.add(panel);

        button.addActionListener(e -> {
            System.out.println("Password: " + password_field.getText());
        });
    }
}
