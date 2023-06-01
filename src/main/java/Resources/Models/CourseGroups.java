package Resources.Models;

import Resources.ConnectorDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CourseGroups {
    static String[] columnNames = {"id", "semester", "workType", "subWorkType", "studentsType", "course", "groups"};

    private int id;
    private String semester;
    private String workType;
    private String subWorkType;
    private String studentsType;
    private String course;
    private String groups;

    public int getId() {        return id;    }

    public String getSemester() {        return semester;    }

    public String getWorkType() {        return workType;    }

    public String getSubWorkType() {        return subWorkType;    }

    public String getStudentsType() {        return studentsType;    }

    public String getCourse() {        return course;    }

    public String getGroups() {        return groups;    }


    public CourseGroups(int id, String semester, String workType, String subWorkType, String studentsType, String course, String groups) {
        this.id = id;
        this.semester = semester;
        this.workType = workType;
        this.subWorkType = subWorkType;
        this.studentsType = studentsType;
        this.course = course;
        this.groups = groups;
    }

    static public void testCourseGroupsExistence() throws SQLException {
        ConnectorDB.getConnection().createStatement().executeQuery("SELECT COUNT(*) FROM `CourseGroups`;");
    }

    static public ArrayList<CourseGroups> selectFromCourseGroupsByWorkType(String workType, String semester) {
        Connection cn;
        Statement stat;
        ResultSet result;
        ArrayList<CourseGroups> courseGroups = new ArrayList<>();

        try {
            cn = ConnectorDB.getConnection();
            stat = cn.createStatement();

            result = stat.executeQuery("SELECT * FROM `CourseGroups` WHERE `workType` = '" + workType +"' AND `semester` = '" + semester + "';");
            while (result.next()) {
                courseGroups.add(new CourseGroups(result.getInt(columnNames[0]),result.getString(columnNames[1]),result.getString(columnNames[2]),
                        result.getString(columnNames[3]),result.getString(columnNames[4]),result.getString(columnNames[5]), result.getString(columnNames[6])
                ));
            }
            cn.close();
            stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courseGroups;
    }

    static public String setUpDB() {
        Connection cn;
        Statement stat;
        try {
            cn = ConnectorDB.getConnection();
            stat = cn.createStatement();
            String query;
            System.out.println(query = "INSERT INTO `CourseGroups` VALUES (0, '1', 'Індивідуальне заняття', '', 'магістрів ОПП', '', '')," +
                                                                         "(1, '1', 'Індивідуальне заняття', '', 'магістрів ОНП', '', '')," +
                                                                         "(2, '1', 'Керівництво практиками', '', 'педаг.', '', '')," +
                                                                         "(3, '1', 'Керівництво практиками', '', 'переддипл.', '', '')," +
                                                                         "(4, '1', 'Керівництво практиками', '', 'наук-досл.', '', '')," +
                                                                         "(5, '1', 'Керівницт. атестац. роб', '', 'бакалаврів', '', '')," +
                                                                         "(6, '1', 'Керівницт. атестац. роб', '', 'бакалаврів заочн.', '', '')," +
                                                                         "(7, '1', 'Керівницт. атестац. роб', '', 'магістрів ОПП', '', '')," +
                                                                         "(8, '1', 'Керівницт. атестац. роб', '', 'магістрів ОНП', '', '')," +
                                                                         "(9, '1', 'Консультув. атестац. роб', '', 'бакалаврів', '', '')," +
                                                                         "(10, '1', 'Консультув. атестац. роб', '', 'магістрів ОПП', '', '')," +
                                                                         "(11, '1', 'Консультув. атестац. роб', '', 'магістрів ОНП', '', '')," +
                                                                         "(12, '1', 'Консультув. по випускн. екз', '', 'бакалаврів', '', '')," +
                                                                         "(13, '1', 'Консультув. по випускн. екз', '', 'магістрів ОПП', '', '')," +
                                                                         "(14, '1', 'Консультув. по випускн. екз', '', 'магістрів ОНП', '', '')," +
                                                                         "(15, '1', 'Рецензування атестац. роб', '', 'бакалаврів', '', '')," +
                                                                         "(16, '1', 'Рецензування атестац. роб', '', 'магістрів ОПП', '', '')," +
                                                                         "(17, '1', 'Рецензування атестац. роб', '', 'магістрів ОНП', '', '')," +
                                                                         "(18, '1', 'Проведення вступних іспитів', '', 'магістрів ОПП', '', '')," +
                                                                         "(19, '1', 'Проведення вступних іспитів', '', 'магістрів ОНП', '', '')," +
                                                                         "(20, '1', 'Проведення вступних іспитів', '', 'доктор філософії', '', '')," +
                                                                         "(21, '1', 'Робота в ЕК', 'захист дипломів', 'бакалаврів', '', '')," +
                                                                         "(22, '1', 'Робота в ЕК', 'екзамен усний', 'бакалаврів', '', '')," +
                                                                         "(23, '1', 'Робота в ЕК', 'екзамен письмовий', 'бакалаврів', '', '')," +
                                                                         "(24, '1', 'Робота в ЕК', 'захист дипломів', 'магістрів ОПП', '', '')," +
                                                                         "(25, '1', 'Робота в ЕК', 'екзамен', 'магістрів ОПП', '', '')," +
                                                                         "(26, '1', 'Робота в ЕК', 'захист дипломів', 'магістрів ОНП', '', '')," +
                                                                         "(27, '1', 'Керівництво', '', 'аспірантами', '', '')," +
                                                                         "(28, '1', 'Керівництво', '', 'здобув., стаж.', '', '')," +
                                                                         "(29, '2', 'Індивідуальне заняття', '', 'магістрів ОПП', '', '')," +
                                                                         "(30, '2', 'Індивідуальне заняття', '', 'магістрів ОНП', '', '')," +
                                                                         "(31, '2', 'Керівництво практиками', '', 'педаг.', '', '')," +
                                                                         "(32, '2', 'Керівництво практиками', '', 'переддипл.', '', '')," +
                                                                         "(33, '2', 'Керівництво практиками', '', 'наук-досл.', '', '')," +
                                                                         "(34, '2', 'Керівницт. атестац. роб', '', 'бакалаврів', '', '')," +
                                                                         "(35, '2', 'Керівницт. атестац. роб', '', 'бакалаврів заочн.', '', '')," +
                                                                         "(36, '2', 'Керівницт. атестац. роб', '', 'магістрів ОПП', '', '')," +
                                                                         "(37, '2', 'Керівницт. атестац. роб', '', 'магістрів ОНП', '', '')," +
                                                                         "(38, '2', 'Консультув. атестац. роб', '', 'бакалаврів', '', '')," +
                                                                         "(39, '2', 'Консультув. атестац. роб', '', 'магістрів ОПП', '', '')," +
                                                                         "(40, '2', 'Консультув. атестац. роб', '', 'магістрів ОНП', '', '')," +
                                                                         "(41, '2', 'Консультув. по випускн. екз', '', 'бакалаврів', '', '')," +
                                                                         "(42, '2', 'Консультув. по випускн. екз', '', 'магістрів ОПП', '', '')," +
                                                                         "(43, '2', 'Консультув. по випускн. екз', '', 'магістрів ОНП', '', '')," +
                                                                         "(44, '2', 'Рецензування атестац. роб', '', 'бакалаврів', '', '')," +
                                                                         "(45, '2', 'Рецензування атестац. роб', '', 'магістрів ОПП', '', '')," +
                                                                         "(46, '2', 'Рецензування атестац. роб', '', 'магістрів ОНП', '', '')," +
                                                                         "(47, '2', 'Проведення вступних іспитів', '', 'магістрів ОПП', '', '')," +
                                                                         "(48, '2', 'Проведення вступних іспитів', '', 'магістрів ОНП', '', '')," +
                                                                         "(49, '2', 'Проведення вступних іспитів', '', 'доктор філософії', '', '')," +
                                                                         "(50, '2', 'Робота в ЕК', 'захист дипломів', 'бакалаврів', '', '')," +
                                                                         "(51, '2', 'Робота в ЕК', 'екзамен усний', 'бакалаврів', '', '')," +
                                                                         "(52, '2', 'Робота в ЕК', 'екзамен письмовий', 'бакалаврів', '', '')," +
                                                                         "(53, '2', 'Робота в ЕК', 'захист дипломів', 'магістрів ОПП', '', '')," +
                                                                         "(54, '2', 'Робота в ЕК', 'екзамен', 'магістрів ОПП', '', '')," +
                                                                         "(55, '2', 'Робота в ЕК', 'захист дипломів', 'магістрів ОНП', '', '')," +
                                                                         "(56, '2', 'Керівництво', '', 'аспірантами', '', '')," +
                                                                         "(57, '2', 'Керівництво', '', 'здобув., стаж.', '', '');");
            stat.executeUpdate(query);
            cn.close();
            stat.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return ex.getMessage();
        }
        return null;
    }

    static public String updateCourseGroups_oneField(int id, String columnName, String value) {
        Connection cn;
        Statement stat;
        try {
            cn = ConnectorDB.getConnection();
            stat = cn.createStatement();
            String query = "UPDATE `CourseGroups` SET `" + columnName +"` = '" + value + "' WHERE `id` = " + id +";";
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
