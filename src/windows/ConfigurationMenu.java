package windows;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JColorChooser;

public class ConfigurationMenu implements ActionListener {
    private JMenu menuConfiguration;
    private JMenuItem menuColorChange;
    private JPanel mainPanel;

    public ConfigurationMenu(JPanel mainPanel) {
        this.mainPanel = mainPanel;

        menuConfiguration = new JMenu("Configuration");

        // creates the menu item to change the background color
        menuColorChange = new JMenuItem("Change Color");
        menuColorChange.addActionListener(this);

        menuConfiguration.add(menuColorChange);
    }

    // return the menuCoonfiguration to be used in mainWindow
    public JMenu getMenu() {
        return menuConfiguration;
    }

    private void changeBackgroundColor() {
        // the color selector page
        Color newColor = JColorChooser.showDialog(null, "Choose Background Color", mainPanel.getBackground());

        if (newColor != null) {
            mainPanel.setBackground(newColor);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuColorChange) {
            changeBackgroundColor();
        }
    }
}