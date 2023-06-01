package GUI.Dialogs;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogDeleteWarning extends JDialog {
    private Font font = new Font("Franklin Gothic Book",Font.PLAIN, 16);
    GridBagLayout GBlayout = new GridBagLayout();
    GridBagConstraints GBconstraints = new GridBagConstraints();

    private boolean yesAnswer = false;
    private JTextPane jtp;
    private JLabel labIcon;
    private JButton yesButton, noButton;

    public boolean isYesAnswer() {
        return yesAnswer;
    }

    public DialogDeleteWarning() {
        super((JDialog) null);
        setModalityType(DEFAULT_MODALITY_TYPE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width-600)/2, (screenSize.height-300)/2);
        setTitle("Попередження");
        setSize(600, 300);
        setLayout(GBlayout);
        setBackground(Color.WHITE);

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
        jtp.setText("Бажаєте видалити з бази даних усіх викладачів?\nУсі пов'язані дані будуть також видалені.");
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

        yesButton = new JButton("Так. Видалити");
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

        noButton = new JButton("Ні. Залишити");
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
