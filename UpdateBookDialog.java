import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateBookDialog extends JDialog {
    private JTextField nameField;
    private JTextField languageField;
    private JTextField typeField;
    private JButton updateButton;
    private JButton cancelButton;
    private JButton deleteButton;
    private int bookId;

    public UpdateBookDialog(JFrame parent, Book book) {
        super(parent, "Update Book", true);
        setSize(400, 350);  // Adjusted size to fit the delete button
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        contentPanel.setLayout(new GridLayout(5, 2, 10, 10));  // Increased rows to 5
        add(contentPanel, BorderLayout.CENTER);

        JLabel nameLabel = new JLabel("Book Name:");
        contentPanel.add(nameLabel);
        nameField = new JTextField(book.getName());
        contentPanel.add(nameField);

        JLabel languageLabel = new JLabel("Language:");
        contentPanel.add(languageLabel);
        languageField = new JTextField(book.getLanguage());
        contentPanel.add(languageField);

        JLabel typeLabel = new JLabel("Type:");
        contentPanel.add(typeLabel);
        typeField = new JTextField(book.getType());
        contentPanel.add(typeField);

        // Bottom panel with buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        cancelButton = new JButton("Cancel");
        cancelButton.setBackground(Color.GRAY);
        cancelButton.setForeground(Color.WHITE);
        buttonPanel.add(cancelButton);

        updateButton = new JButton("Update");
        updateButton.setBackground(Color.BLACK);
        updateButton.setForeground(Color.WHITE);
        buttonPanel.add(updateButton);

        deleteButton = new JButton("Delete");
        deleteButton.setBackground(Color.RED);
        deleteButton.setForeground(Color.WHITE);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Button Actions
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String language = languageField.getText();
                String type = typeField.getText();

                if (!name.isEmpty() && !language.isEmpty() && !type.isEmpty()) {
                    // Update the book in the database
                    Book updatedBook = new Book(bookId, name, type, language);
                    BooksDAO.updateBook(updatedBook);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(UpdateBookDialog.this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the dialog
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(UpdateBookDialog.this, 
                        "Are you sure you want to delete this book?", 
                        "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    // Delete the book from the database
                    BooksDAO.deleteBook(bookId);
                    dispose();
                }
            }
        });

        bookId = book.getId();
        setVisible(true);
    }
}
