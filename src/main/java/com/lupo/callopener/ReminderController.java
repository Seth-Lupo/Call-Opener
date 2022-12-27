package com.lupo.callopener;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class ReminderController {

    @FXML
    private Button closeButton;

    @FXML
    private Label nameText;
    
    private MediaPlayer mediaPlayer;
    
    @FXML
    private ImageView imageView;

	public boolean alarm;
    
    @FXML
    void closeButtonAct(ActionEvent event) {
    	
    	

    	if(alarm) mediaPlayer.pause();
    	Main.reminderStage.close();
    }
    
    @FXML
    public void initialize() {
    	
    	
    			
    			imageView.setImage(Main.icon);
    		
    			
    			mediaPlayer = new MediaPlayer(Main.sound);
        		mediaPlayer.setOnEndOfMedia(new Runnable() {
    	    		public void run() {
    	    			mediaPlayer.seek(Duration.ZERO);
    	    	    }
        		});
    		
    		
    		
    	
    }
    
    public void loadPrompt(String name, boolean alarm) {
    	
    	nameText.setText(name);
	    this.alarm = alarm;
    	
    	if(alarm) {
    	
    		mediaPlayer.seek(new Duration(0));
	    	mediaPlayer.setAutoPlay(true);  
	    	mediaPlayer.play();
	    	
    	}
    }

}
