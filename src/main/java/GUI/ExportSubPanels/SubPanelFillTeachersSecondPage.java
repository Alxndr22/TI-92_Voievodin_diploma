package GUI.ExportSubPanels;

import GUI.Dialogs.DialogWait;
import Resources.CalculationsAndExport;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SubPanelFillTeachersSecondPage extends JPanel {
    private Font font;
    private GridBagLayout GBlayout = new GridBagLayout();
    private GridBagConstraints GBconstraints = new GridBagConstraints();

    private JButton pathToExportFilesButton, fillTeachersSecondPageButton, generateOnlySecondPageButton;
    private JTextField savePathField;
    private JLabel savePathLabel;

    private String savePath;

    public SubPanelFillTeachersSecondPage() {
        setBackground(Color.white);
        setLayout(GBlayout);
        font = new Font("Franklin Gothic Book", Font.PLAIN, 14);

        GBconstraints.weighty = 0.3;
        GBconstraints.fill = GridBagConstraints.HORIZONTAL;
        GBconstraints.gridy = 0;

        GBconstraints.gridx = 0;
        GBconstraints.weightx = 0.5;
        JLabel space = new JLabel();
        add(space, GBconstraints);

        GBconstraints.gridx = 1;
        GBconstraints.weightx = 1.0;
        JLabel space1 = new JLabel();
        add(space1, GBconstraints);

        GBconstraints.gridx = 2;

        JLabel space2 = new JLabel();
        add(space2, GBconstraints);

        GBconstraints.gridx = 3;
        JLabel space3 = new JLabel();
        add(space3, GBconstraints);

        GBconstraints.gridx = 4;
        GBconstraints.weightx = 0.1;
        JLabel space4 = new JLabel();
        add(space4, GBconstraints);

        GBconstraints.gridx = 5;
        JLabel space5 = new JLabel();
        add(space5, GBconstraints);


        GBconstraints.insets = new Insets(20, 20, 5, 20);
        GBconstraints.weightx = 1.0;
        GBconstraints.gridy = 1;
        GBconstraints.gridx = 0;
        GBconstraints.gridwidth = 6;

        savePathLabel = new JLabel("Шлях експорту заповнених excel-файлів:");
        savePathLabel.setFont(font);

        add(savePathLabel, GBconstraints);

        GBconstraints.insets = new Insets(0, 20, 20, 10);
        GBconstraints.gridy = 2;
        GBconstraints.gridx = 0;
        GBconstraints.gridwidth = 4;


        savePathField = new JTextField(100);
        savePathField.setEditable(false);
        savePath = System.getProperty("user.dir");     // default user directory (this time - project directory)
        savePathField.setText(savePath);
        add(savePathField, GBconstraints);

        GBconstraints.insets = new Insets(0, 0, 20, 10);
        GBconstraints.gridy = 2;
        GBconstraints.gridx = 4;
        GBconstraints.weightx = 0.1;
        GBconstraints.gridwidth = 2;

        pathToExportFilesButton = new JButton("Вказати інший шлях");
        pathToExportFilesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setDialogTitle("Оберіть директорію для експорту заповнених excel-файлів:");
                jFileChooser.setCurrentDirectory(new File(savePath));
                jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                jFileChooser.setAcceptAllFileFilterUsed(false);
                if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    savePath = jFileChooser.getSelectedFile().toString();
                    savePathField.setText(savePath);
                } else {
                    System.out.println("No Selection ");
                }
            }
        });
        pathToExportFilesButton.setBackground(new Color(220,220,220));
        pathToExportFilesButton.setFont(font);
        add(pathToExportFilesButton, GBconstraints);


        //       BUTTONS


        GBconstraints.fill = GridBagConstraints.NONE;
        GBconstraints.weightx = 1.0;
        GBconstraints.insets = new Insets(30, 50, 20, 25);
        GBconstraints.gridy = 3;
        GBconstraints.gridx = 1;
        GBconstraints.gridwidth = 2;
        GBconstraints.ipady = 10;
        GBconstraints.ipadx = 150;
        fillTeachersSecondPageButton = new JButton("<html>Заповнити другу сторінку<br>excel-файлів викладачів</html>");
        fillTeachersSecondPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogWait dialWait = new DialogWait();            // Show Wait (Dialog)

                Timer timer = new Timer(10, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        CalculationsAndExport.exportTeachersFirstAndSecondPage(savePath);
                        dialWait.dispose();
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
        });
        fillTeachersSecondPageButton.setBackground(new Color(220,220,220));
        fillTeachersSecondPageButton.setFont(font);
        add(fillTeachersSecondPageButton, GBconstraints);

        GBconstraints.insets = new Insets(30, 25, 20, 50);
        GBconstraints.gridy = 3;
        GBconstraints.gridx = 3;
        GBconstraints.gridwidth = 2;
        generateOnlySecondPageButton = new JButton("<html>Експортувати лише другу сторінку<br>excel-файлів викладачів (без 'Лист1')</html>");
        generateOnlySecondPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogWait dialWait = new DialogWait();            // Show Wait (Dialog)

                Timer timer = new Timer(10, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CalculationsAndExport.exportOnlyTeachersSecondPageUsingTemplate(null, savePath);
                        dialWait.dispose();
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
        });
        generateOnlySecondPageButton.setBackground(new Color(220,220,220));
        generateOnlySecondPageButton.setFont(font);
        add(generateOnlySecondPageButton, GBconstraints);

    }
}
