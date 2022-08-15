package ru.job4j.cars.store;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Ad;

import java.util.List;

@Repository
public class AdStore implements DefaultQuery {
    private final SessionFactory sf;

    public AdStore(SessionFactory sf) {
        this.sf = sf;
    }

    public List<Ad> findAll() {
        return tx(session -> {
           final Query<Ad> query =
                   session.createQuery("select distinct a from Ad a join fetch a.user order by a.created DESC",
                           Ad.class);
           return query.list();
        }, sf);
    }

    public Ad add(Ad ad) {
        return tx(session -> (Ad) session.merge(ad), sf);
    }

    public Ad getById(Integer id) {
        return tx(session -> {
            final Query<Ad> query = session.createQuery("select a from Ad a where a.id = :id", Ad.class)
                    .setParameter("id", id);
            return query.uniqueResult();
        }, sf);
    }

    public Ad update(Ad ad) {
        return (Ad) tx(session -> session.merge(ad), sf);
    }
}
