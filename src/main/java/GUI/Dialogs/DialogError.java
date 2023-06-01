package GUI.Dialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogError extends JDialog {
    private Font font;
    GridBagLayout GBlayout = new GridBagLayout();
    GridBagConstraints GBconstraints = new GridBagConstraints();

    private JTextArea textArea;
    private JLabel labIcon;
    private JButton exit;

    public DialogError(String errorMessage) {
        super((JDialog) null);
        setModalityType(DEFAULT_MODALITY_TYPE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width-700)/2, (screenSize.height-400)/2);
        setTitle("Статус виконання: ПОМИЛКА");
        setSize(700, 400);
        setLayout(GBlayout);
        setBackground(Color.WHITE);
        font = new Font("Franklin Gothic Book", Font.PLAIN, 16);

        ImageIcon icon_error = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("cross1.png")).getImage().getScaledInstance(80,80, Image.SCALE_SMOOTH));
        labIcon = new JLabel(icon_error);
        GBconstraints.insets = new Insets(0, 0, 15, 0);
        GBconstraints.gridwidth = GridBagConstraints.REMAINDER;
        add(labIcon, GBconstraints);

        textArea = new JTextArea("Сталася помилка:\n\n\"" + errorMessage + "\".\n\nУсуньте помилку і спробуйте ще раз!");
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(font);
        textArea.setBackground(new Color(242, 242, 242));
        textArea.setSize(600, 100);

        GBconstraints.insets = new Insets(0, 50, 0, 50);
        add(textArea, GBconstraints);

        GBconstraints.fill = GridBagConstraints.NONE;
        GBconstraints.gridwidth = GridBagConstraints.REMAINDER;
        GBconstraints.gridy = 2;
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
