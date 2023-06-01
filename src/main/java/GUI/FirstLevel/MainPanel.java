package GUI.FirstLevel;

import GUI.SecondLevel.PanelDiplomaDistribution;
import GUI.SecondLevel.PanelExport;
import GUI.SecondLevel.PanelSettings;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class MainPanel extends JPanel {
    private Font font;
    private GridBagLayout GBlayout = new GridBagLayout();
    private GridBagConstraints GBconstraints = new GridBagConstraints();

    int previousIndex;

    public MainPanel(MainFrame parent, int JTabbedPaneOpeningIndex_outer, int JTabbedPaneOpeningIndex_inner, boolean[] openSecondSemester, int JScrollBarValue, int verticalScrollBarMaximum) {
        setBackground(Color.white);
        setLayout(GBlayout);
        font = new Font("Franklin Gothic Book", Font.PLAIN, 14);

        GBconstraints.fill = GridBagConstraints.BOTH;
        GBconstraints.weighty = 1;
        GBconstraints.weightx = 1;

        JTabbedPane jtp = new JTabbedPane();
        jtp.addTab("Вхідні дані", new PanelSettings(parent, JScrollBarValue, verticalScrollBarMaximum));
        jtp.addTab("Розподіл неаудиторного навантаження", new PanelDiplomaDistribution(parent, JTabbedPaneOpeningIndex_inner, openSecondSemester, JScrollBarValue, verticalScrollBarMaximum));
        jtp.addTab("Експорт результатів", new PanelExport(parent));
        jtp.setSelectedIndex(JTabbedPaneOpeningIndex_outer);

        previousIndex = JTabbedPaneOpeningIndex_outer;
        jtp.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (previousIndex == 0) {       // update teacher data after edit/delete on 'PanelSettings'
                    parent.updateThisPanel(jtp.getSelectedIndex(), 0, new boolean[] {false, false, false, false, false, false, false, false, false}, 0, 100);
                }
                previousIndex = jtp.getSelectedIndex();
            }
        });

        ImageIcon icon_import = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("database-edit.png")).getImage());
        ImageIcon icon_edit = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("edit.png")).getImage());
        ImageIcon icon_export = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("xls-export.png")).getImage());

        jtp.setIconAt(0, icon_import);
        jtp.setIconAt(1, icon_edit);
        jtp.setIconAt(2, icon_export);

        add(jtp, GBconstraints);
    }
}
