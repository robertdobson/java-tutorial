/**
 *  Generates a GUI that serves as a code demonstration interface.
 *
 *  @author   Robert Dobson
 *  @version  1.0
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CodeDemoGUI extends JPanel implements ActionListener, ChangeListener {

    private static String submit = "Submit";
    private static String introMessage;
    private static String demoTitle;
    private int userInput;
    private static int spinnerStart, spinnerMin, spinnerMax, spinnerStep;

    private JLabel introText = new JLabel(introMessage);
    private SpinnerModel spinnerEntry = new SpinnerNumberModel(spinnerStart, spinnerMin, spinnerMax, spinnerStep);
    private JSpinner spinner = new JSpinner(spinnerEntry);
    private JButton processResult = new JButton();
    public static JTextArea showResult = new JTextArea(17, 45);
    private static JPanel codeDemoPanel = new JPanel();

    public CodeDemoGUI() {
        super();

        codeDemoPanel.setLayout(new BoxLayout(codeDemoPanel, BoxLayout.Y_AXIS));
        this.add(introText);
        this.add(spinner);
        this.add(processResult);
        this.add(showResult);
        this.setBorder(BorderFactory.createEmptyBorder(20,10,5,10));

        introText.setPreferredSize(new Dimension(300, 50));

        spinner.getEditor().setPreferredSize(new Dimension(30, 20));
        spinner.setAlignmentX(LEFT_ALIGNMENT);

        processResult.setText(submit);
        processResult.setCursor(Cursor.getDefaultCursor());
        processResult.setMargin(new Insets(10,20,10,20));
        processResult.setActionCommand(submit);

        showResult.setLineWrap(true);
        showResult.setWrapStyleWord(true);

        processResult.addActionListener(this);
        spinnerEntry.addChangeListener(this);

    }

    /** Listens to the selection spinner. */
    public void stateChanged(ChangeEvent e) {
        userInput = (Integer) spinner.getValue();
    }

    /** Listens to the button. */
    public void actionPerformed(ActionEvent e) {
        if (submit.equals(e.getActionCommand())) {
            // Call specific demo processing method here
            if (TutorialMenu.branchingButton.isSelected()) {
                CodeDemoMethods.branchingDemoMethod(userInput);
            }
            else if (TutorialMenu.loopsButton.isSelected()) {
                CodeDemoMethods.loopDemoMethod(userInput);
            }
            else if (TutorialMenu.arraysButton.isSelected()) {
                CodeDemoMethods.arrayDemoMethod(userInput);
            }
            else if (TutorialMenu.fileIOButton.isSelected()) {
                CodeDemoMethods.fileIODemoMethod();
            }
            else if (TutorialMenu.recursionButton.isSelected()) {
                CodeDemoMethods.recursionDemoMethod(userInput);
            }
            Toolkit.getDefaultToolkit().beep();
        }
    }

    /**
     * Create the GUI and show it.
     */
    private static void createAndShowGUI() {

        // Create and set up the window.
        JFrame codeDemoFrame = new JFrame(demoTitle);
        codeDemoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create and set up the content pane.
        JComponent newContentPane = new CodeDemoGUI();
        newContentPane.setOpaque(true);
        codeDemoFrame.setContentPane(newContentPane);
        codeDemoFrame.add(codeDemoPanel);
        codeDemoFrame.setPreferredSize(new Dimension(550, 425));

        // Display the window.
        codeDemoFrame.pack();
        codeDemoFrame.setVisible(true);
    }

    public static void runCodeDemo(String title, String intro, int start, int min, int max, int step) {

        // Passes in relevant values for the chosen code demo
        demoTitle = title;
        introMessage = intro;
        spinnerStart = start;
        spinnerMin = min;
        spinnerMax = max;
        spinnerStep = step;

        // Reset the text area
        showResult.setText("");

        // Creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

}
