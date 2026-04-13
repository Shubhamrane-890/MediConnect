package com.edutech.progressive.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Patient;

public class PatientDAOImpl implements PatientDAO {

    @Override
    public int addPatient(Patient patient) throws SQLException {
        String sql = "INSERT INTO patient (full_name, date_of_birth, contact_number, email, address) VALUES (?, ?, ?, ?, ?)";
        int generatedId = -1;

        try (Connection connection = DatabaseConnectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, patient.getFullName());
            statement.setDate(2, new java.sql.Date(patient.getDateOfBirth().getTime()));
            statement.setString(3, patient.getContactNumber());
            statement.setString(4, patient.getEmail());
            statement.setString(5, patient.getAddress());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet rs = statement.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedId = rs.getInt(1);
                        patient.setPatientId(generatedId);
                    }
                }
            }
        } catch (SQLException e) {
            throw e;
        }

        return generatedId;
    }

    @Override
    public Patient getPatientById(int patientId) throws SQLException {
        String sql = "SELECT patient_id, full_name, date_of_birth, contact_number, email, address FROM patient WHERE patient_id = ?";
        Patient patient = null;

        try (Connection connection = DatabaseConnectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, patientId);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    patient = new Patient();
                    patient.setPatientId(rs.getInt("patient_id"));
                    patient.setFullName(rs.getString("full_name"));
                    patient.setDateOfBirth(rs.getDate("date_of_birth"));
                    patient.setContactNumber(rs.getString("contact_number"));
                    patient.setEmail(rs.getString("email"));
                    patient.setAddress(rs.getString("address"));
                }
            }
        } catch (SQLException e) {
            throw e;
        }

        return patient;
    }

    @Override
    public void updatePatient(Patient patient) throws SQLException {
        String sql = "UPDATE patient SET full_name = ?, date_of_birth = ?, contact_number = ?, email = ?, address = ? WHERE patient_id = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, patient.getFullName());
            statement.setDate(2, new java.sql.Date(patient.getDateOfBirth().getTime()));
            statement.setString(3, patient.getContactNumber());
            statement.setString(4, patient.getEmail());
            statement.setString(5, patient.getAddress());
            statement.setInt(6, patient.getPatientId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public void deletePatient(int patientId) throws SQLException {
        String sql = "DELETE FROM patient WHERE patient_id = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, patientId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public List<Patient> getAllPatients() throws SQLException {
        String sql = "SELECT patient_id, full_name, date_of_birth, contact_number, email, address FROM patient";
        List<Patient> patients = new ArrayList<>();

        try (Connection connection = DatabaseConnectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Patient patient = new Patient();
                patient.setPatientId(rs.getInt("patient_id"));
                patient.setFullName(rs.getString("full_name"));
                patient.setDateOfBirth(rs.getDate("date_of_birth"));
                patient.setContactNumber(rs.getString("contact_number"));
                patient.setEmail(rs.getString("email"));
                patient.setAddress(rs.getString("address"));

                patients.add(patient);
            }
        } catch (SQLException e) {
            throw e;
        }

        return patients;
    }
}