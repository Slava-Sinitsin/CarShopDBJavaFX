package org.example.daos;

import org.example.mappers.UsedInfo;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UsedInfoDao {
    public static String getAllUsedInfo(Session session) {
        Query<UsedInfo> query = session.createQuery("FROM UsedInfo", UsedInfo.class);
        List<UsedInfo> usedInfoList = query.list();
        StringBuilder resultString = new StringBuilder();
        for (UsedInfo usedInfo : usedInfoList) {
            resultString.append("ID: ").append(usedInfo.getId())
                    .append(", Employee ID: ").append(usedInfo.getEmployeeId())
                    .append(", Purchase Price: ").append(usedInfo.getPurchasePrice())
                    .append(", Certificate Date: ").append(usedInfo.getCertificateDate())
                    .append("\n");
        }
        return resultString.toString();
    }

    public static boolean doesUsedInfoExist(Session session, Integer usedInfoId) {
        Query<Long> query = session.createQuery("SELECT COUNT(*) FROM UsedInfo WHERE id = :usedInfoId", Long.class);
        query.setParameter("usedInfoId", usedInfoId);
        Long count = query.uniqueResult();
        return count != null && count > 0;
    }

    @SuppressWarnings({"CallToPrintStackTrace", "DuplicatedCode"})
    public static void addUsedInfo(Session session, UsedInfo usedInfo) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(usedInfo);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}