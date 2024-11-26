import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBookDialog extends JDialog {
    private JTextField nameField;
    private JTextField languageField;
    private JTextField typeField;
    private JButton addButton;
    private JButton cancelButton;

    public AddBookDialog(JFrame parent) {
        super(parent, "Add Book", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // Set the border for the dialog
        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        contentPanel.setLayout(new GridLayout(4, 2, 10, 10));
        add(contentPanel, BorderLayout.CENTER);

        // Book Name
        JLabel nameLabel = new JLabel("Book Name:");
        contentPanel.add(nameLabel);
        nameField = new JTextField();
        contentPanel.add(nameField);

        // Language
        JLabel languageLabel = new JLabel("Language:");
        contentPanel.add(languageLabel);
        languageField = new JTextField();
        contentPanel.add(languageField);

        // Type
        JLabel typeLabel = new JLabel("Type:");
        contentPanel.add(typeLabel);
        typeField = new JTextField();
        contentPanel.add(typeField);

        // Bottom panel with buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        cancelButton = new JButton("Cancel");
        cancelButton.setBackground(Color.GRAY);
        cancelButton.setForeground(Color.WHITE);
        buttonPanel.add(cancelButton);

        addButton = new JButton("Add");
        addButton.setBackground(Color.BLACK);
        addButton.setForeground(Color.WHITE);
        buttonPanel.add(addButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Button Actions
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String language = languageField.getText();
                String type = typeField.getText();

                if (!name.isEmpty() && !language.isEmpty() && !type.isEmpty()) {
                    // Add new book to the database
                    Book newBook = new Book(0, name, type, language); // id will be auto-incremented
                    BooksDAO.addBook(newBook);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(AddBookDialog.this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the dialog
            }
        });

        setVisible(true);
    }
}
