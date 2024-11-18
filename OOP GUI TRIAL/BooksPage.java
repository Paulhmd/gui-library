import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BooksPage extends JFrame {

    private JTable booksTable;
    private DefaultTableModel tableModel;

    public BooksPage() {
        setTitle("Books - BookWorm Library");
        setSize(1200, 800); // Set the size of the window
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
        buttonsPanel.setLayout(new BorderLayout()); // Using BorderLayout for top panel
        buttonsPanel.setBounds(150, 70, 1050, 50); // Positioned below the header
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
        String[] columnNames = {"Book ID", "Name", "Type", "Language"};
        Object[][] data = {
                {"1", "Hibernate - Core", "Educational", "English"},
                {"2", "Java Basics", "Educational", "English"},
                {"3", "Spring Boot", "Educational", "English"},
                {"4", "Machine Learning", "Reference", "English"},
                {"5", "Python Programming", "Reference", "English"},
                {"6", "Data Structures", "Educational", "English"},
                {"7", "Algorithms", "Educational", "English"},
                {"8", "Web Development", "Educational", "English"},
        };

        tableModel = new DefaultTableModel(data, columnNames);
        booksTable = new JTable(tableModel);
        booksTable.setRowHeight(30);
        booksTable.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane tableScrollPane = new JScrollPane(booksTable);
        tableScrollPane.setBounds(150, 130, 1050, 600); // Adjust position below header and buttons
        add(tableScrollPane);

        // Search Button ActionListener
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText().toLowerCase(); // Get text from search field

                // Filter rows based on the search text
                filterTableData(searchText);
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
        // Create a new model to store the filtered data
        DefaultTableModel filteredModel = new DefaultTableModel(new String[]{"Book ID", "Name", "Type", "Language"}, 0);

        // Iterate over all rows in the original model and add rows that match the search text
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String bookId = tableModel.getValueAt(i, 0).toString().toLowerCase();
            String type = tableModel.getValueAt(i, 2).toString().toLowerCase();

            if (bookId.contains(searchText) || type.contains(searchText)) {
                Object[] row = {
                    tableModel.getValueAt(i, 0),
                    tableModel.getValueAt(i, 1),
                    tableModel.getValueAt(i, 2),
                    tableModel.getValueAt(i, 3)
                };
                filteredModel.addRow(row);
            }
        }

        // Set the filtered model to the table
        booksTable.setModel(filteredModel);
    }

    public static void main(String[] args) {
        new BooksPage();
    }
}
