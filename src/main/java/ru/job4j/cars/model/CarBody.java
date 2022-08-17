package ru.job4j.cars.model;

import javax.persistence.*;

@Entity
@Table(name = "car_bodies")
public class CarBody {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    String body;

    public CarBody() {
    }

    public CarBody(String body) {
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
