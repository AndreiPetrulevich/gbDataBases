package ru.gb.dao.ManufacturerDao;

import ru.gb.DBConnection;
import ru.gb.entity.Manufacturer;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class OldJdbcManufacturerDao implements ManufacturerDao {

    @Override
    public Iterable<Manufacturer> findAll() {
        Set<Manufacturer> manufacturers = new HashSet<>();
        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM manufacturer");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                final Manufacturer manufacturer = Manufacturer.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .build();
                manufacturers.add(manufacturer);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(connection);
        }
        return manufacturers;
    }

    @Override
    public Manufacturer findById(long id) {
        return null;
    }

    @Override
    public void insert(Manufacturer manufacturer) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void update(Manufacturer manufacturer) {

    }

    @Override
    public String findNameById(Long id) {
        return null;
    }
}
