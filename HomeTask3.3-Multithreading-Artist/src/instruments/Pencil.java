package instruments;

import creators.Artist;

public class Pencil implements Runnable{

	    @Override
	    public void run() {
	        while (true) { 
	            try {
	                Thread.sleep(1000);
	                synchronized (Artist.class) {
	                    System.out.println("Artist writing");
	                    Thread.sleep(800); 
	                    System.out.println("Artist don't writing...");
	                }
	            } catch (InterruptedException e) {
	           
	            }
	        }
	    }
}
