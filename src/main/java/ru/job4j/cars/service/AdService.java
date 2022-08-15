package ru.job4j.cars.service;

import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Ad;
import ru.job4j.cars.store.AdStore;

import java.util.List;

@Service
public class AdService {
    private final AdStore adStore;

    public AdService(AdStore adStore) {
        this.adStore = adStore;
    }

    public List<Ad> findAll() {
        return adStore.findAll();
    }

    public Ad add(Ad ad) {
        return adStore.add(ad);
    }

    public Ad getById(Integer id) {
        return adStore.getById(id);
    }

    public Ad update(Ad ad) {
        return  adStore.update(ad);
    }
 }
