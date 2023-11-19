package org.example.daos;

import org.example.mappers.Car;
import org.example.utils.DateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CarDao {
    public static String getAllCars(Session session) {
        Query<Car> query = session.createQuery("FROM Car", Car.class);
        List<Car> carList = query.list();
        StringBuilder resultString = new StringBuilder();
        for (Car car : carList) {
            resultString.append("ID: ").append(car.getId())
                    .append(", Used Info ID: ").append(car.getUsedInfoId())
                    .append(", Name: ").append(car.getName())
                    .append(", Color: ").append(car.getColor())
                    .append(", Engine Number: ").append(car.getEngineNumber())
                    .append(", Reg Number: ").append(car.getRegNumber())
                    .append(", Body Number: ").append(car.getBodyNumber())
                    .append(", Chassis Number: ").append(car.getChassisNumber())
                    .append(", Release Date: ").append(DateUtil.formatDate(car.getReleaseDate()))
                    .append(", Mileage: ").append(car.getMileage())
                    .append(", Release Price: ").append(car.getReleasePrice())
                    .append(", Sales Price: ").append(car.getSalesPrice())
                    .append("\n");
        }
        return resultString.toString();
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public static void addCar(Session session, Car car) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(car);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static Integer getCarIdByRegNumber(Session session, String regNumber) {
        Query<Car> query = session.createQuery("FROM Car c WHERE c.regNumber = :regNumber", Car.class);
        query.setParameter("regNumber", regNumber);
        return query.uniqueResult().getId();
    }

    public static List<Car> getAllCarsList(Session session) {
        Query<Car> query = session.createQuery("FROM Car", Car.class);
        return query.list();
    }

    public static List<String> getAllUniqueCarNames(Session session) {
        Query<String> query = session.createQuery("SELECT DISTINCT c.name FROM Car c", String.class);
        return query.list();
    }

    public static List<Car> getUnsoldCars(Session session) {
        Query<Car> query = session.createQuery("FROM Car c WHERE c.id NOT IN (SELECT cb.carId FROM ClientBuyer cb)", Car.class);
        return query.list();
    }
}