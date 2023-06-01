package GUI.SecondLevel;

import GUI.Dialogs.DialogDeleteWarning;
import GUI.FirstLevel.MainFrame;
import GUI.Utils.GroupableHeaderJTable.GroupableTableHeader;
import Resources.Models.NormInHours;
import Resources.Models.Teacher;
import GUI.Utils.ButtonInJTable;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;


public class PanelSettings extends JPanel {
    private Font font;
    private GridBagLayout GBlayout = new GridBagLayout();
    private GridBagConstraints GBconstraints = new GridBagConstraints();

    ArrayList<Teacher> teachers;
    ArrayList<NormInHours> hours;

    private JTable table1 = new JTable();
    private JTable tableHours;
    private ArrayList<Integer> blankFields = new ArrayList<>();
    private JButton importTeachersButton, deleteAllTeachers;
    JScrollPane jsp1;

    public PanelSettings(MainFrame parent, int JScrollBarValue, int verticalScrollBarMaximum) {
        setBackground(Color.white);
        setLayout(GBlayout);
        font = new Font("Franklin Gothic Book", Font.PLAIN, 14);


        GBconstraints.gridwidth = GridBagConstraints.REMAINDER;
        GBconstraints.fill = GridBagConstraints.BOTH;
        GBconstraints.weightx = 1;
        GBconstraints.weighty = 1;
        GBconstraints.insets = new Insets(20, 150, 0, 150);

        table1.setAutoCreateRowSorter(true);   // SORTING Tables

        teachers = Teacher.selectFromTeacher();

        ImageIcon icon = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("cross1.png")).getImage().getScaledInstance(20,20, Image.SCALE_SMOOTH));

        String[] columnNamesUKR = {"ПІБ", "Email", "Посада", "Ставка", ""};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNamesUKR);
        for (Teacher teacher : teachers) {
            Object[] o = new Object[] {teacher.getName(), teacher.getEmail(), teacher.getJob_title(), teacher.getBid(), icon};
            model.addRow(o);
        }
        for (int i = 0; i < 10; ++i) {               // add 10 blank fields for new teachers
            model.addRow(new Object[] {"","","",""});
            blankFields.add(model.getRowCount() - 1);
        }

        table1.setModel(model);

        TableModel tb = table1.getModel();
        tb.addTableModelListener(new TableModelListener() {       // Update Tables
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.DELETE)
                    return;
                if (e.getColumn() == 4) {          // Delete this row
                    int rowIndex = e.getFirstRow();
                    if (rowIndex < teachers.size()) {
                        System.out.println("DEL");
                        Teacher delTeacher = teachers.get(rowIndex);
                        Teacher.deleteTeacher(delTeacher.getID());
                        teachers.remove(delTeacher);
                        blankFields.add(model.getRowCount() - 11);
                        blankFields.remove(Integer.valueOf(model.getRowCount() - 1));
                        model.removeRow(e.getFirstRow());    // will produce TableModelEvent.DELETE
                    }
                    return;
                }
                System.out.println(e.getFirstRow() + " " + e.getColumn() + " -> " + tb.getValueAt(e.getFirstRow(),e.getColumn()));
                boolean isBlank = false;
                for (int blankField : blankFields) {
                    if (blankField == e.getFirstRow()) {
                        blankFields.remove((Integer) blankField);
                        isBlank = true;
                        break;
                    }
                }

                String value = tb.getValueAt(e.getFirstRow(), e.getColumn()).toString();
                if (isBlank) {
                    switch (e.getColumn()) {
                        case 0 -> Teacher.insertNewTeacher(value, "", "", "");  // name
                        case 1 -> Teacher.insertNewTeacher("", value, "", "");  // email
                        case 2 -> Teacher.insertNewTeacher("", "", value, "");  // job title
                        case 3 -> {
                            if (value.contains(","))
                                value = value.replace(",", ".");
                            if (value.matches("(\\d*\\.?\\d+)"))
                                Teacher.insertNewTeacher("", "", "", value);  // bid
                            else
                                Teacher.insertNewTeacher("", "", "", "0.0");  // bid
                        }
                    }
                } else {
                    Teacher.updateTeacherInDictionary_oneField(teachers.get(e.getFirstRow()).getID(),e.getColumn(), value, e.getColumn() == 3);
                }

                parent.updateThisPanel(0, 0, new boolean[] {false, false, false, false, false, false, false, false, false}, jsp1.getVerticalScrollBar().getValue(), jsp1.getVerticalScrollBar().getMaximum());

            }

        });


        // ********** Add Delete Button to JTable ********//


        ButtonInJTable buttonInJTable = new ButtonInJTable(table1, null, 4);

        TableColumnModel columnModel = table1.getColumnModel();
        columnModel.getColumn(4).setMaxWidth(20);


        // ********************************************//



        jsp1 = new JScrollPane(table1);
        jsp1.setBackground(Color.white);
        jsp1.setFont(font);
        jsp1.getVerticalScrollBar().setMaximum(verticalScrollBarMaximum);
        jsp1.getVerticalScrollBar().setValue(JScrollBarValue);
        Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        TitledBorder titled = BorderFactory.createTitledBorder(loweredetched, "Список Викладачів");
        titled.setTitleFont(font);
        jsp1.setBorder(titled);
        GBlayout.setConstraints(jsp1, GBconstraints);
        add(jsp1);




        GBconstraints.insets = new Insets(10, 150, 50, 100);
        GBconstraints.weighty = 0.1;
        GBconstraints.weightx = 0.5;
        GBconstraints.gridwidth = GridBagConstraints.RELATIVE;
        //GBconstraints.gridx = 0;
        GBconstraints.fill = GridBagConstraints.HORIZONTAL;

        importTeachersButton = new JButton("Імпорувати викладачів з Excel");
        importTeachersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (importTeachersFromExcel())
                    parent.updateThisPanel(0, 0, new boolean[] {false, false, false, false, false, false, false, false, false}, 0, 100);
            }
        });
        importTeachersButton.setBackground(new Color(220,220,220));
        //importTeachersButton.setBorderPainted(false);
        importTeachersButton.setFont(font);
        add(importTeachersButton, GBconstraints);


        GBconstraints.insets = new Insets(10, 100, 50, 150);

        deleteAllTeachers = new JButton("Видалити усіх викладачів");
        deleteAllTeachers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogDeleteWarning warn = new DialogDeleteWarning();
                if (warn.isYesAnswer()) {
                    Teacher.Truncate();
                    parent.updateThisPanel(0, 0, new boolean[] {false, false, false, false, false, false, false, false, false}, 0, 100);
                }
            }
        });
        deleteAllTeachers.setBackground(new Color(220,220,220));
        //deleteAllTeachers.setBorderPainted(false);
        deleteAllTeachers.setFont(font);
        add(deleteAllTeachers, GBconstraints);



        // ********************* Normativniy Rozpodil ******************************//

        GBconstraints.gridy = 2;
        GBconstraints.gridwidth = GridBagConstraints.REMAINDER;
        GBconstraints.fill = GridBagConstraints.BOTH;
        GBconstraints.weightx = 1;
        GBconstraints.weighty = 0.7;
        GBconstraints.insets = new Insets(20, 150, 50, 150);


        String[] columnNames = {"", "Усі", "Бакалавр", "Магістр ОПП", "Магістр ОНП", "Доктор Філософії", "Аспірант", "Аспірант заоч.", "Здобувач, стаж"};

        hours = NormInHours.selectFromNormInHours("");

        if (hours.size() != 14) {  // only for the first time (initiating DB)
            NormInHours.setUpDB();
            hours = NormInHours.selectFromNormInHours("");
        }

        tableHours = new JTable() {
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                else if (column == 1 && row != 1)
                    return false;
                else if (column == 2 && (row == 0 || row == 1 || row == 6 || row == 10 || row == 11 || row == 12 || row == 13))
                    return false;
                else if (column == 3 && (row == 1 || row == 7 || row == 8 || row == 9 || row == 12 || row == 13))
                    return false;
                else if (column == 4 && (row == 1 || row == 7 || row == 8 || row == 9 || row == 10 || row == 11 || row == 13))
                    return false;
                else if (column == 5 && row != 6)
                    return false;
                else if (row != 13 && (column == 6 || column == 7 || column == 8))
                    return false;
                else
                    return true;
            }
            protected JTableHeader createDefaultTableHeader() {
                return new GroupableTableHeader(columnModel);}
        };

        DefaultTableModel modelHours = new DefaultTableModel();
        modelHours.setColumnIdentifiers(columnNames);
        String all, b, m_opp, m_onp, df, a, a_z, zd_st;
        for (NormInHours hour : hours) {
            all = Math.abs(hour.getAll() + 1.0) < 0.000001d ? "" : String.valueOf(hour.getAll());
            b = Math.abs(hour.getB() + 1.0) < 0.000001d ? "" : String.valueOf(hour.getB());
            m_opp = Math.abs(hour.getM_opp() + 1.0) < 0.000001d ? "" : String.valueOf(hour.getM_opp());
            m_onp = Math.abs(hour.getM_onp() + 1.0) < 0.000001d ? "" : String.valueOf(hour.getM_onp());
            df = Math.abs(hour.getDf() + 1.0) < 0.000001d ? "" : String.valueOf(hour.getDf());
            a = Math.abs(hour.getA() + 1.0) < 0.000001d ? "" : String.valueOf(hour.getA());
            a_z = Math.abs(hour.getA_z() + 1.0) < 0.000001d ? "" : String.valueOf(hour.getA_z());
            zd_st = Math.abs(hour.getZd_st() + 1.0) < 0.000001d ? "" : String.valueOf(hour.getZd_st());

            Object[] o = new Object[] {hour.getTitle(), all, b, m_opp, m_onp, df, a, a_z, zd_st};
            modelHours.addRow(o);
        }
        tableHours.setModel(modelHours);

        TableColumnModel columnHoursModel = tableHours.getColumnModel();
        columnHoursModel.getColumn(0).setMinWidth(200);

        modelHours.addTableModelListener(new TableModelListener() {      // Update Table 'Норма в годинах'
            @Override
            public void tableChanged(TableModelEvent e) {
                System.out.println(e.getFirstRow() + " " + e.getColumn() + " -> " + modelHours.getValueAt(e.getFirstRow(),e.getColumn()));
                String input = modelHours.getValueAt(e.getFirstRow(),e.getColumn()).toString();
                if (input.contains(",")) {
                    input = input.replace(",", ".");
                    modelHours.setValueAt(input, e.getFirstRow(),e.getColumn());
                }
                if (input.matches("(\\d*\\.?\\d+)"))
                    NormInHours.updateNormInHours_oneField(hours.get(e.getFirstRow()).getId(),e.getColumn() + 1, input);
                else
                    modelHours.setValueAt(0.0, e.getFirstRow(),e.getColumn());
            }
        });




        JScrollPane jsp2 = new JScrollPane(tableHours);
        jsp2.setBackground(Color.white);
        jsp2.setFont(font);
        TitledBorder titled2 = BorderFactory.createTitledBorder(loweredetched, "Норма в годинах");
        titled2.setTitleFont(font);
        jsp2.setBorder(titled2);
        GBlayout.setConstraints(jsp2, GBconstraints);
        add(jsp2);


        // ******************************************************************//



    }

    static public boolean importTeachersFromExcel() {
        try {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setPreferredSize(new Dimension(700, 400));
            FileNameExtensionFilter xls = new FileNameExtensionFilter("Microsoft Excel Documents", "xls");
            jFileChooser.addChoosableFileFilter(xls);
            jFileChooser.setFileFilter(xls);
            jFileChooser.setAcceptAllFileFilterUsed(false);
            jFileChooser.setDialogTitle("<html>Виберіти excel-файл з викладачами, кожен рядок якого заповнений у вигляді: <i>'ПІБ' 'Пошта' 'Посада' 'Ставка'</html>");
            if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File openFile = jFileChooser.getSelectedFile();
                if (openFile != null) {
                    System.out.println(openFile);
                    HSSFWorkbook myExcelBook = new HSSFWorkbook(new FileInputStream(openFile));
                    HSSFSheet myExcelSheet = myExcelBook.getSheetAt(0);
                    HSSFRow row = myExcelSheet.getRow(0);

                    ArrayList<String> data = new ArrayList<>();
                    for (int i = 1; ; ++i) {
                        data.add(row.getCell(0).getStringCellValue());
                        data.add(row.getCell(1).getStringCellValue());
                        data.add(row.getCell(2).getStringCellValue());
                        data.add(String.valueOf(row.getCell(3).getNumericCellValue()));
                        row = myExcelSheet.getRow(i);
                        if (row == null)
                            break;
                    }
                    Teacher.insertTeachers(data);
                    myExcelBook.close();
                    System.out.println("--End--");
                }
                return true;
            }
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
        return false;
    }
}
