package com.lupo.callopener;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class PromptController {

    @FXML
    private Button joinButton;

    @FXML
    private Button hangButton;

    @FXML
    private Label nameText;
    
    @FXML
    private ImageView imageView;
    
    public String url;
	public MediaPlayer mediaPlayer;
	public Boolean alarm;

    @FXML
    void hangButtonAct(ActionEvent event) {
    	if(alarm)mediaPlayer.pause();
    	Main.promptStage.close();
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
    
    @FXML
    void joinButtonAct(ActionEvent event) throws IOException, URISyntaxException {
    	if(alarm)mediaPlayer.pause();
    	Desktop.getDesktop().browse(new URI(url));
    	Main.promptStage.close();
    }
    
    public void loadPrompt(String name, String url, boolean alarm) {
    	
    	nameText.setText(name);
    	this.url = url; 
    	this.alarm = alarm;
    	

    	if(alarm) {
    		
    		mediaPlayer.seek(new Duration(0));
	    	mediaPlayer.setAutoPlay(true);  
	    	mediaPlayer.play();
	    	
    	}
    	
    }

}
