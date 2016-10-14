package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;

public class CellView extends JTextField implements TableCellRenderer {

    public CellView() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setCustomRender(value);
        return this;
    }

    protected void setCustomRender(Object value) {
        setHorizontalAlignment(SwingConstants.CENTER);
        setForeground(Color.black);
    }
}
