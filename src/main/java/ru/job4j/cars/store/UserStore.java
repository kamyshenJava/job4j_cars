package ru.job4j.cars.store;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.User;

import java.util.Optional;

@Repository
public class UserStore implements Store {

    private static final String FIND_USER_BY_NAME_AND_PASSWORD
            = "From User u where u.name = :name and u.password = :password";

    private final SessionFactory sf;

    public UserStore(SessionFactory sf) {
        this.sf = sf;
    }

    public Optional<User> add(User user) {
        try {
            return tx(session -> {
                int id = (int) session.save(user);
                user.setId(id);
                return Optional.of(user);
            }, sf);
        } catch (HibernateException e) {
            return Optional.empty();
        }
    }

    public Optional<User> findUserByNameAndPassword(String name, String password) {
        return tx(session -> {
            final Query<User> query
                    = session.createQuery(FIND_USER_BY_NAME_AND_PASSWORD);
            query.setParameter("name", name);
            query.setParameter("password", password);
            return query.uniqueResultOptional();
        }, sf);
    }
}
