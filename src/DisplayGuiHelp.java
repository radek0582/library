import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class DisplayGuiHelp
{
    public DisplayGuiHelp(ArrayList<String> list) //constructor of the DisplayGuiHelp object that has the list passed to it on creation
    {
        final JFrame theFrame = new JFrame();
        theFrame.setTitle("Stack exchange help");
        theFrame.setSize(500, 500);
        theFrame.setLocation(550, 400);

        JPanel mainPanel = new JPanel();

        JTextArea theText = new JTextArea(5,25); //create the text area

        for(String text : list)
        {
            theText.append(text + "\n"); //append the contents of the array list to the text area

        }
        mainPanel.add(theText); //add the text area to the panel

        theFrame.getContentPane(); //add the panel to the frame
        theFrame.add(mainPanel);
        theFrame.pack();
        theFrame.setVisible(true);

    }
}