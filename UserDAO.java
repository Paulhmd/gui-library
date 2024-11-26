import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public static boolean usernameExists(String username) {
        String sql = "SELECT COUNT(*) FROM Users WHERE username = ?";
        
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next() && rs.getInt(1) > 0) {
                return true; // Username exists
            }
        } catch (SQLException e) {
            System.out.println("Error checking username: " + e.getMessage());
        }
        return false; // Username does not exist
    }
    

    // Register a new user in the database
    public static boolean registerUser(String firstName, String lastName, String contact, String email, String username, String password) {
        String sql = "INSERT INTO Users(first_name, last_name, contact_no, email, username, password) VALUES(?,?,?,?,?,?)";
        
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, contact);
            pstmt.setString(4, email);
            pstmt.setString(5, username);
            pstmt.setString(6, password);
            
            pstmt.executeUpdate();  // Insert the user data
            return true;
        } catch (SQLException e) {
            System.out.println("Error inserting user: " + e.getMessage());
        }
        return false;
    }

    // Validate user credentials during sign-in
    public static boolean validateCredentials(String username, String password) {
        String sql = "SELECT COUNT(*) FROM Users WHERE username = ? AND password = ?";
        
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next() && rs.getInt(1) > 0) {
                return true; // Valid credentials
            }
        } catch (SQLException e) {
            System.out.println("Error validating credentials: " + e.getMessage());
        }
        return false; // Invalid credentials
    }
}
