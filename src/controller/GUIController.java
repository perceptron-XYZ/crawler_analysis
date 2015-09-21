package controller;

import common.ElementsSharable;
import view.MainWindow;
import model.Table;
import model.Chart;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTable;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;


public class GUIController implements ElementsSharable {
	
	private MainWindow mainframe;
	
	public GUIController(){
		MainWindow newView = new MainWindow();
		mainframe = newView;
		analysisButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(mainframe.isSelectedTable()){
					mainPanel.add(scrollPane);
					introWindow.setVisible(false);
					displayTable();							
				}
				else if(mainframe.isSelectedChart()){
					mainPanel.add(scrollPane);
					introWindow.setVisible(false);
					displayChart();
				}	
				else {
					errorMsg.setText("Select type first.");
					errorMsg.setForeground(Color.RED);
				}
					
			}
		});
		tableButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if(tableButton.isSelected()){
					errorMsg.setText("");
					base.removeAllItems();
					base.addItem("Developers");
					base.addItem("Categories");
					base.addItem("Rating");
					base.addItem("Number of Installs");
				}
			}
		});
		chartButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if(chartButton.isSelected()){
					errorMsg.setText("");
					base.removeAllItems();
					base.addItem("Categories");
					base.addItem("Rating");
					base.addItem("Number of Installs");
				}
			}
		});
		base.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(base.getSelectedItem()==null)
					return;
				else
					displayAggregate(base.getSelectedItem().toString());
			}		
		});
		helpButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				helpWindow.setVisible(!helpWindow.isVisible());
			}
		});
		closeButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				helpWindow.dispose();
				mainframe.dispose();
			}
		});
	}
	public void displayTable(){
		// clear error message;
		errorMsg.setText("");
		String baseSelected = base.getSelectedItem().toString();
		String aggregateSelected = aggregate.getSelectedItem().toString();
		System.out.println(baseSelected);
		String[] params = {baseSelected,aggregateSelected};
		Table resultTable = new Table(params);
		JTable newTable = resultTable.getTableData();
		mainframe.addTable(newTable);
		refreshScreen();
	}
	public void displayChart(){
		String baseSelected = base.getSelectedItem().toString();
		String aggregateSelected = aggregate.getSelectedItem().toString();
		String[] params = {baseSelected,aggregateSelected};
		Chart chartObject = new Chart();
		JFreeChart chart = chartObject.createChart(params);
		ChartPanel chartPane = new ChartPanel(chart);
		mainframe.addChart(chartPane);
		refreshScreen();
		
	}
	public void displayAggregate(String selectedComboBoxString){
		if(selectedComboBoxString.equals("Developers")){
			aggregate.removeAllItems();
			aggregate.addItem("Number of Apps for Each");
			aggregate.addItem("Average Rating");
			aggregate.addItem("Total Number of People Rated");
		}else if(selectedComboBoxString.equals("Categories")){
			aggregate.removeAllItems();
			aggregate.addItem("Number of Apps for Each");
			aggregate.addItem("Total Number of People Rated");
			//aggregate.addItem("Number of Downloads for Each");
		}else if(selectedComboBoxString.equals("Rating")){
			aggregate.removeAllItems();
			aggregate.addItem("Number of Apps for Each");
			//aggregate.addItem("Number of Downloads for Each");
		}else if(selectedComboBoxString.equals("Size")){
			aggregate.removeAllItems();
			aggregate.addItem("Number of Apps for Each");
		}else if(selectedComboBoxString.equals("Number of Installs")){
			aggregate.removeAllItems();
			aggregate.addItem("Number of Apps for Each");
		}
		else 
			return;
		refreshScreen();
	}
	public void refreshScreen(){
		mainframe.revalidate();
		mainframe.repaint();
	}
	public static void main(String[] args){
		GUIController app = new GUIController();
		app.mainframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		app.mainframe.setVisible(true);
		
	}

}
