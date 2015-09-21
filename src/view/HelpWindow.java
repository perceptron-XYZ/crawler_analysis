package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;

public class HelpWindow extends JFrame{
	
	private static final long serialVersionUID = 2204204005624859275L;
	JPanel titlePane = new JPanel();
	JLabel title = new JLabel("How to Guide to the GPADCAT Analysis Tool");
	JPanel instructionPane = new JPanel(new GridLayout(10,0));
	JLabel[] instructions = new JLabel[10];
	public HelpWindow(){
		setSize(550,300);
		setLayout(new BorderLayout());
		titlePane.add(title);
		getContentPane().add(titlePane,BorderLayout.NORTH);
		//initialize ten instructions
		for(int i=0;i<instructions.length;i++)
			instructions[i] = new JLabel();
		instructions[0].setText("----------------This is a step by step guide for the use of this software----------------");
		instructions[1].setText("STEP 1: Select what kind of analysis you want: Table or Chart");
		instructions[2].setText("STEP 2: Select a Base");
		instructions[3].setText("STEP 3: Select an Aggregate");
		instructions[4].setText("STEP 4: Click on Analyse to generate the desired Table or Chart");
		instructions[5].setText(" ");
		instructions[6].setText("----------------Note that----------------");
		instructions[7].setText("When the tool opens: no data will be displayed and no analysis type will be selected");
		instructions[8].setText("When alternating between chart and table: the base and aggregate will reset to defaults");	
		
		for(int i=0;i<instructions.length;i++){
			instructionPane.add(instructions[i]);
		}
		getContentPane().add(instructionPane,BorderLayout.CENTER);
	}
}
