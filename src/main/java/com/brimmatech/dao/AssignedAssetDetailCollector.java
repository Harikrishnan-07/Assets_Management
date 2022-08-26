package com.brimmatech.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class AssignedAssetDetailCollector {
    public List<AssetDetailsClass> assignedAssetDetail() {
        List<AssetDetailsClass> list = new LinkedList<>();
        try {

            Connection connection = DatabaseConnection.initializeDatabase();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from assignedasset\n" +
                    " LEFT JOIN\n" +
                    " employeedetails ON assignedasset.emailid=employeedetails.emailid\n" +
                    " LEFT JOIN\n" +
                    " assetdetails ON assignedasset.serialnumber=assetdetails.serialnumber where assetreturndate IS NULL;");


            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                AssetDetailsClass assetDetailsClass = new AssetDetailsClass();
                assetDetailsClass.setEmailid(resultSet.getString("emailid"));
                assetDetailsClass.setSerialnumber(resultSet.getString("serialnumber"));
                assetDetailsClass.setAllocatedon(resultSet.getString("allocatedon"));
                assetDetailsClass.setAssettype(resultSet.getString("assettype"));
                assetDetailsClass.setAssetname(resultSet.getString("assetname"));
                assetDetailsClass.setModelnumber(resultSet.getString("modelnumber"));
                assetDetailsClass.setStatus(resultSet.getString("status"));

                list.add(assetDetailsClass);
            }
            return list;

        } catch (SQLException | IOException exception) {
            throw new RuntimeException(exception);
        }

    }
}