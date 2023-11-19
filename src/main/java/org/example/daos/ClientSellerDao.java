package org.example.daos;

import org.example.mappers.ClientSeller;
import org.example.utils.DateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ClientSellerDao {
    public static String getAllClientSellers(Session session) {
        Query<ClientSeller> query = session.createQuery("FROM ClientSeller", ClientSeller.class);
        List<ClientSeller> clientSellerList = query.list();
        StringBuilder resultString = new StringBuilder();
        for (ClientSeller clientSeller : clientSellerList) {
            resultString.append("ID: ").append(clientSeller.getId())
                    .append(", Passport ID: ").append(clientSeller.getPassportId())
                    .append(", Car ID: ").append(clientSeller.getCarId())
                    .append(", Certifying Document ID: ").append(clientSeller.getCertifyingDocumentId())
                    .append(", Purchase Date: ").append(DateUtil.formatDate(clientSeller.getPurchaseDate()))
                    .append("\n");
        }
        return resultString.toString();
    }

    @SuppressWarnings({"DuplicatedCode", "CallToPrintStackTrace"})
    public static void addClientSeller(Session session, ClientSeller clientSeller) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(clientSeller);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}