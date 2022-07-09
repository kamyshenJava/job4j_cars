package ru.job4j.cars;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import ru.job4j.cars.model.Ad;

import java.time.LocalDateTime;
import java.util.List;

public class AdRepository {
    public static void main(String[] args) {
        List<Ad> rsl;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Query query = session.createQuery("Select distinct a From Ad a where a.created >= :lastDay");
            query.setParameter("lastDay", LocalDateTime.now().minusHours(24));
            rsl = query.list();
            System.out.println("The ads within last 24 hours: ");
            for (Ad ad : rsl) {
                System.out.println(ad);
            }

            Criteria cr = session.createCriteria(Ad.class);
            cr.add(Restrictions.isNotNull("photo"));
            rsl = cr.list();
            System.out.println("The ads with photos: ");
            for (Ad ad : rsl) {
                System.out.println(ad);
            }

            Criteria cr1 = session.createCriteria(Ad.class);
            cr1.add(Restrictions.eq("carBody", "hatchback"));
            rsl = cr1.list();
            System.out.println("The ads with hatchback: ");
            for (Ad ad : rsl) {
                System.out.println(ad);
            }

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
