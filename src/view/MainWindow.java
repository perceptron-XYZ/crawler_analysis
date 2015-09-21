package view;
import common.ElementsSharable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

import org.jfree.chart.ChartPanel;


public class MainWindow extends JFrame implements ElementsSharable {

	private static final long serialVersionUID = 6629216261449276015L;
	
	public MainWindow(){
		initComponents();
	}
	public void initComponents(){
		setTitle("An awesome google play analysis tool");
		setSize(1400,600);	
		setLayout(new BorderLayout());
		setBackground(Color.gray);
		// Left part of the screen
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		getContentPane().add( mainPanel,BorderLayout.CENTER );
		//scrollPane.add(introWindow);
		mainPanel.add(scrollPane);
		mainPanel.add(introWindow);
		// Right part of the screen
		selectionPanel.setPreferredSize(new Dimension(500,600));
		selectionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		helpButton.setPreferredSize(new Dimension(50,0));
		
		JPanel helpButtonPanel = new JPanel(new BorderLayout());
		JPanel innerHelpButtonPanel = new JPanel(new GridLayout(0,2));
		closeButton.setPreferredSize(new Dimension(70,30));
		helpButton.setPreferredSize(new Dimension(70,30));
		innerHelpButtonPanel.add(closeButton);
		innerHelpButtonPanel.add(helpButton);
		helpButtonPanel.add(innerHelpButtonPanel,BorderLayout.EAST);
		selectionPanel.add(new JPanel()); // skip a cell in gridlayout
		selectionPanel.add(helpButtonPanel);
		JPanel tableOrChart = new JPanel(new BorderLayout());
		tableOrChart.add(tableButton,BorderLayout.WEST);
		tableOrChart.add(chartButton,BorderLayout.CENTER);
		selectBtnGroup.add(tableButton);
        selectBtnGroup.add(chartButton);
        selectionPanel.add(formatLabel);
        selectionPanel.add(tableOrChart);
        baseLabel.setLabelFor(base);
        aggregateLabel.setLabelFor(aggregate);
        selectionPanel.add(baseLabel);
        selectionPanel.add(base);
        selectionPanel.add(aggregateLabel);
        selectionPanel.add(aggregate);
        selectionPanel.add(new JPanel());
        selectionPanel.add(analysisButton);
        selectionPanel.add(errorMsg);
        getContentPane().add(selectionPanel,BorderLayout.EAST);
          
	}

	public boolean isSelectedTable(){
		return tableButton.isSelected();
	}
	public boolean isSelectedChart(){
		return chartButton.isSelected();
	}
	// add a new table to the view at designated position. In this case, add to topPanel.
	public void addTable(JTable newTable){
		scrollPane.setViewportView(newTable);	
	}
	public void addChart(ChartPanel newChart){
		scrollPane.setViewportView(newChart);
	}

}
