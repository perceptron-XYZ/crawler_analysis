package model;

import javax.swing.table.DefaultTableModel;

/**
 * Defines a new table model for all the tables in our application. 
 * The model fixes the data types of each column.
 * The second row is Number type, and the rest is String. This class guarantees that the sorter works correctly.
 * @author RYAN
 *
 */
public class AnalysisTableModel extends DefaultTableModel{
	
	private static final long serialVersionUID = 7439648301760615976L;

	public AnalysisTableModel(Object rowData[][], Object columnNames[]) {  
         super(rowData, columnNames);  
      }  
     
     @Override  
      public Class<?> getColumnClass(int col) {  
        if (col == 1)       //second column accepts only Integer values  
            return Integer.class;  
        else return String.class;  //other columns accept String values  
    }  
  
}
