import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignUpPage extends JFrame {

    public SignUpPage() {
        setTitle("Sign Up - BookWorm Library");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel titleLabel = new JLabel("Sign Up");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(300, 20, 200, 30);
        add(titleLabel);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(200, 80, 100, 25);
        add(firstNameLabel);

        JTextField firstNameField = new JTextField();
        firstNameField.setBounds(300, 80, 200, 30);
        add(firstNameField);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(200, 130, 100, 25);
        add(lastNameLabel);

        JTextField lastNameField = new JTextField();
        lastNameField.setBounds(300, 130, 200, 30);
        add(lastNameField);

        JLabel contactLabel = new JLabel("Contact No:");
        contactLabel.setBounds(200, 180, 100, 25);
        add(contactLabel);

        JTextField contactField = new JTextField();
        contactField.setBounds(300, 180, 200, 30);
        add(contactField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(200, 230, 100, 25);
        add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(300, 230, 200, 30);
        add(emailField);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(200, 280, 100, 25);
        add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(300, 280, 200, 30);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(200, 330, 100, 25);
        add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(300, 330, 200, 30);
        add(passwordField);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(300, 380, 100, 30);
        add(registerButton);

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get data from the fields
                String firstName = firstNameField.getText().trim();
                String lastName = lastNameField.getText().trim();
                String contact = contactField.getText().trim();
                String email = emailField.getText().trim();
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                // Basic validation
                if (firstName.isEmpty() || lastName.isEmpty() || contact.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "All fields are required!");
                    return;
                }

                // Check if the username already exists
                if (UserDAO.usernameExists(username)) {
                    JOptionPane.showMessageDialog(null, "Username already exists.");
                    return;
                }

                // Register the user in the database
                boolean success = UserDAO.registerUser(firstName, lastName, contact, email, username, password);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Registration Successful!");
                    dispose(); // Close the Sign-Up Page
                    new MainPage(); // Open the Main Page after successful registration
                } else {
                    JOptionPane.showMessageDialog(null, "Registration Failed. Please try again.");
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new SignUpPage();
    }
}
