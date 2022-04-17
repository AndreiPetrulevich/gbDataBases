package ru.gb;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import ru.gb.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductExtractor implements ResultSetExtractor<Product> {
    @Override
    public Product extractData(ResultSet rs) throws SQLException, DataAccessException {
        return Product.builder()
                .id(rs.getLong("product_id"))
                .title(rs.getString("title"))
                .cost(rs.getBigDecimal("cost"))
                .date(rs.getDate("manufacture_date").toLocalDate())
                .build();
    }
}
