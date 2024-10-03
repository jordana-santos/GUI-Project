package windows;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
public class mainWindow extends JFrame implements ActionListener
{
    private static final long serialVersionUID = 1L;
    //main panel
    private JPanel mainPanel;

    //status
    private JPanel statusPanel;
    private JLabel statusLabel;

    //menu bar and its items
    private JMenuBar menuBar;
    private JMenu menuFile;

    private JMenuItem menuFileItem;
    private JMenuItem menuConfiguration;
    private JMenuItem menuHelp;
    private JMenuItem menuItemExit;
    private ConfigurationMenu configMenu;

    //calling functions when a menu item is pressed
    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        //open the file window
        if (actionEvent.getSource() == this.menuFileItem)
        {
            menuFile();
        }
        //open the configuration window
        if (actionEvent.getSource() == this.menuConfiguration)
        {
            menuConfiguration();
        }
        //open the help menu
        if (actionEvent.getSource() == this.menuHelp)
        {
            menuHelp();
        }
        //exit the application
        if (actionEvent.getSource() == this.menuItemExit)
        {
            exitSystem();
        }
    }

    //called methods by actionPerformed() function
    void exitSystem()
    {
        System.exit(0);
    }

    private void menuFile()
    {

        setStatus("File menu selected");
    }

    private void menuConfiguration()
    {

        setStatus("Configuration menu selected");
    }

    private void menuHelp()
    {

        setStatus("Help menu selected");
    }

    void setStatus(String message)
    {
        statusLabel.setText(message);
    }

    //windows listener (to exit the application consistently)
    private void setupWindowsListener()
    {
        this.addWindowListener(new java.awt.event.WindowAdapter()
        {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent)
            {
                exitSystem();
            }
        });
    }

    //start the window
    void start() throws HeadlessException
    {
        //width x height
        this.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.4), (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.3));
        this.setLocationRelativeTo(null); //where the window will appear for the first time
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(2, 2));
        this.setupWindowsListener();

        //configuring the main panel
        this.mainPanel = new JPanel();
        this.mainPanel.setBackground(Color.decode("#222425"));
        this.add(mainPanel, BorderLayout.CENTER);

        //configuring the menu bar and its items
        menuFile = new JMenu("File");
        menuFile.setForeground(Color.decode("#b2bdb1"));
        this.menuFile.setBackground(Color.decode("#455644"));
        this.menuFile.setBorder(null);

        //creating the menu options
        menuFileItem = new JMenuItem("TextFile"); //TextFile
        menuFileItem.setBackground(Color.decode("#61835d"));
        menuFileItem.setForeground(Color.decode("#c2d8c0"));
        this.menuFileItem.setBorder(null);
        menuFileItem.addActionListener(this);

        menuConfiguration = new JMenuItem("Configuration"); //Configuration
        menuConfiguration.setBackground(Color.decode("#61835d"));
        menuConfiguration.setForeground(Color.decode("#c2d8c0"));
        this.menuConfiguration.setBorder(null);
        menuConfiguration.addActionListener(this);

        menuHelp = new JMenuItem("Help"); //Help
        menuHelp.setBackground(Color.decode("#61835d"));
        menuHelp.setForeground(Color.decode("#c2d8c0"));
        this.menuHelp.setBorder(null);
        menuHelp.addActionListener(this);

        menuItemExit = new JMenuItem("Exit"); //Exit
        menuItemExit.setBackground(Color.decode("#8ba372"));
        menuItemExit.setForeground(Color.decode("#c2d8c0"));
        this.menuItemExit.setBorder(null);
        menuItemExit.addActionListener(this);

        menuFile.add(menuFileItem);
        menuFile.add(menuConfiguration);
        menuFile.add(menuHelp);
        menuFile.add(menuItemExit);

        menuBar = new JMenuBar();
        this.menuBar.setBorder(null);
        menuBar.add(menuFile);
        this.menuBar.setBackground(Color.decode("#455644"));
        this.setJMenuBar(menuBar);

        //add the configuration menu
        configMenu = new ConfigurationMenu(mainPanel);
        menuBar.add(configMenu.getMenu());

        //configuring the status panel
        this.statusPanel = new JPanel();
        this.statusLabel = new JLabel();
        this.statusPanel.add(statusLabel);
        this.statusPanel.setBackground(Color.decode("#313e3c"));
        this.statusLabel.setForeground(Color.decode("#6d7c7b"));
        this.statusPanel.setBorder(null);
        this.add(statusPanel, BorderLayout.SOUTH);
        this.setStatus(this.getClass().getCanonicalName());

        this.setVisible(true);

        //Color.decode("#42D8DB")

        //cinza escuro: #222425
        //azul escuro: #3d4947
        //verde escuro: #455644
    }
}
