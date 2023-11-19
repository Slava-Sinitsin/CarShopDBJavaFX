package org.example.mappers;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "transfer_info", schema = "public", catalog = "car_shop")
public class TransferInfo {
    public TransferInfo() {
    }

    public TransferInfo(String previousPosition, String transferReason, Date orderDate) {
        this.previousPosition = previousPosition;
        this.transferReason = transferReason;
        this.orderDate = orderDate;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "previous_position", nullable = false, length = -1)
    private String previousPosition;
    @Basic
    @Column(name = "transfer_reason", nullable = false, length = -1)
    private String transferReason;
    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "order_date", nullable = false)
    private Date orderDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPreviousPosition() {
        return previousPosition;
    }

    public void setPreviousPosition(String previousPosition) {
        this.previousPosition = previousPosition;
    }

    public String getTransferReason() {
        return transferReason;
    }

    public void setTransferReason(String transferReason) {
        this.transferReason = transferReason;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransferInfo that = (TransferInfo) o;
        return Objects.equals(id, that.id) && Objects.equals(previousPosition, that.previousPosition) && Objects.equals(transferReason, that.transferReason) && Objects.equals(orderDate, that.orderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, previousPosition, transferReason, orderDate);
    }
}
