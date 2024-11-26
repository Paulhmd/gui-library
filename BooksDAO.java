import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BooksDAO {

    // Method to retrieve all books from the database
    public static List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM Books";

        try (Connection conn = DatabaseConnection.connect(); 
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String type = rs.getString("type");
                String language = rs.getString("language");
                books.add(new Book(id, name, type, language));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving books: " + e.getMessage());
        }
        return books;
    }

    // Method to add a new book to the database
    public static void addBook(Book book) {
        String sql = "INSERT INTO Books (name, type, language) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, book.getName());
            pstmt.setString(2, book.getType());
            pstmt.setString(3, book.getLanguage());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding book: " + e.getMessage());
        }
    }

    // Method to update an existing book in the database
    public static void updateBook(Book book) {
        String sql = "UPDATE Books SET name = ?, type = ?, language = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, book.getName());
            pstmt.setString(2, book.getType());
            pstmt.setString(3, book.getLanguage());
            pstmt.setInt(4, book.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating book: " + e.getMessage());
        }
    }

    // Method to delete a book from the database
    public static void deleteBook(int id) {
        String deleteSql = "DELETE FROM Books WHERE id = ?";
        String resetSequenceSql = "UPDATE sqlite_sequence SET seq = (SELECT MAX(id) FROM Books) WHERE name = 'Books'";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement deleteStmt = conn.prepareStatement(deleteSql);
             Statement resetSeqStmt = conn.createStatement()) {

            // Step 1: Delete the book
            deleteStmt.setInt(1, id);
            deleteStmt.executeUpdate();
            System.out.println("Book with ID " + id + " deleted.");

            // Step 2: Reset the sequence
            resetSeqStmt.executeUpdate(resetSequenceSql);
            System.out.println("Book ID sequence reset.");

        } catch (SQLException e) {
            System.out.println("Error deleting book: " + e.getMessage());
        }
    }

    // Method to retrieve a book by its ID
    public static Book getBookById(int id) {
        String sql = "SELECT * FROM Books WHERE id = ?";
        try (Connection conn = DatabaseConnection.connect(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    String type = rs.getString("type");
                    String language = rs.getString("language");
                    return new Book(id, name, type, language);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving book by ID: " + e.getMessage());
        }
        return null;
    }
}
