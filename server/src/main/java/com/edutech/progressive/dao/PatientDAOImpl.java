package com.edutech.progressive.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Patient;

public class PatientDAOImpl implements PatientDAO{

    public Connection connection;
    

    public PatientDAOImpl() throws SQLException {
        
            connection = DatabaseConnectionManager.getConnection();
        
    }


    @Override
    public int addPatient(Patient patient) throws SQLException{
        int result = -1;
        String query = "insert into patient(full_name, date_of_birth, contact_number, email, address) values(?,?,?,?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, patient.getFullName());
            ps.setDate(2, new java.sql.Date(patient.getDateOfBirth().getTime()));
            ps.setString(3, patient.getContactNumber());
            ps.setString(4, patient.getEmail());
            ps.setString(5, patient.getAddress());

            int rows = ps.executeUpdate();
            if (rows>0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                    
                }
                
            }
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }

    @Override
    public Patient getPatientById(int patientId) {
        Patient patient = null;
        String query = "select * from patient where patient_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, patientId);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                patient = new Patient(rs.getInt(1),rs.getString(2),rs.getDate(3),rs.getString(4),rs.getString(5),rs.getString(6));
                return patient;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patient;
    }

    @Override
    public void updatePatient(Patient patient) {
        String query = "update patient set full_name = ?, date_of_birth = ?, contact_number = ?, email = ?, address = ? where patient_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, patient.getFullName());
            ps.setDate(2, new java.sql.Date(patient.getDateOfBirth().getTime()));
            ps.setString(3, patient.getContactNumber());
            ps.setString(4, patient.getEmail());
            ps.setString(5, patient.getAddress());
            ps.setInt(6, patient.getPatientId());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePatient(int patientId){
        String query = "delete from patient where patient_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, patientId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Patient> getAllPatients() {
        List<Patient> list = new ArrayList<>();
        String query = "select * from patient";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Patient patient = new Patient(rs.getInt(1),rs.getString(2),rs.getDate(3),rs.getString(4),rs.getString(5),rs.getString(6));
                list.add(patient);

                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    
}