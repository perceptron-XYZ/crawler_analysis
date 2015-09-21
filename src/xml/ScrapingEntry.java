package xml;

import java.util.ArrayList;


public class ScrapingEntry {
	public String name;
	public String offeredBy;
	public String contentRating;
	public float rating;
	public String numOfPeopleRated;
	public int numOfGoogleUpvotes;
	public String category;
	public String dateLastUpdated;
	public String description;
	public String numOfInstalls;
	public String currentVersion;
	public String size;
	public ArrayList<String> similarApps;
	
	@Override public String toString() {
		String s = name + " " + offeredBy  + " " + contentRating  + " " + rating  + " " + numOfPeopleRated + " " + 
				this.numOfGoogleUpvotes + " " + category  + " " + this.dateLastUpdated + " " + description  + " " + this.numOfInstalls  + " " + this.currentVersion  + " " + size;
		
		for (int i = 0; i < similarApps.size(); ++i) 
		{
			s += " " + similarApps.get(i);
		}
		
		return s;
	}
	
}
