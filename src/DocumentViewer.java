/**
 *  Generates a GUI that serves as a document viewer.
 *
 *  @author   Robert Dobson
 *  @version  1.0
 */

import javax.swing.*;
import java.awt.*;              //for layout managers and more
import java.awt.event.*;        //for action events
import java.io.IOException;

public class DocumentViewer extends JPanel implements ActionListener {

    private static String document;
    private static String titledBorder;

    private DocumentViewer() {

        setLayout(new BorderLayout());

        //Create an editor pane.
        JEditorPane editorPane = createEditorPane();
        JScrollPane editorScrollPane = new JScrollPane(editorPane);
        editorScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        editorScrollPane.setPreferredSize(new Dimension(600, 900));
        editorScrollPane.setMinimumSize(new Dimension(10, 10));
        editorPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(titledBorder),
                BorderFactory.createEmptyBorder(5,5,5,5)));
        add(editorScrollPane, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {

    }

    private JEditorPane createEditorPane() {

        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        java.net.URL helpURL = DocumentViewer.class.getResource(document);
        if (helpURL != null) {
            try {
                editorPane.setPage(helpURL);
            } catch (IOException e) {
                System.err.println("Attempted to read a bad URL: " + helpURL);
            }
        } else {
            System.err.println("Couldn't find file: " + document);
        }
        return editorPane;
    }

    /**
     * Create the GUI and show it.
     */
    private static void createAndShowGUI() {

        //Create and set up the window.
        JFrame frame = new JFrame("Document Viewer");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Add content to the window.
        frame.add(new DocumentViewer());
        frame.setPreferredSize(new Dimension(600, 900));

        //Display the window.
        frame.pack();
        //This code positions window in upper right corner of screen
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        int x = (int) rect.getMaxX() - frame.getWidth();
        int y = 0;
        frame.setLocation(x, y);
        frame.setVisible(true);
    }

    public static void runDocumentViewer(String doc, String border) {

        document = doc;
        titledBorder = border;

        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });
    }
}