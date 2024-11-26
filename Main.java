import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Initialize database tables
        try {
            DatabaseConnection.createTables(); // Create all necessary tables (Books, Users, BorrowedBooks)
        } catch (Exception e) {
            System.out.println("Error initializing database: " + e.getMessage());
            e.printStackTrace();
        }
        
        // Launch the main page of the application
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainPage();  // Start the application
            }
        });
    }
}
