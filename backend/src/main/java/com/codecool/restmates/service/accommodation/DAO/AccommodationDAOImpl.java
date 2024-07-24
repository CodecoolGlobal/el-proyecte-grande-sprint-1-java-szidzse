package com.codecool.restmates.service.accommodation.DAO;

import com.codecool.restmates.model.Accommodation;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class AccommodationDAOImpl implements AccommodationDAO {
    private DataSource dataSource;

    public AccommodationDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Accommodation findAccommodationById(long accommodationId) {
        String sql = "SELECT id, name, price_per_night FROM accommodation WHERE id = ?;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, accommodationId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double pricePerNight = rs.getDouble("price_per_night");
                return new Accommodation(id, name, pricePerNight);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading accommodation: ", e);
        }
        return null;
    }
}
