package GUI.FirstLevel;

import GUI.Dialogs.DialogDBSettings;
import Resources.ConnectorDB;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainFrame extends JFrame {
    Font font = new Font("Franklin Gothic Book", Font.PLAIN, 14);
    private GridBagLayout GBlayout = new GridBagLayout();
    private GridBagConstraints GBconstraints = new GridBagConstraints();

    JTextArea errorArea;
    JButton refreshButton;

    MainFrame thisFrame = this;
    MainPanel mainPanel = null;
    DialogDBSettings dialogDBSettings = null;

    public MainFrame() {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setFont(font);
        setSize(1250, 1000);
        setTitle("Розподіл неаудиторного пед.навантаження та формування плану навантаження кафедри");
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("app2.png")).getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation((screenSize.width-1250)/2, (screenSize.height-1000)/2);
        getContentPane().setBackground(Color.WHITE);
        setLayout(GBlayout);

        ConnectorDB.configuringConnectionData();        // configure DB on launch

        JMenuBar mBar = new JMenuBar();
        JMenu menu = new JMenu("Налаштування БД");
        menu.setFont(font);
        menu.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                dialogDBSettings = new DialogDBSettings(thisFrame);
                menu.setSelected(false);
            }

            @Override
            public void menuDeselected(MenuEvent e) {
                dialogDBSettings.dispose();
            }

            @Override
            public void menuCanceled(MenuEvent e) { }
        });
        mBar.add(menu);
        setJMenuBar(mBar);


        doDataBaseTests();


        setVisible(true);
    }

    public void addMainPanel(int JTabbedPaneOpeningIndex_outer, int JTabbedPaneOpeningIndex_inner, boolean[] openSecondSemester, int JScrollBarValue, int verticalScrollBarMaximum) {
        GBconstraints.gridy = 0;
        GBconstraints.ipady = 0;
        GBconstraints.ipadx = 0;
        GBconstraints.insets = new Insets(0, 0, 0, 0);
        GBconstraints.weightx = 1;
        GBconstraints.weighty = 1;
        GBconstraints.fill = GridBagConstraints.BOTH;
        mainPanel = new MainPanel(this, JTabbedPaneOpeningIndex_outer, JTabbedPaneOpeningIndex_inner, openSecondSemester, JScrollBarValue, verticalScrollBarMaximum);
        add(mainPanel, GBconstraints);
    }
    public void addErrorPage(String labelText) {
        if (mainPanel != null)
            remove(mainPanel);
        if (errorArea != null)
            remove(errorArea);
        if (refreshButton != null)
            remove(refreshButton);
        setVisible(false);

        GBconstraints = new GridBagConstraints();
        GBconstraints.gridy = 0;
        GBconstraints.insets = new Insets(0, 0, 0, 0);
        errorArea = new JTextArea(labelText);
        errorArea.setFont(font);
        errorArea.setForeground(new Color(120, 45,45));
        add(errorArea, GBconstraints);

        GBconstraints.gridy = 1;
        GBconstraints.ipady = 20;
        GBconstraints.ipadx = 20;
        GBconstraints.insets = new Insets(20, 0, 0, 0);
        refreshButton = new JButton("Оновити графічний інтерфейс");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ConnectorDB.testConnection().equals("success") && ConnectorDB.testTablesExistence()) {
                    remove(errorArea);
                    remove(refreshButton);
                    addMainPanel(0, 0, new boolean[] {false, false, false, false, false, false, false, false}, 0, 100);
                    thisFrame.setVisible(false);
                    thisFrame.setVisible(true);
                } else {
                    remove(errorArea);
                    remove(refreshButton);
                    thisFrame.setVisible(false);
                    thisFrame.setVisible(true);
                    doDataBaseTests();
                }
            }
        });
        refreshButton.setBackground(new Color(220,220,220));
        add(refreshButton, GBconstraints);
        thisFrame.setVisible(true);
    }
    public void doDataBaseTests() {
        String connectionStatus = ConnectorDB.testConnection();
        if (connectionStatus.equals("success")) {
            if (ConnectorDB.testDataBaseExistence()) {
                if (ConnectorDB.testTablesExistence()) {
                    if (mainPanel != null)
                        remove(mainPanel);
                    if (errorArea != null)
                        remove(errorArea);
                    if (refreshButton != null)
                        remove(refreshButton);
                    addMainPanel(0, 0, new boolean[] {false, false, false, false, false, false, false, false}, 0, 100);
                    setVisible(false);
                    setVisible(true);
                } else {
                    System.out.println(">> Error: tables in db don't exist");
                    addErrorPage("Помилка. Неправильно сконфігурована база даних! Вибрана база даних немає необхідних таблиц для роботи застосунку. Варіанти вирішення проблеми:\n\n     1. Створіть нову базу даних з необхідними таблицями (перша кнопка в меню \"Налаштування БД/Ініціалізація бази даних\")\n\n     2. Додайте необхідні таблиці до цієї бази даних (друга кнопка в меню \"Налаштування БД/Ініціалізація бази даних\")\n\n     3. Перевірте введені дані для підключення до бази даних (в меню \"Налаштування БД/Підключення\")\n\n\nУсуньте помилку і оновіть графічний інтерфейс кнопкою знизу:");
                }
            } else {
                System.out.println(">> Error: db doesn't exist1");
                addErrorPage("Помилка. Бази даних з таким ім'ям не існує!\n\n\n     1.Створіть її за допомогою нижньої панелі 'Ініціалізація бази даних' в меню \"Налаштування БД\"\n\n     2.Усуньте помилку і оновіть графічний інтерфейс кнопкою знизу:");
            }
        } else if (connectionStatus.equals("access")) {
            System.out.println(">> Error during connection");
            addErrorPage("Помилка. Не вдалося з'єднатися з БД!\n\n\n     1.Перевірте введені дані (host, port, user, password) для підключення до бази даних (в меню \"Налаштування БД/Підключення\")\n\n     2.Усуньте помилку і оновіть графічний інтерфейс кнопкою знизу:");
        } else {
            System.out.println(">> Error: db doesn't exist2");
            addErrorPage("Помилка. Бази даних з таким ім'ям не існує!\n\n\n     1.Створіть її за допомогою нижньої панелі 'Ініціалізація бази даних' в меню \"Налаштування БД\"\n\n     2.Усуньте помилку і оновіть графічний інтерфейс кнопкою знизу:");
        }
    }

    public void updateThisPanel(int JTabbedPaneOpeningIndex_outer, int JTabbedPaneOpeningIndex_inner, boolean[] openSecondSemester, int JScrollBarValue, int verticalScrollBarMaximum) {
        mainPanel.setVisible(false);
        addMainPanel(JTabbedPaneOpeningIndex_outer, JTabbedPaneOpeningIndex_inner, openSecondSemester, JScrollBarValue, verticalScrollBarMaximum);
        mainPanel.setVisible(true);
    }
}
