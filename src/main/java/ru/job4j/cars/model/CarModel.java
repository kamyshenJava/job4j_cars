package ru.job4j.cars.model;

import javax.persistence.*;

@Entity
@Table(name = "car_models")
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    String model;

    public CarModel() {
    }

    public CarModel(String model) {
        this.model = model;
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

}
