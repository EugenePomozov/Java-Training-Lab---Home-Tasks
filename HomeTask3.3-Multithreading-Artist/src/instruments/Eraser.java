package instruments;

import creators.Artist;

public class Eraser implements Runnable {

    @Override
    public void run() {
        while (true) { 
            try {
                Thread.sleep(1000);
                synchronized (Artist.class) {
                    System.out.println("Artist erasing");
                    Thread.sleep(400); 
                    System.out.println("Artist don't erasing...");
                }
            } catch (InterruptedException e) {

            }
        }
    }
}
