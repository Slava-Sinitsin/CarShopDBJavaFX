package org.example.mappers;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Passport {
    public Passport() {
    }

    public Passport(Integer id, String firstName, String secondName, String middleName, Date birthDate, String address, Date issueDate, String gender) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.address = address;
        this.issueDate = issueDate;
        this.gender = gender;
    }

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "first_name", nullable = false, length = -1)
    private String firstName;
    @Basic
    @Column(name = "second_name", nullable = false, length = -1)
    private String secondName;
    @Basic
    @Column(name = "middle_name", nullable = false, length = -1)
    private String middleName;
    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date", nullable = false)
    private Date birthDate;
    @Basic
    @Column(name = "address", nullable = false, length = -1)
    private String address;
    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "issue_date", nullable = false)
    private Date issueDate;
    @Basic
    @Column(name = "gender", nullable = false, length = -1)
    private String gender;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passport passport = (Passport) o;
        return Objects.equals(id, passport.id) && Objects.equals(firstName, passport.firstName) && Objects.equals(secondName, passport.secondName) && Objects.equals(middleName, passport.middleName) && Objects.equals(birthDate, passport.birthDate) && Objects.equals(address, passport.address) && Objects.equals(issueDate, passport.issueDate) && Objects.equals(gender, passport.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, secondName, middleName, birthDate, address, issueDate, gender);
    }
}
