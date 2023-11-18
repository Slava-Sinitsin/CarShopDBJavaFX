package org.example.daos;

import org.example.mappers.TransferInfo;
import org.hibernate.Session;
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
                    .append(", Order Date: ").append(transferInfo.getOrderDate())
                    .append("\n");
        }
        return resultString.toString();
    }
}