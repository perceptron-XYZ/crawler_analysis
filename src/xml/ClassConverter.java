package xml;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import common.ElementsSharable;

// external libs
import com.thoughtworks.xstream.XStream;

public class ClassConverter implements ElementsSharable
{

	
	public static ArrayList<ScrapingEntry> readXML(String xmlFileName) {
		Scanner decoder = null;
		try {
			decoder = new Scanner(
					new BufferedReader(
							new FileReader(xmlFileName)
							)
					);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("ERROR: Could not read " + xmlFileName + ", so exiting!");
			return null;
		}
		
		ArrayList<ScrapingEntry> xmlDatas = new ArrayList<ScrapingEntry>();
		
		XStream xstream = new XStream();
			
		int tagCounter = 0;
		String parsedLine = "";
		String parsedObject = "";
		while (decoder.hasNextLine()) {
			parsedLine = decoder.nextLine();
			
			if (parsedLine.contains("xml.ScrapingEntry")) {
				tagCounter++;
			}
			
			parsedObject += parsedLine;
			parsedLine = "";
			
			if (tagCounter >= 2) {
				tagCounter = 0;
				//System.out.println(xstream.fromXML(parsedObject));
				xmlDatas.add((ScrapingEntry)xstream.fromXML(parsedObject));
				
				parsedObject = "";
			}
		}
			
		decoder.close();
		
		xmlDatas.trimToSize();
		return xmlDatas;
	
	}
}
