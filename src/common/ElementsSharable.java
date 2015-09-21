package common;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import view.HelpWindow;
import view.IntroductionWindow;


public interface ElementsSharable {

	 JPanel mainPanel = new JPanel(new BorderLayout());
	 JPanel selectionPanel = new JPanel(new GridLayout(6,2));
	 JButton analysisButton = new JButton("Analyze!");
	 JScrollPane scrollPane = new JScrollPane(); // store result table
     JScrollPane chartScrollPane = new JScrollPane();
     JScrollPane tableScrollPane = new JScrollPane();
     JComboBox<String> base = new JComboBox<>();
     JComboBox<String> aggregate = new JComboBox<>();
     JLabel formatLabel = new JLabel("Select Analysis Type:");
     JLabel baseLabel = new JLabel("Select Base:");
     JLabel aggregateLabel = new JLabel("Select Aggregate:");
     ButtonGroup selectBtnGroup = new ButtonGroup();
     JRadioButton tableButton = new JRadioButton("Table");
     JRadioButton chartButton = new JRadioButton("Chart");
     JLabel errorMsg = new JLabel();
     JButton helpButton = new JButton("Help");
     JButton closeButton = new JButton("Quit");
     HelpWindow helpWindow = new HelpWindow();
     IntroductionWindow introWindow = new IntroductionWindow();
}
