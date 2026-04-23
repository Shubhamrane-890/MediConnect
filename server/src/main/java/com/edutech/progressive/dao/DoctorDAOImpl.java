package com.edutech.progressive.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Doctor;

public class DoctorDAOImpl implements DoctorDAO{

    // public Connection connection;

    // public DoctorDAOImpl() {
    //     try {
    //         connection = DatabaseConnectionManager.getConnection();
    //     } catch (SQLException e) {
    //         // TODO Auto-generated catch block
    //         e.printStackTrace();
    //     }
    // }

    @Override
    public int addDoctor(Doctor doctor)throws SQLException {
        int result = 0;
        String query = "insert into doctor(full_name, specialty, contact_number, email,years_of_experience) values(?,?,?,?,?)";
        try (Connection connection = DatabaseConnectionManager.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, doctor.getFullName());
            ps.setString(2, doctor.getSpecialty());
            ps.setString(3, doctor.getContactNumber()); 
            ps.setString(4, doctor.getEmail());
            ps.setInt(5, doctor.getYearsOfExperience()); 
            int rows = ps.executeUpdate();
            if (rows>0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    doctor.setDoctorId(rs.getInt(1));
                    result = rs.getInt(1);
                    return result;
                    
                }
  
            } 
        } catch (SQLException e) {
            throw e;

        }
        return result;
    }

    

    @Override
    public Doctor getDoctorById(int doctorId) throws SQLException{
        Doctor doctor = null;
        String query = "select * from doctor where doctor_id = ?";
        try(Connection connection = DatabaseConnectionManager.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, doctorId);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                doctor = new Doctor(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6));
                return doctor;
            }
        } catch (SQLException e) {
            throw e;
        }
        return doctor;
    }

    @Override
    public void updateDoctor(Doctor doctor) throws SQLException{
        String query = "update doctor set full_name = ?, specialty = ?,contact_number = ?,email = ?, years_of_experience = ? where doctor_id = ?";
        try(Connection connection = DatabaseConnectionManager.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, doctor.getFullName());
            ps.setString(2, doctor.getSpecialty());
            ps.setString(3, doctor.getContactNumber());
            ps.setString(4, doctor.getEmail());
            ps.setInt(5, doctor.getYearsOfExperience());
            ps.setInt(6, doctor.getDoctorId());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public void deleteDoctor(int doctorId) throws SQLException{
        String query = "delete from doctor where doctor_id = ?";
        try (Connection connection = DatabaseConnectionManager.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, doctorId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public List<Doctor> getAllDoctors() throws SQLException{
        List<Doctor> list = new ArrayList<>();
        String query = "select * from doctor";
        try(Connection connection = DatabaseConnectionManager.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Doctor doctor = new Doctor(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6));
                list.add(doctor);   
            }
        } catch (SQLException e) {
            throw e;
        }
        return list;
    }

    
}