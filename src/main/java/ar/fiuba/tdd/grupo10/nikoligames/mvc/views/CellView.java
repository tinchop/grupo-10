package ar.fiuba.tdd.grupo10.nikoligames.mvc.views;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class CellView extends JLabel implements TableCellRenderer {

    public CellView() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        String text = value.toString();
        setText(text);
        setEnabled(false);
        setHorizontalAlignment(SwingConstants.CENTER);
        setBackground(Color.lightGray);
        setForeground(Color.black);

        return this;
    }
}
