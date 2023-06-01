package GUI.ExportSubPanels;

import GUI.Dialogs.DialogError;
import GUI.Dialogs.DialogExportFinished;
import GUI.Dialogs.DialogWait;
import GUI.Dialogs.DialogWarning;
import Resources.CalculationsAndExport;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SubPanelFillReportDiplomaStudents extends JPanel {
    private Font font;
    private GridBagLayout GBlayout = new GridBagLayout();
    private GridBagConstraints GBconstraints = new GridBagConstraints();

    private JButton pathToExportFilesButton, fillReportButton;
    private JTextField savePathField;
    private JLabel savePathLabel;

    private String savePath;

    public SubPanelFillReportDiplomaStudents() {
        setBackground(Color.white);
        setLayout(GBlayout);
        font = new Font("Franklin Gothic Book", Font.PLAIN, 14);

        GBconstraints.insets = new Insets(20, 20, 5, 0);
        GBconstraints.weighty = 0.3;
        GBconstraints.weightx = 1;
        GBconstraints.gridwidth = GridBagConstraints.REMAINDER;
        GBconstraints.fill = GridBagConstraints.HORIZONTAL;

        savePathLabel = new JLabel("Шлях експорту заповненого excel-файлу:");
        savePathLabel.setFont(font);
        add(savePathLabel, GBconstraints);

        GBconstraints.insets = new Insets(0, 20, 20, 10);
        GBconstraints.gridwidth = GridBagConstraints.RELATIVE;
        GBconstraints.weightx = 0.8;

        savePathField = new JTextField(100);
        savePathField.setEditable(false);
        savePath = System.getProperty("user.dir") + "\\ReportDiplomaStudents.xls";     // default user directory (this time - project directory)
        savePathField.setText(savePath);
        add(savePathField, GBconstraints);

        GBconstraints.insets = new Insets(0, 0, 20, 20);
        GBconstraints.gridwidth = GridBagConstraints.REMAINDER;
        GBconstraints.weightx = 0.1;

        pathToExportFilesButton = new JButton("Вказати інший шлях");
        pathToExportFilesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setCurrentDirectory(new File(savePath));
                jFileChooser.setDialogTitle("Оберіть директорію і введіть назву excel-файлу для експорту:");
                jFileChooser.showSaveDialog(null);
                File saveFile = jFileChooser.getSelectedFile();
                if (saveFile != null) {
                    savePath = saveFile.toString();
                    int dotIndex = savePath.indexOf(".");
                    if (dotIndex != -1) {
                        savePath = savePath.substring(0, dotIndex);
                    }
                    savePath += ".xls";
                    System.out.println(savePath);
                    savePathField.setText(savePath);  // ??  for func 'InputOutputTools.isFileAlreadyExist()'

                        if (isFileAlreadyExist(savePathField)) {     // isAlreadyExist
                            DialogWarning warn = new DialogWarning();
                            if (!warn.isYesAnswer()) {             // НЕ перезаписуємо файл
                                savePathField.setText(savePath);
                                pathToExportFilesButton.doClick();
                            }
                        }
                }
            }
        });
        pathToExportFilesButton.setBackground(new Color(220,220,220));
        pathToExportFilesButton.setFont(font);
        add(pathToExportFilesButton, GBconstraints);




        GBconstraints.insets = new Insets(30, 250, 20, 250);
        GBconstraints.weightx = 1;
        fillReportButton = new JButton("Заповнити Звіт \"викладач - кількість дипломників\"");
        fillReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogWait dialWait = new DialogWait();            // Show Wait (Dialog)

                Timer timer = new Timer(100, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {

                        boolean yesAnswer = false;
                        if (isFileAlreadyExist(savePathField)) {   // isAlreadyExist
                            DialogWarning warn = new DialogWarning();
                            yesAnswer = warn.isYesAnswer();
                            if (yesAnswer) {   // перезаписуємо
                                String status = CalculationsAndExport.exportReport(savePath);
                                dialWait.dispose();
                                if (status.equals("success")) {
                                    DialogExportFinished dialExpFin = new DialogExportFinished();
                                } else {
                                    DialogError dialError = new DialogError(status);
                                }
                            } else {         // НЕ перезаписуємо файл
                                dialWait.dispose();
                                pathToExportFilesButton.doClick();
                            }
                        } else {
                            String status = CalculationsAndExport.exportReport(savePath);
                            dialWait.dispose();
                            if (status.equals("success")) {
                                DialogExportFinished dialExpFin = new DialogExportFinished();
                            } else {
                                DialogError dialError = new DialogError(status);
                            }
                        }



                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
        });
        fillReportButton.setBackground(new Color(220,220,220));
        fillReportButton.setFont(font);
        add(fillReportButton, GBconstraints);
    }

    public static boolean isFileAlreadyExist(JTextField savePathField) {
        boolean isAlreadyExist = true;
        String savePath = savePathField.getText();
        FileInputStream test = null;
        try {
            test = new FileInputStream(savePath);
        } catch (FileNotFoundException ee) {
            savePathField.setText(savePath);
            isAlreadyExist = false;
            System.out.println(ee);
        } finally {
            try {
                test.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (NullPointerException npe) {
                System.out.println(npe);
            }
        }
        return isAlreadyExist;
    }
}
