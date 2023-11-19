package org.example.daos;

import org.example.mappers.Car;
import org.example.mappers.TransferInfo;
import org.example.utils.DateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class TransferInfoDao {
    public static String getAllTransferInfo(Session session) {
        Query<TransferInfo> query = session.createQuery("FROM TransferInfo", TransferInfo.class);
        List<TransferInfo> transferInfoList = query.list();
        StringBuilder resultString = new StringBuilder();
        for (TransferInfo transferInfo : transferInfoList) {
            resultString.append("ID: ").append(transferInfo.getId())
                    .append(", Previous Position: ").append(transferInfo.getPreviousPosition())
                    .append(", Transfer Reason: ").append(transferInfo.getTransferReason())
                    .append(", Order Date: ").append(DateUtil.formatDate(transferInfo.getOrderDate()))
                    .append("\n");
        }
        return resultString.toString();
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public static void addTransferInfo(Session session, TransferInfo transferInfo) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(transferInfo);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}