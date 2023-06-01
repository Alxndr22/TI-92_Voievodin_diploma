package Resources.Models;

import GUI.Dialogs.DialogExportResults;
import Resources.ConnectorDB;

import java.sql.*;
import java.util.ArrayList;


public class Teacher {

    static String[] columnNames = {"ID", "name", "email", "job title", "bid"};

    static String[] dataColumnNames = {"", "", "", "iz_mopp_b", "iz_mopp_k", "iz_monp_b",
            "iz_monp_k", "kp_p_b", "kp_p_k", "kp_pd_b", "kp_pd_k", "kp_nd_b", "kp_nd_k", "kar_b_b", "kar_b_k", "kar_b_b_z",
            "kar_b_k_z", "kar_mopp_b", "kar_mopp_k", "kar_mopp_b_z", "kar_mopp_k_z",
            "kar_monp_b", "kar_monp_k", "koar_b_b", "koar_b_k", "koar_mopp_b", "koar_mopp_k", "koar_monp_b",
            "koar_monp_k", "kove_b_b", "kove_b_k", "kove_mopp_b", "kove_mopp_k", "kove_monp_b", "kove_monp_k",
            "rar_b_b", "rar_b_k", "rar_mopp_b", "rar_mopp_k", "rar_monp_b", "rar_monp_k", "pvi_mopp_b", "pvi_mopp_k",
            "pvi_monp_b", "pvi_monp_k", "pvi_df_b", "pvi_df_k", "rve_zd_b_b", "rve_zd_b_k", "rve_ey_b_b", "rve_ey_b_k",
            "rve_ep_b_b", "rve_ep_b_k", "rve_zd_mopp_b", "rve_zd_mopp_k", "rve_e_mopp_b", "rve_e_mopp_k", "rve_zd_monp_b",
            "rve_zd_monp_k", "k_a_b", "k_a_k", "k_zdo_b", "k_zdo_k",
            "iz_mopp_b_2", "iz_mopp_k_2", "iz_monp_b_2",
            "iz_monp_k_2", "kp_p_b_2", "kp_p_k_2", "kp_pd_b_2", "kp_pd_k_2", "kp_nd_b_2", "kp_nd_k_2", "kar_b_b_2", "kar_b_k_2", "kar_b_b_z_2",
            "kar_b_k_z_2", "kar_mopp_b_2", "kar_mopp_k_2", "kar_mopp_b_z_2", "kar_mopp_k_z_2",
            "kar_monp_b_2", "kar_monp_k_2", "koar_b_b_2", "koar_b_k_2", "koar_mopp_b_2", "koar_mopp_k_2", "koar_monp_b_2",
            "koar_monp_k_2", "kove_b_b_2", "kove_b_k_2", "kove_mopp_b_2", "kove_mopp_k_2", "kove_monp_b_2", "kove_monp_k_2",
            "rar_b_b_2", "rar_b_k_2", "rar_mopp_b_2", "rar_mopp_k_2", "rar_monp_b_2", "rar_monp_k_2", "pvi_mopp_b_2", "pvi_mopp_k_2",
            "pvi_monp_b_2", "pvi_monp_k_2", "pvi_df_b_2", "pvi_df_k_2", "rve_zd_b_b_2", "rve_zd_b_k_2", "rve_ey_b_b_2", "rve_ey_b_k_2",
            "rve_ep_b_b_2", "rve_ep_b_k_2", "rve_zd_mopp_b_2", "rve_zd_mopp_k_2", "rve_e_mopp_b_2", "rve_e_mopp_k_2", "rve_zd_monp_b_2",
            "rve_zd_monp_k_2", "k_a_b_2", "k_a_k_2", "k_zdo_b_2", "k_zdo_k_2"};

    private int ID;
    private String name;
    private String email;
    private String job_title;
    private float bid;

    public Teacher(int ID, String name, String email, String job_title, float bid) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.job_title = job_title;
        this.bid = bid;
    }

    public int getID() {        return ID;    }

    public String getName() {        return name;    }

    public String getEmail() {        return email;    }

    public String getJob_title() {        return job_title;    }

    public float getBid() {        return bid;    }

    static public void testTeacherExistence() throws SQLException {
        ConnectorDB.getConnection().createStatement().executeQuery("SELECT COUNT(*) FROM `teachers`;");
    }

    static public ArrayList<Teacher> selectFromTeacher() {
        Connection cn;
        Statement stat;
        ResultSet result;
        ArrayList<Teacher> teachers = new ArrayList<>();
        int count_teacher, count_diplStudData;
        try {
            cn = ConnectorDB.getConnection();
            stat = cn.createStatement();

            result = stat.executeQuery("SELECT COUNT(*) FROM `teachers`;");
            result.next();
            count_teacher = result.getInt(1);
            result = stat.executeQuery("SELECT COUNT(*) FROM `DiplomaStudentsData` WHERE `name` != 'planned';");  // `name` != 'distributed' AND
            result.next();
            count_diplStudData = result.getInt(1);

            if (count_teacher != count_diplStudData) {       // add data to 'DiplomaStudentsData' for teachers that was created in Gleb's program
                result = stat.executeQuery("SELECT `teachers`.`ID`, `teachers`.`name` FROM `teachers` WHERE `teachers`.`ID` NOT IN (SELECT `DiplomaStudentsData`.`id` FROM `DiplomaStudentsData`);");
                String query = "INSERT INTO `DiplomaStudentsData` VALUES (";
                while (result.next()) {
                    query += result.getInt(1) + ", '" + result.getString(2) + "', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)";
                    query += result.isLast() ? ";" : ",(";
                }
                stat.executeUpdate(query);
                System.out.println(query);
            }

            result = stat.executeQuery("SELECT * FROM `teachers`");

            while (result.next()) {
                teachers.add(new Teacher(result.getInt(columnNames[0]),result.getString(columnNames[1]),result.getString(columnNames[2]),
                        result.getString(columnNames[3]),result.getFloat(columnNames[4])));
            }

            cn.close();
            stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teachers;
    }

    static public String selectTeachersIfTheyDontHaveReport() {
        Connection cn;
        Statement stat;
        ResultSet result;
        String ret = "\n\nУВАГА. Наступні викладачі не мають даних з Аудиторного Пед. Навантаження [Лист1]:\n\n";
        String query = "SELECT `teachers`.`name` FROM `teachers` WHERE `teachers`.`name` NOT IN (SELECT `report`.`name` FROM `report`);";
        try {
            cn = ConnectorDB.getConnection();
            stat = cn.createStatement();
            System.out.println(query);
            result = stat.executeQuery(query);
            while (result.next()) {
                ret += ">  " + result.getString(1) + "\n";
            }
            cn.close();
            stat.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return ex.getMessage();
        }
        ret += "\n\nТому дані Аудиторного Пед. Навантаження цих викладачів, не будуть занесені до ПЛАНу!\n\n";
        return ret;
    }

    static public ArrayList<TeacherFile> selectListOfTeachersId() {
        Connection cn;
        Statement stat;
        ResultSet result;
        ArrayList<TeacherFile> ret = new ArrayList<>();
        String query = "SELECT `teachers`.`ID` FROM `teachers`;";
        try {
            cn = ConnectorDB.getConnection();
            stat = cn.createStatement();
            System.out.println(query);
            result = stat.executeQuery(query);
            while (result.next())
                ret.add(new TeacherFile(result.getInt(1)));
            cn.close();
            stat.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ret;
    }




    static public String updateTeacher_oneField(int id, int column, String value) {
        Connection cn;
        Statement stat;
        String query = "";
        try {
            cn = ConnectorDB.getConnection();
            stat = cn.createStatement();
            if (column > 2)    // distribution data
                query = "UPDATE `DiplomaStudentsData` SET `" + dataColumnNames[column] + "` = " + value + " WHERE `ID` = " + id + ";";
             else if (column == 2)  // job title (not email)
                query = "UPDATE `teachers` SET " + "`" + columnNames[column + 1] + "` = '" + value + "' WHERE `ID` = " + id +";";
            else   // other teacher data
                query = "UPDATE `teachers` SET " + "`" + columnNames[column] + "` = '" + value + "' WHERE `ID` = " + id +";";

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

    static public String updateTeacherInDictionary_oneField(int id, int column, String value, boolean isNumberValue) {
        Connection cn;
        Statement stat;
        try {
            cn = ConnectorDB.getConnection();
            stat = cn.createStatement();
            String query = isNumberValue ? "UPDATE `teachers` SET " + "`" + columnNames[column + 1] + "` = " + value + " WHERE `ID` = " + id +";" : "UPDATE `teachers` SET " + "`" + columnNames[column + 1] + "` = '" + value + "' WHERE `ID` = " + id +";";
            System.out.println(query);
            stat.executeUpdate(query);
            if (column == 0) {    // need to change name in table 'DiplomaStudentsData'
                query = "UPDATE `DiplomaStudentsData` SET `name` = '" + value + "' WHERE `ID` = " + id + ";";
                System.out.println(query);
                stat.executeUpdate(query);
            }
            cn.close();
            stat.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return ex.getMessage();
        }
        return null;
    }

    static public String insertTeachers(ArrayList<String> data) {
        Connection cn;
        Statement stat;
        ResultSet result;
        String query;
        StringBuilder successImportResults = new StringBuilder();
        StringBuilder failedImportResults = new StringBuilder();
        try {
            cn = ConnectorDB.getConnection();
            stat = cn.createStatement();
            result = stat.executeQuery("SELECT COUNT(*) FROM `teachers`;");
            result.next();
            if (result.getInt(1) == 0)
                query = "SELECT MAX(`DiplomaStudentsData`.`id`) FROM `DiplomaStudentsData`;";
            else
                query = "SELECT greatest(MAX(`teachers`.`ID`), MAX(`DiplomaStudentsData`.`id`)) FROM `teachers`, `DiplomaStudentsData`;";
            System.out.println(query);
            result = stat.executeQuery(query);
            result.next();
            int id = (result.getInt(1) + 1);
            for (int i = 0; i < data.size(); i+=4) {
                query = "SELECT TRUE FROM `teachers` WHERE `teachers`.`name` = '" + data.get(i) + "';";
                result = stat.executeQuery(query);
                if (result.next()) {   // duplicate by name
                    System.out.println("FROM DB: " + result.getInt(1) + ". " + data.get(i));
                    failedImportResults.append("[Вже є в базі даних]   ");
                    failedImportResults.append(data.get(i));
                    failedImportResults.append("\n");
                } else {   // new teacher
                    System.out.println("FROM DB: NULL. " + data.get(i));
                    query = "INSERT INTO `teachers` VALUES " + "(" + id + ", '" + data.get(i) +"', '" + data.get(i+1) + "', '" + data.get(i+2) +"', " + data.get(i+3) +");";
                    System.out.println(query);
                    stat.executeUpdate(query);
                    query = "INSERT INTO `DiplomaStudentsData` VALUES (" + id +", '" + data.get(i) + "', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);";
                    System.out.println(query);
                    stat.executeUpdate(query);
                    successImportResults.append(data.get(i));
                    successImportResults.append("  ");
                    successImportResults.append(data.get(i+1));
                    successImportResults.append("  ");
                    successImportResults.append(data.get(i+2));
                    successImportResults.append("  ");
                    successImportResults.append(data.get(i+3));
                    successImportResults.append("\n");
                    ++id;
                }
            }
            cn.close();
            stat.close();
            new DialogExportResults(successImportResults.toString(), failedImportResults.toString(), false);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return ex.getMessage();
        }
        return null;
    }

    public static String insertNewTeacher(String name, String email, String job_title, String bid) {
        Connection cn;
        Statement stat;
        ResultSet result;
        String query;
        try {
            cn = ConnectorDB.getConnection();
            stat = cn.createStatement();
            result = stat.executeQuery("SELECT COUNT(*) FROM `teachers`;");
            result.next();
            if (result.getInt(1) == 0)
                query = "SELECT MAX(`DiplomaStudentsData`.`id`) FROM `DiplomaStudentsData`;";
            else
                query = "SELECT greatest(MAX(`teachers`.`ID`), MAX(`DiplomaStudentsData`.`id`)) FROM `teachers`, `DiplomaStudentsData`;";
            System.out.println(query);
            result = stat.executeQuery(query);
            result.next();
            int id = (result.getInt(1) + 1);
            if (!name.equals(""))
                query = "INSERT INTO `teachers` VALUES " + "("+ id +", '" + name + "', '-', '-', 0.0);";
            else if (!email.equals(""))
                query = "INSERT INTO `teachers` VALUES " + "("+ id +", '-', '" + email + "', '-', 0.0);";
            else if (!job_title.equals(""))
                query = "INSERT INTO `teachers` VALUES " + "("+ id +", '-', '-', '" + job_title + "', 0.0);";
            else if (!bid.equals(""))
                query = "INSERT INTO `teachers` VALUES " + "("+ id +", '-', '-', '-', " + bid + ");";
            System.out.println(query);
            stat.executeUpdate(query);

            if (name.equals("")) {
                result = stat.executeQuery("SELECT `teachers`.`name` FROM `teachers` WHERE `ID` = " + id);
                result.next();
                name = result.getString(1);
            }
            query = "INSERT INTO `DiplomaStudentsData` VALUES (" + id +", '" + name + "', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);";
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

    public static String Truncate() {
        Connection cn;
        Statement stat;
        try {
            cn = ConnectorDB.getConnection();
            stat = cn.createStatement();
            stat.executeUpdate("TRUNCATE TABLE `teachers`;");
            stat.executeUpdate("DELETE FROM `DiplomaStudentsData` WHERE `name` != 'planned';");    // delete data in table 'DiplomaStudentsData'

            cn.close();
            stat.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return ex.getMessage();
        }
        return null;
    }

    static public String deleteTeacher(int id) {
        Connection cn;
        Statement stat;
        try {
            cn = ConnectorDB.getConnection();
            stat = cn.createStatement();
            String query = "DELETE FROM `teachers` WHERE " + "ID = " + id;
            stat.executeUpdate(query);
            System.out.println(query);
            query = "DELETE FROM `DiplomaStudentsData` WHERE `id` = " + id;
            stat.executeUpdate(query);    // delete data in table 'DiplomaStudentsData'
            System.out.println(query);
            cn.close();
            stat.close();
        } catch(SQLException ex){
            ex.printStackTrace();
            return ex.getMessage();
        }
        return null;
    }
}

