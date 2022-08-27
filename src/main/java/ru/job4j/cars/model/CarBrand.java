package ru.job4j.cars.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "car_brands")
public class CarBrand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String brand;

    public CarBrand() {
    }

    public CarBrand(String brand) {
        this.brand = brand;
    }

    public CarBrand(int id, String brand) {
        this.id = id;
        this.brand = brand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
