package com.lupo.callopener;

import javafx.application.Platform;

public class Updater implements Runnable {

	@Override
	public void run() {
		
		try {
		
			
	    	long time = System.currentTimeMillis();
	    	Thread caller = new Thread(new Caller());
	    	
	    	
	    	
	    	Thread.sleep(60000 - (time % 60000));
	    	
	    	while(true) {
	    		
	    		Platform.runLater(caller);
	    		Thread.sleep(60000);
	    		
	    	}
		
		
		} catch (InterruptedException e) {
			
			
		}
	    	
		
	}

}
