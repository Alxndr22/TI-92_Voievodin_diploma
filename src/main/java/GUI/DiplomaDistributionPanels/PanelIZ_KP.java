package GUI.DiplomaDistributionPanels;

import GUI.Utils.GroupableHeaderJTable.ColumnGroup;
import GUI.Utils.GroupableHeaderJTable.GroupableTableHeader;
import GUI.FirstLevel.MainFrame;
import Resources.Models.*;
import GUI.Utils.RedGreenAlertSystem;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class PanelIZ_KP extends DiplomaDistributionTab {  // Індивід. зан. & Керівн. практ.

    public PanelIZ_KP(MainFrame parent, boolean openSecondSemester, int JScrollBarValue, int verticalScrollBarMaximum) {
        super(parent);


        // ************************************   PanelIZ_KP_logic (1 semester)   **************************************
        cards.add(new JPanel() {
            private Font font;
            private JTable table1 = new JTable() {
                public boolean isCellEditable(int row, int column) {
                    return column > 2;
                }
                protected JTableHeader createDefaultTableHeader() {
                return new GroupableTableHeader(columnModel);} };
            private JTable tableTotal = new JTable() {


                public boolean isCellEditable(int row, int column) {
                    if (row < 1)
                        return false;
                    else if (row > 0 && column <= 2)
                        return false;
                    else
                        return true;
                }
                protected JTableHeader createDefaultTableHeader() {
                    return new GroupableTableHeader(columnModel);}};

            private JTable tableCourseGroups = new JTable() {
                public boolean isCellEditable(int row, int column) {
                    if (column == 0 || column == 3 || column == 4)
                        return false;
                    else if ((row == 2 && column == 1) || (row == 2 && column == 2))
                        return false;
                    else
                        return true;
                }
                protected JTableHeader createDefaultTableHeader() {
                    return new GroupableTableHeader(columnModel);}
            };


            private GridBagLayout GBlayout = new GridBagLayout();
            private GridBagConstraints GBconstraints = new GridBagConstraints();

            ArrayList<Teacher_and_DiplomaStudentsData> teacherAndData;
            ArrayList<CourseGroups> courseGroups1, courseGroups2;

            private RedGreenAlertSystem redGreenAlertSystem = new RedGreenAlertSystem(tableTotal);

            InnerPanelWithThreeTables iptt;

            JPanel execute() {
                setBackground(Color.white);
                setLayout(GBlayout);
                font = new Font("Franklin Gothic Book", Font.PLAIN, 14);


                GBconstraints.gridwidth = GridBagConstraints.REMAINDER;
                GBconstraints.fill = GridBagConstraints.BOTH;
                GBconstraints.weightx = 1;
                GBconstraints.weighty = 1;
                GBconstraints.insets = new Insets(0, 20, 50, 20);

                table1.setAutoCreateRowSorter(true);   // SORTING Tables

                teacherAndData = Teacher_and_DiplomaStudentsData.selectFromTeacher_and_DiplomaStudentsData();    // !!!!!!! (30_04)


                String[] columnNamesUKR = {"№", "ПІБ", "Посада", "з маг ОПП Б", "з маг ОПП К", "з маг ОНП Б", "з маг ОНП К", "педаг. Б", "педаг. К", "переддипл. Б", "переддипл. К", "наук-досл. Б", "наук-досл. К"};
                DefaultTableModel model2 = new DefaultTableModel();
                String[] totalColumnNames = {"РАЗОМ:", "-", "-", "з маг ОПП Б", "з маг ОПП К", "з маг ОНП Б", "з маг ОНП К", "педаг. Б", "педаг. К", "переддипл. Б", "переддипл. К", "наук-досл. Б", "наук-досл. К"};
                model2.setColumnIdentifiers(totalColumnNames);


                DefaultTableModel model = new DefaultTableModel();
                model.setColumnIdentifiers(columnNamesUKR);
                for (Teacher_and_DiplomaStudentsData TandD : teacherAndData) {          // !!!!!!! (30_04)
                    Object[] o = new Object[] {TandD.getId_teacher(), TandD.getName(), TandD.getJob_title(),
                            TandD.getIz_mopp_b(), TandD.getIz_mopp_k(), TandD.getIz_monp_b(), TandD.getIz_monp_k(),
                            TandD.getKp_p_b(), TandD.getKp_p_k(), TandD.getKp_pd_b(), TandD.getKp_pd_k(), TandD.getKp_nd_b(),
                            TandD.getKp_nd_k()};
                    model.addRow(o);
                }
                table1.setModel(model);

                // !!!!!!!!!  (07_05)
                int[] plannedData = Teacher_and_DiplomaStudentsData.selectPlannedData_or_distributedSum(true);
                if (plannedData.length != 120) {
                    Teacher_and_DiplomaStudentsData.setUpDB();   // only for the first time
                    plannedData = Teacher_and_DiplomaStudentsData.selectPlannedData_or_distributedSum(true);
                }
                int[] distributedSum = Teacher_and_DiplomaStudentsData.selectPlannedData_or_distributedSum(false);
                String[] totalAmount = {"РОЗПОДІЛЕНО", "-", "-", String.valueOf(distributedSum[0]), String.valueOf(distributedSum[1]), String.valueOf(distributedSum[2]), String.valueOf(distributedSum[3]), String.valueOf(distributedSum[4]),
                        String.valueOf(distributedSum[5]), String.valueOf(distributedSum[6]), String.valueOf(distributedSum[7]), String.valueOf(distributedSum[8]), String.valueOf(distributedSum[9])};
                model2.addRow(totalAmount);

                String[] plannedAmount = {"ЗАПЛАНОВАНО", "-", "-", String.valueOf(plannedData[0]), String.valueOf(plannedData[1]), String.valueOf(plannedData[2]), String.valueOf(plannedData[3]), String.valueOf(plannedData[4]),
                        String.valueOf(plannedData[5]), String.valueOf(plannedData[6]), String.valueOf(plannedData[7]), String.valueOf(plannedData[8]), String.valueOf(plannedData[9])};
                model2.addRow(plannedAmount);
                tableTotal.setModel(model2);


                for (int i = 3; i < tableTotal.getColumnCount(); i++) {                                 // compute and mark red fields when first open panel
                    tableTotal.getColumnModel().getColumn(i).setCellRenderer(redGreenAlertSystem);              // RedGreenAlertSystem
                }


                TableColumnModel cm = table1.getColumnModel();
                ColumnGroup g_name = new ColumnGroup("Викладач");
                g_name.add(cm.getColumn(1));
                g_name.add(cm.getColumn(2));
                ColumnGroup g_iz = new ColumnGroup("Індивідуальне заняття");
                g_iz.add(cm.getColumn(3));
                g_iz.add(cm.getColumn(4));
                g_iz.add(cm.getColumn(5));
                g_iz.add(cm.getColumn(6));
                ColumnGroup g_kp = new ColumnGroup("Керівництво практиками");
                g_kp.add(cm.getColumn(7));
                g_kp.add(cm.getColumn(8));
                g_kp.add(cm.getColumn(9));
                g_kp.add(cm.getColumn(10));
                g_kp.add(cm.getColumn(11));
                g_kp.add(cm.getColumn(12));

                GroupableTableHeader header = (GroupableTableHeader)table1.getTableHeader();
                header.addColumnGroup(g_name);
                header.addColumnGroup(g_iz);
                header.addColumnGroup(g_kp);

                TableColumnModel cm1 = tableTotal.getColumnModel();
                g_name = new ColumnGroup("-");
                g_name.add(cm1.getColumn(1));
                g_name.add(cm1.getColumn(2));
                g_iz = new ColumnGroup("Індивідуальне заняття");
                g_iz.add(cm1.getColumn(3));
                g_iz.add(cm1.getColumn(4));
                g_iz.add(cm1.getColumn(5));
                g_iz.add(cm1.getColumn(6));
                g_kp = new ColumnGroup("Керівництво практиками");
                g_kp.add(cm1.getColumn(7));
                g_kp.add(cm1.getColumn(8));
                g_kp.add(cm1.getColumn(9));
                g_kp.add(cm1.getColumn(10));
                g_kp.add(cm1.getColumn(11));
                g_kp.add(cm1.getColumn(12));

                header = (GroupableTableHeader)tableTotal.getTableHeader();
                header.addColumnGroup(g_name);
                header.addColumnGroup(g_iz);
                header.addColumnGroup(g_kp);



                TableModel tb = table1.getModel();
                tb.addTableModelListener(new TableModelListener() {       // Update Tables
                    @Override
                    public void tableChanged(TableModelEvent e) {
                        System.out.println(e.getFirstRow() + " " + e.getColumn() + " -> " + tb.getValueAt(e.getFirstRow(),e.getColumn()));
                        Teacher.updateTeacher_oneField(teacherAndData.get(e.getFirstRow()).getId_teacher(),e.getColumn(), tb.getValueAt(e.getFirstRow(),e.getColumn()).toString());

                        System.out.println("••• UPDATING THIS PANEL •••");
                        parent.updateThisPanel(1, 0, new boolean[] {false, false, false, false, false, false, false, false}, iptt.getJScrollPane_table1().getVerticalScrollBar().getValue(), iptt.getJScrollPane_table1().getVerticalScrollBar().getMaximum());
                    }
                });
                TableModel tm1 = tableTotal.getModel();
                tm1.addTableModelListener(new TableModelListener() {
                    @Override
                    public void tableChanged(TableModelEvent e) {
                        System.out.println(e.getFirstRow() + " " + e.getColumn() + " -> " + tm1.getValueAt(e.getFirstRow(),e.getColumn()));
                        Teacher_and_DiplomaStudentsData.updatePlanned_oneField(e.getColumn() - 3, Integer.parseInt(tm1.getValueAt(e.getFirstRow(), e.getColumn()).toString()));     // updating 'ЗАПЛАНОВАНО' (planned)

                        System.out.println("••• UPDATING THIS PANEL •••");
                        parent.updateThisPanel(1, 0, new boolean[] {false, false, false, false, false, false, false, false}, iptt.getJScrollPane_table1().getVerticalScrollBar().getValue(), iptt.getJScrollPane_table1().getVerticalScrollBar().getMaximum());
                    }
                });



                Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
                TitledBorder titled = BorderFactory.createTitledBorder(loweredetched, "I семестр");
                titled.setTitleFont(font);


                // ---------- TableCourseGroup ----------

                courseGroups1 = CourseGroups.selectFromCourseGroupsByWorkType("Індивідуальне заняття", "1");        // workType: IZ  semester: 1
                courseGroups2 = CourseGroups.selectFromCourseGroupsByWorkType("Керівництво практиками", "1");        // workType: KP  semester: 1

                if (courseGroups1.isEmpty()) {
                    CourseGroups.setUpDB();
                    courseGroups1 = CourseGroups.selectFromCourseGroupsByWorkType("Індивідуальне заняття", "1");      // workType: IZ  semester: 1
                    courseGroups2 = CourseGroups.selectFromCourseGroupsByWorkType("Керівництво практиками", "1");        // workType: KP  semester: 1
                }

                DefaultTableModel model3 = new DefaultTableModel();
                model3.setColumnIdentifiers(new String[] {"", "Курс", "Шифр Груп", "||", "", "Курс", "Шифр Груп"});
                for (int i = 0; i < 3; ++i) {
                    Object[] o;
                    if (i == 2) {
                        o = new Object[] {"", "", "",
                                "", courseGroups2.get(i).getSubWorkType() + ' ' + courseGroups2.get(i).getStudentsType(), courseGroups2.get(i).getCourse(), courseGroups2.get(i).getGroups()};
                    } else {
                        o = new Object[] {courseGroups1.get(i).getSubWorkType() + ' ' + courseGroups1.get(i).getStudentsType(), courseGroups1.get(i).getCourse(), courseGroups1.get(i).getGroups(),
                                "", courseGroups2.get(i).getSubWorkType() + ' ' + courseGroups2.get(i).getStudentsType(), courseGroups2.get(i).getCourse(), courseGroups2.get(i).getGroups()};
                    }
                    model3.addRow(o);
                }
                tableCourseGroups.setModel(model3);

                TableColumnModel cg_cm = tableCourseGroups.getColumnModel();
                ColumnGroup g_iz1 = new ColumnGroup("Індивідуальне заняття");               // !!!
                g_iz1.add(cg_cm.getColumn(0));
                g_iz1.add(cg_cm.getColumn(1));
                g_iz1.add(cg_cm.getColumn(2));

                ColumnGroup g_kp1 = new ColumnGroup("Керівництво практиками");               // !!!
                g_kp1.add(cg_cm.getColumn(4));
                g_kp1.add(cg_cm.getColumn(5));
                g_kp1.add(cg_cm.getColumn(6));

                GroupableTableHeader cg_header = (GroupableTableHeader) tableCourseGroups.getTableHeader();
                cg_header.addColumnGroup(g_iz1);
                cg_header.addColumnGroup(g_kp1);

                cg_cm.getColumn(3).setMaxWidth(10);

                model3.addTableModelListener(new TableModelListener() {    // Update Table 'tableCourseGroups'
                    @Override
                    public void tableChanged(TableModelEvent e) {
                        System.out.println(e.getFirstRow() + " " + e.getColumn() + " -> " + model3.getValueAt(e.getFirstRow(),e.getColumn()));
                        if (e.getColumn() == 1) {
                            if (model3.getValueAt(e.getFirstRow(), e.getColumn()).toString().matches("[1-9]|^$"))  // only 1-9 or empty string
                                CourseGroups.updateCourseGroups_oneField(courseGroups1.get(e.getFirstRow()).getId(), "course", model3.getValueAt(e.getFirstRow(), e.getColumn()).toString());
                        } else if (e.getColumn() == 2)
                            CourseGroups.updateCourseGroups_oneField(courseGroups1.get(e.getFirstRow()).getId(), "groups", model3.getValueAt(e.getFirstRow(), e.getColumn()).toString());
                        else if (e.getColumn() == 5) {
                            if (model3.getValueAt(e.getFirstRow(), e.getColumn()).toString().matches("[1-9]|^$"))  // only 1-9 or empty string
                                CourseGroups.updateCourseGroups_oneField(courseGroups2.get(e.getFirstRow()).getId(), "course", model3.getValueAt(e.getFirstRow(), e.getColumn()).toString());
                        } else if (e.getColumn() == 6)
                            CourseGroups.updateCourseGroups_oneField(courseGroups2.get(e.getFirstRow()).getId(), "groups", model3.getValueAt(e.getFirstRow(), e.getColumn()).toString());
                        System.out.println("••• UPDATING THIS PANEL •••");
                        parent.updateThisPanel(1, 0, new boolean[] {false, false, false, false, false, false, false, false}, iptt.getJScrollPane_table1().getVerticalScrollBar().getValue(), iptt.getJScrollPane_table1().getVerticalScrollBar().getMaximum());        // JTabbedPaneOpenIndex: 1   2semester: false
                    }
                });

                // ---------- ---------- ----------

                iptt = new InnerPanelWithThreeTables(table1, tableTotal, tableCourseGroups, 0.25, JScrollBarValue, verticalScrollBarMaximum);
                iptt.setBackground(Color.white);
                iptt.setBorder(titled);
                iptt.setFont(font);
                GBlayout.setConstraints(iptt, GBconstraints);
                add(iptt);                                                                       // add panel with two tables

                return this;
            }
        }.execute(), "semester1"); // panelIZ_KP_logic

        // ******************************************  END  ************************************************************

        // ************************************   PanelIZ_KP_logic (2 semester)   **************************************
        cards.add(new JPanel() {
            private Font font;
            private JTable table1 = new JTable() {
                public boolean isCellEditable(int row, int column) {
                    return column > 2;
                }
                protected JTableHeader createDefaultTableHeader() {
                return new GroupableTableHeader(columnModel);} };
            private JTable tableTotal = new JTable() {


                public boolean isCellEditable(int row, int column) {     //
                    if (row < 1)
                        return false;
                    else if (row > 0 && column <= 2)
                        return false;
                    else
                        return true;
                }
                protected JTableHeader createDefaultTableHeader() {
                    return new GroupableTableHeader(columnModel);}};

            private JTable tableCourseGroups = new JTable() {
                public boolean isCellEditable(int row, int column) {
                    if (column == 0 || column == 3 || column == 4)
                        return false;
                    else if ((row == 2 && column == 1) || (row == 2 && column == 2))
                        return false;
                    else
                        return true;
                }
                protected JTableHeader createDefaultTableHeader() {
                    return new GroupableTableHeader(columnModel);}
            };

            private int dataShift = 60;

            private GridBagLayout GBlayout = new GridBagLayout();
            private GridBagConstraints GBconstraints = new GridBagConstraints();

            ArrayList<Teacher_and_DiplomaStudentsData> teacherAndData;
            ArrayList<CourseGroups> courseGroups1, courseGroups2;

            private RedGreenAlertSystem redGreenAlertSystem = new RedGreenAlertSystem(tableTotal);

            InnerPanelWithThreeTables iptt;

            JPanel execute() {
                setBackground(Color.white);
                setLayout(GBlayout);
                font = new Font("Franklin Gothic Book", Font.PLAIN, 14);


                GBconstraints.gridwidth = GridBagConstraints.REMAINDER;
                GBconstraints.fill = GridBagConstraints.BOTH;
                GBconstraints.weightx = 1;
                GBconstraints.weighty = 1;
                GBconstraints.insets = new Insets(0, 20, 50, 20);

                table1.setAutoCreateRowSorter(true);   // SORTING Tables

                teacherAndData = Teacher_and_DiplomaStudentsData.selectFromTeacher_and_DiplomaStudentsData();    // !!!!!!! (30_04)


                String[] columnNamesUKR = {"№", "ПІБ", "Посада", "з маг ОПП Б", "з маг ОПП К", "з маг ОНП Б", "з маг ОНП К", "педаг. Б", "педаг. К", "переддипл. Б", "переддипл. К", "наук-досл. Б", "наук-досл. К"};
                DefaultTableModel model2 = new DefaultTableModel();
                String[] totalColumnNames = {"РАЗОМ:", "-", "-", "з маг ОПП Б", "з маг ОПП К", "з маг ОНП Б", "з маг ОНП К", "педаг. Б", "педаг. К", "переддипл. Б", "переддипл. К", "наук-досл. Б", "наук-досл. К"};
                model2.setColumnIdentifiers(totalColumnNames);


                DefaultTableModel model = new DefaultTableModel();
                model.setColumnIdentifiers(columnNamesUKR);
                for (Teacher_and_DiplomaStudentsData TandD : teacherAndData) {
                    Object[] o = new Object[] {TandD.getId_teacher(), TandD.getName(), TandD.getJob_title(),
                            TandD.getIz_mopp_b_2(), TandD.getIz_mopp_k_2(), TandD.getIz_monp_b_2(), TandD.getIz_monp_k_2(),
                            TandD.getKp_p_b_2(), TandD.getKp_p_k_2(), TandD.getKp_pd_b_2(), TandD.getKp_pd_k_2(), TandD.getKp_nd_b_2(),
                            TandD.getKp_nd_k_2()};
                    model.addRow(o);
                }
                table1.setModel(model);


                // !!!!!!!!!  (07_05)
                int[] plannedData = Teacher_and_DiplomaStudentsData.selectPlannedData_or_distributedSum(true);
                if (plannedData.length != 120) {
                    Teacher_and_DiplomaStudentsData.setUpDB();   // only for the first time
                    plannedData = Teacher_and_DiplomaStudentsData.selectPlannedData_or_distributedSum(true);
                }
                int[] distributedSum = Teacher_and_DiplomaStudentsData.selectPlannedData_or_distributedSum(false);
                String[] totalAmount = {"РОЗПОДІЛЕНО", "-", "-", String.valueOf(distributedSum[60]), String.valueOf(distributedSum[61]), String.valueOf(distributedSum[62]), String.valueOf(distributedSum[63]), String.valueOf(distributedSum[64]),
                        String.valueOf(distributedSum[65]), String.valueOf(distributedSum[66]), String.valueOf(distributedSum[67]), String.valueOf(distributedSum[68]), String.valueOf(distributedSum[69])};
                model2.addRow(totalAmount);

                String[] plannedAmount = {"ЗАПЛАНОВАНО", "-", "-", String.valueOf(plannedData[60]), String.valueOf(plannedData[61]), String.valueOf(plannedData[62]), String.valueOf(plannedData[63]), String.valueOf(plannedData[64]),
                        String.valueOf(plannedData[65]), String.valueOf(plannedData[66]), String.valueOf(plannedData[67]), String.valueOf(plannedData[68]), String.valueOf(plannedData[69])};
                model2.addRow(plannedAmount);
                tableTotal.setModel(model2);


                for (int i = 3; i < tableTotal.getColumnCount(); i++) {                                 // compute and mark red fields when first open panel
                    tableTotal.getColumnModel().getColumn(i).setCellRenderer(redGreenAlertSystem);              // RedGreenAlertSystem
                }


                TableColumnModel cm = table1.getColumnModel();
                ColumnGroup g_name = new ColumnGroup("Викладач");
                g_name.add(cm.getColumn(1));
                g_name.add(cm.getColumn(2));
                ColumnGroup g_iz = new ColumnGroup("Індивідуальне заняття");
                g_iz.add(cm.getColumn(3));
                g_iz.add(cm.getColumn(4));
                g_iz.add(cm.getColumn(5));
                g_iz.add(cm.getColumn(6));
                ColumnGroup g_kp = new ColumnGroup("Керівництво практиками");
                g_kp.add(cm.getColumn(7));
                g_kp.add(cm.getColumn(8));
                g_kp.add(cm.getColumn(9));
                g_kp.add(cm.getColumn(10));
                g_kp.add(cm.getColumn(11));
                g_kp.add(cm.getColumn(12));

                GroupableTableHeader header = (GroupableTableHeader)table1.getTableHeader();
                header.addColumnGroup(g_name);
                header.addColumnGroup(g_iz);
                header.addColumnGroup(g_kp);

                TableColumnModel cm1 = tableTotal.getColumnModel();
                g_name = new ColumnGroup("-");
                g_name.add(cm1.getColumn(1));
                g_name.add(cm1.getColumn(2));
                g_iz = new ColumnGroup("Індивідуальне заняття");
                g_iz.add(cm1.getColumn(3));
                g_iz.add(cm1.getColumn(4));
                g_iz.add(cm1.getColumn(5));
                g_iz.add(cm1.getColumn(6));
                g_kp = new ColumnGroup("Керівництво практиками");
                g_kp.add(cm1.getColumn(7));
                g_kp.add(cm1.getColumn(8));
                g_kp.add(cm1.getColumn(9));
                g_kp.add(cm1.getColumn(10));
                g_kp.add(cm1.getColumn(11));
                g_kp.add(cm1.getColumn(12));

                header = (GroupableTableHeader)tableTotal.getTableHeader();
                header.addColumnGroup(g_name);
                header.addColumnGroup(g_iz);
                header.addColumnGroup(g_kp);



                TableModel tb = table1.getModel();
                tb.addTableModelListener(new TableModelListener() {       // Update Tables
                    @Override
                    public void tableChanged(TableModelEvent e) {
                        System.out.println(e.getFirstRow() + " " + e.getColumn() + " -> " + tb.getValueAt(e.getFirstRow(),e.getColumn()));
                        Teacher.updateTeacher_oneField(teacherAndData.get(e.getFirstRow()).getId_teacher(),e.getColumn() + dataShift, tb.getValueAt(e.getFirstRow(),e.getColumn()).toString());     // (зсув + 60)

                        System.out.println("••• UPDATING THIS PANEL •••");
                        parent.updateThisPanel(1, 0, new boolean[] {true, false, false, false, false, false, false, false}, iptt.getJScrollPane_table1().getVerticalScrollBar().getValue(), iptt.getJScrollPane_table1().getVerticalScrollBar().getMaximum());
                    }
                });
                TableModel tm1 = tableTotal.getModel();
                tm1.addTableModelListener(new TableModelListener() {
                    @Override
                    public void tableChanged(TableModelEvent e) {
                        System.out.println(e.getFirstRow() + " " + e.getColumn() + " -> " + tm1.getValueAt(e.getFirstRow(),e.getColumn()));
                        Teacher_and_DiplomaStudentsData.updatePlanned_oneField(e.getColumn() - 3 + dataShift, Integer.parseInt(tm1.getValueAt(e.getFirstRow(), e.getColumn()).toString()));     // updating 'ЗАПЛАНОВАНО' (planned)   (зсув + 60)

                        System.out.println("••• UPDATING THIS PANEL •••");
                        parent.updateThisPanel(1, 0, new boolean[] {true, false, false, false, false, false, false, false}, iptt.getJScrollPane_table1().getVerticalScrollBar().getValue(), iptt.getJScrollPane_table1().getVerticalScrollBar().getMaximum());
                    }
                });



                Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
                TitledBorder titled = BorderFactory.createTitledBorder(loweredetched, "II семестр");
                titled.setTitleFont(font);


                // ---------- TableCourseGroup ----------

                courseGroups1 = CourseGroups.selectFromCourseGroupsByWorkType("Індивідуальне заняття", "2");        // workType: IZ  semester: 2
                courseGroups2 = CourseGroups.selectFromCourseGroupsByWorkType("Керівництво практиками", "2");        // workType: KP  semester: 2

                if (courseGroups1.isEmpty()) {
                    CourseGroups.setUpDB();
                    courseGroups1 = CourseGroups.selectFromCourseGroupsByWorkType("Індивідуальне заняття", "2");      // workType: IZ  semester: 2
                    courseGroups2 = CourseGroups.selectFromCourseGroupsByWorkType("Керівництво практиками", "2");        // workType: KP  semester: 2
                }

                DefaultTableModel model3 = new DefaultTableModel();
                model3.setColumnIdentifiers(new String[] {"", "Курс", "Шифр Груп", "||", "", "Курс", "Шифр Груп"});
                for (int i = 0; i < 3; ++i) {
                    Object[] o;
                    if (i == 2) {
                        o = new Object[] {"", "", "",
                                "", courseGroups2.get(i).getSubWorkType() + ' ' + courseGroups2.get(i).getStudentsType(), courseGroups2.get(i).getCourse(), courseGroups2.get(i).getGroups()};
                    } else {
                        o = new Object[] {courseGroups1.get(i).getSubWorkType() + ' ' + courseGroups1.get(i).getStudentsType(), courseGroups1.get(i).getCourse(), courseGroups1.get(i).getGroups(),
                                "", courseGroups2.get(i).getSubWorkType() + ' ' + courseGroups2.get(i).getStudentsType(), courseGroups2.get(i).getCourse(), courseGroups2.get(i).getGroups()};
                    }
                    model3.addRow(o);
                }
                tableCourseGroups.setModel(model3);

                TableColumnModel cg_cm = tableCourseGroups.getColumnModel();
                ColumnGroup g_iz1 = new ColumnGroup("Індивідуальне заняття");               // !!!
                g_iz1.add(cg_cm.getColumn(0));
                g_iz1.add(cg_cm.getColumn(1));
                g_iz1.add(cg_cm.getColumn(2));

                ColumnGroup g_kp1 = new ColumnGroup("Керівництво практиками");               // !!!
                g_kp1.add(cg_cm.getColumn(4));
                g_kp1.add(cg_cm.getColumn(5));
                g_kp1.add(cg_cm.getColumn(6));

                GroupableTableHeader cg_header = (GroupableTableHeader) tableCourseGroups.getTableHeader();
                cg_header.addColumnGroup(g_iz1);
                cg_header.addColumnGroup(g_kp1);

                cg_cm.getColumn(3).setMaxWidth(10);

                model3.addTableModelListener(new TableModelListener() {    // Update Table 'tableCourseGroups'
                    @Override
                    public void tableChanged(TableModelEvent e) {
                        System.out.println(e.getFirstRow() + " " + e.getColumn() + " -> " + model3.getValueAt(e.getFirstRow(),e.getColumn()));
                        if (e.getColumn() == 1) {
                            if (model3.getValueAt(e.getFirstRow(), e.getColumn()).toString().matches("[1-9]|^$"))  // only 1-9 or empty string
                                CourseGroups.updateCourseGroups_oneField(courseGroups1.get(e.getFirstRow()).getId(), "course", model3.getValueAt(e.getFirstRow(), e.getColumn()).toString());
                        } else if (e.getColumn() == 2)
                            CourseGroups.updateCourseGroups_oneField(courseGroups1.get(e.getFirstRow()).getId(), "groups", model3.getValueAt(e.getFirstRow(), e.getColumn()).toString());
                        else if (e.getColumn() == 5) {
                            if (model3.getValueAt(e.getFirstRow(), e.getColumn()).toString().matches("[1-9]|^$"))  // only 1-9 or empty string
                                CourseGroups.updateCourseGroups_oneField(courseGroups2.get(e.getFirstRow()).getId(), "course", model3.getValueAt(e.getFirstRow(), e.getColumn()).toString());
                        } else if (e.getColumn() == 6)
                            CourseGroups.updateCourseGroups_oneField(courseGroups2.get(e.getFirstRow()).getId(), "groups", model3.getValueAt(e.getFirstRow(), e.getColumn()).toString());
                        System.out.println("••• UPDATING THIS PANEL •••");
                        parent.updateThisPanel(1, 0, new boolean[] {true, false, false, false, false, false, false, false}, iptt.getJScrollPane_table1().getVerticalScrollBar().getValue(), iptt.getJScrollPane_table1().getVerticalScrollBar().getMaximum());        // JTabbedPaneOpenIndex: 1   2semester: true
                    }
                });

                // ---------- ---------- ----------


                iptt = new InnerPanelWithThreeTables(table1, tableTotal, tableCourseGroups, 0.25, JScrollBarValue, verticalScrollBarMaximum);
                iptt.setBackground(Color.white);
                iptt.setBorder(titled);
                iptt.setFont(font);
                GBlayout.setConstraints(iptt, GBconstraints);
                add(iptt);                                                                       // add panel with two tables

                return this;
            }
        }.execute(), "semester2");

        // ******************************************  END  ************************************************************

        add(cards, GBconstraints);

        if (openSecondSemester)
            semester2Button.doClick();
    }
}
