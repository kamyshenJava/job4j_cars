package ru.job4j.cars.service;

import org.springframework.stereotype.Service;
import ru.job4j.cars.model.User;
import ru.job4j.cars.store.UserStore;

import java.util.Optional;

@Service
public class UserService {
    private final UserStore userStore;

    public UserService(UserStore userStore) {
        this.userStore = userStore;
    }

    public Optional<User> add(User user) {
        return userStore.add(user);
    }

    public Optional<User> findUserByNameAndPassword(String name, String password) {
        return userStore.findUserByNameAndPassword(name, password);
    }
}
