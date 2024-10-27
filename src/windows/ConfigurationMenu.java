package windows;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;

public class ConfigurationMenu implements ActionListener {
    private JMenu menuConfiguration;
    private JMenuItem menuColorChange, menuPatternChange, menuSpeedControl;
    private AnimatedShapePanel animatedPanel;
    private mainWindow mainWindow;

    public ConfigurationMenu(AnimatedShapePanel animatedPanel, mainWindow mainWindow) {
        this.animatedPanel = animatedPanel;
        this.mainWindow = mainWindow;
        menuConfiguration = new JMenu("Configuration");

        menuColorChange = new JMenuItem("Change Color");
        menuColorChange.addActionListener(this);
        menuConfiguration.add(menuColorChange);

        menuPatternChange = new JMenuItem("Change Pattern");
        menuPatternChange.addActionListener(this);
        menuConfiguration.add(menuPatternChange);

        menuSpeedControl = new JMenuItem("Change Speed");
        menuSpeedControl.addActionListener(this);
        menuConfiguration.add(menuSpeedControl);
    }

    public JMenu getMenu() {
        return menuConfiguration;
    }

    private void changeShapeColor() {
        Color newColor = JColorChooser.showDialog(null, "Choose Shape Color", animatedPanel.getShapeColor());
        if (newColor != null) {
            animatedPanel.setShapeColor(newColor);
            mainWindow.updateStatus("Color changed");
        } else {
            mainWindow.updateStatus("Color change canceled");
        }
    }

    private void changeMovementPattern() {
        String[] options = {"Straight", "Diagonal"};
        String pattern = (String) JOptionPane.showInputDialog(null, "Choose Movement Pattern:", "Pattern Settings",
                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        if (pattern != null) {
            animatedPanel.setMovementPattern(pattern);
            mainWindow.updateStatus("Movement pattern changed to " + pattern);
        } else {
            mainWindow.updateStatus("Pattern change canceled");
        }
    }

    private void changeSpeed() {
        JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, 0, 3, animatedPanel.getSpeedLevel());
        speedSlider.setMajorTickSpacing(1);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);

        speedSlider.setLabelTable(speedSlider.createStandardLabels(1));

        int result = JOptionPane.showConfirmDialog(null, speedSlider, "Select Speed", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            int speedLevel = speedSlider.getValue();
            animatedPanel.setSpeedLevel(speedLevel);
            mainWindow.updateStatus("Speed changed to " + speedLevel + "x");
        } else {
            mainWindow.updateStatus("Speed change canceled");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuColorChange) {
            changeShapeColor();
        } else if (e.getSource() == menuPatternChange) {
            changeMovementPattern();
        } else if (e.getSource() == menuSpeedControl) {
            changeSpeed();
        }
    }
}
