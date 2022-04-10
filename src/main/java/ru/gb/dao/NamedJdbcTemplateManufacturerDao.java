package ru.gb.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.gb.entity.Manufacturer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class NamedJdbcTemplateManufacturerDao implements ManufacturerDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Iterable<Manufacturer> findAll() {
        String sql = "SELECT * FROM manufacturer";

        return namedParameterJdbcTemplate.query(sql, new ManufacturerMapper());
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
        String sql = "SELECT name FROM manufacturer WHERE id = :manufacturer_id";
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("manufacturer_id", id);
        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, String.class);
    }

    private static class ManufacturerMapper implements RowMapper<Manufacturer> {

        @Override
        public Manufacturer mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Manufacturer.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .build();
        }
    }
}
