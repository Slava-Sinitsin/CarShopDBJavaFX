package org.example.mappers;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "certifying_document", schema = "public", catalog = "car_shop")
public class CertifyingDocument {
    public CertifyingDocument() {
    }

    public CertifyingDocument(Integer id, String name, Date issueDate, String issuer) {
        this.id = id;
        this.name = name;
        this.issueDate = issueDate;
        this.issuer = issuer;
    }

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "name", nullable = false, length = -1)
    private String name;
    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "issue_date", nullable = false)
    private Date issueDate;
    @Basic
    @Column(name = "issuer", nullable = false, length = -1)
    private String issuer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CertifyingDocument that = (CertifyingDocument) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(issueDate, that.issueDate) && Objects.equals(issuer, that.issuer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, issueDate, issuer);
    }
}
