package GUI.SecondLevel;

import GUI.DiplomaDistributionPanels.*;
import GUI.FirstLevel.MainFrame;

import java.awt.*;
import javax.swing.*;


public class PanelDiplomaDistribution extends JPanel {
    private Font font;
    private GridBagLayout GBlayout = new GridBagLayout();
    private GridBagConstraints GBconstraints = new GridBagConstraints();


    public PanelDiplomaDistribution(MainFrame parent, int JTabbedPaneOpeningIndex, boolean[] openSecondSemester, int JScrollBarValue, int verticalScrollBarMaximum) {
        setBackground(Color.white);
        setLayout(GBlayout);
        font = new Font("Franklin Gothic Book", Font.PLAIN, 14);

        GBconstraints.fill = GridBagConstraints.BOTH;
        GBconstraints.weighty = 1;
        GBconstraints.weightx = 1;

        JTabbedPane jtp = new JTabbedPane();
        jtp.addTab("Індивід. зан. & Керівн. практ.", new PanelIZ_KP(parent, openSecondSemester[0], JScrollBarValue, verticalScrollBarMaximum));
        jtp.addTab("Керівницт. атестац. роб", new PanelKAR(parent, openSecondSemester[1], JScrollBarValue, verticalScrollBarMaximum));
        jtp.addTab("Консультув атестац. роб", new PanelKOAR(parent, openSecondSemester[2], JScrollBarValue, verticalScrollBarMaximum));
        jtp.addTab("Консультув по випускн. екз", new PanelKOVE(parent, openSecondSemester[3], JScrollBarValue, verticalScrollBarMaximum));
        jtp.addTab("Рецензування атестац. роб", new PanelRAR(parent, openSecondSemester[4], JScrollBarValue, verticalScrollBarMaximum));
        jtp.addTab("Проведення вступних іспитів", new PanelPVI(parent, openSecondSemester[5], JScrollBarValue, verticalScrollBarMaximum));
        jtp.addTab("Робота в ЕК", new PanelRVE(parent, openSecondSemester[6], JScrollBarValue, verticalScrollBarMaximum));
        jtp.addTab("Керівництво", new PanelK(parent, openSecondSemester[7], JScrollBarValue, verticalScrollBarMaximum));
        jtp.setSelectedIndex(JTabbedPaneOpeningIndex);

        add(jtp, GBconstraints);

    }
}
