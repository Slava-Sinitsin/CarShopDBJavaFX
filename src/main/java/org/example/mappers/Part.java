package org.example.mappers;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Part {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "car_id", nullable = true)
    private Integer carId;
    @Basic
    @Column(name = "name", nullable = false, length = -1)
    private String name;
    @Basic
    @Column(name = "price", nullable = false)
    private Integer price;
    @Basic
    @Column(name = "count", nullable = false)
    private Integer count;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Part part = (Part) o;
        return Objects.equals(id, part.id) && Objects.equals(carId, part.carId) && Objects.equals(name, part.name) && Objects.equals(price, part.price) && Objects.equals(count, part.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, carId, name, price, count);
    }

    @Override
    public String toString() {
        return id + " " + name;
    }
}
