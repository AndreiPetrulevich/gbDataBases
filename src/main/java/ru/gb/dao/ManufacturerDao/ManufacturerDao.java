package ru.gb.dao.ManufacturerDao;

import ru.gb.entity.Manufacturer;

public interface ManufacturerDao {
    Iterable<Manufacturer> findAll();
    Manufacturer findById(long id);
    void insert(Manufacturer manufacturer);
    void deleteById(Long id);
    void update(Manufacturer manufacturer);
    public String findNameById(Long id);
}
