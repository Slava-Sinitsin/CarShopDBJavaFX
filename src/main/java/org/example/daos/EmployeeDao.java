package org.example.daos;

import org.example.mappers.Employee;
import org.example.mappers.TransferInfo;
import org.example.utils.DateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.*;

public class EmployeeDao {
    public static String getAllEmployees(Session session) {
        Query<Employee> query = session.createQuery("FROM Employee", Employee.class);
        List<Employee> employeeList = query.list();
        StringBuilder resultString = new StringBuilder();
        for (Employee employee : employeeList) {
            resultString.append("ID: ").append(employee.getId())
                    .append(", Transfer Info ID: ").append(employee.getTransferInfoId())
                    .append(", First Name: ").append(employee.getFirstName())
                    .append(", Second Name: ").append(employee.getSecondName())
                    .append(", Middle Name: ").append(employee.getMiddleName())
                    .append(", Birth Date: ").append(DateUtil.formatDate(employee.getBirthDate()))
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

    public static List<Employee> getAllEmployeesList(Session session) {
        Query<Employee> query = session.createQuery(
                "SELECT e FROM Employee e " +
                        "JOIN TransferInfo t ON e.transferInfoId = t.id " +
                        "ORDER BY e.firstName, e.secondName, t.orderDate DESC",
                Employee.class
        );
        List<Employee> employees = query.list();
        return filterByLatestTransferDate(session, employees);
    }

    private static List<Employee> filterByLatestTransferDate(Session session, List<Employee> employees) {
        List<Employee> result = new ArrayList<>();
        Map<String, Map<String, Employee>> latestByFullName = new HashMap<>();
        for (Employee employee : employees) {
            String firstName = employee.getFirstName();
            String secondName = employee.getSecondName();
            Map<String, Employee> latestForName = latestByFullName.computeIfAbsent(firstName, k -> new HashMap<>());
            if (!latestForName.containsKey(secondName) ||
                    Objects.requireNonNull(getTransferOrderDate(session, employee.getTransferInfoId())).after(getTransferOrderDate(session, latestForName.get(secondName).getTransferInfoId()))) {
                latestForName.put(secondName, employee);
            }
        }

        for (Map<String, Employee> map : latestByFullName.values()) {
            result.addAll(map.values());
        }
        result.sort(Comparator.comparing(Employee::getId));
        result.removeIf(employee -> "Fired".equals(employee.getPosition()));
        return result;
    }

    private static Date getTransferOrderDate(Session session, Integer transferInfoId) {
        TransferInfo transferInfo = session.get(TransferInfo.class, transferInfoId);
        return transferInfo != null ? transferInfo.getOrderDate() : null;
    }

    @SuppressWarnings({"CallToPrintStackTrace", "DuplicatedCode"})
    public static void addEmployee(Session session, Employee employee) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}