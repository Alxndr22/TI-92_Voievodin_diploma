package GUI.Dialogs;

import GUI.FirstLevel.MainFrame;
import Resources.ConnectorDB;
import Resources.Models.CreateTables;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogDBSettings extends JDialog {
    private Font font = new Font("Franklin Gothic Book", Font.PLAIN, 14);
    private GridBagLayout GBlayout = new GridBagLayout();
    private GridBagConstraints GBconstraints = new GridBagConstraints();

    private JPanel dataPanel, initializationPanel;

    private JButton initButton1, initButton2;

    public DialogDBSettings(MainFrame parent) {
        super((JDialog) null);   // dialog will show in the taskbar
        setModalityType(DEFAULT_MODALITY_TYPE);
        setTitle("Налаштування та ініціалізація бази даних");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width-600)/2, (screenSize.height-600)/2);
        setSize(600, 600);
        setLayout(GBlayout);
        setBackground(Color.WHITE);
        getContentPane().setBackground(Color.WHITE);


        GBconstraints.fill = GridBagConstraints.BOTH;
        GBconstraints.weighty = 1;
        GBconstraints.weightx = 1;
        GBconstraints.insets = new Insets(30, 25, 15, 25);


        dataPanel = new JPanel() {
            private Font font = new Font("Franklin Gothic Book", Font.PLAIN, 14);
            private GridBagLayout GBlayout = new GridBagLayout();
            private GridBagConstraints GBconstraints = new GridBagConstraints();

            private JLabel hostLabel, portLabel, userLabel, passwordLabel, databaseLabel, statusLabel;
            private JTextField hostField, portField, userField, passwordField, databaseField;

            private String[] connectionPreviousValues = {ConnectorDB.getHost(), ConnectorDB.getPort(), ConnectorDB.getUser(), ConnectorDB.getPassword(), ConnectorDB.getDatabaseName()};


            public JPanel execute() {
                setLayout(GBlayout);
                setBackground(Color.WHITE);

                GBconstraints.weighty = 0;
                GBconstraints.fill = GridBagConstraints.HORIZONTAL;
                GBconstraints.gridy = 0;

                GBconstraints.gridx = 0;
                JLabel space = new JLabel();
                add(space, GBconstraints);

                GBconstraints.gridx = 1;
                GBconstraints.weightx = 1.0;
                JLabel space1 = new JLabel();
                add(space1, GBconstraints);

                GBconstraints.gridx = 2;
                GBconstraints.weightx = 0;
                JLabel space2 = new JLabel();
                add(space2, GBconstraints);

                GBconstraints.gridx = 3;
                GBconstraints.weightx = 1.0;
                JLabel space3 = new JLabel();
                add(space3, GBconstraints);


                GBconstraints.gridy = 1;
                GBconstraints.gridx = 0;
                GBconstraints.weightx = 0;
                GBconstraints.insets = new Insets(0, 10, 20, 10);
                hostLabel = new JLabel("Host:");
                hostLabel.setFont(font);

                add(hostLabel, GBconstraints);
                GBconstraints.gridx = 1;
                GBconstraints.weightx = 1;
                hostField = new JTextField(100);
                hostField.setText(connectionPreviousValues[0]);
                add(hostField, GBconstraints);

                GBconstraints.weightx = 0;
                GBconstraints.gridx = 2;
                portLabel = new JLabel("Port:");
                portLabel.setFont(font);
                add(portLabel, GBconstraints);

                GBconstraints.gridx = 3;
                GBconstraints.weightx = 1;
                portField = new JTextField(100);
                portField.setText(connectionPreviousValues[1]);
                add(portField, GBconstraints);

                GBconstraints.gridy = 2;
                GBconstraints.gridx = 0;
                GBconstraints.weightx = 0;
                GBconstraints.insets = new Insets(0, 10, 5, 10);
                userLabel = new JLabel("User:");
                userLabel.setFont(font);

                add(userLabel, GBconstraints);

                GBconstraints.gridx = 1;
                GBconstraints.weightx = 1;
                userField = new JTextField(100);
                userField.setText(connectionPreviousValues[2]);
                add(userField, GBconstraints);

                GBconstraints.gridy = 3;
                GBconstraints.gridx = 0;
                GBconstraints.weightx = 0;
                GBconstraints.insets = new Insets(0, 10, 20, 10);
                passwordLabel = new JLabel("Password:");
                passwordLabel.setFont(font);

                add(passwordLabel, GBconstraints);

                GBconstraints.gridx = 1;
                GBconstraints.weightx = 1;
                passwordField = new JTextField(100);
                passwordField.setText(connectionPreviousValues[3]);
                add(passwordField, GBconstraints);

                GBconstraints.gridy = 4;
                GBconstraints.gridx = 0;
                GBconstraints.weightx = 0;
                databaseLabel = new JLabel("Database Name:");
                databaseLabel.setFont(font);

                add(databaseLabel, GBconstraints);

                GBconstraints.gridx = 1;
                GBconstraints.weightx = 1;
                databaseField = new JTextField(100);
                databaseField.setText(connectionPreviousValues[4]);
                add(databaseField, GBconstraints);


                GBconstraints.gridy = 5;
                GBconstraints.gridx = 0;
                GBconstraints.gridwidth = 4;
                GBconstraints.insets = new Insets(10, 10, 0, 10);
                statusLabel = new JLabel("");
                statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
                add(statusLabel, GBconstraints);


                GBconstraints.gridy = 6;
                GBconstraints.gridx = 0;
                GBconstraints.gridwidth = 4;
                GBconstraints.insets = new Insets(15, 0, 0, 0);
                add(new JPanel() {
                    private JButton apply;
                    public JPanel execute() {
                        setBackground(Color.WHITE);
                        apply = new JButton("Застосувати зміни");
                        apply.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String testConnection = ConnectorDB.testConnection(hostField.getText(), portField.getText(), userField.getText(), passwordField.getText(), databaseField.getText());

                                if (testConnection.equals("success") || testConnection.equals("database")) {
                                    System.out.println("-- Connection tested successfully --");
                                    if (!hostField.getText().equals("")) {
                                        connectionPreviousValues[0] = hostField.getText();
                                        ConnectorDB.setHost(connectionPreviousValues[0]);
                                    }
                                    if (!portField.getText().equals("")) {
                                        connectionPreviousValues[1] = portField.getText();
                                        ConnectorDB.setPort(connectionPreviousValues[1]);
                                    }
                                    if (!userField.getText().equals("")) {
                                        connectionPreviousValues[2] = userField.getText();
                                        ConnectorDB.setUser(connectionPreviousValues[2]);
                                    }
                                    if (!passwordField.getText().equals("")) {
                                        connectionPreviousValues[3] = passwordField.getText();
                                        ConnectorDB.setPassword(connectionPreviousValues[3]);
                                    }
                                    if (!databaseField.getText().equals("")) {
                                        connectionPreviousValues[4] = databaseField.getText();
                                        ConnectorDB.setDatabaseName(connectionPreviousValues[4]);
                                        initButton1.setText("Створити нову базу даних '" + connectionPreviousValues[4] + "' з необхіними таблицями");
                                        initButton2.setText("Додати необхідні таблиці до бази даних '" + connectionPreviousValues[4] + "'");
                                    }

                                    if (testConnection.equals("success")) {
                                        statusLabel.setText("Успішно підключено до БД");
                                        statusLabel.setForeground(new Color(60, 140, 50));
                                    } else {
                                        statusLabel.setText("<html>Успішно підключено до серверу, але бази даних з таким ім'ям не існує.<br>Створіть її за допомогою нижньої панелі 'Ініціалізація бази даних'</html>");
                                        statusLabel.setForeground(new Color(140, 100, 0));
                                    }
                                    parent.doDataBaseTests();   // check and update GUI

                                } else {
                                    hostField.setText(connectionPreviousValues[0]);
                                    portField.setText(connectionPreviousValues[1]);
                                    userField.setText(connectionPreviousValues[2]);
                                    passwordField.setText(connectionPreviousValues[3]);
                                    databaseField.setText(connectionPreviousValues[4]);

                                    statusLabel.setText("Не вдалося з'єднатися з БД! Повернення до попереднього стану.");
                                    statusLabel.setForeground(new Color(196, 45, 45));
                                }
                            }
                        });
                        apply.setBackground(new Color(220,220,220));
                        apply.setFont(font);

                        add(apply);
                        setVisible(true);
                        return this;
                    }
                }.execute(), GBconstraints);

                setVisible(true);
                return this;
            }
        }.execute();
        Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        TitledBorder titled = BorderFactory.createTitledBorder(loweredetched, "Підключення");
        titled.setTitleFont(font);
        dataPanel.setBorder(titled);
        add(dataPanel, GBconstraints);


        initializationPanel = new JPanel() {
            private Font font = new Font("Franklin Gothic Book", Font.PLAIN, 14);
            private GridBagLayout GBlayout = new GridBagLayout();
            private GridBagConstraints GBconstraints = new GridBagConstraints();

            private JLabel statusLabel;

            public JPanel execute() {
                setBackground(Color.WHITE);
                setLayout(GBlayout);
                GBconstraints.fill = GridBagConstraints.HORIZONTAL;
                GBconstraints.insets = new Insets(15, 0, 15, 0);
                initButton1 = new JButton("Створити нову базу даних '" + ConnectorDB.getDatabaseName() + "' з необхіними таблицями");
                initButton1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (CreateTables.create(true)) {
                            statusLabel.setText("Успішно створено базу даних і заповнено таблицями.");
                            statusLabel.setForeground(new Color(60, 140, 50));
                        } else {
                            statusLabel.setText("Сталася помилка! Можливо база даних з таким ім'ям вже існує.");
                            statusLabel.setForeground(new Color(196, 45, 45));
                        }
                    }
                });
                initButton1.setBackground(new Color(220,220,220));
                initButton1.setFont(font);
                add(initButton1, GBconstraints);

                GBconstraints.gridy = 1;
                initButton2 = new JButton("Додати необхідні таблиці до бази даних '" + ConnectorDB.getDatabaseName() + "'");
                initButton2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (CreateTables.create(false)) {
                            statusLabel.setText("Таблиці успішно додані до БД");
                            statusLabel.setForeground(new Color(60, 140, 50));
                        } else {
                            statusLabel.setText("Сталася помилка!");
                            statusLabel.setForeground(new Color(196, 45, 45));
                        }
                    }
                });
                initButton2.setBackground(new Color(220,220,220));
                initButton2.setFont(font);
                add(initButton2, GBconstraints);

                GBconstraints.gridy = 2;
                statusLabel = new JLabel("");
                statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
                add(statusLabel, GBconstraints);
                setVisible(true);
                return this;
            }
        }.execute();
        titled = BorderFactory.createTitledBorder(loweredetched, "Ініціалізація бази даних");
        titled.setTitleFont(font);
        initializationPanel.setBorder(titled);

        GBconstraints.insets = new Insets(15, 25, 30, 25);
        GBconstraints.gridy = 1;

        add(initializationPanel, GBconstraints);

        setVisible(true);
    }
}
