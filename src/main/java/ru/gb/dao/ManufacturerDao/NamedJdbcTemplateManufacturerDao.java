package ru.gb.dao.ManufacturerDao;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.gb.entity.Manufacturer;
import ru.gb.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

//@Component
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
        String sql = "SELECT name, p.id as product_id, title, cost,MANUFACTURE_DATE, MANUFACTURER_ID\n" +
                "FROM MANUFACTURER m\n" +
                "INNER JOIN PRODUCT p on m.ID = p.MANUFACTURER_ID\n" +
                "WHERE m.id = :manufacturer_id;";
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("manufacturer_id", id);
        return namedParameterJdbcTemplate.query(sql, namedParameters, new ManufacturerWithProductExtractor());
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

    private static class ManufacturerWithProductExtractor implements ResultSetExtractor<Manufacturer> {

        @Override
        public Manufacturer extractData(ResultSet rs) throws SQLException, DataAccessException {
            Manufacturer manufacturer = null;
            while (rs.next()) {
                if (manufacturer == null) {
                    manufacturer = Manufacturer.builder()
                            .id(rs.getLong("manufacturer_id"))
                            .name(rs.getString("name"))
                            .build();
                }
                final Product product = Product.builder()
                        .id(rs.getLong("product_id"))
                        .title(rs.getString("title"))
                        .cost(rs.getBigDecimal("cost"))
                        .date(rs.getDate("manufacture_date").toLocalDate())
                        .build();
                manufacturer.addProduct(product);
            }
            return manufacturer;
        }
    }
}
