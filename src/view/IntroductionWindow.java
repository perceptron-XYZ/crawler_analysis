package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class IntroductionWindow extends JPanel {
	
	private static final long serialVersionUID = 6478112900009407972L;
	private JLabel appTitle = new JLabel("GPADCAT Analysis Tool");
	private JPanel contentPane = new JPanel(new GridLayout(6,1,2,0));
	private JLabel intro1 = new JLabel("GPADCAT is a data analysis tool to analyze android applications based"
			+ " on a provided xml file."+"\n");
	private JLabel intro2 = new JLabel("It provides two features: \n"
			+ "1. Analysis in Table. \n"
			+ "2. Analysis in Chart.");
	private JLabel intro3 = new JLabel("-Table type provides a list view of app information. The list can"
			+ " be sorted by clicking the column headers.");
	private JLabel intro4 = new JLabel("-Chart type provides a pie chart view of percentages of app information. "
			+ "It can be used to study the different distributions. ");
	private JLabel team = new JLabel("Created By: Jermaine Santoya, Joey Oksenhendler, Keshia Coriolan, Olivier Sicard, Xingyu Zhou");
	public IntroductionWindow(){
		setBackground(Color.WHITE);
		contentPane.setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		setSize(900,600);
		appTitle.setFont(new Font("Serif", Font.ROMAN_BASELINE, 30));
		add(appTitle,BorderLayout.NORTH);
		contentPane.add(intro1);
		contentPane.add(intro2);
		contentPane.add(intro3);
		contentPane.add(intro4);
		contentPane.setFont(new Font("Serif",Font.PLAIN,20));
		add(contentPane,BorderLayout.CENTER);
		team.setFont(new Font("Serif", Font.BOLD, 16));
		add(team,BorderLayout.SOUTH);
	}

}
