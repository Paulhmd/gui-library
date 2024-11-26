import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LibraryDashboard extends JFrame {

    public LibraryDashboard() {
        setTitle("Library Dashboard - BookWorm Library");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // Sidebar Panel
        JPanel sidebar = createSidebar();
        add(sidebar);

        // Header Panel
        JPanel header = createHeader();
        add(header);

        // Main Dashboard Content
        JLabel welcomeLabel = new JLabel("Welcome to the BookWorm Library Dashboard!", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setBounds(200, 100, 900, 50);
        add(welcomeLabel);

        JLabel infoLabel = new JLabel("Select options from the sidebar to navigate.", JLabel.CENTER);
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        infoLabel.setBounds(200, 160, 900, 30);
        add(infoLabel);

        // Sample Widget Panel for Future Extensions
        JPanel widgetPanel = new JPanel();
        widgetPanel.setBackground(Color.LIGHT_GRAY);
        widgetPanel.setBounds(200, 200, 900, 500);
        widgetPanel.setLayout(new GridLayout(2, 2, 20, 20)); // Placeholder for widgets
        add(widgetPanel);

        // Add Widgets with Real Data
        widgetPanel.add(createWidget("Total Books", String.valueOf(getBookCount())));
        widgetPanel.add(createWidget("Borrowed Books", String.valueOf(getBorrowedBookCount())));
        widgetPanel.add(createWidget("Registered Users", String.valueOf(getUserCount())));
        widgetPanel.add(createWidget("Overdue Returns", String.valueOf(getOverdueBookCount())));

        setVisible(true);
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setBackground(Color.BLACK);
        sidebar.setBounds(0, 0, 150, 800);
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
            yPosition += 40;

            // Add ActionListener for each button
            button.addActionListener(e -> handleSidebarActions(name));
        }

        // Logout Button (outside the loop)
        JButton logoutButton = createSidebarButton("Logout");
        logoutButton.setBounds(10, 700, 130, 30);
        sidebar.add(logoutButton);

        // Add logout button action
        logoutButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to logout?",
                    "Logout Confirmation",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        return sidebar;
    }

    private JPanel createHeader() {
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

        return header;
    }

    private JPanel createWidget(String title, String value) {
        JPanel widget = new JPanel();
        widget.setLayout(new BorderLayout());
        widget.setBackground(Color.WHITE);
        widget.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        JLabel widgetTitle = new JLabel(title, JLabel.CENTER);
        widgetTitle.setFont(new Font("Arial", Font.BOLD, 18));
        widget.add(widgetTitle, BorderLayout.NORTH);

        JLabel widgetValue = new JLabel(value, JLabel.CENTER);
        widgetValue.setFont(new Font("Arial", Font.BOLD, 36));
        widgetValue.setForeground(Color.BLUE);
        widget.add(widgetValue, BorderLayout.CENTER);

        return widget;
    }

    private JButton createSidebarButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        return button;
    }

    private void handleSidebarActions(String action) {
        switch (action) {
            case "Dashboard":
                new LibraryDashboard();  // Reload Library Dashboard
                break;
            case "Catalog":
                new CatalogPage();  // Navigate to Catalog page (you should create this page)
                break;
            case "Books":
                new BooksPage();  // Navigate to Books page (you should create this page)
                break;
            case "Users":
                new UsersPage();  // Navigate to Users page (you should create this page)
                break;
            case "Logout":
                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to logout?",
                        "Logout Confirmation",
                        JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);  // Exit the application
                }
                break;
        }
        dispose();  // Close the current window after action
    }

    private int getUserCount() {
        String query = "SELECT COUNT(*) FROM Users";
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching user count: " + e.getMessage());
        }
        return 0;
    }

    private int getBookCount() {
        String query = "SELECT COUNT(*) FROM Books";
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching book count: " + e.getMessage());
        }
        return 0;
    }

    private int getBorrowedBookCount() {
        String query = "SELECT COUNT(*) FROM BorrowedBooks";
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching borrowed book count: " + e.getMessage());
        }
        return 0;
    }

    private int getOverdueBookCount() {
        String query = "SELECT COUNT(*) FROM BorrowedBooks WHERE due_date < DATE('now')";
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching overdue book count: " + e.getMessage());
        }
        return 0;
    }

    public static void main(String[] args) {
        DatabaseConnection.createTables();  // Create tables when the app starts
        new LibraryDashboard();  // Start the dashboard
    }
}
