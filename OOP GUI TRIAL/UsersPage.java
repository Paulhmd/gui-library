import javax.swing.*;
import java.awt.*;

public class UsersPage extends JFrame {
    public UsersPage() {
        setTitle("Users - BookWorm Library");
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel label = new JLabel("Users Page");
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label);

        setVisible(true);
    }
}
