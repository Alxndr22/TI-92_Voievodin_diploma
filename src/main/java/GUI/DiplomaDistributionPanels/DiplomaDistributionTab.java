package GUI.DiplomaDistributionPanels;

import GUI.FirstLevel.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class DiplomaDistributionTab extends JPanel {
    private Font font;
    private GridBagLayout GBlayout = new GridBagLayout();
    protected GridBagConstraints GBconstraints = new GridBagConstraints();
    protected JButton semester1Button, semester2Button;
    protected JPanel cards = new JPanel();
    protected CardLayout cardLO;

    public DiplomaDistributionTab(MainFrame parent) {
        setBackground(Color.white);
        setLayout(GBlayout);
        font = new Font("Franklin Gothic Book", Font.PLAIN, 14);

        GBconstraints.gridx = 0;
        GBconstraints.gridy = 0;
        GBconstraints.weightx = .5;
        GBconstraints.weighty = .05;
        GBconstraints.insets = new Insets(20, 200, 20, 30);
        GBconstraints.gridwidth = GridBagConstraints.RELATIVE;
        GBconstraints.fill = GridBagConstraints.HORIZONTAL;

        cardLO = new CardLayout();

        cards.setLayout(cardLO);

        semester1Button = new JButton("I семестр");
        semester2Button = new JButton("II семестр");
        semester1Button.setBackground(new Color(0x8081BECE, true));
        semester2Button.setBackground(new Color(220,220,220));
        semester1Button.setFont(font);
        semester2Button.setFont(font);
        semester1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                semester1Button.setBackground(new Color(0x8081BECE, true));
                semester2Button.setBackground(new Color(220,220,220));
                cardLO.show(cards, "semester1");
            }
        });
        semester2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                semester1Button.setBackground(new Color(220,220,220));
                semester2Button.setBackground(new Color(0x8081BECE, true));
                cardLO.show(cards, "semester2");
            }
        });
        add(semester1Button, GBconstraints);
        GBconstraints.gridx = 1;
        GBconstraints.insets = new Insets(20, 30, 20, 200);
        add(semester2Button, GBconstraints);

        // for 'add(cards, GBconstraints);'
        GBconstraints.insets = new Insets(0, 0, 0, 0);
        GBconstraints.gridwidth = GridBagConstraints.REMAINDER;
        GBconstraints.fill = GridBagConstraints.BOTH;
        GBconstraints.gridx = 0;
        GBconstraints.gridy = 1;
        GBconstraints.weightx = 1;
        GBconstraints.weighty = .9;
    }
}
