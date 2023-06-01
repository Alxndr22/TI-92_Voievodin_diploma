package GUI.Utils;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;
import java.awt.Color;

public class RedGreenAlertSystem extends DefaultTableCellRenderer {
    private JTable selectedTable;

    public RedGreenAlertSystem(JTable table) {
        this.selectedTable = table;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        int value_row0 = Integer.parseInt(selectedTable.getValueAt( 0, column).toString());
        int value_row1 = Integer.parseInt(selectedTable.getValueAt(1, column).toString());
        if (value_row0 < value_row1 && row == 0)
            c.setForeground(Color.RED);
        else if (value_row0 >= value_row1 && row == 0)
            c.setForeground(Color.green.darker());
        else
            c.setForeground(Color.BLACK);

        return this;
    }
}
