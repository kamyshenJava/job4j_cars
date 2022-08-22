package ru.job4j.cars.store;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Ad;

import java.util.List;

@Repository
public class AdStore implements Store {

    private static final String FIND_ALL_ADS = "select distinct a from Ad a join fetch a.user order by a.created DESC";

    private static final String FIND_ADS_BY_ID = "select a from Ad a where a.id = :id";
    private final SessionFactory sf;

    public AdStore(SessionFactory sf) {
        this.sf = sf;
    }

    public List<Ad> findAll() {
        return tx(session -> {
           final Query<Ad> query =
                   session.createQuery(FIND_ALL_ADS,
                           Ad.class);
           return query.list();
        }, sf);
    }

    public Ad add(Ad ad) {
        return tx(session -> (Ad) session.merge(ad), sf);
    }

    public Ad getById(Integer id) {
        return tx(session -> {
            final Query<Ad> query = session.createQuery(FIND_ADS_BY_ID, Ad.class)
                    .setParameter("id", id);
            return query.uniqueResult();
        }, sf);
    }

    public Ad update(Ad ad) {
        return (Ad) tx(session -> session.merge(ad), sf);
    }
}
