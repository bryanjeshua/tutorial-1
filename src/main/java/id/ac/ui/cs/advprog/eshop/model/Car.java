package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;
import java.util.Objects;
@Getter @Setter
public class Car extends Product{
    private String carColor;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        if (!super.equals(o)) return false; // Check equality based on superclass (Product) attribute
        Car car = (Car) o;
        return Objects.equals(carColor, car.carColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), carColor);
    }
}
