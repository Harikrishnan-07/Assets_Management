package com.brimmatech.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AssetUpdatorDetail {
    public void assetUpdator(String serialnumber, String modelnumber, String assettype, String assetname, String status, String reason) {
        try {
            Connection connection = DatabaseConnection.initializeDatabase();
            PreparedStatement preparedStatement = connection.prepareStatement("Insert into assetdetails (serialnumber,modelnumber,assettype,assetname,status,reason) values(?,?,?,?,?,?)");
            preparedStatement.setString(1, serialnumber);
            preparedStatement.setString(2, modelnumber);
            preparedStatement.setString(3, assettype);
            preparedStatement.setString(4, assetname);
            preparedStatement.setString(5, status);
            preparedStatement.setString(6, reason);
            preparedStatement.executeUpdate();

        } catch (SQLException | IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
