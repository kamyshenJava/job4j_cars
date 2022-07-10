package ru.job4j.cars;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import ru.job4j.cars.model.Ad;

import java.time.LocalDateTime;
import java.util.List;

public class AdRepository implements DefaultQuery {
    public List<Ad> getAdsWithin24Hours(SessionFactory sf) {
        return tx(session -> {
            Query query = session.createQuery("Select distinct a From Ad a where a.created >= :lastDay");
            query.setParameter("lastDay", LocalDateTime.now().minusHours(24));
            return query.list();
        }, sf);
    }

    public List<Ad> getAdsWithPhotos(SessionFactory sf) {
        return tx(session -> {
            Criteria cr = session.createCriteria(Ad.class);
            cr.add(Restrictions.isNotNull("photo"));
            return cr.list();
        }, sf);
    }

    public List<Ad> getAdsWithSpecificCarBody(SessionFactory sf, String carBody) {
        return tx(session -> {
            Query query = session.createQuery("Select distinct a from Ad a where a.carBody = :carBody");
            query.setParameter("carBody", carBody);
            return query.list();
        }, sf);
    }

    public static void main(String[] args) {
        AdRepository adRepository = new AdRepository();
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();

        try {
            final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();

            System.out.println("The ads within last 24 hours: ");
            for (Ad ad : adRepository.getAdsWithin24Hours(sf)) {
                System.out.println(ad);
            }

            System.out.println("The ads with photos: ");
            for (Ad ad : adRepository.getAdsWithPhotos(sf)) {
                System.out.println(ad);
            }

            System.out.println("The ads with hatchback: ");
            for (Ad ad : adRepository.getAdsWithSpecificCarBody(sf, "hatchback")) {
                System.out.println(ad);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
