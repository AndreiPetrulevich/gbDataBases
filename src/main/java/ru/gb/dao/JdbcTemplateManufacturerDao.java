package ru.gb.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.gb.entity.Manufacturer;

//@Component
@RequiredArgsConstructor
public class JdbcTemplateManufacturerDao implements ManufacturerDao {

    private final JdbcTemplate jdbcTemplate;

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
        return jdbcTemplate.queryForObject("SELECT name FROM manufacturer WHERE id = ?", String.class, id);
    }
}
