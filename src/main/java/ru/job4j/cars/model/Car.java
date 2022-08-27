package ru.job4j.cars.model;

import ru.job4j.cars.service.CarBodyConverter;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Convert(converter = CarBodyConverter.class)
    private CarBody carBody;

    @ManyToOne(cascade = CascadeType.ALL)
    private CarModel carModel;

    public Car() {
    }

    public Car(CarBody carBody, CarModel carModel) {
        this.carBody = carBody;
        this.carModel = carModel;
    }

    public Car(int id, CarBody carBody, CarModel carModel) {
        this.id = id;
        this.carBody = carBody;
        this.carModel = carModel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CarBody getCarBody() {
        return carBody;
    }

    public void setCarBody(CarBody carBody) {
        this.carBody = carBody;
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }
}
