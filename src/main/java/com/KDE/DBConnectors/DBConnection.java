package com.KDE.DBConnectors;

import java.sql.Connection;

public interface DBConnection {

    void connect(String path, String password);

    void closeConnection();

    Connection getConnection();
}
