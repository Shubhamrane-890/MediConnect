package com.edutech.progressive.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Clinic;

public class ClinicDAOImpl implements ClinicDAO {

    @Override
    public int addClinic(Clinic clinic) throws SQLException {
        String sql = "INSERT INTO clinic (clinic_name, location, doctor_id, contact_number, established_year) VALUES (?, ?, ?, ?, ?)";
        int generatedId = -1;

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, clinic.getClinicName());
            statement.setString(2, clinic.getLocation());
            statement.setInt(3, clinic.getDoctorId());
            statement.setString(4, clinic.getContactNumber());
            statement.setInt(5, clinic.getEstablishedYear());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet rs = statement.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedId = rs.getInt(1);
                        clinic.setClinicId(generatedId);
                    }
                }
            }
        } catch (SQLException e) {
            throw e;
        }

        return generatedId;
    }

    @Override
    public Clinic getClinicById(int clinicId) throws SQLException {
        String sql = "SELECT clinic_id, clinic_name, location, doctor_id, contact_number, established_year FROM clinic WHERE clinic_id = ?";
        Clinic clinic = null;

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, clinicId);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    clinic = new Clinic();
                    clinic.setClinicId(rs.getInt("clinic_id"));
                    clinic.setClinicName(rs.getString("clinic_name"));
                    clinic.setLocation(rs.getString("location"));
                    clinic.setDoctorId(rs.getInt("doctor_id"));
                    clinic.setContactNumber(rs.getString("contact_number"));
                    clinic.setEstablishedYear(rs.getInt("established_year"));
                }
            }
        } catch (SQLException e) {
            throw e;
        }

        return clinic;
    }

    @Override
    public void updateClinic(Clinic clinic) throws SQLException {
        String sql = "UPDATE clinic SET clinic_name = ?, location = ?, doctor_id = ?, contact_number = ?, established_year = ? WHERE clinic_id = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, clinic.getClinicName());
            statement.setString(2, clinic.getLocation());
            statement.setInt(3, clinic.getDoctorId());
            statement.setString(4, clinic.getContactNumber());
            statement.setInt(5, clinic.getEstablishedYear());
            statement.setInt(6, clinic.getClinicId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public void deleteClinic(int clinicId) throws SQLException {
        String sql = "DELETE FROM clinic WHERE clinic_id = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, clinicId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public List<Clinic> getAllClinics() throws SQLException {
        String sql = "SELECT clinic_id, clinic_name, location, doctor_id, contact_number, established_year FROM clinic";
        List<Clinic> clinics = new ArrayList<>();

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Clinic clinic = new Clinic();
                clinic.setClinicId(rs.getInt("clinic_id"));
                clinic.setClinicName(rs.getString("clinic_name"));
                clinic.setLocation(rs.getString("location"));
                clinic.setDoctorId(rs.getInt("doctor_id"));
                clinic.setContactNumber(rs.getString("contact_number"));
                clinic.setEstablishedYear(rs.getInt("established_year"));

                clinics.add(clinic);
            }
        } catch (SQLException e) {
            throw e;
        }

        return clinics;
    }
}