package model;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;


public class Table extends AnalysisResult{

	JTable tableData;
	String tableName;
	
	public Table(String[] parameters){
		String cols[] = parameters;
		Map<Object,Object> dataMap = createData(parameters);
		Object[][] data = convertMapToArray(dataMap);
		//Create a table model so that it can be sorted later
		AnalysisTableModel model = new AnalysisTableModel(data,cols);
		System.out.println(model.getColumnClass(1).toString());
		tableData = new JTable(model);
		// align table elements
		DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
		leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);
		tableData.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);
		// add a sorter to jtable
		final TableRowSorter<AnalysisTableModel> sorter = new TableRowSorter<>(model);
		tableData.setRowSorter(sorter);
		// set font of table
		tableData.getTableHeader().setFont(new Font("Serif", Font.BOLD, 20));
		tableData.setFont(new Font("Serif",Font.PLAIN,20));
		// set gap for table
		int gapWidth = 10;
		int gapHeight = 10;
		tableData.setIntercellSpacing(new Dimension(gapWidth,gapHeight));
		tableData.setRowHeight(tableData.getRowHeight()+gapHeight);
		tableName = "default table";
	}
	
	public JTable getTableData(){
		return tableData;
	}
}
