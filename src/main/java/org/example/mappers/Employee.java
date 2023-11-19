package org.example.mappers;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Employee {
    public Employee() {
    }

    public Employee(Integer transferInfoId, String firstName, String secondName, String middleName, Date birthDate, String address, String position, Integer salary) {
        this.transferInfoId = transferInfoId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.address = address;
        this.position = position;
        this.salary = salary;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "transfer_info_id", nullable = false)
    private Integer transferInfoId;
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
    @Column(name = "position", nullable = false, length = -1)
    private String position;
    @Basic
    @Column(name = "salary", nullable = false)
    private Integer salary;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTransferInfoId() {
        return transferInfoId;
    }

    public void setTransferInfoId(Integer transferInfoId) {
        this.transferInfoId = transferInfoId;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(transferInfoId, employee.transferInfoId) && Objects.equals(firstName, employee.firstName) && Objects.equals(secondName, employee.secondName) && Objects.equals(middleName, employee.middleName) && Objects.equals(birthDate, employee.birthDate) && Objects.equals(address, employee.address) && Objects.equals(position, employee.position) && Objects.equals(salary, employee.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, transferInfoId, firstName, secondName, middleName, birthDate, address, position, salary);
    }

    @Override
    public String toString() {
        return id + " " + firstName + " " + secondName + " ";
    }
}
