import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import java.util.List;

public class BooksPage extends JFrame {

    private JTable booksTable;
    private DefaultTableModel tableModel;

    public BooksPage() {
        setTitle("Books - BookWorm Library");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // Sidebar Panel
        JPanel sidebar = createSidebar();
        add(sidebar);

        // Header Panel
        JPanel header = new JPanel();
        header.setLayout(new BorderLayout());
        header.setBounds(150, 0, 1050, 60);
        header.setBackground(Color.LIGHT_GRAY);

        JLabel userInfo = new JLabel(" PAUL DACALAN - Admin", JLabel.LEFT);
        userInfo.setFont(new Font("Arial", Font.BOLD, 16));
        header.add(userInfo, BorderLayout.WEST);

        JLabel dateTime = new JLabel(" 04:09 PM | Nov 11, 2024", JLabel.RIGHT);
        dateTime.setFont(new Font("Arial", Font.PLAIN, 16));
        header.add(dateTime, BorderLayout.EAST);

        add(header);

        // Top Buttons and Search Bar Panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BorderLayout());
        buttonsPanel.setBounds(150, 70, 1050, 50);
        buttonsPanel.setBackground(Color.WHITE);

        // Action Buttons Panel (Add, Update)
        JPanel actionButtonsPanel = new JPanel();
        actionButtonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
        actionButtonsPanel.setBackground(Color.WHITE);

        JButton addBookButton = new JButton("+ ADD BOOKS");
        styleButton(addBookButton);
        actionButtonsPanel.add(addBookButton);

        JButton updateBookButton = new JButton("+ UPDATE BOOKS");
        styleButton(updateBookButton);
        actionButtonsPanel.add(updateBookButton);

        buttonsPanel.add(actionButtonsPanel, BorderLayout.WEST);

        // Search Bar (Positioned on the right side of the panel)
        JTextField searchField = new JTextField("Search by ID or Type");
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("Arial", Font.BOLD, 14));
        searchButton.setPreferredSize(new Dimension(100, 30));

        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);
        buttonsPanel.add(searchPanel, BorderLayout.EAST);

        add(buttonsPanel);

        // Books Table
        loadBooksTableData();

        booksTable = new JTable(tableModel);
        booksTable.setRowHeight(30);
        booksTable.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane tableScrollPane = new JScrollPane(booksTable);
        tableScrollPane.setBounds(150, 130, 1050, 600);
        add(tableScrollPane);

        // Search Button ActionListener
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText().toLowerCase(); // Get text from search field
                filterTableData(searchText);
            }
        });

        // Add Button ActionListener
        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddBookDialog(BooksPage.this); // Open Add Book dialog
                loadBooksTableData();  // Refresh table after adding a book
            }
        });

        // Update Button ActionListener
        updateBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = booksTable.getSelectedRow();
                if (selectedRow != -1) {
                    int bookId = (int) booksTable.getValueAt(selectedRow, 0);
                    Book selectedBook = BooksDAO.getBookById(bookId);
                    new UpdateBookDialog(BooksPage.this, selectedBook); // Open Update Book dialog
                    loadBooksTableData();  // Refresh table after updating
                } else {
                    JOptionPane.showMessageDialog(BooksPage.this, "Please select a book to update.", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        setVisible(true);
    }

    private void styleButton(JButton button) {
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(150, 30));
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setBackground(Color.BLACK);
        sidebar.setBounds(0, 0, 150, 800); // Sidebar on the left side
        sidebar.setLayout(null);

        // Logo
        JLabel logo = new JLabel("★");
        logo.setFont(new Font("Arial", Font.BOLD, 50));
        logo.setForeground(Color.WHITE);
        logo.setBounds(50, 20, 50, 50);
        sidebar.add(logo);

        // Sidebar Buttons
        String[] buttonNames = {"Dashboard", "Catalog", "Books", "Users"};
        int yPosition = 100;

        for (String name : buttonNames) {
            JButton button = createSidebarButton(name);
            button.setBounds(10, yPosition, 130, 30);
            sidebar.add(button);

            // Add ActionListener for each button
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (name) {
                        case "Dashboard":
                            new LibraryDashboard(); // Open Dashboard
                            break;
                        case "Catalog":
                            new CatalogPage(); // Open Catalog
                            break;
                        case "Books":
                            new BooksPage(); // Open Books page (already here, so this line might be unnecessary)
                            break;
                        case "Users":
                            new UsersPage(); // Open Users page
                            break;
                    }
                    dispose(); // Close current window
                }
            });

            yPosition += 40;
        }

        // Logout Button
        JButton logoutButton = createSidebarButton("Logout");
        logoutButton.setBounds(10, 700, 130, 30);
        sidebar.add(logoutButton);

        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Exit the application
            }
        });

        return sidebar;
    }

    private JButton createSidebarButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        return button;
    }

    private void filterTableData(String searchText) {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        booksTable.setRowSorter(sorter);
        RowFilter<DefaultTableModel, Object> filter = RowFilter.regexFilter("(?i)" + searchText); 
        sorter.setRowFilter(filter);
    }

    private void loadBooksTableData() {
        List<Book> books = BooksDAO.getAllBooks();
        String[] columnNames = {"Book ID", "Name", "Type", "Language"};
        Object[][] data = new Object[books.size()][4];

        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            data[i][0] = book.getId();
            data[i][1] = book.getName();
            data[i][2] = book.getType();
            data[i][3] = book.getLanguage();
        }

        tableModel = new DefaultTableModel(data, columnNames);
    }
}
