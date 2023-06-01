package Resources;

import GUI.Dialogs.DialogError;
import Resources.Models.CourseGroups;
import Resources.Models.NormInHours;
import Resources.Models.Teacher;
import Resources.Models.Teacher_and_DiplomaStudentsData;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.Scanner;

public class ConnectorDB {
    private static String host;
    private static String port;
    private static String user;
    private static String password;
    private static String databaseName;

    public static void setHost(String host) {
        ConnectorDB.host = host;
        fillDBSettingsFile(host, 0);
    }
    public static void setPort(String port) {
        ConnectorDB.port = port;
        fillDBSettingsFile(port, 1);
    }
    public static void setUser(String user) {
        ConnectorDB.user = user;
        fillDBSettingsFile(user, 2);
    }
    public static void setPassword(String password) {
        ConnectorDB.password = password;
        fillDBSettingsFile(password, 3);
    }
    public static void setDatabaseName(String databaseName) {
        ConnectorDB.databaseName = databaseName;
        fillDBSettingsFile(databaseName, 4);
    }

    public static String getHost() {        return host;    }

    public static String getPort() {        return port;    }

    public static String getUser() {        return user;    }

    public static String getPassword() {        return password;    }

    public static String getDatabaseName() {        return databaseName;    }

    static void fillDBSettingsFile(String value, int index) {
        try {
            String filename = System.getProperty("user.dir") + "\\app\\DBSettings.txt";
            PrintWriter writer = new PrintWriter(filename);
            switch (index) {
                case 0 -> {
                    writer.println(value);
                    writer.println(port);
                    writer.println(user);
                    writer.println(password);
                    writer.println(databaseName);
                }
                case 1 -> {
                    writer.println(host);
                    writer.println(value);
                    writer.println(user);
                    writer.println(password);
                    writer.println(databaseName);
                }
                case 2 -> {
                    writer.println(host);
                    writer.println(port);
                    writer.println(value);
                    writer.println(password);
                    writer.println(databaseName);
                }
                case 3 -> {
                    writer.println(host);
                    writer.println(port);
                    writer.println(user);
                    writer.println(value);
                    writer.println(databaseName);
                }
                case 4 -> {
                    writer.println(host);
                    writer.println(port);
                    writer.println(user);
                    writer.println(password);
                    writer.println(value);
                }
            }
            writer.close();

        } catch (NullPointerException e) {
            System.out.println(e);
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public static void configuringConnectionData() {
        try {
            String filename = System.getProperty("user.dir") + "\\app\\DBSettings.txt";
            File file = new File(filename);
            Scanner sc = new Scanner(file);

            host = sc.nextLine();
            port = sc.nextLine();
            user = sc.nextLine();
            password = sc.nextLine();
            databaseName = sc.nextLine();

            //System.out.println(host + " " + port + " " + user + " " + password + " " + databaseName);
        } catch (NullPointerException e) {
            System.out.println(e);
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public static String testConnection(String testHost, String testPort, String testUser, String testPassword, String testDatabase) {
        try {
            String url = "jdbc:mysql://" + testHost + ":" + testPort + "/" + testDatabase + "?serverTimezone=UTC";
            DriverManager.getConnection(url, testUser, testPassword);
        } catch (SQLSyntaxErrorException see) {  // Unknown database
            System.out.println(see);
            return "database";
        } catch (SQLException e) {  // Access denied for user ...
            System.out.println(e);
            return "access";
        }
        return "success";
    }

    public static String testConnection() {
        try {
            String url = "jdbc:mysql://" + host + ":" + port + "/" + databaseName + "?serverTimezone=UTC";
            DriverManager.getConnection(url, user, password);
        } catch (SQLSyntaxErrorException see) {  // Unknown database
            System.out.println(see);
            return "database";
        } catch (SQLException e) {  // Access denied for user ...
            System.out.println(e);
            return "access";
        }
        return "success";
    }

    public static boolean testDataBaseExistence() {
        try {
            String url = "jdbc:mysql://" + host + ":" + port + "/" + databaseName + "?serverTimezone=UTC";
            DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public static boolean testTablesExistence() {
        try {
            Teacher.testTeacherExistence();
            CourseGroups.testCourseGroupsExistence();
            NormInHours.testNormInHoursExistence();
            Teacher_and_DiplomaStudentsData.testDiplomaStudentsDataExistence();
        } catch (SQLException e) { // doesn't exist or wrong database
            System.out.println(">>testTablesExistence: " + e);
            return false;
        }
        return true;
    }

    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://" + host + ":" + port + "/" + databaseName + "?serverTimezone=UTC";
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e);
            new DialogError(e.toString() + "\n\nПідказка: Перевірте введені дані для підключення до бази даних (Верхній лівий кут - \"Налаштування БД\").");
        }
        return null;
    }
    public static Connection getConnectionWithoutDatabase() {
        try {
            String url = "jdbc:mysql://" + host + ":" + port + "/?serverTimezone=UTC";
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e);
            new DialogError(e.toString() + "\n\nПідказка: Перевірте введені дані для підключення до бази даних (Верхній лівий кут - \"Налаштування БД\").");
        }
        return null;
    }
}
