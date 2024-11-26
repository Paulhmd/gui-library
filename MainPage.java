import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainPage extends JFrame {

    public MainPage() {
        setTitle("BookWorm Library");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2)); // Split into two panels

        // Left Panel - Sign In
        JPanel signInPanel = new JPanel();
        signInPanel.setLayout(null);
        signInPanel.setBackground(Color.WHITE);

        JLabel signInTitle = new JLabel("Welcome Back!!");
        signInTitle.setFont(new Font("Arial", Font.BOLD, 24));
        signInTitle.setBounds(80, 20, 300, 30);
        signInPanel.add(signInTitle);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 80, 200, 25);
        signInPanel.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(50, 110, 250, 30);
        signInPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 160, 200, 25);
        signInPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(50, 190, 250, 30);
        signInPanel.add(passwordField);

        JButton signInButton = new JButton("Sign In");
        signInButton.setBounds(50, 240, 250, 30);
        signInPanel.add(signInButton);

        JLabel forgotPassword = new JLabel("Forgot password?");
        forgotPassword.setBounds(50, 280, 200, 20);
        forgotPassword.setForeground(Color.BLUE);
        signInPanel.add(forgotPassword);

        // Right Panel - Sign Up
        JPanel signUpPanel = new JPanel();
        signUpPanel.setLayout(null);
        signUpPanel.setBackground(Color.BLACK);

        JLabel signUpTitle = new JLabel("BookWorm LIBRARY");
        signUpTitle.setFont(new Font("Arial", Font.BOLD, 20));
        signUpTitle.setForeground(Color.WHITE);
        signUpTitle.setBounds(80, 80, 300, 30);
        signUpPanel.add(signUpTitle);

        JLabel signUpPrompt = new JLabel("New to our platform? Sign Up now.");
        signUpPrompt.setForeground(Color.WHITE);
        signUpPrompt.setBounds(80, 130, 300, 30);
        signUpPanel.add(signUpPrompt);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(80, 180, 250, 30);
        signUpPanel.add(signUpButton);

        // Add panels to the frame
        add(signInPanel);
        add(signUpPanel);

        // Action Listeners
        signInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                // Validate user credentials
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter both username and password.");
                    return;
                }

                boolean valid = UserDAO.validateCredentials(username, password);
                if (valid) {
                    JOptionPane.showMessageDialog(null, "Sign In Successful!");
                    dispose(); // Close MainPage
                    new LibraryDashboard(); // Open LibraryDashboard
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid credentials. Please try again.");
                }
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close MainPage
                new SignUpPage(); // Open Sign-Up Page
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainPage(); // Entry point
    }
}
