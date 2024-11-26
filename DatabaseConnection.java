import java.sql.*;

public class DatabaseConnection {

    private static final String URL = "jdbc:sqlite:your_database_path.db"; // Replace with your actual SQLite database path
    private static final String DRIVER = "org.sqlite.JDBC";

    // Establish the connection to the database
    public static Connection connect() throws SQLException {
        try {
            Class.forName(DRIVER); // Load SQLite JDBC driver
            return DriverManager.getConnection(URL); // Return the connection object
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Error while connecting to the database: " + e.getMessage());
        }
    }

    // Create tables if they do not exist
    public static void createTables() {
        String createBooksTableSQL = "CREATE TABLE IF NOT EXISTS Books ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name TEXT NOT NULL, "
                + "type TEXT NOT NULL, "
                + "language TEXT NOT NULL);";

        String createUsersTableSQL = "CREATE TABLE IF NOT EXISTS Users ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "username TEXT NOT NULL, "
                + "email TEXT NOT NULL, "
                + "password TEXT NOT NULL);";

        String createBorrowedBooksTableSQL = "CREATE TABLE IF NOT EXISTS BorrowedBooks ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "user_id INTEGER, "
                + "book_id INTEGER, "
                + "borrow_date TEXT NOT NULL, "
                + "due_date TEXT NOT NULL, "
                + "FOREIGN KEY(user_id) REFERENCES Users(id), "
                + "FOREIGN KEY(book_id) REFERENCES Books(id));";

        try (Connection conn = connect(); 
             Statement stmt = conn.createStatement()) {
            stmt.execute(createBooksTableSQL);
            stmt.execute(createUsersTableSQL);
            stmt.execute(createBorrowedBooksTableSQL);
            System.out.println("Tables created (if they didn't exist already).");
        } catch (SQLException e) {
            System.out.println("Error creating tables: " + e.getMessage());
        }
    }

    // Insert a new book into the Books table
    public static void insertBook(String name, String type, String language) {
        String insertSQL = "INSERT INTO Books (name, type, language) VALUES (?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, name);
            pstmt.setString(2, type);
            pstmt.setString(3, language);
            pstmt.executeUpdate();
            System.out.println("Book inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Error inserting book: " + e.getMessage());
        }
    }

    // Insert a new user into the Users table
    public static void insertUser(String username, String email, String password) {
        String insertSQL = "INSERT INTO Users (username, email, password) VALUES (?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, username);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.executeUpdate();
            System.out.println("User inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Error inserting user: " + e.getMessage());
        }
    }

    // Insert a new borrowed book record into the BorrowedBooks table
    public static void insertBorrowedBook(int userId, int bookId, String borrowDate, String dueDate) {
        String insertSQL = "INSERT INTO BorrowedBooks (user_id, book_id, borrow_date, due_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, bookId);
            pstmt.setString(3, borrowDate);
            pstmt.setString(4, dueDate);
            pstmt.executeUpdate();
            System.out.println("Borrowed book record inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Error inserting borrowed book: " + e.getMessage());
        }
    }

    // Optional: Add a method to insert multiple books or users if needed
    public static void insertMultipleBooks(String[][] books) {
        String insertSQL = "INSERT INTO Books (name, type, language) VALUES (?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            for (String[] book : books) {
                pstmt.setString(1, book[0]);
                pstmt.setString(2, book[1]);
                pstmt.setString(3, book[2]);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            System.out.println("Multiple books inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Error inserting multiple books: " + e.getMessage());
        }
    }

    // Optional: Add a method to insert multiple users if needed
    public static void insertMultipleUsers(String[][] users) {
        String insertSQL = "INSERT INTO Users (username, email, password) VALUES (?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            for (String[] user : users) {
                pstmt.setString(1, user[0]);
                pstmt.setString(2, user[1]);
                pstmt.setString(3, user[2]);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            System.out.println("Multiple users inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Error inserting multiple users: " + e.getMessage());
        }
    }
}
