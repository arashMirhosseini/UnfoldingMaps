package module6;


import java.util.*;

import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import processing.core.PConstants;
import processing.core.PGraphics;

/** Implements a common marker for cities and earthquakes on an earthquake map
 * 
 * @author UC San Diego Intermediate Software Development MOOC team
 *
 */
public abstract class CommonMarker extends SimplePointMarker {

	// Records whether this marker has been clicked (most recently)
	protected boolean clicked = false;
	// a flag to indicate if a city is clicked on or not 
	 
	// a list of earthquake markers
	private List<Marker> markerEarthquake;
	
	// the marker for the clicked city
	private Marker markerCity; 
	
	public CommonMarker(Location location) {
		super(location);
	}
	
	public CommonMarker(Location location, java.util.HashMap<java.lang.String,java.lang.Object> properties) {
		super(location, properties);
	}
	
	// Getter method for clicked field
	public boolean getClicked() {
		return clicked;
	}
	
	// Setter method for clicked field
	public void setClicked(boolean state) {
		clicked = state;
	}
	
	public void setMarkerEarthquake(List<Marker> marker) {
		markerEarthquake = new ArrayList<Marker>(marker);
	}
	
	public void setMarkerCity(Marker marker) {
		markerCity = marker; 
	}
	
	// Common piece of drawing method for markers; 
	// YOU WILL IMPLEMENT. 
	// Note that you should implement this by making calls 
	// drawMarker and showTitle, which are abstract methods 
	// implemented in subclasses
	public void draw(PGraphics pg, float x, float y) {
		// For starter code just drawMaker(...)
		if (!hidden) {
			drawMarker(pg, x, y);
			if (selected) {
				showTitle(pg, x, y);	
			}
			
			if (clicked) showPopup(pg, x, y); 
		}
		
	}
	
	public void showPopup(PGraphics pg, float x, float y) {
		int countNearbyQuake = 0; 
		float averageMag = 0; 
		float mag = 0;
		for (Marker m : markerEarthquake) {
			EarthquakeMarker quakeMarker = (EarthquakeMarker)m;
			Location city = markerCity.getLocation();
			if (quakeMarker.getDistanceTo(city) < quakeMarker.threatCircle()) {
				countNearbyQuake++;
				mag = mag + quakeMarker.getMagnitude(); 
			}
		}
		averageMag = mag / countNearbyQuake; 
		String nearby = "# of nearby earthquakes: " + countNearbyQuake; 
		String average = "Average Magnitude: " + averageMag; 
		
		pg.pushStyle();
		
		pg.fill(255, 255, 255);
		pg.textSize(12);
		pg.rectMode(PConstants.CORNER);
		pg.rect(x, y-5-39, Math.max(pg.textWidth(nearby), pg.textWidth(average)) + 6, 39);
		pg.fill(0, 0, 0);
		pg.textAlign(PConstants.LEFT, PConstants.TOP);
		pg.text(nearby, x+3, y-5-33);
		pg.text(average, x+3, y - 5 -18);
		
		pg.popStyle();

	}
	public abstract void drawMarker(PGraphics pg, float x, float y);
	public abstract void showTitle(PGraphics pg, float x, float y);
}