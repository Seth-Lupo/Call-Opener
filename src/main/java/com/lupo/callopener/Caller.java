package com.lupo.callopener;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;

import org.json.simple.JSONObject;

import javafx.application.Platform;

public class Caller implements Runnable {

	@Override
	public void run() {
		
		Main.upcomingController.loadUpcoming();
		
		int dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK); 
    	JSONObject meetingsThisDay = null;
		
    	switch (dayOfWeek) {
    	
    		case Calendar.MONDAY: meetingsThisDay = (JSONObject) Main.meetingsJSON.get("Monday"); break;
    		case Calendar.TUESDAY: meetingsThisDay = (JSONObject) Main.meetingsJSON.get("Tuesday"); break;
    		case Calendar.WEDNESDAY: meetingsThisDay = (JSONObject) Main.meetingsJSON.get("Wednesday"); break;
    		case Calendar.THURSDAY: meetingsThisDay = (JSONObject) Main.meetingsJSON.get("Thursday"); break;
    		case Calendar.FRIDAY: meetingsThisDay = (JSONObject) Main.meetingsJSON.get("Friday"); break;
    		case Calendar.SATURDAY: meetingsThisDay = (JSONObject) Main.meetingsJSON.get("Saturday"); break;
    		case Calendar.SUNDAY: meetingsThisDay = (JSONObject) Main.meetingsJSON.get("Sunday"); break;
    	
    	}
		
    	String earlyString= (String) Main.settingsJSON.get("earlyInt");
    	int early = 0;
    	if(!earlyString.equals("")) early = Integer.parseInt(earlyString); 
    	boolean alarm = Boolean.parseBoolean((String) Main.settingsJSON.get("alarmBool"));
    	boolean prompt = Boolean.parseBoolean((String) Main.settingsJSON.get("promptBool"));
    	boolean enabled = Boolean.parseBoolean((String) Main.settingsJSON.get("enabledBool"));
    	
    	if(enabled) {
    	
			for (int i = 1; i <= 10; i++) {
				
				boolean infoExists = (((JSONObject) meetingsThisDay.get("Names")).get(Integer.toString(i)) != null) 
	    				&& (((JSONObject) meetingsThisDay.get("Times")).get(Integer.toString(i)) != null) 
	    				&& (((JSONObject) meetingsThisDay.get("URLs")).get(Integer.toString(i)) != null);
				
				if(infoExists) {
					
					String timeString = (String) ((JSONObject) meetingsThisDay.get("Times")).get(Integer.toString(i));
					
					if (Main.convertTimeToMinutes(timeString) - early == Main.getMinutesSinceMidnight()) {
						
						Main.reminderStage.close();
						Main.promptStage.close();
						
						String name = (String) ((JSONObject) meetingsThisDay.get("Names")).get(Integer.toString(i));
						String url = (String) ((JSONObject) meetingsThisDay.get("URLs")).get(Integer.toString(i));
						
						if (prompt) {
								
							Main.promptController.loadPrompt(name, url, alarm);
							Main.promptStage.show();
						
						} else {
							
							try {
								Desktop.getDesktop().browse(new URI(url));
							} catch (IOException | URISyntaxException e) {
								e.printStackTrace();
							}
							
							Main.reminderController.loadPrompt(name, alarm);
							Main.reminderStage.show();
							
							
							
						}
						
						
						
					}
					
				}
				
			}
			
    	}
		
	}

}
