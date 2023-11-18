package org.example.mappers;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "client_seller", schema = "public", catalog = "car_shop")
public class ClientSeller {
    public ClientSeller() {
    }

    public ClientSeller(Integer passportId, Integer carId, Integer certifyingDocumentId, Date purchaseDate) {
        this.passportId = passportId;
        this.carId = carId;
        this.certifyingDocumentId = certifyingDocumentId;
        this.purchaseDate = purchaseDate;
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
    @Column(name = "certifying_document_id", nullable = false)
    private Integer certifyingDocumentId;
    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "purchase_date", nullable = false)
    private Date purchaseDate;

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

    public Integer getCertifyingDocumentId() {
        return certifyingDocumentId;
    }

    public void setCertifyingDocumentId(Integer certifyingDocumentId) {
        this.certifyingDocumentId = certifyingDocumentId;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientSeller that = (ClientSeller) o;
        return Objects.equals(id, that.id) && Objects.equals(passportId, that.passportId) && Objects.equals(carId, that.carId) && Objects.equals(certifyingDocumentId, that.certifyingDocumentId) && Objects.equals(purchaseDate, that.purchaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, passportId, carId, certifyingDocumentId, purchaseDate);
    }
}
