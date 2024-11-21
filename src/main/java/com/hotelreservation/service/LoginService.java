
package com.hotelreservation.service;

import com.hotelreservation.model.Staff;
import com.hotelreservation.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginService {

    public Staff authenticateStaff(String staffId, String password) {
        String sql = "SELECT * FROM Staffs WHERE staffId = ? AND password = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, staffId);
            pstmt.setString(2, password);
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Staff(
                    rs.getString("staffId"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("middleName"),
                    rs.getString("password"),
                    rs.getString("position")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
}
