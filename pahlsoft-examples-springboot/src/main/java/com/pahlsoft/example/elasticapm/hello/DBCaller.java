package com.pahlsoft.example.elasticapm.hello;

import co.elastic.apm.api.ElasticApm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBCaller {

        private static final String DB_DRIVER = "org.h2.Driver";
        private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
        private static final String DB_USER = "";
        private static final String DB_PASSWORD = "";

    public static void init() throws Exception {
            try {
                insertWithStatement();
                dropAll();
                insertWithPreparedStatement();
                dropAll();
            } catch (SQLException e) {
                ElasticApm.captureException(e);
            }
        }

    private static void dropAll() throws Exception {
        WastedTime wastedTime = new WastedTime();
            Connection connection  = getDBConnection();
        Statement stmt = connection.createStatement();
        String DropQuery = "drop TABLE PERSON";
        stmt = connection.createStatement();
        wastedTime.randomTimeWaster();
        stmt.execute(DropQuery);
        connection.commit();
        connection.close();
    }

    private static void insertWithPreparedStatement() throws SQLException {
        WastedTime wastedTime = new WastedTime();
            Connection connection = getDBConnection();
            PreparedStatement createPreparedStatement = null;
            PreparedStatement insertPreparedStatement = null;
            PreparedStatement selectPreparedStatement = null;

            String CreateQuery = "CREATE TABLE PERSON(id int primary key, name varchar(255))";
            String InsertQuery = "INSERT INTO PERSON" + "(id, name) values" + "(?,?)";
            String SelectQuery = "select * from PERSON";

            try {
                connection.setAutoCommit(false);

                createPreparedStatement = connection.prepareStatement(CreateQuery);
                createPreparedStatement.executeUpdate();
                createPreparedStatement.close();

                insertPreparedStatement = connection.prepareStatement(InsertQuery);
                insertPreparedStatement.setInt(1, 1);
                insertPreparedStatement.setString(2, "Jose");
                wastedTime.randomTimeWaster();
                insertPreparedStatement.executeUpdate();
                insertPreparedStatement.close();

                selectPreparedStatement = connection.prepareStatement(SelectQuery);
                ResultSet rs = selectPreparedStatement.executeQuery();
                System.out.println("H2 In-Memory Database inserted through PreparedStatement");
                while (rs.next()) {
                    System.out.println("Id " + rs.getInt("id") + " Name " + rs.getString("name"));
                }
                selectPreparedStatement.close();

                connection.commit();
            } catch (SQLException e) {
                System.out.println("Exception Message " + e.getLocalizedMessage());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connection.close();
            }
        }

        private static void insertWithStatement() throws SQLException {
            Connection connection = getDBConnection();
            Statement stmt = null;
            try {
                connection.setAutoCommit(false);
                stmt = connection.createStatement();
                stmt.execute("CREATE TABLE PERSON(id int primary key, name varchar(255))");
                stmt.execute("INSERT INTO PERSON(id, name) VALUES(1, 'Anju')");
                stmt.execute("INSERT INTO PERSON(id, name) VALUES(2, 'Sonia')");
                stmt.execute("INSERT INTO PERSON(id, name) VALUES(3, 'Asha')");

                ResultSet rs = stmt.executeQuery("select * from PERSON");
                System.out.println("H2 In-Memory Database inserted through Statement");
                while (rs.next()) {
                    System.out.println("Id " + rs.getInt("id") + " Name " + rs.getString("name"));
                }

                stmt.close();
                connection.commit();
            } catch (SQLException e) {
                System.out.println("Exception Message " + e.getLocalizedMessage());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connection.close();
            }
        }

        private static Connection getDBConnection() {
            Connection dbConnection = null;
            try {
                Class.forName(DB_DRIVER);
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
            try {
                dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
                return dbConnection;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return dbConnection;
        }
    }
