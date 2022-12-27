package com.lupo.callopener;

import java.awt.List;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONObject;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditorController {

    @FXML
    private TextField t4;

    @FXML
    private TextField t5;

    @FXML
    private TextField t6;

    @FXML
    private TextField n1;

    @FXML
    private TextField t7;

    @FXML
    private TextField n2;

    @FXML
    private TextField t8;

    @FXML
    private TextField n3;

    @FXML
    private TextField t9;

    @FXML
    private TextField n4;

    @FXML
    private TextField n5;

    @FXML
    private TextField n6;

    @FXML
    private TextField n7;

    @FXML
    private TextField n8;

    @FXML
    private TextField n9;

    @FXML
    private TextField n10;

    @FXML
    private Button buttonSave;

    @FXML
    private TextField t10;

    @FXML
    private TextField u10;

    @FXML
    private Label editorTitle;

    @FXML
    private TextField u1;

    @FXML
    private TextField u2;

    @FXML
    private TextField u3;

    @FXML
    private TextField u4;

    @FXML
    private TextField u5;

    @FXML
    private TextField u6;

    @FXML
    private TextField u7;

    @FXML
    private TextField u8;

    @FXML
    private TextField u9;

    @FXML
    private Button buttonClear;

    @FXML
    private Button buttonCancel;

    @FXML
    private TextField t1;

    @FXML
    private TextField t2;

    @FXML
    private TextField t3;
    
    private TextField[] nameArray;
    private TextField[] timeArray;
    private TextField[] urlArray;
    
    public JSONObject meetingsThisDay;
    
    @FXML
    public void initialize() {
    	
    	nameArray = new TextField[]{n1, n2, n3, n4, n5, n6, n7, n8, n9, n10};
    	timeArray = new TextField[]{t1, t2, t3, t4, t5, t6, t7, t8, t9, t10};
    	urlArray = new TextField[]{u1, u2, u3, u4, u5, u6, u7, u8, u9, u10};
    	
    	for (int i = 0; i < 10; i++) {
    		
    		addTextLimiter(nameArray[i], 20);
    		
    	}
    	
    }
    
    public void loadEditor(String day) {
    	
    	editorTitle.setText(day);
    	
    	meetingsThisDay = (JSONObject) Main.meetingsJSON.get(day);
    	
    	JSONObject names = (JSONObject) meetingsThisDay.get("Names");
    	JSONObject times = (JSONObject) meetingsThisDay.get("Times");
    	JSONObject urls = (JSONObject) meetingsThisDay.get("URLs");
    	
    	for(int i = 1; i <= 10; i++) {	
    		
    		String name = (String) names.get(Integer.toString(i));
    		
    		if(name != null) nameArray[i-1].setText(name);	
    		else nameArray[i-1].setText(name);
    		
    	}
    	
    	for(int i = 1; i <= 10; i++) {
    		
    		String time = (String) times.get(Integer.toString(i));
    		
    		if(time != null) timeArray[i-1].setText(time);	
    		else timeArray[i-1].setText(time);
    		
    	}
    	
    	for(int i = 1; i <= 10; i++) {
    		
    		String url = (String) urls.get(Integer.toString(i));
    		
    		if(url != null) urlArray[i-1].setText(url);	
    		else urlArray[i-1].setText(url);
    		
    	}
    	
    	
    	
    }

    @FXML
    void buttonSaveAct(ActionEvent event) {
    	
    	JSONObject names = new JSONObject();
    	JSONObject times = new JSONObject();
    	JSONObject urls = new JSONObject();
    	
    	for(int i = 1; i <= 10; i++) {   		
    		
    		if("".equals(nameArray[i-1].getText())) nameArray[i-1].setText(null);
    		
    		String name = nameArray[i-1].getText();
    		
    		if(name != null) {
    			name = name.trim();
    			names.put(Integer.toString(i), name);
    		}
    	
    	}
    	
    	ArrayList<String> duplicateCheckerList = new ArrayList<String>();
    	
    	for(int i = 1; i <= 10; i++) {   		
    		
    		if("".equals(timeArray[i-1].getText())) timeArray[i-1].setText(null);
    		
    		String time = timeArray[i-1].getText();
    		
    		if(time != null) {
    			time = time.trim();
    			
    			if (!validateTime(time)) {
    				
    				Main.correctorController.editContent("Make sure you format your time correctly. \n Ex. 6:12 am, 10:29 pm");
        			Main.correctorStage.show();
        			return;
    			}
    			
    			duplicateCheckerList.add(time);
    			times.put(Integer.toString(i), time);
    		}
    	
    	}
    	
    	for (String element: duplicateCheckerList) {
    		System.out.println(element);
    	}
    	
    	for (int j=0;j<duplicateCheckerList.size();j++) {
    	
    		for (int k=j+1;k<duplicateCheckerList.size();k++) {
    		
    			if (k!=j && duplicateCheckerList.get(k).contentEquals(duplicateCheckerList.get(j))) {
    				
    				Main.correctorController.editContent("You repeated times. Are you really that busy?");
        			Main.correctorStage.show();
    				return;
    				
    			}
    		}
    	}
    	
    	for(int i = 1; i <= 10; i++) {   		
    		
    		if("".equals(urlArray[i-1].getText())) urlArray[i-1].setText(null);
    		
    		String url = urlArray[i-1].getText();
    		
    		if(url != null) {
    			url = url.trim();
    			urls.put(Integer.toString(i), url);
    		}
    	
    	}
    	
    	for (int i = 1; i <= 10; i++) {
    		
    		boolean nameIsNull = (names.get(Integer.toString(i)) == null);
    		boolean timeIsNull = (times.get(Integer.toString(i)) == null);
    		boolean urlIsNull = (urls.get(Integer.toString(i)) == null);

    		if((nameIsNull || timeIsNull || urlIsNull) && (!nameIsNull || !timeIsNull || !urlIsNull)) {
    			
    			Main.correctorController.editContent("If you currently don't finish filling out the row, you won't be called for the meeting.");
    			Main.correctorStage.showAndWait();
    			Main.editorStage.close();
    			
    		}
    	}
    	
    	meetingsThisDay.clear();
    	meetingsThisDay.put("Names", names);
    	meetingsThisDay.put("Times", times);
    	meetingsThisDay.put("URLs", urls);
    	
    	try (FileWriter file = new FileWriter(Main.appdataDir + "/CallOpener/meetings.json")) {
    		
    		file.write(Main.meetingsJSON.toJSONString());
	   
    	} catch (IOException e) {
	    	
    		e.printStackTrace();
	   
    	}
    	
    	Main.editorStage.close();
    	
    }
    	
    @FXML
    void buttonCancelAct(ActionEvent event) {
    	
    	Main.editorStage.close();
    	
    }

    @FXML
    void buttonClearAct(ActionEvent event) {
    	
    	for (TextField name : nameArray) {
    		
    		name.clear();
    		
    	}
    	
    	for (TextField time : timeArray) {
    		
    		time.clear();
    		
    	}
    	
    	for (TextField url : urlArray) {
    		
    		url.clear();
    		
    	}
    	
    }
    
    boolean validateTime(String time) {
		
    	
    	char[] array = time.toCharArray();
    	
    	if (array.length == 7) {
    		
    		boolean char0 = (Character.isDigit(array[0])) && (array[0] != '0');
    		boolean char1 = (array[1] == ':');
    		boolean char2 = (Character.isDigit(array[2])) && Character.getNumericValue(array[2]) <= 5;
    		boolean char3 = (Character.isDigit(array[3]));
    		boolean char4 = (array[4] == ' ');
    		boolean char5 = (array[5] == 'a') || (array[5] == 'p');
    		boolean char6 = (array[6] == 'm');
    		
    		
    		return char0 && char1 && char2 && char3 && char4 && char5 && char6;
    		
    	} else if (array.length == 8) {
    		
    		boolean char0 = (array[0] == '1');
    		boolean char1 = (array[1] == '0') || (array[1] == '1') || (array[1] == '2');
    		boolean char2 = (array[2] == ':');
    		boolean char3 = (Character.isDigit(array[3])) && Character.getNumericValue(array[3]) <= 5;
    		boolean char4 = (Character.isDigit(array[4]));
    		boolean char5 = (array[5] == ' ');
    		boolean char6 = (array[6] == 'a') || (array[6] == 'p');
    		boolean char7 = (array[7] == 'm');
    		
    		return char0 && char1 && char2 && char3 && char4 && char5 && char6 && char7;
 	
    	} else return false;
    	
    }
    
    public static void addTextLimiter(final TextField tf, final int maxLength) {
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	                
            	if (tf.getText() != null) {
            		if (tf.getText().length() > maxLength) {
	                    String s = tf.getText().substring(0, maxLength);
	                    tf.setText(s);
	                }
            	}
            }
        });
    }

}
