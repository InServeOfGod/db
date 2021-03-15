import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class WidgetSetting extends BaseCollector {
    public static void clearTable(DefaultTableModel model){
        int row = model.getRowCount();

        if (row > 0) {
            for (int r = 1; r <= row; r++){
                model.removeRow(r);
            }
        }
    }

    public static boolean remove(DefaultTableModel model, int r, ColumnData columnData){
        File f = new File(columnData.getDirectory() + columnData.getId());
        if (f.delete()){
            model.removeRow(r);
            return true;
        }

        return false;
    }

    public static void putData(DefaultTableModel model, String dir, ColumnData columnData){
        File f = new File(dir);
        File[] files = f.listFiles();

        if (files != null){
            for (File file : files){
                String[] content = read(file, columnData);
                model.addRow(content);
            }
        }
    }

    public static void setColumnWidth(JTable jTable, int width) {
        for (int i = 0; i < jTable.getColumnCount(); i++) {
            jTable.getColumnModel().getColumn(i).setPreferredWidth(width);
        }
    }

    public static String[] setColumnText(DefaultTableModel model, ColumnData colData) {
        String[] column = colData.getColumns();

        for (String col : column){
            model.addColumn(col);
        }

        return column;
    }

    public static void setTable(JTable jTable, DefaultTableModel model, JScrollPane jscrollPane) {
        jTable.getTableHeader().setResizingAllowed(true);
        jTable.getTableHeader().setReorderingAllowed(false);

        jscrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jscrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        jTable.setSelectionMode(0);
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }

    public static String[] getSelected(JTable jTable){
        int len = jTable.getColumnCount();
        int selected = jTable.getSelectedRow();
        String[] data = new String[len];

        // we're starting counting from 1 because we do not want to include id

        for (int i = 1; i < len; i++){
            String colData = jTable.getValueAt(selected, i).toString();

            if (colData != null){
                data[i-1] = colData;
            } else {
                data[i-1] = "";
            }
        }

        return data;
    }

    private static boolean screenStatus = false;
    private static boolean menuStatus = true;

    public static void fullScreen(JFrame jFrame){
        screenStatus = (screenStatus == false) ? true : false;

        if (screenStatus){
            jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        } else {
            jFrame.setExtendedState(JFrame.NORMAL);
        }

        jFrame.setUndecorated(true);
        jFrame.setVisible(true);
    }

    public static void toggleMenu(JMenuBar bar){
        menuStatus = (menuStatus == false) ? true : false;
        bar.setVisible(menuStatus);
    }

    public static void callListeners(JTable jTable, JButton button){
        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button.setEnabled(jTable.getSelectedRow() >= 0);
            }
        });
    }

    public static void callListeners(JTable jTable, JMenuItem item){
        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                item.setEnabled(jTable.getSelectedRow() >= 0);
            }
        });
    }
}
