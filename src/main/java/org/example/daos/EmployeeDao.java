package org.example.daos;

import org.example.mappers.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeeDao {
    public static String getAllEmployees(Session session) {
        Query<Employee> query = session.createQuery("FROM Employee", Employee.class);
        List<Employee> employeeList = query.list();
        StringBuilder resultString = new StringBuilder();
        for (Employee employee : employeeList) {
            resultString.append("ID: ").append(employee.getId())
                    .append(", First Name: ").append(employee.getFirstName())
                    .append(", Second Name: ").append(employee.getSecondName())
                    .append(", Middle Name: ").append(employee.getMiddleName())
                    .append(", Birth Date: ").append(employee.getBirthDate())
                    .append(", Address: ").append(employee.getAddress())
                    .append(", Position: ").append(employee.getPosition())
                    .append(", Salary: ").append(employee.getSalary())
                    .append("\n");
        }
        return resultString.toString();
    }

    public static Integer getEmployeeIdByNames(Session session, String firstName, String secondName) {
        Query<Employee> query = session.createQuery(
                "FROM Employee e WHERE e.firstName = :firstName AND e.secondName = :secondName", Employee.class);
        query.setParameter("firstName", firstName);
        query.setParameter("secondName", secondName);
        System.out.println(query.uniqueResult());
        return query.uniqueResult().getId();
    }
}