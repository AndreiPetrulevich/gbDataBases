package ru.gb.dao.ProductDao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.gb.ProductExtractor;
import ru.gb.ProductMapper;
import ru.gb.entity.Product;

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
                "WHERE p.id = " + id;
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
}
