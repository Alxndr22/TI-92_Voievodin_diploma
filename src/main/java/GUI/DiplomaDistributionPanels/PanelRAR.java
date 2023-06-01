package GUI.DiplomaDistributionPanels;

import GUI.Utils.GroupableHeaderJTable.ColumnGroup;
import GUI.Utils.GroupableHeaderJTable.GroupableTableHeader;
import GUI.FirstLevel.MainFrame;
import Resources.Models.CourseGroups;
import Resources.Models.Teacher;
import Resources.Models.Teacher_and_DiplomaStudentsData;
import GUI.Utils.RedGreenAlertSystem;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;

public class PanelRAR extends DiplomaDistributionTab {    // Рецензув. атестац. роб

    public PanelRAR(MainFrame parent, boolean openSecondSemester, int JScrollBarValue, int verticalScrollBarMaximum) {
        super(parent);

        // **************************************   PanelRAR_logic (1 semester)  ***************************************
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
                    return column != 0;
                }
                protected JTableHeader createDefaultTableHeader() {
                    return new GroupableTableHeader(columnModel);}
            };

            private int dataShift = 32;

            private GridBagLayout GBlayout = new GridBagLayout();
            private GridBagConstraints GBconstraints = new GridBagConstraints();

            ArrayList<Teacher_and_DiplomaStudentsData> teacherAndData;
            ArrayList<CourseGroups> courseGroups;

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
                GBconstraints.insets = new Insets(20, 20, 50, 20);

                table1.setAutoCreateRowSorter(true);   // SORTING Tables

                teacherAndData = Teacher_and_DiplomaStudentsData.selectFromTeacher_and_DiplomaStudentsData();    // !!!!!!! (30_04)


                String[] columnNamesUKR = {"№", "ПІБ", "Посада", "бак Б", "бак К", "маг ОПП Б", "маг ОПП К", "маг ОНП Б", "маг ОНП К"};
                DefaultTableModel model2 = new DefaultTableModel();
                String[] totalColumnNames = {"РАЗОМ:", "-", "-", "бак Б", "бак К", "маг ОПП Б", "маг ОПП К", "маг ОНП Б", "маг ОНП К"};
                model2.setColumnIdentifiers(totalColumnNames);

                DefaultTableModel model = new DefaultTableModel();
                model.setColumnIdentifiers(columnNamesUKR);
                for (Teacher_and_DiplomaStudentsData TandD : teacherAndData) {
                    Object[] o = new Object[] {TandD.getId_teacher(), TandD.getName(), TandD.getJob_title(),
                            TandD.getRar_b_b(), TandD.getRar_b_k(), TandD.getRar_mopp_b(), TandD.getRar_mopp_k(),
                            TandD.getRar_monp_b(), TandD.getRar_monp_k() };
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
                String[] totalAmount = {"РОЗПОДІЛЕНО", "-", "-", String.valueOf(distributedSum[dataShift]), String.valueOf(distributedSum[1 + dataShift]), String.valueOf(distributedSum[2 + dataShift]), String.valueOf(distributedSum[3 + dataShift]), String.valueOf(distributedSum[4 + dataShift]),
                        String.valueOf(distributedSum[5 + dataShift])};
                model2.addRow(totalAmount);

                String[] plannedAmount = {"ЗАПЛАНОВАНО", "-", "-", String.valueOf(plannedData[dataShift]), String.valueOf(plannedData[1 + dataShift]), String.valueOf(plannedData[2 + dataShift]), String.valueOf(plannedData[3 + dataShift]), String.valueOf(plannedData[4 + dataShift]),
                        String.valueOf(plannedData[5 + dataShift])};
                model2.addRow(plannedAmount);
                tableTotal.setModel(model2);


                for (int i = 3; i < tableTotal.getColumnCount(); i++) {                                 // compute and mark red fields when first open panel
                    tableTotal.getColumnModel().getColumn(i).setCellRenderer(redGreenAlertSystem);              // RedGreenAlertSystem
                }


                TableColumnModel cm = table1.getColumnModel();
                ColumnGroup g_name = new ColumnGroup("Викладач");
                g_name.add(cm.getColumn(1));
                g_name.add(cm.getColumn(2));
                ColumnGroup g_rar = new ColumnGroup("Рецензування атестац. роб");
                g_rar.add(cm.getColumn(3));
                g_rar.add(cm.getColumn(4));
                g_rar.add(cm.getColumn(5));
                g_rar.add(cm.getColumn(6));
                g_rar.add(cm.getColumn(7));
                g_rar.add(cm.getColumn(8));

                GroupableTableHeader header = (GroupableTableHeader)table1.getTableHeader();
                header.addColumnGroup(g_name);
                header.addColumnGroup(g_rar);

                TableColumnModel cm1 = tableTotal.getColumnModel();
                g_name = new ColumnGroup("-");
                g_name.add(cm1.getColumn(1));
                g_name.add(cm1.getColumn(2));
                g_rar = new ColumnGroup("Рецензування атестац. роб");
                g_rar.add(cm1.getColumn(3));
                g_rar.add(cm1.getColumn(4));
                g_rar.add(cm1.getColumn(5));
                g_rar.add(cm1.getColumn(6));
                g_rar.add(cm1.getColumn(7));
                g_rar.add(cm1.getColumn(8));
                header = (GroupableTableHeader)tableTotal.getTableHeader();
                header.addColumnGroup(g_name);
                header.addColumnGroup(g_rar);




                TableModel tb = table1.getModel();
                tb.addTableModelListener(new TableModelListener() {       // Update Tables
                    @Override
                    public void tableChanged(TableModelEvent e) {
                        System.out.println(e.getFirstRow() + " " + e.getColumn() + " -> " + tb.getValueAt(e.getFirstRow(),e.getColumn()));
                        Teacher.updateTeacher_oneField(teacherAndData.get(e.getFirstRow()).getId_teacher(), e.getColumn() + dataShift, tb.getValueAt(e.getFirstRow(),e.getColumn()).toString());      // (зсув + 32)

                        System.out.println("••• UPDATING THIS PANEL •••");
                        parent.updateThisPanel(1, 4, new boolean[] {false, false, false, false, false, false, false, false}, iptt.getJScrollPane_table1().getVerticalScrollBar().getValue(), iptt.getJScrollPane_table1().getVerticalScrollBar().getMaximum());           // JTabbedPaneOpenIndex: 5   openSecondSemester: false
                    }
                });
                TableModel tm1 = tableTotal.getModel();
                tm1.addTableModelListener(new TableModelListener() {
                    @Override
                    public void tableChanged(TableModelEvent e) {
                        System.out.println(e.getFirstRow() + " " + e.getColumn() + " -> " + tm1.getValueAt(e.getFirstRow(),e.getColumn()));
                        Teacher_and_DiplomaStudentsData.updatePlanned_oneField(e.getColumn() - 3 + dataShift, Integer.parseInt(tm1.getValueAt(e.getFirstRow(), e.getColumn()).toString()));     // updating 'ЗАПЛАНОВАНО' (planned)   (зсув + 32)

                        System.out.println("••• UPDATING THIS PANEL •••");
                        parent.updateThisPanel(1, 4, new boolean[] {false, false, false, false, false, false, false, false}, iptt.getJScrollPane_table1().getVerticalScrollBar().getValue(), iptt.getJScrollPane_table1().getVerticalScrollBar().getMaximum());           // JTabbedPaneOpenIndex: 5   openSecondSemester: false
                    }
                });


                Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
                TitledBorder titled = BorderFactory.createTitledBorder(loweredetched, "I семестр");
                titled.setTitleFont(font);


                // ---------- TableCourseGroup ----------

                courseGroups = CourseGroups.selectFromCourseGroupsByWorkType("Рецензування атестац. роб", "1");        // workType: RAR   semester: 1

                if (courseGroups.isEmpty()) {
                    CourseGroups.setUpDB();
                    courseGroups = CourseGroups.selectFromCourseGroupsByWorkType("Рецензування атестац. роб", "1");      // workType: RAR   semester: 1
                }

                DefaultTableModel model3 = new DefaultTableModel();
                model3.setColumnIdentifiers(new String[] {"", "Курс", "Шифр Груп"});
                for (CourseGroups cg : courseGroups) {
                    Object[] o = new Object[] {cg.getSubWorkType() + ' ' + cg.getStudentsType(), cg.getCourse(), cg.getGroups()};
                    model3.addRow(o);
                }
                tableCourseGroups.setModel(model3);

                TableColumnModel cg_cm = tableCourseGroups.getColumnModel();
                ColumnGroup g_kar1 = new ColumnGroup("Рецензування атестац. роб");               // !!!
                g_kar1.add(cg_cm.getColumn(0));
                g_kar1.add(cg_cm.getColumn(1));
                g_kar1.add(cg_cm.getColumn(2));


                GroupableTableHeader cg_header = (GroupableTableHeader) tableCourseGroups.getTableHeader();
                cg_header.addColumnGroup(g_kar1);

                model3.addTableModelListener(new TableModelListener() {    // Update Table 'tableCourseGroups'
                    @Override
                    public void tableChanged(TableModelEvent e) {
                        System.out.println(e.getFirstRow() + " " + e.getColumn() + " -> " + model3.getValueAt(e.getFirstRow(),e.getColumn()));
                        if (e.getColumn() == 1) {
                            if (model3.getValueAt(e.getFirstRow(), e.getColumn()).toString().matches("[1-9]|^$"))  // only 1-9 or empty string
                                CourseGroups.updateCourseGroups_oneField(courseGroups.get(e.getFirstRow()).getId(), "course", model3.getValueAt(e.getFirstRow(), e.getColumn()).toString());
                        } else if (e.getColumn() == 2)
                            CourseGroups.updateCourseGroups_oneField(courseGroups.get(e.getFirstRow()).getId(), "groups", model3.getValueAt(e.getFirstRow(), e.getColumn()).toString());
                        System.out.println("••• UPDATING THIS PANEL •••");
                        parent.updateThisPanel(1, 4, new boolean[] {false, false, false, false, false, false, false, false}, iptt.getJScrollPane_table1().getVerticalScrollBar().getValue(), iptt.getJScrollPane_table1().getVerticalScrollBar().getMaximum());        // JTabbedPaneOpenIndex: 5   2semester: false
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
        }.execute(), "semester1");

        // ******************************************  END  ************************************************************

        // **************************************   PanelRAR_logic (2 semester)   **************************************
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
                    return column != 0;
                }
                protected JTableHeader createDefaultTableHeader() {
                    return new GroupableTableHeader(columnModel);}
            };

            private int dataShift = 60 + 32;

            private GridBagLayout GBlayout = new GridBagLayout();
            private GridBagConstraints GBconstraints = new GridBagConstraints();

            ArrayList<Teacher_and_DiplomaStudentsData> teacherAndData;
            ArrayList<CourseGroups> courseGroups;

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
                GBconstraints.insets = new Insets(20, 20, 50, 20);

                table1.setAutoCreateRowSorter(true);   // SORTING Tables

                teacherAndData = Teacher_and_DiplomaStudentsData.selectFromTeacher_and_DiplomaStudentsData();    // !!!!!!! (30_04)


                String[] columnNamesUKR = {"№", "ПІБ", "Посада", "бак Б", "бак К", "маг ОПП Б", "маг ОПП К", "маг ОНП Б", "маг ОНП К"};
                DefaultTableModel model2 = new DefaultTableModel();
                String[] totalColumnNames = {"РАЗОМ:", "-", "-", "бак Б", "бак К", "маг ОПП Б", "маг ОПП К", "маг ОНП Б", "маг ОНП К"};
                model2.setColumnIdentifiers(totalColumnNames);

                DefaultTableModel model = new DefaultTableModel();
                model.setColumnIdentifiers(columnNamesUKR);
                for (Teacher_and_DiplomaStudentsData TandD : teacherAndData) {
                    Object[] o = new Object[] {TandD.getId_teacher(), TandD.getName(), TandD.getJob_title(),
                            TandD.getRar_b_b_2(), TandD.getRar_b_k_2(), TandD.getRar_mopp_b_2(), TandD.getRar_mopp_k_2(),
                            TandD.getRar_monp_b_2(), TandD.getRar_monp_k_2() };
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
                String[] totalAmount = {"РОЗПОДІЛЕНО", "-", "-", String.valueOf(distributedSum[dataShift]), String.valueOf(distributedSum[1 + dataShift]), String.valueOf(distributedSum[2 + dataShift]), String.valueOf(distributedSum[3 + dataShift]), String.valueOf(distributedSum[4 + dataShift]),
                        String.valueOf(distributedSum[5 + dataShift])};
                model2.addRow(totalAmount);

                String[] plannedAmount = {"ЗАПЛАНОВАНО", "-", "-", String.valueOf(plannedData[dataShift]), String.valueOf(plannedData[1 + dataShift]), String.valueOf(plannedData[2 + dataShift]), String.valueOf(plannedData[3 + dataShift]), String.valueOf(plannedData[4 + dataShift]),
                        String.valueOf(plannedData[5 + dataShift])};
                model2.addRow(plannedAmount);
                tableTotal.setModel(model2);


                for (int i = 3; i < tableTotal.getColumnCount(); i++) {                                 // compute and mark red fields when first open panel
                    tableTotal.getColumnModel().getColumn(i).setCellRenderer(redGreenAlertSystem);              // RedGreenAlertSystem
                }


                TableColumnModel cm = table1.getColumnModel();
                ColumnGroup g_name = new ColumnGroup("Викладач");
                g_name.add(cm.getColumn(1));
                g_name.add(cm.getColumn(2));
                ColumnGroup g_rar = new ColumnGroup("Рецензування атестац. роб");
                g_rar.add(cm.getColumn(3));
                g_rar.add(cm.getColumn(4));
                g_rar.add(cm.getColumn(5));
                g_rar.add(cm.getColumn(6));
                g_rar.add(cm.getColumn(7));
                g_rar.add(cm.getColumn(8));

                GroupableTableHeader header = (GroupableTableHeader)table1.getTableHeader();
                header.addColumnGroup(g_name);
                header.addColumnGroup(g_rar);

                TableColumnModel cm1 = tableTotal.getColumnModel();
                g_name = new ColumnGroup("-");
                g_name.add(cm1.getColumn(1));
                g_name.add(cm1.getColumn(2));
                g_rar = new ColumnGroup("Рецензування атестац. роб");
                g_rar.add(cm1.getColumn(3));
                g_rar.add(cm1.getColumn(4));
                g_rar.add(cm1.getColumn(5));
                g_rar.add(cm1.getColumn(6));
                g_rar.add(cm1.getColumn(7));
                g_rar.add(cm1.getColumn(8));
                header = (GroupableTableHeader)tableTotal.getTableHeader();
                header.addColumnGroup(g_name);
                header.addColumnGroup(g_rar);




                TableModel tb = table1.getModel();
                tb.addTableModelListener(new TableModelListener() {       // Update Tables
                    @Override
                    public void tableChanged(TableModelEvent e) {
                        System.out.println(e.getFirstRow() + " " + e.getColumn() + " -> " + tb.getValueAt(e.getFirstRow(),e.getColumn()));
                        Teacher.updateTeacher_oneField(teacherAndData.get(e.getFirstRow()).getId_teacher(), e.getColumn() + dataShift, tb.getValueAt(e.getFirstRow(),e.getColumn()).toString());      // (зсув + 60 + 32)

                        System.out.println("••• UPDATING THIS PANEL •••");
                        parent.updateThisPanel(1, 4, new boolean[] {false, false, false, false, true, false, false, false}, iptt.getJScrollPane_table1().getVerticalScrollBar().getValue(), iptt.getJScrollPane_table1().getVerticalScrollBar().getMaximum());           // JTabbedPaneOpenIndex: 5   openSecondSemester: true
                    }
                });
                TableModel tm1 = tableTotal.getModel();
                tm1.addTableModelListener(new TableModelListener() {
                    @Override
                    public void tableChanged(TableModelEvent e) {
                        System.out.println(e.getFirstRow() + " " + e.getColumn() + " -> " + tm1.getValueAt(e.getFirstRow(),e.getColumn()));
                        Teacher_and_DiplomaStudentsData.updatePlanned_oneField(e.getColumn() - 3 + dataShift, Integer.parseInt(tm1.getValueAt(e.getFirstRow(), e.getColumn()).toString()));     // updating 'ЗАПЛАНОВАНО' (planned)   (зсув + 60 + 32)

                        System.out.println("••• UPDATING THIS PANEL •••");
                        parent.updateThisPanel(1, 4, new boolean[] {false, false, false, false, true, false, false, false}, iptt.getJScrollPane_table1().getVerticalScrollBar().getValue(), iptt.getJScrollPane_table1().getVerticalScrollBar().getMaximum());           // JTabbedPaneOpenIndex: 5   openSecondSemester: true
                    }
                });


                Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
                TitledBorder titled = BorderFactory.createTitledBorder(loweredetched, "II семестр");
                titled.setTitleFont(font);


                // ---------- TableCourseGroup ----------

                courseGroups = CourseGroups.selectFromCourseGroupsByWorkType("Рецензування атестац. роб", "2");        // workType: RAR   semester: 2

                if (courseGroups.isEmpty()) {
                    CourseGroups.setUpDB();
                    courseGroups = CourseGroups.selectFromCourseGroupsByWorkType("Рецензування атестац. роб", "2");      // workType: RAR   semester: 2
                }

                DefaultTableModel model3 = new DefaultTableModel();
                model3.setColumnIdentifiers(new String[] {"", "Курс", "Шифр Груп"});
                for (CourseGroups cg : courseGroups) {
                    Object[] o = new Object[] {cg.getSubWorkType() + ' ' + cg.getStudentsType(), cg.getCourse(), cg.getGroups()};
                    model3.addRow(o);
                }
                tableCourseGroups.setModel(model3);

                TableColumnModel cg_cm = tableCourseGroups.getColumnModel();
                ColumnGroup g_kar1 = new ColumnGroup("Рецензування атестац. роб");               // !!!
                g_kar1.add(cg_cm.getColumn(0));
                g_kar1.add(cg_cm.getColumn(1));
                g_kar1.add(cg_cm.getColumn(2));


                GroupableTableHeader cg_header = (GroupableTableHeader) tableCourseGroups.getTableHeader();
                cg_header.addColumnGroup(g_kar1);

                model3.addTableModelListener(new TableModelListener() {    // Update Table 'tableCourseGroups'
                    @Override
                    public void tableChanged(TableModelEvent e) {
                        System.out.println(e.getFirstRow() + " " + e.getColumn() + " -> " + model3.getValueAt(e.getFirstRow(),e.getColumn()));
                        if (e.getColumn() == 1) {
                            if (model3.getValueAt(e.getFirstRow(), e.getColumn()).toString().matches("[1-9]|^$"))  // only 1-9 or empty string
                                CourseGroups.updateCourseGroups_oneField(courseGroups.get(e.getFirstRow()).getId(), "course", model3.getValueAt(e.getFirstRow(), e.getColumn()).toString());
                        } else if (e.getColumn() == 2)
                            CourseGroups.updateCourseGroups_oneField(courseGroups.get(e.getFirstRow()).getId(), "groups", model3.getValueAt(e.getFirstRow(), e.getColumn()).toString());
                        System.out.println("••• UPDATING THIS PANEL •••");
                        parent.updateThisPanel(1 ,4, new boolean[] {false, false, false, false, true, false, false, false}, iptt.getJScrollPane_table1().getVerticalScrollBar().getValue(), iptt.getJScrollPane_table1().getVerticalScrollBar().getMaximum());        // JTabbedPaneOpenIndex: 5   2semester: true
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
