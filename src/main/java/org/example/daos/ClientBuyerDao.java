package org.example.daos;

import org.example.mappers.ClientBuyer;
import org.example.utils.DateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ClientBuyerDao {
    public static String getAllClientBuyers(Session session) {
        Query<ClientBuyer> query = session.createQuery("FROM ClientBuyer", ClientBuyer.class);
        List<ClientBuyer> clientBuyerList = query.list();
        StringBuilder resultString = new StringBuilder();
        for (ClientBuyer clientBuyer : clientBuyerList) {
            resultString.append("ID: ").append(clientBuyer.getId())
                    .append(", Passport ID: ").append(clientBuyer.getPassportId())
                    .append(", Car ID: ").append(clientBuyer.getCarId())
                    .append(", Sale Date: ").append(DateUtil.formatDate(clientBuyer.getSaleDate()))
                    .append(", Account Number: ").append(clientBuyer.getAccountNumber())
                    .append(", Payment Type: ").append(clientBuyer.getPaymentType())
                    .append("\n");
        }
        return resultString.toString();
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public static void addClientBuyer(Session session, ClientBuyer clientBuyer) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(clientBuyer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}