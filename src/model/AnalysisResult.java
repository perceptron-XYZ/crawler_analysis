package model;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import xml.ClassConverter;
import xml.ScrapingEntry;

/**
 * Super class for Table and Chart. Defines common functions for two classes 
 * @author RYAN
 *
 */
public class AnalysisResult {
	@SuppressWarnings("unchecked")
	public Map<Object,Object> createData(String[] parameters){
		Map<Object,Object> dataMap = new HashMap<Object,Object>();
		ArrayList<ScrapingEntry> rawData = ClassConverter.readXML("aaa.xml");
		System.out.println(rawData.size());
		Iterator<ScrapingEntry> rawDataIterator = rawData.iterator();
		if(parameters[0].equals("Rating")){
			createRatingInterval(dataMap);
		}else if(parameters[0].equals("Size")){
			createSizeInterval(dataMap);
		}	
		// Read in each app info, store in dataMap
		while(rawDataIterator.hasNext()){
			ScrapingEntry appInfo = rawDataIterator.next();
			String key =String.valueOf(getData(appInfo,parameters[0])); 
			if(parameters[0].equals("Rating")){
				Float rating = (Float) getData(appInfo,parameters[0]);
				Integer number = (Integer) getData(appInfo,parameters[1]);
				pushRatingData(dataMap,parameters[1],rating,number);
				continue;
			}else if(parameters[0].equals("Size")){
				Float size = (Float) getData(appInfo,parameters[0]);
				Integer number = (Integer) getData(appInfo,parameters[1]);
				pushSizeData(dataMap,parameters[1],size,number);
				continue;
			}	
				
			
			if(parameters[1].equals("Number of Apps for Each")){
				if(dataMap.containsKey(key)){
					Integer count = (Integer)dataMap.get(key);
					dataMap.put(key, ++count);
				}else
					dataMap.put(key, 1);		
			}else if(parameters[1].equals("Average Rating")){
				Float rating = (Float) getData(appInfo,parameters[1]);
				System.out.println(rating);
				if(dataMap.containsKey(key)){	
					((ArrayList<Float>)dataMap.get(key)).add(rating);
				}else{
					ArrayList<Float> ratings = new ArrayList<Float>();
					ratings.add(rating);
					dataMap.put(key, ratings);
				}
				
			}
			else if(parameters[1].equals("Number of Downloads for Each")){
				Integer numOfDownloads = (Integer) getData(appInfo,parameters[1]);
				System.out.println(numOfDownloads);
				if(dataMap.containsKey(key)){	
					Integer count = (Integer)dataMap.get(key);
					dataMap.put(key, count+numOfDownloads);
				}else
					dataMap.put(key, numOfDownloads);
			}
			else if(parameters[1].equals("Total Number of People Rated")){
				Long numOfPeopleRated = (Long) getData(appInfo,parameters[1]);
				System.out.println(numOfPeopleRated);
				if(dataMap.containsKey(key)){	
					Long count = (Long)dataMap.get(key);
					dataMap.put(key, count+numOfPeopleRated);
				}else
					dataMap.put(key, numOfPeopleRated);
			}else if(parameters[1].equals("Number of Apps for Each")){
				if(dataMap.containsKey(key)){
					Integer count = (Integer)dataMap.get(key);
					dataMap.put(key, ++count);
				}else
					dataMap.put(key, 1);		
			}
		}
		//extra step to compute average
		if(parameters[1].equals("Average Rating")){
			for(Map.Entry<Object,Object> entry : dataMap.entrySet()){
					Object key = entry.getKey();
					ArrayList<Object> ratings = (ArrayList<Object>) entry.getValue();
					float average = computeAverage(ratings);
					dataMap.put(key,average);
			}
		}
		return dataMap;
	}
	public Object getData(ScrapingEntry data,String parameter){
		if(parameter.equals("Developers"))
			return data.offeredBy;
		else if(parameter.equals("Categories"))
			return data.category;
		else if(parameter.equals("Rating"))
			return data.rating;
		else if(parameter.equals("Number of Downloads for Each"))
			return data.numOfInstalls;
		else if(parameter.equals("Number of Installs"))
			return data.numOfInstalls;
		else if(parameter.equals("Average Rating"))
			return data.rating;
		else if(parameter.equals("Size"))
			return data.size;
		else if(parameter.equals("Total Number of People Rated"))
			try {
				return NumberFormat.getNumberInstance(java.util.Locale.US).parse(data.numOfPeopleRated);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		else 
			return null;
	}
	public void createRatingInterval(Map<Object,Object> dataMap){
		dataMap.put("Terrible [0,1)",0);
		dataMap.put("Bad [1,2)",0);
		dataMap.put("Acceptable [2,3)",0);
		dataMap.put("Not Bad [3,4)",0);
		dataMap.put("Good [4,5)",0);
		dataMap.put("Perfect 5",0);
		
	}
	public void createSizeInterval(Map<Object,Object> dataMap){
		dataMap.put("[0,1)",0);
		dataMap.put("[1,2)",0);
		dataMap.put("[2,3)",0);
		dataMap.put("[3,4)",0);
		dataMap.put("[4,5)",0);
		dataMap.put("5",0);
	}
	public void pushRatingData(Map<Object,Object>dataMap,String param,float rating,Integer addition){
		int count = 0;
		// addition == null means we are calculationg the number of apps, so only increment by 1.
		if(addition == null)
			addition = 1;
		if(rating==5){
			count = (Integer)dataMap.get("Perfect [5]");
			dataMap.put("Perfect 5", addition+count);
		}else if(rating>=4){
			count = (Integer)dataMap.get("Good [4,5)");
			dataMap.put("Good [4,5)", addition+count);
		}else if(rating>=3){
			count = (Integer)dataMap.get("Average [3,4)");
			dataMap.put("Average [3,4)", addition+count);
		}else if(rating>=2){
			count = (Integer)dataMap.get("Acceptable [2,3)");
			dataMap.put("Acceptable [2,3)", addition+count);
		}else if(rating>=1){
			count = (Integer)dataMap.get("Bad [1,2)");
			dataMap.put("Bad [1,2)", addition+count);
		}else if(rating>=0){
			count = (Integer)dataMap.get("Terrible [0,1)");
			dataMap.put("Terrible [0,1)", addition+count);
		}
	}
	public void pushSizeData(Map<Object,Object>dataMap,String param,float rating,Integer addition){
		if(addition == null)
			addition =1;
		int count = 0;
		if(rating==5){
			count = (Integer)dataMap.get("5");
			dataMap.put("5", addition+count);
		}else if(rating>=4){
			count = (Integer)dataMap.get("[4,5)");
			dataMap.put("[4,5)", addition+count);
		}else if(rating>=3){
			count = (Integer)dataMap.get("[3,4)");
			dataMap.put("[3,4)", addition+count);
		}else if(rating>=2){
			count = (Integer)dataMap.get("[2,3)");
			dataMap.put("[2,3)", addition+count);
		}else if(rating>=1){
			count = (Integer)dataMap.get("[1,2)");
			dataMap.put("[1,2)", addition+count);
		}else if(rating>=0){
			count = (Integer)dataMap.get("[0,1)");
			dataMap.put("[0,1)", addition+count);
		}
	}
	public Object[][] convertMapToArray(Map<Object,Object> map){
		Object[][] array = new Object[map.size()][2];
		int count = 0;
		for(Map.Entry<Object,Object> entry : map.entrySet()){
		    array[count][0] = entry.getKey();
		    array[count][1] = entry.getValue();
		    count++;
		}
		return array;
	}
	public float computeAverage(ArrayList<Object> nums){
		float sum = 0;
		for(int i=0;i<nums.size();i++){
			sum += (Float)nums.get(i);
		}
		return sum/nums.size();
	}
	
}
