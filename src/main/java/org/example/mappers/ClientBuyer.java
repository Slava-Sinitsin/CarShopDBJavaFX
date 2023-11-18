package org.example.mappers;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "client_buyer", schema = "public", catalog = "car_shop")
public class ClientBuyer {
    public ClientBuyer() {
    }

    public ClientBuyer(Integer passportId, Integer carId, Date saleDate, Long accountNumber, String paymentType) {
        this.passportId = passportId;
        this.carId = carId;
        this.saleDate = saleDate;
        this.accountNumber = accountNumber;
        this.paymentType = paymentType;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "passport_id", nullable = false)
    private Integer passportId;
    @Basic
    @Column(name = "car_id", nullable = false)
    private Integer carId;
    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "sale_date", nullable = false)
    private Date saleDate;
    @Basic
    @Column(name = "account_number", nullable = false)
    private Long accountNumber;
    @Basic
    @Column(name = "payment_type", nullable = false, length = -1)
    private String paymentType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPassportId() {
        return passportId;
    }

    public void setPassportId(Integer passportId) {
        this.passportId = passportId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientBuyer that = (ClientBuyer) o;
        return Objects.equals(id, that.id) && Objects.equals(passportId, that.passportId) && Objects.equals(carId, that.carId) && Objects.equals(saleDate, that.saleDate) && Objects.equals(accountNumber, that.accountNumber) && Objects.equals(paymentType, that.paymentType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, passportId, carId, saleDate, accountNumber, paymentType);
    }
}
