package creators;

import instruments.Eraser;
import instruments.Pencil;

public class Artist {
	 public void draw() throws InterruptedException {

	        Thread thread1 = new Thread(new Pencil());
	        Thread thread2 = new Thread(new Eraser());
	        thread1.setDaemon(true);
	        thread2.setDaemon(true);
	        thread1.start();
	        thread2.start();
	    
	        Thread.sleep(10000);
	     
	 }
}
