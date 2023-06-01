package GUI.Dialogs;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogWarning extends JDialog {
    private Font font;
    GridBagLayout GBlayout = new GridBagLayout();
    GridBagConstraints GBconstraints = new GridBagConstraints();

    private boolean yesAnswer;
    private JTextPane jtp;
    private JLabel labIcon;
    private JButton yesButton, noButton;

    public boolean isYesAnswer() {
        return yesAnswer;
    }

    public DialogWarning() {
        super((JDialog) null);
        setModalityType(DEFAULT_MODALITY_TYPE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width-700)/2, (screenSize.height-300)/2);
        setTitle("Попередження");
        setSize(700, 300);
        setLayout(GBlayout);
        setBackground(Color.WHITE);
        font = new Font("Franklin Gothic Book", Font.PLAIN, 16);

        ImageIcon icon_warning = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("warning.png")).getImage());
        labIcon = new JLabel(icon_warning);
        GBconstraints.insets = new Insets(0, 0, 15, 0);
        GBconstraints.gridwidth = GridBagConstraints.REMAINDER;
        labIcon.setBackground(Color.pink);
        add(labIcon, GBconstraints);


        jtp = new JTextPane();
        StyledDocument doc = jtp.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        jtp.setText("Файл з такою назвою вже існує!\n\nБажаєте ПЕРЕЗАПИСАТИ його?");
        jtp.setEditable(false);
        jtp.setFont(font);


        GBconstraints.insets = new Insets(0, 50, 0, 50);
        add(jtp, GBconstraints);

        GBconstraints.weightx = 0.5;
        GBconstraints.fill = GridBagConstraints.HORIZONTAL;
        GBconstraints.gridwidth = GridBagConstraints.RELATIVE;
        GBconstraints.gridx = 0;
        GBconstraints.gridy = 2;
        GBconstraints.insets = new Insets(50, 50, 0, 10);

        yesButton = new JButton("Так. Перезаписати існуючий");
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                yesAnswer = true;
                dispose();
            }
        });
        yesButton.setBackground(new Color(220,220,220));
        yesButton.setFont(font);
        add(yesButton, GBconstraints);


        GBconstraints.gridwidth = GridBagConstraints.REMAINDER;
        GBconstraints.gridx = 1;
        GBconstraints.gridy = 2;
        GBconstraints.insets = new Insets(50, 10, 0, 50);

        noButton = new JButton("Ні. Змінити шлях або назву файлу");
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                yesAnswer = false;
                dispose();
            }
        });
        noButton.setBackground(new Color(220,220,220));
        noButton.setFont(font);
        add(noButton, GBconstraints);

        setVisible(true);
    }
}
