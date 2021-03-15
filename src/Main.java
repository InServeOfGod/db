import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class Main extends WidgetSetting {
    public static void main(String[] args) {
        // set data column for all

        ColumnData columnData = new ColumnData();

        // set properties of GUI

        final int[] size = {1250, 700};

        // set widgets

        final JFrame frame = new JFrame();
        final JMenuBar menuBar = new JMenuBar();

        final JMenu manageMenu = new JMenu();
        final JMenuItem addManageMenu = new JMenuItem();
        final JMenuItem editManageMenu = new JMenuItem();
        final JMenuItem delManageMenu = new JMenuItem();
        final JMenuItem exitManageMenu = new JMenuItem();

        final JMenu viewMenu = new JMenu();
        final JCheckBoxMenuItem fullViewMenu = new JCheckBoxMenuItem();
        final JCheckBoxMenuItem showViewMenu = new JCheckBoxMenuItem();

        final JMenu settingMenu = new JMenu();
        final JMenuItem setSettingMenu = new JMenuItem();
        final JMenuItem protocolSettingMenu = new JMenuItem();

        final JMenu helpMenu = new JMenu();
        final JMenuItem helpHelpMenu = new JMenuItem();
        final JMenuItem aboutHelpMenu = new JMenuItem();

        final JToolBar toolBar = new JToolBar();
        final JButton addBtn = new JButton();
        final JButton editBtn = new JButton();
        final JButton delBtn = new JButton();
        final JButton exitBtn = new JButton();

        final DefaultTableModel tableModel = new DefaultTableModel();
        final JTable table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        final JScrollPane scrollPane = new JScrollPane(table);

        // create listeners

        final ActionListener addListener = new ActionListener() {
            // what to do when adding new element into table

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddDialog addDialog = new AddDialog(frame, columnData, null);

                if (addDialog.getSaved()){
                    clearTable(tableModel);
                    putData(tableModel, columnData.getDirectory(), columnData);
                }
            }
        };

        final ActionListener editListener = new ActionListener() {
            // what to do when editing an element of the table

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String[] data = getSelected(table);
                int row = table.getSelectedRow();

                columnData.setId(table.getValueAt(row, 0).toString());
                AddDialog editDialog = new AddDialog(frame, columnData, data);

                if (editDialog.getSaved()){
                    clearTable(tableModel);
                    putData(tableModel, columnData.getDirectory(), columnData);
                }
            }
        };

        final ActionListener delListener = new ActionListener() {
            // what to do when deleting an element of the table

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                /*
                * get selected row..
                * and get the id through first column of selected row
                * give the id to the remove() function
                * */

                int row = table.getSelectedRow();
                String id = table.getValueAt(row, 0).toString();
                int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete which has id : " + id + " ?", frame.getTitle(), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (confirm == JOptionPane.YES_OPTION){
                    columnData.setId(id);
                    if (remove(tableModel, row, columnData)){
                        if (tableModel.getRowCount() < 0){
                            editBtn.setEnabled(false);
                            editManageMenu.setEnabled(false);
                            delBtn.setEnabled(false);
                            delManageMenu.setEnabled(false);
                        }

                        JOptionPane.showMessageDialog(frame, "Deleted successfully", frame.getTitle(), JOptionPane.INFORMATION_MESSAGE);
                    }

                    else {
                        JOptionPane.showMessageDialog(frame, "Deleting has been failed", frame.getTitle(), JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        };

        final WindowListener windowListener = new WindowAdapter() {
            // what to do when user clicks on X of window

            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?", frame.getTitle(), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (confirm == JOptionPane.YES_OPTION) {
                    frame.dispose();
                    System.exit(0);
                }
            }
        };

        final ActionListener exitListener = new ActionListener() {
            // what to do when clicked on exit button

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?", frame.getTitle(), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (confirm == JOptionPane.YES_OPTION) {
                    frame.dispose();
                    System.exit(0);
                }
            }
        };

        final ActionListener showListener = new ActionListener() {
            // what to do when user toggles then "show/hide menu" checkbox

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                toggleMenu(menuBar);
            }
        };

        final ActionListener fullListener = new ActionListener() {
            // what to do when user toggles then "full screen" checkbox

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                fullScreen(frame);
            }
        };

        final ActionListener helpListener = new ActionListener() {
            // what to do when user clicks help menu

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(frame, "you can mail to inserveofgod@gmail.com", frame.getTitle(), JOptionPane.INFORMATION_MESSAGE);
            }
        };

        final ActionListener aboutListener = new ActionListener() {
            // what to do when user clicks about menu

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(frame, "This program is made by <InServeOfGod/>", frame.getTitle(), JOptionPane.INFORMATION_MESSAGE);
            }
        };

        // set frame of GUI

        frame.setTitle("Database");
        frame.setSize(size[0], size[1]);
        frame.setLayout(new BorderLayout());

        // set toolbar of GUI

        addBtn.setText("ADD DATA");
        addBtn.addActionListener(addListener);

        editBtn.setText("EDIT DATA");
        editBtn.addActionListener(editListener);
        editBtn.setEnabled(false);

        delBtn.setText("DELETE DATA");
        delBtn.addActionListener(delListener);
        delBtn.setEnabled(false);

        exitBtn.setText("EXIT PROGRAM");
        exitBtn.addActionListener(exitListener);

        // add buttons into toolbar

        toolBar.add(addBtn);
        toolBar.add(editBtn);
        toolBar.add(delBtn);
        toolBar.add(exitBtn);
        frame.add(toolBar, BorderLayout.PAGE_START);

        // set table of GUI

        setTable(table, tableModel, scrollPane);
        setColumnText(tableModel, columnData);
        setColumnWidth(table, 200);

        frame.add(scrollPane, BorderLayout.CENTER);

        // set menu of GUI

        // manage menu
        manageMenu.setText("Manage");

        addManageMenu.setText("Add Data");
        addManageMenu.addActionListener(addListener);

        editManageMenu.setText("Edit Data");
        editManageMenu.addActionListener(editListener);
        editManageMenu.setEnabled(false);

        delManageMenu.setText("Delete Data");
        delManageMenu.addActionListener(delListener);
        delManageMenu.setEnabled(false);

        exitManageMenu.setText("Exit");
        exitManageMenu.addActionListener(exitListener);


        // view menu
        viewMenu.setText("View");

        fullViewMenu.setText("Full Screen");
        fullViewMenu.addActionListener(fullListener);

        showViewMenu.setText("Show/Hide Menu");
        showViewMenu.addActionListener(showListener);
        showViewMenu.setState(true);


        // setting menu
        settingMenu.setText("Setting");

        setSettingMenu.setText("Program Settings");
        protocolSettingMenu.setText("Security Protocol");


        // help menu
        helpMenu.setText("Help");

        helpHelpMenu.setText("Help");
        helpHelpMenu.addActionListener(helpListener);

        aboutHelpMenu.setText("About");
        aboutHelpMenu.addActionListener(aboutListener);


        // add menu items into menus

        manageMenu.add(addManageMenu);
        manageMenu.add(editManageMenu);
        manageMenu.add(delManageMenu);
        manageMenu.add(exitManageMenu);

        viewMenu.add(fullViewMenu);
        viewMenu.add(showViewMenu);

        settingMenu.add(setSettingMenu);
        settingMenu.add(protocolSettingMenu);

        helpMenu.add(helpHelpMenu);
        helpMenu.add(aboutHelpMenu);

        menuBar.add(manageMenu);
        menuBar.add(viewMenu);
        menuBar.add(settingMenu);
        menuBar.add(helpMenu);

        // read data and put it on to table

        setDir(columnData.getDirectory());
        clearTable(tableModel);
        putData(tableModel, columnData.getDirectory(), columnData);

        // set properties

        // run mouse and keyboard listener

        // banned actions before selecting any row

        callListeners(table, editBtn);
        callListeners(table, editManageMenu);
        callListeners(table, delBtn);
        callListeners(table, delManageMenu);

        // end of GUI

        frame.addWindowListener(windowListener);
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }
}
