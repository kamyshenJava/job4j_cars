package ru.job4j.cars.model;

import javax.persistence.*;

@Entity
@Table(name = "car_models")
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String model;

    @ManyToOne(cascade = CascadeType.MERGE)
    private CarBrand brand;

    public CarModel() {
    }

    public CarModel(String model, CarBrand brand) {
        this.model = model;
        this.brand = brand;
    }

    public CarModel(int id, String model, CarBrand brand) {
        this.id = id;
        this.model = model;
        this.brand = brand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public CarBrand getBrand() {
        return brand;
    }

    public void setBrand(CarBrand brand) {
        this.brand = brand;
    }
}
