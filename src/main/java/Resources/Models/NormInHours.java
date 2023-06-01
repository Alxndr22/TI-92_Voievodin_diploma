package Resources.Models;

import Resources.ConnectorDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class NormInHours {
    static String[] columnNames = {"id", "title", "all", "b", "m_opp", "m_onp", "df", "a", "a_z", "zd_st"};
    static String[] titles = {"Індивід. заняття", "Керівництво практиками", "Керівницт. атестац. роб", "Консультув. атестац. роб",
            "Консультув по випускн. екзамену", "Рецензув. атестац. роб", "Проведення вступних іспитів", "Робота в ЕК", "Керівництво"};

    private int id;
    private String title;
    private double all;
    private double b;
    private double m_opp;
    private double m_onp;
    private double df;
    private double a;
    private double a_z;
    private double zd_st;

    public NormInHours(int id, String title, double all, double b, double m_opp, double m_onp, double df, double a, double a_z, double zd_st) {
        this.id = id;
        this.title = title;
        this.all = all;
        this.b = b;
        this.m_opp = m_opp;
        this.m_onp = m_onp;
        this.df = df;
        this.a = a;
        this.a_z = a_z;
        this.zd_st = zd_st;
    }

    public int getId() {        return id;    }

    public String getTitle() {        return title;    }

    public double getAll() {        return all;    }

    public double getB() {        return b;    }

    public double getM_opp() {        return m_opp;    }

    public double getM_onp() {        return m_onp;    }

    public double getDf() {        return df;    }

    public double getA() {        return a;    }

    public double getA_z() {        return a_z;    }

    public double getZd_st() {        return zd_st;    }


    static public void testNormInHoursExistence() throws SQLException {
        ConnectorDB.getConnection().createStatement().executeQuery("SELECT COUNT(*) FROM `NormInHours`;");
    }


    static public ArrayList<NormInHours> selectFromNormInHours(String query) {
        Connection cn;
        Statement stat;
        ResultSet result;
        ArrayList<NormInHours> normInHours = new ArrayList<>();

        try {
            cn = ConnectorDB.getConnection();
            stat = cn.createStatement();
            if (query.equals(""))
                result = stat.executeQuery("SELECT * FROM `NormInHours`");
            else
                result = stat.executeQuery(query);
            while (result.next()) {
                normInHours.add(new NormInHours(result.getInt(columnNames[0]), result.getString(columnNames[1]), result.getDouble(columnNames[2]),
                        result.getDouble(columnNames[3]), result.getDouble(columnNames[4]), result.getDouble(columnNames[5]), result.getDouble(columnNames[6]),
                        result.getDouble(columnNames[7]), result.getDouble(columnNames[8]), result.getDouble(columnNames[9])));
            }
            cn.close();
            stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return normInHours;
    }

    static public String updateNormInHours_oneField(int id, int column, String value) {
        Connection cn;
        Statement stat;
        try {
            cn = ConnectorDB.getConnection();
            stat = cn.createStatement();
            String query = "UPDATE `NormInHours` SET " + "`" + columnNames[column] + "` = " + value + " WHERE `id` = " + id +";";
            System.out.println(query);
            stat.executeUpdate(query);
            cn.close();
            stat.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return ex.getMessage();
        }
        return null;
    }

    static public String setUpDB() {
        Connection cn;
        Statement stat;
        try {
            cn = ConnectorDB.getConnection();
            stat = cn.createStatement();
            String query = "INSERT INTO `NormInHours` VALUES (0, 'Індивід. заняття', -1, -1, 0, 0, -1, -1, -1, -1)," +
                    "(1, 'Керівництво практиками', 0, -1, -1, -1, -1, -1, -1, -1)," +
                    "(2, 'Керівницт. атестац. роб', -1, 0, 0, 0, -1, -1, -1, -1)," +
                    "(3, 'Консультув. атестац. роб', -1, 0, 0, 0, -1, -1, -1, -1)," +
                    "(4, 'Консультув по випускн. екзамену', -1, 0, 0, 0, -1, -1, -1, -1)," +
                    "(5, 'Рецензув. атестац. роб', -1, 0, 0, 0, -1, -1, -1, -1)," +
                    "(6, 'Проведення вступних іспитів', -1, -1, 0, 0, 0, -1, -1, -1)," +
                    "(7, 'Робота в ЕК -> захист дипломів', -1, 0, -1, -1, -1, -1, -1, -1)," +
                    "(8, 'Робота в ЕК -> екзамен усний', -1, 0, -1, -1, -1, -1, -1, -1)," +
                    "(9, 'Робота в ЕК -> екзамен письмовий', -1, 0, -1, -1, -1, -1, -1, -1)," +
                    "(10, 'Робота в ЕК -> захист дипломів', -1, -1, 0, -1, -1, -1, -1, -1)," +
                    "(11, 'Робота в ЕК -> екзамен', -1, -1, 0, -1, -1, -1, -1, -1)," +
                    "(12, 'Робота в ЕК -> захист дипломів', -1, -1, -1, 0, -1, -1, -1, -1)," +
                    "(13, 'Керівництво', -1, -1, -1, -1, -1, 0, 0, 0);";

            System.out.println(query);
            stat.executeUpdate(query);
            cn.close();
            stat.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return ex.getMessage();
        }
        return null;
    }
}
