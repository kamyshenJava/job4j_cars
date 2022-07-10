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

    public List<Ad> getAdsWithin24Hours(Session session) {
        Query query = session.createQuery("Select distinct a From Ad a where a.created >= :lastDay");
        query.setParameter("lastDay", LocalDateTime.now().minusHours(24));
        return query.list();
    }

    public List<Ad> getAdsWithPhotos(Session session) {
        Criteria cr = session.createCriteria(Ad.class);
        cr.add(Restrictions.isNotNull("photo"));
        return cr.list();
    }

    public List<Ad> getAdsWithSpecificCarBody(Session session, String carBody) {
        Query query = session.createQuery("Select distinct a from Ad a where a.carBody = :carBody");
        query.setParameter("carBody", carBody);
        return query.list();
    }

    public static void main(String[] args) {
        AdRepository adRepository = new AdRepository();
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            System.out.println("The ads within last 24 hours: ");
            for (Ad ad : adRepository.getAdsWithin24Hours(session)) {
                System.out.println(ad);
            }

            System.out.println("The ads with photos: ");
            for (Ad ad : adRepository.getAdsWithPhotos(session)) {
                System.out.println(ad);
            }

            System.out.println("The ads with hatchback: ");
            for (Ad ad : adRepository.getAdsWithSpecificCarBody(session, "hatchback")) {
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
