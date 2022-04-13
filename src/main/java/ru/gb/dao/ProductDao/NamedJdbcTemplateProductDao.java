package ru.gb.dao.ProductDao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.gb.ProductExtractor;
import ru.gb.ProductMapper;
import ru.gb.entity.Product;

import java.util.HashMap;
import java.util.Map;

//@Component
@RequiredArgsConstructor
public class NamedJdbcTemplateProductDao implements ProductDao {

    private final NamedParameterJdbcTemplate template;

    @Override
    public Iterable<Product> findAllProducts() {
        String sql = "SELECT * FROM product;";

        return template.query(sql, new ProductMapper());
    }

    @Override
    public Product findProductById(Long id) {
        String sql = "SELECT title, cost, MANUFACTURE_DATE, NAME\n" +
                "FROM PRODUCT p\n" +
                "INNER JOIN MANUFACTURER M on M.ID = p.MANUFACTURER_ID\n" +
                "WHERE p.id = :product_id;";
        Map<String, Object> params = new HashMap<>();
        params.put("product_id", id);

        return template.query(sql, params, new ProductExtractor());
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
