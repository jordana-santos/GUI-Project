package windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConfigurationMenu implements ActionListener {
    private JMenu menuConfiguration;
    private JMenuItem menuColorChange;
    private JPanel mainPanel;
    private AnimatedShapePanel animatedPanel = new AnimatedShapePanel();


    public ConfigurationMenu(JPanel mainPanel) {
        this.mainPanel = mainPanel;
        menuConfiguration = new JMenu("Configuration");

        // creates the menu item to change the shape color
        menuColorChange = new JMenuItem("Change Color");
        menuColorChange.addActionListener(this);
        menuConfiguration.add(menuColorChange);
    }

    // return the menuConfiguration to be used in mainWindow
    public JMenu getMenu() {
        return menuConfiguration;
    }

    private void changeShapeColor(){
        Color newColor = JColorChooser.showDialog(null, "Choose Shape Color", animatedPanel.getShapeColor());
        if (newColor != null) {
            animatedPanel.setShapeColor(newColor);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuColorChange) {
            changeShapeColor();
        }
    }
}