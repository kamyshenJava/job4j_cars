package ru.job4j.cars.model;

public enum CarBody {
    STATION_WAGON("SW"), SEDAN("S"), HATCHBACK("H"), VAN("V"), SUV("SU"), JEEP("J"),
    COUPE("CO"), SPORTS_CAR("SC"), MINIVAN("M"), PICKUP_TRUCK("P"), CONVERTIBLE("C");

    public final String code;

    CarBody(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return super.toString().replaceAll("_", " ");
    }
}
