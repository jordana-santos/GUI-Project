package windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class mainWindow extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextArea textArea;
    private AnimatedShapePanel animatedPanel;
    private JMenuBar menuBar;
    private JMenu menuFile;
    private JMenuItem menuOpenFile, menuCloseFile, menuItemExit;
    private ConfigurationMenu configurationMenu;
    private File currentFile;
    private JLabel statusLabel;

    public mainWindow() {
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(2, 2));

        setupUI();
        setVisible(true);
    }

    private void setupUI() {
        animatedPanel = new AnimatedShapePanel();
        animatedPanel.setPreferredSize(new Dimension(800, 300));

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, animatedPanel, scrollPane);
        splitPane.setDividerLocation(300);

        this.add(splitPane, BorderLayout.CENTER);

        menuBar = new JMenuBar();

        menuFile = new JMenu("File");
        menuFile.setForeground(Color.BLACK);

        menuOpenFile = new JMenuItem("Open File");
        menuCloseFile = new JMenuItem("Close File");
        menuItemExit = new JMenuItem("Exit");

        menuOpenFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });

        menuCloseFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeFile();
            }
        });

        menuItemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStatus("Exit selected");
                System.exit(0);
            }
        });

        menuFile.add(menuOpenFile);
        menuFile.add(menuCloseFile);
        menuFile.addSeparator();
        menuFile.add(menuItemExit);

        menuBar.add(menuFile);

        configurationMenu = new ConfigurationMenu(animatedPanel, this);
        menuBar.add(configurationMenu.getMenu());

        this.setJMenuBar(menuBar);

        JPanel statusPanel = new JPanel();
        statusLabel = new JLabel("Help menu selected");
        statusPanel.add(statusLabel);
        statusPanel.setBackground(Color.decode("#313e3c"));
        statusLabel.setForeground(Color.decode("#6d7c7b"));
        this.add(statusPanel, BorderLayout.SOUTH);
    }


    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(currentFile))) {
                textArea.setText("");
                String line;
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }
                updateStatus("File opened: " + currentFile.getName());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                updateStatus("Error reading file");
            }
        } else {
            updateStatus("Open file canceled");
        }
    }


    private void closeFile() {
        textArea.setText("");
        currentFile = null;
        updateStatus("File closed");
    }

    public void updateStatus(String message) {
        statusLabel.setText(message);
    }

    public void start() {
    }
}
