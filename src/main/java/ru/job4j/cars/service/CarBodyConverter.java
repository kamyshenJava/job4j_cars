package ru.job4j.cars.service;

import ru.job4j.cars.model.CarBody;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class CarBodyConverter implements AttributeConverter<CarBody, String> {
    @Override
    public String convertToDatabaseColumn(CarBody carBody) {
        if (carBody == null) {
            return null;
        }
        return carBody.getCode();
    }

    @Override
    public CarBody convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }
        return Stream.of(CarBody.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
