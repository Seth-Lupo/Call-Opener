package com.lupo.callopener;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class CorrectorController {

    @FXML
    private Button closeButton;

    @FXML
    private Label nameText;

    @FXML
    private Text contentText;

    @FXML
    void closeButtonAct(ActionEvent event) {
    	
    	Main.correctorStage.close();
    	
    }
    
    public void editContent(String string) {
    	
    	contentText.setText(string);
    	
    }

}
