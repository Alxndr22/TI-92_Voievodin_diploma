package Resources;

import GUI.Dialogs.DialogExportResults;
import Resources.Models.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CalculationsAndExport {
     // all needed columns of table 'DiplomaStudentsData'
     static String[] diplomaStudentsDataColumnNames = {"iz_mopp_b", "iz_mopp_k", "iz_monp_b",
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

    static public ResultSet selectTeacherAndSecondPageData() {
        String query = "SELECT `teachers`.`ID`, `teachers`.`name`";
        for (int i = 0; i < diplomaStudentsDataColumnNames.length; ++i)
            query += ", `DiplomaStudentsData`.`" + diplomaStudentsDataColumnNames[i] + "`";
        query += " FROM `teachers`, `DiplomaStudentsData` WHERE `teachers`.`ID` = `DiplomaStudentsData`.`id`;";
        Connection cn;
        Statement stat;
        try {
            cn = ConnectorDB.getConnection();
            stat = cn.createStatement();
            ResultSet result = stat.executeQuery(query);
            //cn.close();
            //stat.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    static public String exportOnlyTeachersSecondPageUsingTemplate(ResultSet result, String savePath) {
        String successExportResults = "";
        String failedExportResults = "";

        try {
            String saveFilename;

            System.out.println(System.getProperty("user.dir") + "\\app\\SecondPageTemplate.xls");
            String templateFilePath = System.getProperty("user.dir") + "\\app\\SecondPageTemplate.xls";
            FileInputStream templateFile;
            HSSFWorkbook templateExcelBook;
            HSSFSheet sheet;
            FileOutputStream out;

            if (result == null) {   // for all teachers
                ResultSet thisResult = selectTeacherAndSecondPageData();
                while (thisResult.next()) {
                    templateFile = new FileInputStream(templateFilePath);
                    templateExcelBook = new HSSFWorkbook(templateFile);
                    sheet = templateExcelBook.getSheetAt(0);

                    calculateTeachersSecondPageData(sheet, thisResult);
                    templateExcelBook.setForceFormulaRecalculation(true);

                    saveFilename = savePath + "\\" + thisResult.getString(2) + ".xls";
                    out = new FileOutputStream(saveFilename);
                    templateExcelBook.write(out);
                    templateExcelBook.close();
                    System.out.println("✔ Exported: " + saveFilename);
                    successExportResults += "[Лист2]  " + saveFilename + "\n";
                    out.close();
                }
            } else {    // only for one selected teacher
                templateFile = new FileInputStream(templateFilePath);
                templateExcelBook = new HSSFWorkbook(templateFile);
                sheet = templateExcelBook.getSheetAt(0);

                calculateTeachersSecondPageData(sheet, result);
                templateExcelBook.setForceFormulaRecalculation(true);

                saveFilename = savePath + "\\" + result.getString(2) + ".xls";
                out = new FileOutputStream(saveFilename);
                templateExcelBook.write(out);
                templateExcelBook.close();
                System.out.println("✔ Exported: " + saveFilename);
                successExportResults += "[Лист2]  " + saveFilename + "\n";
                out.close();
                return successExportResults;       // quick exit without DialogExportResult
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            System.out.println(exception);
            System.out.println("-- Exception occurred --");
            failedExportResults += exception.toString();
        }
        System.out.println("-- Export Finished --");
        new DialogExportResults(successExportResults, failedExportResults, true);
        return successExportResults;
    }


    static void fillExcelUsingTeachersSecondPageData(HSSFSheet sheet, Object ... rowData) {
        Row row;
        Cell cell;
        double probablyDouble;

        row = sheet.getRow((int) rowData[0]);
        for (int i = 1; i < rowData.length; ++i) {
            cell = row.getCell(6 + i);
            if (i == 2) {
                if (((int) rowData[5]) == 0 && ((int) rowData[6]) == 0)  // for faculty
                    cell.setBlank();
                else
                    cell.setCellValue(rowData[i].toString());
                continue;
            }
            if (i == 9) {
                if (((int) rowData[12]) == 0 && ((int) rowData[13]) == 0)   // for faculty_2
                    cell.setBlank();
                else
                    cell.setCellValue(rowData[i].toString());
                continue;
            }

            if (i == 3 || i == 4 || i == 10 || i == 11)   // strings
                cell.setCellValue(rowData[i].toString());
            else {
                probablyDouble = Double.parseDouble(rowData[i].toString());
                if (Math.abs(probablyDouble - 0.0) < 0.000001d)
                    cell.setBlank();
                else
                    cell.setCellValue(probablyDouble);
            }
        }
    }

    static void calculateTeachersSecondPageData(HSSFSheet sheet, ResultSet result) throws SQLException {
        ArrayList<NormInHours> normInHours = NormInHours.selectFromNormInHours("");

        double hours;
        int b, k, b2, k2;
        ArrayList<CourseGroups> courseGroups1, courseGroups2;
        double b_sum_total = 0.0, k_sum_total = 0.0, b_sum_total2 = 0.0, k_sum_total2 = 0.0, b_year_total = 0.0, k_year_total = 0.0;
        int[] rows = {5, 6, 7, 8, 9, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 35};    // right rows in excel to be pasted in
        // ----- start:
        courseGroups1 = CourseGroups.selectFromCourseGroupsByWorkType("Індивідуальне заняття", "1");
        courseGroups2 = CourseGroups.selectFromCourseGroupsByWorkType("Індивідуальне заняття", "2");
        hours = normInHours.get(0).getM_opp();
        b = result.getInt(3);
        k = result.getInt(4);
        b2 = result.getInt(63);
        k2 = result.getInt(64);
        b_sum_total += hours * b;
        k_sum_total += hours * k;
        b_sum_total2 += hours * b2;
        k_sum_total2 += hours * k2;
        fillExcelUsingTeachersSecondPageData(sheet, rows[0], hours, "ННІАТЕ", courseGroups1.get(0).getCourse(), courseGroups1.get(0).getGroups(), b, k, hours * b, hours * k,
                "ННІАТЕ", courseGroups2.get(0).getCourse(), courseGroups2.get(0).getGroups(), b2, k2, hours * b2, hours * k2, (hours * b) + (hours * b2), (hours * k) + (hours * k2));
        hours = normInHours.get(0).getM_onp();
        b = result.getInt(5);
        k = result.getInt(6);
        b2 = result.getInt(65);
        k2 = result.getInt(66);
        b_sum_total += hours * b;
        k_sum_total += hours * k;
        b_sum_total2 += hours * b2;
        k_sum_total2 += hours * k2;
        fillExcelUsingTeachersSecondPageData(sheet, rows[1], hours, "ННІАТЕ", courseGroups1.get(1).getCourse(), courseGroups1.get(1).getGroups(), b, k, hours * b, hours * k,
                "ННІАТЕ", courseGroups2.get(1).getCourse(), courseGroups2.get(1).getGroups(), b2, k2, hours * b2, hours * k2, (hours * b) + (hours * b2), (hours * k) + (hours * k2));
        // -----
        courseGroups1 = CourseGroups.selectFromCourseGroupsByWorkType("Керівництво практиками", "1");
        courseGroups2 = CourseGroups.selectFromCourseGroupsByWorkType("Керівництво практиками", "2");
        hours = normInHours.get(1).getAll();
        b = result.getInt(7);
        k = result.getInt(8);
        b2 = result.getInt(67);
        k2 = result.getInt(68);
        b_sum_total += hours * b;
        k_sum_total += hours * k;
        b_sum_total2 += hours * b2;
        k_sum_total2 += hours * k2;
        fillExcelUsingTeachersSecondPageData(sheet,rows[2], hours, "ННІАТЕ", courseGroups1.get(0).getCourse(), courseGroups1.get(0).getGroups(), b, k, hours * b, hours * k,
                "ННІАТЕ", courseGroups2.get(0).getCourse(), courseGroups2.get(0).getGroups(), b2, k2, hours * b2, hours * k2, (hours * b) + (hours * b2), (hours * k) + (hours * k2));
        b = result.getInt(9);
        k = result.getInt(10);
        b2 = result.getInt(69);
        k2 = result.getInt(70);
        b_sum_total += hours * b;
        k_sum_total += hours * k;
        b_sum_total2 += hours * b2;
        k_sum_total2 += hours * k2;
        fillExcelUsingTeachersSecondPageData(sheet, rows[3], hours, "ННІАТЕ", courseGroups1.get(1).getCourse(), courseGroups1.get(1).getGroups(), b, k, hours * b, hours * k,
                "ННІАТЕ", courseGroups2.get(1).getCourse(), courseGroups2.get(1).getGroups(), b2, k2, hours * b2, hours * k2, (hours * b) + (hours * b2), (hours * k) + (hours * k2));
        b = result.getInt(11);
        k = result.getInt(12);
        b2 = result.getInt(71);
        k2 = result.getInt(72);
        b_sum_total += hours * b;
        k_sum_total += hours * k;
        b_sum_total2 += hours * b2;
        k_sum_total2 += hours * k2;
        fillExcelUsingTeachersSecondPageData(sheet, rows[4], hours, "ННІАТЕ", courseGroups1.get(2).getCourse(), courseGroups1.get(2).getGroups(), b, k, hours * b, hours * k,
                "ННІАТЕ", courseGroups2.get(2).getCourse(), courseGroups2.get(2).getGroups(), b2, k2, hours * b2, hours * k2, (hours * b) + (hours * b2), (hours * k) + (hours * k2));
        // -----
        courseGroups1 = CourseGroups.selectFromCourseGroupsByWorkType("Керівницт. атестац. роб", "1");
        courseGroups2 = CourseGroups.selectFromCourseGroupsByWorkType("Керівницт. атестац. роб", "2");
        hours = normInHours.get(2).getB();
        b = result.getInt(13);
        k = result.getInt(14);
        b2 = result.getInt(73);
        k2 = result.getInt(74);
        b_sum_total += hours * b;
        k_sum_total += hours * k;
        b_sum_total2 += hours * b2;
        k_sum_total2 += hours * k2;
        fillExcelUsingTeachersSecondPageData(sheet,rows[5], hours, "ННІАТЕ", courseGroups1.get(0).getCourse(), courseGroups1.get(0).getGroups(), b, k, hours * b, hours * k,
                "ННІАТЕ", courseGroups2.get(0).getCourse(), courseGroups2.get(0).getGroups(), b2, k2, hours * b2, hours * k2, (hours * b) + (hours * b2), (hours * k) + (hours * k2));
        b = result.getInt(15);
        k = result.getInt(16);
        b2 = result.getInt(75);
        k2 = result.getInt(76);
        b_sum_total += hours * b;
        k_sum_total += hours * k;
        b_sum_total2 += hours * b2;
        k_sum_total2 += hours * k2;
        fillExcelUsingTeachersSecondPageData(sheet,rows[6], hours, "ННІАТЕ", courseGroups1.get(1).getCourse(), courseGroups1.get(1).getGroups(), b, k, hours * b, hours * k,
                "ННІАТЕ", courseGroups2.get(1).getCourse(), courseGroups2.get(1).getGroups(), b2, k2, hours * b2, hours * k2, (hours * b) + (hours * b2), (hours * k) + (hours * k2));
        hours = normInHours.get(2).getM_opp();
        b = result.getInt(17) + result.getInt(19);
        k = result.getInt(18) + result.getInt(20);
        b2 = result.getInt(77) + result.getInt(79);
        k2 = result.getInt(78) + result.getInt(80);
        b_sum_total += hours * b;
        k_sum_total += hours * k;
        b_sum_total2 += hours * b2;
        k_sum_total2 += hours * k2;
        fillExcelUsingTeachersSecondPageData(sheet,rows[7], hours, "ННІАТЕ", courseGroups1.get(2).getCourse(), courseGroups1.get(2).getGroups(), b, k, hours * b, hours * k,
                "ННІАТЕ", courseGroups2.get(2).getCourse(), courseGroups2.get(2).getGroups(), b2, k2, hours * b2, hours * k2, (hours * b) + (hours * b2), (hours * k) + (hours * k2));
        hours = normInHours.get(2).getM_onp();
        b = result.getInt(21);
        k = result.getInt(22);
        b2 = result.getInt(81);
        k2 = result.getInt(82);
        b_sum_total += hours * b;
        k_sum_total += hours * k;
        b_sum_total2 += hours * b2;
        k_sum_total2 += hours * k2;
        fillExcelUsingTeachersSecondPageData(sheet,rows[8], hours, "ННІАТЕ", courseGroups1.get(3).getCourse(), courseGroups1.get(3).getGroups(), b, k, hours * b, hours * k,
                "ННІАТЕ", courseGroups2.get(3).getCourse(), courseGroups2.get(3).getGroups(), b2, k2, hours * b2, hours * k2, (hours * b) + (hours * b2), (hours * k) + (hours * k2));
        // -----
        courseGroups1 = CourseGroups.selectFromCourseGroupsByWorkType("Консультув. атестац. роб", "1");
        courseGroups2 = CourseGroups.selectFromCourseGroupsByWorkType("Консультув. атестац. роб", "2");
        hours = normInHours.get(3).getB();
        b = result.getInt(23);
        k = result.getInt(24);
        b2 = result.getInt(83);
        k2 = result.getInt(84);
        b_sum_total += hours * b;
        k_sum_total += hours * k;
        b_sum_total2 += hours * b2;
        k_sum_total2 += hours * k2;
        fillExcelUsingTeachersSecondPageData(sheet,rows[9], hours, "ННІАТЕ", courseGroups1.get(0).getCourse(), courseGroups1.get(0).getGroups(), b, k, hours * b, hours * k,
                "ННІАТЕ", courseGroups2.get(0).getCourse(), courseGroups2.get(0).getGroups(), b2, k2, hours * b2, hours * k2, (hours * b) + (hours * b2), (hours * k) + (hours * k2));
        hours = normInHours.get(3).getM_opp();
        b = result.getInt(25);
        k = result.getInt(26);
        b2 = result.getInt(85);
        k2 = result.getInt(86);
        b_sum_total += hours * b;
        k_sum_total += hours * k;
        b_sum_total2 += hours * b2;
        k_sum_total2 += hours * k2;
        fillExcelUsingTeachersSecondPageData(sheet,rows[10], hours, "ННІАТЕ", courseGroups1.get(1).getCourse(), courseGroups1.get(1).getGroups(), b, k, hours * b, hours * k,
                "ННІАТЕ", courseGroups2.get(1).getCourse(), courseGroups2.get(1).getGroups(), b2, k2, hours * b2, hours * k2, (hours * b) + (hours * b2), (hours * k) + (hours * k2));
        hours = normInHours.get(3).getM_onp();
        b = result.getInt(27);
        k = result.getInt(28);
        b2 = result.getInt(87);
        k2 = result.getInt(88);
        b_sum_total += hours * b;
        k_sum_total += hours * k;
        b_sum_total2 += hours * b2;
        k_sum_total2 += hours * k2;
        fillExcelUsingTeachersSecondPageData(sheet,rows[11], hours, "ННІАТЕ", courseGroups1.get(2).getCourse(), courseGroups1.get(2).getGroups(), b, k, hours * b, hours * k,
                "ННІАТЕ", courseGroups2.get(2).getCourse(), courseGroups2.get(2).getGroups(), b2, k2, hours * b2, hours * k2, (hours * b) + (hours * b2), (hours * k) + (hours * k2));
        // -----
        courseGroups1 = CourseGroups.selectFromCourseGroupsByWorkType("Рецензування атестац. роб", "1");
        courseGroups2 = CourseGroups.selectFromCourseGroupsByWorkType("Рецензування атестац. роб", "2");
        hours = normInHours.get(5).getB();
        b = result.getInt(35);
        k = result.getInt(36);
        b2 = result.getInt(95);
        k2 = result.getInt(96);
        b_sum_total += hours * b;
        k_sum_total += hours * k;
        b_sum_total2 += hours * b2;
        k_sum_total2 += hours * k2;
        fillExcelUsingTeachersSecondPageData(sheet,rows[12], hours, "ННІАТЕ", courseGroups1.get(0).getCourse(), courseGroups1.get(0).getGroups(), b, k, hours * b, hours * k,
                "ННІАТЕ", courseGroups2.get(0).getCourse(), courseGroups2.get(0).getGroups(), b2, k2, hours * b2, hours * k2, (hours * b) + (hours * b2), (hours * k) + (hours * k2));
        hours = normInHours.get(5).getM_opp();
        b = result.getInt(37);
        k = result.getInt(38);
        b2 = result.getInt(97);
        k2 = result.getInt(98);
        b_sum_total += hours * b;
        k_sum_total += hours * k;
        b_sum_total2 += hours * b2;
        k_sum_total2 += hours * k2;
        fillExcelUsingTeachersSecondPageData(sheet,rows[13], hours, "ННІАТЕ", courseGroups1.get(1).getCourse(), courseGroups1.get(1).getGroups(), b, k, hours * b, hours * k,
                "ННІАТЕ", courseGroups2.get(1).getCourse(), courseGroups2.get(1).getGroups(), b2, k2, hours * b2, hours * k2, (hours * b) + (hours * b2), (hours * k) + (hours * k2));
        hours = normInHours.get(5).getM_onp();
        b = result.getInt(39);
        k = result.getInt(40);
        b2 = result.getInt(99);
        k2 = result.getInt(100);
        b_sum_total += hours * b;
        k_sum_total += hours * k;
        b_sum_total2 += hours * b2;
        k_sum_total2 += hours * k2;
        fillExcelUsingTeachersSecondPageData(sheet,rows[14], hours, "ННІАТЕ", courseGroups1.get(2).getCourse(), courseGroups1.get(2).getGroups(), b, k, hours * b, hours * k,
                "ННІАТЕ", courseGroups2.get(2).getCourse(), courseGroups2.get(2).getGroups(), b2, k2, hours * b2, hours * k2, (hours * b) + (hours * b2), (hours * k) + (hours * k2));
        // -----
        courseGroups1 = CourseGroups.selectFromCourseGroupsByWorkType("Проведення вступних іспитів", "1");
        courseGroups2 = CourseGroups.selectFromCourseGroupsByWorkType("Проведення вступних іспитів", "2");
        hours = normInHours.get(6).getM_opp();
        b = result.getInt(41) + result.getInt(43);
        k = result.getInt(42) + result.getInt(44);
        b2 = result.getInt(101) + result.getInt(103);
        k2 = result.getInt(102) + result.getInt(104);
        b_sum_total += hours * b;
        k_sum_total += hours * k;
        b_sum_total2 += hours * b2;
        k_sum_total2 += hours * k2;
        fillExcelUsingTeachersSecondPageData(sheet,rows[15], hours, "ННІАТЕ", courseGroups1.get(0).getCourse(), courseGroups1.get(0).getGroups() + "  " + courseGroups1.get(1).getGroups(), b, k, hours * b, hours * k,
                "ННІАТЕ", courseGroups2.get(0).getCourse(), courseGroups2.get(0).getGroups() + "  " + courseGroups2.get(1).getGroups(), b2, k2, hours * b2, hours * k2, (hours * b) + (hours * b2), (hours * k) + (hours * k2));
        hours = normInHours.get(6).getDf();
        b = result.getInt(45);
        k = result.getInt(46);
        b2 = result.getInt(105);
        k2 = result.getInt(106);
        b_sum_total += hours * b;
        k_sum_total += hours * k;
        b_sum_total2 += hours * b2;
        k_sum_total2 += hours * k2;
        fillExcelUsingTeachersSecondPageData(sheet,rows[16], hours, "ННІАТЕ", courseGroups1.get(2).getCourse(), courseGroups1.get(2).getGroups(), b, k, hours * b, hours * k,
                "ННІАТЕ", courseGroups2.get(2).getCourse(), courseGroups2.get(2).getGroups(), b2, k2, hours * b2, hours * k2, (hours * b) + (hours * b2), (hours * k) + (hours * k2));
        // -----
        courseGroups1 = CourseGroups.selectFromCourseGroupsByWorkType("Робота в ЕК", "1");
        courseGroups2 = CourseGroups.selectFromCourseGroupsByWorkType("Робота в ЕК", "2");
        hours = normInHours.get(7).getB();
        b = result.getInt(47);
        k = result.getInt(48);
        b2 = result.getInt(107);
        k2 = result.getInt(108);
        b_sum_total += hours * b;
        k_sum_total += hours * k;
        b_sum_total2 += hours * b2;
        k_sum_total2 += hours * k2;
        fillExcelUsingTeachersSecondPageData(sheet,rows[17], hours, "ННІАТЕ", courseGroups1.get(0).getCourse(), courseGroups1.get(0).getGroups(), b, k, hours * b, hours * k,
                "ННІАТЕ", courseGroups2.get(0).getCourse(), courseGroups2.get(0).getGroups(), b2, k2, hours * b2, hours * k2, (hours * b) + (hours * b2), (hours * k) + (hours * k2));
        hours = normInHours.get(8).getB();
        b = result.getInt(49);
        k = result.getInt(50);
        b2 = result.getInt(109);
        k2 = result.getInt(110);
        b_sum_total += hours * b;
        k_sum_total += hours * k;
        b_sum_total2 += hours * b2;
        k_sum_total2 += hours * k2;
        fillExcelUsingTeachersSecondPageData(sheet,rows[18], hours, "ННІАТЕ", courseGroups1.get(1).getCourse(), courseGroups1.get(1).getGroups(), b, k, hours * b, hours * k,
                "ННІАТЕ", courseGroups2.get(1).getCourse(), courseGroups2.get(1).getGroups(), b2, k2, hours * b2, hours * k2, (hours * b) + (hours * b2), (hours * k) + (hours * k2));
        hours = normInHours.get(9).getB();
        b = result.getInt(51);
        k = result.getInt(52);
        b2 = result.getInt(111);
        k2 = result.getInt(112);
        b_sum_total += hours * b;
        k_sum_total += hours * k;
        b_sum_total2 += hours * b2;
        k_sum_total2 += hours * k2;
        fillExcelUsingTeachersSecondPageData(sheet,rows[19], hours, "ННІАТЕ", courseGroups1.get(2).getCourse(), courseGroups1.get(2).getGroups(), b, k, hours * b, hours * k,
                "ННІАТЕ", courseGroups2.get(2).getCourse(), courseGroups2.get(2).getGroups(), b2, k2, hours * b2, hours * k2, (hours * b) + (hours * b2), (hours * k) + (hours * k2));
        hours = normInHours.get(10).getM_opp();
        b = result.getInt(53);
        k = result.getInt(54);
        b2 = result.getInt(113);
        k2 = result.getInt(114);
        b_sum_total += hours * b;
        k_sum_total += hours * k;
        b_sum_total2 += hours * b2;
        k_sum_total2 += hours * k2;
        fillExcelUsingTeachersSecondPageData(sheet,rows[20], hours, "ННІАТЕ", courseGroups1.get(3).getCourse(), courseGroups1.get(3).getGroups(), b, k, hours * b, hours * k,
                "ННІАТЕ", courseGroups2.get(3).getCourse(), courseGroups2.get(3).getGroups(), b2, k2, hours * b2, hours * k2, (hours * b) + (hours * b2), (hours * k) + (hours * k2));
        hours = normInHours.get(11).getM_opp();
        b = result.getInt(55);
        k = result.getInt(56);
        b2 = result.getInt(115);
        k2 = result.getInt(116);
        b_sum_total += hours * b;
        k_sum_total += hours * k;
        b_sum_total2 += hours * b2;
        k_sum_total2 += hours * k2;
        fillExcelUsingTeachersSecondPageData(sheet,rows[21], hours, "ННІАТЕ", courseGroups1.get(4).getCourse(), courseGroups1.get(4).getGroups(), b, k, hours * b, hours * k,
                "ННІАТЕ", courseGroups2.get(4).getCourse(), courseGroups2.get(4).getGroups(), b2, k2, hours * b2, hours * k2, (hours * b) + (hours * b2), (hours * k) + (hours * k2));
        hours = normInHours.get(12).getM_onp();
        b = result.getInt(57);
        k = result.getInt(58);
        b2 = result.getInt(117);
        k2 = result.getInt(118);
        b_sum_total += hours * b;
        k_sum_total += hours * k;
        b_sum_total2 += hours * b2;
        k_sum_total2 += hours * k2;
        fillExcelUsingTeachersSecondPageData(sheet,rows[22], hours, "ННІАТЕ", courseGroups1.get(5).getCourse(), courseGroups1.get(5).getGroups(), b, k, hours * b, hours * k,
                "ННІАТЕ", courseGroups2.get(5).getCourse(), courseGroups2.get(5).getGroups(), b2, k2, hours * b2, hours * k2, (hours * b) + (hours * b2), (hours * k) + (hours * k2));
        // -----
        courseGroups1 = CourseGroups.selectFromCourseGroupsByWorkType("Керівництво", "1");
        courseGroups2 = CourseGroups.selectFromCourseGroupsByWorkType("Керівництво", "2");
        hours = normInHours.get(13).getA();
        b = result.getInt(59);
        k = result.getInt(60);
        b2 = result.getInt(119);
        k2 = result.getInt(120);
        b_sum_total += hours * b;
        k_sum_total += hours * k;
        b_sum_total2 += hours * b2;
        k_sum_total2 += hours * k2;
        fillExcelUsingTeachersSecondPageData(sheet,rows[23], hours, "ННІАТЕ", courseGroups1.get(0).getCourse(), courseGroups1.get(0).getGroups(), b, k, hours * b, hours * k,
                "ННІАТЕ", courseGroups2.get(0).getCourse(), courseGroups2.get(0).getGroups(), b2, k2, hours * b2, hours * k2, (hours * b) + (hours * b2), (hours * k) + (hours * k2));
        hours = normInHours.get(13).getZd_st();
        b = result.getInt(61);
        k = result.getInt(62);
        b2 = result.getInt(121);
        k2 = result.getInt(122);
        b_sum_total += hours * b;
        k_sum_total += hours * k;
        b_sum_total2 += hours * b2;
        k_sum_total2 += hours * k2;
        fillExcelUsingTeachersSecondPageData(sheet,rows[24], hours, "ННІАТЕ", courseGroups1.get(1).getCourse(), courseGroups1.get(1).getGroups(), b, k, hours * b, hours * k,
                "ННІАТЕ", courseGroups2.get(1).getCourse(), courseGroups2.get(1).getGroups(), b2, k2, hours * b2, hours * k2, (hours * b) + (hours * b2), (hours * k) + (hours * k2));
        // -----

        b_year_total += b_sum_total + b_sum_total2;
        k_year_total += k_sum_total + k_sum_total2;
        fillExcelUsingTeachersSecondPageData(sheet,rows[25], 0, "", "", "", 0, 0, b_sum_total, k_sum_total, "", "", "", 0, 0, b_sum_total2, k_sum_total2, b_year_total, k_year_total);
    }

    static public void exportTeachersFirstAndSecondPage(String savePath) {
        String successExportResults = "";
        String successExportResults2Page = "";
        String failedExportResults = "";
        String saveFilename;

        try {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setMultiSelectionEnabled(true);          // !!
            FileNameExtensionFilter xls = new FileNameExtensionFilter("Microsoft Excel Documents", "xls");
            jFileChooser.addChoosableFileFilter(xls);
            jFileChooser.setFileFilter(xls);
            jFileChooser.setAcceptAllFileFilterUsed(false);
            jFileChooser.setDialogTitle("<html>Виберіти excel-файли викладачів, що мають заповнений 'Лист1'<br><br>(назви файлів повинні складатися лише з цифр (id викладача в БД), наприклад '534.xls'</html>");
            if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File[] openFiles = jFileChooser.getSelectedFiles();   // !!
                ArrayList<TeacherFile> teacherFile = Teacher.selectListOfTeachersId();

                int idFromFile_int;
                for (File openFile : openFiles) {
                    if (openFile != null) {
                        System.out.println(openFile);
                        String idFromFile = openFile.getName().substring(0, openFile.getName().lastIndexOf("."));
                        try {
                            idFromFile_int = Integer.parseInt(idFromFile);
                        } catch (NumberFormatException nfe) {
                            System.out.println(nfe);
                            continue;
                        }
                        for (TeacherFile TandF : teacherFile) {
                            if (TandF.getTeacher_id() == idFromFile_int) {
                                TandF.setFile(openFile);
                                break;
                            }
                        }
                    }
                }

                ResultSet result = selectTeacherAndSecondPageData();
                TeacherFile TandF;
                int nextTeacherIndex = 0;
                while (result.next()) {
                    for (;nextTeacherIndex < teacherFile.size();) {
                        TandF = teacherFile.get(nextTeacherIndex);

                        ++nextTeacherIndex;
                        if (TandF.getFile() == null) {     // only for Alex teachers  -> generating file with single (2nd) page
                            successExportResults2Page += exportOnlyTeachersSecondPageUsingTemplate(result, savePath);
                        } else {                          // only for Gleb+Alex teachers
                            // add data on existed 2nd page
                            try {
                                HSSFWorkbook myExcelBook = new HSSFWorkbook(new FileInputStream(TandF.getFile()));
                                HSSFSheet sheet2 = myExcelBook.getSheetAt(1);    // Лист2

                                calculateTeachersSecondPageData(sheet2, result);
                                myExcelBook.setForceFormulaRecalculation(true);

                                saveFilename = savePath + "\\" + result.getString(2) + ".xls";
                                FileOutputStream out = new FileOutputStream(saveFilename);
                                myExcelBook.write(out);
                                myExcelBook.close();
                                System.out.println("✔ Exported: " + saveFilename);
                                successExportResults += "[Лист1 + Лист2]  " + saveFilename + "\n";
                                out.close();
                            } catch (IOException exception) {
                                System.out.println(exception);
                                failedExportResults += exception.toString() + "\n";
                            }
                        }
                        break;   // leave current result
                    }
                }
            } else {
                System.out.println("-- Export Failed --");
            }
        } catch (SQLException exception) {
            System.out.println("SQL IN exportDataInTeachersSecondPage_Ver2");
            System.out.println(exception);
            failedExportResults += exception.toString() + "\n";
        }
        System.out.println("-- Export Finished --");
        new DialogExportResults(successExportResults + "\n" + successExportResults2Page, failedExportResults, true);
    }


    static void fillExcelUsingReportData(HSSFSheet sheet, Object ... rowData) {
        Row row;
        Cell cell;
        int probablyInt;

        row = sheet.getRow((int) rowData[0]) == null ? sheet.createRow((int) rowData[0]) : sheet.getRow((int) rowData[0]);
        for (int i = 1; i < rowData.length; ++i) {
            cell = row.getCell(i - 1) == null ? row.createCell(i - 1) : row.getCell(i - 1);
            if (i == 1)
                cell.setCellValue(rowData[1].toString());
            else {
                probablyInt = Integer.parseInt(rowData[i].toString());
                if (probablyInt == 0)
                    cell.setBlank();
                else
                    cell.setCellValue(probablyInt);
            }
        }
    }

    static public String exportReport(String savePath) {
        try {
            String templateFilePath = System.getProperty("user.dir") + "\\app\\ReportDiplomaStudentsTemplate.xls";
            System.out.println(templateFilePath);
            FileInputStream templateFile = new FileInputStream(templateFilePath);
            HSSFWorkbook myExcelBook = new HSSFWorkbook(templateFile);
            HSSFSheet sheet = myExcelBook.getSheetAt(0);

            ResultSet result = selectTeacherAndSecondPageData();

            int b_b, b_k, b_b_z, b_k_z, mopp_b, mopp_k, mopp_b_z, mopp_k_z, monp_b, monp_k, a_b, a_k, zdo_b, zdo_k;
            int b_b_total = 0, b_k_total = 0, b_b_z_total = 0, b_k_z_total = 0, mopp_b_total = 0, mopp_k_total = 0, mopp_b_z_total = 0, mopp_k_z_total = 0, monp_b_total = 0, monp_k_total = 0, a_b_total = 0, a_k_total = 0, zdo_b_total = 0, zdo_k_total = 0;
            int currentRow = 2;
            while(result.next()) {
                // ----- start:
                b_b = result.getInt(13) + result.getInt(73);
                b_k = result.getInt(14) + result.getInt(74);
                b_b_z = result.getInt(15) + result.getInt(75);
                b_k_z = result.getInt(16) + result.getInt(76);
                mopp_b = result.getInt(17) + result.getInt(77);
                mopp_k = result.getInt(18) + result.getInt(78);
                mopp_b_z = result.getInt(19) + result.getInt(79);
                mopp_k_z = result.getInt(20) + result.getInt(80);
                monp_b = result.getInt(21) + result.getInt(81);
                monp_k = result.getInt(22) + result.getInt(82);
                a_b = result.getInt(59) + result.getInt(119);
                a_k = result.getInt(60) + result.getInt(120);
                zdo_b = result.getInt(61) + result.getInt(121);
                zdo_k = result.getInt(62) + result.getInt(122);
                fillExcelUsingReportData(sheet, currentRow, result.getString(2), b_b, b_k, b_b + b_k, b_b_z, b_k_z, b_b_z + b_k_z,
                        mopp_b, mopp_k, mopp_b + mopp_k, mopp_b_z, mopp_k_z, mopp_b_z + mopp_k_z, monp_b, monp_k, monp_b + monp_k,
                        a_b, a_k, a_b + a_k, zdo_b, zdo_k, zdo_b + zdo_k);
                ++currentRow;
                b_b_total += b_b;
                b_k_total += b_k;
                b_b_z_total += b_b_z;
                b_k_z_total += b_k_z;
                mopp_b_total += mopp_b;
                mopp_k_total += mopp_k;
                mopp_b_z_total += mopp_b_z;
                mopp_k_z_total += mopp_k_z;
                monp_b_total += monp_b;
                monp_k_total += monp_k;
                a_b_total += a_b;
                a_k_total += a_k;
                zdo_b_total += zdo_b;
                zdo_k_total += zdo_k;
            }
            fillExcelUsingReportData(sheet, currentRow + 2, "ВСЬОГО:", b_b_total, b_k_total, b_b_total + b_k_total, b_b_z_total, b_k_z_total,
                    b_b_z_total + b_k_z_total, mopp_b_total, mopp_k_total, mopp_b_total + mopp_k_total,
                    mopp_b_z_total, mopp_k_z_total, mopp_b_z_total + mopp_k_z_total, monp_b_total, monp_k_total, monp_b_total + monp_k_total, a_b_total, a_k_total, a_b_total + a_k_total, zdo_b_total, zdo_k_total, zdo_b_total + zdo_k_total);

            FileOutputStream out = new FileOutputStream(savePath);
            myExcelBook.write(out);
            myExcelBook.close();
            out.close();
            System.out.println("--End--  Saved in : " + savePath);
        } catch (SQLException | IOException e) {
            System.out.println(e);
            return "error";
        }
        return "success";
    }


    static void calculateAndFillExcelUsingPlanData(HSSFWorkbook myExcelBook, HSSFSheet sheet, int shift) {
        ArrayList<NormInHours> normInHours = NormInHours.selectFromNormInHours("");

        // all needed columns of table 'report'
        String[] reportColumnNames_b = {"л х р", "п х гп", "l х гl", "0_33х_ex(б+бк)", "заліки,+al10", "0_25х_мх(б+бк)",
                "qх(б+бк)", "gх(б+бк)", "0_5х_rх(б+бк)", "0_33х_dх(б+бк)", "0_25х_fх(б+бк)", "2 х e х г + 0,06 х n х (б+бк)/25"};     // 12
        String[] reportColumnNames_k = {"л х рк", "п х гкп", "l х гкl", "0_33х_ex(к+кк)", "0_25х_мх(к+кк)",
                "qх(к+кк)", "gх(к+кк)", "0_5х_rх(к+кк)", "0_33х_dх(к+кк)", "0_25х_fх(к+кк)", "2х_ехгк+0,06х_nх(к+кк)/25"};    // 11 (without zaliki_k)


        // "-- display all teachers whether they have report or not (if not, those fields will be null)\n"
        String query = "SELECT `teachers`.`ID`, `teachers`.`name`, `teachers`.`job title`, `teachers`.`bid`";
        for (int i = 0; i < reportColumnNames_b.length; ++i)
            query += ", `report`.`" + reportColumnNames_b[i] + "`";
        for (int i = 0; i < reportColumnNames_k.length; ++i)
            query += ", `report`.`" + reportColumnNames_k[i] + "`";
        for (int i = 0; i < diplomaStudentsDataColumnNames.length; ++i)
            query += ", `DiplomaStudentsData`.`" + diplomaStudentsDataColumnNames[i] + "`";
        query += " FROM `DiplomaStudentsData`, `teachers` LEFT JOIN `report` ON `report`.`name` = `teachers`.`name` WHERE `teachers`.`ID` = `DiplomaStudentsData`.`id`;";

        Connection cn;
        Statement stat;
        ResultSet result;
        try {
            Row row;
            Cell cell;
            double hours1, hours2, hours3;
            int currentRow = 10;

            cn = ConnectorDB.getConnection();
            stat = cn.createStatement();

            result = stat.executeQuery(query);
            while (result.next()) {
                row = sheet.getRow(currentRow) == null ? sheet.createRow(currentRow) : sheet.getRow(currentRow);

                cell = row.getCell(1) == null ? row.createCell(1) : row.getCell(1);
                cell.setCellValue(result.getString(2) + "," + result.getString(3));   // name + job_title

                cell = row.getCell(2) == null ? row.createCell(2) : row.getCell(2);
                cell.setCellValue(result.getFloat(4));   // bid

                if (shift == 0) {  // PlanB
                    for (int i = 5; i <= 16; ++i) {     // report data  ('Лист1')
                        cell = row.getCell(i - 2) == null ? row.createCell(i - 2) : row.getCell(i - 2);
                        cell.setCellValue(result.getFloat(i));
                    }
                } else if (shift == 1) {   // PlanK
                    for (int i = 17; i <= 27; ++i) {     // report data  ('Лист1')
                        if (i >= 21)   // pass zaliki_k
                            cell = row.getCell(i - 13) == null ? row.createCell(i - 13) : row.getCell(i - 13);
                        else
                            cell = row.getCell(i - 14) == null ? row.createCell(i - 14) : row.getCell(i - 14);
                        cell.setCellValue(result.getFloat(i));
                    }
                }

                hours1 = normInHours.get(0).getM_opp();
                hours2 = normInHours.get(0).getM_onp();
                cell = row.getCell(16) == null ? row.createCell(16) : row.getCell(16);
                cell.setCellValue((result.getInt(28 + shift) * hours1) + (result.getInt(88 + shift) * hours1) + (result.getInt(30 + shift) * hours2) + (result.getInt(90 + shift) * hours2));  // індивід. заняття - з магістрами

                hours1 = normInHours.get(1).getAll();
                cell = row.getCell(18) == null ? row.createCell(18) : row.getCell(18);
                cell.setCellValue((result.getInt(32 + shift) * hours1) + (result.getInt(92 + shift) * hours1) + (result.getInt(34 + shift) * hours1) + (result.getInt(94 + shift) * hours1) + (result.getInt(36 + shift) * hours1) + (result.getInt(96 + shift) * hours1));  // керівн. практ - усі

                hours1 = normInHours.get(2).getB();
                cell = row.getCell(19) == null ? row.createCell(19) : row.getCell(19);
                cell.setCellValue((result.getInt(38 + shift) * hours1) + (result.getInt(98 + shift) * hours1) + (result.getInt(40 + shift) * hours1) + (result.getInt(100 + shift)));   // керівн. атест. роб - бакалаврів

                hours1 = normInHours.get(2).getM_opp();
                cell = row.getCell(20) == null ? row.createCell(20) : row.getCell(20);
                cell.setCellValue((result.getInt(42 + shift) * hours1) + (result.getInt(102 + shift) * hours1) + (result.getInt(44 + shift) * hours1) + (result.getInt(104 + shift)));   // керівн. атест. роб - маг ОПП

                hours1 = normInHours.get(2).getM_onp();
                cell = row.getCell(21) == null ? row.createCell(21) : row.getCell(21);
                cell.setCellValue((result.getInt(46 + shift) * hours1) + (result.getInt(106 + shift) * hours1));   // керівн. атест. роб - маг ОНП

                hours1 = normInHours.get(3).getB();
                cell = row.getCell(22) == null ? row.createCell(22) : row.getCell(22);
                cell.setCellValue((result.getInt(48 + shift) * hours1) + (result.getInt(108 + shift) * hours1));   // консул. атест. роб - бакалаврів

                hours1 = normInHours.get(3).getM_opp();
                cell = row.getCell(23) == null ? row.createCell(23) : row.getCell(23);
                cell.setCellValue((result.getInt(50 + shift) * hours1) + (result.getInt(110 + shift) * hours1));   // консул. атест. роб - маг ОПП

                hours1 = normInHours.get(3).getM_onp();
                cell = row.getCell(24) == null ? row.createCell(24) : row.getCell(24);
                cell.setCellValue((result.getInt(52 + shift) * hours1) + (result.getInt(112 + shift) * hours1));   // консул. атест. роб - маг ОНП

                hours1 = normInHours.get(5).getB();
                cell = row.getCell(28) == null ? row.createCell(28) : row.getCell(28);
                cell.setCellValue((result.getInt(60 + shift) * hours1) + (result.getInt(120 + shift) * hours1));   // рецензув. атест. роб - бакалаврів

                hours1 = normInHours.get(5).getM_opp();
                cell = row.getCell(29) == null ? row.createCell(29) : row.getCell(29);
                cell.setCellValue((result.getInt(62 + shift) * hours1) + (result.getInt(122 + shift) * hours1));   // рецензув. атест. роб - маг ОПП

                hours1 = normInHours.get(5).getM_onp();
                cell = row.getCell(30) == null ? row.createCell(30) : row.getCell(30);
                cell.setCellValue((result.getInt(64 + shift) * hours1) + (result.getInt(124 + shift) * hours1));   // рецензув. атест. роб - маг ОНП

                hours1 = normInHours.get(7).getB();
                hours2 = normInHours.get(8).getB();
                hours3 = normInHours.get(9).getB();
                cell = row.getCell(31) == null ? row.createCell(31) : row.getCell(31);
                cell.setCellValue((result.getInt(72 + shift) * hours1) + (result.getInt(132 + shift) * hours1) + (result.getInt(74 + shift) * hours2) + (result.getInt(134 + shift) * hours2) + (result.getInt(76 + shift) * hours3) + (result.getInt(136 + shift) * hours3));   // робота в ЕК - бакалаврів

                hours1 = normInHours.get(10).getM_opp();
                hours2 = normInHours.get(11).getM_opp();
                cell = row.getCell(32) == null ? row.createCell(32) : row.getCell(32);
                cell.setCellValue((result.getInt(78 + shift) * hours1) + (result.getInt(138 + shift) * hours1) + (result.getInt(80 + shift) * hours2) + (result.getInt(140 + shift) * hours2));   // робота в ЕК - маг ОПП

                hours1 = normInHours.get(12).getM_onp();
                cell = row.getCell(33) == null ? row.createCell(33) : row.getCell(33);
                cell.setCellValue((result.getInt(82 + shift) * hours1) + (result.getInt(142 + shift) * hours1));   // робота в ЕК - маг ОНП

                hours1 = normInHours.get(6).getM_opp();  // m_opp == m_onp
                cell = row.getCell(34) == null ? row.createCell(34) : row.getCell(34);
                cell.setCellValue((result.getInt(66 + shift) * hours1) + (result.getInt(126 + shift) * hours1));   // провед. вступ. ісп. - маг ОПП

                cell = row.getCell(35) == null ? row.createCell(35) : row.getCell(35);
                cell.setCellValue((result.getInt(68 + shift) * hours1) + (result.getInt(128 + shift) * hours1));   // провед. вступ. ісп. - маг ОНП

                hours1 = normInHours.get(6).getDf();
                cell = row.getCell(36) == null ? row.createCell(36) : row.getCell(36);
                cell.setCellValue((result.getInt(70 + shift) * hours1) + (result.getInt(130 + shift) * hours1));   // провед. вступ. ісп. - док. філос.

                hours1 = normInHours.get(13).getA();
                cell = row.getCell(37) == null ? row.createCell(37) : row.getCell(37);
                cell.setCellValue((result.getInt(84 + shift) * hours1) + (result.getInt(144 + shift) * hours1));   // керівництво - аспір.

                hours1 = normInHours.get(13).getZd_st();
                cell = row.getCell(38) == null ? row.createCell(38) : row.getCell(38);
                cell.setCellValue((result.getInt(86 + shift) * hours1) + (result.getInt(146 + shift) * hours1));   // керівництво - здоб. стаж.

                ++currentRow;
            }
            cn.close();
            stat.close();

            myExcelBook.setForceFormulaRecalculation(true);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static public void exportPlanB_PlanK(String savePath) {
        String successExportResults = "";
        String failedExportResults = "";
        String saveFilename;
        FileInputStream templateFile;
        HSSFWorkbook myExcelBook;
        HSSFSheet sheet;
        FileOutputStream out;
        try {  // ***  PlanB  ***
            String templateFilePath = System.getProperty("user.dir") + "\\app\\PlanBTemplate.xls";
            System.out.println(templateFilePath);
            templateFile = new FileInputStream(templateFilePath);
            myExcelBook = new HSSFWorkbook(templateFile);
            sheet = myExcelBook.getSheetAt(0);

            calculateAndFillExcelUsingPlanData(myExcelBook, sheet, 0);

            saveFilename = savePath + "\\" + "PLAN_b.xls";
            out = new FileOutputStream(saveFilename);
            myExcelBook.write(out);
            myExcelBook.close();
            out.close();
            System.out.println("✔ Exported: " + saveFilename);
            successExportResults += saveFilename + "\n";
        } catch (IOException exception) {
            System.out.println(exception);
            failedExportResults += exception.toString() + "\n";
        }
        try {   // ***  PlanK  ***
            String templateFilePath = System.getProperty("user.dir") + "\\app\\PlanKTemplate.xls";
            System.out.println(templateFilePath);
            templateFile = new FileInputStream(templateFilePath);
            myExcelBook = new HSSFWorkbook(templateFile);
            sheet = myExcelBook.getSheetAt(0);

            calculateAndFillExcelUsingPlanData(myExcelBook, sheet, 1);

            saveFilename = savePath + "\\" + "PLAN_k.xls";
            out = new FileOutputStream(saveFilename);
            myExcelBook.write(out);
            myExcelBook.close();
            out.close();
            System.out.println("✔ Exported: " + saveFilename);
            successExportResults += saveFilename + "\n";
        } catch (IOException exception) {
            System.out.println(exception);
            failedExportResults += exception.toString() + "\n";
        }
        System.out.println(">> calculatePlanB_PlanK: CALCULATION FINISHED!");
        successExportResults += Teacher.selectTeachersIfTheyDontHaveReport();
        new DialogExportResults(successExportResults, failedExportResults, true);
    }
}
