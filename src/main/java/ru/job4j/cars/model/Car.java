package ru.job4j.cars.model;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private byte[] photo;

    @ManyToOne(cascade = CascadeType.ALL)
    private CarBody carBody;

    @ManyToOne(cascade = CascadeType.ALL)
    private CarBrand carBrand;

    @ManyToOne(cascade = CascadeType.ALL)
    private CarModel carModel;

    public Car() {
    }

    public Car(byte[] photo, CarBody carBody, CarBrand carBrand, CarModel carModel) {
        this.photo = photo;
        this.carBody = carBody;
        this.carBrand = carBrand;
        this.carModel = carModel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public CarBody getCarBody() {
        return carBody;
    }

    public void setCarBody(CarBody carBody) {
        this.carBody = carBody;
    }

    public CarBrand getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(CarBrand carBrand) {
        this.carBrand = carBrand;
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }
}
