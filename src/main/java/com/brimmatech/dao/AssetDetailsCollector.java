package com.brimmatech.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
public class AssetDetailsCollector {
    public List<AssetDetailsClass> assetCollector(String status) {

        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.initializeDatabase();
            List<AssetDetailsClass> list = new LinkedList<>();
            PreparedStatement preparedStatement = connection.prepareStatement("Select * from assetdetails where status = ?");
            preparedStatement.setString(1, status);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                AssetDetailsClass assetDetailsClass = new AssetDetailsClass();
                assetDetailsClass.setSerialnumber(resultSet.getString("serialnumber"));
                assetDetailsClass.setModelnumber(resultSet.getString("modelnumber"));
                assetDetailsClass.setAssettype(resultSet.getString("assettype"));
                assetDetailsClass.setAssetname(resultSet.getString("assetname"));
                assetDetailsClass.setReason(resultSet.getString("reason"));
                assetDetailsClass.setStatus(resultSet.getString("status"));
                list.add(assetDetailsClass);
            }
            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
