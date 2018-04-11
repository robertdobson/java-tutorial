import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TutorialMenu extends JPanel implements ActionListener{

    private static String branchingString = "Branching";
    private static String loopsString = "Loops";
    private static String arraysString = "Arrays";
    private static String fileIOString = "File Input/Output";
    private static String recursionString = "Recursion";
    private static String genericsString = "Generics";
    private static String buttonString = "Explore";

    private JLabel picture;

    //Create the radio buttons.
    static JRadioButton branchingButton = new JRadioButton(branchingString);
    static JRadioButton loopsButton = new JRadioButton(loopsString);
    static JRadioButton arraysButton = new JRadioButton(arraysString);
    static JRadioButton fileIOButton = new JRadioButton(fileIOString);
    static JRadioButton recursionButton = new JRadioButton(recursionString);
    static JRadioButton genericsButton = new JRadioButton(genericsString);

    public TutorialMenu() {
        super(new BorderLayout());

        JLabel textLabel = new JLabel("Please select the subject area of interest," +
                " and click the 'Explore' button.", SwingConstants.CENTER);
        textLabel.setPreferredSize(new Dimension(300, 50));

        //Set radio button behavior
        branchingButton.setMnemonic(KeyEvent.VK_B);
        branchingButton.setActionCommand(branchingString);
        branchingButton.setSelected(true);

        loopsButton.setMnemonic(KeyEvent.VK_L);
        loopsButton.setActionCommand(loopsString);

        arraysButton.setMnemonic(KeyEvent.VK_A);
        arraysButton.setActionCommand(arraysString);

        fileIOButton.setMnemonic(KeyEvent.VK_F);
        fileIOButton.setActionCommand(fileIOString);

        recursionButton.setMnemonic(KeyEvent.VK_R);
        recursionButton.setActionCommand(recursionString);

        genericsButton.setMnemonic(KeyEvent.VK_G);
        genericsButton.setActionCommand(genericsString);

        //Group the radio buttons.
        final ButtonGroup group = new ButtonGroup();
        group.add(branchingButton);
        group.add(loopsButton);
        group.add(arraysButton);
        group.add(fileIOButton);
        group.add(recursionButton);
        group.add(genericsButton);

        //Register a listener for the radio buttons.
        branchingButton.addActionListener(this);
        loopsButton.addActionListener(this);
        arraysButton.addActionListener(this);
        fileIOButton.addActionListener(this);
        recursionButton.addActionListener(this);
        genericsButton.addActionListener(this);

        //Set up the picture label.
        picture = new JLabel(createImageIcon("images/Programmer.jpg"));

        //Setting size of the picture displayed on TutorialMenu
        picture.setPreferredSize(new Dimension(360, 250));
        picture.setIcon(createImageIcon("images/Programmer.jpg"));


        //Put the radio buttons in a column in a panel.
        JPanel radioPanel = new JPanel(new GridLayout(0, 1));
        radioPanel.add(branchingButton);
        radioPanel.add(loopsButton);
        radioPanel.add(arraysButton);
        radioPanel.add(fileIOButton);
        radioPanel.add(recursionButton);
        radioPanel.add(genericsButton);

        JButton exploreButton = new JButton();
        exploreButton.setText(buttonString);

        exploreButton.setCursor(Cursor.getDefaultCursor());
        exploreButton.setMargin(new Insets(10,0,10,0));
        exploreButton.setActionCommand(buttonString);
        exploreButton.addActionListener(this);

        add(textLabel, BorderLayout.NORTH);
        add(radioPanel, BorderLayout.LINE_START);
        add(picture, BorderLayout.CENTER);
        add(exploreButton, BorderLayout.SOUTH);
        setBorder(BorderFactory.createEmptyBorder(20,30,20,30));
    }

    /** Listens to the'Explore' button and checks which radio button is selected. */
        public void actionPerformed(ActionEvent e) {
            if (buttonString.equals(e.getActionCommand()) && branchingButton.isSelected()) {
                DocumentViewer.runDocumentViewer("Branching.html", "Branching");
                CodeDemoGUI.runCodeDemo("Branching Demo",
                        "Please select your age to purchase a movie ticket:", 0, 0, 120, 1);
                Toolkit.getDefaultToolkit().beep();
            }
            else if (buttonString.equals(e.getActionCommand()) && loopsButton.isSelected()) {
                DocumentViewer.runDocumentViewer("Loops.html", "Loops");
                CodeDemoGUI.runCodeDemo("Loop Demo","Enter your change, between 1 and 99 cents:",
                        1, 1, 99, 1);
                Toolkit.getDefaultToolkit().beep();
            }
            else if (buttonString.equals(e.getActionCommand()) && arraysButton.isSelected()) {
                DocumentViewer.runDocumentViewer("Arrays.html", "Arrays");
                CodeDemoGUI.runCodeDemo("Array Demo", "Enter an array length between 1 and 10:",
                        1, 1, 10, 1);
                Toolkit.getDefaultToolkit().beep();
            }
            else if (buttonString.equals(e.getActionCommand()) && fileIOButton.isSelected()) {
                DocumentViewer.runDocumentViewer("FileIO.html", "File Input/Output");
                CodeDemoGUI.runCodeDemo("File Input/Output Demo", "Click button to read and write files",
                        0, 0, 0, 0);
                Toolkit.getDefaultToolkit().beep();
            }
            else if (buttonString.equals(e.getActionCommand()) && recursionButton.isSelected()) {
                DocumentViewer.runDocumentViewer("Recursion.html", "Recursion");
                CodeDemoGUI.runCodeDemo("Recursion Demo", "Choose a number from 0 to 100:",
                        0, 0, 100, 1);
                Toolkit.getDefaultToolkit().beep();
            }
            else if (buttonString.equals(e.getActionCommand()) && genericsButton.isSelected()) {
                Toolkit.getDefaultToolkit().beep();
            }
        }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = TutorialMenu.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Create the GUI and show it.
     */
    private static void createAndShowGUI() {

        //Create and set up the window.
        JFrame menuFrame = new JFrame("Java Tutorial");
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new TutorialMenu();
        newContentPane.setOpaque(true); //content panes must be opaque
        menuFrame.setContentPane(newContentPane);

        //Display the window.
        menuFrame.pack();
        menuFrame.setLocationRelativeTo(null); //Sets window position in middle of screen
        menuFrame.setVisible(true);
    }

    public static void main(String[] args) {

        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}