package ru.gb.dao.ProductDao;

import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.entity.Product;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class HibernateProductDao implements ProductDao {

    private final SessionFactory sessionFactory;

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAllProducts() {
        return Collections.unmodifiableList(sessionFactory.getCurrentSession().createQuery("FROM Product p").list());
    }

    @Override
    @Transactional(readOnly = true)
    public Product findProductById(Long id) {
        return (Product) sessionFactory.getCurrentSession().getNamedQuery("Product.findProductById").setParameter("id", id).uniqueResult();
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
