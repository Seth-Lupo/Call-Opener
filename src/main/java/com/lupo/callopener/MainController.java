package com.lupo.callopener;

import java.io.FileWriter;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MainController {
	
    @FXML
    private Button buttonTue;

    @FXML
    private Button buttonDisable;

    @FXML
    private Button buttonWed;

    @FXML
    private Button buttonSat;

    @FXML
    private TextField textEarly;

    @FXML
    private CheckBox checkPrompt;

    @FXML
    private CheckBox checkAlarm;

    @FXML
    private Button buttonMon;

    @FXML
    private Button buttonSun;

    @FXML
    private Button buttonFri;

    @FXML
    private Button buttonUpcoming;

    @FXML
    private Button buttonThu;
    
    @FXML
    public void initialize() {
    	
    	System.out.println(Main.settingsJSON.get("earlyInt"));
    	textEarly.setText((String) Main.settingsJSON.get("earlyInt"));
    	
    	if (Main.settingsJSON.get("alarmBool").equals("true")) checkAlarm.setSelected(true);
    	if (Main.settingsJSON.get("promptBool").equals("true")) checkPrompt.setSelected(true);
    	if (Main.settingsJSON.get("enabledBool").equals("false")) {
    		
    		buttonDisable.setText("Meetings Disabled");
    		buttonDisable.setStyle("-fx-background-color: #94534B; -fx-text-fill: #FFFFFF;");
    		
    	}
    
    	
    	textEarly.textProperty().addListener((obs, oldText, newText) -> {
    	    
    		textEarlyAct(newText);
 
    	});
    	
    }
    
    public void openEditor(String day) {
    	
    	Main.editorController.loadEditor(day);
    	Main.editorStage.show();	
    	
    }
    
    @FXML
    void buttonMonAct(ActionEvent event) {
    	openEditor("Monday");
    	
    }

    @FXML
    void buttonTueAct(ActionEvent event) {
    	openEditor("Tuesday");
    }

    @FXML
    void buttonWedAct(ActionEvent event) {
    	openEditor("Wednesday");
    }

    @FXML
    void buttonThuAct(ActionEvent event) {
    	openEditor("Thursday");
    }

    @FXML
    void buttonFriAct(ActionEvent event) {
    	openEditor("Friday");
    }

    @FXML
    void buttonSatAct(ActionEvent event) {
    	openEditor("Saturday");
    }

    @FXML
    void buttonSunAct(ActionEvent event) {
    	openEditor("Sunday");
    }

    @FXML
    void buttonUpcomingAct(ActionEvent event) {
    	
    	Main.upcomingController.loadUpcoming();
    	Main.upcomingStage.show();	
    }
   
    void textEarlyAct(String newText) {
    	
    	newText = newText.replaceAll("[^\\d]", "");
    	
    	if (newText.length() > 2) newText = newText.substring(0, 2);
    	
    	textEarly.setText(newText);
        
    	
    	Main.settingsJSON.put("earlyInt", newText);
    	
    	try (FileWriter file = new FileWriter(Main.appdataDir + "/CallOpener/settings.json")) {
    		
    		file.write(Main.settingsJSON.toJSONString());
	   
    	} catch (IOException e) {
	    	
    		e.printStackTrace();
	   
    	}
    	
    }
    
    

    @FXML
    void checkAlarmAct(ActionEvent event) {
    	
    	
    	
    	System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAA PAY ATTENTION TO ME");
    	
    	
    	Main.settingsJSON.put("alarmBool", Boolean.toString(checkAlarm.isSelected()));
    	
    	try (FileWriter file = new FileWriter(Main.appdataDir + "/CallOpener/settings.json")) {
    		
    		file.write(Main.settingsJSON.toJSONString());
	   
    	} catch (IOException e) {
	    	
    		e.printStackTrace();
	   
    	}
    }

    @FXML
    void checkPromptAct(ActionEvent event) {
    	
    	Main.settingsJSON.put("promptBool", Boolean.toString(checkPrompt.isSelected()));
    	
    	try (FileWriter file = new FileWriter(Main.appdataDir + "/CallOpener/settings.json")) {
    		
    		file.write(Main.settingsJSON.toJSONString());
	   
    	} catch (IOException e) {
	    	
    		e.printStackTrace();
	   
    	}
    }

    @FXML
    void buttonDisableAct(ActionEvent event) {
    	
    	if (buttonDisable.getText().equals("Meetings Enabled")) {
    		Main.settingsJSON.put("enabledBool", "false");
    		buttonDisable.setText("Meetings Disabled");
    		buttonDisable.setStyle("-fx-background-color: #94534B; -fx-text-fill: #FFFFFF;");
    	} else {
    		
    		Main.settingsJSON.put("enabledBool", "true");
    		buttonDisable.setText("Meetings Enabled");
    		buttonDisable.setStyle("-fx-background-color:  #7C655C; -fx-text-fill: #FFFFFF;");
    	}
    	
    	try (FileWriter file = new FileWriter(Main.appdataDir + "/CallOpener/settings.json")) {
    		
    		file.write(Main.settingsJSON.toJSONString());
	   
    	} catch (IOException e) {
	    	
    		e.printStackTrace();
	   
    	}
    	

    }

}
