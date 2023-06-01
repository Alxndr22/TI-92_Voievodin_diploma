package GUI.ExportSubPanels;

import GUI.Dialogs.DialogWait;
import Resources.CalculationsAndExport;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SubPanelFillPlan extends JPanel {
    private Font font;
    private GridBagLayout GBlayout = new GridBagLayout();
    private GridBagConstraints GBconstraints = new GridBagConstraints();

    private JButton pathToExportFilesButton, fillPlanButton;
    private JTextField savePathField;
    private JLabel savePathLabel;

    private String savePath;

    public SubPanelFillPlan() {
        setBackground(Color.white);
        setLayout(GBlayout);
        font = new Font("Franklin Gothic Book", Font.PLAIN, 14);


        GBconstraints.insets = new Insets(20, 20, 5, 0);
        GBconstraints.weighty = 0.3;
        GBconstraints.weightx = 1;
        GBconstraints.gridwidth = GridBagConstraints.REMAINDER;
        GBconstraints.fill = GridBagConstraints.HORIZONTAL;

        savePathLabel = new JLabel("Шлях експорту заповнених ПЛАНів навч. навант.:");
        savePathLabel.setFont(font);
        add(savePathLabel, GBconstraints);

        GBconstraints.insets = new Insets(0, 20, 20, 10);
        GBconstraints.gridwidth = GridBagConstraints.RELATIVE;
        GBconstraints.weightx = 0.8;

        savePathField = new JTextField(100);
        savePathField.setEditable(false);
        savePath = System.getProperty("user.dir");     // default user directory (this time - project directory)
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




        GBconstraints.insets = new Insets(30, 250, 20, 250);
        GBconstraints.weightx = 1;
        fillPlanButton = new JButton("Заповнити ПЛАН навч. навант. [Б+К]");
        fillPlanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogWait dialWait = new DialogWait();            // Show Wait (Dialog)

                Timer timer = new Timer(10, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        CalculationsAndExport.exportPlanB_PlanK(savePath);
                        dialWait.dispose();
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
        });
        fillPlanButton.setBackground(new Color(220,220,220));
        fillPlanButton.setFont(font);
        add(fillPlanButton, GBconstraints);

    }
}
