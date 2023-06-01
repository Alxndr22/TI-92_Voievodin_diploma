package GUI.Dialogs;


import javax.swing.*;
import java.awt.*;

public class DialogWait extends JDialog {
    private Font font;

    private JLabel labIcon, labInfo;

    GridBagLayout GBlayout = new GridBagLayout();
    GridBagConstraints GBconstraints = new GridBagConstraints();
    public DialogWait() {
        super((JDialog) null);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width-500)/2, (screenSize.height-150)/2);
        setTitle("Статус виконання");
        setSize(500, 150);
        setLayout(GBlayout);
        setBackground(Color.WHITE);
        font = new Font("Franklin Gothic Book", Font.PLAIN, 16);

        ImageIcon icon_wait = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("wait.png")).getImage());
        labIcon = new JLabel(icon_wait);
        GBconstraints.insets = new Insets(0, 0, 0, 10);
        add(labIcon, GBconstraints);

        labInfo = new JLabel("Будь-ласка, зачекайте. Виконується підрахунок.");
        labInfo.setFont(font);
        GBconstraints.insets = new Insets(0, 0, 0, 0);
        add(labInfo, GBconstraints);

        setVisible(true);
    }
}
