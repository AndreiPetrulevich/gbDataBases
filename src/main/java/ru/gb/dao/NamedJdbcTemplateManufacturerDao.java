package ru.gb.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.gb.entity.Manufacturer;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class NamedJdbcTemplateManufacturerDao implements ManufacturerDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Iterable<Manufacturer> findAll() {
        return null;
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
}
