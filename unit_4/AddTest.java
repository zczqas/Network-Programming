import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("AdapterTest");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        JLabel label_one = new JLabel();
        JLabel label_two = new JLabel();

        JTextField textfield_one = new JTextField(20);
        JTextField textfield_two = new JTextField(20);

        JButton button = new JButton("Add!!");

        JPanel panel = new JPanel();

        panel.add(label_one);
        panel.add(textfield_one);

        panel.add(label_two);
        panel.add(textfield_two);

        panel.add(button);

        frame.add(panel);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = Integer.parseInt(textfield_one.getText());
                int b = Integer.parseInt(textfield_two.getText());
                int c = a + b;
                JOptionPane.showMessageDialog(frame, c);
            }
        });
    }
}
