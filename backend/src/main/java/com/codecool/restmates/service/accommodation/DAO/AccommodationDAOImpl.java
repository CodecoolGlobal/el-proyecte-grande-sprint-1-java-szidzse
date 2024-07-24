package com.codecool.restmates.service.accommodation.DAO;

import com.codecool.restmates.model.Accommodation;
import com.codecool.restmates.service.accommodation.DTO.AccommodationResponseDTO;
import com.codecool.restmates.service.accommodation.DTO.NewAccommodationDTO;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AccommodationDAOImpl implements AccommodationDAO {
    private DataSource dataSource;

    public AccommodationDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<AccommodationResponseDTO> findAllAccommodation() {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT accommodation_id, name, price_per_night FROM accommodation;";
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            List<AccommodationResponseDTO> accommodations = new ArrayList<>();
            while (resultSet.next()) {
                int accommodationId = resultSet.getInt("accommodation_id");
                String name = resultSet.getString("name");
                double pricePerNight = resultSet.getDouble("price_per_night");
                AccommodationResponseDTO accommodation = new AccommodationResponseDTO(accommodationId, name, pricePerNight);
                accommodations.add(accommodation);
            }
            return accommodations;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all accommodations: ", e);
        }
    }

    @Override
    public Accommodation findAccommodationById(long accommodationId) {
        String sql = "SELECT accommodation_id, name, price_per_night FROM accommodation WHERE accommodation_id = ?;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, accommodationId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("accommodation_id");
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

    @Override
    public AccommodationResponseDTO createAccommodation(NewAccommodationDTO accommodation) {
        String sql = " INSERT INTO accommodation (accommodation_id, name, price_per_night ) VALUES (?, ?, ?);";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, accommodation.accommodation_id());
            preparedStatement.setString(2, accommodation.name());
            preparedStatement.setDouble(3, accommodation.price_per_night());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            return new AccommodationResponseDTO(accommodation.accommodation_id(), accommodation.name(), accommodation.price_per_night());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
