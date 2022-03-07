package netology.domain;

import java.util.Objects;

public class Smartphone extends Product {
    private String make;

    public Smartphone() {
        super();
    }

    public Smartphone(int id, String name, int price, String make) {
        super(id, name, price);
        this.make = make;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Smartphone smartphone = (Smartphone) o;
        return make == smartphone.make &&
                Objects.equals(make, smartphone.make);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), make);
    }

    @Override
    public String toString() {
        return "Smartphone{" +
                "make='" + make + '\'' + '}';
    }
}
