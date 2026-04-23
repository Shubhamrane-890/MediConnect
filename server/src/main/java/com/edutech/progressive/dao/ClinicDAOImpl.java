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

public class ClinicDAOImpl implements ClinicDAO{
    // public Connection connection;

    

    // public ClinicDAOImpl() throws SQLException {
    //         this.connection = DatabaseConnectionManager.getConnection();

    // }



     @Override
    public int addClinic(Clinic clinic)throws SQLException {
        int result = -1;
        String query = "insert into clinic(clinic_name, location, doctor_id, contact_number,established_year) values(?,?,?,?,?)";
        try(Connection connection = DatabaseConnectionManager.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, clinic.getClinicName());
            ps.setString(2, clinic.getLocation());
            ps.setInt(3, clinic.getDoctorId());
            ps.setString(4, clinic.getContactNumber());
            ps.setInt(5, clinic.getEstablishedYear());
            int rows = ps.executeUpdate();
            if (rows > 0){
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
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
public Clinic getClinicById(int clinicId) throws SQLException {
    String query = "select * from clinic where clinic_id = ?";
    try (Connection connection = DatabaseConnectionManager.getConnection();
         PreparedStatement ps = connection.prepareStatement(query)) {

        ps.setInt(1, clinicId);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return new Clinic(
                    rs.getInt("clinic_id"),
                    rs.getString("clinic_name"),
                    rs.getString("location"),
                    rs.getInt("doctor_id"),
                    rs.getString("contact_number"),
                    rs.getInt("established_year")
                );
            }
        }
    } catch (SQLException e) {
        throw e;
    }
    return null;   // very important
}

    @Override
    public void updateClinic(Clinic clinic)throws SQLException {
        String query = "update clinic set clinic_name = ?, location = ?, doctor_id = ?, contact_number = ?, established_year = ? where clinic_id = ?";
        try(Connection connection = DatabaseConnectionManager.getConnection())  {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, clinic.getClinicName());
            ps.setString(2, clinic.getLocation());
            ps.setInt(3, clinic.getDoctorId());
            ps.setString(4, clinic.getContactNumber());
            ps.setInt(5, clinic.getEstablishedYear());
            ps.setInt(6, clinic.getClinicId());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public void deleteClinic(int clinicId) throws SQLException{
        String query = "delete from clinic where clinic_id = ?";
        try(Connection connection = DatabaseConnectionManager.getConnection())  {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, clinicId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public List<Clinic> getAllClinics() throws SQLException{
        List<Clinic> list = new ArrayList<>();
        String query = "select * from clinic";
        try(Connection connection = DatabaseConnectionManager.getConnection())  {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Clinic clinic = new Clinic(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getInt(6));
                list.add(clinic);   
            }
        } catch (SQLException e) {
          throw e;
        }
        return list;
    }

   

}