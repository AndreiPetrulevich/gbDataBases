package ru.gb.dao.ProductDao;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.gb.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JdbcTemplateProductDao implements ProductDao {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Iterable<Product> findAllProducts() {
        String sql = "SELECT * FROM product";
        return jdbcTemplate.query(sql, new ProductMapper());
    }

    @Override
    public Product findProductById(Long id) {
        String sql = "SELECT title, cost, MANUFACTURE_DATE\n" +
                "FROM PRODUCT p\n" +
                "WHERE p.id = ?;";
        return jdbcTemplate.query(sql, new ProductExtractor());
    }

    @Override
    public void addProduct(Product product) {

    }

    @Override
    public void deleteProductByID(Long id) {

    }

    @Override
    public void updateProduct(Product product) {

    }

    private static class ProductMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Product.builder()
                    .id(rs.getLong("product_id"))
                    .title(rs.getString("title"))
                    .cost(rs.getBigDecimal("cost"))
                    .date(rs.getDate("manufacture_date").toLocalDate())
                    .build();
        }
    }

    private static class ProductExtractor implements ResultSetExtractor<Product> {
        @Override
        public Product extractData(ResultSet rs) throws SQLException, DataAccessException {
            if (rs != null) {
                return Product.builder()
                        .id(rs.getLong("product_id"))
                        .title(rs.getString("title"))
                        .cost(rs.getBigDecimal("cost"))
                        .date(rs.getDate("manufacture_date").toLocalDate())
                        .build();
            }
            return null;
        }
    }
}
