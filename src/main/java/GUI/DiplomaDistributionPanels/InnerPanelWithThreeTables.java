package GUI.DiplomaDistributionPanels;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class InnerPanelWithThreeTables extends JPanel {
    private Font font;
    private GridBagLayout GBlayout = new GridBagLayout();
    private GridBagConstraints GBconstraints = new GridBagConstraints();

    private JScrollPane jScrollPane_table1;

    InnerPanelWithThreeTables(JTable table1, JTable tableTotal, JTable tableCourseGroups, double thirdTableWeightY, int JScrollBarValue, int verticalScrollBarMaximum) {
        setBackground(new Color(240, 240, 240));
        font = new Font("Franklin Gothic Book", Font.PLAIN, 14);
        setLayout(GBlayout);

        GBconstraints.gridwidth = GridBagConstraints.REMAINDER;
        GBconstraints.fill = GridBagConstraints.BOTH;
        GBconstraints.weightx = 0.3;
        GBconstraints.weighty = 1;
        GBconstraints.insets = new Insets(0, 0, 0, 0);

        jScrollPane_table1 = new JScrollPane(table1);
        jScrollPane_table1.setBackground(Color.white);
        jScrollPane_table1.setFont(font);
        GBlayout.setConstraints(jScrollPane_table1, GBconstraints);
        jScrollPane_table1.getVerticalScrollBar().setMaximum(verticalScrollBarMaximum);
        jScrollPane_table1.getVerticalScrollBar().setValue(JScrollBarValue);
        add(jScrollPane_table1);


        GBconstraints.weighty = 0.22;

        JScrollPane jsp2 = new JScrollPane(tableTotal);
        jsp2.setBackground(Color.white);
        jsp2.setFont(font);
        GBlayout.setConstraints(jsp2, GBconstraints);
        add(jsp2);

        GBconstraints.weighty = thirdTableWeightY;
        GBconstraints.insets = new Insets(20, 0, 0, 0);

        Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        TitledBorder titled = BorderFactory.createTitledBorder(loweredetched, "Коментар (Курс, Шифр груп)");
        titled.setTitleFont(font);

        JScrollPane jsp3 = new JScrollPane(tableCourseGroups);
        jsp3.setBackground(Color.white);
        jsp3.setFont(font);
        jsp3.setBorder(titled);
        GBlayout.setConstraints(jsp3, GBconstraints);
        add(jsp3);


        setVisible(true);
    }
    public JScrollPane getJScrollPane_table1() {
        return jScrollPane_table1;
    }
}
