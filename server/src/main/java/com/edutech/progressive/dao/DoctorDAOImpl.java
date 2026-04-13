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

public class DoctorDAOImpl implements DoctorDAO {

    @Override
    public int addDoctor(Doctor doctor) throws SQLException {
        String sql = "INSERT INTO doctor (full_name, specialty, contact_number, email, years_of_experience) VALUES (?, ?, ?, ?, ?)";
        int generatedId = -1;

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, doctor.getFullName());
            statement.setString(2, doctor.getSpecialty());
            statement.setString(3, doctor.getContactNumber());
            statement.setString(4, doctor.getEmail());
            statement.setInt(5, doctor.getYearsOfExperience());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet rs = statement.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedId = rs.getInt(1);
                        doctor.setDoctorId(generatedId);
                    }
                }
            }
        } catch (SQLException e) {
            throw e;
        }

        return generatedId;
    }

    @Override
    public Doctor getDoctorById(int doctorId) throws SQLException {
        String sql = "SELECT doctor_id, full_name, specialty, contact_number, email, years_of_experience FROM doctor WHERE doctor_id = ?";
        Doctor doctor = null;

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, doctorId);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    doctor = new Doctor();
                    doctor.setDoctorId(rs.getInt("doctor_id"));
                    doctor.setFullName(rs.getString("full_name"));
                    doctor.setSpecialty(rs.getString("specialty"));
                    doctor.setContactNumber(rs.getString("contact_number"));
                    doctor.setEmail(rs.getString("email"));
                    doctor.setYearsOfExperience(rs.getInt("years_of_experience"));
                }
            }
        } catch (SQLException e) {
            throw e;
        }

        return doctor;
    }

    @Override
    public void updateDoctor(Doctor doctor) throws SQLException {
        String sql = "UPDATE doctor SET full_name = ?, specialty = ?, contact_number = ?, email = ?, years_of_experience = ? WHERE doctor_id = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, doctor.getFullName());
            statement.setString(2, doctor.getSpecialty());
            statement.setString(3, doctor.getContactNumber());
            statement.setString(4, doctor.getEmail());
            statement.setInt(5, doctor.getYearsOfExperience());
            statement.setInt(6, doctor.getDoctorId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public void deleteDoctor(int doctorId) throws SQLException {
        String sql = "DELETE FROM doctor WHERE doctor_id = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, doctorId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public List<Doctor> getAllDoctors() throws SQLException {
        String sql = "SELECT doctor_id, full_name, specialty, contact_number, email, years_of_experience FROM doctor";
        List<Doctor> doctors = new ArrayList<>();

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Doctor doctor = new Doctor();
                doctor.setDoctorId(rs.getInt("doctor_id"));
                doctor.setFullName(rs.getString("full_name"));
                doctor.setSpecialty(rs.getString("specialty"));
                doctor.setContactNumber(rs.getString("contact_number"));
                doctor.setEmail(rs.getString("email"));
                doctor.setYearsOfExperience(rs.getInt("years_of_experience"));

                doctors.add(doctor);
            }
        } catch (SQLException e) {
            throw e;
        }

        return doctors;
    }
}