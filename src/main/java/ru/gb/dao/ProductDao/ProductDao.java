package ru.gb.dao.ProductDao;

import ru.gb.entity.Product;

public interface ProductDao {
    Iterable<Product> findAllProducts();
    Product findProductById(Long id);
    void addProduct(Product product);
    void deleteProductByID(Long id);
    void updateProduct(Product product);
}
