package ru.gb.dao.ManufacturerDao;

import lombok.RequiredArgsConstructor;
import ru.gb.entity.Manufacturer;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

//@Component
@RequiredArgsConstructor
public class SpringJdbcManufacturerDao implements ManufacturerDao {

    private final DataSource dataSource;

    @Override
    public Iterable<Manufacturer> findAll() {
        Set<Manufacturer> manufacturers = new HashSet<>();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
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
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
