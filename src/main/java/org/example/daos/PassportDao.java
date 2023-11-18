package org.example.daos;

import org.example.mappers.Passport;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PassportDao {
    public static String getAllPassports(Session session) {
        Query<Passport> query = session.createQuery("FROM Passport", Passport.class);
        List<Passport> passportList = query.list();
        StringBuilder resultString = new StringBuilder();
        for (Passport passport : passportList) {
            resultString.append("ID: ").append(passport.getId())
                    .append(", First Name: ").append(passport.getFirstName())
                    .append(", Second Name: ").append(passport.getSecondName())
                    .append(", Middle Name: ").append(passport.getMiddleName())
                    .append(", Birth Date: ").append(passport.getBirthDate())
                    .append(", Address: ").append(passport.getAddress())
                    .append(", Issue Date: ").append(passport.getIssueDate())
                    .append(", Gender: ").append(passport.getGender())
                    .append("\n");
        }
        return resultString.toString();
    }

    public static boolean doesPassportExist(Session session, Integer passportId) {
        Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Passport WHERE id = :passportId", Long.class);
        query.setParameter("passportId", passportId);
        Long count = query.uniqueResult();
        return count != null && count > 0;
    }

    @SuppressWarnings({"CallToPrintStackTrace", "DuplicatedCode"})
    public static void addPassport(Session session, Passport passport) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(passport);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}