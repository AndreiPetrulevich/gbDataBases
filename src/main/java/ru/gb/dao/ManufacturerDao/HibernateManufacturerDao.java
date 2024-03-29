package ru.gb.dao.ManufacturerDao;

import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.entity.Manufacturer;

import java.util.Collections;
import java.util.List;


@Component
@RequiredArgsConstructor
public class HibernateManufacturerDao implements ManufacturerDao {

    private final SessionFactory sessionFactory;

    @Override
    @Transactional(readOnly = true)
    public List<Manufacturer> findAll() {
        return Collections.unmodifiableList(sessionFactory.getCurrentSession().createQuery("FROM Manufacturer m").list());
    }

    @Override
    @Transactional(readOnly = true)
    public String findNameById(Long id) {
        return (String) sessionFactory.getCurrentSession().getNamedQuery("Manufacturer.findNameById")
                .setParameter("id", id).uniqueResult();
    }

    @Override
    @Transactional(readOnly = true)
    public Manufacturer findById(long id) {

        return (Manufacturer) sessionFactory.getCurrentSession().getNamedQuery("Manufacturer.findById")
                .setParameter("id", id).uniqueResult();
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
}
