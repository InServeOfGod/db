import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AddDialog extends BaseCollector {
    private static final JDialog dialog = new JDialog();

    private static final JLabel nameLabel = new JLabel("NAME : ");
    private static final JLabel surnameLabel = new JLabel("SURNAME : ");
    private static final JLabel motherLabel = new JLabel("MOTHER NAME : ");
    private static final JLabel fatherLabel = new JLabel("FATHER NAME : ");
    private static final JLabel birthLabel = new JLabel("BIRTH DATE : ");
    private static final JLabel mailLabel = new JLabel("MAIL ADDRESS : ");
    private static final JLabel mailPassLabel = new JLabel("MAIL PASSWORD: ");
    private static final JLabel pcUserLabel = new JLabel("PC USERNAME : ");
    private static final JLabel pcPassLabel = new JLabel("PC PASSWORD : ");
    private static final JLabel pcOsLabel = new JLabel("PC OS : ");
    private static final JLabel pcMacLabel = new JLabel("PC MAC : ");
    private static final JLabel routerEssidLabel = new JLabel("ROUTER ESSID : ");
    private static final JLabel routerBssidLabel = new JLabel("ROUTER BSSID : ");
    private static final JLabel routerPassLabel = new JLabel("ROUTER PASSWORD : ");
    private static final JLabel routerIpLabel = new JLabel("ROUTER EXTERNAL IP : ");
    private static final JLabel routerPinLabel = new JLabel("ROUTER PIN : ");
    private static final JLabel routerAdminLabel = new JLabel("ROUTER ADMIN USERNAME : ");
    private static final JLabel routerAdminPassLabel = new JLabel("ROUTER ADMIN PASSWORD : ");
    private static final JLabel locLabel = new JLabel("LOCATION : ");
    private static final JLabel detailLabel = new JLabel("DETAILS : ");

    private static final JTextField nameField = new JTextField();
    private static final JTextField surnameField = new JTextField();
    private static final JTextField motherField = new JTextField();
    private static final JTextField fatherField = new JTextField();
    private static final JTextField birthField = new JTextField();
    private static final JTextField mailField = new JTextField();
    private static final JTextField mailPassField = new JTextField();
    private static final JTextField pcUserField = new JTextField();
    private static final JTextField pcPassField = new JTextField();
    private static final JTextField pcOsField = new JTextField();
    private static final JTextField pcMacField = new JTextField();
    private static final JTextField routerEssidField = new JTextField();
    private static final JTextField routerBssidField = new JTextField();
    private static final JTextField routerPassField = new JTextField();
    private static final JTextField routerIpField = new JTextField();
    private static final JTextField routerPinField = new JTextField();
    private static final JTextField routerAdminField = new JTextField();
    private static final JTextField routerAdminPassField = new JTextField();
    private static final JTextField locField = new JTextField();
    private static final JTextField detailField = new JTextField();
    private static final JButton saveBtn = new JButton();
    private static final JButton cancelBtn = new JButton();

    public boolean saved;

    // jtextfield and jtextarea extends class from jtextcomponent

    private static final JTextComponent[] textFields = {
            nameField,
            surnameField,
            motherField,
            fatherField,
            birthField,
            mailField,
            mailPassField,
            pcUserField,
            pcPassField,
            pcOsField,
            pcMacField,
            routerEssidField,
            routerBssidField,
            routerPassField,
            routerIpField,
            routerPinField,
            routerAdminField,
            routerAdminPassField,
            locField,
            detailField
    };

    private static final JLabel[] labels = {
            nameLabel,
            surnameLabel,
            motherLabel,
            fatherLabel,
            birthLabel,
            mailLabel,
            mailPassLabel,
            pcUserLabel,
            pcPassLabel,
            pcOsLabel,
            pcMacLabel,
            routerEssidLabel,
            routerBssidLabel,
            routerPassLabel,
            routerIpLabel,
            routerPinLabel,
            routerAdminLabel,
            routerAdminPassLabel,
            locLabel,
            detailLabel
    };

    AddDialog(JFrame jFrame, ColumnData colData, String[] data){
        int len = labels.length;

        final ActionListener saveListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                colData.setName(nameField.getText());
                colData.setSurname(surnameField.getText());
                colData.setMother_name(motherField.getText());
                colData.setFather_name(fatherField.getText());
                colData.setBirth_date(birthField.getText());
                colData.setMail_address(mailField.getText());
                colData.setMail_password(mailPassField.getText());
                colData.setPc_username(pcUserField.getText());
                colData.setPc_password(pcPassField.getText());
                colData.setPc_os(pcOsField.getText());
                colData.setPc_mac(pcMacField.getText());
                colData.setRouter_ESSID(routerEssidField.getText());
                colData.setRouter_BSSID(routerBssidField.getText());
                colData.setRouter_password(routerPassField.getText());
                colData.setRouter_external_ip(routerIpField.getText());
                colData.setRouter_PIN(routerPinField.getText());
                colData.setRouter_admin_username(routerAdminField.getText());
                colData.setRouter_admin_password(routerAdminPassField.getText());
                colData.setLocation(locField.getText());
                colData.setDetails(detailField.getText());

                long id;
                String strId;
                File f;

                if (data == null){
                    id = produceId(".xml");
                    strId = String.valueOf(id);

                    f = new File(colData.getDirectory() + strId);
                    colData.setId(strId);
                }

                else {
                    f = new File(colData.getDirectory() + colData.getId());
                }

                saved = BaseCollector.write(f, colData);

                if (!saved){
                    JOptionPane.showMessageDialog(jFrame, "Could not save data",
                            jFrame.getTitle(), JOptionPane.WARNING_MESSAGE);
                }

                else {
                    JOptionPane.showMessageDialog(jFrame, "data has been saved",
                            jFrame.getTitle(), JOptionPane.INFORMATION_MESSAGE);
                    destroyDialog();
                }
            }
        };

        final ActionListener cancelListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                for (JTextComponent component : textFields){
                    component.setText("");
                }

                destroyDialog();
            }
        };

        dialog.setTitle(jFrame.getTitle());
        dialog.setLayout(new GridLayout(len +1, len +2));
        dialog.setSize(500, 500);
        dialog.setResizable(false);
        dialog.setVisible(true);

        for (int i = 0; i < len; i++) {
            if (data != null){
                textFields[i].setText(data[i]);
            } else {
                textFields[i].setText("");
            }

            dialog.add(labels[i], new GridLayout(i+1, i+1));
            dialog.add(textFields[i], new GridLayout(i+1, i+2));
        }

        saveBtn.setText("SAVE");
        saveBtn.addActionListener(saveListener);
        cancelBtn.setText("CANCEL");
        cancelBtn.addActionListener(cancelListener);

        dialog.add(saveBtn, new GridLayout(len +1, len +1));
        dialog.add(cancelBtn, new GridLayout(len +1, len +2));
    }

    private void destroyDialog(){
        for (JTextComponent textComponent : textFields){
            textComponent.setText("");
        }

        dialog.dispose();
    }

    public boolean getSaved(){
        return saved;
    }
}
