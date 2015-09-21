package model;

import java.util.HashMap;
import java.util.Map;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFactory;
import org.jfree.data.general.DefaultPieDataset;

public class Chart extends AnalysisResult{
	private DefaultPieDataset pieDataset;
	public Chart(){
		pieDataset = new DefaultPieDataset();
	}
	
	public JFreeChart createChart(String[] parameters){
		Map<Object,Object> dataMap = new HashMap<>();
		dataMap = createData(parameters);
		setChartDataset(dataMap);
		JFreeChart chart = ChartFactory.createPieChart(
				parameters[0]+" VS "+parameters[1],
				pieDataset,
				true,  // show legends 
				true,  // use tooltips
				false  // configure chart to generate urls?
				);
		return chart;
	}
	
	public void setChartDataset(Map<Object,Object> dataMap){
		for(Map.Entry<Object,Object> entry : dataMap.entrySet()){
		    pieDataset.setValue((String)entry.getKey(),(Number)entry.getValue());
		}
		
	}

	
}
