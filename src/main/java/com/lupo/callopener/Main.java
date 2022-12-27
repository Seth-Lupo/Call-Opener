package com.lupo.callopener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;



public class Main extends Application {
	
		public static Pane mainPane;
		public static Pane editorPane;
		public static Pane upcomingPane;
		public static Pane promptPane;
		public static Pane reminderPane;
		public static Pane correctorPane;
		public static Stage editorStage;
		public static Stage upcomingStage;
		public static Stage promptStage;
		public static Stage reminderStage;
		public static Stage correctorStage;
		public static MainController mainController;
		public static EditorController editorController;
		public static UpcomingController upcomingController;
		public static PromptController promptController;
		public static ReminderController reminderController;
		public static CorrectorController correctorController;
		
		public FXMLLoader loader;
		public static Stage stage;
		public static JSONObject meetingsJSON; 
		public static JSONObject settingsJSON;
		
		public static Media sound;
		public static Image icon;
		
		public static String appdataDir;
		
		public static void main(String[] args) {
			launch();
		}
		
		
		
		public static void copyInputStreamToFile(InputStream input, File file) {  

		    try (OutputStream output = new FileOutputStream(file)) {
		        input.transferTo(output);
		    } catch (IOException ioException) {
		        ioException.printStackTrace();
		    }

		}
		
	public void start(Stage primaryStage) throws InterruptedException, IOException{
		
		
		appdataDir = System.getenv("APPDATA").replace("\\", "/");
		System.out.println(appdataDir);
		
		File baseDir = new File(appdataDir +"/CallOpener/");
		baseDir.mkdir();
		
		try {
			copyInputStreamToFile(getClass().getResourceAsStream("SlowMorning.mp3"), new File(appdataDir + "/CallOpener/alarm.mp3"));
		} catch (Exception e) {
			
		}
		
		sound = new Media("File:///" + appdataDir + "/CallOpener/alarm.mp3");
		icon = new Image(Main.class.getResourceAsStream("riseandshine.png"));
		
		
		try {
			
			JSONParser jsonParser = new JSONParser();
			FileReader reader = new FileReader(appdataDir + "/CallOpener/meetings.json");
			
			Object obj = jsonParser.parse(reader);
	        meetingsJSON = (JSONObject) obj;
	        
        
		} catch (ParseException e) {
			
			
			
			
		} catch (FileNotFoundException e) {
			
			System.out.println("No File");
			
			JSONObject organizer = new JSONObject();
			organizer.put("Names", new JSONObject());
			organizer.put("Times", new JSONObject());
			organizer.put("URLs", new JSONObject());
			
			meetingsJSON = new JSONObject();
	        meetingsJSON.put("Monday", organizer.clone());
	        meetingsJSON.put("Tuesday", organizer.clone());
	        meetingsJSON.put("Wednesday", organizer.clone());
	        meetingsJSON.put("Thursday", organizer.clone());
	        meetingsJSON.put("Friday", organizer.clone());
	        meetingsJSON.put("Saturday", organizer.clone());
	        meetingsJSON.put("Sunday", organizer.clone());
	        
	        organizer.put("ASD", "DSA");
	        
	        System.out.println(meetingsJSON.toJSONString());
	        
	        try (FileWriter file = new FileWriter(appdataDir + "/CallOpener/meetings.json")) {
	            file.write(meetingsJSON.toJSONString());
	        } catch (IOException e1) {
	            e.printStackTrace();
	        }
			
		}
		
		try {
			
			JSONParser jsonParser = new JSONParser();
			FileReader reader = new FileReader(appdataDir + "/CallOpener/settings.json");
			Object obj = jsonParser.parse(reader);
	        settingsJSON = (JSONObject) obj;
        
		} catch (ParseException e) {
			
			
			
		} catch (FileNotFoundException e) {
			
			System.out.println("No File");
			
			settingsJSON = new JSONObject();
			settingsJSON.put("earlyInt", "0");
			settingsJSON.put("alarmBool", "false");
			settingsJSON.put("promptBool", "false");
			settingsJSON.put("enabledBool", "true");
			
			try (FileWriter file = new FileWriter(appdataDir + "/CallOpener/settings.json")) {
	            file.write(settingsJSON.toJSONString());
	        } catch (IOException e1) {
	            e.printStackTrace();
	        }
			
		}
		

		
		
		stage = primaryStage;
		
		

		System.out.println(getClass().getResourceAsStream("main.fxml"));
		
		
		loader = new FXMLLoader(getClass().getResource("main.fxml"));
		mainPane = (Pane) loader.load();
		mainController = loader.getController();
		
		loader = new FXMLLoader((getClass().getResource("editor.fxml")));
		editorPane = (Pane) loader.load();
		editorController = loader.getController();
		
		editorStage = new Stage();
    	editorStage.setScene(new Scene(Main.editorPane));
    	editorStage.initModality(Modality.APPLICATION_MODAL);
    	editorStage.setResizable(false);
		
		loader = new FXMLLoader((getClass().getResource("upcoming.fxml")));
		upcomingPane = (Pane) loader.load();
		upcomingController = loader.getController();
		
		upcomingStage = new Stage();
		upcomingStage.setScene(new Scene(Main.upcomingPane));
		upcomingStage.initModality(Modality.APPLICATION_MODAL);
		upcomingStage.setResizable(false);
		
		loader = new FXMLLoader((getClass().getResource("prompt.fxml")));
		promptPane = (Pane) loader.load();
		promptController = loader.getController();
		
		promptStage = new Stage();
		promptStage.setScene(new Scene(Main.promptPane));
		promptStage.initModality(Modality.APPLICATION_MODAL);
		promptStage.setResizable(false);
		promptStage.setAlwaysOnTop(true);
		promptStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
			public void handle(WindowEvent event) {
		        event.consume();
		    }
		});
		
		loader = new FXMLLoader((getClass().getResource("reminder.fxml")));
		reminderPane = (Pane) loader.load();
		reminderController = loader.getController();
		
		reminderStage = new Stage();
		reminderStage.setScene(new Scene(Main.reminderPane));
		reminderStage.initModality(Modality.APPLICATION_MODAL);
		reminderStage.setResizable(false);
		reminderStage.setAlwaysOnTop(true);
		reminderStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
			public void handle(WindowEvent event) {
		        event.consume();
		    }
		});
		
		loader = new FXMLLoader((getClass().getResource("corrector.fxml")));
		correctorPane = (Pane) loader.load();
		correctorController = loader.getController();
		
		correctorStage = new Stage();
		correctorStage.setScene(new Scene(Main.correctorPane));
		correctorStage.initModality(Modality.APPLICATION_MODAL);
		correctorStage.setResizable(false);
		correctorStage.setAlwaysOnTop(true);
		correctorStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
			public void handle(WindowEvent event) {
		        event.consume();
		    }
		});
//		
		Scene scene = new Scene(mainPane);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Video Call Opener");
		
		primaryStage.setResizable(false);
		
		Thread updater = new Thread(new Updater());
		updater.start();
		
		primaryStage.setOnCloseRequest(e -> {updater.stop();});
		
		stage.getIcons().add(icon);
		editorStage.getIcons().add(icon);
		upcomingStage.getIcons().add(icon);
		correctorStage.getIcons().add(icon);
		promptStage.getIcons().add(icon);
		reminderStage.getIcons().add(icon);
		
		primaryStage.show();
		
		
		
	}
	
	public static int convertTimeToMinutes(String time) {
		
		boolean amBool = false;
		if(time.split(" ")[1].equals("am")) amBool = true;
		
		int[] timeArray = new int[2]; 
		
		if (amBool) {
			if (time.split(":")[0].equals("12")) timeArray[0] = 0;
			else timeArray[0] = Integer.parseInt(time.split(":")[0]);
			timeArray[1] = Integer.parseInt(time.split(":")[1].substring(0, 2));
		} else {
			timeArray[0] = Integer.parseInt(time.split(":")[0]) + 12;
			timeArray[1] = Integer.parseInt(time.split(":")[1].substring(0, 2));
		}
		

		return 60 * timeArray[0] + timeArray[1];
		
	}
	
	public static int getMinutesSinceMidnight() {         

	    DateFormat dateFormat = new SimpleDateFormat();      
	    java.util.Date date = new java.util.Date();

	    dateFormat = new SimpleDateFormat("HH");
	    date = new java.util.Date();
	    int hour = Integer.parseInt(dateFormat.format(date));         

	    dateFormat = new SimpleDateFormat("mm");
	    date = new java.util.Date();
	    int minute = Integer.parseInt(dateFormat.format(date));
	    

	    return (hour * 60) + (minute);      

	 }
	
}
			