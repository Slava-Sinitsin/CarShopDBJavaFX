package org.example.mappers;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Car {
    public Car() {
    }

    public Car(Integer usedInfoId, String name, String color, String engineNumber, String regNumber, String bodyNumber, String chassisNumber, Date releaseDate, Integer mileage, Integer releasePrice, Integer salesPrice) {
        this.usedInfoId = usedInfoId;
        this.name = name;
        this.color = color;
        this.engineNumber = engineNumber;
        this.regNumber = regNumber;
        this.bodyNumber = bodyNumber;
        this.chassisNumber = chassisNumber;
        this.releaseDate = releaseDate;
        this.mileage = mileage;
        this.releasePrice = releasePrice;
        this.salesPrice = salesPrice;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "used_info_id", nullable = false)
    private Integer usedInfoId;
    @Basic
    @Column(name = "name", nullable = false, length = -1)
    private String name;
    @Basic
    @Column(name = "color", nullable = false, length = -1)
    private String color;
    @Basic
    @Column(name = "engine_number", nullable = false, length = -1)
    private String engineNumber;
    @Basic
    @Column(name = "reg_number", nullable = false, length = -1)
    private String regNumber;
    @Basic
    @Column(name = "body_number", nullable = false, length = -1)
    private String bodyNumber;
    @Basic
    @Column(name = "chassis_number", nullable = false, length = -1)
    private String chassisNumber;
    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "release_date", nullable = false)
    private Date releaseDate;
    @Basic
    @Column(name = "mileage", nullable = false)
    private Integer mileage;
    @Basic
    @Column(name = "release_price", nullable = false)
    private Integer releasePrice;
    @Basic
    @Column(name = "sales_price", nullable = false)
    private Integer salesPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUsedInfoId() {
        return usedInfoId;
    }

    public void setUsedInfoId(Integer usedInfoId) {
        this.usedInfoId = usedInfoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getBodyNumber() {
        return bodyNumber;
    }

    public void setBodyNumber(String bodyNumber) {
        this.bodyNumber = bodyNumber;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Integer getReleasePrice() {
        return releasePrice;
    }

    public void setReleasePrice(Integer releasePrice) {
        this.releasePrice = releasePrice;
    }

    public Integer getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(Integer salesPrice) {
        this.salesPrice = salesPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id) && Objects.equals(usedInfoId, car.usedInfoId) && Objects.equals(name, car.name) && Objects.equals(color, car.color) && Objects.equals(engineNumber, car.engineNumber) && Objects.equals(regNumber, car.regNumber) && Objects.equals(bodyNumber, car.bodyNumber) && Objects.equals(chassisNumber, car.chassisNumber) && Objects.equals(releaseDate, car.releaseDate) && Objects.equals(mileage, car.mileage) && Objects.equals(releasePrice, car.releasePrice) && Objects.equals(salesPrice, car.salesPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usedInfoId, name, color, engineNumber, regNumber, bodyNumber, chassisNumber, releaseDate, mileage, releasePrice, salesPrice);
    }

    @Override
    public String toString() {
        return name + " \"" + regNumber + "\"";
    }
}
