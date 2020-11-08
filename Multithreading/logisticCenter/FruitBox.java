package Multithreading.logisticCenter;

import java.util.Objects;

public class FruitBox {

    int weight;
    String name;

    public FruitBox(int weight, String name) {
        this.weight = weight;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FruitBox fruitBox = (FruitBox) o;
        return weight == fruitBox.weight &&
                Objects.equals(name, fruitBox.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, name);
    }
}
