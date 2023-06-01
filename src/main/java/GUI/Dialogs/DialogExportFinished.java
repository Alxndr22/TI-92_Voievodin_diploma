package GUI.Dialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogExportFinished extends JDialog {
    private Font font;

    private JLabel labIcon, labInfo;
    private JButton exit;

    GridBagLayout GBlayout = new GridBagLayout();
    GridBagConstraints GBconstraints = new GridBagConstraints();
    public DialogExportFinished() {
        super((JDialog) null);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width-300)/2, (screenSize.height-150)/2);
        setTitle("Статус виконання");
        setSize(300, 150);
        setLayout(GBlayout);
        setBackground(Color.WHITE);
        font = new Font("Franklin Gothic Book", Font.PLAIN, 16);

        ImageIcon icon_finished = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("finished.png")).getImage());
        labIcon = new JLabel(icon_finished);
        GBconstraints.insets = new Insets(0, 0, 0, 10);
        add(labIcon, GBconstraints);

        labInfo = new JLabel("Експорт завершено!");
        labInfo.setFont(font);
        GBconstraints.insets = new Insets(0, 0, 0, 0);
        add(labInfo, GBconstraints);

        GBconstraints.gridwidth = GridBagConstraints.REMAINDER;
        GBconstraints.gridy = 1;
        GBconstraints.insets = new Insets(30, 0, 0, 0);

        exit = new JButton("ОК");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        exit.setBackground(new Color(220,220,220));
        exit.setFont(font);
        add(exit, GBconstraints);

        setVisible(true);
    }
}
