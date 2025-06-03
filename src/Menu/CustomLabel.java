package Menu;

import javax.swing.*;
import Objects.Label;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomLabel extends MenuItem implements ActionListener {
    private Label objLabel;
    private JDialog dialog;
    private JTextField nameField;
    private JComboBox<String> shapeComboBox;
    private JComboBox<String> colorComboBox;
    private JComboBox<String> fontSizeComboBox;

    public CustomLabel(String name) {
        super(name);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        handleCustomLabel();
    }

    private void handleCustomLabel() {
        if(canvas.selectedObject != null) {
            objLabel = canvas.selectedObject.getLabel();
            createCustomLabelDialog();
        } 
        else {
            showNoObjectSelectedError();
        }
    }

    private void createCustomLabelDialog() {
        dialog = new JDialog();
        dialog.setTitle("Custom Label Style");
        dialog.setModal(true);
        dialog.setLayout(new BorderLayout());

        JPanel formPanel = createFormPanel();
        JPanel buttonPanel = createButtonPanel();

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        formPanel.setBackground(Color.LIGHT_GRAY);

        addNameField(formPanel);
        addShapeComboBox(formPanel);
        addColorComboBox(formPanel);
        addFontSizeComboBox(formPanel);

        return formPanel;
    }

    private void addNameField(JPanel formPanel) {
        JLabel nameLabel = new JLabel("Name");
        nameField = new JTextField(objLabel.getName());
        formPanel.add(nameLabel);
        formPanel.add(nameField);
    }

    private void addShapeComboBox(JPanel formPanel) {
        JLabel shapeLabel = new JLabel("Shape");
        String[] shapes = { "Rectangle", "Oval" };
        shapeComboBox = new JComboBox<>(shapes);
        shapeComboBox.setSelectedItem(objLabel.getShape());
        formPanel.add(shapeLabel);
        formPanel.add(shapeComboBox);
    }

    private void addColorComboBox(JPanel formPanel) {
        JLabel colorLabel = new JLabel("Color");
        String[] colors = { "yellow", "red", "blue", "green", "white" };
        colorComboBox = new JComboBox<>(colors);
        colorComboBox.setSelectedItem(objLabel.getColor());
        formPanel.add(colorLabel);
        formPanel.add(colorComboBox);
    }

    private void addFontSizeComboBox(JPanel formPanel) {
        JLabel fontSizeLabel = new JLabel("FontSize");
        String[] fontSizes = { "8", "10", "12", "14", "16", "18" };
        fontSizeComboBox = new JComboBox<>(fontSizes);
        fontSizeComboBox.setSelectedItem(String.valueOf(objLabel.getFontSize()));
        formPanel.add(fontSizeLabel);
        formPanel.add(fontSizeComboBox);
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton cancelButton = createCancelButton();
        JButton okButton = createOkButton();

        buttonPanel.add(cancelButton);
        buttonPanel.add(okButton);

        return buttonPanel;
    }

    private JButton createCancelButton() {
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(event -> dialog.dispose());
        return cancelButton;
    }

    private JButton createOkButton() {
        JButton okButton = new JButton("OK");
        okButton.addActionListener(event -> {
            updateLabelProperties();
            objLabel.enable();
            canvas.repaint();
            dialog.dispose();
        });
        return okButton;
    }

    private void updateLabelProperties() {
        objLabel.setName(nameField.getText());
        objLabel.setShape((String) shapeComboBox.getSelectedItem());
        objLabel.setColor((String) colorComboBox.getSelectedItem());
        objLabel.setFontSize(Integer.parseInt((String) fontSizeComboBox.getSelectedItem()));
    }

    private void showNoObjectSelectedError() {
        JOptionPane.showMessageDialog(null, "No object selected!");
    }
}