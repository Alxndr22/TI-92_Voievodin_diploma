package Resources;

import GUI.FirstLevel.MainFrame;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;

public class Runner {

    public static void main(String[] args) {
        FlatLightLaf.setup();
        // TabbedPane
        UIManager.put( "TabbedPane.selectedBackground", Color.white );
        UIManager.put( "TabbedPane.underlineColor", new Color(0x012E4A) );
        UIManager.put( "TabbedPane.inactiveUnderlineColor", new Color(0x012E4A) );
        UIManager.put( "TabbedPane.disabledUnderlineColor", new Color(0x8081BECE, true) );
        // Table
        UIManager.put("TableHeader.background", new Color(0xEFEFEF));
        UIManager.put("TableHeader.separatorColor", Color.lightGray);
        UIManager.put("TableHeader.bottomSeparatorColor", Color.lightGray);
        UIManager.put("Table.selectionBackground", new Color(0x8081BECE, true));
        UIManager.put("Table.selectionForeground", new Color(0x012E4A));
        UIManager.put("Table.sortIconColor", new Color(0xDBDBDB));
        UIManager.put("Table.showHorizontalLines", true);
        UIManager.put("Table.showVerticalLines", true);
        // MenuBar
        UIManager.put("MenuBar.selectionBackground", new Color(0x8081BECE, true));
        UIManager.put("MenuBar.selectionForeground", new Color(0x012E4A));
        // MenuItem
        UIManager.put("MenuItem.selectionBackground", new Color(0x8081BECE, true));
        UIManager.put("MenuItem.selectionForeground", new Color(0x012E4A));


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
    }
}
