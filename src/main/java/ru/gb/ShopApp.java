package ru.gb;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.gb.config.HibernateConfig;
import ru.gb.dao.ManufacturerDao.ManufacturerDao;
import ru.gb.dao.ManufacturerDao.OldJdbcManufacturerDao;
import ru.gb.entity.Manufacturer;

public class ShopApp {
    public static void main(String[] args) {
        //findAllByOldJdbcManufacturerDao();
        findAllBySpringJdbcManufacturerDao();
        //findNameByIdByJdbcTemplateManufacturerDao(3L);
        //findByIdByNamedJdbcTemplateManufacturerDao(3L);
    }

    public static void findAllByOldJdbcManufacturerDao() {
        OldJdbcManufacturerDao oldJdbcManufacturerDao = new OldJdbcManufacturerDao();
        for (Manufacturer manufacturer : oldJdbcManufacturerDao.findAll()) {
            System.out.println(manufacturer);
        }
    }

    public static void findAllBySpringJdbcManufacturerDao() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);
        ManufacturerDao manufacturerDao = context.getBean(ManufacturerDao.class);
        for (Manufacturer manufacturer : manufacturerDao.findAll()) {
            System.out.println(manufacturer);
        }
    }

    public static void findNameByIdByJdbcTemplateManufacturerDao(Long id) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);
        ManufacturerDao manufacturerDao = context.getBean(ManufacturerDao.class);
        System.out.println(manufacturerDao.findNameById(id));
    }

    public static void findByIdByNamedJdbcTemplateManufacturerDao(Long id) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);
        ManufacturerDao manufacturerDao = context.getBean(ManufacturerDao.class);
        System.out.println(manufacturerDao.findById(id));
    }
}
