package org.example.daos;

import org.example.mappers.Car;
import org.example.mappers.Part;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PartDao {
    public static String getAllParts(Session session) {
        Query<Part> query = session.createQuery("FROM Part", Part.class);
        List<Part> partList = query.list();
        StringBuilder resultString = new StringBuilder();
        for (Part part : partList) {
            resultString.append("ID: ").append(part.getId())
                    .append(", Car ID: ").append(part.getCarId() != null ? part.getCarId() : "N/A")
                    .append(", Name: ").append(part.getName())
                    .append(", Price: ").append(part.getPrice())
                    .append(", Count: ").append(part.getCount())
                    .append("\n");
        }
        return resultString.toString();
    }

    public static List<Part> getPartsForCar(Session session, Integer carId) {
        Query<Part> query = session.createQuery("FROM Part WHERE carId = :carId", Part.class);
        query.setParameter("carId", carId);
        return query.list();
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public static void deletePart(Session session, Integer partId) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Part partToDelete = session.get(Part.class, partId);
            session.delete(partToDelete);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public static void updatePart(Session session, Part part) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(part);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static Part getPartById(Session session, Long partId) {
        return session.get(Part.class, partId);
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public static void addPart(Session session, Part part) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(part);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}