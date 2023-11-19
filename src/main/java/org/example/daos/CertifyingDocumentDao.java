package org.example.daos;

import org.example.mappers.CertifyingDocument;
import org.example.utils.DateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CertifyingDocumentDao {
    public static String getAllCertifyingDocuments(Session session) {
        Query<CertifyingDocument> query = session.createQuery("FROM CertifyingDocument", CertifyingDocument.class);
        List<CertifyingDocument> certifyingDocumentList = query.list();
        StringBuilder resultString = new StringBuilder();
        for (CertifyingDocument certifyingDocument : certifyingDocumentList) {
            resultString.append("ID: ").append(certifyingDocument.getId())
                    .append(", Name: ").append(certifyingDocument.getName())
                    .append(", Issue Date: ").append(DateUtil.formatDate(certifyingDocument.getIssueDate()))
                    .append(", Issuer: ").append(certifyingDocument.getIssuer())
                    .append("\n");
        }
        return resultString.toString();
    }

    @SuppressWarnings({"CallToPrintStackTrace", "DuplicatedCode"})
    public static void addCertifyingDocument(Session session, CertifyingDocument certifyingDocument) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(certifyingDocument);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static boolean doesCertifyingDocumentExist(Session session, Integer certifyingDocumentId) {
        Query<Long> query = session.createQuery("SELECT COUNT(*) FROM CertifyingDocument WHERE id = :certifyingDocumentId", Long.class);
        query.setParameter("certifyingDocumentId", certifyingDocumentId);
        Long count = query.uniqueResult();
        return count != null && count > 0;
    }
}