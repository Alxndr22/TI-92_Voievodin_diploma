package GUI.Dialogs;

import javax.swing.*;
import java.awt.*;

public class DialogExportResults extends JDialog {

    public DialogExportResults(String successExportResults, String failedExportResults, boolean isExport) {
        super((JDialog) null);
        setModalityType(DEFAULT_MODALITY_TYPE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width-1000)/2, (screenSize.height-800)/2);
        if (isExport)
            setTitle("Результати експорту");
        else
            setTitle("Результати імпорту");
        setSize(1000, 800);
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JScrollPane jsp = new JScrollPane(new JPanel() {
            private Font font, fontBold;
            GridBagLayout GBlayout = new GridBagLayout();
            GridBagConstraints GBconstraints = new GridBagConstraints();


            private JTextArea successTextArea, failedTextArea;
            private JLabel successLabel, failedLabel;

            public JPanel execute() {
                setLayout(GBlayout);
                setBackground(Color.WHITE);
                font = new Font("Franklin Gothic Book", Font.PLAIN, 16);
                fontBold = new Font("Franklin Gothic Demi", Font.PLAIN, 16);

                GBconstraints.insets = new Insets(15, 50, 10, 50);
                GBconstraints.gridy = 0;
                GBconstraints.gridx = 0;
                GBconstraints.weightx = 1.0;
                successLabel = isExport ? new JLabel("Успішно експортовано:") : new JLabel("Успішно занесено в базу даних:");
                successLabel.setFont(fontBold);
                add(successLabel, GBconstraints);

                GBconstraints.insets = new Insets(0, 50, 10, 50);
                GBconstraints.gridy = 1;
                successTextArea = new JTextArea(successExportResults);
                successTextArea.setEditable(false);
                successTextArea.setLineWrap(true);
                successTextArea.setWrapStyleWord(true);
                successTextArea.setFont(font);
                successTextArea.setForeground(new Color(100, 100, 100));
                successTextArea.setBackground(Color.WHITE);  // 242
                successTextArea.setSize(900, 100);
                add(successTextArea, GBconstraints);


                GBconstraints.gridy = 2;
                failedLabel = isExport ? new JLabel("Не вдалося експортувати:") : new JLabel("Не занесено в базу даних (дублікати):");
                failedLabel.setFont(fontBold);
                add(failedLabel, GBconstraints);

                GBconstraints.gridy = 3;
                failedTextArea = new JTextArea(failedExportResults);
                failedTextArea.setEditable(false);
                failedTextArea.setLineWrap(true);
                failedTextArea.setWrapStyleWord(true);
                failedTextArea.setFont(font);
                failedTextArea.setForeground(new Color(139, 43, 43));
                failedTextArea.setBackground(Color.WHITE);
                failedTextArea.setSize(900, 100);
                add(failedTextArea, GBconstraints);

                setVisible(true);
                return this;
            }
        }.execute());

        add(jsp);

        setVisible(true);
    }
}
