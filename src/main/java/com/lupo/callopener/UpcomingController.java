package com.lupo.callopener;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

import org.json.simple.JSONObject;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

public class UpcomingController {

    @FXML
    private Button b1;

    @FXML
    private Button b2;

    @FXML
    private Button b3;

    @FXML
    private Button b10;

    @FXML
    private Button b9;

    @FXML
    private Button b8;

    @FXML
    private Button b7;

    @FXML
    private Button b6;

    @FXML
    private Button b5;

    @FXML
    private Button b4;

    @FXML
    private Button closeButton;
    
    @FXML
    private Label title;

    @FXML
    private Label t1;

    @FXML
    private Label t2;

    @FXML
    private Label t3;

    @FXML
    private Label t4;

    @FXML
    private Label t6;

    @FXML
    private Label t7;

    @FXML
    private Label t8;

    @FXML
    private Label t9;

    @FXML
    private Label t5;

    @FXML
    private Label t10;
    
    @FXML
    private RowConstraints r1;

    @FXML
    private RowConstraints r2;

    @FXML
    private RowConstraints r3;

    @FXML
    private RowConstraints r4;

    @FXML
    private RowConstraints r5;

    @FXML
    private RowConstraints r6;

    @FXML
    private RowConstraints r7;

    @FXML
    private RowConstraints r8;

    @FXML
    private RowConstraints r9;

    @FXML
    private RowConstraints r10;
    
    @FXML
    private GridPane gridPane;
    
    
    @FXML
    private RowConstraints buttonRow;
    
    private Label[] textArray;
    private Button[] buttonArray;
    private RowConstraints[] rowArray;
    
    private ArrayList<MeetingInfo> rankedMeetings;
    
    @FXML
    public void initialize() {
    	
    	textArray = new Label[] {t1, t2, t3, t4, t5, t6, t7, t8, t9, t10};
    	buttonArray = new Button[] {b1, b2, b3, b4, b5, b6, b7, b8, b9, b10};
    	rowArray = new RowConstraints[] {r1, r2, r3, r4, r5, r6, r7, r8, r9, r10};
    	
    }
    
    public void loadUpcoming() {
    	
    	rankedMeetings = null;

    	int dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK); 
    	
    	switch (dayOfWeek) {
    	
    		case Calendar.MONDAY: rankedMeetings = rankMeetings("Monday"); break;
    		case Calendar.TUESDAY: rankedMeetings = rankMeetings("Tuesday"); break;
    		case Calendar.WEDNESDAY: rankedMeetings = rankMeetings("Wednesday"); break;
    		case Calendar.THURSDAY: rankedMeetings = rankMeetings("Thursday"); break;
    		case Calendar.FRIDAY: rankedMeetings = rankMeetings("Friday"); break;
    		case Calendar.SATURDAY: rankedMeetings = rankMeetings("Saturday"); break;
    		case Calendar.SUNDAY: rankedMeetings = rankMeetings("Sunday"); break;
    	
    	}
    	
    	for (int i = 0; i < 10; i++) {
    		
    		if (i < rankedMeetings.size()) {
    			
    			String message = rankedMeetings.get(i).createMessage();
    			
    			textArray[i].setText(message);
    			
    			textArray[i].setVisible(true);
    			buttonArray[i].setVisible(true);
    			textArray[i].setManaged(true);
    			buttonArray[i].setManaged(true);
    			rowArray[i].setMaxHeight(40);
    			rowArray[i].setMinHeight(40);
 
    			
     			if (message.substring(message.length() - 1).equals("!")) textArray[i].setStyle("-fx-font-weight: 900; -fx-text-fill:  #584D4C; -fx-font-size: 20; -fx-font-family: 'Lucida Sans';");
     			else textArray[i].setStyle("-fx-font-weight: normal; -fx-text-fill:  #584D4C; -fx-font-size: 18; -fx-font-family: 'Lucida Sans';");
    			
    		} else {
    			
    			textArray[i].setVisible(false);
    			buttonArray[i].setVisible(false);
    			rowArray[i].setMaxHeight(0);
    			textArray[i].setManaged(false);
    			buttonArray[i].setManaged(false);
    			
    		}
    		
    		if (rankedMeetings.size() == 0) title.setText("There Are No Upcoming Calls");
    		else title.setText("Upcoming Calls");
    		
    		gridPane.setPrefHeight(rankedMeetings.size() * 40);;
    		    		
    		Main.upcomingStage.setMaxHeight(240 + rankedMeetings.size() * 40);
    		
    		
    	}
    	
    }
    
    @FXML
    void b10Act(ActionEvent event) throws IOException, URISyntaxException {
    	
    	Desktop.getDesktop().browse(new URI(rankedMeetings.get(9).url));
    	
    }

    @FXML
    void b1Act(ActionEvent event) throws IOException, URISyntaxException {
    	Desktop.getDesktop().browse(new URI(rankedMeetings.get(0).url));
    }

    @FXML
    void b2Act(ActionEvent event) throws IOException, URISyntaxException {
    	Desktop.getDesktop().browse(new URI(rankedMeetings.get(1).url));
    	
    }

    @FXML
    void b3Act(ActionEvent event) throws IOException, URISyntaxException {
    	Desktop.getDesktop().browse(new URI(rankedMeetings.get(2).url));
    	
    }

    @FXML
    void b4Act(ActionEvent event) throws IOException, URISyntaxException {
    	Desktop.getDesktop().browse(new URI(rankedMeetings.get(3).url));
    	
    }

    @FXML
    void b5Act(ActionEvent event) throws IOException, URISyntaxException {
    	Desktop.getDesktop().browse(new URI(rankedMeetings.get(4).url));
    	
    }

    @FXML
    void b6Act(ActionEvent event) throws IOException, URISyntaxException {
    	Desktop.getDesktop().browse(new URI(rankedMeetings.get(5).url));
    	
    }

    @FXML
    void b7Act(ActionEvent event) throws IOException, URISyntaxException {
    	Desktop.getDesktop().browse(new URI(rankedMeetings.get(6).url));
    	
    }

    @FXML
    void b8Act(ActionEvent event) throws IOException, URISyntaxException {
    	Desktop.getDesktop().browse(new URI(rankedMeetings.get(7).url));
    	
    }

    @FXML
    void b9Act(ActionEvent event) throws IOException, URISyntaxException {
    	Desktop.getDesktop().browse(new URI(rankedMeetings.get(8).url));
    	
    }

    @FXML
    void closeButtonAct(ActionEvent event) {
    	
    	Main.upcomingStage.close();
    	
    }
    
    
    
    public ArrayList<MeetingInfo> rankMeetings(String day) {
		
    	JSONObject meetingsThisDay = (JSONObject) Main.meetingsJSON.get(day);
    	
    	ArrayList<MeetingInfo> meetingList = new ArrayList<MeetingInfo>();
    	
    	for(int i = 1; i <= 10; i++) {	
    		
    		boolean infoExists = (((JSONObject) meetingsThisDay.get("Names")).get(Integer.toString(i)) != null) 
    				&& (((JSONObject) meetingsThisDay.get("Times")).get(Integer.toString(i)) != null) 
    				&& (((JSONObject) meetingsThisDay.get("URLs")).get(Integer.toString(i)) != null);
    		
    		
    		if(infoExists) {
    			
    			String name = (String) ((JSONObject) meetingsThisDay.get("Names")).get(Integer.toString(i));
    			String time = (String) ((JSONObject) meetingsThisDay.get("Times")).get(Integer.toString(i));
    			String url = (String) ((JSONObject) meetingsThisDay.get("URLs")).get(Integer.toString(i));
    			int timeInt = Main.convertTimeToMinutes(time);
    			
    			meetingList.add(new MeetingInfo(name, time, url, timeInt));  			
    			
    		}
    		
    	}
    	
    	Collections.sort(meetingList);
		
    	int size = meetingList.size();
    	
		for (int i = 0; i < size; i++) {
		
			if (meetingList.get(0).timeDif < -30) meetingList.remove(0); 
				
		}
		
		return meetingList;
    	
    	
    }
    
    private static class MeetingInfo implements Comparable {
    	
    	public String name, time, url;
    	public int timeInt, timeDif;
    	
    	public MeetingInfo(String name, String time, String url, int timeInt) {
    		
    		this.name = name;
    		this.time = time;
    		this.url = url;
    		this.timeInt = timeInt;
    		this.timeDif = timeInt - Main.getMinutesSinceMidnight();
    		
    	}
    	
    	public String createMessage() {
			
    		if (timeDif < 0) {
    			
    			String minuteNoun = "";
    			if (timeDif == 1) minuteNoun = "minute";
    			else minuteNoun = "minutes";
    			
    			return "The meeting " + name + " has started " + timeDif * -1 + " " + minuteNoun + " ago!";
    		
    		} else if (timeDif == 0) {
    			
    			return "The meeting " + name + " has just started!";
    			
    		} else {
    			
    			int hours = (int) Math.floor(timeDif/60);
    			int minutes = timeDif - hours * 60;
    			
    			String minuteNoun = "";
    			if (minutes == 1) minuteNoun = "minute";
    			else minuteNoun = "minutes";
    			
    			String hourNoun = "";
    			if (hours == 1) hourNoun = "hour";
    			else hourNoun = "hours";	
    			
    			if (hours == 0) return "The meeting " + name + " will start in " + minutes + " " + minuteNoun + ".";
    			else return "The meeting " + name + " will start in " + hours + " " + hourNoun + " and " + minutes + " " + minuteNoun + ".";
    			
    		}
    		

    	}

		@Override
		public int compareTo(Object o) {
			
			return timeInt - ((MeetingInfo) o).timeInt;
		}
    	
    }

}
