package org.example.mappers;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "used_info", schema = "public", catalog = "car_shop")
public class UsedInfo {
    public UsedInfo() {
    }

    public UsedInfo(Integer employeeId, Integer purchasePrice, Date certificateDate) {
        this.employeeId = employeeId;
        this.purchasePrice = purchasePrice;
        this.certificateDate = certificateDate;
    }

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false, insertable = false, updatable = false)
    private Employee employee;

    @OneToMany(mappedBy = "usedInfo", cascade = CascadeType.ALL)
    private List<Car> cars;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "employee_id", nullable = false)
    private Integer employeeId;
    @Basic
    @Column(name = "purchase_price", nullable = false)
    private Integer purchasePrice;
    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "certificate_date", nullable = false)
    private Date certificateDate;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> car) {
        this.cars = car;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Integer purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Date getCertificateDate() {
        return certificateDate;
    }

    public void setCertificateDate(Date certificateDate) {
        this.certificateDate = certificateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsedInfo usedInfo = (UsedInfo) o;
        return Objects.equals(id, usedInfo.id) && Objects.equals(employeeId, usedInfo.employeeId) && Objects.equals(purchasePrice, usedInfo.purchasePrice) && Objects.equals(certificateDate, usedInfo.certificateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeeId, purchasePrice, certificateDate);
    }
}
