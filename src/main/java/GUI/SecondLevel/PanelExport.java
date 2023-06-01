package GUI.SecondLevel;

import GUI.ExportSubPanels.SubPanelFillPlan;
import GUI.ExportSubPanels.SubPanelFillReportDiplomaStudents;
import GUI.ExportSubPanels.SubPanelFillTeachersSecondPage;
import GUI.FirstLevel.MainFrame;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class PanelExport extends JPanel {
    private Font font;
    private GridBagLayout GBlayout = new GridBagLayout();
    private GridBagConstraints GBconstraints = new GridBagConstraints();

    public PanelExport(MainFrame parent) {
        setBackground(Color.white);
        setLayout(GBlayout);
        font = new Font("Franklin Gothic Book", Font.PLAIN, 14);


        GBconstraints.insets = new Insets(50, 150, 25, 150);
        GBconstraints.weighty = 0;
        GBconstraints.weightx = 1;
        GBconstraints.gridwidth = GridBagConstraints.REMAINDER;
        GBconstraints.fill = GridBagConstraints.HORIZONTAL;


        Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        TitledBorder titled = BorderFactory.createTitledBorder(loweredetched, "Друга сторінка викладачів");
        titled.setTitleFont(font);

        SubPanelFillTeachersSecondPage fillSecondPage = new SubPanelFillTeachersSecondPage();
        fillSecondPage.setBorder(titled);
        add(fillSecondPage, GBconstraints);


        GBconstraints.insets = new Insets(25, 150, 25, 150);

        SubPanelFillReportDiplomaStudents fillDipl = new SubPanelFillReportDiplomaStudents();
        titled = BorderFactory.createTitledBorder(loweredetched, "Звіт \"Список викладачів з кількістю дипломників\"");
        titled.setTitleFont(font);
        fillDipl.setBorder(titled);
        add(fillDipl, GBconstraints);


        GBconstraints.insets = new Insets(25, 150, 50, 150);

        SubPanelFillPlan fillPlan = new SubPanelFillPlan();
        titled = BorderFactory.createTitledBorder(loweredetched, "ПЛАН навчального навантаження");
        fillPlan.setBorder(titled);
        add(fillPlan, GBconstraints);

    }
}
