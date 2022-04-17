package ru.gb.dao.ProductDao;

import ru.gb.DBConnection;
import ru.gb.entity.Product;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class OldJdbcProductDao implements ProductDao {
    @Override
    public Iterable<Product> findAllProducts() {
        Set<Product> products = new HashSet<>();
        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM product");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                final Product product = Product.builder()
                        .id(resultSet.getLong("id"))
                        .title(resultSet.getString("title"))
                        .cost(resultSet.getBigDecimal("cost"))
                        .date(resultSet.getDate("manufacture_date").toLocalDate())
                        .build();
                products.add(product);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(connection);
        }
        return products;
    }

    @Override
    public Product findProductById(Long id) {
        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT p.id, title, cost, manufacture_date FROM product p WHERE p.id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet != null) {
                return Product.builder()
                        .id(resultSet.getLong("id"))
                        .title(resultSet.getString("title"))
                        .cost(resultSet.getBigDecimal("cost"))
                        .date(resultSet.getDate("manufacture_date").toLocalDate())
                        .build();
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(connection);
        }
        return null;
    }

    @Override
    public void addProduct(Product product) {
    }

    @Override
    public void deleteProductByID(Long id) {
        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM product p WHERE p.id = ?");
            statement.setLong(1, id);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(connection);
        }
    }

    @Override
    public void updateProduct(Product product) {
    }
}
