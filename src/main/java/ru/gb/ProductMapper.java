package ru.gb;

import org.springframework.jdbc.core.RowMapper;
import ru.gb.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {
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
