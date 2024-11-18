import javax.swing.*;
import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;

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

        // Example placeholders for widgets
        widgetPanel.add(createWidget("Total Books", "1500"));
        widgetPanel.add(createWidget("Borrowed Books", "320"));
        widgetPanel.add(createWidget("Registered Users", "240"));
        widgetPanel.add(createWidget("Overdue Returns", "10"));

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

            JButton logoutButton = createSidebarButton("Logout");
            logoutButton.setBounds(10, 700, 130, 30);
            sidebar.add(logoutButton);

            // Add button actions
            button.addActionListener(e -> handleSidebarActions(name));
        }

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
                new LibraryDashboard(); // Reload Library Dashboard
                break;
            case "Catalog":
                new CatalogPage(); // Navigate to Catalog
                break;
            case "Books":
                new BooksPage(); // Navigate to Books
                break;
            case "Users":
                new UsersPage(); // Navigate to Users
                break;
            case "Logout":
                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to logout?",
                        "Logout Confirmation",
                        JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
                return; // Skip dispose if no logout
        }
        dispose();
    }

    public static void main(String[] args) {
        new LibraryDashboard();
    }
}
