import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CatalogPage extends JFrame {

    public CatalogPage() {
        setTitle("Catalog - BookWorm Library");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // Sidebar Panel
        JPanel sidebar = new JPanel();
        sidebar.setBackground(Color.BLACK);
        sidebar.setBounds(0, 0, 150, 800); // Adjusted for uniformity
        sidebar.setLayout(null);

        // Logo
        JLabel logo = new JLabel("★");
        logo.setFont(new Font("Arial", Font.BOLD, 50));
        logo.setForeground(Color.WHITE);
        logo.setBounds(50, 20, 50, 50); // Centered logo in sidebar
        sidebar.add(logo);

        // Sidebar Buttons
        JButton dashboardButton = createSidebarButton("Dashboard");
        dashboardButton.setBounds(10, 100, 130, 30);
        sidebar.add(dashboardButton);

        JButton catalogButton = createSidebarButton("Catalog");
        catalogButton.setBounds(10, 140, 130, 30);
        sidebar.add(catalogButton);

        JButton booksButton = createSidebarButton("Books");
        booksButton.setBounds(10, 180, 130, 30);
        sidebar.add(booksButton);

        JButton usersButton = createSidebarButton("Users");
        usersButton.setBounds(10, 220, 130, 30);
        sidebar.add(usersButton);

        JButton logoutButton = createSidebarButton("Logout");
        logoutButton.setBounds(10, 700, 130, 30);
        sidebar.add(logoutButton);

        // Add sidebar to frame
        add(sidebar);

        // Sidebar Button Actions
        dashboardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LibraryDashboard(); // Navigate to Dashboard
                dispose();
            }
        });

        catalogButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CatalogPage(); // Reload Catalog Page
                dispose();
            }
        });

        booksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new BooksPage(); // Navigate to Books
                dispose();
            }
        });

        usersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new UsersPage(); // Navigate to Users
                dispose();
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Exit the application
            }
        });

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

        // Table Content
        String[] columnNames = {"ID", "User ID", "Amount", "Due Date", "Date & Time", "Action"};
        Object[][] data = {
            {"001", "1", "002 Books", "13-03-2024", "25-02-2024 10:39:43", "Edit"},
            {"001", "1", "002 Books", "13-03-2024", "25-02-2024 10:39:43", "Edit"},
            // Add more rows as needed...
        };

        JTable table = new JTable(new DefaultTableModel(data, columnNames));
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(160, 80, 1020, 670); // Adjusted for layout consistency
        add(tableScrollPane);

        setVisible(true);
    }

    private JButton createSidebarButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        return button;
    }

    public static void main(String[] args) {
        new CatalogPage();
    }
}


// Books Page
class BooksPage extends JFrame {
    public BooksPage() {
        setTitle("Books - BookWorm Library");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Books Page", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        add(label, BorderLayout.CENTER);
        setVisible(true);
    }
}

// Users Page
class UsersPage extends JFrame {
    public UsersPage() {
        setTitle("Users - BookWorm Library");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Users Page", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        add(label, BorderLayout.CENTER);
        setVisible(true);
    }
}

// Dashboard Page
class DashboardPage extends JFrame {
    public DashboardPage() {
        setTitle("Dashboard - BookWorm Library");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Dashboard Page", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        add(label, BorderLayout.CENTER);
        setVisible(true);
    }
}